package com.ort.fineart.Model.Response_Model.MyCart;

public class MyCart_ResponseModel {


    private int id;
    private String customer;
    private int VarientProductId;
    private int VarientId;
    private String Date;
    private String VarientColor;
    private String VarientSize;
    private int VarientPrice;
    private int VarientQuantity;
    private int VarientDiscountPrice;
    private int VarientDiscount;
    private String VarientDiscountMode;
    private int VarientWeight;
    private int VarientTotalWeight;
    private int VarientTotalWithoutDiscount;
    private int VarientTotalWithDiscount;
    private boolean isactive;
    private int ProductAvailableQuantity;
    private String ProductCategory;
    private String ProductSubCategory;
    private String ProductName;
    private String ProductNumber;
    private String HSNCODE;
    private String ProductDescription;
    private String ProductShortDescription;
    private String ProductUsage;
    private String ProductMoreDetails;
    private boolean Delivery_Charges;
    private boolean Best_seller;
    private boolean Deal_of_the_day;
    private String ProductImage;
    private int AverageRating;
    private boolean PublishStatus;

    public MyCart_ResponseModel(int id, String customer, int varientProductId, int varientId, String date, String varientColor, String varientSize, int varientPrice, int varientQuantity, int varientDiscountPrice, int varientDiscount, String varientDiscountMode, int varientWeight, int varientTotalWeight, int varientTotalWithoutDiscount, int varientTotalWithDiscount, boolean isactive, int productAvailableQuantity, String productCategory, String productSubCategory, String productName, String productNumber, String HSNCODE, String productDescription, String productShortDescription, String productUsage, String productMoreDetails, boolean delivery_Charges, boolean best_seller, boolean deal_of_the_day, String productImage, int averageRating, boolean publishStatus) {
        this.id = id;
        this.customer = customer;
        VarientProductId = varientProductId;
        VarientId = varientId;
        Date = date;
        VarientColor = varientColor;
        VarientSize = varientSize;
        VarientPrice = varientPrice;
        VarientQuantity = varientQuantity;
        VarientDiscountPrice = varientDiscountPrice;
        VarientDiscount = varientDiscount;
        VarientDiscountMode = varientDiscountMode;
        VarientWeight = varientWeight;
        VarientTotalWeight = varientTotalWeight;
        VarientTotalWithoutDiscount = varientTotalWithoutDiscount;
        VarientTotalWithDiscount = varientTotalWithDiscount;
        this.isactive = isactive;
        ProductAvailableQuantity = productAvailableQuantity;
        ProductCategory = productCategory;
        ProductSubCategory = productSubCategory;
        ProductName = productName;
        ProductNumber = productNumber;
        this.HSNCODE = HSNCODE;
        ProductDescription = productDescription;
        ProductShortDescription = productShortDescription;
        ProductUsage = productUsage;
        ProductMoreDetails = productMoreDetails;
        Delivery_Charges = delivery_Charges;
        Best_seller = best_seller;
        Deal_of_the_day = deal_of_the_day;
        ProductImage = productImage;
        AverageRating = averageRating;
        PublishStatus = publishStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getVarientProductId() {
        return VarientProductId;
    }

    public void setVarientProductId(int varientProductId) {
        VarientProductId = varientProductId;
    }

    public int getVarientId() {
        return VarientId;
    }

    public void setVarientId(int varientId) {
        VarientId = varientId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getVarientColor() {
        return VarientColor;
    }

    public void setVarientColor(String varientColor) {
        VarientColor = varientColor;
    }

    public String getVarientSize() {
        return VarientSize;
    }

    public void setVarientSize(String varientSize) {
        VarientSize = varientSize;
    }

    public int getVarientPrice() {
        return VarientPrice;
    }

    public void setVarientPrice(int varientPrice) {
        VarientPrice = varientPrice;
    }

    public int getVarientQuantity() {
        return VarientQuantity;
    }

    public void setVarientQuantity(int varientQuantity) {
        VarientQuantity = varientQuantity;
    }

    public int getVarientDiscountPrice() {
        return VarientDiscountPrice;
    }

    public void setVarientDiscountPrice(int varientDiscountPrice) {
        VarientDiscountPrice = varientDiscountPrice;
    }

    public int getVarientDiscount() {
        return VarientDiscount;
    }

    public void setVarientDiscount(int varientDiscount) {
        VarientDiscount = varientDiscount;
    }

    public String getVarientDiscountMode() {
        return VarientDiscountMode;
    }

    public void setVarientDiscountMode(String varientDiscountMode) {
        VarientDiscountMode = varientDiscountMode;
    }

    public int getVarientWeight() {
        return VarientWeight;
    }

    public void setVarientWeight(int varientWeight) {
        VarientWeight = varientWeight;
    }

    public int getVarientTotalWeight() {
        return VarientTotalWeight;
    }

    public void setVarientTotalWeight(int varientTotalWeight) {
        VarientTotalWeight = varientTotalWeight;
    }

    public int getVarientTotalWithoutDiscount() {
        return VarientTotalWithoutDiscount;
    }

    public void setVarientTotalWithoutDiscount(int varientTotalWithoutDiscount) {
        VarientTotalWithoutDiscount = varientTotalWithoutDiscount;
    }

    public int getVarientTotalWithDiscount() {
        return VarientTotalWithDiscount;
    }

    public void setVarientTotalWithDiscount(int varientTotalWithDiscount) {
        VarientTotalWithDiscount = varientTotalWithDiscount;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public int getProductAvailableQuantity() {
        return ProductAvailableQuantity;
    }

    public void setProductAvailableQuantity(int productAvailableQuantity) {
        ProductAvailableQuantity = productAvailableQuantity;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getProductSubCategory() {
        return ProductSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        ProductSubCategory = productSubCategory;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(String productNumber) {
        ProductNumber = productNumber;
    }

    public String getHSNCODE() {
        return HSNCODE;
    }

    public void setHSNCODE(String HSNCODE) {
        this.HSNCODE = HSNCODE;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getProductShortDescription() {
        return ProductShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        ProductShortDescription = productShortDescription;
    }

    public String getProductUsage() {
        return ProductUsage;
    }

    public void setProductUsage(String productUsage) {
        ProductUsage = productUsage;
    }

    public String getProductMoreDetails() {
        return ProductMoreDetails;
    }

    public void setProductMoreDetails(String productMoreDetails) {
        ProductMoreDetails = productMoreDetails;
    }

    public boolean isDelivery_Charges() {
        return Delivery_Charges;
    }

    public void setDelivery_Charges(boolean delivery_Charges) {
        Delivery_Charges = delivery_Charges;
    }

    public boolean isBest_seller() {
        return Best_seller;
    }

    public void setBest_seller(boolean best_seller) {
        Best_seller = best_seller;
    }

    public boolean isDeal_of_the_day() {
        return Deal_of_the_day;
    }

    public void setDeal_of_the_day(boolean deal_of_the_day) {
        Deal_of_the_day = deal_of_the_day;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public int getAverageRating() {
        return AverageRating;
    }

    public void setAverageRating(int averageRating) {
        AverageRating = averageRating;
    }

    public boolean isPublishStatus() {
        return PublishStatus;
    }

    public void setPublishStatus(boolean publishStatus) {
        PublishStatus = publishStatus;
    }
}
