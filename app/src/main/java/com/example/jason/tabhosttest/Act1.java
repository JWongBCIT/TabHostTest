package com.example.jason.tabhosttest;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Act1 extends Activity implements MongoAdapter {
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
    public void processResult(String result) throws JSONException {
        JSONArray jsonarr = null;
        jsonarr = new JSONArray(result);
        JSONObject json = jsonarr.getJSONObject(0);
        if (json.getString("chat").equals("")) {
        }


    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_act);
        Intent i = getIntent();
        mp = MediaPlayer.create(Act1.this, R.raw.bleat);
        //Add listview of usernames
        final ListView lView = (ListView) findViewById(R.id.userList);
        String[] tempList = i.getStringArrayExtra("users");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempList);


        lView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                chat(lView.getAdapter().getItem(arg2).toString());
                mp.start();

            }

        });


        lView.setAdapter(adapter);

    }

    public void chat(String name) {
        /*try {
            InputStream inputStream = openFileInput("user.txt");
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
            String username = s.next();
            JSONObject json = new JSONObject();
            json.put("chat" , name);
            Mongo.get(this,username, json);
        }catch (Exception e){
            e.printStackTrace();
        }*/

        Intent chat = new Intent(this, ChatBubbleActivity.class);
        chat.putExtra("name", name);
        startActivity(chat);
    }
}