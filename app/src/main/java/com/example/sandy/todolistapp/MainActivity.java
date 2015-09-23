package com.example.sandy.todolistapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    static final int ADD_TASK_REQUEST = 1;

    ArrayAdapter<String> todolistAdapter;
    ArrayList<String> todolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listview);

        //TODO replace temporary list with loading list from stored memory
        // Initialize array to store to-do items
        // Temporarily the list is not saved
        todolist = new ArrayList<>();
        // Populate to do list with temp items
        todolist.add("Buy apples");
        todolist.add("Create App");
        todolist.add("Work on typing");

        // Initialize Adapter to display todolist array
        todolistAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todolist);
        listView.setAdapter(todolistAdapter);
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
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_add){
            openAddTask();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openAddTask(){
        // open the Add screen
        int requestCode = ADD_TASK_REQUEST;
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, requestCode);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_TASK_REQUEST){
            if (resultCode == RESULT_OK){
                String newTask = data.getStringExtra(AddTaskActivity.EXTRA_NEW_TASK);
                todolist.add(newTask);
                todolistAdapter.notifyDataSetChanged();
            }
        }

    }
}
