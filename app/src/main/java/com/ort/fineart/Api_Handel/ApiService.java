package com.ort.fineart.Api_Handel;

import static com.ort.fineart.Api_Handel.All_URL.Testimonial_List;
import static com.ort.fineart.Api_Handel.All_URL.bottom_baneer_list;
import static com.ort.fineart.Api_Handel.All_URL.register;

import com.ort.fineart.Model.Response_Model.BottomBannerData_ResponseModel;
import com.ort.fineart.Model.Response_Model.Product_ResponseModel;
import com.ort.fineart.Model.Response_Model.RegistrationApiResponse_ResponseModel;
import com.ort.fineart.Model.Response_Model.Testimonial_ResponseModel;
import com.ort.fineart.Model.Response_Model.TopBannerModel_ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    @GET(Testimonial_List)
    Call<List<Testimonial_ResponseModel>> getTestimonials();

    //Top banner list Dashboard
    @GET(All_URL.Top_banner_list)
    Call<List<TopBannerModel_ResponseModel>> getTopBanner();

    //
    @GET("product_master_backend/get_published_product_list/")
    Call<List<Product_ResponseModel>> getProductList();

    @GET("catlogue_master_backend/get_category_list/")
    Call<List<String>> getCategoriesList();
}
