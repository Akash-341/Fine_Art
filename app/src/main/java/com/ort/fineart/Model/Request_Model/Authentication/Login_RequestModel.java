package com.ort.fineart.Model.Request_Model.Authentication;

import com.ort.fineart.Model.Response_Model.Authentication.CustomerPayload_ResponseModel;
import com.ort.fineart.Model.Response_Model.Authentication.Login_ResponseModel;

public class Login_RequestModel {
    private int status;
    private String Email;
    private String password;
    Login_ResponseModel customer_payload;
    private String customer_token;
    private String message;
    private String FirstName;
    private String LastName;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login_ResponseModel getCustomer_payload() {
        return customer_payload;
    }

    public void setCustomer_payload(Login_ResponseModel customer_payload) {
        this.customer_payload = customer_payload;
    }

    public String getCustomer_token() {
        return customer_token;
    }

    public void setCustomer_token(String customer_token) {
        this.customer_token = customer_token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
