package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Response_Model.Home.HomeDealOfDay_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.SpecifeProductDetails.SpecifeProductDetails_Activity;
import com.ort.fineart.Utils.AddRemoveProductWishlist_Interface;

import java.util.ArrayList;
import java.util.List;

public class DealsOfDay_RecyclerAdapter extends RecyclerView.Adapter<DealsOfDay_RecyclerAdapter.DealsOfDayViewHolder>{
   List<HomeDealOfDay_ResponseModel>dealOfDayList=new ArrayList<>();
   Context context;
    AddRemoveProductWishlist_Interface addRemoveProductWishlist_interface;
    public DealsOfDay_RecyclerAdapter(Context context ,AddRemoveProductWishlist_Interface addRemoveProductWishlist) {
        this.context = context;
        addRemoveProductWishlist_interface=addRemoveProductWishlist;
    }

    @NonNull
    @Override
    public DealsOfDay_RecyclerAdapter.DealsOfDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_layout, parent, false);
        return new DealsOfDay_RecyclerAdapter.DealsOfDayViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull DealsOfDay_RecyclerAdapter.DealsOfDayViewHolder holder, int position) {
        HomeDealOfDay_ResponseModel homeDealOfDay_responseModel = dealOfDayList.get(position);
        String productImage=homeDealOfDay_responseModel.getProductImage()!= null ?homeDealOfDay_responseModel.getProductImage(): "";
        String productName=homeDealOfDay_responseModel.getProductName()!= null ?homeDealOfDay_responseModel.getProductName(): "";
        Object discountPrice=homeDealOfDay_responseModel.getProductDiscountPrice();
        Object productPrice=homeDealOfDay_responseModel.getProductPrice();
        Object  productDistcountPer=homeDealOfDay_responseModel.getProductDiscount();
        Object  VeriantId= homeDealOfDay_responseModel.getProduct_VarientId();
        String ProductDiscount= String.valueOf(homeDealOfDay_responseModel.getProductDiscount()) ;
        String ProductDiscountMode= String.valueOf(homeDealOfDay_responseModel.getProductDiscountMode());
        String AverageRating= String.valueOf(homeDealOfDay_responseModel.getAverageRating());

        String productVariantId = String.valueOf((int) ((Double) VeriantId).doubleValue());


        if (productPrice.toString().length()!=0){
            holder.SetData(productImage,productName,discountPrice,productPrice,productDistcountPer,ProductDiscount,ProductDiscountMode,AverageRating);

        }





        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SpecifeProductDetails_Activity.class);
                intent.putExtra("Product_VarientId",productVariantId);
                context.startActivity(intent);
            }
        });

        holder.addWish_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addRemoveProductWishlist_interface.add_Or_remove_productWish(Integer.valueOf(productVariantId));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (dealOfDayList != null) {
            return dealOfDayList.size();
        } else {
            return 0;
        }
    }


    public void setDataList(List<HomeDealOfDay_ResponseModel> DataList) {


        if (DataList != null) {
            dealOfDayList.clear();
            dealOfDayList = DataList;
            notifyDataSetChanged();
        } else {
            dealOfDayList = null;
            notifyDataSetChanged();
        }


    }




    public class DealsOfDayViewHolder extends RecyclerView.ViewHolder {
        CardView cardLayout;
        ShapeableImageView product_img;
        TextView productName_txt,productDiscountPrice_txt,productPrice_txt,discount_value_txt,product_rating_txt;
        ImageView addWish_Btn;
        public DealsOfDayViewHolder(@NonNull View itemView) {
            super(itemView);
            cardLayout=itemView.findViewById(R.id.cardLayout);
            product_img=itemView.findViewById(R.id.product_img);
            productName_txt=itemView.findViewById(R.id.productName_txt);
            productDiscountPrice_txt=itemView.findViewById(R.id.productDiscountPrice_txt);
            productPrice_txt=itemView.findViewById(R.id.productPrice_txt);
            addWish_Btn=itemView.findViewById(R.id.addWish_Btn);
            discount_value_txt=itemView.findViewById(R.id.discount_value_txt);
            product_rating_txt=itemView.findViewById(R.id.product_rating_txt);

        }

        public void SetData(String productImage,String productName,Object discountPrice,Object productPrice,Object productDistcountPer,String productDiscount,String productDiscountMode,String averageRating){

            LoadImage(productImage,product_img);
            productName_txt.setText(productName);
            productDiscountPrice_txt.setText(discountPrice.toString());
            productPrice_txt.setText(productPrice.toString());
            discount_value_txt.setText(productDiscount+""+productDiscountMode);
            Log.e("averageRating",averageRating+"");
            if (averageRating==null||averageRating.toLowerCase().equals("null")){
                product_rating_txt.setText("0.0");
            }else {
                product_rating_txt.setText(averageRating);
            }


        }
        public void LoadImage(String ImageUrl,ShapeableImageView imageView) {




            if (ImageUrl != null || ImageUrl.length() != 0) {

                Glide.with(context)
                        .load(All_URL.ImageBase_URL+ImageUrl)
                        //.placeholder(R.drawable.custom_image_placeholder) // Optional: Placeholder image until the actual image is loaded
                        // .error(R.drawable.custom_image_placeholder) // Optional: Error image if the image loading fails
                        .apply(RequestOptions.centerCropTransform()).into(imageView);

            }
        }

    }
}
