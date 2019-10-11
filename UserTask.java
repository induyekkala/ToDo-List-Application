package com.novare.IndividualProject;

public class UserTask {
    
 private int taskId;
 private String taskTitle;
 private String taskStatus;
 private String taskdueDate;
 private String projectName;
 public UserTask() {
    super();
    // TODO Auto-generated constructor stub
   }

public UserTask(int taskId, String taskName, String taskdueDate,String taskStatus,  String projectName) {
    super();
    this.setTaskId(taskId);
    this.setTaskTitle(taskName);
    this.setTaskDueDate(taskdueDate);
    this.setTaskStatus(taskStatus);
   
    this.setProjectName(projectName);
}
public String getTaskTitle() {
    return taskTitle;
}

public void setTaskTitle(String taskTitle) {
    this.taskTitle = taskTitle;
}
public int getTaskId() {
    return taskId;
}
public void setTaskId(int taskId) {
    this.taskId = taskId;
}

public String getTaskStatus() {
    return taskStatus;
}
public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
}
public String getTaskDueDate() {
    return taskdueDate;
}
public void setTaskDueDate(String taskdueDate) {
    this.taskdueDate = taskdueDate;
}
public String getProjectName() {
    return projectName;
}
public void setProjectName(String projectName) {
    this.projectName = projectName;
}
public String toString() {
    return "TaskId="+taskId+"\n"+"TaskName="+taskTitle+"\n"+"TaskStatus="+taskStatus+"\n"+"TaskDate="+taskdueDate+"\n"+"ProjectName="+projectName;
}

 
 
}