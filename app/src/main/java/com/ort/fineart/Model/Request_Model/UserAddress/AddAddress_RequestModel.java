package com.ort.fineart.Model.Request_Model.UserAddress;

import com.ort.fineart.Model.Response_Model.UserAddress.AddAddress_ResponseModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;

import java.util.ArrayList;

public class AddAddress_RequestModel {

    private int status;
    AddAddress_ResponseModel payload;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AddAddress_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(AddAddress_ResponseModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
