package com.ort.fineart.Model.Request_Model.Home;

import com.ort.fineart.Model.Response_Model.Home.HomeDealOfDay_ResponseModel;

import java.util.ArrayList;

public class HomeDealOfDay_RequestModel {
    private int status;
    ArrayList<HomeDealOfDay_ResponseModel> payload = new ArrayList ();
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<HomeDealOfDay_ResponseModel> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<HomeDealOfDay_ResponseModel> payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
