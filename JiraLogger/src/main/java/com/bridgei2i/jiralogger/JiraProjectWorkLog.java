package com.bridgei2i.jiralogger;

import java.util.Iterator;

import com.atlassian.jira.rest.client.domain.Worklog;

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
