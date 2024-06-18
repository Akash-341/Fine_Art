package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ort.fineart.Model.Response_Model.Checkout.CouponList_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeDealOfDay_ResponseModel;
import com.ort.fineart.R;

import java.util.ArrayList;
import java.util.List;

public class CouponList_RecyclerAdapter extends RecyclerView.Adapter<CouponList_RecyclerAdapter.CouponList_ViewHolder>{
    List<CouponList_ResponseModel> couponList=new ArrayList<>();
    Context context;
    ApplyCoupon_Interface applyCoupon_interface;


    public CouponList_RecyclerAdapter(Context context, ApplyCoupon_Interface applyCoupon) {
        this.context = context;
        applyCoupon_interface=applyCoupon;
    }

    @NonNull
    @Override
    public CouponList_RecyclerAdapter.CouponList_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupons_card_view_layout, parent, false);
        return new CouponList_RecyclerAdapter.CouponList_ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CouponList_RecyclerAdapter.CouponList_ViewHolder holder, int position) {
        CouponList_ResponseModel couponList_responseModel = couponList.get(position);
        String couponCode=couponList_responseModel.getCouponCode()!= null ?couponList_responseModel.getCouponCode(): "";
        String couponExpireDate=couponList_responseModel.getExpiryDate()!= null ?couponList_responseModel.getExpiryDate(): "";
        String couponDesc=couponList_responseModel.getCouponDescription()!= null ?couponList_responseModel.getCouponDescription(): "";

        holder.SetData(couponCode,couponDesc,couponExpireDate);




        holder.apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyCoupon_interface.applyCoupon(couponList_responseModel);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (couponList != null) {
            return couponList.size();
        } else {
            return 0;
        }
    }


    public void setDataList(List<CouponList_ResponseModel> DataList) {


        if (DataList != null) {
            couponList.clear();
            couponList = DataList;
            notifyDataSetChanged();
        } else {
            couponList = null;
            notifyDataSetChanged();
        }


    }

    public class CouponList_ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout apply_btn;
        TextView couponCode_txt,expireDate_txt,couponDescp_txt;



        public CouponList_ViewHolder(@NonNull View itemView) {
            super(itemView);
            apply_btn=itemView.findViewById(R.id.apply_btn);
            couponCode_txt=itemView.findViewById(R.id.couponCode_txt);
            expireDate_txt=itemView.findViewById(R.id.expireDate_txt);
            couponDescp_txt=itemView.findViewById(R.id.couponDescp_txt);

        }


        void SetData(String CouponCode,String CouponDesc,String ExpiryDate){
            couponCode_txt.setText(CouponCode);
            couponDescp_txt.setText(CouponDesc);
            expireDate_txt.setText(ExpiryDate);
        }
    }





    public interface ApplyCoupon_Interface{
        void applyCoupon(CouponList_ResponseModel couponList_responseModel);
    }
}
