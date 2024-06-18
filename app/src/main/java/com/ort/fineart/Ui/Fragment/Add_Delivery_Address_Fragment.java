package com.ort.fineart.Ui.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ort.fineart.Model.Request_Model.UserAddress.AddAddress_RequestModel;
import com.ort.fineart.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ort.fineart.Ui.Dialogs.SuccesfullDialog_Fragment;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.Utils.Validations;
import com.ort.fineart.ViewModel.UserAddress_ViewModel;
import com.ort.fineart.databinding.FragmentAddDeliveryAddressBinding;
import com.ort.fineart.databinding.FragmentFilterViewAllProductBinding;


public class Add_Delivery_Address_Fragment extends BottomSheetDialogFragment {
    FragmentAddDeliveryAddressBinding binding;
    UserAddress_ViewModel userAddress_viewModel;

    Delivery_Address_Interface delivery_address_interface;


    public Add_Delivery_Address_Fragment() {
        // Required empty public constructor
    }

    public Add_Delivery_Address_Fragment( Delivery_Address_Interface delivery_address) {
        // Required empty public constructor
        delivery_address_interface=delivery_address;
    }
    public int getTheme() {
        return R.style.NoBackgroundDialogTheme;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddDeliveryAddressBinding.inflate(inflater, container, false);
        binding.getRoot().setBackgroundResource(R.drawable.bottom_sheet_backgroud);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);

        ViewInit();
        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivery_address_interface.addressAdded(true);
                dismiss();
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.addressTypeEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Address Type", getContext());
                }else if (binding.addressLine1Edt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Address Line 1", getContext());
                }else if (binding.addressLine2Edt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Address Line 2 ", getContext());
                }else if (binding.addressCityEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter City", getContext());
                }else if (binding.addressPinEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Pin Code", getContext());
                } else if (!Validations.validatePin(binding.addressPinEdt.getText().toString().trim())) {
                    ShowToast.ShowMsgToast("Please Enter Valid Pin Code", getContext());
                } else if (binding.addressStateEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter State", getContext());
                }else if (binding.addressCountryEdt.getText().toString().trim().length()==0){
                    ShowToast.ShowMsgToast("Please Enter Country", getContext());
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
        binding.progressBar.setVisibility(View.VISIBLE);
        userAddress_viewModel.AddAddress_Callback(addressLine1,addressLine2,country,state,city,pincode);
        userAddress_viewModel.addAddress().observe(this, new Observer<AddAddress_RequestModel>() {
            @Override
            public void onChanged(AddAddress_RequestModel addAddress_requestModel) {
                if (addAddress_requestModel!=null){


                    binding.progressBar.setVisibility(View.GONE);
                    ShowToast.ShowMsgToast("Address Added Successfully",getContext());

                    ClearAllField();



                }else {
                    ShowToast.ShowMsgToast("Failed to Add Address,Try Again...",getContext());


                    binding.progressBar.setVisibility(View.GONE);

                }
                userAddress_viewModel.addAddress().removeObserver(this);
            }
        });




    }

    private void ClearAllField() {

        binding.addressTypeEdt.setText("");
        binding.addressLine1Edt.setText("");
        binding.addressLine2Edt.setText("");
        binding.addressCityEdt.setText("");
        binding.addressPinEdt.setText("");
        binding.addressStateEdt.setText("");
        binding.addressCountryEdt.setText("");

    }

    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        userAddress_viewModel = viewModelProvider.get(UserAddress_ViewModel.class);

    }



    public interface Delivery_Address_Interface{
        void addressAdded(boolean isAdded);
    }
}