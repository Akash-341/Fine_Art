package com.ort.fineart.Model.Request_Model.Checkout;

import com.ort.fineart.Model.Response_Model.Checkout.CouponList_ResponseModel;

import java.util.ArrayList;

public class CouponList_RequestModel {

    private int status;
    ArrayList <CouponList_ResponseModel> payload ;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<CouponList_ResponseModel> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<CouponList_ResponseModel> payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
