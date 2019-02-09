package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;

public class MenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
