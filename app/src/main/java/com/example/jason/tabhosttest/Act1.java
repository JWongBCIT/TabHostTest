package com.example.jason.tabhosttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Act1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_act);

        //Add listview of usernames
        final ListView lView = (ListView) findViewById(R.id.userList);
        String[] tempList = new String[]{"Adam H", "Langaditis Speridon", "Jason W", "Joe Smith", "Bob Ross", "Timmy Lin", "Nicolas Cage"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempList);


        lView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                chat(lView.getAdapter().getItem(arg2).toString());
            }

        });


        lView.setAdapter(adapter);

    }

    public void chat(String name) {
        Intent chat = new Intent(this, Chat.class);
        chat.putExtra("name", name);
        startActivity(chat);
    }
}