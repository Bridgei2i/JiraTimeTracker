/**
 * Copyright (C) 2015 BRIDGEI2I Analytics Solutions - All rights reserved.
 */
package com.bridgei2i.jiralogger;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author      Saddam Hussain
 * @version     0.1 
 * @since       2015-03-01
 * This class implements method to sort a map key, And has method to create a csv file. 
 */
public class ApplicationUtil {

	//Sorting the map based on the key and return the sorted keys as list
	public static List sortMapKey(Map<String,Integer> logMap){
		List<String> mapKeys = new ArrayList<String>(logMap.keySet()); 
		Collections.sort(mapKeys);
		return mapKeys;
	}
	
	
	//Creating CSV for worklogs and saveing it in the filePath
	public static Boolean generateCSV(Map<String,Integer> logMap,String filePath){
		Boolean success=true;
		String fileHeader="User,Date,Project,Hours Spent \r\n";
		FileWriter fw= null;
		try{
			File file = new File(filePath);
			if(!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            fw.write(fileHeader);
            List<String> sortedMapKeys=sortMapKey(logMap);
            if(sortedMapKeys==null || sortedMapKeys.size()==0){
            	System.out.println("No Log Details Found!!");
            	fw.flush();
                fw.close();
            	return false;
            }
	        for (int i=0;i<sortedMapKeys.size();i++)
	        {
	        	String []fields=sortedMapKeys.get(i).split("::");
	        	fw.write(fields[0]+","+fields[1]+","+fields[2]+","+(logMap.get(sortedMapKeys.get(i))/60)+"\r\n");
	        }
	        fw.flush();
            fw.close();
		}catch(Exception e){
			return false;
		}
		return success;
	}
}
