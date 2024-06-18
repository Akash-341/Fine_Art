package com.ort.fineart.Model.Request_Model.Hub;

import com.ort.fineart.Model.Response_Model.Hub.GetHubProductListByCategory_ResponseModel;

import java.util.ArrayList;

public class GetHubProductListByCategory_RequestModel {
    private float status;
    ArrayList<GetHubProductListByCategory_ResponseModel> payload;
    private String messge;

    public float getStatus() {
        return status;
    }

    public void setStatus(float status) {
        this.status = status;
    }

    public ArrayList<GetHubProductListByCategory_ResponseModel> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<GetHubProductListByCategory_ResponseModel> payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
