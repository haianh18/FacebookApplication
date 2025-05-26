package com.example.facebookapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        welcomeTextView = findViewById(R.id.welcomeTextView);
        String displayingName = getIntent().getStringExtra("displayingName");
        if (displayingName != null) {
            welcomeTextView.setText("Welcome, " + displayingName + "!");
        }
        Button closeButton = findViewById(R.id.button_close);
        closeButton.setOnClickListener(v -> {
             finishAffinity();
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        welcomeTextView.setText("");
    }

}
