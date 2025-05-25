package com.example.facebookapplication;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OnLogInEventListener implements View.OnClickListener{
    private EditText emailEditText;
    private EditText passwordEditText;
    private Context context;

    public OnLogInEventListener(Context context, EditText emailEditText, EditText passwordEditText) {
        this.context = context;
        this.emailEditText = emailEditText;
        this.passwordEditText = passwordEditText;
    }

    @Override
    public void onClick(View v) {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.equals("admin") && password.equals("123456")) {
            Toast.makeText(context, "Login successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }
}
