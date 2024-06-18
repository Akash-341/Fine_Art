package com.ort.fineart.Model.Request_Model.Hub;

import com.ort.fineart.Model.Response_Model.Hub.Hub_CategoryList_ResponseModel;
import com.ort.fineart.Model.Response_Model.Hub.Hub_SubCategoryList_ResponseModel;

import java.util.ArrayList;
import java.util.List;

public class Hub_CategoryList_RequestModel {
    private int status;
    List<Hub_CategoryList_ResponseModel> payload = new ArrayList();
    private String messge;

    public List<Hub_CategoryList_ResponseModel> getPayload() {
        return payload;
    }

    public void setPayload(List<Hub_CategoryList_ResponseModel> payload) {
        this.payload = payload;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
