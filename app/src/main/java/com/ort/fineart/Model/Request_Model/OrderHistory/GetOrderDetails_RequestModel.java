package com.ort.fineart.Model.Request_Model.OrderHistory;

import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistory_ResponseModel;

public class GetOrderDetails_RequestModel {
    private int status;
    OrderHistory_ResponseModel payload;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderHistory_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(OrderHistory_ResponseModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
