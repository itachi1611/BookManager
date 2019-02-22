package com.fox.bookmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.UserDAO;
import com.fox.bookmanager.model.User;

public class AddUserActivity extends BaseActivity implements View.OnClickListener {

    private EditText edtUsername;
    private EditText edtPass;
    private EditText edtRePass;
    private EditText edtPhone;
    private EditText edtFullName;
    private Button btnAdd;
    private Button btnReset;
    private Button btnShow;
    private UserDAO userDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initViews();

        btnAdd.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }

    private void initViews(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        edtRePass = findViewById(R.id.edtRePass);
        edtPhone = findViewById(R.id.edtPhone);
        edtFullName = findViewById(R.id.edtFullName);
        btnAdd = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);
        btnShow = findViewById(R.id.btnShow);
        userDAO = new UserDAO(AddUserActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnAdd:
                checkUserData();
                break;
            case R.id.btnReset:
                resetData();
                break;
            case R.id.btnShow:
                finish();
                break;
        }
    }

    private void checkUserData(){
        User user = new User();
        user.USER_USER_NAME = edtUsername.getText().toString().trim();
        if(user.USER_USER_NAME.matches("")){
            edtUsername.setError("Username can not be empty!");
            return;
        }
        user.USER_PASSWORD = edtPass.getText().toString().trim();
        if(user.USER_PASSWORD.matches("")){
            edtPass.setError("Password can not be empty!");
            return;
        }
        String re_pass = edtRePass.getText().toString().trim();
        if(re_pass.matches("") || !re_pass.equals(edtPass.getText().toString().trim())){
            edtRePass.setError("Password not match!");
            return;
        }
        user.USER_PHONE = edtPhone.getText().toString().trim();
        if(user.USER_PHONE.matches("")){
            edtPhone.setError("Phone can not be empty!");
            return;
        }
        user.USER_FULL_NAME = edtFullName.getText().toString().trim();
        userDAO.insertUser(user);
        resetData();
    }

    private void resetData(){
        edtUsername.setText("");
        edtPass.setText("");
        edtRePass.setText("");
        edtPhone.setText("");
        edtFullName.setText("");
    }

}
