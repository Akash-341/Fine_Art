package com.ort.fineart.Model.Request_Model.WishList;

import com.ort.fineart.Model.Response_Model.WishList.GetWishList_ResponseModel;

import java.util.ArrayList;

public class GetWishList_RequestModel {


    private int status;
    ArrayList<GetWishList_ResponseModel> payload = new ArrayList ();
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<GetWishList_ResponseModel> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<GetWishList_ResponseModel> payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
