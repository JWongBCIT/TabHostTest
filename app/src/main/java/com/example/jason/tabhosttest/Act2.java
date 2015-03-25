package com.example.jason.tabhosttest;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Act2 extends Activity {
    public static MediaPlayer mp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_act);

        if(Act3.a == false)
            mp = MediaPlayer.create(Act2.this, R.raw.bleat);

        //Add listview of usernames
        final ListView lView = (ListView) findViewById(R.id.chatTextList);
        String[] tempList = {""};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempList);

        lView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                chat(lView.getAdapter().getItem(arg2).toString());
               if(Act3.a == true)
                    mp.start();
            }

        });
        lView.setAdapter(adapter);
    }
    public void chat(String name) {
        Intent chat = new Intent(this, ChatBubbleActivity.class);
        chat.putExtra("name", name);
        startActivity(chat);
    }
}