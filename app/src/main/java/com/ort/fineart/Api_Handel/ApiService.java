package com.ort.fineart.Api_Handel;


import static com.ort.fineart.Api_Handel.All_URL.Best_Seller_List;
import static com.ort.fineart.Api_Handel.All_URL.Category_List;
import static com.ort.fineart.Api_Handel.All_URL.PERSONAL_DETAILS;
import static com.ort.fineart.Api_Handel.All_URL.UPDATE_PERSONAL_DETAILS;
import static com.ort.fineart.Api_Handel.All_URL.addInCart;
import static com.ort.fineart.Api_Handel.All_URL.addNewAddress;
import static com.ort.fineart.Api_Handel.All_URL.addProductReview;
import static com.ort.fineart.Api_Handel.All_URL.addRemoveWishListProduct;
import static com.ort.fineart.Api_Handel.All_URL.applyCoupon;
import static com.ort.fineart.Api_Handel.All_URL.bottom_baneer_list;
import static com.ort.fineart.Api_Handel.All_URL.changeMyCartProductQuantity;
import static com.ort.fineart.Api_Handel.All_URL.deal_of_the_day;

import static com.ort.fineart.Api_Handel.All_URL.deleteAddress;
import static com.ort.fineart.Api_Handel.All_URL.forgotPassword;
import static com.ort.fineart.Api_Handel.All_URL.getAddressById;
import static com.ort.fineart.Api_Handel.All_URL.getAddressList;
import static com.ort.fineart.Api_Handel.All_URL.getCouponList;
import static com.ort.fineart.Api_Handel.All_URL.getOrderDetails;
import static com.ort.fineart.Api_Handel.All_URL.getOrderHistoryList;
import static com.ort.fineart.Api_Handel.All_URL.getProductByCSS;
import static com.ort.fineart.Api_Handel.All_URL.getWishList;
import static com.ort.fineart.Api_Handel.All_URL.hubCategoryList;
import static com.ort.fineart.Api_Handel.All_URL.hubProductList;
import static com.ort.fineart.Api_Handel.All_URL.login;
import static com.ort.fineart.Api_Handel.All_URL.myCart;
import static com.ort.fineart.Api_Handel.All_URL.orderHistoryDetails;
import static com.ort.fineart.Api_Handel.All_URL.placeOrder;
import static com.ort.fineart.Api_Handel.All_URL.quickBuy;
import static com.ort.fineart.Api_Handel.All_URL.quickOrder;
import static com.ort.fineart.Api_Handel.All_URL.register;
import static com.ort.fineart.Api_Handel.All_URL.removeProductFromCart;
import static com.ort.fineart.Api_Handel.All_URL.resetPassword;
import static com.ort.fineart.Api_Handel.All_URL.specifeProductDetails;
import static com.ort.fineart.Api_Handel.All_URL.subCategoryList;
import static com.ort.fineart.Api_Handel.All_URL.testimonial_list;
import static com.ort.fineart.Api_Handel.All_URL.top_banner_list;
import static com.ort.fineart.Api_Handel.All_URL.updateAddress;
import static com.ort.fineart.Api_Handel.All_URL.viewProduct;

