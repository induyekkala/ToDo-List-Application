package com.novare.IndividualProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskManager {
    
    private List<UserTask> usersTask = new ArrayList<UserTask>();
    
    //Creating File and FileWriter object for writing data into file 
    public File file;
    public FileWriter fileWriter;
    
    //Line_Separator variable insert a new 
    private static final String Line_Separator = "\n";
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
  //Reading the file path     
    private Path filePath = new File("/Users/induyekkala/ToDoList.csv").toPath();
    private Charset charset = Charset.defaultCharset(); 
   
   
    //Creating File header
    private static final String HEADER = "Id,Task_Title,DueDate,Status,Project_Name";
    
    //Adding Task to ArrayList
    public void addToDoList(UserTask user)
    {
	
	usersTask.add(user);
    }
    
    //Writing data to a CSV file
    public  void writeCsv(String filePath,UserTask user) {
	    
	    
	try {
	    file = new File(filePath);
	    fileWriter = new FileWriter(file,true);
	    if(file.length() == 0) {
		fileWriter.write(HEADER);
		fileWriter.append(Line_Separator);
	    }
	    		
	  fileWriter.append(String.valueOf(user.getTaskId()));
	  fileWriter.append("\t");
	  fileWriter.append(","); 
	  fileWriter.append(user.getTaskTitle());
	  fileWriter.append("\t");
	  fileWriter.append(","); 
	  fileWriter.append(""+user.getTaskDueDate());
	  fileWriter.append("\t");
	  fileWriter.append(","); 
	  fileWriter.append(user.getTaskStatus());
	  fileWriter.append("\t");
	  fileWriter.append(","); 
	  fileWriter.append(user.getProjectName());	
	  fileWriter.append(Line_Separator);
	    		
	System.out.println("Write to CSV file Succeeded!!!");
	}
        catch(Exception e)
        {
	e.printStackTrace(); 
        }
        finally
        {
	try
	{
	fileWriter.close();
	}
	catch(IOException ie)
	{
		System.out.println("Error occured while closing the fileWriter");
		ie.printStackTrace();
	}
      }
    }
    //Reading data from a CSV file
    
    public void readCSV(String filepath)
    {
        File file=new File(filepath);
        try
        {
		//read file into stream 
		try (Stream<String> stream = Files.lines(Paths.get(filepath))) {
		    
		       
			stream.forEach(System.out::println);
    	}
        }
        catch(IOException e)
        {
    	e.printStackTrace();
        }
        
     }

       
    //Sort the Tasks with  ProjectName
    public void sort_ProjectName()
    {	
	try
	{
	//reading the data from csv file in string format
	
        List<String> list = Files.readAllLines(filePath, charset); 
	list.remove(0);
	//passing into string array as array of strings
	String[] userTaskArray = list.toArray(new String[]{});
	
	
	//Sort the array using projectName 
	Arrays.sort(userTaskArray, new Comparator<String>(){
	    public int compare(String firstprojectName, String secondprojectName) {
		
	        return (firstprojectName.split(",")[4]).compareTo((secondprojectName.split(",")[4]));
	        
	    }
	});
	//print the sorted list
	System.out.println("TaskId"+"\t"+"TaskTitle"+"\t"+"Task Due Date" +"\t"+"Task Status"+"\t"+"Project Name");
	
	for (String sortedUserTask: userTaskArray){
	  
	    System.out.println(sortedUserTask+"\t");
	    System.out.println("\n");
	}
	
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
	}
         
    
    //Sort the Tasks with due Date
    public void sort_due_Date()
    {
	
	try
	{
	//Reading the data from csv file in string format
	List<String> taskList= Files.readAllLines(filePath, charset);
	
	//Removing the header from list
	taskList.remove(0);
	
	//Passing into string array as array of strings        
	String[] userTaskArray = taskList.toArray(new String[]{});

	//Sort the array using Date 
	    Arrays.sort(userTaskArray, new Comparator<String>(){
		
        //Comparing two string and the method return integer value		
	    public int compare(String firstTaskDate, String secondTaskDate)  {
		    
		try
		{
		    //Parsing the date to sort in order to day,month and year
		    return dateFormat.parse(firstTaskDate.split(",")[2]).compareTo(dateFormat.parse(secondTaskDate.split(",")[2]));
		    
		}
		//Catching parse exception for date
		catch(ParseException e)
		{
		    throw new IllegalArgumentException(e);
		}
		
	    }
	});
	//print the sorted list
	      System.out.println("TaskId" +"\t"+"TaskTitle"+"\t"+"Task Due Date" +"\t"+"Task Status"+"\t"+"Project Name");
	      for (String sortedUserTask: userTaskArray){
	      System.out.println(sortedUserTask); 
	      System.out.println("\n");
	      }
	     
	
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
	}
      
    //Write the data into CSV file
 
public void validateDate(String format){
   
    try
    {
    Date task_Date = null;
    task_Date = (Date)dateFormat.parse(format);
   
    }
    catch(ParseException e)
    {
	System.out.println("User must enter the correct format DD-MM-YYYY");
    }
   
}	

public boolean findTask(String taskTitle)
{
    
	//Reading the data from csv file in string format
    try
    {
	  int i=0;
	  boolean find=false;
	  List<String> taskList= Files.readAllLines(filePath, charset);
	  for(i=0;i<taskList.size();i++)
	      
	  {
	      String temp_title=taskList.get(i).split(",")[i];
	      if(temp_title.equals(taskTitle))
	      {
		  System.out.println("Task Title found in the record");
		  break;
	      }  
	      
	  }
	  
	  
	  }	    
	
    catch(Exception e)
    {
	e.printStackTrace();
    }
    return true;
}



}