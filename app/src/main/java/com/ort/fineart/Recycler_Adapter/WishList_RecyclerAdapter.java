package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.android.material.imageview.ShapeableImageView;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Request_Model.WishList.AddToCart_Interface;
import com.ort.fineart.Model.Request_Model.WishList.DeleteProductFromWishList_Interface;
import com.ort.fineart.Model.Response_Model.WishList.GetWishList_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.databinding.WishlistCardLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class WishList_RecyclerAdapter extends RecyclerView.Adapter<WishList_RecyclerAdapter.WishListViewHolder> {

    WishlistCardLayoutBinding binding;
    private List<GetWishList_ResponseModel> wishList = new ArrayList<>();
    Context context;
    DeleteProductFromWishList_Interface deleteProductFromWishList_interface;
    AddToCart_Interface addToCart_interface;


    public WishList_RecyclerAdapter(Context context, DeleteProductFromWishList_Interface deleteProduct, AddToCart_Interface addCart) {

        this.context = context;
        deleteProductFromWishList_interface = deleteProduct;
        addToCart_interface = addCart;
    }

    @NonNull
    @Override
    public WishList_RecyclerAdapter.WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WishlistCardLayoutBinding binding = WishlistCardLayoutBinding.inflate(layoutInflater, parent, false);
        return new WishList_RecyclerAdapter.WishListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WishList_RecyclerAdapter.WishListViewHolder holder, int position) {
        GetWishList_ResponseModel wishList_responseModel = wishList.get(position);

        String productName = wishList_responseModel.getProductName() != null ? wishList_responseModel.getProductName() : "";
        String productSize = wishList_responseModel.getProductSize() != null ? wishList_responseModel.getProductSize() : "";
        String productColor = wishList_responseModel.getProductColor() != null ? wishList_responseModel.getProductColor() : "";
        Integer price = wishList_responseModel.getProductPrice();
        Integer discount_Price = wishList_responseModel.getProductDiscountPrice();
        String productImage = wishList_responseModel.getProductImage() != null ? wishList_responseModel.getProductImage() : "";


        holder.SetData(productName, productSize, productColor, price, discount_Price, productImage);


        binding.moreOptionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup;
                popup = new PopupMenu(context,((WishListViewHolder) holder).moreOption);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                    popup = new PopupMenu(context,((WishListViewHolder) holder).moreOption, Gravity.END, 0, R.style.RoundPopUpMenu);
                } else {
                    popup = new PopupMenu(context, ((WishListViewHolder) holder).moreOption);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popup.setForceShowIcon(true);
                }
                popup.inflate(R.menu.wishlist_menu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.remove) {
                            deleteProductFromWishList_interface.deleteProduct(wishList_responseModel.getProduct_VarientId());
                            return true;
                        }
                        return false;
                    }

                });
                popup.show();
            }
        });

        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart_interface.addCart(wishList_responseModel.getProduct_VarientId(), 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (wishList != null) {
            return wishList.size();
        } else {
            return 0;
        }

    }

    public void setWishList(List<GetWishList_ResponseModel> WishList) {
        if (WishList != null) {
            wishList.clear();
            wishList = WishList;
            notifyDataSetChanged();
            Log.e("MyWhishlist",":25"+wishList.size()+":Wishlist Size");
        } else {
            wishList = null;
            notifyDataSetChanged();
            Log.e("MyWhishlist",":30");
        }
    }

    public class WishListViewHolder extends RecyclerView.ViewHolder {
        ImageView moreOption;


        public WishListViewHolder(@NonNull WishlistCardLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            moreOption=binding.moreOptionIcon;
        }

        public void SetData(String productName, String productSize, String productColor, Integer price, Integer discount_price, String productImage) {
            LoadImage(binding.productImage, productImage, context);
            binding.productNameTxt.setText(productName);
            binding.sizeTxt.setText(productSize);
            binding.colorTxt.setText(productColor);
            binding.TotalPriceTxt.setText(price + "");
            binding.discountPriceTxt.setText(discount_price + "");


        }

    }


    public void LoadImage(ShapeableImageView imageView, String ImageUrl, Context context) {
        Glide.with(context)
                .load(All_URL.imgURL + ImageUrl)
                .transform(new CenterCrop())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
