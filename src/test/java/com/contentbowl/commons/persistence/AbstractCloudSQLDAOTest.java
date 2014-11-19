package com.contentbowl.commons.persistence;

import junit.framework.Assert;

import org.junit.Test;

import com.contentbowl.commons.test.AbstractTestCase;

public class AbstractCloudSQLDAOTest extends AbstractTestCase {

	@Test
	public void shouldBeLocal() throws Exception {
		TestCloudSQLDAO dao = new TestCloudSQLDAO();
		Assert.assertTrue(dao.isRemote() == false);
	}
	
	@Test
	public void shouldBeAbleToCreateAConnection() throws Exception {
		TestCloudSQLDAO dao = new TestCloudSQLDAO();
		Assert.assertNotNull(dao.createConnection());
	}
	
	class TestCloudSQLDAO extends AbstractCloudSQLDAO {
		
	}
}
