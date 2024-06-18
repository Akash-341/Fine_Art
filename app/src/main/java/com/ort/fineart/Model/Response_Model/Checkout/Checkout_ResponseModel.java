package com.ort.fineart.Model.Response_Model.Checkout;

import java.util.ArrayList;

public class Checkout_ResponseModel {

    private ArrayList<Checkout_ProductList_ResponseModel> cart_products;
    private int TotalDiscount;
    private int TotalPriceWithoutDiscount;
    private int TotalPriceWithDiscount;
    private int Dileverycharges;

    private int CouponDiscount;

    public Checkout_ResponseModel(ArrayList<Checkout_ProductList_ResponseModel> cart_products, int totalDiscount, int totalPriceWithoutDiscount, int totalPriceWithDiscount, int dileverycharges, int couponDiscount) {
        this.cart_products = cart_products;
        TotalDiscount = totalDiscount;
        TotalPriceWithoutDiscount = totalPriceWithoutDiscount;
        TotalPriceWithDiscount = totalPriceWithDiscount;
        Dileverycharges = dileverycharges;
        CouponDiscount = couponDiscount;
    }

    public ArrayList<Checkout_ProductList_ResponseModel> getCart_products() {
        return cart_products;
    }

    public void setCart_products(ArrayList<Checkout_ProductList_ResponseModel> cart_products) {
        this.cart_products = cart_products;
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

    public int getCouponDiscount() {
        return CouponDiscount;
    }

    public void setCouponDiscount(int couponDiscount) {
        CouponDiscount = couponDiscount;
    }

    @Override
    public String toString() {
        return "Checkout_ResponseModel{" +
                "cart_products=" + cart_products +
                ", TotalDiscount=" + TotalDiscount +
                ", TotalPriceWithoutDiscount=" + TotalPriceWithoutDiscount +
                ", TotalPriceWithDiscount=" + TotalPriceWithDiscount +
                ", Dileverycharges=" + Dileverycharges +
                ", CouponDiscount=" + CouponDiscount +
                '}';
    }
}
