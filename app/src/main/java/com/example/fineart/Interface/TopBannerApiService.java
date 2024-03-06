package com.example.fineart.Interface;

import com.example.fineart.Model.BottomBannerData;
import com.example.fineart.Model.TopBannerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TopBannerApiService {
    @GET("banner_master_backend/get_published_topbanner_list/")
    Call<List<TopBannerModel>> getTopBanner();
}
