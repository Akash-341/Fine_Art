package com.ort.fineart.Model.Request_Model.Checkout;

import com.ort.fineart.Model.Response_Model.Checkout.Checkout_ResponseModel;

public class Checkout_RequestModel {

    private int status;
    private Checkout_ResponseModel payload;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Checkout_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(Checkout_ResponseModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
