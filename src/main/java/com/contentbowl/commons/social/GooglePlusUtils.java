package com.contentbowl.commons.social;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.contentbowl.commons.configuration.ConfigurationService;
import com.contentbowl.commons.configuration.ConfigurationServiceFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusRequestInitializer;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.Activity.PlusObject.Attachments.FullImage;
import com.google.api.services.plus.model.Activity.PlusObject.Attachments.Image;
import com.google.api.services.plus.model.ActivityFeed;

public class GooglePlusUtils {
    
    /** Singleton Instance */
    private static GooglePlusUtils _instance;
    
    private static final String GOOGLE_DRIVE_URL_REGEX = "https?://(drive|docs)\\.google\\.com[^\\s]*/(spreadsheets?|file|drawings?|documents?|presentations?)/[^\\s]*";

    /**
     * Constructor
     */
    GooglePlusUtils() {
     
    }
    
    /**
     * Return the singleton instance of this class
     */
    public static GooglePlusUtils getInstance() {
        if ( _instance == null ) {
            _instance = new GooglePlusUtils();
        }
        return _instance;
    }
    
    /**
     * Given an Image and a FullImage object, return a string with the URL
     * pointing to the image with original size.
     * 
     * This is necessary since Image and FullImage objects point go images that
     * are smaller than the original uploaded one.
     * 
     * @param image
     *            The image object
     * @param fullImage
     *            The object representing the original uploaded image
     * @return URL string pointing to the image with original size
     */
    public String getOriginalImageUrl(Image image, FullImage fullImage) {
        String imageDimensionUrlPart = "/w" + fullImage.getWidth() + "-" + "h" + fullImage.getHeight();
        return image.getUrl().replaceFirst("/w[0-9]{1,4}-h[0-9]{1,4}", imageDimensionUrlPart);
    }

    /**
     * List the public activities based on the userId
     * 
     * @param userId
     *            Google Plus user id
     * @param maxResults
     *            Maximum number of results
     * @param apiKey API Key to be used to get the activities
     */
    public List<Activity> listActivities(String userId, Integer maxResults, String apiKey) throws IOException {
        List<Activity> activities = new ArrayList<Activity>();
        Plus plus = this.initialize(apiKey);
        Plus.Activities.List listActivities = plus.activities().list(userId, "public");

        listActivities.setMaxResults(new Long(maxResults));

        // get the 1st page of activity objects
        ActivityFeed activityFeed = listActivities.execute();

        // unwrap the request and extract the pieces we want
        List<Activity> pageOfActivities = activityFeed.getItems();

        boolean endReached = false;

        // loop through until we arrive at an empty page
        while ((pageOfActivities != null) && (!endReached)) {
            for (Activity activity : pageOfActivities) {
                // it seems that the API is ignoring MaxResults... lets deal
                // with it explicitly
                if (activities.size() < maxResults.intValue()) {
                    activities.add(activity);
                    if (activities.size() == maxResults.intValue()) {
                        endReached = true;
                    }
                } else {
                    break;
                }
            }

            if (!endReached) {
                // we will know we are on the last page when the next page token
                // is null (in which case, break).
                if (activityFeed.getNextPageToken() == null) {
                    break;
                }

                // prepare to request the next page of activities
                listActivities.setPageToken(activityFeed.getNextPageToken());

                // execute and process the next page request
                activityFeed = listActivities.execute();
                pageOfActivities = activityFeed.getItems();
            }
        }

        return activities;
    }

    /**
     * Initialization
     * 
     * @param googleApiKey
     * @param applicationName
     */
    private Plus initialize(String apiKey) {
        GoogleCredential credential = new GoogleCredential();
        // initializes Google Plus
        JsonFactory jsonFactory = new JacksonFactory();
        HttpTransport httpTransport = new NetHttpTransport();
        Plus plus = new Plus.Builder(httpTransport, jsonFactory, credential).setApplicationName("Content Bowl")
                .setHttpRequestInitializer(credential)
                .setPlusRequestInitializer(new PlusRequestInitializer(apiKey)).build();
        return plus;
    }

    /**
     * Get some activities and save them in the disk for testing purposes
     * 
     * @param args
     *            Command line parameters
     */
    public static void main(String args[]) {
        try {
            File dir = new File("./samples/GooglePlus");
            if ( !dir.exists() ) {
                if (!dir.mkdirs()) {
                    System.err.println( "Error creating dir: " + dir.getAbsolutePath());
                    System.exit(1);
                }
            }
            GooglePlusUtils googlePlusUtils = GooglePlusUtils.getInstance();
            List<Activity> activities = googlePlusUtils.listActivities("118239183782204424177", 
                    50, "AIzaSyDZIKKCZiHmIyki0yyPWnEUrkgFzw09zUs");
            for (Activity activity : activities ) {
                File newFile = new File( "./samples/GooglePlus/" + activity.getId() + ".json");
                FileWriter writer = new FileWriter( newFile );
                BufferedWriter bufWriter = new BufferedWriter( writer );
                bufWriter.write(activity.toPrettyString());
                bufWriter.flush();
                bufWriter.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            System.exit(-1);
        }

        System.out.println( "Done!" );
        System.exit(0);
    }

}
