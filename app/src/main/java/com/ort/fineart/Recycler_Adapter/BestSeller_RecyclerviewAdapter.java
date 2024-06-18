package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Response_Model.Home.HomeBestSeller_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.SpecifeProductDetails.SpecifeProductDetails_Activity;
import com.ort.fineart.Utils.AddRemoveProductWishlist_Interface;

import java.util.ArrayList;
import java.util.List;

public class BestSeller_RecyclerviewAdapter extends RecyclerView.Adapter<BestSeller_RecyclerviewAdapter.BestSellerViewHolder>{
    List<HomeBestSeller_ResponseModel>bestSeller_List=new ArrayList<>();
    Context context;
    AddRemoveProductWishlist_Interface addRemoveProductWishlist_interface;
    public BestSeller_RecyclerviewAdapter(Context context,AddRemoveProductWishlist_Interface addRemoveProductWishlist) {
        this.context = context;
        addRemoveProductWishlist_interface=addRemoveProductWishlist;
    }

    @NonNull
    @Override
    public BestSeller_RecyclerviewAdapter.BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_layout, parent, false);
        return new BestSeller_RecyclerviewAdapter.BestSellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSeller_RecyclerviewAdapter.BestSellerViewHolder holder, int position) {
        HomeBestSeller_ResponseModel bestSeller_responseModel = bestSeller_List.get(position);
        String productImage=bestSeller_responseModel.getProductImage()!= null ?bestSeller_responseModel.getProductImage(): "";
        String productName=bestSeller_responseModel.getProductName()!= null ?bestSeller_responseModel.getProductName(): "";
        Object discountPrice=bestSeller_responseModel.getProductDiscountPrice();
        Object productPrice=bestSeller_responseModel.getProductPrice();
        Object  productDistcountPer=bestSeller_responseModel.getProductDiscount().toString();
        Object  VeriantId= bestSeller_responseModel.getProduct_VarientId();
        String ProductDiscount= String.valueOf(bestSeller_responseModel.getProductDiscount()) ;
        String ProductDiscountMode= String.valueOf(bestSeller_responseModel.getProductDiscountMode());
        String AverageRating= String.valueOf(bestSeller_responseModel.getAverageRating());
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
        if (bestSeller_List != null) {
            return bestSeller_List.size();
        } else {
            return 0;
        }
    }



    public void setDataList(List<HomeBestSeller_ResponseModel> DataList) {

        if (DataList != null) {
            bestSeller_List.clear();
            bestSeller_List = DataList;
            notifyDataSetChanged();
        } else {
            bestSeller_List = null;
            notifyDataSetChanged();
        }


    }


    public class BestSellerViewHolder extends RecyclerView.ViewHolder {
        CardView cardLayout;
        ShapeableImageView product_img;
        TextView productName_txt,productDiscountPrice_txt,productPrice_txt,discount_value_txt,product_rating_txt;
        ImageView addWish_Btn;
        public BestSellerViewHolder(@NonNull View itemView) {
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

            if (productImage!=null&&productImage.length()!=0){
                LoadImage(productImage,product_img);
            }

            productName_txt.setText(productName);
            productDiscountPrice_txt.setText(discountPrice.toString());
            productPrice_txt.setText(productPrice.toString());

            discount_value_txt.setText(productDiscount+""+productDiscountMode);
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



