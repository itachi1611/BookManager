package com.fox.bookmanager.activity;

import android.os.BaseBundle;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.UserAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.UserDAO;
import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserActivity extends BaseActivity {

    private DBHelper dbHelper;
    private UserDAO userDAO;

    private RecyclerView lvList;
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

        User user = new User("admin","admin","","admin");
        userDAO.insertUser(user);

        users = userDAO.getAllUser();

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setAdapter(userAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViews(){
        dbHelper = new DBHelper(UserActivity.this);
        userDAO = new UserDAO(UserActivity.this);
        lvList = findViewById(R.id.lvList);
        userAdapter = new UserAdapter(this,users);
        linearLayoutManager = new LinearLayoutManager(this);
    }

}
