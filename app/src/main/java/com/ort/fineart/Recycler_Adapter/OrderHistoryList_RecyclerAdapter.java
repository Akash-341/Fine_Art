package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistory_ResponseModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;
import com.ort.fineart.Model.Response_Model.ViewAll_Deal_and_BestSeller_Product;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.Oder_Details_Activity;
import com.ort.fineart.Utils.DownloadUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryList_RecyclerAdapter extends RecyclerView.Adapter<OrderHistoryList_RecyclerAdapter.OrderHistoryListViewHolder> {



    List<OrderHistory_ResponseModel>orderList=new ArrayList<>();
   Context context;


    public OrderHistoryList_RecyclerAdapter(Context context) {

        this.context = context;

    }

    @NonNull
    @Override
    public OrderHistoryList_RecyclerAdapter.OrderHistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_card_layout, parent, false);
        return new  OrderHistoryList_RecyclerAdapter.OrderHistoryListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryList_RecyclerAdapter.OrderHistoryListViewHolder holder, int position) {
        OrderHistory_ResponseModel orderHistoryResponseModel = orderList.get(position);

        String PaymentMode=orderHistoryResponseModel.getPaymentMode()!= null ?orderHistoryResponseModel.getPaymentMode(): "";
        String OrderDate=orderHistoryResponseModel.getOrderDate()!= null ?orderHistoryResponseModel.getOrderDate(): "";
        String ShippingAddress=orderHistoryResponseModel.getShipingAddress()!= null ?orderHistoryResponseModel.getShipingAddress(): "";

        int TotalPrice=orderHistoryResponseModel.getTotalPrice();
        int OrderId=orderHistoryResponseModel.getOrderId();
        boolean OrderPlaced=orderHistoryResponseModel.isOrderPlaced();
        boolean OrderDispatched=orderHistoryResponseModel.isOrderDispatched();
        boolean OrderDelivered=orderHistoryResponseModel.isOrderDelivered();
        boolean OrderCancelled=orderHistoryResponseModel.isOrderCancelled();



        holder.SetData(PaymentMode,OrderDate,ShippingAddress,TotalPrice,OrderId,OrderPlaced,OrderDispatched,OrderDelivered,OrderCancelled);

        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, Oder_Details_Activity.class);
                intent.putExtra("OrderId",OrderId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.invoice_download_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderHistoryResponseModel.getInvoice()!=null&&orderHistoryResponseModel.getInvoice().trim().length()!=0){
                    DownloadUtil.downloadFile(context, orderHistoryResponseModel.getInvoice());


                }else {

                    Toast.makeText(context, "Invoice Not Found...", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


    @Override
    public int getItemCount() {
        if (orderList != null) {
            return orderList.size();
        } else {
            return 0;
        }
    }
    public void setDataList(List<OrderHistory_ResponseModel> DataList) {

        if (DataList != null) {
            orderList.clear();
            orderList = DataList;
            notifyDataSetChanged();
        } else {
            orderList = null;
            notifyDataSetChanged();
        }
    }
    public class OrderHistoryListViewHolder extends RecyclerView.ViewHolder {

        CardView cardLayout;
        RelativeLayout statusbackgroud_lay,invoice_download_icon;
        ImageView status_icon;
        TextView status_txt,orderId_txt,totalAmount_txt,paymentMode_txt,orderDate_txt,shippingAddress_txt;

        public OrderHistoryListViewHolder(@NonNull View itemView) {
            super(itemView);
            cardLayout=itemView.findViewById(R.id.cardLayout);
            statusbackgroud_lay=itemView.findViewById(R.id.statusbackgroud_lay);
            status_icon=itemView.findViewById(R.id.status_icon);
            status_txt=itemView.findViewById(R.id.status_txt);
            orderId_txt=itemView.findViewById(R.id.orderId_txt);
            totalAmount_txt=itemView.findViewById(R.id.totalAmount_txt);
            paymentMode_txt=itemView.findViewById(R.id.paymentMode_txt);
            orderDate_txt=itemView.findViewById(R.id.orderDate_txt);
            shippingAddress_txt=itemView.findViewById(R.id.shippingAddress_txt);
            invoice_download_icon=itemView.findViewById(R.id.invoice_download_icon);


        }

        public void SetData(String PaymentMode,String OrderDate,String ShippingAddress,int TotalPrice,int OrderId,boolean OrderPlaced,boolean OrderDispatched,boolean OrderDelivered,boolean OrderCancelled){
            String formattedDate="";
            paymentMode_txt.setText("( "+PaymentMode+" )");

            totalAmount_txt.setText(TotalPrice+"");
            orderId_txt.setText(OrderId+"");

            LocalDate date = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                date = LocalDate.parse(OrderDate);
            }

            // Format the date to "dd MMM yyyy" pattern
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formattedDate = date.format(formatter);
            }
            orderDate_txt.setText(formattedDate);
            shippingAddress_txt.setText(ShippingAddress);


            if (OrderPlaced==true&&OrderDelivered==false&&OrderCancelled==false){
                //Order Place
                status_txt.setText("Order Placed");
                status_txt.setTextColor(ContextCompat.getColor(context, R.color.yellow));

                statusbackgroud_lay.setBackgroundTintList(context.getResources().getColorStateList(R.color.light_yellow));

                int tintColor = ContextCompat.getColor(context, R.color.yellow);

                // Apply tint color to the FloatingActionButton
                status_icon.getDrawable().setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);





            }else if (OrderPlaced==true&&OrderDelivered==true&&OrderCancelled==false){
                //Order Deliver
                status_txt.setText("Order Delivered");
                status_txt.setTextColor(ContextCompat.getColor(context, R.color.green));

                statusbackgroud_lay.setBackgroundTintList(context.getResources().getColorStateList(R.color.light_green_color));

                status_icon.setImageDrawable(context.getDrawable(R.drawable.order_delivered_icon));
                int tintColor = ContextCompat.getColor(context, R.color.green);

                // Apply tint color to the FloatingActionButton
                status_icon.getDrawable().setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);




            }else if (OrderPlaced==true&&OrderDelivered==true&&OrderCancelled==true){
                //Order Cancel

                status_txt.setText("Order Cancelled");
                status_txt.setTextColor(ContextCompat.getColor(context, R.color.app_color));

                statusbackgroud_lay.setBackgroundTintList(context.getResources().getColorStateList(R.color.app_color_light));
                status_icon.setImageDrawable(context.getDrawable(R.drawable.order_cancel_icon));
                int tintColor = ContextCompat.getColor(context, R.color.app_color);

                // Apply tint color to the FloatingActionButton
                status_icon.getDrawable().setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);


            }





        }
    }


}
