package com.ort.fineart.Model.Request_Model.MyCart;

import com.ort.fineart.Model.Response_Model.MyCart.MyCart_ResponseModel;

import java.util.ArrayList;

public class MyCartList_RequestModel {
    private float status;
    ArrayList<MyCart_ResponseModel> payload;
    private String messge;

    public float getStatus() {
        return status;
    }

    public void setStatus(float status) {
        this.status = status;
    }

    public ArrayList<MyCart_ResponseModel> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<MyCart_ResponseModel> payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
