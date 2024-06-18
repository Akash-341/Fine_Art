package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
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

public class ColorList_RecyclerAdapter extends RecyclerView.Adapter<ColorList_RecyclerAdapter.ColorList_ViewHolder>{
    ArrayList<String> ColorList=new ArrayList<>();
    ArrayList<String> ColorHexList=new ArrayList<>();
    Context context;
    GetProductByCSSProperty_Interface getProductByCSSProperty_interface;
    String SelectedColor="Not";

    public ColorList_RecyclerAdapter(Context context,GetProductByCSSProperty_Interface getProductByCSS) {
        this.context = context;
        getProductByCSSProperty_interface=getProductByCSS;
    }

    @NonNull
    @Override
    public ColorList_RecyclerAdapter.ColorList_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_list_card_layout, parent, false);
        return new ColorList_RecyclerAdapter.ColorList_ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull ColorList_RecyclerAdapter.ColorList_ViewHolder holder, int position) {

        int color = Color.parseColor(ColorHexList.get(position));
        holder.color_lay.setBackgroundTintList(ColorStateList.valueOf(color));


        if (ColorList.get(position).trim().equals(SelectedColor)){
            holder.colorCard.setBackground(context.getDrawable(R.drawable.product_color_selected_background));
        }else {
            holder.colorCard.setBackground(null);

        }

        holder.colorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductByCSSProperty_interface.getProductByColor(ColorList.get(position).trim());
            }
        });



    }

    @Override
    public int getItemCount() {
        if (ColorList != null) {
            return ColorList.size();
        } else {
            return 0;
        }
    }


    public void setDataList(ArrayList<String> ColorDataList,ArrayList<String>ColorHexDataList,String selectedColor) {



        if (ColorDataList != null) {
            ColorList.clear();
            ColorHexList.clear();
             ColorList= ColorDataList;
             ColorHexList=ColorHexDataList;
             SelectedColor=selectedColor;
            Log.e("SpecifeProductDetails",ColorDataList.size()+":Color List ");

            notifyDataSetChanged();
        } else {
            ColorList=null;
            ColorHexList=null;
            SelectedColor="Not";
            Log.e("SpecifeProductDetails",":Color List  No");

            notifyDataSetChanged();
        }


    }

    public class ColorList_ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout colorCard,color_lay;


        public ColorList_ViewHolder(@NonNull View itemView) {
            super(itemView);
            colorCard=itemView.findViewById(R.id.colorCard);
            color_lay=itemView.findViewById(R.id.color_lay);


        }
    }
}
