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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ort.fineart.Model.Request_Model.UserAddress.UpdateDeleteAddress_Interface;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.databinding.AddressCardviewLayoutBinding;
import com.ort.fineart.databinding.DeliveryAddressCardviewLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressList_RecyclerviewAdapter extends RecyclerView.Adapter<DeliveryAddressList_RecyclerviewAdapter.DeliveryAddressListViewHolder> {
    Context context;


    UpdateDeleteAddress_Interface updateDeleteAddress_interface;
    List<GetUserAddress_ResponseModel> addressList=new ArrayList<>();

    private int selectedPosition = RecyclerView.NO_POSITION;


    public DeliveryAddressList_RecyclerviewAdapter(Context context, UpdateDeleteAddress_Interface updateDeleteAddress_interface) {
        this.context = context;
        this.updateDeleteAddress_interface = updateDeleteAddress_interface;
    }

    @NonNull
    @Override
    public DeliveryAddressList_RecyclerviewAdapter.DeliveryAddressListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_address_cardview_layout, parent, false);
        return new DeliveryAddressList_RecyclerviewAdapter.DeliveryAddressListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryAddressListViewHolder holder, int position) {
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






        if (position == selectedPosition) {
            int color = ContextCompat.getColor(context, R.color.green_color_light); // Use your color resource
           holder.delivery_card.setCardBackgroundColor(color);
           holder.moreOption.setVisibility(View.VISIBLE);

            int whiteColor = ContextCompat.getColor(context, R.color.green); // Use your text color resource


        } else {
            int color = ContextCompat.getColor(context, R.color.white); // Use your color resource
            holder.delivery_card.setCardBackgroundColor(color);
            holder.moreOption.setVisibility(View.GONE);
            int backColor = ContextCompat.getColor(context, R.color.black); // Use your text color resource

        }








        holder.delivery_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelectedPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                String userAddress=AddressLine1+" "+AddressLine2+" ,"+City+" ,"+State+" ,"+Country+" -"+Pincode;

                updateDeleteAddress_interface.updateAddress(Address_id,AddressLine1,AddressLine2 ,Country ,State ,City, Pincode);







                notifyItemChanged(previousSelectedPosition);
                notifyItemChanged(selectedPosition);
            }
        });









        holder.moreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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
    public class DeliveryAddressListViewHolder extends RecyclerView.ViewHolder {
        ImageView moreOption;
        TextView address_txt,addressType_txt;
        RelativeLayout location_icon;
        CardView delivery_card;


        public DeliveryAddressListViewHolder(@NonNull View itemView) {
            super(itemView);
            moreOption=itemView.findViewById(R.id.more_icon);
            address_txt=itemView.findViewById(R.id.address_txt);
            addressType_txt=itemView.findViewById(R.id.addressType_txt);
            location_icon=itemView.findViewById(R.id.location_icon);
            delivery_card=itemView.findViewById(R.id.delivery_card);
        }


        public void SetData(String addressType, String addressLine1, String addressLine2, String country, String state, String city, int pincode) {
            addressType_txt.setText(addressType);
            String userAddress=addressLine1+" "+addressLine2+" ,"+city+" ,"+state+" ,"+country+" -"+pincode;
            address_txt.setText(userAddress);

        }
    }
}
