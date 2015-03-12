package com.bridgei2i.gcal2jira;

import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.input.WorklogInput;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import org.joda.time.DateTime;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        makeconn();
    }
    
    public static void makeconn(){
        //final JiraRestClientFactory factory = new JiraRestClientFactory();
    	
        URI jiraServerUri;
		try {
			final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
			jiraServerUri = new URI("https://bridgei2i.atlassian.net/");
	        //final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "Kiran", "password");
	        final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "hussain.j@bridgei2i.com", "afzulthufail28");
//	        final NullProgressMonitor pm = new NullProgressMonitor();
	        final Issue issue = restClient.getIssueClient().getIssue("MG-10").claim();
	        final IssueRestClient irc= restClient.getIssueClient();

	        URI workloguri= issue.getWorklogUri();
	        URI issueuri=issue.getSelf();
	        DateTime dt=new DateTime();
	       // WorklogInput worklogInput=WorklogInput.create(issueuri, "testcomment", dt, 1);
	        //irc.addWorklog(workloguri, worklogInput);
//	        TimeTracking tt=issue.getTimeTracking();
//	        tt.
	       // System.out.println(issue);
	        //WorklogInput wl=WorklogInput.create(issueUri, comment, startDate, minutesSpent)
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public static void getCalendarEvents(){
    	
    }
    
}
