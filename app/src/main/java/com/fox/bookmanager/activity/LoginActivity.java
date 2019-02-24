package com.fox.bookmanager.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.UserDAO;
import com.fox.bookmanager.model.User;

public class LoginActivity extends BaseActivity {

    private EditText edtUsername;
    private EditText edtPass;
    private CheckBox cbAutoLogin;
    private Button btnLogin,btnCancel;
    private SharedPreferences sharedPreferences;
    private UserDAO userDAO;
    private String username,password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        sharedPreferences = getSharedPreferences("USER_ACCOUNT",MODE_PRIVATE);
        edtUsername.setText(sharedPreferences.getString("USERNAME",""));
        edtPass.setText(sharedPreferences.getString("PASSWORD",""));
        cbAutoLogin.setChecked(sharedPreferences.getBoolean("STATUS",false));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUsername.setText("");
                edtPass.setText("");
            }
        });
    }

    private void initViews(){
        userDAO = new UserDAO(LoginActivity.this);
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        cbAutoLogin = findViewById(R.id.cbAutoLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
//        sharedPref = this.getPreferences(LoginActivity.MODE_PRIVATE);
    }

    private void checkLogin() {
        username = edtUsername.getText().toString().trim();
        password = edtPass.getText().toString().trim();
        if(password.length() < 5 || username.isEmpty() || password.isEmpty()){

            if(username.isEmpty()){
                edtUsername.setError(getString(R.string.notify_empty_user));
            }

            if(password.length() < 5){
                edtPass.setError(getString(R.string.notify_length_pass));
            }

            if(password.isEmpty()){
                edtPass.setError(getString(R.string.notify_empty_pass));
            }

        }else{
            User user = userDAO.getUser(username);

            if(user == null) {
                Toast.makeText(this, getString(R.string.notify_wrong_username_password), Toast.LENGTH_SHORT).show();
            }else{
                String passwordOnDB = user.USER_PASSWORD;
                if(passwordOnDB.equals(password)){
                    getUserPositionOnList();
                    rememberUser(username,password,cbAutoLogin.isChecked());
                    startNewActivity(MenuActivity.class);
                    finish();
                }else{
                    Toast.makeText(this, getString(R.string.notify_wrong_username_password), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void rememberUser(String u,String p,boolean status){
        sharedPreferences = getSharedPreferences("USER_ACCOUNT",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!status){
            editor.clear();
        }else{
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("STATUS",status);
        }
        editor.commit();
    }

    private void getUserPositionOnList(){
        sharedPreferences = getSharedPreferences("USER_POSITION",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("POSITION",userDAO.getPosition(username));
        Log.e("e",userDAO.getPosition(username) + "");
        editor.commit();
    }

}


