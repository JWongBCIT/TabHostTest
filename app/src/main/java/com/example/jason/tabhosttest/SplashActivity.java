package com.example.jason.tabhosttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;


public class SplashActivity extends Activity implements MongoAdapter{
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
        String [] users = new String[jsonarr.length()];
        for (int i = 0; i < jsonarr.length(); i++) {
            JSONObject row = jsonarr.getJSONObject(i);
            users[i] = row.getString("user");
        }
        int myTimer = 3000;
        final String[] finalUsers = users;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = openFileInput("user.txt");
                    Intent i2 = new Intent(SplashActivity.this ,MainActivity.class);
                    i2.putExtra("users" , finalUsers);
                    startActivity(i2);
                    finish();
                }catch (Exception e) {
                    Intent i = new Intent(SplashActivity.this, signUp.class);
                    i.putExtra("users", finalUsers);
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
        Mongo.get(this ,"users" , new JSONObject());
    }
}