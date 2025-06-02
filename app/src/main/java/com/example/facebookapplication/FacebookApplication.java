package com.example.facebookapplication;

import android.app.Application;

import com.example.facebookapplication.model.CartManager;
import com.example.facebookapplication.model.Product;

public class FacebookApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the cart with some products
        initializeCart();

        // We don't need to show notification here, it will be handled in MainActivity
        // after proper permission handling
    }

    private void initializeCart() {
        // Add some sample products to the cart before the app starts
        CartManager cartManager = CartManager.getInstance();

        // Add a few sample products to the cart
        cartManager.addToCart(new Product("pharmacy", R.drawable.ic_pharmacy));
        cartManager.addToCart(new Product("clothing", R.drawable.ic_clothing));
        cartManager.addToCart(new Product("shoes", R.drawable.ic_shoes));
    }
}
