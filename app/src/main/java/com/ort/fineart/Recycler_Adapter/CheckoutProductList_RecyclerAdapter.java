package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Request_Model.UserAddress.UpdateDeleteAddress_Interface;
import com.ort.fineart.Model.Response_Model.Checkout.Checkout_ProductList_ResponseModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;
import com.ort.fineart.R;

import java.util.ArrayList;
import java.util.List;

public class CheckoutProductList_RecyclerAdapter  extends RecyclerView.Adapter<CheckoutProductList_RecyclerAdapter.CheckoutProductListViewHolder> {


    Context context;



    List<Checkout_ProductList_ResponseModel> productList=new ArrayList<>();

    public CheckoutProductList_RecyclerAdapter(Context context) {
        this.context = context;
    }



    @NonNull
    @Override
    public CheckoutProductList_RecyclerAdapter.CheckoutProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_product_details_card_layout, parent, false);
        return new CheckoutProductList_RecyclerAdapter.CheckoutProductListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutProductList_RecyclerAdapter.CheckoutProductListViewHolder holder, int position) {
        Checkout_ProductList_ResponseModel productList_responseModel = productList.get(position);



        int VarientId=productList_responseModel.getVarientProductId();
        String VarientColor=productList_responseModel.getVarientColor()!=null ? productList_responseModel.getVarientColor() : "";
        String VarientSize=productList_responseModel.getVarientSize()!=null ? productList_responseModel.getVarientSize() : "";;
        int VarientDiscountPrice=productList_responseModel.getVarientDiscountPrice();
        int VarientQuantity=productList_responseModel.getVarientQuantity();
        int VarientTotalWithDiscount=productList_responseModel.getVarientDiscountPrice();
        String ProductImage=productList_responseModel.getProductImage()!=null ? productList_responseModel.getProductImage() : "";;
        String ProductName=productList_responseModel.getProduct_name()!=null ? productList_responseModel.getProduct_name() : "";;



        holder.SetData(ProductName,ProductImage,VarientColor,VarientQuantity,VarientDiscountPrice);






    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        } else {
            return 0;
        }
    }
    public void setDataList(List<Checkout_ProductList_ResponseModel> DataList) {

        if (DataList != null) {
            productList.clear();
            productList = DataList;
            notifyDataSetChanged();
        } else {
            productList = null;
            notifyDataSetChanged();
        }


    }

    public class CheckoutProductListViewHolder extends RecyclerView.ViewHolder {
        TextView productName_txt,productColor_txt,productPrice_txt,productQuantity_txt,totalPrice_txt;
        ShapeableImageView product_img;



        public CheckoutProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            product_img=itemView.findViewById(R.id.product_img);
            productName_txt=itemView.findViewById(R.id.productName_txt);
            productColor_txt=itemView.findViewById(R.id.productColor_txt);
            productPrice_txt=itemView.findViewById(R.id.productPrice_txt);
            productQuantity_txt=itemView.findViewById(R.id.productQuantity_txt);
            totalPrice_txt=itemView.findViewById(R.id.totalPrice_txt);
        }

        public void SetData(String ProductName,String ProductImage,String ProductColor,int ProductQuantity,int ProductDiscountPrice){

          LoadImage(ProductImage,product_img);


            Log.e("ProductImageCheckout",ProductImage+":");

            productName_txt.setText(ProductName);
            productColor_txt.setText(ProductColor);
            productPrice_txt.setText(ProductDiscountPrice+"");
            productQuantity_txt.setText(ProductQuantity+"");

            int TotalPrice=ProductDiscountPrice *  ProductQuantity;
            totalPrice_txt.setText(TotalPrice+"");




        }
        public void LoadImage(String ImageUrl, ShapeableImageView imageView) {




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
