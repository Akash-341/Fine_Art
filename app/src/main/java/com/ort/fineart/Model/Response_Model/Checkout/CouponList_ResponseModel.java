package com.ort.fineart.Model.Response_Model.Checkout;

import java.io.Serializable;

public class CouponList_ResponseModel implements Serializable {
    private int id;
    private String CouponCode;
    private String CouponDescription;
    private String DiscountType;
    private int CouponValue;
    private String MaxCount = null;
    private String ExpiryDate;
    private int MaxLimit;
    private boolean Status;
    private boolean is_active;
    private String Category;

    public CouponList_ResponseModel(int id, String couponCode, String couponDescription, String discountType, int couponValue, String maxCount, String expiryDate, int maxLimit, boolean status, boolean is_active, String category) {
        this.id = id;
        CouponCode = couponCode;
        CouponDescription = couponDescription;
        DiscountType = discountType;
        CouponValue = couponValue;
        MaxCount = maxCount;
        ExpiryDate = expiryDate;
        MaxLimit = maxLimit;
        Status = status;
        this.is_active = is_active;
        Category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }

    public String getCouponDescription() {
        return CouponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        CouponDescription = couponDescription;
    }

    public String getDiscountType() {
        return DiscountType;
    }

    public void setDiscountType(String discountType) {
        DiscountType = discountType;
    }

    public int getCouponValue() {
        return CouponValue;
    }

    public void setCouponValue(int couponValue) {
        CouponValue = couponValue;
    }

    public String getMaxCount() {
        return MaxCount;
    }

    public void setMaxCount(String maxCount) {
        MaxCount = maxCount;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public int getMaxLimit() {
        return MaxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        MaxLimit = maxLimit;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    @Override
    public String toString() {
        return "CouponList_ResponseModel{" +
                "id=" + id +
                ", CouponCode='" + CouponCode + '\'' +
                ", CouponDescription='" + CouponDescription + '\'' +
                ", DiscountType='" + DiscountType + '\'' +
                ", CouponValue=" + CouponValue +
                ", MaxCount='" + MaxCount + '\'' +
                ", ExpiryDate='" + ExpiryDate + '\'' +
                ", MaxLimit=" + MaxLimit +
                ", Status=" + Status +
                ", is_active=" + is_active +
                ", Category='" + Category + '\'' +
                '}';
    }
}
