package com.example.facebookapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class DetailActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PHONE = 1;
    private String phoneNumber;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.detail_image);
        TextView textView = findViewById(R.id.detail_name);
        TextView phoneTextView = findViewById(R.id.store_phone);

        String name = getIntent().getStringExtra("product_name");
        int imageRes = getIntent().getIntExtra("product_image", 0);

        textView.setText(name);
        imageView.setImageResource(imageRes);
        
        phoneNumber = "1234567890";
        phoneTextView.setText("Store Phone: " + phoneNumber);
        
        phoneTextView.setOnClickListener(v -> {
            makePhoneCall();
        });

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }
    
    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(DetailActivity.this, 
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
                //Toast.makeText(this, "Call permission is required to make a call", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(DetailActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
        } else {
            startCall();
        }
    }
    
    private void startCall() {
        String dial = "tel:" + phoneNumber;
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(dial));
        startActivity(callIntent);
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
