package com.example.facebookapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapplication.DetailActivity;
import com.example.facebookapplication.R;
import com.example.facebookapplication.model.CartManager;
import com.example.facebookapplication.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if(product == null) {
            return;
        }
        holder.textView.setText(product.getName());
        holder.imageView.setImageResource(product.getImageResId());
        holder.addToCartButton.setOnClickListener(v -> {
             CartManager.getInstance().addToCart(product);
             Toast.makeText(context, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_image", product.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }
        return 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
         ImageView imageView;
         TextView textView;
         Button addToCartButton;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            textView = itemView.findViewById(R.id.product_name);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}
