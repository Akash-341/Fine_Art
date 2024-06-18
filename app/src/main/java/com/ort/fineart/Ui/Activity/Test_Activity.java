package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ort.fineart.Model.Response_Model.Checkout.OrderProductList_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Repository.Checkout_Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Test_Activity extends AppCompatActivity {
    ViewPager View_Pager;
    TabLayout tablayout;

    WebView webView;

    List<String>Horizontal_Banner_ArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        View_Pager = findViewById(R.id.View_Pager);
        webView=findViewById(R.id.webview);
        tablayout = findViewById(R.id.tablayout);
        Horizontal_Banner_ArrayList.add("Welceme");
        Horizontal_Banner_ArrayList.add("Suraj");
        Horizontal_Banner_ArrayList.add("Vikas");
        Horizontal_Banner_ArrayList.add("Omkar ");
        Horizontal_Banner_ArrayList.add("Prajawal");
        Horizontal_Banner_ArrayList.add("Ajay");






        /*View_Pager.setAdapter(new TopHomeBanner_Adapter(getApplicationContext(), Horizontal_Banner_ArrayList));
        tablayout.setupWithViewPager(View_Pager);
        autoImageSlide();*/



      //  String htmlCode = "<ul>\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long descriptionLong description</li>\r\n\t<li>Long description</li>\r\n</ul>";
        String htmlCode = "<ul style=\"font-size: 32px;\">\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long description</li>\r\n\t<li>Long descriptionLong description</li>\r\n\t<li>Long description</li>\r\n</ul>";




      // webView.loadDataWithBaseURL(null, htmlCode, "text/html", "UTF-8", null);


        List<OrderProductList_ResponseModel>variants=new ArrayList<>();


        JsonArray jsonArray = new JsonArray();
        for (OrderProductList_ResponseModel variant : variants) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("varient_id", variant.getVarient_id());
            jsonObject.addProperty("varient_quantity", variant.getVarient_quantity());
            jsonArray.add(jsonObject);
        }

        // Convert JSON array to string
        String jsonString = jsonArray.toString();
        Log.e("ProductQuantity",jsonString);


        Checkout_Repository checkout_repository=new Checkout_Repository(getApplicationContext());
        //checkout_repository.PlaceOrder_ApiCall(String Order_Products,String Amount,String CouponId,String ShoppingAddress,String TotalPrice,String CouponDiscount,String TotalDiscount,String GiftCharges,String DeliveryCharges,String PaymentMode);








    }

    private void autoImageSlide() {
        final long DELAY_MS = 3000;
        final long PERIOD_MS = 3000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (View_Pager.getCurrentItem() == Horizontal_Banner_ArrayList.size() - 1) {
                    View_Pager.setCurrentItem(0);
                } else {
                    View_Pager.setCurrentItem(View_Pager.getCurrentItem() + 1, true);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAY_MS, PERIOD_MS);

    }

}



