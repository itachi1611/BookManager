package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    private EditText edtUsername;
    private EditText edtPass;
    private CheckBox cbAutoLogin;
    private Button btnLogin;
    private TextView tvRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(MenuActivity.class);
                finish();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(RegisterActivity.class);
                finish();
            }
        });

    }

    private void initViews(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        cbAutoLogin = findViewById(R.id.cbAutoLogin);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
    }

}


