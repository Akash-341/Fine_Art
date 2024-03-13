package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ort.fineart.Api_Handel.ApiService;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.BottomBannerData_ResponseModel;
import com.ort.fineart.Model.Response_Model.PersonalDetailsData;
import com.ort.fineart.R;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.ViewModel.PersonalDetailViewModel;
import com.ort.fineart.databinding.ActivityPersonalDetailsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Personal_Details_Activity extends AppCompatActivity {
    ActivityPersonalDetailsBinding binding;
    private PersonalDetailViewModel personalDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPersonalDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Drawable backArrow = getResources().getDrawable(R.drawable.back_arrow);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");fetchPeofileData();

    }
    private void fetchPeofileData() {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<PersonalDetailsData> call = apiService.getPersonalData(sharedPreferenceManager.getUserEmail());

        call.enqueue(new Callback<PersonalDetailsData>() {
            @Override
            public void onResponse(Call<PersonalDetailsData> call, Response<PersonalDetailsData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PersonalDetailsData personalDetailsData = response.body();
                    updateUI(personalDetailsData);
                }
            }

            @Override
            public void onFailure(Call<PersonalDetailsData> call, Throwable t) {
                // Handle API call failure
            }
        });
    }
    private void updateUI(PersonalDetailsData personalDetailsDataList) {
        binding.firstName.setText(personalDetailsDataList.getFirstName());
        binding.lastName.setText(personalDetailsDataList.getLastName());
        binding.editEmail.setText(personalDetailsDataList.getEmail());
        binding.phoneNumber.setText(personalDetailsDataList.getPhoneNumber());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}