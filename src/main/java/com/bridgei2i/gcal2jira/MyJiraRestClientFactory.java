package com.bridgei2i.gcal2jira;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.BasicProject;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.IssueLink;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.domain.User;
import com.atlassian.jira.rest.client.domain.Worklog;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

public class MyJiraRestClientFactory {
	 private static final String JIRA_URL = "http://bridgei2i.atlassian.net";
	    private static final String JIRA_ADMIN_USERNAME = "hussain.j@bridgei2i.com";
	    private static final String JIRA_ADMIN_PASSWORD = "afzulthufail28";
	    public static void main(String[] args) throws Exception
	    {
	        // Print usage instructions
	        StringBuilder intro = new StringBuilder();
	        intro.append("**********************************************************************************************\r\n");
	        intro.append("* JIRA Java REST Client ('JRJC') example.                                                    *\r\n");
	        intro.append("* NOTE: Start JIRA using the Atlassian Plugin SDK before running this example.               *\r\n");
	        intro.append("* (for example, use 'atlas-run-standalone --product jira --version 6.0 --data-version 6.0'.) *\r\n");
	        intro.append("**********************************************************************************************\r\n");
	        System.out.println(intro.toString());

	        // Construct the JRJC client
	        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
	        URI uri = new URI(JIRA_URL);
	        JiraRestClient jc = factory.createWithBasicHttpAuthentication(uri, JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD);
	        
	       //Get total no.of isssues in a project
	        String currentUser="\"hussain.j@bridgei2i.com\"";
	        ArrayList<Iterator<Worklog>> workList=new ArrayList<Iterator<Worklog>>();
	        
	        SearchResult sc= jc.getSearchClient().searchJql("assignee ="+currentUser).claim();
	        Iterator<BasicIssue> itr=sc.getIssues().iterator();
	        while (itr.hasNext()) {
	        	BasicIssue bi=itr.next();
	        	Issue issue = jc.getIssueClient().getIssue(bi.getKey()).claim();
	        	String userName=issue.getAssignee().getDisplayName();
	        	String project=issue.getProject().getName();
	        	workList.add(issue.getWorklogs().iterator());
	        }
	        	
	        	Map<String,Integer> logMap=new HashMap<String,Integer>();
		        for(int i=0;i<workList.size();i++){
		        	Iterator<Worklog> worklogs=workList.get(i);
		        	while (worklogs.hasNext()) {
			        	Worklog wl=worklogs.next();
			        	String date=wl.getStartDate().toString().substring(0,10);
			        	System.out.println(date+"::"+wl.getMinutesSpent()+"::"+wl.getAuthor().getDisplayName());
			        	if(!logMap.containsKey(date)){
			        		logMap.put(date, wl.getMinutesSpent());
			        	}else{
			        		logMap.put(date, wl.getMinutesSpent()+((Integer)logMap.get(date)));
			        	}
		        	}
	        	}
	        	
		        for (Map.Entry<String,Integer> entry : logMap.entrySet())
		        {
		        	System.out.println(entry.getKey() + "::" + entry.getValue()+"min::"+(entry.getValue()/60)+"hrs");
		        }
	        System.out.println("Example complete. Now exiting.");
	        System.exit(0);
	    
	    }
}