package com.ort.fineart.Model.Response_Model.Deal_Of_The_day;

import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("id")
    private Integer id;

    @SerializedName("ProductCategory")
    private String productCategory;

    @SerializedName("ProductSubCategory")
    private String productSubCategory;

    @SerializedName("ProductName")
    private String productName;

    @SerializedName("ProductNumber")
    private String productNumber;

    @SerializedName("HSNCODE")
    private String hsncode;

    @SerializedName("ProductDescription")
    private String productDescription;

    @SerializedName("ProductShortDescription")
    private String productShortDescription;

    @SerializedName("ProductUsage")
    private String productUsage;

    @SerializedName("ProductMoreDetails")
    private String productMoreDetails;

    @SerializedName("isactive")
    private Boolean isactive;

    @SerializedName("Delivery_Charges")
    private Boolean deliveryCharges;

    @SerializedName("Best_seller")
    private Boolean bestSeller;

    @SerializedName("Deal_of_the_day")
    private Boolean dealOfTheDay;

    @SerializedName("AverageRating")
    private String averageRating;

    @SerializedName("Status")
    private Boolean status;

    @SerializedName("ProductImage")
    private String productImage;

    @SerializedName("ProductSize")
    private String productSize;

    @SerializedName("ProductWeight")
    private String productWeight;

    @SerializedName("ProductColor")
    private String productColor;

    @SerializedName("ProductDiscount")
    private String productDiscount;

    @SerializedName("ProductDiscountMode")
    private String productDiscountMode;

    @SerializedName("ProductQuantity")
    private String productQuantity;

    @SerializedName("ProductPrice")
    private String productPrice;

    @SerializedName("ProductDiscountPrice")
    private String productDiscountPrice;

    @SerializedName("Product_VarientId")
    private String productVarientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getHsncode() {
        return hsncode;
    }

    public void setHsncode(String hsncode) {
        this.hsncode = hsncode;
    }

    public String getProductDescription() {
        this.productDescription = checkNullOrEmpty(this.productDescription);
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductShortDescription() {
        this.productShortDescription = checkNullOrEmpty(this.productShortDescription);
        return productShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        this.productShortDescription = productShortDescription;
    }

    public String getProductUsage() {
        this.productUsage = checkNullOrEmpty(this.productUsage);
        return productUsage;
    }

    public void setProductUsage(String productUsage) {
        this.productUsage = productUsage;
    }

    public String getProductMoreDetails() {
        this.productMoreDetails = checkNullOrEmpty(this.productMoreDetails);
        return productMoreDetails;
    }

    public void setProductMoreDetails(String productMoreDetails) {
        this.productMoreDetails = productMoreDetails;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Boolean getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(Boolean deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public Boolean getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(Boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public Boolean getDealOfTheDay() {
        return dealOfTheDay;
    }

    public void setDealOfTheDay(Boolean dealOfTheDay) {
        this.dealOfTheDay = dealOfTheDay;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getProductImage() {
        this.productImage = checkNullOrEmpty(this.productImage);
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductSize() {
        this.productSize = checkNullOrEmpty(this.productSize);
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductColor() {
        this.productColor = checkNullOrEmpty(this.productColor);
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductDiscountMode() {
        this.productDiscountMode = checkNullOrEmpty(this.productDiscountMode);
        return productDiscountMode;
    }

    public void setProductDiscountMode(String productDiscountMode) {
        this.productDiscountMode = productDiscountMode;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(String productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductVarientId() {
        return productVarientId;
    }

    public void setProductVarientId(String productVarientId) {
        this.productVarientId = productVarientId;
    }

    private String checkNullOrEmpty(String value) {
        return (value != null && !value.trim().isEmpty()) ? value : null;
    }
}


