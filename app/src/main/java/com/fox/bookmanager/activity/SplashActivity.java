package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.view.View;

import com.fox.bookmanager.R;
import com.fox.bookmanager.activity.ui.login.LoginActivity;
import com.fox.bookmanager.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startNewActivity(LoginActivity.class);
                finish();
            }
        },1500);

    }

}
