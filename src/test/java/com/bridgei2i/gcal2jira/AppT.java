package com.bridgei2i.gcal2jira;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bridgei2i.gcal2jira.App;

public class AppT {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//@Test
	public void testMakeconn() {
		App.makeconn();
		fail("Not yet implemented");
	}
	
	@Test
	public void testGConn(){
		try {
			CalendarData.getCalData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
