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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.android.material.imageview.ShapeableImageView;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Request_Model.MyCart.ChangeCartProductQuantity_Interface;
import com.ort.fineart.Model.Request_Model.MyCart.DeleteProductFromCart_Interface;
import com.ort.fineart.Model.Response_Model.MyCart.MyCart_ResponseModel;
import com.ort.fineart.Model.Response_Model.WishList.GetWishList_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.databinding.ShoppingCartCardLayoutBinding;
import com.ort.fineart.databinding.WishlistCardLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class MyCart_RecyclerAdapter extends RecyclerView.Adapter<MyCart_RecyclerAdapter.MyCartViewHolder> {



    private List<MyCart_ResponseModel> myCartList = new ArrayList<>();
    Context context;

    DeleteProductFromCart_Interface deleteProductFromCart_interface;
    ChangeCartProductQuantity_Interface changeCartProductQuantity_interface;
    public MyCart_RecyclerAdapter(Context context, DeleteProductFromCart_Interface deleteProductFromCart,ChangeCartProductQuantity_Interface changeCartProductQuantity) {

        this.context = context;
        deleteProductFromCart_interface = deleteProductFromCart;
        changeCartProductQuantity_interface=changeCartProductQuantity;
    }

    @NonNull
    @Override
    public MyCart_RecyclerAdapter.MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_card_layout, parent, false);
        return new MyCart_RecyclerAdapter.MyCartViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull MyCart_RecyclerAdapter.MyCartViewHolder holder, int position) {
        MyCart_ResponseModel myCart_responseModel = myCartList.get(position);
        String productName = myCart_responseModel.getProductName() != null ? myCart_responseModel.getProductName() : "";
        String productSize = myCart_responseModel.getVarientSize() != null ? myCart_responseModel.getVarientSize() : "";
        String productColor = myCart_responseModel.getVarientColor() != null ? myCart_responseModel.getVarientColor() : "";
        Integer price = myCart_responseModel.getVarientTotalWithoutDiscount();
        Integer discount_Price = myCart_responseModel.getVarientTotalWithDiscount();
        Integer productQuentity = myCart_responseModel.getVarientQuantity();
        String productImage = myCart_responseModel.getProductImage() != null ? myCart_responseModel.getProductImage() : "";
        holder.moreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup;
                popup = new PopupMenu(context, ((MyCart_RecyclerAdapter.MyCartViewHolder) holder).moreOption);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                    popup = new PopupMenu(context, ((MyCart_RecyclerAdapter.MyCartViewHolder) holder).moreOption, Gravity.END, 0, R.style.RoundPopUpMenu);
                } else {
                    popup = new PopupMenu(context, ((MyCart_RecyclerAdapter.MyCartViewHolder) holder).moreOption);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popup.setForceShowIcon(true);
                }
                popup.inflate(R.menu.wishlist_menu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.remove) {
                            deleteProductFromCart_interface.deleteProduct(myCart_responseModel.getId());
                            return true;
                        }
                        return false;
                    }

                });
                popup.show();
            }
        });

        holder.SetData(context, productName, productSize, productColor, price, discount_Price, productImage, productQuentity);


        holder.minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentQuantity = Integer.parseInt(holder.quntity_txt.getText().toString());


                currentQuantity=currentQuantity-1;

                if (currentQuantity>=1) {
                    changeCartProductQuantity_interface.increaseDecreaseQuantity(myCart_responseModel.getId(),currentQuantity);

                   // holder.quntity_txt.setText(currentQuantity+"");

                    int productPrice=Integer.parseInt(holder.productPrice_txt.getText().toString());
                    int discountPrice=Integer.parseInt(holder.discountPrice_txt.getText().toString());

                   /* productPrice=productPrice/currentQuantity;
                    discountPrice=discountPrice/currentQuantity;
                    holder.productPrice_txt.setText(productPrice+"");
                    holder.discountPrice_txt.setText(discountPrice+"");*/




                }

              //  notifyItemChanged(position);

            }
        });

        holder.plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.quntity_txt.getText().toString());
                currentQuantity=currentQuantity+1;
                if (currentQuantity<=myCart_responseModel.getProductAvailableQuantity()){

                    changeCartProductQuantity_interface.increaseDecreaseQuantity(myCart_responseModel.getId(),currentQuantity);
                  //  holder.quntity_txt.setText(currentQuantity+"");



                    /*int productPrice=Integer.parseInt(holder.productPrice_txt.getText().toString());
                    int discountPrice=Integer.parseInt(holder.discountPrice_txt.getText().toString());

                    productPrice=productPrice*currentQuantity;
                    discountPrice=discountPrice*currentQuantity;
                    holder.productPrice_txt.setText(productPrice+"");
                    holder.discountPrice_txt.setText(discountPrice+"");*/
                }

                //notifyItemChanged(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (myCartList != null) {
            return myCartList.size();
        } else {
            return 0;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setCartList(List<MyCart_ResponseModel> MyCartList) {

        if (MyCartList != null) {
            myCartList.clear();
            myCartList = MyCartList;
            notifyDataSetChanged();
        } else {
            myCartList = null;
            notifyDataSetChanged();
        }


    }

    public void increaseQuantity(int currentQuantity, int availabeQuantity,int discountPrice,int productPrice,TextView discountPriceTxt,TextView productPriceTxt,TextView quantityTxt) {
        if (currentQuantity < availabeQuantity) {
            currentQuantity++;
            quantityTxt.setText(currentQuantity+"");
            discountPriceTxt.setText(discountPrice*2+"");
            productPriceTxt.setText(productPrice*2+"");





        }
    }

    public void decreaseQuantity(int currentQuantity, int availabeQuantity, TextView textView) {
        if (currentQuantity > availabeQuantity) {
            currentQuantity--;
            Log.e("DecreaeQuantity",currentQuantity+":1");
            textView.setText(currentQuantity + "");
        }
    }


    public class MyCartViewHolder extends RecyclerView.ViewHolder {
        ImageView moreOption;
        ShapeableImageView product_image;
        TextView productName_txt,productColor_txt,productSize_txt,productPrice_txt,discountPrice_txt,quntity_txt;
        RelativeLayout minus_btn,plus_btn;




        public MyCartViewHolder(@NonNull View itemView) {
            super(itemView);
            moreOption = itemView.findViewById(R.id.moreOption_icon);
            product_image = itemView.findViewById(R.id.product_image);
            productName_txt = itemView.findViewById(R.id.productName_txt);
            productColor_txt = itemView.findViewById(R.id.productColor_txt);
            productSize_txt = itemView.findViewById(R.id.productSize_txt);
            productPrice_txt = itemView.findViewById(R.id.productPrice_txt);

            discountPrice_txt = itemView.findViewById(R.id.discountPrice_txt);
            quntity_txt = itemView.findViewById(R.id.quntity_txt);
            minus_btn = itemView.findViewById(R.id.minus_btn);
            plus_btn= itemView.findViewById(R.id.plus_btn);
        }

        public void SetData(Context context, String productName, String productSize, String productColor, Integer price, Integer discount_price, String productImage, Integer productQuntity) {
            LoadImage(product_image, productImage, context);
            productName_txt.setText(productName);
            productColor_txt.setText(productColor);
            productSize_txt.setText(productSize);
            productPrice_txt.setText(discount_price + "");
            discountPrice_txt.setText(price + "");
            quntity_txt.setText(productQuntity + "");


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
