package com.example.jason.tabhosttest;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {
    // create the TabHost that will contain the Tabs

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Users");
        tab1.setContent(new Intent(this, Act1.class));

        tab2.setIndicator("Chats");
        tab2.setContent(new Intent(this, Act2.class));

        tab3.setIndicator("Settings");
        tab3.setContent(new Intent(this, Act3.class));


        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.WHITE);
        TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv1.setTextColor(Color.rgb(100, 65, 165));

        for (int i = 1; i < 3; i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.rgb(100, 65, 165));
            TextView tv2 = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv2.setTextColor(Color.WHITE);
        }

        //Change tab colour based on current tab
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                switch (getTabHost().getCurrentTab()) {
                    case 0:
                        for (int i = 1; i < 3; i++) {
                            getTabHost().getTabWidget().getChildAt(i).setBackgroundColor(Color.rgb(100, 65, 165));
                            TextView tv2 = (TextView) getTabHost().getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                            tv2.setTextColor(Color.WHITE);
                        }

                        getTabHost().getTabWidget().getChildAt(0).setBackgroundColor(Color.WHITE);
                        TextView tv1 = (TextView) getTabHost().getTabWidget().getChildAt(0).findViewById(android.R.id.title);
                        tv1.setTextColor(Color.rgb(100, 65, 165));
                        break;
                    case 1:
                        for (int i = 0; i < 3; i++) {
                            getTabHost().getTabWidget().getChildAt(i).setBackgroundColor(Color.rgb(100, 65, 165));
                            TextView tv2 = (TextView) getTabHost().getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                            tv2.setTextColor(Color.WHITE);
                        }

                        getTabHost().getTabWidget().getChildAt(1).setBackgroundColor(Color.WHITE);
                        TextView tv2 = (TextView) getTabHost().getTabWidget().getChildAt(1).findViewById(android.R.id.title);
                        tv2.setTextColor(Color.rgb(100, 65, 165));
                        break;
                    case 2:
                        for (int i = 0; i < 2; i++) {
                            getTabHost().getTabWidget().getChildAt(i).setBackgroundColor(Color.rgb(100, 65, 165));
                            TextView tv3 = (TextView) getTabHost().getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                            tv3.setTextColor(Color.WHITE);
                        }

                        getTabHost().getTabWidget().getChildAt(2).setBackgroundColor(Color.WHITE);
                        TextView tv3 = (TextView) getTabHost().getTabWidget().getChildAt(2).findViewById(android.R.id.title);
                        tv3.setTextColor(Color.rgb(100, 65, 165));
                        break;

                    default:
                        break;
                }
            }
        });
    }
}