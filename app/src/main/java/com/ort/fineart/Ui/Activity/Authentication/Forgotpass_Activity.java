package com.ort.fineart.Ui.Activity.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ort.fineart.Model.Request_Model.Authentication.ForgotPassword_RequestModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Dialogs.SuccesfullDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Sucess_Dialog_Interface;
import com.ort.fineart.Utils.ShowDialog;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.Utils.Validations;
import com.ort.fineart.ViewModel.Authentication_ViewModel;
import com.ort.fineart.databinding.ActivityForgotpassBinding;
import com.ort.fineart.databinding.ActivityLoginBinding;

public class Forgotpass_Activity extends AppCompatActivity {
    ActivityForgotpassBinding binding;
    Authentication_ViewModel authentication_viewModel;
    Sucess_Dialog_Interface sucess_dialog_interface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotpassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewInit();
        ChangeStatusBar();


        sucess_dialog_interface=new Sucess_Dialog_Interface() {
            @Override
            public void doneSuccessfully(String TaskName, boolean TaskDoneStatus, boolean Y_N) {
                if (TaskName.equals("SendMail")&&TaskDoneStatus){
                    finish();

                }else {


                }


            }
        };


        binding.CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.emailEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please Enter Email", getApplicationContext());
                } else if (!Validations.isValidEmail(binding.emailEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Please Enter Valid Email", getApplicationContext());
                } else {
                    ForgotPassword();
                }
            }
        });


    }

    private void ForgotPassword() {


        binding.loadingLottie.setVisibility(View.VISIBLE);
        authentication_viewModel.ForgotPassword_Callback(binding.emailEdt.getText().toString().trim());
        authentication_viewModel.getForgotPassword().observe(Forgotpass_Activity.this, new Observer<ForgotPassword_RequestModel>() {
            @Override
            public void onChanged(ForgotPassword_RequestModel forgotPassword_requestModel) {

                if (forgotPassword_requestModel!=null){
                    if (forgotPassword_requestModel.getStatus()==200){
                        binding.loadingLottie.setVisibility(View.GONE);
                        SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.success_vector_icon), forgotPassword_requestModel.getMessge(), "Yes,Okay","SendMail",true);
                        succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());
                        authentication_viewModel.getForgotPassword().removeObserver(this);

                    } else if (forgotPassword_requestModel.getStatus()==201) {
                        binding.loadingLottie.setVisibility(View.GONE);
                        SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.fail_vector_icon), forgotPassword_requestModel.getMessge(), "Yes,Okay","SendMail",false);
                        succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());
                        authentication_viewModel.getForgotPassword().removeObserver(this);

                    }

                }else {
                    binding.loadingLottie.setVisibility(View.GONE);
                    SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.fail_vector_icon), "Something Went Wrong,Please Try Again.", "Yes,Okay","SendMail",false);
                    succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());
                    authentication_viewModel.getForgotPassword().removeObserver(this);
                }




            }
        });













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


    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        authentication_viewModel = viewModelProvider.get(Authentication_ViewModel.class);

    }
}