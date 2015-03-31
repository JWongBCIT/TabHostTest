package com.example.jason.tabhosttest;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

public class ChatBubbleActivity extends ActionBarActivity implements MongoAdapter {
    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    private String curName;
    private String username;
    private boolean update = false;
    private static boolean brake = false;

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

        if (!update) {
            update = true;
            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject row = jsonarr.getJSONObject(i);
                try {
                    String message = row.getString("message");
                    boolean user = row.getBoolean("user");
                    sendChatMessage(user, message);
                } catch (JSONException e) {

                }
            }
        } else {
            List<ChatMessage> al = chatArrayAdapter.chatMessageList;
            for (int i = jsonarr.length() - 1; i > al.size(); i--) {
                JSONObject row = jsonarr.getJSONObject(i);
                try {
                    String message = row.getString("message");
                    boolean user = row.getBoolean("user");
                    sendChatMessage(user, message);

                } catch (Exception e) {

                }
            }
        }

    }
    @Override
    public void onBackPressed() {
        brake = true;
        NavUtils.navigateUpFromSameTask(this);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                brake = true;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6441a5")));
        setContentView(R.layout.activity_chat);
        Intent call = getIntent();
        String s = call.getStringExtra("name");
        setTitle(s);
        brake = false;
        update = false;
        try {
            InputStream inputStream = openFileInput("user.txt");
            java.util.Scanner scan = new java.util.Scanner(inputStream).useDelimiter("\\A");
            username = scan.next();

        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
        curName = s;

        buttonSend = (Button) findViewById(R.id.buttonSend);

        listView = (ListView) findViewById(R.id.listView1);
        listView.setDivider(null);
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.activity_chat_singlemessage);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.chatText);
        chatText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage(false, chatText.getText().toString(), true);
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage(false, chatText.getText().toString(), true);
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
        Mongo.get(ChatBubbleActivity.this, username + curName, new JSONObject());
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        if(brake){
                            return;
                        }
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (update)
                                    Mongo.get(ChatBubbleActivity.this, username + curName, null);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    private boolean sendChatMessage(boolean side, String message) {
        chatArrayAdapter.add(new ChatMessage(side, message));
        return true;
    }

    private boolean sendChatMessage(boolean side, String message, boolean New) {
        JSONObject message1 = new JSONObject();
        JSONObject message2 = new JSONObject();
        try {
            message1.put("message", chatText.getText().toString());
            message1.put("user", false);
            message2.put("message", chatText.getText().toString());
            message2.put("user", true);
            Mongo.post(this, username + curName, message1);
            Mongo.post(this, curName + username, message2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        chatArrayAdapter.add(new ChatMessage(side, message));
        chatText.setText("");
        return true;
    }
}

