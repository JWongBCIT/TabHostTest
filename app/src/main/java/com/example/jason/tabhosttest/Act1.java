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
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;

public class Act1 extends Activity implements MongoAdapter {
    public static MediaPlayer mp;
    public String username;
    public String currName;
    private boolean clicked = false;

    @Override
    public String dbName() {
        return getResources().getString(R.string.DataBase);
    }

    @Override
    public String apiKey() {
        return getResources().getString(R.string.API_KEY);
    }

    @Override
    public void processResult(String result) {
        try {
            JSONArray jsonarr = new JSONArray(result);
            JSONObject json = jsonarr.getJSONObject(0);
            if (json.get("user").equals(currName)) {

            }
        } catch (JSONException e) {
            JSONObject json2 = new JSONObject();
            JSONObject json3 = new JSONObject();
            try {
                json2.put("user", currName);
                Mongo.post(this, username, json2);
                json3.put("user", username);
                Mongo.post(this, currName, json3);
                Mongo.post(this, currName+username, new JSONObject());
                Mongo.post(this,username+currName, new JSONObject());

            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        Intent chat = new Intent(this, ChatBubbleActivity.class);
        chat.putExtra("name", currName);
        startActivity(chat);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_act);
        try {
            InputStream inputStream = openFileInput("user.txt");
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
            username = s.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent i = getIntent();
        mp = MediaPlayer.create(Act1.this, R.raw.bleat);
        //Add listview of usernames
        final ListView lView = (ListView) findViewById(R.id.userList);
        ArrayList<String> List =  i.getStringArrayListExtra("users");
        String [] tempList = List.toArray(new String[List.size()]);
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
        if(!clicked) {
            clicked = true;
            try {
                JSONObject json = new JSONObject();
                json.put("user", name);
                currName = name;
                Mongo.get(this, username, json);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                clicked = false;

            }
        }
    }
}