package com.ort.fineart.Model.Response_Model.ProductDetailsData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product_Details_Model {

    @SerializedName("status")

    private Integer status;
    @SerializedName("payload")

    private Payload payload;
    @SerializedName("messge")

    private String messge;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

}
