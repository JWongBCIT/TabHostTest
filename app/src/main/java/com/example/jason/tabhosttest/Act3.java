package com.example.jason.tabhosttest;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Act3 extends Activity
{
    //final MediaPlayer mp = MediaPlayer.create(Act3.this, R.raw.bleat);
    static boolean a = true;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_act);
        try {
            InputStream inputStream = openFileInput("user.txt");
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
            String username = s.next();
            ((TextView) findViewById(R.id.textView5)).setText("Username: " + username);
        } catch (Exception e) {
            e.printStackTrace();
        }

    //push test lol
    }
  public void onClick(View view){
   // mplayer m = new mplayer();
    //  m.begin();
     // final MediaPlayer mp = MediaPlayer.create(Act3.this, R.raw.bleat);
      try {
          if (a) {
              a = false;
              Act1.mp.reset();
              Act2.mp.reset();
          } else if (!a) {
              a = true;
              Act1.mp = MediaPlayer.create(Act3.this, R.raw.bleat);
              Act2.mp = MediaPlayer.create(Act3.this, R.raw.bleat);
          }
      } catch (Exception e){
          e.printStackTrace();
      }
       // Button zero = (Button) this.findViewById(R.id.checkBox);
               // mp.pause();
    }
}