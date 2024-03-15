package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Response_Model.ProductByCategory.Payload;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.ProductDetails;

import java.util.ArrayList;
import java.util.List;

public class Hub_CategoryAdapter extends RecyclerView.Adapter<Hub_CategoryAdapter.viewHolder> {
    List<Payload>payloadList;
    Context context;
    private FragmentActivity activity;

    public Hub_CategoryAdapter(List<Payload> payloadList, Context context, FragmentActivity activity) {
        this.payloadList = payloadList;
        this.context = context;
        this.activity = activity;
    }
    public void setProductList(List<Payload> productList) {
        if (productList!=null) {
            List<Payload> filteredList = new ArrayList<>();
            for (Payload product : productList) {
                if (!product.getProductVarientId().isEmpty()) {
                    filteredList.add(product);
                }
            }
            this.payloadList = filteredList;
            notifyDataSetChanged();
        }
        else{
            Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
        }
    }
    @NonNull
    @Override
    public Hub_CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new Hub_CategoryAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hub_CategoryAdapter.viewHolder holder, int position) {
        Payload product=payloadList.get(position);
        holder.productName.setText(product.getProductName());

        CharSequence shortDescription = Html.fromHtml(product.getProductShortDescription());
        holder.productDesc.setText(shortDescription);
        holder.productprice.setText(product.getProductPrice()+" ₹");
        holder.discountprice.setText("Discount Price "+product.getProductDiscountPrice()+" ₹");

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
            intent.putExtra("productId", product.getProductVarientId());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        });

    }

    @Override
    public int getItemCount() {
        return payloadList != null ? payloadList.size() : 0;

    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView productName,productDesc,productprice,discountprice;
        ImageView wishlistImage,productimage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productTitle);
            productDesc= itemView.findViewById(R.id.productDesc);
            productprice = itemView.findViewById(R.id.productPrice);
            discountprice = itemView.findViewById(R.id.discountedPriceTextView);
            wishlistImage = itemView.findViewById(R.id.ic_wishlist);
            productimage = itemView.findViewById(R.id.productImg);

//            wishlistImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onWishlistClick(position);
//                        }
//                    }
//                }
//            });
        }
    }
}
