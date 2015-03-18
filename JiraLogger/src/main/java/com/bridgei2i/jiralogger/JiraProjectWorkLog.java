/**
 * Copyright © 2015 Bridgei2i Analytics Pvt Ltd - All rights reserved.
 */
package com.bridgei2i.jiralogger;

import java.util.Iterator;

import com.atlassian.jira.rest.client.domain.Worklog;

/**
 * @author      Saddam Hussain
 * @version     0.1 
 * @since       2015-03-01
 * This class implements getter and setter for jira worklogs.
 */
public class JiraProjectWorkLog {

	private String userName;
	private String projactName;
	private Iterator<Worklog> projectWorkLog;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProjactName() {
		return projactName;
	}
	public void setProjactName(String projactName) {
		this.projactName = projactName;
	}
	public Iterator<Worklog> getProjectWorkLog() {
		return projectWorkLog;
	}
	public void setProjectWorkLog(Iterator<Worklog> projectWorkLog) {
		this.projectWorkLog = projectWorkLog;
	}
}
