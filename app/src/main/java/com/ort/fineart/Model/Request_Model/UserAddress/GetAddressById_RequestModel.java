package com.ort.fineart.Model.Request_Model.UserAddress;

import com.ort.fineart.Model.Response_Model.UserAddress.GetAddressById_ResponseModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;

import java.util.ArrayList;

public class GetAddressById_RequestModel {


    private int status;
    GetAddressById_ResponseModel payload;
    private String messge;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GetAddressById_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(GetAddressById_ResponseModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
