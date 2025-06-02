package com.example.facebookapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapplication.adapter.CartAdapter;
import com.example.facebookapplication.model.CartManager;
import com.example.facebookapplication.model.Product;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView emptyCartText;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerView_cart);
        emptyCartText = findViewById(R.id.empty_cart_text);
        checkoutButton = findViewById(R.id.checkout_button);
        Button backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(v -> finish());
        
        checkoutButton.setOnClickListener(v -> {
            Toast.makeText(this, "Checkout completed!", Toast.LENGTH_SHORT).show();
            CartManager.getInstance().clearCart();
            updateCartDisplay();
        });

        updateCartDisplay();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        updateCartDisplay();
    }
    
    private void updateCartDisplay() {
        List<Product> cartItems = CartManager.getInstance().getCartItems();
        
        if (cartItems.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyCartText.setVisibility(View.VISIBLE);
            checkoutButton.setEnabled(false);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyCartText.setVisibility(View.GONE);
            checkoutButton.setEnabled(true);
            
            CartAdapter adapter = new CartAdapter(this, cartItems);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }
}
