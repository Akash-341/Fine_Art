package com.ort.fineart.Ui.Activity.Authentication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ort.fineart.Model.Request_Model.Authentication.Login_RequestModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.Home_Activity;
import com.ort.fineart.Utils.Internet_Permission;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowDialog;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.ViewModel.Authentication_ViewModel;
import com.ort.fineart.databinding.ActivityLoginBinding;

public class Login_Activity extends AppCompatActivity {
    ActivityLoginBinding binding;
    Authentication_ViewModel authentication_viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewInit();
        ChangeStatusBar();

        binding.forgotPassTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(), Forgotpass_Activity.class);
                startActivity(intent);


            }
        });

        binding.signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Registration_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.passEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please enter email",Login_Activity.this);
                } else if (binding.passEdt.getText().toString().trim().length()==0) {
                    ShowToast.ShowMsgToast("Please enter password",Login_Activity.this);

                }else if (!Internet_Permission.isInternetConnected(Login_Activity.this)){

                    ShowToast.ShowMsgToast("Please check Internet",Login_Activity.this);

                }else {
                    Login();
                }
            }
        });

    }

    private void Login() {

       binding.loadingLottie.setVisibility(View.VISIBLE);
        authentication_viewModel.Login_Callback(binding.emailEdt.getText().toString(),binding.passEdt.getText().toString());

        authentication_viewModel.getLogin().observe(Login_Activity.this, new Observer<Login_RequestModel>() {
            @Override
            public void onChanged(Login_RequestModel login_requestModel) {

                if (login_requestModel!=null){

                    String userToken="",userId="",userEmail="",userFirstName="",userLastName="",userPhoneNumber="";

                    userToken=login_requestModel.getCustomer_token();

                    if (login_requestModel.getCustomer_payload()!=null){
                        userId= String.valueOf(login_requestModel.getCustomer_payload().getId());
                        userEmail=login_requestModel.getCustomer_payload().getEmail();
                        userFirstName=login_requestModel.getCustomer_payload().getFirstName();
                        userLastName=login_requestModel.getCustomer_payload().getLastName();
                        userPhoneNumber=login_requestModel.getCustomer_payload().getPhoneNumber();



                    }


                    SharedPreferenceManager sharedPreferenceManager=new SharedPreferenceManager(Login_Activity.this);
                    sharedPreferenceManager.saveUserDetails(userId, userEmail, userFirstName, userLastName, userToken,userPhoneNumber);

                    Intent intent=new Intent(getApplicationContext(), Home_Activity.class);
                    startActivity(intent);
                    finish();
                    binding.loadingLottie.setVisibility(View.GONE);

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
    public void ChangeStatusBar() {
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