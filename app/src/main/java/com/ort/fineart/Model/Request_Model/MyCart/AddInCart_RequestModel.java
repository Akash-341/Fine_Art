package com.ort.fineart.Model.Request_Model.MyCart;

import com.ort.fineart.Model.Response_Model.MyCart.AddInCart_ResponseModel;

public class AddInCart_RequestModel {

    private int status;

    private String messge;



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
