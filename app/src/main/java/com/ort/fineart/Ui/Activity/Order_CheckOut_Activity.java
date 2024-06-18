package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ort.fineart.Model.Request_Model.Checkout.PlaceOrder_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.UpdateDeleteAddress_Interface;
import com.ort.fineart.Model.Response_Model.Checkout.OrderProductList_ResponseModel;
import com.ort.fineart.Model.Response_Model.Checkout.Checkout_ProductList_ResponseModel;
import com.ort.fineart.Model.Response_Model.Checkout.Checkout_ResponseModel;
import com.ort.fineart.Model.Response_Model.Checkout.CouponList_ResponseModel;
import com.ort.fineart.Model.Response_Model.Checkout.PlaceOrder_ResponseModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.CheckoutProductList_RecyclerAdapter;
import com.ort.fineart.Recycler_Adapter.DeliveryAddressList_RecyclerviewAdapter;
import com.ort.fineart.Ui.Dialogs.SuccesfullDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Sucess_Dialog_Interface;
import com.ort.fineart.Ui.Fragment.Add_Delivery_Address_Fragment;
import com.ort.fineart.Utils.CheckoutVerticalSpaceItemDecoration;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowDialog;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.ViewModel.Authentication_ViewModel;
import com.ort.fineart.ViewModel.Checkout_ViewModel;
import com.ort.fineart.ViewModel.UserAddress_ViewModel;
import com.ort.fineart.databinding.ActivityOrderCheckOutBinding;

import java.util.ArrayList;
import java.util.List;

public class Order_CheckOut_Activity extends AppCompatActivity {
    ActivityOrderCheckOutBinding binding;

    DeliveryAddressList_RecyclerviewAdapter deliveryAddressList_recyclerviewAdapter;
    CheckoutProductList_RecyclerAdapter checkoutProductList_recyclerAdapter;
    UserAddress_ViewModel userAddress_viewModel;
    Checkout_ViewModel checkout_viewModel;
    int RecyclerViewCardMargin = 16;
    Add_Delivery_Address_Fragment.Delivery_Address_Interface delivery_address_interface;

    UpdateDeleteAddress_Interface updateDeleteAddress_interface;


    String userSelectedAddress = "";

    List<OrderProductList_ResponseModel> PlaceOrder_productList = new ArrayList<>();


