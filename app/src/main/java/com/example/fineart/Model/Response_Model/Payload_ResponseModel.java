
package com.example.fineart.Model.Response_Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Payload_ResponseModel {

    @SerializedName("date_joined")
    private String mDateJoined;
    @SerializedName("Email")
    private String mEmail;
    @SerializedName("FirstName")
    private String mFirstName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_active")
    private Boolean mIsActive;
    @SerializedName("is_staff")
    private Boolean mIsStaff;
    @SerializedName("last_login")
    private Object mLastLogin;
    @SerializedName("LastName")
    private String mLastName;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("PhoneNumber")
    private String mPhoneNumber;

    public String getDateJoined() {
        return mDateJoined;
    }

    public void setDateJoined(String dateJoined) {
        mDateJoined = dateJoined;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Boolean isActive) {
        mIsActive = isActive;
    }

    public Boolean getIsStaff() {
        return mIsStaff;
    }

    public void setIsStaff(Boolean isStaff) {
        mIsStaff = isStaff;
    }

    public Object getLastLogin() {
        return mLastLogin;
    }

    public void setLastLogin(Object lastLogin) {
        mLastLogin = lastLogin;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

}
