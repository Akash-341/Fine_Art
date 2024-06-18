package com.ort.fineart.Model.Request_Model.Checkout;

import com.ort.fineart.Model.Response_Model.Checkout.ApplyCoupon_ResponseModel;
import com.ort.fineart.Model.Response_Model.Checkout.Checkout_ResponseModel;

public class ApplyCoupon_RequestModel {

    private int status;
    private ApplyCoupon_ResponseModel payload;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ApplyCoupon_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(ApplyCoupon_ResponseModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
