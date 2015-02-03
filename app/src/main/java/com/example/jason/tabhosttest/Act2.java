package com.example.jason.tabhosttest;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class Act2 extends ListActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_act);

        ListView lView = (ListView) findViewById(R.id.userList);

        //Create arrays to hold user data
        String[] userList = new String[]{"Adam H", "Langaditis Speridon", "Jason W", "Nicolas Cage", "Timmy Lin"};
        String[] msgList = new String[]{"I wanna do something cool m8!", "Wanna get a Klean Kanteen bottle?", "How fun is Android", "I'm a great actor :)", "Meet up for coffee later??"};

        ArrayList<User> users = new ArrayList<User>();
        User u;

        //Copy data into User array
        for(int i = 0; i < userList.length; i++){
            u = new User();
            u.setName(userList[i]);
            u.setMessage(msgList[i]);

            users.add(u);
        }

        MyAdapter ad = new MyAdapter(this, users);
        /*lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }
        });*/
        setListAdapter(ad);

    }
    /*public void chat(String name){
        Intent chat = new Intent(this ,Chat.class);
        chat.putExtra("name" ,name);
        startActivity(chat);
    }*/
}