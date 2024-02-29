package com.example.fineart.Model;

public class Product {
    private String name;
    private boolean isWishlist;

    public Product(String name, boolean isWishlist) {
        this.name = name;
        this.isWishlist = isWishlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWishlist() {
        return isWishlist;
    }

    public void setWishlist(boolean wishlist) {
        isWishlist = wishlist;
    }
}
