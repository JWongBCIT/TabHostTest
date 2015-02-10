package com.example.jason.tabhosttest;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Chat extends ActionBarActivity {

    //String[] tempList = new String[]{"Adam H", "Langaditis Speridon", "Jason W", "Joe Smith", "Bob Ross", "Timmy Lin", "Nicolas Cage"};
    ArrayList<String> tempList;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent call = getIntent();
        String s = call.getStringExtra("name");
        setTitle(s);
    }

    public void onClick(View view) {
        EditText text = (EditText) findViewById(R.id.editText);
        Toast.makeText(this, text.toString(), Toast.LENGTH_LONG);
        try {
            tempList.add(text.toString());
        } catch (NullPointerException e){
        Toast.makeText(this, "Blank message", Toast.LENGTH_LONG);
        }

        final ListView lView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempList);

        lView.setAdapter(adapter);
    }
}
