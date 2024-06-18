package com.ort.fineart.Repository;


import androidx.lifecycle.MutableLiveData;


import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.ProductDetails.AddReview_RequestModel;
import com.ort.fineart.Model.Request_Model.ProductDetails.GetSpecifeproductDetailsByCSS_RequestModel;
import com.ort.fineart.Model.Request_Model.ProductDetails.SpecifeProductDetails_RequestModel;
import com.ort.fineart.Model.Response_Model.PersonalDetails_ResponseModel;
import com.ort.fineart.Model.Response_Model.ProductDetailsData.Product_Details_Model;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails_Repository {

    public ProductDetails_Repository() {
    }


    public void GetProductDetails_ApiCall(String varientId, RequestStatus_Interface requestStatus_interface){


        Call<SpecifeProductDetails_RequestModel> call = RetrofitClient.getInstance().getApiService().getSpecifeProductDetail(varientId);
       call.enqueue(new Callback<SpecifeProductDetails_RequestModel>() {
           @Override
           public void onResponse(Call<SpecifeProductDetails_RequestModel> call, Response<SpecifeProductDetails_RequestModel> response) {
               if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200){
                   requestStatus_interface.onSuccessResponse(response.body().getPayload());
               }else {
                   requestStatus_interface.onSuccessResponse(null);
               }
           }

           @Override
           public void onFailure(Call<SpecifeProductDetails_RequestModel> call, Throwable t) {
               requestStatus_interface.onSuccessResponse(null);
           }
       });



    }

    public void GetProductByCSS_ApiCall(int ProductID,String color,String size,RequestStatus_Interface requestStatus_interface){

        Call<GetSpecifeproductDetailsByCSS_RequestModel> call = RetrofitClient.getInstance().getApiService().getProductByCSS(ProductID,color,size);

        call.enqueue(new Callback<GetSpecifeproductDetailsByCSS_RequestModel>() {
            @Override
            public void onResponse(Call<GetSpecifeproductDetailsByCSS_RequestModel> call, Response<GetSpecifeproductDetailsByCSS_RequestModel> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200){
                    requestStatus_interface.onSuccessResponse(response.body().getPayload());
                }else {
                    requestStatus_interface.onSuccessResponse(null);
                }
            }

            @Override
            public void onFailure(Call<GetSpecifeproductDetailsByCSS_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });






    }


    public void AddProductReviews_ApiCall(String full_name,String title,String email,String message,String product_id,String rating_count,RequestStatus_Interface requestStatus_interface){


        HashMap<String,String>parms=new HashMap<>();
        parms.put("FullName",full_name);
        parms.put("Title",title);
        parms.put("Email",email);
        parms.put("Message",message);
        parms.put("ProductId",product_id);
        parms.put("RatingCount",rating_count);

        Call<AddReview_RequestModel> call = RetrofitClient.getInstance().getApiService().addReview(parms);
        call.enqueue(new Callback<AddReview_RequestModel>() {
            @Override
            public void onResponse(Call<AddReview_RequestModel> call, Response<AddReview_RequestModel> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200){
                    requestStatus_interface.onSuccessResponse(response.body());
                } else if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==201){
                    requestStatus_interface.onSuccessResponse(response.body());
                } else {
                    requestStatus_interface.onSuccessResponse(null);
                }
            }

            @Override
            public void onFailure(Call<AddReview_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });



    }






}
