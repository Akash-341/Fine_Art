package com.ort.fineart.Api_Handel;

import com.ort.fineart.Model.Response_Model.BottomBannerData_ResponseModel;
import com.ort.fineart.Model.Response_Model.Product_ResponseModel;
import com.ort.fineart.Model.Response_Model.RegistrationApiResponse_ResponseModel;
import com.ort.fineart.Model.Response_Model.TestimonialModel_ResponseModel;
import com.ort.fineart.Model.Response_Model.TopBannerModel_ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("Customer_Backend/add_customer/")
    Call<RegistrationApiResponse_ResponseModel> registerUser(

            @Field("FirstName") String firstName,
            @Field("LastName") String lastName,
            @Field("Email") String email,
            @Field("PhoneNumber") String phoneNumber,
            @Field("Password") String password,
            @Field("ConfirmPassword") String confirmPassword
    );


    @GET("banner_master_backend/get_published_bottombanner_list/")
    Call<List<BottomBannerData_ResponseModel>> getBottomBaner();



    @GET("testimonial_master_backend/get_published_customer_testimonials_list")
    Call<List<TestimonialModel_ResponseModel>> getTestimonials();


    @GET("banner_master_backend/get_published_topbanner_list/")
    Call<List<TopBannerModel_ResponseModel>> getTopBanner();

    @GET("product_master_backend/get_published_product_list/")
    Call<List<Product_ResponseModel>> getProductList();
}
