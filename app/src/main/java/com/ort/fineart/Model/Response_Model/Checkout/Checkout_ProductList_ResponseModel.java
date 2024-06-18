package com.ort.fineart.Model.Response_Model.Checkout;

public class Checkout_ProductList_ResponseModel {


    private int id;
    private String customer;
    private int VarientProductId;
    private int VarientId;
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
    private boolean quick_buy;

    private boolean isactive;
    private String ProductImage;
    private String product_name;

    public Checkout_ProductList_ResponseModel(int id, String customer, int varientProductId, int varientId, String varientColor, String varientSize, int varientPrice, int varientQuantity, int varientDiscountPrice, int varientDiscount, String varientDiscountMode, int varientWeight, int varientTotalWeight, int varientTotalWithoutDiscount, int varientTotalWithDiscount, boolean quick_buy, boolean isactive, String productImage, String product_name) {
        this.id = id;
        this.customer = customer;
        VarientProductId = varientProductId;
        VarientId = varientId;
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
        this.quick_buy = quick_buy;
        this.isactive = isactive;
        ProductImage = productImage;
        this.product_name = product_name;
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

    public boolean isQuick_buy() {
        return quick_buy;
    }

    public void setQuick_buy(boolean quick_buy) {
        this.quick_buy = quick_buy;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
