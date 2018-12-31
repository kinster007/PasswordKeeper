package com.alphacholera.passwordkeeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar().isShowing())
        getSupportActionBar().hide();
        SharedPreferences sp = getSharedPreferences("PasswordSharedPreference", 0);
        password = sp.getString("password", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (password.equals("")) {
                    // If there is no password, user is new here
                    Intent intent = new Intent(MainActivity.this, CreatePassword.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } else {
                    // If there is an old user, verify password
                    Intent intent = new Intent(MainActivity.this, PasswordVerification.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }
        }, 2000);
    }
}
