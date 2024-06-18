package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ort.fineart.Model.Response_Model.Home.HomeDealOfDay_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.SpecifeProductDetails.GetProductByCSSProperty_Interface;

import java.util.ArrayList;
import java.util.List;

public class SizeList_RecyclerAdapter extends RecyclerView.Adapter<SizeList_RecyclerAdapter.SizeList_ViewHolder>{
    ArrayList<String> SizeList=new ArrayList<>();
    GetProductByCSSProperty_Interface getProductByCSSProperty_interface;
    Context context;

    public SizeList_RecyclerAdapter(Context context,GetProductByCSSProperty_Interface getProductByCSS) {
        this.context = context;
        getProductByCSSProperty_interface=getProductByCSS;
    }

    @NonNull
    @Override
    public SizeList_RecyclerAdapter.SizeList_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.size_list_cardview_layout, parent, false);
        return new SizeList_RecyclerAdapter.SizeList_ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull SizeList_RecyclerAdapter.SizeList_ViewHolder holder, int position) {


        holder.sizeTxt.setText(SizeList.get(position));
        holder.sizeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductByCSSProperty_interface.getProductBySize(SizeList.get(position).trim());
            }
        });




    }

    @Override
    public int getItemCount() {
        if (SizeList != null) {
            return SizeList.size();
        } else {
            return 0;
        }
    }


    public void setDataList(ArrayList<String> DataList) {


        if (DataList != null) {
            SizeList.clear();
            SizeList = DataList;
            notifyDataSetChanged();
        } else {
            SizeList = null;
            notifyDataSetChanged();
        }


    }








    public class SizeList_ViewHolder extends RecyclerView.ViewHolder {

        TextView sizeTxt;
        LinearLayout sizeCard;


        public SizeList_ViewHolder(@NonNull View itemView) {
            super(itemView);
            sizeTxt=itemView.findViewById(R.id.sizeTxt);
            sizeCard=itemView.findViewById(R.id.sizeCard);

        }
    }
}
