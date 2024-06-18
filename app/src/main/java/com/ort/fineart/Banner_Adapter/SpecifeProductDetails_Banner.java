package com.ort.fineart.Banner_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.SpecifeProductDetails.ImageZoom_In_Out_Activity;

import java.util.ArrayList;
import java.util.List;

public class SpecifeProductDetails_Banner extends PagerAdapter {
    Context context;

    private List<String> items;

    public SpecifeProductDetails_Banner(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.specife_product_details_viewpager_layout, container, false);


        ShapeableImageView bannerImg=view.findViewById(R.id.bannerImg);
        LoadImage(items.get(position).toString(),bannerImg);

        bannerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ImageZoom_In_Out_Activity.class);

                ArrayList<String> listPrivate =new ArrayList<> ();
                for (String imagepath:items){
                    listPrivate.add(imagepath);
                }





                intent.putExtra("Position",position);
                intent.putExtra("private_list", new Gson().toJson(listPrivate));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);


            }
        });
      //  TextView eventdate=view.findViewById(R.id.productName_txt);





     //   eventdate.setText(items.get(position).toString());

        /*events_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Event_Details_Activity.class);
                intent.putExtra("EventName",item.getEventname());
                intent.putExtra("EventDetails",item.getEventdetail());
                intent.putExtra("EventImage",item.getImagesrc());
                intent.putExtra("EventDate",item.getEventdate());
                intent.putExtra("ImageSrc",item.getImagesrc());
                v.getContext().startActivity(intent);

            }
        });*/







        container.addView(view);
        return view;
    }


    public void LoadImage(String ImageUrl, ShapeableImageView imageView) {




        if (ImageUrl != null || ImageUrl.length() != 0) {

            Glide.with(context)
                    .load(All_URL.ImageBase_URL+ImageUrl)
                    //.placeholder(R.drawable.custom_image_placeholder) // Optional: Placeholder image until the actual image is loaded
                    // .error(R.drawable.custom_image_placeholder) // Optional: Error image if the image loading fails
                    .apply(RequestOptions.centerCropTransform()).into(imageView);

        }
    }



    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
