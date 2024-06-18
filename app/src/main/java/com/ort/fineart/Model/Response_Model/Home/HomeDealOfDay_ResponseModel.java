package com.ort.fineart.Model.Response_Model.Home;

public class HomeDealOfDay_ResponseModel {
    private int id;
    private String ProductCategory;
    private String ProductSubCategory;
    private String ProductName;
    private String ProductNumber;
    private String HSNCODE;
    private String ProductDescription;
    private String ProductShortDescription;
    private String ProductUsage;
    private String ProductMoreDetails;
    private boolean isactive;
    private boolean Delivery_Charges;
    private boolean Best_seller;
    private boolean Deal_of_the_day;
    private Object AverageRating;
    private boolean Status;
    private String ProductImage;
    private String ProductSize;
    private Object ProductWeight;
    private String ProductColor;
    private Object ProductDiscount;
    private Object ProductDiscountMode;
    private Object ProductQuantity;
    private Object ProductPrice;
    private Object ProductDiscountPrice;
    private Object Product_VarientId;

    public HomeDealOfDay_ResponseModel(int id, String productCategory, String productSubCategory, String productName, String productNumber, String HSNCODE, String productDescription, String productShortDescription, String productUsage, String productMoreDetails, boolean isactive, boolean delivery_Charges, boolean best_seller, boolean deal_of_the_day, Object averageRating, boolean status, String productImage, String productSize, Object productWeight, String productColor, Object productDiscount, Object productDiscountMode, Object productQuantity, Object productPrice, Object productDiscountPrice, Object product_VarientId) {
        this.id = id;
        ProductCategory = productCategory;
        ProductSubCategory = productSubCategory;
        ProductName = productName;
        ProductNumber = productNumber;
        this.HSNCODE = HSNCODE;
        ProductDescription = productDescription;
        ProductShortDescription = productShortDescription;
        ProductUsage = productUsage;
        ProductMoreDetails = productMoreDetails;
        this.isactive = isactive;
        Delivery_Charges = delivery_Charges;
        Best_seller = best_seller;
        Deal_of_the_day = deal_of_the_day;
        AverageRating = averageRating;
        Status = status;
        ProductImage = productImage;
        ProductSize = productSize;
        ProductWeight = productWeight;
        ProductColor = productColor;
        ProductDiscount = productDiscount;
        ProductDiscountMode = productDiscountMode;
        ProductQuantity = productQuantity;
        ProductPrice = productPrice;
        ProductDiscountPrice = productDiscountPrice;
        Product_VarientId = product_VarientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
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

    public Object getAverageRating() {
        return AverageRating;
    }

    public void setAverageRating(Object averageRating) {
        AverageRating = averageRating;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductSize() {
        return ProductSize;
    }

    public void setProductSize(String productSize) {
        ProductSize = productSize;
    }

    public Object getProductWeight() {
        return ProductWeight;
    }

    public void setProductWeight(Object productWeight) {
        ProductWeight = productWeight;
    }

    public String getProductColor() {
        return ProductColor;
    }

    public void setProductColor(String productColor) {
        ProductColor = productColor;
    }

    public Object getProductDiscount() {
        return ProductDiscount;
    }

    public void setProductDiscount(Object productDiscount) {
        ProductDiscount = productDiscount;
    }

    public Object getProductDiscountMode() {
        return ProductDiscountMode;
    }

    public void setProductDiscountMode(Object productDiscountMode) {
        ProductDiscountMode = productDiscountMode;
    }

    public Object getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(Object productQuantity) {
        ProductQuantity = productQuantity;
    }

    public Object getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(Object productPrice) {
        ProductPrice = productPrice;
    }

    public Object getProductDiscountPrice() {
        return ProductDiscountPrice;
    }

    public void setProductDiscountPrice(Object productDiscountPrice) {
        ProductDiscountPrice = productDiscountPrice;
    }

    public Object getProduct_VarientId() {
        return Product_VarientId;
    }

    public void setProduct_VarientId(Object product_VarientId) {
        Product_VarientId = product_VarientId;
    }
}
