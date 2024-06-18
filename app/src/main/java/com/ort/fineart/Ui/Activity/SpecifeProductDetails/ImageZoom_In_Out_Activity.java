package com.ort.fineart.Ui.Activity.SpecifeProductDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.Image_Slider_Recyclerview_Adapter;
import com.ort.fineart.databinding.ActivityImageZoomInOutBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ImageZoom_In_Out_Activity extends AppCompatActivity {
    ActivityImageZoomInOutBinding binding;

    ArrayList<String> Images_Path_List = new ArrayList<>();
    int Position;
    RelativeLayout back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageZoomInOutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ChangeStatusBar();


        binding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        Position=intent.getIntExtra("Position",0);

        Type type = new TypeToken<List<String>>() {}.getType();
        Images_Path_List = new Gson().fromJson(getIntent().getStringExtra("private_list"), type);


        Log.e("Images_Path_List",Images_Path_List.size()+"");


        for (String img:Images_Path_List){
            Log.e("Images_Path_List",img);
        }

        binding.viewPagerMain.setAdapter(new Image_Slider_Recyclerview_Adapter(ImageZoom_In_Out_Activity.this,Images_Path_List,binding.viewPagerMain,Position));

    }


    public void ChangeStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}