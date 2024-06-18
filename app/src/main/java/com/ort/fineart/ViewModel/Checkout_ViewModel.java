package com.ort.fineart.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.Checkout.CouponList_RequestModel;
import com.ort.fineart.Model.Request_Model.Checkout.PlaceOrder_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.MyCartList_RequestModel;
import com.ort.fineart.Model.Response_Model.Checkout.ApplyCouponProductList_ResponseModel;
import com.ort.fineart.Model.Response_Model.Checkout.ApplyCoupon_ResponseModel;
import com.ort.fineart.Model.Response_Model.Checkout.Checkout_ProductList_ResponseModel;
import com.ort.fineart.Model.Response_Model.Checkout.Checkout_ResponseModel;
import com.ort.fineart.Model.Response_Model.Checkout.CouponList_ResponseModel;
import com.ort.fineart.Model.Response_Model.MyCart.MyCart_ResponseModel;
import com.ort.fineart.Repository.Checkout_Repository;
import com.ort.fineart.Repository.MyCart_Repository;

import java.util.ArrayList;
import java.util.List;

public class Checkout_ViewModel extends AndroidViewModel {
    Checkout_Repository checkout_repository;
    public MutableLiveData<Checkout_ResponseModel> checkOutDetails_LiveData = new MutableLiveData<>();

    public MutableLiveData<PlaceOrder_RequestModel> placeOrder_LiveData = new MutableLiveData<>();

    public MutableLiveData<List<CouponList_ResponseModel>> couponList_LiveData = new MutableLiveData<>();


    public Checkout_ViewModel(@NonNull Application application) {
        super(application);
        checkout_repository = new Checkout_Repository(application);


    }


    /**
     * All Return
     */
    public LiveData<Checkout_ResponseModel> getCheckoutDetails() {
        return checkOutDetails_LiveData;
    }


    public LiveData<PlaceOrder_RequestModel> placeOrder() {
        return placeOrder_LiveData;
    }


    public LiveData<List<CouponList_ResponseModel>> getCouponList() {
        return couponList_LiveData;
    }

    /**
     * Api Calls
     */


    public void GetCheckoutDetails_Callback() {

        checkout_repository.GetCheckoutOrderDetails_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                Checkout_ResponseModel model = (Checkout_ResponseModel) data;

                if (model != null) {
                    checkOutDetails_LiveData.postValue(model);
                } else {
                    checkOutDetails_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                checkOutDetails_LiveData.postValue(null);
            }
        });


    }


    public void QuickBuyCheckoutDetails_Callback(String productId, String color, String size, String quantity) {
        checkout_repository.QuickBuyOrderDetails_ApiCall(productId, color, size, quantity, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                Checkout_ResponseModel model = (Checkout_ResponseModel) data;

                if (model != null) {
                    checkOutDetails_LiveData.postValue(model);
                } else {
                    checkOutDetails_LiveData.postValue(null);
                }
            }

            @Override
            public void onFailureResponse(String message) {
                checkOutDetails_LiveData.postValue(null);
            }
        });


    }

    public void PlaceOrder_Callback(String Order_Products, String Amount, String CouponId, String ShoppingAddress, String TotalPrice, String CouponDiscount, String TotalDiscount, String GiftCharges, String DeliveryCharges, String PaymentMode) {

        checkout_repository.PlaceOrder_ApiCall(Order_Products, Amount, CouponId, ShoppingAddress, TotalPrice, CouponDiscount, TotalDiscount, GiftCharges, DeliveryCharges, PaymentMode, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                PlaceOrder_RequestModel model = (PlaceOrder_RequestModel) data;

                if (model != null) {

                    placeOrder_LiveData.postValue(model);
                } else {
                    placeOrder_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                placeOrder_LiveData.postValue(null);
            }
        });


    }


    public void GetCouponList_Callback() {
        checkout_repository.GetCouponsList_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                CouponList_RequestModel model = (CouponList_RequestModel) data;
                if (model != null && model.getPayload() != null & model.getPayload().size() != 0) {
                    couponList_LiveData.postValue(model.getPayload());

                } else {
                    couponList_LiveData.postValue(null);
                }
            }

            @Override
            public void onFailureResponse(String message) {
                couponList_LiveData.postValue(null);
            }
        });
    }

    public void ApplyCouponCode_Callback(String Order_Products, String TotalPrice, String CouponId) {
        checkout_repository.ApplyCoupon_ApiCall(Order_Products, TotalPrice, CouponId, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                ApplyCoupon_ResponseModel model = (ApplyCoupon_ResponseModel) data;
                if (data != null) {
                    ArrayList<Checkout_ProductList_ResponseModel> cart_products = new ArrayList<>();
                    for (ApplyCouponProductList_ResponseModel couponProductList : model.getCart_products()) {
                        cart_products.add(new Checkout_ProductList_ResponseModel(
                                couponProductList.getId(),
                                couponProductList.getCustomer(),
                                couponProductList.getVarientProductId(),
                                couponProductList.getVarientId(),
                                couponProductList.getVarientColor(),
                                couponProductList.getVarientSize(),
                                couponProductList.getVarientPrice(),
                                couponProductList.getVarientQuantity(),
                                couponProductList.getVarientDiscountPrice(),
                                couponProductList.getVarientDiscount(),
                                couponProductList.getVarientDiscountMode(),
                                couponProductList.getVarientWeight(),
                                couponProductList.getVarientTotalWeight(),
                                couponProductList.getVarientTotalWithoutDiscount(),
                                couponProductList.getVarientTotalWithDiscount(),
                                false,
                                couponProductList.isIsactive(),
                                couponProductList.getProductImage(),
                                couponProductList.getProduct_name()
                        ));
                    }


                    Checkout_ResponseModel checkout_responseModel = new Checkout_ResponseModel(
                            cart_products,
                            model.getTotalDiscount(),
                            model.getTotalPriceWithoutDiscount(),
                            model.getTotalPriceWithDiscount(),
                            model.getDileverycharges(),
                            model.getCouponDiscount()

                    );

                    checkOutDetails_LiveData.postValue(checkout_responseModel);

                    Log.e("ApplyCouponApiCode","ViewModel-:"+checkout_responseModel.toString());


                } else {
                    checkOutDetails_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {

            }
        });

    }

}