    /**
     * Place Order
     */
    String Place_Order_Products;
    String Place_Order_Amount;
    String Place_Order_CouponId = "";
    String Place_Order_ShoppingAddress;
    String Place_Order_TotalPrice;
    String Place_Order_CouponDiscount;
    String Place_Order_TotalDiscount;
    String Place_Order_GiftCharges;
    String Place_Order_DeliveryCharges;
    String Place_Order_PaymentMode = "Online";
    Sucess_Dialog_Interface sucess_dialog_interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderCheckOutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getApplicationContext(), RecyclerViewCardMargin);
        ViewInit();
        Intent intent = getIntent();
        getOrderDetails_ByCheckoutType(intent);


        GetAddressList();
        SetAddressList();
        SetCheckoutDetails();
        GetBillingInformation();


        //All Interface
        delivery_address_interface = new Add_Delivery_Address_Fragment.Delivery_Address_Interface() {
            @Override
            public void addressAdded(boolean isAdded) {
                if (isAdded) {
                    GetAddressList();
                }

            }
        };

        updateDeleteAddress_interface = new UpdateDeleteAddress_Interface() {
            @Override
            public void deleteAddress(int AddressId) {

            }

            @Override
            public void updateAddress(int addressId, String AddressLine1, String AddressLine2, String Country, String State, String City, int Pincode) {
                String userAddress = AddressLine1 + " " + AddressLine2 + " ," + City + " ," + State + " ," + Country + " -" + Pincode;
                userSelectedAddress = userAddress;

            }
        };
        sucess_dialog_interface=new Sucess_Dialog_Interface() {
            @Override
            public void doneSuccessfully(String TaskName, boolean TaskDoneStatus, boolean Y_N) {
                if (TaskName.equals("PlaceOrder")&&TaskDoneStatus){


                    Intent intent=new Intent(getApplicationContext(), Home_Activity.class);
                    startActivity(intent);
                    finish();

                }else {


                }


            }
        };
        /**
         *
         */

        binding.addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Delivery_Address_Fragment address_fragment = new Add_Delivery_Address_Fragment(delivery_address_interface);
                address_fragment.show(getSupportFragmentManager(), address_fragment.getTag());


            }
        });


        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        binding.viewCouponListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), View_All_Coupons_Activity.class);
                startActivityForResult(intent, 1000);

            }
        });


        binding.removeCouponBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewCouponListBtn.setVisibility(View.VISIBLE);
                binding.viewCouponDetailsBtn.setVisibility(View.GONE);
                binding.CouponeCodeTxt.setText("");
                getOrderDetails_ByCheckoutType(intent);

            }
        });


        deliveryAddressList_recyclerviewAdapter = new DeliveryAddressList_RecyclerviewAdapter(getApplicationContext(), updateDeleteAddress_interface);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.deliveryRecyclerview.setLayoutManager(layoutManager);
        binding.deliveryRecyclerview.setAdapter(deliveryAddressList_recyclerviewAdapter);
        binding.deliveryRecyclerview.addItemDecoration(new CheckoutVerticalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));


        checkoutProductList_recyclerAdapter = new CheckoutProductList_RecyclerAdapter(getApplicationContext());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.productListRecyclerview.setLayoutManager(layoutManager1);
        binding.productListRecyclerview.setAdapter(checkoutProductList_recyclerAdapter);
        binding.productListRecyclerview.addItemDecoration(new CheckoutVerticalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));


        binding.placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userSelectedAddress.length() == 0) {
                    ShowToast.ShowMsgToast("Please Select Delivery Address", getApplicationContext());
                } else {


                    JsonArray jsonArray = new JsonArray();
                    for (OrderProductList_ResponseModel variant : PlaceOrder_productList) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("varient_id", variant.getVarient_id());
                        jsonObject.addProperty("varient_quantity", variant.getVarient_quantity());
                        jsonArray.add(jsonObject);
                    }

                    //   String Order_Products,String Amount,String CouponId,String ShoppingAddress,String TotalPrice,String CouponDiscount,String TotalDiscount,String GiftCharges,String DeliveryCharges,String PaymentMode
                    Place_Order_Products = jsonArray.toString();
                    Place_Order_ShoppingAddress = userSelectedAddress;


                    PlaceOrder(Place_Order_Products, Place_Order_Amount, Place_Order_CouponId, Place_Order_ShoppingAddress, Place_Order_Amount, Place_Order_CouponDiscount, Place_Order_TotalDiscount, Place_Order_GiftCharges, Place_Order_DeliveryCharges, Place_Order_PaymentMode);


                }


            }
        });


    }

    private void PlaceOrder(String place_order_products, String place_order_amount, String place_order_couponId, String place_order_shoppingAddress, String place_order_totalPrice, String place_order_couponDiscount, String place_order_totalDiscount, String place_order_giftCharges, String place_order_deliveryCharges, String place_order_paymentMode) {



        binding.loadingLottie.setVisibility(View.VISIBLE);
        checkout_viewModel.PlaceOrder_Callback(place_order_products,place_order_amount,place_order_couponId,place_order_shoppingAddress,place_order_totalPrice,place_order_couponDiscount,place_order_totalDiscount,"",place_order_deliveryCharges,place_order_paymentMode);
        checkout_viewModel.placeOrder().observe(Order_CheckOut_Activity.this, new Observer<PlaceOrder_RequestModel>() {
            @Override
            public void onChanged(PlaceOrder_RequestModel placeOrder_requestModel) {

                if (placeOrder_requestModel!=null){
                    binding.loadingLottie.setVisibility(View.GONE);
                    SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.success_vector_icon), placeOrder_requestModel.getMessge(), "Yes,Okay","PlaceOrder",true);
                    succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());
                    checkout_viewModel.placeOrder().removeObserver(this);



                }else {
                    binding.loadingLottie.setVisibility(View.GONE);
                   ShowToast.ShowMsgToast("Something wrong,please try again",getApplicationContext());
                    checkout_viewModel.placeOrder().removeObserver(this);

                }

            }
        });






    }


    private void GetBillingInformation() {

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());
        String FirstName = sharedPreferenceManager.getUserFirstName();
        String LastName = sharedPreferenceManager.getUserLastName();
        String UserName = FirstName + " " + LastName;
        String Email = sharedPreferenceManager.getUserEmail();
        String PhoneNo = sharedPreferenceManager.getUserPhone();


        binding.userNameTxt.setText(UserName);
        binding.userEmailTxt.setText(Email);
        binding.UserMobileNoTxt.setText(PhoneNo);


    }

    private void GetAddressList() {
        userAddress_viewModel.GetAddressList_Callback();
    }

    private void SetAddressList() {
        userAddress_viewModel.getAddressList().observe(this, new Observer<List<GetUserAddress_ResponseModel>>() {
            @Override
            public void onChanged(List<GetUserAddress_ResponseModel> getUserAddress_responseModels) {
                if (getUserAddress_responseModels != null && getUserAddress_responseModels.size() != 0) {
                    deliveryAddressList_recyclerviewAdapter.setDataList(getUserAddress_responseModels);
                } else {

                }
            }
        });
    }


    private void GetCheckoutDetails() {
        checkout_viewModel.GetCheckoutDetails_Callback();
    }

    private void SetCheckoutDetails() {

        checkout_viewModel.getCheckoutDetails().observe(this, new Observer<Checkout_ResponseModel>() {
            @Override
            public void onChanged(Checkout_ResponseModel checkout_responseModel) {
                if (checkout_responseModel != null) {


                    int TotalPriceWithoutDiscount = checkout_responseModel.getTotalPriceWithoutDiscount();     //Sub Total
                    int TotalDiscount = checkout_responseModel.getTotalDiscount();                 //Total Discount
                    int Dileverycharges = checkout_responseModel.getDileverycharges();
                    int TotalPriceWithDiscount = checkout_responseModel.getTotalPriceWithDiscount();        //Total
                    int CouponDiscount = checkout_responseModel.getCouponDiscount();

                    Place_Order_Amount = String.valueOf(TotalPriceWithDiscount);
                    Place_Order_CouponDiscount = String.valueOf(CouponDiscount);
                    Place_Order_DeliveryCharges = String.valueOf(Dileverycharges);
                    Place_Order_TotalDiscount = String.valueOf(TotalDiscount);


                    PriceDetails_Data(TotalPriceWithoutDiscount, TotalDiscount, Dileverycharges, TotalPriceWithDiscount, CouponDiscount);


                    if (checkout_responseModel != null && checkout_responseModel.getCart_products().size() != 0) {
                        checkoutProductList_recyclerAdapter.setDataList(checkout_responseModel.getCart_products());
                        PlaceOrder_productList.clear();
                        for (Checkout_ProductList_ResponseModel checkout_productList_responseModel : checkout_responseModel.getCart_products()) {
                            PlaceOrder_productList.add(new OrderProductList_ResponseModel(String.valueOf(checkout_productList_responseModel.getVarientId()), String.valueOf(checkout_productList_responseModel.getVarientQuantity()),String.valueOf(checkout_productList_responseModel.getVarientProductId())));
                        }

                    } else {

                    }


                } else {

                }
            }
        });


    }


    private void GetQuickBuyCheckoutDetails(String productId, String productColor, String productSize, String productQuantity) {
        checkout_viewModel.QuickBuyCheckoutDetails_Callback(productId, productColor, productSize, productQuantity);
    }

    private void PriceDetails_Data(int totalPriceWithoutDiscount, int totalDiscount, int dileverycharges, int totalPriceWithDiscount, int couponDiscount) {

        binding.subTotalTxt.setText(totalPriceWithoutDiscount + " ₹");
        binding.TotalDiscountTxt.setText(totalDiscount + " ₹");
        binding.DeliveryChangersTxt.setText(dileverycharges + " ₹");
        binding.CouponDiscountTxt.setText(couponDiscount + " ₹");
        binding.TotalPriceTxt.setText(totalPriceWithDiscount + " ₹");

    }




    public void getOrderDetails_ByCheckoutType(Intent intent){
        if (intent.hasExtra("QuickBuy")) {

            String ProductId = String.valueOf(intent.getIntExtra("ProductId", 0));
            String ProductQuantity = String.valueOf(intent.getIntExtra("ProductQuntity", 0));
            String Product_color = intent.getStringExtra("ProductColor");
            String Product_size = intent.getStringExtra("ProductSize");
            GetQuickBuyCheckoutDetails(ProductId, Product_color, Product_size, ProductQuantity);

        } else {

            GetCheckoutDetails();
        }
    }


    public void ApplyCoupon(String Order_Products, String TotalPrice, String CouponId){
        checkout_viewModel.ApplyCouponCode_Callback(Order_Products,TotalPrice,CouponId);
    }



    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        userAddress_viewModel = viewModelProvider.get(UserAddress_ViewModel.class);
        checkout_viewModel = viewModelProvider.get(Checkout_ViewModel.class);


    }


    //For Getting Data From Coupon Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            // Check if the result was successful
            if (resultCode == RESULT_OK) {

                if (data != null) {
                    CouponList_ResponseModel couponList_responseModel = (CouponList_ResponseModel) data.getSerializableExtra("CouponData");

                    binding.viewCouponListBtn.setVisibility(View.GONE);
                    binding.viewCouponDetailsBtn.setVisibility(View.VISIBLE);
                    binding.CouponeCodeTxt.setText(couponList_responseModel.getCouponCode());

                    JsonArray jsonArray = new JsonArray();
                    for (OrderProductList_ResponseModel variant : PlaceOrder_productList) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("varient_id", variant.getVarient_id());
                        jsonObject.addProperty("varient_quantity", variant.getVarient_quantity());
                        jsonObject.addProperty("CartProductId",variant.getVarientProductId());
                        jsonArray.add(jsonObject);
                    }

                    //   String Order_Products,String Amount,String CouponId,String ShoppingAddress,String TotalPrice,String CouponDiscount,String TotalDiscount,String GiftCharges,String DeliveryCharges,String PaymentMode
                    Place_Order_Products = jsonArray.toString();
                    ApplyCoupon(Place_Order_Products,Place_Order_Amount,String.valueOf(couponList_responseModel.getId()));

                }
            } else if (resultCode == RESULT_CANCELED) {
                Place_Order_CouponId = "";


                // The result was canceled by the user or other factors
            }
        }
    }


}