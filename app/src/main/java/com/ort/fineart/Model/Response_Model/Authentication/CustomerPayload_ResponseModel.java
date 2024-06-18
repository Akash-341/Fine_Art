package com.ort.fineart.Model.Response_Model.Authentication;

public class CustomerPayload_ResponseModel {
    private int id;
    private String password;
    private String last_login ;
    private String Email;
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private boolean is_active;
    private boolean is_staff;
    private String date_joined;

    public CustomerPayload_ResponseModel(int id, String password, String last_login, String email, String firstName, String lastName, String phoneNumber, boolean is_active, boolean is_staff, String date_joined) {
        this.id = id;
        this.password = password;
        this.last_login = last_login;
        Email = email;
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
        this.is_active = is_active;
        this.is_staff = is_staff;
        this.date_joined = date_joined;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_staff() {
        return is_staff;
    }

    public void setIs_staff(boolean is_staff) {
        this.is_staff = is_staff;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }
}
