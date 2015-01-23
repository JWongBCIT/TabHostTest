package com.example.jason.tabhosttest;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Act1 extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_act);

        //Add listview of usernames
        ListView lView = (ListView) findViewById(R.id.userList);
        String[] tempList = new String[]{"Adam H", "Langaditis Speridon", "Jason W"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempList);
        lView.setAdapter(adapter);
    }
}