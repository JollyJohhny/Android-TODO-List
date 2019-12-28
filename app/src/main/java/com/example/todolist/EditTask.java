package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditTask extends AppCompatActivity {

    public EditText txtTitle;
    public EditText txtDate;
    public EditText txtTime;
    public int ElementId;

    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        getSupportActionBar().setTitle("Edit Task"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        txtTitle = findViewById(R.id.txtEditTitle);
        txtTime = findViewById(R.id.txtEdittime);
        txtDate = findViewById(R.id.txtEditDate);

        ElementId =getIntent().getIntExtra("SESSION_ID",-1);
        TaskType task = new TaskType();

        task = MainActivity.arrayList.get(ElementId);

        txtTitle.setText(task.TaskTitle);
        txtTime.setText(task.TaskTime);
        txtDate.setText(task.TaskDate);


    }

    public void setDate(View v) {
        // TODO Auto-generated method stub
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtDate.setText(sdf.format(myCalendar.getTime()));
    }


    public void setTime(View v) {
        // TODO Auto-generated method stub
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                txtTime.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
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

    public void EditTask(View v){
        Toast.makeText(getApplicationContext(), "Task Edited Successfully!", Toast.LENGTH_SHORT).show();
        String title = txtTitle.getText().toString();
        String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();

        TaskType task =  new TaskType(title,date,time);
        MainActivity.arrayList.set(ElementId,task);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
