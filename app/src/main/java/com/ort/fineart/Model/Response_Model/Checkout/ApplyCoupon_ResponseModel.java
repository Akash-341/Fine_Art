package com.ort.fineart.Model.Response_Model.Checkout;

import java.util.ArrayList;

public class ApplyCoupon_ResponseModel {
    private int CouponDiscount;
    private int TotalDiscount;
    private int TotalPriceWithoutDiscount;
    private int TotalPriceWithDiscount;
    private int Dileverycharges;
    ArrayList<ApplyCouponProductList_ResponseModel> cart_products;
    private String CouponCode;

    public ApplyCoupon_ResponseModel(int couponDiscount, int totalDiscount, int totalPriceWithoutDiscount, int totalPriceWithDiscount, int dileverycharges, ArrayList<ApplyCouponProductList_ResponseModel> cart_products, String couponCode) {
        CouponDiscount = couponDiscount;
        TotalDiscount = totalDiscount;
        TotalPriceWithoutDiscount = totalPriceWithoutDiscount;
        TotalPriceWithDiscount = totalPriceWithDiscount;
        Dileverycharges = dileverycharges;
        this.cart_products = cart_products;
        CouponCode = couponCode;
    }

    public int getCouponDiscount() {
        return CouponDiscount;
    }

    public void setCouponDiscount(int couponDiscount) {
        CouponDiscount = couponDiscount;
    }

    public int getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public int getTotalPriceWithoutDiscount() {
        return TotalPriceWithoutDiscount;
    }

    public void setTotalPriceWithoutDiscount(int totalPriceWithoutDiscount) {
        TotalPriceWithoutDiscount = totalPriceWithoutDiscount;
    }

    public int getTotalPriceWithDiscount() {
        return TotalPriceWithDiscount;
    }

    public void setTotalPriceWithDiscount(int totalPriceWithDiscount) {
        TotalPriceWithDiscount = totalPriceWithDiscount;
    }

    public int getDileverycharges() {
        return Dileverycharges;
    }

    public void setDileverycharges(int dileverycharges) {
        Dileverycharges = dileverycharges;
    }

    public ArrayList<ApplyCouponProductList_ResponseModel> getCart_products() {
        return cart_products;
    }

    public void setCart_products(ArrayList<ApplyCouponProductList_ResponseModel> cart_products) {
        this.cart_products = cart_products;
    }

    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }
}
