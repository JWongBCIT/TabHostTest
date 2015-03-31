package com.example.jason.tabhosttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;


public class SplashActivity extends Activity implements MongoAdapter{
    private String username;
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
        JSONArray jsonarr = new JSONArray(result);
        ArrayList<String> users = new ArrayList<String>();
        for (int i = 0; i < jsonarr.length(); i++) {
            JSONObject row = jsonarr.getJSONObject(i);
            if(!row.getString("user").equals(username))
                users.add(row.getString("user"));
        }
        int myTimer = 3000;
        final ArrayList<String> finalUsers = users;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = openFileInput("user.txt");
                    java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
                    String username = s.next();
                    Intent i2 = new Intent(SplashActivity.this ,MainActivity.class);
                    i2.putStringArrayListExtra("users" , finalUsers);
                    i2.putExtra("user" , username);
                    startActivity(i2);
                    finish();
                }catch (Exception e) {
                    Intent i = new Intent(SplashActivity.this, signUp.class);
                    i.putStringArrayListExtra("users", finalUsers);
                    startActivity(i);
                    finish(); // close this activity
                }
            }
        }, myTimer);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            InputStream inputStream = openFileInput("user.txt");
            java.util.Scanner scan = new java.util.Scanner(inputStream).useDelimiter("\\A");
            username = scan.next();

        }catch (Exception e){
            e.printStackTrace();
        }
        Mongo.get(this ,"users" , new JSONObject());
    }
}