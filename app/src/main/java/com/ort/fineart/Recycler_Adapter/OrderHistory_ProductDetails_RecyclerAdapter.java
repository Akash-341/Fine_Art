package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistory_ProductDetail_ResponseModel;
import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistory_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.SpecifeProductDetails.SpecifeProductDetails_Activity;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory_ProductDetails_RecyclerAdapter extends RecyclerView.Adapter<OrderHistory_ProductDetails_RecyclerAdapter.OrderHistory_ProductDetailsViewHolder> {
    List<OrderHistory_ProductDetail_ResponseModel> orderProductList=new ArrayList<>();
    Context context;

    public OrderHistory_ProductDetails_RecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrderHistory_ProductDetails_RecyclerAdapter.OrderHistory_ProductDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_cardview_layout, parent, false);
        return new  OrderHistory_ProductDetails_RecyclerAdapter.OrderHistory_ProductDetailsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistory_ProductDetails_RecyclerAdapter.OrderHistory_ProductDetailsViewHolder holder, int position) {
        OrderHistory_ProductDetail_ResponseModel productDetail_responseModel =orderProductList.get(position);

        String ProductName=productDetail_responseModel.getProductName()!= null ?productDetail_responseModel.getProductName(): "";
        String ProductImage=productDetail_responseModel.getProductImage()!= null ?productDetail_responseModel.getProductImage(): "";
        String ProductColor=productDetail_responseModel.getProductColor()!= null ?productDetail_responseModel.getProductColor(): "";
        String ProductQuantity=productDetail_responseModel.getProductQuantity()!= null ?productDetail_responseModel.getProductQuantity(): "";
        int ProductDiscountPrice=productDetail_responseModel.getProductDiscountPrice();
        int OrderId=productDetail_responseModel.getProduct_VarientId();
        String productVariantId = String.valueOf(productDetail_responseModel.getProduct_VarientId());


        holder.SetData(ProductName,ProductImage,ProductColor,ProductQuantity,ProductDiscountPrice);


        holder.product_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SpecifeProductDetails_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Product_VarientId",productVariantId);
                context.startActivity(intent);
            }
        });


        holder.buyAgain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, SpecifeProductDetails_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Product_VarientId",productVariantId);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        if (orderProductList != null) {
            return orderProductList.size();
        } else {
            return 0;
        }
    }
    public void setDataList(List<OrderHistory_ProductDetail_ResponseModel> DataList) {

        if (DataList != null) {
            orderProductList.clear();
            orderProductList = DataList;
            notifyDataSetChanged();
        } else {
            orderProductList = null;
            notifyDataSetChanged();
        }
    }

    public class OrderHistory_ProductDetailsViewHolder extends RecyclerView.ViewHolder {

    ShapeableImageView product_Img;
    TextView productName_txt,productColor_txt,productPrice_txt,productQuantity_txt,totalPrice_txt;
    LinearLayout buyAgain_btn;


        public OrderHistory_ProductDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            product_Img=itemView.findViewById(R.id.product_Img);
            productName_txt=itemView.findViewById(R.id.productName_txt);
            productColor_txt=itemView.findViewById(R.id.productColor_txt);
            productPrice_txt=itemView.findViewById(R.id.productPrice_txt);
            productQuantity_txt=itemView.findViewById(R.id.productQuantity_txt);
            totalPrice_txt=itemView.findViewById(R.id.totalPrice_txt);
            buyAgain_btn=itemView.findViewById(R.id.buyAgain_btn);




        }

        public void SetData(String ProductName,String ProductImage,String ProductColor,String ProductQuantity,int ProductDiscountPrice){

            LoadImage(ProductImage,product_Img);
            productName_txt.setText(ProductName);
            productColor_txt.setText(ProductColor);
            productPrice_txt.setText(ProductDiscountPrice+"");
            productQuantity_txt.setText(ProductQuantity+"");

            int TotalPrice=ProductDiscountPrice *  Integer.parseInt(ProductQuantity);
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
