package com.contentbowl.commons.social;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.contentbowl.commons.test.AbstractTestCase;
import com.google.api.services.plus.model.Activity;

public class GooglePlusUtilsTest  extends AbstractTestCase {
    
    private static GooglePlusUtils googlePlusUtils = GooglePlusUtils.getInstance();
    private static GooglePlusUtils googlePlusUtilsMock = new GooglePlusUtilsMock(); 
    
    @Test
    public void shouldReadPostFromGooglePlus() throws Exception {
        
        Integer maxResults = 10;
        String apiKey = getTestProperty( "googleplus.apikey" );
        String userId = getTestProperty( "googleplus.user.id" );
        
        List<Activity> activities = null;
        if ( isOnline() ) {
            activities = googlePlusUtils.listActivities(userId, maxResults, apiKey);
        } else {
            activities = googlePlusUtilsMock.listActivities(userId, maxResults, apiKey);
        }
        Assert.assertNotNull(activities);
        Assert.assertEquals(maxResults.intValue(), activities.size());
        
    }

}
