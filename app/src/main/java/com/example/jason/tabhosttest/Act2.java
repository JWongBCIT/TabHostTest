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
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Act2 extends Activity implements MongoAdapter {
    public static MediaPlayer mp;
    @Override
    public String dbName() {
        return getResources().getString(R.string.DataBase);
    }

    @Override
    public String apiKey() {
        return getResources().getString(R.string.API_KEY);
    }
    @Override
    public void processResult(String result)  {
        InputStream inputStream = null;
        try {
            inputStream = openFileInput("user.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        String username = s.next();
        ArrayList<String> List = new ArrayList<String>();
        mp = MediaPlayer.create(Act2.this, R.raw.bleat);
        try {

            JSONArray jsonarr = new JSONArray(result);
            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject row = jsonarr.getJSONObject(i);
                List.add(row.getString("user"));
            }
        }catch (Exception e){
        }
        final ListView lView = (ListView) findViewById(R.id.chatTextList);
        String[] tempList;
        if(List.size() != 0) {
            tempList = List.toArray(new String[List.size()]);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempList);

            lView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    chat(lView.getAdapter().getItem(arg2).toString());
                    if (Act3.a == true)
                        mp.start();
                }

            });
            lView.setAdapter(adapter);
        }

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_act);
        Intent i = getIntent();
        mp = MediaPlayer.create(Act2.this, R.raw.bleat);
    }

    @Override
    protected void onResume() {
        super.onResume();
        InputStream inputStream = null;
        try {
            inputStream = openFileInput("user.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        String username = s.next();
        Mongo.get(this , username, new JSONObject());
    }

    public void chat(String name) {
        Intent chat = new Intent(this, ChatBubbleActivity.class);
        chat.putExtra("name", name);
        startActivity(chat);
    }
}