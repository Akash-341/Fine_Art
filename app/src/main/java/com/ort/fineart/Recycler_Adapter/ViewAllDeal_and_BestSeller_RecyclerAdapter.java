package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.content.Intent;
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

import com.ort.fineart.Model.Response_Model.ViewAll_Deal_and_BestSeller_Product;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.SpecifeProductDetails.SpecifeProductDetails_Activity;
import com.ort.fineart.Utils.AddRemoveProductWishlist_Interface;

import java.util.ArrayList;
import java.util.List;

public class ViewAllDeal_and_BestSeller_RecyclerAdapter extends RecyclerView.Adapter<ViewAllDeal_and_BestSeller_RecyclerAdapter.ViewAllDeal_and_BestSellerViewHolder>{

    List<ViewAll_Deal_and_BestSeller_Product> viewAllProduct_List=new ArrayList<>();
    Context context;
    AddRemoveProductWishlist_Interface addRemoveProductWishlist_interface;
    public ViewAllDeal_and_BestSeller_RecyclerAdapter(Context context,AddRemoveProductWishlist_Interface addRemoveProductWishlist) {
        this.context = context;
        addRemoveProductWishlist_interface=addRemoveProductWishlist;
    }

    @NonNull
    @Override
    public ViewAllDeal_and_BestSeller_RecyclerAdapter.ViewAllDeal_and_BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hub_product_cardview_layout, parent, false);
        return new ViewAllDeal_and_BestSeller_RecyclerAdapter.ViewAllDeal_and_BestSellerViewHolder(view);
        
        
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllDeal_and_BestSeller_RecyclerAdapter.ViewAllDeal_and_BestSellerViewHolder holder, int position) {

        ViewAll_Deal_and_BestSeller_Product viewAll_deal_and_bestSeller_product = viewAllProduct_List.get(position);
        String productImage=viewAll_deal_and_bestSeller_product.getProductImage()!= null ?viewAll_deal_and_bestSeller_product.getProductImage(): "";
        String productName=viewAll_deal_and_bestSeller_product.getProductName()!= null ?viewAll_deal_and_bestSeller_product.getProductName(): "";
        Object discountPrice=viewAll_deal_and_bestSeller_product.getProductDiscountPrice();
        Object productPrice=viewAll_deal_and_bestSeller_product.getProductPrice();
        Object  productDistcountPer=viewAll_deal_and_bestSeller_product.getProductDiscount();
        Object  VeriantId= viewAll_deal_and_bestSeller_product.getProduct_VarientId();

        String productVariantId = String.valueOf((int) ((Double) VeriantId).doubleValue());
        String ProductDiscount= String.valueOf(viewAll_deal_and_bestSeller_product.getProductDiscount()) ;
        String ProductDiscountMode= String.valueOf(viewAll_deal_and_bestSeller_product.getProductDiscountMode());
        String AverageRating= String.valueOf(viewAll_deal_and_bestSeller_product.getAverageRating());



        if (productPrice.toString().length()!=0){
            holder.SetData(productImage,productName,discountPrice,productPrice,productDistcountPer,ProductDiscount,ProductDiscountMode,AverageRating);

        }





        holder.productCardView_lay.setOnClickListener(new View.OnClickListener() {
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
        if (viewAllProduct_List!= null) {
            return viewAllProduct_List.size();
        } else {
            return 0;
        }
    }
    public void setDataList(List<ViewAll_Deal_and_BestSeller_Product> DataList) {

        if (DataList != null) {
            viewAllProduct_List.clear();
            viewAllProduct_List = DataList;
            notifyDataSetChanged();
        } else {
            viewAllProduct_List = null;
            notifyDataSetChanged();
        }


    }

    public class ViewAllDeal_and_BestSellerViewHolder extends RecyclerView.ViewHolder {

        CardView productCardView_lay;
        ShapeableImageView product_img;
        TextView productName_txt,productDiscountPrice_txt,productPrice_txt,discount_value_txt,product_rating_txt;
        ImageView addWish_Btn;

        public ViewAllDeal_and_BestSellerViewHolder(@NonNull View itemView) {
            super(itemView);


            productCardView_lay=itemView.findViewById(R.id.productCardView_lay);

            product_img=itemView.findViewById(R.id.product_img);
            productName_txt=itemView.findViewById(R.id.productName_txt);
            productDiscountPrice_txt=itemView.findViewById(R.id.productDiscountPrice_txt);
            productPrice_txt=itemView.findViewById(R.id.productPrice_txt);
            discount_value_txt=itemView.findViewById(R.id.discount_value_txt);
            product_rating_txt=itemView.findViewById(R.id.product_rating_txt);
            addWish_Btn=itemView.findViewById(R.id.addWish_Btn);

        }

        public void SetData(String productImage,String productName,Object discountPrice,Object productPrice,Object productDistcountPer,String productDiscount,String productDiscountMode,String averageRating){

            if (productImage!=null&&productImage.length()!=0){
                LoadImage(productImage,product_img);
            }

            productName_txt.setText(productName);
            productDiscountPrice_txt.setText(discountPrice.toString());
            productPrice_txt.setText(productPrice.toString());
            discount_value_txt.setText(productDiscount+""+productDiscountMode);
            product_rating_txt.setText(averageRating);



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
