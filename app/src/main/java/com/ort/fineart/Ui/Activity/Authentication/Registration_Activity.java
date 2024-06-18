package com.ort.fineart.Ui.Activity.Authentication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ort.fineart.Model.Request_Model.Authentication.Login_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.Registration_RequestModel;
import com.ort.fineart.R;
import com.ort.fineart.Utils.Internet_Permission;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowDialog;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.Utils.Validations;
import com.ort.fineart.ViewModel.Authentication_ViewModel;
import com.ort.fineart.databinding.ActivityRegistrationBinding;

public class Registration_Activity extends AppCompatActivity {
    ActivityRegistrationBinding binding;
    Authentication_ViewModel authentication_viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
              binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewInit();
        ChangeStatusBar();



        binding.signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.firstNameEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please enter firstname", Registration_Activity.this);
                } else if (!Validations.validateTextOnly(binding.firstNameEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Please valid firstname", Registration_Activity.this);
                } else if (binding.lastNameEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please enter lastname", Registration_Activity.this);

                }else if (!Validations.validateTextOnly(binding.lastNameEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Please valid lastname", Registration_Activity.this);
                }else if (binding.mobileNoEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please enter mobile no", Registration_Activity.this);

                } else if (!Validations.isValidMobileNumber(binding.mobileNoEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Please enter valid mobile no", Registration_Activity.this);


                } else if (binding.emailEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please enter email", Registration_Activity.this);

                } else if (!Validations.isValidEmail(binding.emailEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Please enter valid email", Registration_Activity.this);

                } else if (binding.passEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please enter password", Registration_Activity.this);

                } else if (!Validations.isValidPassword(binding.passEdt.getText().toString().trim())) {

                    ShowToast.ShowMsgToast("Password length must be 8 and must contain  at least one digit, one uppercase letter, and one lowercase letter ", Registration_Activity.this);

                } else if (binding.confirmPassEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please confirm password", Registration_Activity.this);

                } else if (!binding.passEdt.getText().toString().trim().equals(binding.confirmPassEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Confirm password not match", Registration_Activity.this);


                } else if (!Internet_Permission.isInternetConnected(Registration_Activity.this)) {

                    ShowToast.ShowMsgToast("Please check Internet", Registration_Activity.this);

                } else {
                    Registration();
                }
            }
        });

    }

    private void Registration() {

        String firstName=binding.firstNameEdt.getText().toString();
        String lastName=binding.lastNameEdt.getText().toString();
        String mobileNo=binding.mobileNoEdt.getText().toString();
        String email=binding.emailEdt.getText().toString();
        String password=binding.passEdt.getText().toString();


      binding.loadingLottie.setVisibility(View.VISIBLE);
        authentication_viewModel.Registration_Callback(firstName,lastName,mobileNo,email,password);
        authentication_viewModel.getRegistration().observe(Registration_Activity.this, new Observer<Registration_RequestModel>() {
            @Override
            public void onChanged(Registration_RequestModel registration_requestModel) {
                if (registration_requestModel!=null){
                    binding.loadingLottie.setVisibility(View.GONE);
                    Intent intent=new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(intent);
                    finish();
                }else {
                    binding.loadingLottie.setVisibility(View.GONE);
                }
            }
        });




    }


    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        authentication_viewModel = viewModelProvider.get(Authentication_ViewModel.class);

    }

    private void ChangeStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}