package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ort.fineart.Model.Request_Model.UserAddress.UpdateDeleteAddress_Interface;
import com.ort.fineart.Model.Response_Model.Home.HomeDealOfDay_ResponseModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;
import com.ort.fineart.Model.Response_Model.WishList.GetWishList_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.databinding.AddressCardviewLayoutBinding;
import com.ort.fineart.databinding.WishlistCardLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class AddressList_RecyclerviewAdapter extends RecyclerView.Adapter<AddressList_RecyclerviewAdapter.AddressListViewHolder> {
    Context context;
    AddressCardviewLayoutBinding binding;
    UpdateDeleteAddress_Interface updateDeleteAddress_interface;
    List<GetUserAddress_ResponseModel>addressList=new ArrayList<>();

    public AddressList_RecyclerviewAdapter(Context context, UpdateDeleteAddress_Interface updateDeleteAddress_interface) {
        this.context = context;
        this.updateDeleteAddress_interface = updateDeleteAddress_interface;
    }

    @NonNull
    @Override
    public AddressList_RecyclerviewAdapter.AddressListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AddressCardviewLayoutBinding binding = AddressCardviewLayoutBinding.inflate(layoutInflater, parent, false);
        return new AddressList_RecyclerviewAdapter.AddressListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressList_RecyclerviewAdapter.AddressListViewHolder holder, int position) {
        GetUserAddress_ResponseModel address_responseModel = addressList.get(position);
         int Address_id=address_responseModel.getId();
         String Customer="";
         String AddressType="Home";
         String AddressLine1=address_responseModel.getAddressLine1()!=null ? address_responseModel.getAddressLine1() : "";
         String AddressLine2=address_responseModel.getAddressLine2()!=null ? address_responseModel.getAddressLine2() : "";;
         String Country=address_responseModel.getCountry()!=null ? address_responseModel.getCountry() : "";;
         String State=address_responseModel.getState()!=null ? address_responseModel.getState() : "";;
         String City=address_responseModel.getCity()!=null ? address_responseModel.getCity() : "";;
         int Pincode=address_responseModel.getPincode();

         holder.SetData(AddressType ,AddressLine1,AddressLine2,Country,State,City,Pincode);



        binding.moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup;
                popup = new PopupMenu(context,((AddressList_RecyclerviewAdapter.AddressListViewHolder) holder).moreOption);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                    popup = new PopupMenu(context,((AddressList_RecyclerviewAdapter.AddressListViewHolder) holder).moreOption, Gravity.END, 0, R.style.RoundPopUpMenu);
                } else {
                    popup = new PopupMenu(context, ((AddressList_RecyclerviewAdapter.AddressListViewHolder) holder).moreOption);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popup.setForceShowIcon(true);
                }
                popup.inflate(R.menu.address_menu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.delete_address) {
                            updateDeleteAddress_interface.deleteAddress(Address_id);
                            return true;
                        } else if (item.getItemId()==R.id.update_address) {
                            updateDeleteAddress_interface.updateAddress(Address_id,AddressLine1,AddressLine2 ,Country ,State ,City,Pincode);
                        }
                        return false;
                    }

                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (addressList != null) {
            return addressList.size();
        } else {
            return 0;
        }
    }
    public void setDataList(List<GetUserAddress_ResponseModel> DataList) {

        if (DataList != null) {
            addressList.clear();
            addressList = DataList;
            notifyDataSetChanged();
        } else {
            addressList = null;
            notifyDataSetChanged();
        }


    }
    public class AddressListViewHolder extends RecyclerView.ViewHolder {
        ImageView moreOption;
        public AddressListViewHolder(@NonNull AddressCardviewLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            moreOption=binding.moreIcon;
        }


        public void SetData(String addressType, String addressLine1, String addressLine2, String country, String state, String city, int pincode) {
         binding.addressTypeTxt.setText(addressType);
         String userAddress=addressLine1+" "+addressLine2+" ,"+city+" ,"+state+" ,"+country+" -"+pincode;
         binding.addressTxt.setText(userAddress);

        }
    }
}
