package com.example.fineart.Interface;

import com.example.fineart.Model.RegistrationApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
}
