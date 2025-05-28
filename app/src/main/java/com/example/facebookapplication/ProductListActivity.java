package com.example.facebookapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapplication.adapter.ProductAdapter;
import com.example.facebookapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_products);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        List<Product> originalProducts = new ArrayList<>();
        originalProducts.add(new Product("pharmacy", R.drawable.ic_pharmacy));
        originalProducts.add(new Product("registry", R.drawable.ic_registry));
        originalProducts.add(new Product("cartwheel", R.drawable.ic_cartwheel));
        originalProducts.add(new Product("clothing", R.drawable.ic_clothing));
        originalProducts.add(new Product("shoes", R.drawable.ic_shoes));
        originalProducts.add(new Product("accessories", R.drawable.ic_accessories));
        originalProducts.add(new Product("baby", R.drawable.ic_baby));
        originalProducts.add(new Product("home", R.drawable.ic_home));
        originalProducts.add(new Product("patio & garden", R.drawable.ic_patio_garden));

        // Fill 1000 products by repeating the originals
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Product base = originalProducts.get(i % originalProducts.size());
            products.add(new Product(base.getName(), base.getImageResId()));
        }

        ProductAdapter adapter = new ProductAdapter(this, products);
        recyclerView.setAdapter(adapter);
    }
}