package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.UserAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.UserDAO;
import com.fox.bookmanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends BaseActivity {

    private UserDAO userDAO;

    private RecyclerView lvList;
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<User> users;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

        User user = new User("admin","admin","123","admin");
        long a = userDAO.insertUser(user);
        Log.i("a",a + "");

        addRecycleView();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(AddUserActivity.class);
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViews(){
        users = new ArrayList<>();
        users.clear();
        userDAO = new UserDAO(UserActivity.this);
        lvList = findViewById(R.id.lvList);
        linearLayoutManager = new LinearLayoutManager(this);
        fab = findViewById(R.id.fab);
    }

    private void addRecycleView(){
        users = userDAO.getAllUser();
        userAdapter = new UserAdapter(this,users);

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setHasFixedSize(true);
        lvList.setAdapter(userAdapter);
    }

}