import com.ort.fineart.Model.Request_Model.Authentication.ForgotPassword_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.Login_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.Registration_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.ResetPassword_RequestModel;
import com.ort.fineart.Model.Request_Model.Checkout.ApplyCoupon_RequestModel;
import com.ort.fineart.Model.Request_Model.Checkout.Checkout_RequestModel;
import com.ort.fineart.Model.Request_Model.Checkout.CouponList_RequestModel;
import com.ort.fineart.Model.Request_Model.Checkout.PlaceOrder_RequestModel;
import com.ort.fineart.Model.Request_Model.Home.HomeBestSeller_RequestModel;
import com.ort.fineart.Model.Request_Model.Home.HomeDealOfDay_RequestModel;
import com.ort.fineart.Model.Request_Model.Hub.GetHubProductListByCategory_RequestModel;
import com.ort.fineart.Model.Request_Model.Hub.Hub_CategoryList_RequestModel;
import com.ort.fineart.Model.Request_Model.Hub.Hub_SubCategoryList_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.AddInCart_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.ChangeProductQuntity_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.MyCartList_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.RemoveCartProduct_RequestModel;
import com.ort.fineart.Model.Request_Model.OrderHistory.GetOrderDetails_RequestModel;
import com.ort.fineart.Model.Request_Model.OrderHistory.GetOrderHistoryList_RequestModel;
import com.ort.fineart.Model.Request_Model.PersonalDetails.Update_Personal_Details_RequestModel;
import com.ort.fineart.Model.Request_Model.ProductDetails.AddReview_RequestModel;
import com.ort.fineart.Model.Request_Model.ProductDetails.GetSpecifeproductDetailsByCSS_RequestModel;
import com.ort.fineart.Model.Request_Model.ProductDetails.SpecifeProductDetails_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.AddAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.DeleteAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.GetAddressById_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.GetUserAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.UpdateAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.WishList.AddRemove_WishListProduct_RequestModel;
import com.ort.fineart.Model.Request_Model.WishList.GetWishList_RequestModel;
import com.ort.fineart.Model.Response_Model.Home.HomeBottomBanner_ResponseModel;
import com.ort.fineart.Model.Response_Model.CategoryModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTopBanner_ResponseModel;
import com.ort.fineart.Model.Response_Model.Hub.Hub_CategoryList_ResponseModel;
import com.ort.fineart.Model.Response_Model.PersonalDetails_ResponseModel;
import com.ort.fineart.Model.Response_Model.ProductDetailsData.Product_Details_Model;
import com.ort.fineart.Model.Response_Model.Home.HomeTestimonial_ResponseModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {


    /**
     * Authentication
     */


    @FormUrlEncoded
    @POST(login)
    Call<Login_RequestModel> login(@FieldMap HashMap<String, String> Fields);

    @FormUrlEncoded
    @POST(register)
    Call<Registration_RequestModel> registerUser(@FieldMap HashMap<String, String> Fields);

    @FormUrlEncoded
    @POST(forgotPassword)
    Call<ForgotPassword_RequestModel> forgotPassword(@FieldMap HashMap<String, String> Fields);


    @FormUrlEncoded
    @POST(resetPassword)
    Call<ResetPassword_RequestModel> resetPassword(@Header("Authorization") String token, @FieldMap HashMap<String, String> Fields);


    /**
     * Home Screen
     */

    @GET(bottom_baneer_list)
    Call<List<HomeBottomBanner_ResponseModel>> getHomeBottomBanner();

    @GET(testimonial_list)
    Call<List<HomeTestimonial_ResponseModel>> getHomeTestimonials();

    @GET(top_banner_list)
    Call<List<HomeTopBanner_ResponseModel>> getHomeTopBanner();

    @GET(deal_of_the_day)
    Call<HomeDealOfDay_RequestModel> getDealOfTheDay();

    @GET(Best_Seller_List)
    Call<HomeBestSeller_RequestModel> getbestSellerList();


    @GET(Category_List)
    Call<List<CategoryModel>> getCategoriesList();


    /**
     * Profile_Details
     */
    @FormUrlEncoded
    @POST(PERSONAL_DETAILS)
    Call<PersonalDetails_ResponseModel> getPersonalData(@Field("Email") String email);


    @FormUrlEncoded
    @POST(UPDATE_PERSONAL_DETAILS)
    Call<Update_Personal_Details_RequestModel> updatePersonalDetails(@Header("Authorization") String token, @Field("FirstName")String FirstName, @Field("LastName")String LastName,@Field("PhoneNumber") String PhoneNumber);




    @FormUrlEncoded
    @POST(viewProduct)
    Call<Product_Details_Model> getProductData(@Field("varientId") String id);


    /**
     * My Cart
     */


    @GET(myCart)
    Call<MyCartList_RequestModel> getMyCart(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST(changeMyCartProductQuantity)
    Call<ChangeProductQuntity_RequestModel> changeCartProducatQuntity(@Header("Authorization") String token, @Field("cart_item_id") int cart_item_id, @Field("cart_item_quantity") int cart_item_quantity);

    @FormUrlEncoded
    @POST(removeProductFromCart)
    Call<RemoveCartProduct_RequestModel> removeCartProducat(@Header("Authorization") String token, @Field("id") int id);

    @FormUrlEncoded
    @POST(addInCart)
    Call<AddInCart_RequestModel> addInCartList(@Header("Authorization") String token, @Field("varientid") String varientid, @Field("quantity") String quantity);

















   /* @FormUrlEncoded
    @POST(changeMyCartProductQuantity)
    Call<ChangeProductQuntity_RequestModel>changeCartProducatQuntity(@Header("Authorization") String token,@Field("cart_item_id") int cart_item_id,@Field("cart_item_quantity") int cart_item_quantity);
*/


    /**
     * Wish List
     */
    @GET(getWishList)
    Call<GetWishList_RequestModel> getWishList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST(addRemoveWishListProduct)
    Call<AddRemove_WishListProduct_RequestModel> addRemoveWishListProduct(@Header("Authorization") String token, @Field("varient") int varient);


    /**
     * User Address
     */

    @POST(getAddressList)
    Call<GetUserAddress_RequestModel> getUserAddressList(@Header("Authorization") String token);


    @FormUrlEncoded
    @POST(addNewAddress)
    Call<AddAddress_RequestModel> addAddress(@Header("Authorization") String token, @FieldMap HashMap<String, String> fields);

    @FormUrlEncoded
    @POST(updateAddress)
    Call<UpdateAddress_RequestModel> updateAddress(@Header("Authorization") String token, @Field("addressid") int addressid, @FieldMap HashMap<String, String> fields);

    @FormUrlEncoded
    @POST(getAddressById)
    Call<GetAddressById_RequestModel> getAddressById(@Header("Authorization") String token, @Field("id") Integer id);

    @FormUrlEncoded
    @POST(deleteAddress)
    Call<DeleteAddress_RequestModel> deleteAddress(@Header("Authorization") String token, @Field("id") Integer id);


    /**
     * Hub
     */
    @GET(hubCategoryList)
    Call<Hub_CategoryList_RequestModel> getHubCategoryList();

    @FormUrlEncoded
    @POST(subCategoryList)
    Call<Hub_SubCategoryList_RequestModel> getHubSubCategoryList(@Field("main_category") String main_category);

    @FormUrlEncoded
    @POST(hubProductList)
    Call<GetHubProductListByCategory_RequestModel> getHubListByCategory(@Field("category") String category, @Field("subcategory") String subcategory);

    @FormUrlEncoded
    @POST(specifeProductDetails)
    Call<SpecifeProductDetails_RequestModel> getSpecifeProductDetail(@Field("varientId") String varientId);

    @FormUrlEncoded
    @POST(getProductByCSS)
    Call<GetSpecifeproductDetailsByCSS_RequestModel> getProductByCSS(@Field("ProductId") int ProductId, @Field("ProductColor") String ProductColor, @Field("ProductSize") String ProductSize);


    @FormUrlEncoded
    @POST(addProductReview)
    Call<AddReview_RequestModel> addReview(@FieldMap HashMap<String, String> fields);


    /**
     * Order History
     */
    @POST(getOrderHistoryList)
    Call<GetOrderHistoryList_RequestModel> getOrderHistoryList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST(orderHistoryDetails)
    Call<GetOrderDetails_RequestModel> getOrderDetails(@Header("Authorization") String token, @Field("OrderId") String OrderId);


    /**
     * Checkout
     */
    @POST(getOrderDetails)
    Call<Checkout_RequestModel> getCheckoutDetails(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST(quickBuy)
    Call<Checkout_RequestModel> quickBuyCheckoutDetails(@Header("Authorization") String token, @FieldMap HashMap<String, String> fields);


    @FormUrlEncoded
    @POST(placeOrder)
    Call<PlaceOrder_RequestModel> placeOrder(@Header("Authorization") String token, @FieldMap HashMap<String, String> fields);


    @GET(getCouponList)
    Call<CouponList_RequestModel> getCouponList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST(applyCoupon)
    Call<ApplyCoupon_RequestModel> applyCoupon(@Header("Authorization") String token, @FieldMap HashMap<String, String> fields);



}
