package com.contentbowl.sample.greetings.config;

import com.contentbowl.sample.greetings.api.CommonResource;
import com.contentbowl.sample.greetings.dao.GreetingDAO;
import com.contentbowl.sample.greetings.dao.ObjectifyGreetingDAO;
import com.contentbowl.sample.greetings.resources.GuestbookServlet;
import com.contentbowl.sample.greetings.resources.SignGuestbookServlet;
import com.google.inject.servlet.ServletModule;

public class GreetingsModule extends ServletModule {
	
	

	@Override
    protected void configureServlets() {
		
		//servlets
		serve("/guestbook").with(GuestbookServlet.class);
		serve("/sign").with(SignGuestbookServlet.class);
		
		//bindings
		bind(CommonResource.class);
		bind(GreetingDAO.class).to(ObjectifyGreetingDAO.class);
    }
}
