/**
 * Copyright � 2015 Bridgei2i Analytics Pvt Ltd - All rights reserved.
 */
package com.bridgei2i.jiralogger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
 
/**
 * @author      Saddam Hussain
 * @version     0.1 
 * @since       2015-03-01
 * This class is used to get the property values stored in the property file.
 */
public class PropertiesCache
{
	Properties prop = new Properties();
	InputStream input = null;
	private String jiraUrl;
	private String jiraUname;
	private String jiraPassword;
	private String jiraFilter;
	private String outFilePath;
	
	public PropertiesCache(String filePath){
		try{
			input = new FileInputStream(filePath);
			prop.load(input);
			jiraUrl=prop.getProperty("URL");
			jiraUname=prop.getProperty("USERNAME");
			jiraPassword=prop.getProperty("PASSWORD");
			jiraFilter=prop.getProperty("QUERY");
			outFilePath=prop.getProperty("FILEPATH");
		}catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
	public String getJiraUrl() {
		return jiraUrl;
	}
	public void setJiraUrl(String jiraUrl) {
		this.jiraUrl = jiraUrl;
	}
	public String getJiraUname() {
		return jiraUname;
	}
	public void setJiraUname(String jiraUname) {
		this.jiraUname = jiraUname;
	}
	public String getJiraPassword() {
		return jiraPassword;
	}
	public void setJiraPassword(String jiraPassword) {
		this.jiraPassword = jiraPassword;
	}
	public String getJiraFilter() {
		return jiraFilter;
	}
	public void setJiraFilter(String jiraFilter) {
		this.jiraFilter = jiraFilter;
	}
	public String getOutFilePath() {
		return outFilePath;
	}
	public void setOutFilePath(String outFilePath) {
		this.outFilePath = outFilePath;
	}

}