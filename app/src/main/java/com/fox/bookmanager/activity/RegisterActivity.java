package com.fox.bookmanager.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtPass;
    private Button btnLogin;
    private TextView tvBackLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

        tvBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(LoginActivity.class);
                finish();
            }
        });
    }

    private void initViews(){
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvBackLogin = findViewById(R.id.tvBackLogin);
    }

}
