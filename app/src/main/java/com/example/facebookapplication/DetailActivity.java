package com.example.facebookapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.detail_image);
        TextView textView = findViewById(R.id.detail_name);

        String name = getIntent().getStringExtra("product_name");
        int imageRes = getIntent().getIntExtra("product_image", 0);

        textView.setText(name);
        imageView.setImageResource(imageRes);

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }


}