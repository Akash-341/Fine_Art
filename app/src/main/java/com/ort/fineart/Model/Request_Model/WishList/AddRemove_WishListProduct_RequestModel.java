package com.ort.fineart.Model.Request_Model.WishList;

import com.ort.fineart.Model.Response_Model.WishList.AddRemove_WishListProduct_ResponseModel;

public class AddRemove_WishListProduct_RequestModel {
    private int status;
    AddRemove_WishListProduct_ResponseModel payload;
    private String messge;
    private int wishlistlength;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AddRemove_WishListProduct_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(AddRemove_WishListProduct_ResponseModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public int getWishlistlength() {
        return wishlistlength;
    }

    public void setWishlistlength(int wishlistlength) {
        this.wishlistlength = wishlistlength;
    }
}
