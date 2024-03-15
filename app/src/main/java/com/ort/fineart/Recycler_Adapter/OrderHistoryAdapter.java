package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistoryModel;
import com.ort.fineart.Model.Response_Model.OrderHistory.Payload;
import com.ort.fineart.Model.Response_Model.OrderHistory.Product;
import com.ort.fineart.R;
import com.ort.fineart.databinding.OrderHistoryCardLayoutBinding;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.viewHolder> {
    private Context context;
    private List<Payload> payloadList;

    public OrderHistoryAdapter(Context context, List<Payload> payloadList) {
        this.context = context;
        this.payloadList = payloadList;
    }
    public void setPayloadList(List<Payload> payloadList) {
        this.payloadList = payloadList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public OrderHistoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        OrderHistoryCardLayoutBinding binding = OrderHistoryCardLayoutBinding.inflate(layoutInflater, parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.viewHolder holder, int position) {
        Payload payload = payloadList.get(position);
        holder.bind(payload);
    }

    @Override
    public int getItemCount() {
        return payloadList!=null ? payloadList.size():0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        OrderHistoryCardLayoutBinding binding;
        public viewHolder(OrderHistoryCardLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.productDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toggle visibility of product_info layout
                    if (payloadList.get(getAdapterPosition()).getProducts().isEmpty()) {
                        return;
                    }
                    if (binding.productInfo.getVisibility() == View.VISIBLE) {
                        // Apply animation for collapsing
                        Animation collapseAnimation = AnimationUtils.loadAnimation(context, R.anim.collapse);
                        binding.productInfo.startAnimation(collapseAnimation);
                        binding.productInfo.setVisibility(View.GONE);
                        binding.productDesc.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24); // Change to down arrow
                    } else {
                        // Apply animation for expanding
                        Animation expandAnimation = AnimationUtils.loadAnimation(context, R.anim.expand);
                        binding.productInfo.startAnimation(expandAnimation);
                        binding.productInfo.setVisibility(View.VISIBLE);
                        binding.productDesc.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24); // Change to up arrow
                    }
                }
            });
        }
        void bind(Payload payload){
            binding.orderNo.setText(payload.getOrderId());
            binding.totalPrice.setText(payload.getTotalPrice());
            binding.paymentMode.setText("("+payload.getPaymentMode()+")");
            binding.orderPlaced.setText(payload.getOrderDate());
            List<Product> products = payload.getProducts();
            for (Product product : products) {
                binding.productname.setText(product.getProductName());
                binding.pdColor.setText(product.getProductColor());
                binding.pdQty.setText(product.getProductQuantity());
                binding.pdPrice.setText(product.getProductPrice());

                Glide.with(context)
                        .load(All_URL.imgURL+product.getProductImage())
                        .placeholder(R.drawable.ic_launcher_background)
                        .transform(new CenterCrop())
                        .into(binding.productImage);
            }
        }
    }
}
