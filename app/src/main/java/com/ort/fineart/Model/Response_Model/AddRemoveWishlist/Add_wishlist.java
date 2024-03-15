package com.ort.fineart.Model.Response_Model.AddRemoveWishlist;


import com.google.gson.annotations.SerializedName;


public class Add_wishlist {

    @SerializedName("status")
    private Integer status;
    @SerializedName("payload")
    private Payload payload;
    @SerializedName("messge")
    private String messge;
    @SerializedName("wishlistlength")
    private Integer wishlistlength;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public Integer getWishlistlength() {
        return wishlistlength;
    }

    public void setWishlistlength(Integer wishlistlength) {
        this.wishlistlength = wishlistlength;
    }

}

