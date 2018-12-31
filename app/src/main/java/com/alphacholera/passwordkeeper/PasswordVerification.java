package com.alphacholera.passwordkeeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordVerification extends AppCompatActivity implements View.OnClickListener{
    private TextView textView, passwordViewer;
    private Button[] buttons;
    private ImageView backSpace;
    private StringBuilder password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_verification);

        textView = findViewById(R.id.textView);
        textView.setText("Enter the password");
        buttons = new Button[10];
        password = new StringBuilder();
        buttons[0] = findViewById(R.id.buttonZero);
        buttons[1] = findViewById(R.id.buttonOne);
        buttons[2] = findViewById(R.id.buttonTwo);
        buttons[3] = findViewById(R.id.buttonThree);
        buttons[4] = findViewById(R.id.buttonFour);
        buttons[5] = findViewById(R.id.buttonFive);
        buttons[6] = findViewById(R.id.buttonSix);
        buttons[7] = findViewById(R.id.buttonSeven);
        buttons[8] = findViewById(R.id.buttonEight);
        buttons[9] = findViewById(R.id.buttonNine);
        backSpace = findViewById(R.id.buttonBackSpace);
        passwordViewer = findViewById(R.id.passwordViewer);

        for (Button button : buttons)
            button.setOnClickListener(this);
        backSpace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttons[0]) makeChanges("0");
        else if (v == buttons[1]) makeChanges("1");
        else if (v == buttons[2]) makeChanges("2");
        else if (v == buttons[3]) makeChanges("3");
        else if (v == buttons[4]) makeChanges("4");
        else if (v == buttons[5]) makeChanges("5");
        else if (v == buttons[6]) makeChanges("6");
        else if (v == buttons[7]) makeChanges("7");
        else if (v == buttons[8]) makeChanges("8");
        else if (v == buttons[9]) makeChanges("9");
        else if (v == backSpace) {
            if (password.length()!=0) {
                password.deleteCharAt(password.length() - 1);
                passwordViewer.setText(password);
            }
        }
    }

    private void makeChanges(String s) {
        password.append(s);
        passwordViewer.setText(password);
        if (password.length()==4) {
            // Get the password correct password
            SharedPreferences sp = getSharedPreferences("PasswordSharedPreference", MODE_PRIVATE);
            String correctPassword = sp.getString("password", "");
            if (correctPassword.equals(password.toString())) {
                Toast.makeText(PasswordVerification.this, "Successful Authentication", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PasswordVerification.this, ViewAllPasswords.class);
                startActivity(intent);
                finish();
            }
            else {
                password.setLength(0);
                passwordViewer.setText(password);
                Toast.makeText(PasswordVerification.this, "Wrong Password", Toast.LENGTH_LONG).show();
            }
        }
    }
}
