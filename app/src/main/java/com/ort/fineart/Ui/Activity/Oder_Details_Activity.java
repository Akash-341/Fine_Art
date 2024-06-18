package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistory_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.OrderHistoryList_RecyclerAdapter;
import com.ort.fineart.Recycler_Adapter.OrderHistory_ProductDetails_RecyclerAdapter;
import com.ort.fineart.Utils.DownloadUtil;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.VerticalSpaceItemDecoration;
import com.ort.fineart.ViewModel.OrderHistory_ViewModel;
import com.ort.fineart.databinding.ActivityOderDetailsBinding;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Oder_Details_Activity extends AppCompatActivity {
    ActivityOderDetailsBinding binding;
    String OrderId="";
    int RecyclerViewCardMargin=16;
    OrderHistory_ViewModel orderHistory_viewModel;
    OrderHistory_ProductDetails_RecyclerAdapter orderHistory_productDetails_recyclerAdapter;


    String Invoice_File="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityOderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getApplicationContext(), RecyclerViewCardMargin);
        ViewInit();
        Intent intent=getIntent();
        OrderId= String.valueOf(intent.getIntExtra("OrderId",0));


        GetOrderDetails();
        SetOrderDetails();

        orderHistory_productDetails_recyclerAdapter = new OrderHistory_ProductDetails_RecyclerAdapter(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.productListRecyclerview.setLayoutManager(layoutManager);
        binding.productListRecyclerview.setAdapter(orderHistory_productDetails_recyclerAdapter);
        binding.productListRecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));




        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.downloadInvoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Invoice_File!=null&&Invoice_File.trim().length()!=0){
                    DownloadUtil.downloadFile(getApplicationContext(), Invoice_File);
                }else {
                    Toast.makeText(getApplicationContext(), "Invoice Not Found...", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        orderHistory_viewModel = viewModelProvider.get(OrderHistory_ViewModel.class);

    }


    public void GetOrderDetails(){
        orderHistory_viewModel.GetOrderDetails_Callback(OrderId);
    }
    public void SetOrderDetails(){
        orderHistory_viewModel.getOrderDetails().observe(Oder_Details_Activity.this, new Observer<OrderHistory_ResponseModel>() {
            @Override
            public void onChanged(OrderHistory_ResponseModel orderHistory_responseModel) {

                if (orderHistory_responseModel!=null){

                    String PaymentMode=orderHistory_responseModel.getPaymentMode()!= null ?orderHistory_responseModel.getPaymentMode(): "";
                    String OrderDate=orderHistory_responseModel.getOrderDate()!= null ?orderHistory_responseModel.getOrderDate(): "";
                    String ShippingAddress=orderHistory_responseModel.getShipingAddress()!= null ?orderHistory_responseModel.getShipingAddress(): "";
                    Invoice_File=orderHistory_responseModel.getInvoice()!= null ?orderHistory_responseModel.getInvoice(): "";




                    int TotalPrice=orderHistory_responseModel.getTotalPrice();
                    int OrderId=orderHistory_responseModel.getOrderId();
                    boolean OrderPlaced=orderHistory_responseModel.isOrderPlaced();
                    boolean OrderDispatched=orderHistory_responseModel.isOrderDispatched();
                    boolean OrderDelivered=orderHistory_responseModel.isOrderDelivered();
                    boolean OrderCancelled=orderHistory_responseModel.isOrderCancelled();

                    SetDetails(PaymentMode,OrderDate,ShippingAddress,TotalPrice,OrderId,OrderPlaced,OrderDispatched,OrderDelivered,OrderCancelled);



                    if (orderHistory_responseModel.getProducts()!=null&&orderHistory_responseModel.getProducts().size()!=0){

                        orderHistory_productDetails_recyclerAdapter.setDataList(orderHistory_responseModel.getProducts());

                    }else {
                        //Don't show product list
                        orderHistory_productDetails_recyclerAdapter.setDataList(null);

                    }


                }else {

                    //Don't show product list

                }


            }
        });


    }

    private void SetDetails(String paymentMode, String orderDate, String shippingAddress, int totalPrice, int orderId, boolean orderPlaced, boolean orderDispatched, boolean orderDelivered, boolean orderCancelled) {


        String formattedDate="";
        binding.paymentModeTxt.setText("( "+paymentMode+" )");

        binding.totalAmountTxt.setText(totalPrice+"");
        binding.orderIdTxt.setText(orderId+"");

        LocalDate date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.parse(orderDate);
        }

        // Format the date to "dd MMM yyyy" pattern
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = date.format(formatter);
        }
        binding.orderDateTxt.setText(formattedDate);
        binding.shippingAddressTxt.setText(shippingAddress);


        if (orderPlaced==true&&orderDelivered==false&&orderCancelled==false){
            //Order Place
            binding.statusTxt.setText("Order Placed");
            binding.statusTxt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));

            binding.statusbackgroudLay.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.light_yellow));

            int tintColor = ContextCompat.getColor(getApplicationContext(), R.color.yellow);

            // Apply tint color to the FloatingActionButton
            binding.statusIcon.getDrawable().setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);





        }else if (orderPlaced==true&&orderDelivered==true&&orderCancelled==false){
            //Order Deliver
            binding.statusTxt.setText("Order Delivered");
            binding.statusTxt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));

            binding.statusbackgroudLay.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.light_green_color));

            binding.statusIcon.setImageDrawable(getApplicationContext().getDrawable(R.drawable.order_delivered_icon));
            int tintColor = ContextCompat.getColor(getApplicationContext(), R.color.green);

            // Apply tint color to the FloatingActionButton
            binding.statusIcon.getDrawable().setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);




        }else if (orderPlaced==true&&orderDelivered==true&&orderCancelled==true){
            //Order Cancel

            binding.statusTxt.setText("Order Cancelled");
            binding.statusTxt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color));

            binding.statusbackgroudLay.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.app_color_light));
            binding.statusIcon.setImageDrawable(getApplicationContext().getDrawable(R.drawable.order_cancel_icon));
            int tintColor = ContextCompat.getColor(getApplicationContext(), R.color.app_color);

            // Apply tint color to the FloatingActionButton
            binding.statusIcon.getDrawable().setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);


        }






    }


}