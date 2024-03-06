package com.example.fineart.Interface;

import com.example.fineart.Model.BottomBannerData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BottomBannerApiService {
        @GET("banner_master_backend/get_published_bottombanner_list/")
        Call<List<BottomBannerData>> getBottomBaner();
}
