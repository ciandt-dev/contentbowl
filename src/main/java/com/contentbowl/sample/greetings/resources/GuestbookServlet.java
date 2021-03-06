package com.contentbowl.sample.greetings.resources;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.contentbowl.commons.configuration.ConfigurationService;
import com.contentbowl.commons.configuration.ConfigurationServiceFactory;
import com.contentbowl.sample.greetings.dao.GreetingDAO;
import com.contentbowl.sample.greetings.entity.Greeting;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class GuestbookServlet extends HttpServlet {
	
	@Inject
	private GreetingDAO greetingDao;
	
	private ConfigurationService confService;
	
	@Inject
	private Logger logger;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		logger.info( "Executing GuestbookServlet" );
		
		confService = ConfigurationServiceFactory.getConfigurationService();
		logger.info( "App_name = " + confService.get("app_name") );
		
		//read greetings
	    List<Greeting> greetings = greetingDao.findGreetings();
		req.setAttribute( "greetings", greetings );
		req.setAttribute( "greetingsSize", greetings.size() );
		logger.info( "Putting " + greetings.size() + " greetings in memory" );
		
		//read user context
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    if (user != null) {
	    	req.setAttribute("userAuthenticated", "true");
	    	req.setAttribute("nickname", user.getNickname());
	    	req.setAttribute("logoutURL", userService.createLogoutURL(req.getRequestURI()));	    	
	    } else {
	    	req.setAttribute("userAuthenticated", "false");
	    	req.setAttribute("loginURL", userService.createLoginURL(req.getRequestURI()));
	    }
		
		req.getRequestDispatcher("/guestbook.jsp").forward(req, resp);
	}
}
