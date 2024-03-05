package com.example.fineart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fineart.Activity.ProductDetails;
import com.example.fineart.Model.Product;
import com.example.fineart.R;

import java.util.List;

public class DotdAdapter extends RecyclerView.Adapter<DotdAdapter.ProductViewHolder> {
    private List<Product> productList;
    private OnWishlistClickListener wishlistClickListener;
    Context context;
    private FragmentActivity activity;

    public DotdAdapter(List<Product> productList, Context context,FragmentActivity activity) {
        this.productList = productList;
        this.context = context;
        this.activity=activity;
    }
    public void setProductList(List<Product> productList) {
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
        Product product = productList.get(position);

        holder.productName.setText(product.getName());

        // Change the heart color based on wishlist status
        if (product.isWishlist()) {
            holder.wishlistImage.setImageResource(R.drawable.ic_baseline_favorite_24);
        } else {
            holder.wishlistImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public interface OnWishlistClickListener {
        void onWishlistClick(int position);
    }

    public void setOnWishlistClickListener(OnWishlistClickListener listener) {
        wishlistClickListener = listener;
    }

    // ViewHolder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        ImageView wishlistImage;

        public ProductViewHolder(@NonNull View itemView, final OnWishlistClickListener listener) {
            super(itemView);
            productName = itemView.findViewById(R.id.productTitle);
            wishlistImage = itemView.findViewById(R.id.ic_wishlist);

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