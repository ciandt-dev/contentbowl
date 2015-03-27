package com.contentbowl.commons.social;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.contentbowl.commons.configuration.ConfigurationService;
import com.contentbowl.commons.configuration.ConfigurationServiceFactory;
import com.google.api.services.plus.model.Activity;

public class GooglePlusUtilsMock extends GooglePlusUtils {
    
    /** Configuration services */
    private static ConfigurationService confServ = ConfigurationServiceFactory.getConfigurationService("test");

    @Override
    public List<Activity> listActivities(String userId, Integer maxResults, String apiKey) throws IOException {
        List<Activity> activities = new ArrayList<Activity>();
        String strDir = confServ.get("googleplus.samples.dir");
        File fDir = new File(strDir);
        if (!fDir.exists()) {
            throw new RuntimeException("Sample dir <" + strDir + "> does not exist");
        }
        
        File[] filesInDir = fDir.listFiles();
        for ( int i = 0; (i < filesInDir.length) && (i < maxResults); i++ ) {
            String strFileContent = readFile( filesInDir[i] );
            Activity activity = fromJsonToActivity( strFileContent );
            activities.add(activity);
        }

        return activities;
    }
    
    /**
     * Convert a JSON file to an activity
     */
    private Activity fromJsonToActivity( String json ) {
        //TODO: implement this
        return new Activity();
    }
    
    /**
     * Read the content of the file
     * @param file
     * @return
     * @throws IOException
     */
    private String readFile( File file ) throws IOException {
        StringBuffer sb = new StringBuffer();
        
        FileReader freader = new FileReader( file );
        BufferedReader breader = new BufferedReader( freader );
        
        String line = null;
        while ( (line = breader.readLine()) != null ) {
            sb.append( line + "\n" );
        }
        
        breader.close();
        
        return sb.toString();
    }
}
