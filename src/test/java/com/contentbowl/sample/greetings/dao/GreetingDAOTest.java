package com.contentbowl.sample.greetings.dao;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.contentbowl.commons.test.AbstractTestCase;
import com.contentbowl.sample.greetings.entity.Greeting;
import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.users.User;

public class GreetingDAOTest extends AbstractTestCase {
	
	private GreetingDAO dao;
	
	@Before
	public void setup() {
		dao = getInstance(ObjectifyGreetingDAO.class);
	}

	@Test
	public void shouldBeAbleToInsertAGreeting() {
		final Greeting greeting = new Greeting( new User("foo@ciandt.com", "ciandt.com"),
				"Testing", new Date());
		Long id = dao.insert(greeting);
		Assert.assertNotNull(id);
	}

}
