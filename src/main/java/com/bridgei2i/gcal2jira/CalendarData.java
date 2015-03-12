package com.bridgei2i.gcal2jira;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.calendar.Calendar.Calendars;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.calendar.Calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Lists;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class CalendarData {

	  /**
	   * Be sure to specify the name of your application. If the application name is {@code null} or
	   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
	   */
	  private static final String APPLICATION_NAME = "";

	  /** Directory to store user credentials. */
	  private static final java.io.File DATA_STORE_DIR =
	      new java.io.File(System.getProperty("user.home"), ".store/calendar_sample");

	  /**
	   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
	   * globally shared instance across your application.
	   */
	  private static FileDataStoreFactory dataStoreFactory;
	  
	  /** Global instance of the HTTP transport. */
	  private static HttpTransport httpTransport;

	  /** Global instance of the JSON factory. */
	  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	  private static com.google.api.services.calendar.Calendar client;

	  static final java.util.List<Calendar> addedCalendarsUsingBatch = Lists.newArrayList();

	  /** Authorizes the installed application to access user's protected data. */
	  public static Credential authorize() throws Exception {

	   // load client secrets
	      GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
	          new InputStreamReader(CalendarData.class.getResourceAsStream("/client_secret.json")));
	      // set up authorization code flow
	      GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	          httpTransport, JSON_FACTORY, clientSecrets,
	          Collections.singleton(CalendarScopes.CALENDAR_READONLY)).setDataStoreFactory(
	          dataStoreFactory).build();
	      // authorize
	      return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	  }
	  
	  public static void getCalData(){
		  Credential credential;
		  try {
			  // initialize the transport
			  httpTransport = GoogleNetHttpTransport.newTrustedTransport();

			  // initialize the data store factory
			  dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
			  credential = authorize();
			  // set up global Calendar instance
			  client = new com.google.api.services.calendar.Calendar.Builder(
					  httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
			  showCalendars();
		  } catch (Exception e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }

	  }
	  
	  private static void showCalendars() throws IOException {
		    View.header("Show Calendars");
		    CalendarList feed = client.calendarList().list().execute();
		    View.display(feed);
		  }
	  
	  private static void listmy() throws IOException{
		  com.google.api.services.calendar.Calendar.CalendarList cds = null;
		 // for(CalendarListEntry cd:((CalendarList) cds).getItems()){
			  
		  //}
		  client.calendarList().get("username@bridgei2i.com");
	  }

}
