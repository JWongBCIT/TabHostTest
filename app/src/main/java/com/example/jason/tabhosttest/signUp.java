package com.example.jason.tabhosttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class signUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void onSubmit(View view) {
        Intent i = new Intent(signUp.this,MainActivity.class);
        String username = ((EditText) findViewById(R.id.editText)).getText().toString();
        String password = ((EditText) findViewById(R.id.editText2)).getText().toString();
        i.putExtra("username" , username);
        i.putExtra("password" , password);
        startActivity(i);
    }

}
