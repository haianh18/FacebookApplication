package com.example.facebookapplication.model;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.facebookapplication.CartActivity;
import com.example.facebookapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final List<Product> cartItems = new ArrayList<>();
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "cart_notification_channel";

    private CartManager() {}

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Product product) {
        cartItems.add(product);
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public void showCartNotification(Context context) {
        if (isCartEmpty()) {
            return;
        }

        createNotificationChannel(context);

        Intent intent = new Intent(context, CartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 
                PendingIntent.FLAG_IMMUTABLE);

        // Create notification title
        String notificationTitle = cartItems.size() + " products in your cart";

        // Create notification content showing product names
        StringBuilder contentBuilder = new StringBuilder();
        for (int i = 0; i < Math.min(3, cartItems.size()); i++) {
            contentBuilder.append("• ").append(cartItems.get(i).getName());
            if (i < Math.min(3, cartItems.size()) - 1) {
                contentBuilder.append("\n");
            }
        }

        // If there are more than 3 products, add "and more..." text
        if (cartItems.size() > 3) {
            contentBuilder.append("\n• and ").append(cartItems.size() - 3).append(" more...");
        }

        String notificationContent = contentBuilder.toString();

        // Create notification with expanded style to show multiple lines
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationContent))
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) 
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Cart Notification";
            String description = "Notifications for shopping cart";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
