package com.ort.fineart.Ui.Activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ort.fineart.Model.Request_Model.UserAddress.DeleteAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.UpdateAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.UpdateDeleteAddress_Interface;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.AddressList_RecyclerviewAdapter;
import com.ort.fineart.Recycler_Adapter.BestSeller_RecyclerviewAdapter;
import com.ort.fineart.Ui.Dialogs.ConformationDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Conformation_Dialog_Interface;
import com.ort.fineart.Ui.Dialogs.SuccesfullDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Sucess_Dialog_Interface;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.HorizontalSpaceItemDecoration;
import com.ort.fineart.Utils.VerticalSpaceItemDecoration;
import com.ort.fineart.ViewModel.HomeScreen_ViewModel;
import com.ort.fineart.ViewModel.UserAddress_ViewModel;
import com.ort.fineart.databinding.ActivityManageAddresesBinding;

import java.util.List;

public class Manage_Addreses_Activity extends AppCompatActivity {
    ActivityManageAddresesBinding binding;
    AddressList_RecyclerviewAdapter addressList_recyclerviewAdapter;
    UpdateDeleteAddress_Interface updateDeleteAddress_interface;
    UserAddress_ViewModel userAddress_viewModel;





    Conformation_Dialog_Interface conformation_dialog_interface;
    Sucess_Dialog_Interface sucess_dialog_interface;

    int RecyclerViewCardMargin=16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityManageAddresesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getApplicationContext(), RecyclerViewCardMargin);
        ViewInit();



        conformation_dialog_interface=new Conformation_Dialog_Interface() {
            @Override
            public void GetConformation(String TaskName, boolean Y_N, Object... arguments) {
                if (TaskName.trim().equals("Delete_Address") && Y_N) {
                    int Address_Id = 0;
                    for (Object arg : arguments) {
                        Address_Id = Integer.parseInt(arg.toString());
                    }

                    DeleteAddress(Address_Id);
                }
            }
        };



        sucess_dialog_interface=new Sucess_Dialog_Interface() {
            @Override
            public void doneSuccessfully(String TaskName, boolean TaskDoneStatus, boolean Y_N) {
                if (TaskName.trim().equals("Delete_Address") && Y_N &&TaskDoneStatus) {
                   // SetRecyclerViewData();
                    GetAddressList();
                }
            }
        };








        updateDeleteAddress_interface=new UpdateDeleteAddress_Interface() {
            @Override
            public void deleteAddress(int AddressId) {
                ConformationDialog_Fragment confomation_dialog = new ConformationDialog_Fragment(conformation_dialog_interface, getDrawable(R.drawable.address_vector_icon), "Confirm address delete?", "It will permanently delete address from Address List.", "Yes, Delete", "Delete_Address", AddressId);
                confomation_dialog.show(getSupportFragmentManager(), confomation_dialog.getTag());




            }

            @Override
            public void updateAddress(int addressId, String AddressLine1, String AddressLine2, String Country, String State, String City, int Pincode) {


                Log.e("UpdateAddress_List",addressId+":1");
                Intent intent=new Intent(getApplicationContext(), Update_Address_Activity.class);
                intent.putExtra("AddressId",addressId);
                startActivity(intent);




            }
        };


        addressList_recyclerviewAdapter = new AddressList_RecyclerviewAdapter(getApplicationContext(),updateDeleteAddress_interface);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.addressRecyclerView.setLayoutManager(layoutManager);
        binding.addressRecyclerView.setAdapter(addressList_recyclerviewAdapter);
        binding.addressRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));



        SetAddressListData();



        binding.fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AddAddress_Activity.class);
                startActivity(intent);
            }
        });


        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void DeleteAddress(int addressId) {
        userAddress_viewModel.DeleteAddress_Callback(addressId);
        userAddress_viewModel.deleteAddress().observe(this, new Observer<DeleteAddress_RequestModel>() {
            @Override
            public void onChanged(DeleteAddress_RequestModel deleteAddress_requestModel) {

                if(deleteAddress_requestModel!=null){

                    SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.success_vector_icon), "Address Deleted Successfully", "Yes,Okay","Delete_Address",true);
                    succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());

                }else {
                    SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.fail_vector_icon), "Something Went Wrong,Please Try Again.", "Yes,Okay","Delete_Address",false);
                    succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());

                }
                userAddress_viewModel.deleteAddress().removeObserver(this);
            }
        });



    }

    private void SetAddressListData() {


        userAddress_viewModel.getAddressList().observe(this, new Observer<List<GetUserAddress_ResponseModel>>() {
            @Override
            public void onChanged(List<GetUserAddress_ResponseModel> getUserAddress_responseModels) {
                if (getUserAddress_responseModels!=null&&getUserAddress_responseModels.size()!=0){
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.GONE);
                    binding.addressRecyclerView.setVisibility(View.VISIBLE);

                    addressList_recyclerviewAdapter.setDataList(getUserAddress_responseModels);
                }else {
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.VISIBLE);
                    binding.addressRecyclerView.setVisibility(View.GONE);

                }
            }
        });



    }

    private void GetAddressList(){
        userAddress_viewModel.GetAddressList_Callback();
        binding.loadingLottie.setVisibility(View.VISIBLE);
    }

    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        userAddress_viewModel = viewModelProvider.get(UserAddress_ViewModel.class);

    }


    @Override
    protected void onResume() {
        super.onResume();
        GetAddressList();
    }
}