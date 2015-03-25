package com.example.jason.tabhosttest;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Act3 extends Activity
{
    //final MediaPlayer mp = MediaPlayer.create(Act3.this, R.raw.bleat);
    boolean a = true;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_act);

    //push test lol
    }
  public void onClick(View view){
   // mplayer m = new mplayer();
    //  m.begin();
     // final MediaPlayer mp = MediaPlayer.create(Act3.this, R.raw.bleat);
       if(a) {
           Act1.mp.reset();
           a = false;
       }else if(!a){
           Act1.mp = MediaPlayer.create(Act3.this, R.raw.bleat);
       }
       // Button zero = (Button) this.findViewById(R.id.checkBox);
               // mp.pause();
    }
}