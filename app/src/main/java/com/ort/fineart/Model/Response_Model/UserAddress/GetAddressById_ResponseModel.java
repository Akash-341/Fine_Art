package com.ort.fineart.Model.Response_Model.UserAddress;

public class GetAddressById_ResponseModel {

    private int id;
    private String Customer;
    private String AddressLine1;
    private String AddressLine2;
    private String Country;
    private String State;
    private String City;
    private int Pincode;
    private boolean isactive;

    public GetAddressById_ResponseModel(int id, String customer, String addressLine1, String addressLine2, String country, String state, String city, int pincode, boolean isactive) {
        this.id = id;
        Customer = customer;
        AddressLine1 = addressLine1;
        AddressLine2 = addressLine2;
        Country = country;
        State = state;
        City = city;
        Pincode = pincode;
        this.isactive = isactive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getPincode() {
        return Pincode;
    }

    public void setPincode(int pincode) {
        Pincode = pincode;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
