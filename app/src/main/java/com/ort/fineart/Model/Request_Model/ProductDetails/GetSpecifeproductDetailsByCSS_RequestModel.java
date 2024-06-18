package com.ort.fineart.Model.Request_Model.ProductDetails;

import com.ort.fineart.Model.Response_Model.ProductDetails.GetSpecifeproductDetailsByCSS_ResponseModel;
import com.ort.fineart.Model.Response_Model.ProductDetails.SpecifeProductDetails_ResponseModel;

public class GetSpecifeproductDetailsByCSS_RequestModel {
    private int status;
    GetSpecifeproductDetailsByCSS_ResponseModel payload;
    private String messge;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GetSpecifeproductDetailsByCSS_ResponseModel getPayload() {
        return payload;
    }

    public void setPayload(GetSpecifeproductDetailsByCSS_ResponseModel payload) {
        this.payload = payload;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
