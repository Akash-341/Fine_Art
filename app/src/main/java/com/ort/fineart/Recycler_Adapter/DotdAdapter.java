package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Response_Model.Product_ResponseModel;
import com.ort.fineart.Ui.Activity.ProductDetails;
import com.ort.fineart.R;

import java.util.List;

public class DotdAdapter extends RecyclerView.Adapter<DotdAdapter.ProductViewHolder> {
    private List<Product_ResponseModel> productList;
    private OnWishlistClickListener wishlistClickListener;
    Context context;
    private FragmentActivity activity;

    public DotdAdapter(List<Product_ResponseModel> productList, Context context,FragmentActivity activity) {
        this.productList = productList;
        this.context = context;
        this.activity=activity;
    }
    public void setProductList(List<Product_ResponseModel> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ProductViewHolder(view, wishlistClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product_ResponseModel product = productList.get(position);

        holder.productName.setText(product.getProductName());
        holder.productDesc.setText(product.getProductShortDescription());
        holder.productprice.setText(product.getProductPrice()+"");
        holder.discountprice.setText(product.getProductDiscountPrice()+"");

        Glide.with(context)
                .load(All_URL.imgURL+product.getProductImage())
                .placeholder(R.drawable.ic_launcher_background)
                .transform(new CenterCrop())
                .into(holder.productimage);
        // Change the heart color based on wishlist status
//        if (product.isWishlist()) {
//            holder.wishlistImage.setImageResource(R.drawable.ic_baseline_favorite_24);
//        } else {
//            holder.wishlistImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
//        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        });
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public interface OnWishlistClickListener {
        void onWishlistClick(int position);
    }

    public void setOnWishlistClickListener(OnWishlistClickListener listener) {
        wishlistClickListener = listener;
    }

    // ViewHolder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productDesc,productprice,discountprice;
        ImageView wishlistImage,productimage;

        public ProductViewHolder(@NonNull View itemView, final OnWishlistClickListener listener) {
            super(itemView);
            productName = itemView.findViewById(R.id.productTitle);
            productDesc= itemView.findViewById(R.id.productDesc);
            productprice = itemView.findViewById(R.id.productPrice);
            discountprice = itemView.findViewById(R.id.discountedPriceTextView);
            wishlistImage = itemView.findViewById(R.id.ic_wishlist);
            productimage = itemView.findViewById(R.id.productImg);

            wishlistImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onWishlistClick(position);
                        }
                    }
                }
            });
        }
    }
}