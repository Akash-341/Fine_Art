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
import com.ort.fineart.Model.Response_Model.Hub.GetHubProductListByCategory_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.SpecifeProductDetails.SpecifeProductDetails_Activity;
import com.ort.fineart.Ui.Fragment.Category_Data;
import com.ort.fineart.Utils.AddRemoveProductWishlist_Interface;

import java.util.ArrayList;
import java.util.List;

public class RelatedProduct_RecyclerAdapter extends RecyclerView.Adapter<RelatedProduct_RecyclerAdapter.RelatedProduct_ViewHolder>{
    List<GetHubProductListByCategory_ResponseModel> hubData_List=new ArrayList<>();
    Context context;
    AddRemoveProductWishlist_Interface addRemoveProductWishlist_interface;
    public RelatedProduct_RecyclerAdapter(Context context,AddRemoveProductWishlist_Interface addRemoveProductWishlist) {
        this.context = context;
        addRemoveProductWishlist_interface=addRemoveProductWishlist;
    }

    @NonNull
    @Override
    public RelatedProduct_RecyclerAdapter.RelatedProduct_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.releated_product_card_layout, parent, false);
        return new RelatedProduct_RecyclerAdapter.RelatedProduct_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedProduct_RecyclerAdapter.RelatedProduct_ViewHolder holder, int position) {
        GetHubProductListByCategory_ResponseModel hubProductListByCategory_responseModel = hubData_List.get(position);
        String productImage=hubProductListByCategory_responseModel.getProductImage()!= null ?hubProductListByCategory_responseModel.getProductImage(): "";
        String productName=hubProductListByCategory_responseModel.getProductName()!= null ?hubProductListByCategory_responseModel.getProductName(): "";
        Object discountPrice=hubProductListByCategory_responseModel.getProductDiscountPrice();
        Object productPrice=hubProductListByCategory_responseModel.getProductPrice();
        Object  productDistcountPer=hubProductListByCategory_responseModel.getProductDiscount();
        Object  VeriantId= hubProductListByCategory_responseModel.getProduct_VarientId();

        String productVariantId = String.valueOf((int) ((Double) VeriantId).doubleValue());
        String productColor=hubProductListByCategory_responseModel.getProductColor()!= null ?hubProductListByCategory_responseModel.getProductColor(): "";
        String productRating=String.valueOf(hubProductListByCategory_responseModel.getAverageRating());
        String ProductDiscount= String.valueOf(hubProductListByCategory_responseModel.getProductDiscount())!= null ?String.valueOf(hubProductListByCategory_responseModel.getProductDiscount()): ""; ;
        String ProductDiscountMode= String.valueOf(hubProductListByCategory_responseModel.getProductDiscountMode());

        holder.SetData(productImage,productName,discountPrice,productPrice,productDistcountPer,productColor,productRating,ProductDiscount,ProductDiscountMode);





        holder.relatedCard_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, SpecifeProductDetails_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        if (hubData_List != null) {
            return hubData_List.size();
        } else {
            return 0;
        }
    }

    public void setDataList(List<GetHubProductListByCategory_ResponseModel> DataList) {

        if (DataList != null && !DataList.isEmpty()) {
            // Clear hubData_List if it's not null
            if (hubData_List != null) {
                hubData_List.clear();
            }
            // Assign DataList to hubData_List
            hubData_List = new ArrayList<>(DataList);
            notifyDataSetChanged();
        } else {
            // If DataList is null or empty, set hubData_List to null
            hubData_List = null;
            notifyDataSetChanged();
        }



    }




    public class RelatedProduct_ViewHolder extends RecyclerView.ViewHolder {
        CardView relatedCard_lay;


        ShapeableImageView product_img;
        TextView productName_txt,product_color_txt,productDiscountPrice_txt,productPrice_txt,product_rating_txt,discount_value_txt;

        ImageView addWish_Btn;


        public RelatedProduct_ViewHolder(@NonNull View itemView) {
            super(itemView);
            relatedCard_lay=itemView.findViewById(R.id.relatedCard_lay);

            product_img=itemView.findViewById(R.id.product_img);
            productName_txt=itemView.findViewById(R.id.productName_txt);
            productDiscountPrice_txt=itemView.findViewById(R.id.productDiscountPrice_txt);
            productPrice_txt=itemView.findViewById(R.id.productPrice_txt);
            product_color_txt=itemView.findViewById(R.id.product_color_txt);
            product_rating_txt=itemView.findViewById(R.id.product_rating_txt);
            discount_value_txt=itemView.findViewById(R.id.discount_value_txt);
            addWish_Btn=itemView.findViewById(R.id.addWish_Btn);
        }
        public void SetData(String productImage,String productName,Object discountPrice,Object productPrice,Object productDistcountPer,String productColor,String rating,String productDiscount,String productDiscountMode){

            LoadImage(productImage,product_img);
            productName_txt.setText(productName);
            if (discountPrice!=null){
                productDiscountPrice_txt.setText(discountPrice.toString());
            }

            productPrice_txt.setText(productPrice.toString());
            product_color_txt.setText(productColor);
            discount_value_txt.setText(productDiscount+""+productDiscountMode);
            product_rating_txt.setText(rating);



        }
        public void LoadImage(String ImageUrl,ShapeableImageView imageView) {

            Log.e("LoafImageHub",ImageUrl);



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
