package com.example.timeloveboy.activitywithmanyfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class Activity_Main  extends FragmentActivity {

    private Fragment[] fragments;
    private int fragment_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new Fragment[] { };
    }
}
