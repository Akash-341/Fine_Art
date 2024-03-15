package com.ort.fineart.Model.Response_Model.AddRemoveWishlist;

import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("id")

    private Integer id;
    @SerializedName("customer")

    private String customer;
    @SerializedName("isactive")

    private Boolean isactive;
    @SerializedName("varient")

    private Integer varient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Integer getVarient() {
        return varient;
    }

    public void setVarient(Integer varient) {
        this.varient = varient;
    }

}
