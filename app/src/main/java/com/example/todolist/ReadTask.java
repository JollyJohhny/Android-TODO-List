package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReadTask extends AppCompatActivity {

    public TextView txtTitle;
    public TextView txtDate;
    public TextView txtTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_task2);

        getSupportActionBar().setTitle("View Task"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        txtTitle = findViewById(R.id.txtEditTitle);
        txtTime = findViewById(R.id.txtEdittime);
        txtDate = findViewById(R.id.txtShowDate);

        int id=getIntent().getIntExtra("SESSION_ID",-1);
        TaskType task = new TaskType();

        task = MainActivity.arrayList.get(id);

        txtTitle.setText(task.TaskTitle);
        txtTime.setText(task.TaskTime);
        txtDate.setText(task.TaskDate);
        FloatingActionButton fab = findViewById(R.id.fab);

    }

    public void AddTask(View v){
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
