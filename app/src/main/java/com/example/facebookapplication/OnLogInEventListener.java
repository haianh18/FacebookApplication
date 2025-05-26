package com.example.facebookapplication;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
        List<UserData> userDataList = new ArrayList<>(UserData.getUserData());
        Intent intent = new Intent(context, HomeActivity.class);

        for (UserData userData : userDataList
             ) {
            if (email.equals(userData.getUsername()) && password.equals(userData.getPassword())) {
                intent.putExtra("displayingName", userData.getName());
                 context.startActivity(intent);
                 return;
            }
        }
        Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
    }
}
