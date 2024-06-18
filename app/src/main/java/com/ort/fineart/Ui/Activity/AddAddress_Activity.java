package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.ort.fineart.Model.Request_Model.UserAddress.AddAddress_RequestModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Dialogs.ConformationDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Conformation_Dialog_Interface;
import com.ort.fineart.Ui.Dialogs.SuccesfullDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Sucess_Dialog_Interface;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.Utils.Validations;
import com.ort.fineart.ViewModel.UserAddress_ViewModel;
import com.ort.fineart.databinding.ActivityAddAddressBinding;
import com.ort.fineart.databinding.ActivityManageAddresesBinding;

public class AddAddress_Activity extends AppCompatActivity {
    ActivityAddAddressBinding binding;
    UserAddress_ViewModel userAddress_viewModel;

    Sucess_Dialog_Interface sucess_dialog_interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewInit();


       sucess_dialog_interface=new Sucess_Dialog_Interface() {
           @Override
           public void doneSuccessfully(String TaskName, boolean TaskDoneStatus, boolean Y_N) {
            if (Y_N==true&&TaskDoneStatus){
               binding.addressTypeEdt.setText("");
                binding.addressLine1Edt.setText("");
                binding.addressLine2Edt.setText("");
                binding.addressCityEdt.setText("");
                binding.addressPinEdt.setText("");
                binding.addressStateEdt.setText("");
                binding.addressCountryEdt.setText("");
            }

           }
       };


        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.addressTypeEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Address Type", getApplicationContext());
                }else if (binding.addressLine1Edt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Address Line 1", getApplicationContext());
                }else if (binding.addressLine2Edt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Address Line 2 ", getApplicationContext());
                }else if (binding.addressCityEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter City", getApplicationContext());
                }else if (binding.addressPinEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Pin Code", getApplicationContext());
                }else if (!Validations.validatePin(binding.addressPinEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Please Enter Valid Pin Code", getApplicationContext());
                }else if (binding.addressStateEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter State", getApplicationContext());
                }else if (binding.addressCountryEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Country", getApplicationContext());
                }else {
                    String addressType=binding.addressTypeEdt.getText().toString();
                    String addressLine1=binding.addressLine1Edt.getText().toString();
                    String addressLine2=binding.addressLine2Edt.getText().toString();
                    String city=binding.addressCityEdt.getText().toString();
                    String pincode=binding.addressPinEdt.getText().toString();
                    String state=binding.addressStateEdt.getText().toString();
                    String country=binding.addressCountryEdt.getText().toString();
                    AddAddress(addressType,addressLine1,addressLine2,city,pincode,state,country);

                }
            }
        });

    }

    private void AddAddress(String addressType, String addressLine1, String addressLine2, String city, String pincode, String state, String country) {

        binding.loadingLottie.setVisibility(View.VISIBLE);
     userAddress_viewModel.AddAddress_Callback(addressLine1,addressLine2,country,state,city,pincode);
     userAddress_viewModel.addAddress().observe(this, new Observer<AddAddress_RequestModel>() {
         @Override
         public void onChanged(AddAddress_RequestModel addAddress_requestModel) {
             if (addAddress_requestModel!=null){
                 binding.loadingLottie.setVisibility(View.GONE);
                 SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.success_vector_icon), "New Address Added Successfully", "Yes,Okay","Add_Address",true);
                 succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());




             }else {
                 binding.loadingLottie.setVisibility(View.GONE);
                 SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.fail_vector_icon), "Something Went Wrong,Please Try Again.", "Yes,Okay","Add_Address",false);
                 succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());


             }
             userAddress_viewModel.addAddress().removeObserver(this);
         }
     });

    }

    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        userAddress_viewModel = viewModelProvider.get(UserAddress_ViewModel.class);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}