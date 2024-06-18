package com.ort.fineart.Model.Request_Model.PersonalDetails;

import com.ort.fineart.Model.Response_Model.PersonalDetails_ResponseModel;

public class Update_Personal_Details_RequestModel {

    private int status;
    PersonalDetails_ResponseModel payload;
    private String token;
    private String message;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PersonalDetails_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(PersonalDetails_ResponseModel payload) {
        this.payload = payload;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
