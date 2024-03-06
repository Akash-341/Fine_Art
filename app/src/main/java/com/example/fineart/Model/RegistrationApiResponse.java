
package com.example.fineart.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class RegistrationApiResponse {

    @SerializedName("messge")
    private String mMessge;
    @SerializedName("payload")
    private Payload mPayload;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("token")
    private String mToken;

    public String getMessge() {
        return mMessge;
    }

    public void setMessge(String messge) {
        mMessge = messge;
    }

    public Payload getPayload() {
        return mPayload;
    }

    public void setPayload(Payload payload) {
        mPayload = payload;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

}
