package com.ort.fineart.Model.Response_Model.ProductDetailsData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payload {

    @SerializedName("ProductSize")
    private String productSize;
    @SerializedName("ProductWeight")
    private Integer productWeight;
    @SerializedName("ProductColor")
    private String productColor;
    @SerializedName("ProductDiscount")
    private Integer productDiscount;
    @SerializedName("ProductDiscountMode")
    private String productDiscountMode;
    @SerializedName("ProductQuantity")
    private Integer productQuantity;
    @SerializedName("ProductPrice")
    private Integer productPrice;
    @SerializedName("ProductDiscountPrice")
    private Integer productDiscountPrice;
    @SerializedName("Product_VarientId")
    private String productVarientId;
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
    private Object averageRating;
    @SerializedName("PublishStatus")
    private Boolean publishStatus;
    @SerializedName("ProductImage")
    private String productImage;



    @SerializedName("Product_Id")
    private String productId;
    @SerializedName("ProductImages1")
    private String productImages1;
    @SerializedName("ProductImages2")
    private String productImages2;
    @SerializedName("ProductImages3")
    private String productImages3;
    @SerializedName("ProductImages4")
    private String productImages4;
    @SerializedName("ProductImages5")
    private String productImages5;
    @SerializedName("ProductImages6")
    private String productImages6;
    @SerializedName("ProductImages7")
    private String productImages7;
    @SerializedName("ProductImages8")
    private String productImages8;
    @SerializedName("ProductImages9")
    private String productImages9;
    @SerializedName("ProductImages10")
    private String productImages10;
    @SerializedName("Sizelist")
    private List<String> sizelist;
    @SerializedName("Colorslist")
    private List<String> colorslist;
    @SerializedName("ColorsNamelist")
    private List<String> colorsNamelist;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public List<String> getColorsNamelist() {
        return colorsNamelist;
    }

    public void setColorsNamelist(List<String> colorsNamelist) {
        this.colorsNamelist = colorsNamelist;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Integer productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public Integer getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Integer productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductDiscountMode() {
        return productDiscountMode;
    }

    public void setProductDiscountMode(String productDiscountMode) {
        this.productDiscountMode = productDiscountMode;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(Integer productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductVarientId() {
        return productVarientId;
    }

    public void setProductVarientId(String productVarientId) {
        this.productVarientId = productVarientId;
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
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductShortDescription() {
        return productShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        this.productShortDescription = productShortDescription;
    }

    public String getProductUsage() {
        return productUsage;
    }

    public void setProductUsage(String productUsage) {
        this.productUsage = productUsage;
    }

    public String getProductMoreDetails() {
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

    public Object getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Object averageRating) {
        this.averageRating = averageRating;
    }

    public Boolean getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Boolean publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductImages1() {
        return productImages1;
    }

    public void setProductImages1(String productImages1) {
        this.productImages1 = productImages1;
    }

    public String getProductImages2() {
        return productImages2;
    }

    public void setProductImages2(String productImages2) {
        this.productImages2 = productImages2;
    }

    public String getProductImages3() {
        return productImages3;
    }

    public void setProductImages3(String productImages3) {
        this.productImages3 = productImages3;
    }

    public String getProductImages4() {
        return productImages4;
    }

    public void setProductImages4(String productImages4) {
        this.productImages4 = productImages4;
    }

    public String getProductImages5() {
        return productImages5;
    }

    public void setProductImages5(String productImages5) {
        this.productImages5 = productImages5;
    }

    public String getProductImages6() {
        return productImages6;
    }

    public void setProductImages6(String productImages6) {
        this.productImages6 = productImages6;
    }

    public String getProductImages7() {
        return productImages7;
    }

    public void setProductImages7(String productImages7) {
        this.productImages7 = productImages7;
    }

    public String getProductImages8() {
        return productImages8;
    }

    public void setProductImages8(String productImages8) {
        this.productImages8 = productImages8;
    }

    public String getProductImages9() {
        return productImages9;
    }

    public void setProductImages9(String productImages9) {
        this.productImages9 = productImages9;
    }

    public String getProductImages10() {
        return productImages10;
    }

    public void setProductImages10(String productImages10) {
        this.productImages10 = productImages10;
    }

    public List<String> getSizelist() {
        return sizelist;
    }

    public void setSizelist(List<String> sizelist) {
        this.sizelist = sizelist;
    }

    public List<String> getColorslist() {
        return colorslist;
    }

    public void setColorslist(List<String> colorslist) {
        this.colorslist = colorslist;
    }

}
