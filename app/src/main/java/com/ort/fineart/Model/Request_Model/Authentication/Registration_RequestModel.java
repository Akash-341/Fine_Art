package com.ort.fineart.Model.Request_Model.Authentication;

import com.ort.fineart.Model.Response_Model.Authentication.Registration_ResponseModel;

public class Registration_RequestModel {

    private int status;
    Registration_ResponseModel payload;
    private String token;
    private String messge;




    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Registration_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(Registration_ResponseModel payload) {
        this.payload = payload;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
