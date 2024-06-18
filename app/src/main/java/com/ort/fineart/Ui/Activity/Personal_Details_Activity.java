package com.ort.fineart.Ui.Activity;

import static com.ort.fineart.Utils.Validations.isValidMobileNumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;

import android.view.View;


import com.ort.fineart.Model.Request_Model.PersonalDetails.Update_Personal_Details_RequestModel;
import com.ort.fineart.Model.Response_Model.PersonalDetails_ResponseModel;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;


import com.ort.fineart.ViewModel.Profile_ViewModel;
import com.ort.fineart.databinding.ActivityPersonalDetailsBinding;

public class Personal_Details_Activity extends AppCompatActivity {
    ActivityPersonalDetailsBinding binding;
    Profile_ViewModel profile_viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPersonalDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewInit();

        GetPersonalData();


        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        binding.updateDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.firstNameEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter First Name",getApplicationContext());
                } else if (binding.lastNameEdt.getText().toString().trim().length()==0) {
                    ShowToast.ShowMsgToast("Please Enter Last Name",getApplicationContext());
                } else if (binding.phoneNumberEdt.getText().toString().trim().length()==0) {
                    ShowToast.ShowMsgToast("Please Enter Mobile No",getApplicationContext());

                } else if (!isValidMobileNumber(binding.phoneNumberEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Please Enter Valid Mobile No",getApplicationContext());

                }else {
                    binding.loadingLottie.setVisibility(View.VISIBLE);
                    UpdateDetails(binding.firstNameEdt.getText().toString().trim(),binding.lastNameEdt.getText().toString().trim(),binding.phoneNumberEdt.getText().toString().trim());
                }


            }
        });
    }

    private void UpdateDetails(String firstName,String lastName,String phoneNo) {

        profile_viewModel.UpdatePersonalDetails_Callback(firstName,lastName,phoneNo);
        profile_viewModel.updatePersonalDetails().observe(Personal_Details_Activity.this, new Observer<Update_Personal_Details_RequestModel>() {
            @Override
            public void onChanged(Update_Personal_Details_RequestModel update_personal_details_requestModel) {


                Update_Personal_Details_RequestModel model= update_personal_details_requestModel;

                if (model!=null&&model.getStatus()==200){
                    binding.loadingLottie.setVisibility(View.GONE);
                    ShowToast.ShowMsgToast("Personal Details Updated Successfully !!",getApplicationContext());
                    finish();
                }else {

                    binding.loadingLottie.setVisibility(View.GONE);
                    ShowToast.ShowMsgToast("Something Wrong ,Please Try Again",getApplicationContext());

                }

            }
        });





    }

    private void GetPersonalData() {

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);

        profile_viewModel.GetPersonalDetails_Callback(sharedPreferenceManager.getUserEmail().trim());
        profile_viewModel.getProfileDetails().observe(Personal_Details_Activity.this, new Observer<PersonalDetails_ResponseModel>() {
            @Override
            public void onChanged(PersonalDetails_ResponseModel personalDetails_responseModel) {
                if (personalDetails_responseModel!=null){


                    String firstName=personalDetails_responseModel.getFirstName()!= null ?personalDetails_responseModel.getFirstName(): "";
                    String lastName=personalDetails_responseModel.getLastName()!= null ?personalDetails_responseModel.getLastName(): "";
                    String email=personalDetails_responseModel.getEmail()!= null ?personalDetails_responseModel.getEmail(): "";
                    String phone=personalDetails_responseModel.getPhoneNumber()!= null ?personalDetails_responseModel.getPhoneNumber(): "";

                    //set Data
                    binding.firstNameEdt.setText(firstName);
                    binding.lastNameEdt.setText(lastName);
                    binding.emailEdt.setText(email);
                    binding.phoneNumberEdt.setText(phone);

                }else {

                }



            }
        });




    }


    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        profile_viewModel = viewModelProvider.get(Profile_ViewModel.class);

    }



}