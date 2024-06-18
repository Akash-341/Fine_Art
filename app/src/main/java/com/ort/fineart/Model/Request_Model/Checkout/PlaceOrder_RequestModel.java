package com.ort.fineart.Model.Request_Model.Checkout;

import com.ort.fineart.Model.Response_Model.Checkout.PlaceOrder_ResponseModel;

public class PlaceOrder_RequestModel {

    private int status;
    private PlaceOrder_ResponseModel payload;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PlaceOrder_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(PlaceOrder_ResponseModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
