package com.ort.fineart.Model.Request_Model.ProductDetails;

import com.ort.fineart.Model.Response_Model.MyCart.MyCart_ResponseModel;
import com.ort.fineart.Model.Response_Model.ProductDetails.SpecifeProductDetails_ResponseModel;

import java.util.ArrayList;

public class SpecifeProductDetails_RequestModel {


    private int status;
    SpecifeProductDetails_ResponseModel payload;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SpecifeProductDetails_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(SpecifeProductDetails_ResponseModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
