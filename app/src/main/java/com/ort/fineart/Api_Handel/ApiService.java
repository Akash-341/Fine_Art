package com.ort.fineart.Api_Handel;


import static com.ort.fineart.Api_Handel.All_URL.Best_Seller_List;
import static com.ort.fineart.Api_Handel.All_URL.Category_List;
import static com.ort.fineart.Api_Handel.All_URL.PERSONAL_DETAILS;
import static com.ort.fineart.Api_Handel.All_URL.Product_List;
import static com.ort.fineart.Api_Handel.All_URL.addtocart;
import static com.ort.fineart.Api_Handel.All_URL.addwishlist;
import static com.ort.fineart.Api_Handel.All_URL.bottom_baneer_list;
import static com.ort.fineart.Api_Handel.All_URL.dotd_List;
import static com.ort.fineart.Api_Handel.All_URL.orderHistoryList;
import static com.ort.fineart.Api_Handel.All_URL.productListbyCategory;
import static com.ort.fineart.Api_Handel.All_URL.register;
import static com.ort.fineart.Api_Handel.All_URL.testimonial_list;
import static com.ort.fineart.Api_Handel.All_URL.top_banner_list;
import static com.ort.fineart.Api_Handel.All_URL.vairentList;
import static com.ort.fineart.Api_Handel.All_URL.viewProduct;

import com.ort.fineart.Model.Response_Model.AddRemoveWishlist.Add_wishlist;
import com.ort.fineart.Model.Response_Model.Best_Seller.Best_Seller_Model;
import com.ort.fineart.Model.Response_Model.BottomBannerData_ResponseModel;
import com.ort.fineart.Model.Response_Model.Cart.Add_Cart;
import com.ort.fineart.Model.Response_Model.CategoryModel;
import com.ort.fineart.Model.Response_Model.Deal_Of_The_day.DOTD_ResponseModel;
import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistoryModel;
import com.ort.fineart.Model.Response_Model.PersonalDetailsData;
import com.ort.fineart.Model.Response_Model.ProductByCategory.Product_By_Category;
import com.ort.fineart.Model.Response_Model.ProductDetailsData.Product_Details_Model;
import com.ort.fineart.Model.Response_Model.Product_ResponseModel;
import com.ort.fineart.Model.Response_Model.RegistrationApiResponse_ResponseModel;
import com.ort.fineart.Model.Response_Model.Testimonial_ResponseModel;
import com.ort.fineart.Model.Response_Model.TopBannerModel_ResponseModel;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    //registration
    @FormUrlEncoded
    @POST(register)
    Call<RegistrationApiResponse_ResponseModel> registerUser(

            @Field("FirstName") String firstName,
            @Field("LastName") String lastName,
            @Field("Email") String email,
            @Field("PhoneNumber") String phoneNumber,
            @Field("Password") String password,
            @Field("ConfirmPassword") String confirmPassword
    );

    //bottom banner list dashboard
    @GET(bottom_baneer_list)
    Call<List<BottomBannerData_ResponseModel>> getBottomBaner();


    //testimonial list dashboard
    @GET(testimonial_list)
    Call<List<Testimonial_ResponseModel>> getTestimonials();

    //Top banner list Dashboard
    @GET(top_banner_list)
    Call<List<TopBannerModel_ResponseModel>> getTopBanner();

    //published
    @GET(dotd_List)
    Call<DOTD_ResponseModel> getProductList();

    //DOtd_list  hubpage
    @GET(Category_List)
    Call<List<CategoryModel>> getCategoriesList();


    //Personal_Details
    @FormUrlEncoded
    @POST(PERSONAL_DETAILS)
    Call<PersonalDetailsData> getPersonalData(@Field("Email") String email);

    @GET(Best_Seller_List)
    Call<Best_Seller_Model> getbestSellerList();

    @FormUrlEncoded
    @POST(viewProduct)
    Call<Product_Details_Model> getProductData(@Field("varientId") String id);

    @FormUrlEncoded
    @POST(vairentList)
    Call<Product_Details_Model> getVarientData(@Field("ProductColor") String color,@Field("ProductSize") String size,@Field("ProductId") String id);

    @FormUrlEncoded
    @POST(addwishlist)
    Call<Add_wishlist> addtoWishlist(@Header("Authorization")String token,@Field("varient")String varient);

    @FormUrlEncoded
    @POST(addtocart)
    Call<Add_Cart> addtocartList(@Header("Authorization")String token, @Field("varientid")String varient ,@Field("quantity")String qty);


    @POST(orderHistoryList)
    Call<OrderHistoryModel> getOrderList(@Header("Authorization")String token);

    @FormUrlEncoded
    @POST(productListbyCategory)
    Call<Product_By_Category> getCategoryProductList(@Field("category")String catid);
}
