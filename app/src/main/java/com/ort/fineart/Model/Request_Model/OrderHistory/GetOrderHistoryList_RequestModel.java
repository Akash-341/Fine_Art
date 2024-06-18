package com.ort.fineart.Model.Request_Model.OrderHistory;

import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistory_ResponseModel;

import java.util.ArrayList;

public class GetOrderHistoryList_RequestModel {
    private int status;
    ArrayList<OrderHistory_ResponseModel> payload;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<OrderHistory_ResponseModel> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<OrderHistory_ResponseModel> payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
