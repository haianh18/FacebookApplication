package com.example.facebookapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facebookapplication.model.CartManager;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        emailEditText = findViewById(R.id.editText_email);
        passwordEditText = findViewById(R.id.editText_password);
        Button loginButton = findViewById(R.id.button_login);

        OnLogInEventListener loginListener = new OnLogInEventListener(this, emailEditText, passwordEditText);
        loginButton.setOnClickListener(loginListener);

        // Set up the permission request launcher
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission granted, show notification
                CartManager.getInstance().showCartNotification(this);
            } else {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
            }
        });

        // Check and request notification permission
        checkNotificationPermission();
    }

    private void checkNotificationPermission() {
        // For Android 13 (API level 33) and above, we need to request POST_NOTIFICATIONS permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                // Permission already granted, show notification immediately
                CartManager.getInstance().showCartNotification(this);
            }
        } else {
            // For older Android versions, permission is granted at install time
            CartManager.getInstance().showCartNotification(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        emailEditText.setText("");
        passwordEditText.setText("");
    }
}
