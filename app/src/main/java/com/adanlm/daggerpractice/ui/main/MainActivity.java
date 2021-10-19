package com.adanlm.daggerpractice.ui.main;

import android.os.Bundle;

import com.adanlm.daggerpractice.BaseActivity;
import com.adanlm.daggerpractice.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}