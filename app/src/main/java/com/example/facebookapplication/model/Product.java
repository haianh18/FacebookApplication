package com.example.facebookapplication.model;

public class Product {
    private String name;
    private int imageResId;

    public Product(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}