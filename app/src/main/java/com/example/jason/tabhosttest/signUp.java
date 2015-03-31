package com.example.jason.tabhosttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class signUp extends Activity implements MongoAdapter {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileOutputStream outputStream;
        setContentView(R.layout.activity_sign_up);

    }
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
        try {
            JSONArray jsonarr = null;
            jsonarr = new JSONArray(result);
            JSONObject json = jsonarr.getJSONObject(0);
            if (json.get("user").equals(username)) {
                ((TextView) findViewById(R.id.textView8)).setText(username + " is taken!");
            }
        } catch (JSONException e) {
            Intent extra = getIntent();
            ArrayList<String> users  = extra.getStringArrayListExtra("users");
            Intent i = new Intent(signUp.this, MainActivity.class);
            i.putStringArrayListExtra("users" , users);
            File file = new File(getApplicationContext().getFilesDir(), "user.txt");
            String filename = "user.txt";
            FileOutputStream outputStream;

            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write((username).getBytes());
                outputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            JSONObject json2 = new JSONObject();
            try {
                json2.put("user", username);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            Mongo.post(this, "users", json2);
            Intent last = getIntent();
            i.putExtra("user" , last.getStringExtra("user"));
            startActivity(i);
            finish();
        }

    }
    public void onSubmit(View view) {
        username = ((EditText) findViewById(R.id.editText)).getText().toString();
        JSONObject json = new JSONObject();
        try {
            json.put("user", username);
            Mongo.get(this,"users",json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
