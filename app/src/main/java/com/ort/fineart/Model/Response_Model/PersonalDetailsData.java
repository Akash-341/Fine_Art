package com.ort.fineart.Model.Response_Model;

import com.google.gson.annotations.SerializedName;

public class PersonalDetailsData {

    @SerializedName("id")
    private Integer id;
    @SerializedName("password")
    private String password;
    @SerializedName("last_login")
    private Object lastLogin;
    @SerializedName("Email")
    private String email;
    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("PhoneNumber")
    private String phoneNumber;
    @SerializedName("is_active")
    private Boolean isActive;
    @SerializedName("is_staff")
    private Boolean isStaff;
    @SerializedName("date_joined")
    private String dateJoined;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Object lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsActive() {
        return isActive;
    }
}

