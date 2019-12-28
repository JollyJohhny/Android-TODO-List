package com.example.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    //    public static List<String> titleList;
//    public static List<String> dateList;
//    public static List<String> timeList;
    public static ArrayList<TaskType> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView TaskList;
    ArrayList<String> arrayNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i(TAG, "onCreate");

        FloatingActionButton fab = findViewById(R.id.fab);
        TaskList = findViewById(R.id.ListId);

//        arrayList = new ArrayList<>();
        if(arrayList != null)
        {
            for (TaskType i:arrayList){
            arrayNames.add(i.TaskTitle);
        }
        }





        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayNames);
        TaskList.setAdapter(adapter);

        TaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopup(view , position);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.SortDate) {
            Toast.makeText(getApplicationContext(), "You Clicked " + item.getTitle().toString(), Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.SortName){
            Toast.makeText(getApplicationContext(), "You Clicked " + item.getTitle().toString(), Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void AddTask(View v){
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);

    }

    public void showPopup(View v,final int i){

        PopupMenu popup= new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        Intent intent = new Intent(getBaseContext(), EditTask.class);
                        intent.putExtra("SESSION_ID", i);
                        startActivity(intent);
                        return true;
                    case R.id.del:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Confirm Deletion")
                                .setMessage("Do you really want to delete Task " + arrayNames.get(i) + " ?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        arrayList.remove(i);
                                        Toast.makeText(MainActivity.this, "Task Deleted!", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(getIntent());
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();
                        return true;
                    case R.id.view:
                        Intent intent2 = new Intent(getBaseContext(), ReadTask.class);
                        intent2.putExtra("SESSION_ID", i);
                        startActivity(intent2);

                    default:
                        return false;

                }
            }
        });
        popup.inflate(R.menu.popup_menu);
        popup.show();


    }

}
