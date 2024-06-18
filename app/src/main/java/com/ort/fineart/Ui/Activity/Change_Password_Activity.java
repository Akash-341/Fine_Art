package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ort.fineart.Model.Request_Model.Authentication.ResetPassword_RequestModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.Authentication.Registration_Activity;
import com.ort.fineart.Ui.Dialogs.SuccesfullDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Sucess_Dialog_Interface;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.Utils.Validations;
import com.ort.fineart.ViewModel.Authentication_ViewModel;
import com.ort.fineart.databinding.ActivityChangePasswordBinding;

public class Change_Password_Activity extends AppCompatActivity {
    ActivityChangePasswordBinding binding;

    Authentication_ViewModel authentication_viewModel;
    Sucess_Dialog_Interface sucess_dialog_interface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewInit();



        sucess_dialog_interface=new Sucess_Dialog_Interface() {
            @Override
            public void doneSuccessfully(String TaskName, boolean TaskDoneStatus, boolean Y_N) {
                if (TaskName.equals("ResetPassword")&&TaskDoneStatus){

                    SharedPreferenceManager.clearSharedPreferences(getApplicationContext());
                    Intent intent=new Intent(getApplicationContext(), Authentication_ViewModel.class);
                    startActivity(intent);
                    finish();

                }else {


                }


            }
        };

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.oldPassEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Old Password",getApplicationContext());
                } else if (binding.newPassEdt.getText().toString().trim().length()==0) {
                    ShowToast.ShowMsgToast("Please Enter New Password",getApplicationContext());

                } else if (!Validations.isValidPassword(binding.newPassEdt.getText().toString())) {
                    ShowToast.ShowMsgToast("Password length must be 8 and must contain  at least one digit, one uppercase letter, and one lowercase letter ", Change_Password_Activity.this);
                } else if (! binding.newPassEdt.getText().toString().trim().equals(binding.confirmPassEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Conform Password doesn't Match",getApplicationContext());
                }else if ( binding.newPassEdt.getText().toString().trim().equals(binding.oldPassEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("New password must not be same as Old Password",getApplicationContext());
                }else {
                    ChangePassword();
                }
            }
        });

        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

    private void ChangePassword() {
        binding.loadingLottie.setVisibility(View.VISIBLE);
        authentication_viewModel.ResetPassword_Callback(binding.oldPassEdt.getText().toString().trim(),binding.newPassEdt.getText().toString().trim());
        authentication_viewModel.getResetPassword().observe(Change_Password_Activity.this, new Observer<ResetPassword_RequestModel>() {
            @Override
            public void onChanged(ResetPassword_RequestModel resetPassword_requestModel) {
                if (resetPassword_requestModel!=null&&resetPassword_requestModel.getStatus()==200){
                    binding.loadingLottie.setVisibility(View.GONE);
                    SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.success_vector_icon), resetPassword_requestModel.getMessge(), "Yes,Okay","ResetPassword",true);
                    succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());
                    authentication_viewModel.getResetPassword().removeObserver(this);

                } else if (resetPassword_requestModel!=null&&resetPassword_requestModel.getStatus()==201){
                    binding.loadingLottie.setVisibility(View.GONE);
                   ShowToast.ShowMsgToast(resetPassword_requestModel.getMessge(),getApplicationContext());
                    authentication_viewModel.getResetPassword().removeObserver(this);

                } else {
                    binding.loadingLottie.setVisibility(View.GONE);
                    SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.fail_vector_icon), "Something went wrong,please try again.", "Yes,Okay","ResetPassword",false);
                    succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());
                    authentication_viewModel.getResetPassword().removeObserver(this);

                }





            }
        });







    }


    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        authentication_viewModel = viewModelProvider.get(Authentication_ViewModel.class);

    }
}