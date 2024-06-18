package com.ort.fineart.Repository;

import android.util.Log;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.Hub.GetHubProductListByCategory_RequestModel;
import com.ort.fineart.Model.Request_Model.Hub.Hub_CategoryList_RequestModel;
import com.ort.fineart.Model.Request_Model.Hub.Hub_SubCategoryList_RequestModel;
import com.ort.fineart.Model.Response_Model.Hub.GetHubProductListByCategory_ResponseModel;
;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hub_Repository {


    public Hub_Repository() {

    }


    public void GetHubCategoryList_ApiCall(RequestStatus_Interface requestStatus_interface) {

        Call<Hub_CategoryList_RequestModel> call = RetrofitClient.getInstance().getApiService().getHubCategoryList();
        call.enqueue(new Callback<Hub_CategoryList_RequestModel>() {
            @Override
            public void onResponse(Call<Hub_CategoryList_RequestModel> call, Response<Hub_CategoryList_RequestModel> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getPayload() != null && response.body().getPayload().size() != 0) {

                    requestStatus_interface.onSuccessResponse(response.body().getPayload());

                } else {

                    requestStatus_interface.onSuccessResponse(null);

                }
            }

            @Override
            public void onFailure(Call<Hub_CategoryList_RequestModel> call, Throwable t) {

                requestStatus_interface.onSuccessResponse(null);
            }
        });


    }

    public void GetSubCategoryHubList_ApiCall(String categoryId, RequestStatus_Interface requestStatus_interface) {


        Call<Hub_SubCategoryList_RequestModel> call = RetrofitClient.getInstance().getApiService().getHubSubCategoryList(categoryId);
        call.enqueue(new Callback<Hub_SubCategoryList_RequestModel>() {
            @Override
            public void onResponse(Call<Hub_SubCategoryList_RequestModel> call, Response<Hub_SubCategoryList_RequestModel> response) {


                if (response.isSuccessful() && response.body() != null && response.body().getPayload() != null && response.body().getPayload().size() != 0) {

                    requestStatus_interface.onSuccessResponse(response.body().getPayload());

                } else {
                    requestStatus_interface.onSuccessResponse(null);

                }
            }

            @Override
            public void onFailure(Call<Hub_SubCategoryList_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);

            }
        });


    }

    public void GetHubListByCategory_ApiCall(String categoryId,String subcategory, RequestStatus_Interface requestStatus_interface) {

        Call<GetHubProductListByCategory_RequestModel> call = RetrofitClient.getInstance().getApiService().getHubListByCategory(categoryId,subcategory);
        call.enqueue(new Callback<GetHubProductListByCategory_RequestModel>() {
            @Override
            public void onResponse(Call<GetHubProductListByCategory_RequestModel> call, Response<GetHubProductListByCategory_RequestModel> response) {


                if (response.isSuccessful() && response.body() != null && response.body().getPayload() != null && response.body().getPayload().size() != 0) {
                    Log.e("HubListResponse", ""+response.body().getPayload().size()+"");
                    requestStatus_interface.onSuccessResponse(response.body().getPayload());

                } else {
                    Log.e("HubListResponse", ":1");

                    requestStatus_interface.onSuccessResponse(null);

                }
            }

            @Override
            public void onFailure(Call<GetHubProductListByCategory_RequestModel> call, Throwable t) {
                Log.e("HubListResponse", t.getMessage() + ":3");

                requestStatus_interface.onSuccessResponse(null);
            }
        });

    }


}
