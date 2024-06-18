package com.ort.fineart.Model.Request_Model.MyCart;

import com.ort.fineart.Model.Response_Model.MyCart.ChangeProductQunatity_ResponeModel;

public class ChangeProductQuntity_RequestModel {
    private int status;
    ChangeProductQunatity_ResponeModel payload;
    private String messge;
    private int grandtotal;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ChangeProductQunatity_ResponeModel getPayload() {
        return payload;
    }

    public void setPayload(ChangeProductQunatity_ResponeModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public int getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(int grandtotal) {
        this.grandtotal = grandtotal;
    }
}
