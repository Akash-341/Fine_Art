package com.ort.fineart.Model.Response_Model.Best_Seller;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Best_Seller_Model {

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

