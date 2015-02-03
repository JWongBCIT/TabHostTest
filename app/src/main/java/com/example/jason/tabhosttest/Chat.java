package com.example.jason.tabhosttest;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;



public class Chat extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent call = getIntent();
        String s = call.getStringExtra("name");
        setTitle(s);
    }
}
