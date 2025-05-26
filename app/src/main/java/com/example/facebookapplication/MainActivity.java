package com.example.facebookapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        emailEditText = findViewById(R.id.editText_email);
        passwordEditText = findViewById(R.id.editText_password);
        Button loginButton = findViewById(R.id.button_login);

        OnLogInEventListener loginListener = new OnLogInEventListener(this, emailEditText, passwordEditText);
        loginButton.setOnClickListener(loginListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        emailEditText.setText("");
        passwordEditText.setText("");
    }
}
