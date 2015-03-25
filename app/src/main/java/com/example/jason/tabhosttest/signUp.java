package com.example.jason.tabhosttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class signUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileOutputStream outputStream;

        try {
            InputStream inputStream = openFileInput("user.txt");
            Intent i = new Intent(signUp.this,MainActivity.class);
            startActivity(i);
        }catch (Exception e) {
            setContentView(R.layout.activity_sign_up);
        }
    }
    public void onSubmit(View view) {
        Intent i = new Intent(signUp.this,MainActivity.class);
        String username = ((EditText) findViewById(R.id.editText)).getText().toString();
        File file = new File(getApplicationContext().getFilesDir(), "user.txt");
        String filename = "user.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write((username).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        i.putExtra("username" , username);
        startActivity(i);
    }

}
