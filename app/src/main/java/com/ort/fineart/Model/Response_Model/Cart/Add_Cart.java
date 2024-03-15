
package com.ort.fineart.Model.Response_Model.Cart;

import com.google.gson.annotations.SerializedName;

public class Add_Cart {

    @SerializedName("status")
    private String status;
    @SerializedName("payload")
    private Payload payload;
    @SerializedName("messge")
    private String messge;
    @SerializedName("cartlength")
    private Integer cartlength;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Integer getCartlength() {
        return cartlength;
    }

    public void setCartlength(Integer cartlength) {
        this.cartlength = cartlength;
    }

}

