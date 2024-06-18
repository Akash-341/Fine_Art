package com.ort.fineart.Model.Request_Model.UserAddress;

import com.ort.fineart.Model.Response_Model.MyCart.MyCart_ResponseModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;

import java.util.ArrayList;

public class GetUserAddress_RequestModel {

    private int status;
    ArrayList<GetUserAddress_ResponseModel> payload;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<GetUserAddress_ResponseModel> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<GetUserAddress_ResponseModel> payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
