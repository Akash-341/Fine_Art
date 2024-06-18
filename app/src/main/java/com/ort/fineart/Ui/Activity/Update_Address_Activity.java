package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ort.fineart.Model.Request_Model.UserAddress.AddAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.DeleteAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.UpdateAddress_RequestModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetAddressById_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Dialogs.ConformationDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Conformation_Dialog_Interface;
import com.ort.fineart.Ui.Dialogs.SuccesfullDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Sucess_Dialog_Interface;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.Utils.Validations;
import com.ort.fineart.ViewModel.UserAddress_ViewModel;
import com.ort.fineart.databinding.ActivityAddAddressBinding;
import com.ort.fineart.databinding.ActivityUpdateAddressBinding;

public class Update_Address_Activity extends AppCompatActivity {
    ActivityUpdateAddressBinding binding;
    UserAddress_ViewModel userAddress_viewModel;
    int Address_id;


    Conformation_Dialog_Interface conformation_dialog_interface;
    Sucess_Dialog_Interface sucess_dialog_interface;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent=getIntent();
        Address_id=intent.getIntExtra("AddressId",0);
        ViewInit();
        GetAddress(Address_id);
        conformation_dialog_interface=new Conformation_Dialog_Interface() {
            @Override
            public void GetConformation(String TaskName, boolean Y_N, Object... arguments) {
                if (TaskName.trim().equals("Update_Address") && Y_N) {
                    UpdateAddress(Address_id);
                }
            }
        };



        sucess_dialog_interface=new Sucess_Dialog_Interface() {
            @Override
            public void doneSuccessfully(String TaskName, boolean TaskDoneStatus, boolean Y_N) {
                if (TaskName.trim().equals("Update_Address") && Y_N &&TaskDoneStatus) {
                    Intent intent1=new Intent(getApplicationContext(), Manage_Addreses_Activity.class);
                    startActivity(intent1);
                    finish();
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

                    ConformationDialog_Fragment confomation_dialog = new ConformationDialog_Fragment(conformation_dialog_interface, getDrawable(R.drawable.address_vector_icon), "Confirm address update?", "It will permanently update address in Address List.", "Yes, Update", "Update_Address", Address_id);
                    confomation_dialog.show(getSupportFragmentManager(), confomation_dialog.getTag());

                }
            }
        });


    }



    private void UpdateAddress(int AddressId) {
        binding.loadingLottie.setVisibility(View.VISIBLE);
        String addressType=binding.addressTypeEdt.getText().toString();
        String addressLine1=binding.addressLine1Edt.getText().toString();
        String addressLine2=binding.addressLine2Edt.getText().toString();
        String city=binding.addressCityEdt.getText().toString();
        String pincode=binding.addressPinEdt.getText().toString();
        String state=binding.addressStateEdt.getText().toString();
        String country=binding.addressCountryEdt.getText().toString();

        userAddress_viewModel.UpdateAddress_Callback(AddressId,addressLine1,addressLine2,country,state,city, Integer.parseInt(pincode));
         userAddress_viewModel.updateAddress().observe(this, new Observer<UpdateAddress_RequestModel>() {
             @Override
             public void onChanged(UpdateAddress_RequestModel updateAddress_requestModel) {

                 if(updateAddress_requestModel!=null){
                     binding.loadingLottie.setVisibility(View.GONE);
                     SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.success_vector_icon), "Address Updated Successfully", "Yes,Okay","Update_Address",true);
                     succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());

                 }else {
                     binding.loadingLottie.setVisibility(View.GONE);
                     SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.fail_vector_icon), "Something Went Wrong,Please Try Again.", "Yes,Okay","Update_Address",false);
                     succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());

                 }
                 userAddress_viewModel.updateAddress().removeObserver(this);
             }
         });



    }
    
    
    private void GetAddress(int AddressId){

        userAddress_viewModel.GetAddressById_Callback(AddressId);

        userAddress_viewModel.getAddressById().observe(Update_Address_Activity.this, new Observer<GetAddressById_ResponseModel>() {
            @Override
            public void onChanged(GetAddressById_ResponseModel getAddressById_responseModel) {
                if (getAddressById_responseModel!=null){
                    String AddressLine1=getAddressById_responseModel.getAddressLine1()!=null ? getAddressById_responseModel.getAddressLine1() : "";
                    String AddressLine2=getAddressById_responseModel.getAddressLine2()!=null ? getAddressById_responseModel.getAddressLine2() : "";;
                    String Country=getAddressById_responseModel.getCountry()!=null ? getAddressById_responseModel.getCountry() : "";;
                    String State=getAddressById_responseModel.getState()!=null ? getAddressById_responseModel.getState() : "";;
                    String City=getAddressById_responseModel.getCity()!=null ? getAddressById_responseModel.getCity() : "";;
                    int Pincode=getAddressById_responseModel.getPincode();

                   binding.addressTypeEdt.setText("Home");
                   binding.addressLine1Edt.setText(AddressLine1);
                   binding.addressLine2Edt.setText(AddressLine2);
                   binding.addressCityEdt.setText(City);
                   binding.addressPinEdt.setText(Pincode+"");
                   binding.addressStateEdt.setText(State);
                   binding.addressCountryEdt.setText(Country);




                }
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