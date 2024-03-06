package com.example.fineart.Api_Handel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("Customer_Backend/add_customer/")
    Call<RegistrationApiResponse> registerUser(

            @Field("FirstName") String firstName,
            @Field("LastName") String lastName,
            @Field("Email") String email,
            @Field("PhoneNumber") String phoneNumber,
            @Field("Password") String password,
            @Field("ConfirmPassword") String confirmPassword
    );


    @GET("banner_master_backend/get_published_bottombanner_list/")
    Call<List<BottomBannerData>> getBottomBaner();



    @GET("testimonial_master_backend/get_published_customer_testimonials_list")
    Call<List<TestimonialModel>> getTestimonials();


    @GET("banner_master_backend/get_published_topbanner_list/")
    Call<List<TopBannerModel>> getTopBanner();


}
