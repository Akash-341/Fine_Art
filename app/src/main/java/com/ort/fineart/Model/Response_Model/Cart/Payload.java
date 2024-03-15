package com.ort.fineart.Model.Response_Model.Cart;

import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("id")
    private Integer id;
    @SerializedName("customer")
    private String customer;
    @SerializedName("VarientProductId")
    private Integer varientProductId;
    @SerializedName("VarientId")
    private Integer varientId;
    @SerializedName("Date")
    private String date;
    @SerializedName("VarientColor")
    private String varientColor;
    @SerializedName("VarientSize")
    private String varientSize;
    @SerializedName("VarientPrice")
    private Integer varientPrice;
    @SerializedName("VarientQuantity")
    private Integer varientQuantity;
    @SerializedName("VarientDiscountPrice")
    private Integer varientDiscountPrice;
    @SerializedName("VarientDiscount")
    private Integer varientDiscount;
    @SerializedName("VarientDiscountMode")
    private String varientDiscountMode;
    @SerializedName("VarientWeight")
    private Integer varientWeight;
    @SerializedName("VarientTotalWeight")
    private Integer varientTotalWeight;
    @SerializedName("VarientTotalWithoutDiscount")
    private Integer varientTotalWithoutDiscount;
    @SerializedName("VarientTotalWithDiscount")
    private Integer varientTotalWithDiscount;
    @SerializedName("isactive")
    private Boolean isactive;

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

    public Integer getVarientProductId() {
        return varientProductId;
    }

    public void setVarientProductId(Integer varientProductId) {
        this.varientProductId = varientProductId;
    }

    public Integer getVarientId() {
        return varientId;
    }

    public void setVarientId(Integer varientId) {
        this.varientId = varientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVarientColor() {
        return varientColor;
    }

    public void setVarientColor(String varientColor) {
        this.varientColor = varientColor;
    }

    public String getVarientSize() {
        return varientSize;
    }

    public void setVarientSize(String varientSize) {
        this.varientSize = varientSize;
    }

    public Integer getVarientPrice() {
        return varientPrice;
    }

    public void setVarientPrice(Integer varientPrice) {
        this.varientPrice = varientPrice;
    }

    public Integer getVarientQuantity() {
        return varientQuantity;
    }

    public void setVarientQuantity(Integer varientQuantity) {
        this.varientQuantity = varientQuantity;
    }

    public Integer getVarientDiscountPrice() {
        return varientDiscountPrice;
    }

    public void setVarientDiscountPrice(Integer varientDiscountPrice) {
        this.varientDiscountPrice = varientDiscountPrice;
    }

    public Integer getVarientDiscount() {
        return varientDiscount;
    }

    public void setVarientDiscount(Integer varientDiscount) {
        this.varientDiscount = varientDiscount;
    }

    public String getVarientDiscountMode() {
        return varientDiscountMode;
    }

    public void setVarientDiscountMode(String varientDiscountMode) {
        this.varientDiscountMode = varientDiscountMode;
    }

    public Integer getVarientWeight() {
        return varientWeight;
    }

    public void setVarientWeight(Integer varientWeight) {
        this.varientWeight = varientWeight;
    }

    public Integer getVarientTotalWeight() {
        return varientTotalWeight;
    }

    public void setVarientTotalWeight(Integer varientTotalWeight) {
        this.varientTotalWeight = varientTotalWeight;
    }

    public Integer getVarientTotalWithoutDiscount() {
        return varientTotalWithoutDiscount;
    }

    public void setVarientTotalWithoutDiscount(Integer varientTotalWithoutDiscount) {
        this.varientTotalWithoutDiscount = varientTotalWithoutDiscount;
    }

    public Integer getVarientTotalWithDiscount() {
        return varientTotalWithDiscount;
    }

    public void setVarientTotalWithDiscount(Integer varientTotalWithDiscount) {
        this.varientTotalWithDiscount = varientTotalWithDiscount;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

}
