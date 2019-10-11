package com.novare.IndividualProject;

import java.io.*;
import java.util.Random;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Date;
import java.util.List;


public class ToDoListTest {
   
    //Declaration of variables
    private static AtomicInteger count = new AtomicInteger(0); 
    private  int taskId;
    public static void main(String args[]) {
	
	TaskManager taskManager=new TaskManager();
	
//	 Date format
	 DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	 UserTask user=new UserTask();
	 
	 //to read input values
	 
	 Scanner input=new Scanner(System.in);	 
	 int read = 0;
		boolean exit = false;
		String filePath = "/Users/induyekkala/ToDoList.csv";		

		while (!exit) {
		    
		    //To display Menu List
		    printCommand();
		    
		    //Read the choice value from user between 0 to 4
		    System.out.println("Enter your choices");
		    read = input.nextInt();
		    input.nextLine();

		    switch (read) {
		    //print the Menu
		    
		     // List the tasks
		    case 1:
			 
			  System.out.println("starting read user.csv file");
			  taskManager.readCSV(filePath);
		
			break;
	            // Add new task
		    case 2:
			try {
			    
			 int   taskId = count.incrementAndGet(); 
			    //Enter the title of the task
			    System.out.println("Enter the Task Title");		   
			    String taskTitle=input.nextLine();
			    System.out.println(" ");
			    //Enter the correct date format
			    System.out.println("Enter the Date in this format DD-MM-YYYY");		   
			    String taskDate=input.next();   
			    input.nextLine();
			    taskManager.validateDate(taskDate);
			    //Intialize date to null
		            Date taskDueDate = null; 
		            //parse the date
		            taskDueDate = (Date)formatter.parse(taskDate); 
		            //converting date to string format
		            String dueDate = formatter.format(taskDueDate);
		          
			    //Enter the status of the task
			    System.out.println("Enter the Status of the Task");
			    String taskStatus=input.nextLine();
			   
			    System.out.println("Enter the Novare SDA Lund,Novare SDA Stockholm are the project lists");		   
			    
			    String projectName=input.nextLine();
			   
			    //setting the project names
			    String list_projectName[]= {"Novare SDA Lund","Novare SDA Stockholm"};
			    
			    if(list_projectName[0].equals(projectName) || list_projectName[1].equals(projectName) )
			    {
				//passing values to constructor 
				 user=new UserTask(taskId ,taskTitle,dueDate,taskStatus,projectName);
				//Adding Task to the list
				 taskManager.addToDoList(user);	  
				 //writing task to the file
				 taskManager.writeCsv(filePath,user);
			    }
			    else
			    {
				System.out.println("check the project names you entered wrong values");
				
			    }
					   	  
			   //catching an ParseException for Date.
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			break;
                         //sort the task by projectname			
		    case 3:
			taskManager.sort_ProjectName();
			break;
		       //sort the task by due date
		    case 4:
			taskManager.sort_due_Date();
			break;
			
	    /*
	     * case 5:System.out.println("Enter the task title to find"); String
	     * taskTitle=input.nextLine(); taskManager.findTask(taskTitle);
	     */
			 
		
		    case 5: System.exit(0);	
		          break;
			
			
		   
		    }

		}

	    }
            //to print the menu

	    public static void printCommand() {
		System.out.println("\nWelcome to ToDoList"+
			"\nYou have X tasks todocompleted and Y tasks are done!"+
			 "\n Pick an option:"+
			 "\n Press 1:List the tasks" + 
			 "\n Press 2:Add new task"
			+ "\n Press 3:Sort the tasks by projectname" + 
			"\n Press 4:Sort the tasks by due date"+"\n Press 5:Exit the application");

	    }
	
	
    }
    
    
    
    


