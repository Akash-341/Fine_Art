package com.ort.fineart.Model.Response_Model.OrderHistory;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class OrderHistoryModel {

    @SerializedName("status")

    private Integer status;
    @SerializedName("payload")

    private List<Payload> payload;
    @SerializedName("messge")

    private String messge;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Payload> getPayload() {
        return payload;
    }

    public void setPayload(List<Payload> payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

}


