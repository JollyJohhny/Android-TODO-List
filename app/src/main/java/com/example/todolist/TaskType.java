package com.example.todolist;

public class TaskType {
    public String TaskTitle;
    public String TaskDate;
    public String TaskTime;

    public TaskType(){

    }

    public TaskType(String title, String date, String time){
        TaskTitle = title;
        TaskDate = date;
        TaskTime = time;
    }
}
