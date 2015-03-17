/**
 * Copyright (C) 2015 BRIDGEI2I Analytics Solutions - All rights reserved.
 */
package com.bridgei2i.jiralogger;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.domain.Worklog;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

/**
 * @author      Saddam Hussain
 * @version     0.1 
 * @since       2015-03-01
 * This class is the entry point of this application which will parse the command line arguments, fetch jira worklogs,
 *  and will create CSV log file. 
 */
public class JiraClientLogger {
	
	public static void main(String[] args) throws Exception{
    	String FilePath;
    	String jiraUrl;
    	String jiraPassword;
    	String jireUsername;
    	String jiraQuery;
    	String outFilePath;
    	
    	System.out.println("Application Started... This will take few seconds to Finish...");
    	//Parsing cmd line arguments
    	CommandParser cmd=new CommandParser(args);
    	
    	//Handling Cmd inputs from File
    	if(cmd.getMode()==1){
    		//Getting Property values from file
    		PropertiesCache pc=new PropertiesCache(cmd.getFilePath());
    		jiraPassword=pc.getJiraPassword();
    		jiraQuery=pc.getJiraFilter();
    		jiraUrl=pc.getJiraUrl();
    		jireUsername=pc.getJiraUname();
    		outFilePath=pc.getOutFilePath();
    	}else{ //Handling Cmd inputs From cmd line 
    		jiraPassword=cmd.getJiraPassword();
    		jiraQuery=cmd.getJiraFilter();
    		jiraUrl=cmd.getJiraUrl();
    		jireUsername=cmd.getJiraUname();
    		outFilePath=cmd.getOutFilePath();
    	}

    	// Construct the JRJC client
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        URI uri = new URI(jiraUrl);
        JiraRestClient jc = factory.createWithBasicHttpAuthentication(uri, jireUsername, jiraPassword);
        
       //Get issues for the Filter
        ArrayList<JiraProjectWorkLog> workList=new ArrayList<JiraProjectWorkLog>();
    	SearchResult sc= jc.getSearchClient().searchJql(jiraQuery).claim();
    	Iterator<BasicIssue> itr=sc.getIssues().iterator();
        while (itr.hasNext()) {
        	try{
	        	JiraProjectWorkLog jiraLog=new JiraProjectWorkLog();
	        	BasicIssue bi=itr.next();
	        	Issue issue = jc.getIssueClient().getIssue(bi.getKey()).claim();
	        	String userName=issue.getAssignee().getDisplayName();
	        	String projectName=issue.getProject().getName();
	        	jiraLog.setProjactName(projectName);
	        	jiraLog.setUserName(userName);
	        	jiraLog.setProjectWorkLog(issue.getWorklogs().iterator());
	        	workList.add(jiraLog);
        	}catch(Exception e){}
        	
        }
        
       //Map to create datewise log data for the users
       //Here key is Date::Project::user and value is time logged on this date for this project
       Map<String,Integer> logMap=new HashMap<String,Integer>();
       for(int i=0;i<workList.size();i++){
    	   JiraProjectWorkLog jiraLog=workList.get(i);
    	   Iterator<Worklog> workLog=jiraLog.getProjectWorkLog();
    	   while(workLog.hasNext()){
    		   Worklog wl=workLog.next();
    		   String key=jiraLog.getUserName()+"::"+(wl.getStartDate().toString().substring(0,10))+"::"+jiraLog.getProjactName();
    		   if(!logMap.containsKey(key)){
	        		logMap.put(key, wl.getMinutesSpent());
	        	}else{
	        		logMap.put(key, wl.getMinutesSpent()+((Integer)logMap.get(key)));
	        	}
    	   }
       }
       
       	Boolean result=ApplicationUtil.generateCSV(logMap,outFilePath);
       	if(result){
       		System.out.println("File Exported Successfully, Location :"+outFilePath);
       	}else{
       		System.out.println("Unable to Export the File");
       	}
        System.out.println("Done!!. Now exiting.");
        System.exit(0);
    
    }
}