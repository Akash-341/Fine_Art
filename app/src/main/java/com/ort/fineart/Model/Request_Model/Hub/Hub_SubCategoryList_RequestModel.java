package com.ort.fineart.Model.Request_Model.Hub;

import com.ort.fineart.Model.Response_Model.Hub.Hub_SubCategoryList_ResponseModel;

import java.util.ArrayList;

public class Hub_SubCategoryList_RequestModel {

    private int status;
    ArrayList <Hub_SubCategoryList_ResponseModel> payload = new ArrayList();
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Hub_SubCategoryList_ResponseModel> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<Hub_SubCategoryList_ResponseModel> payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
