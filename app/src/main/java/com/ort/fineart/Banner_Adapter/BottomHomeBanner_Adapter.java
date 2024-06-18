package com.ort.fineart.Banner_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Response_Model.Home.HomeBottomBanner_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTopBanner_ResponseModel;
import com.ort.fineart.R;

import java.util.ArrayList;
import java.util.List;

public class BottomHomeBanner_Adapter extends PagerAdapter {
    Context context;


    List<HomeBottomBanner_ResponseModel> items=new ArrayList<>();
    public BottomHomeBanner_Adapter(Context context, List<HomeBottomBanner_ResponseModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       /* LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.image_slider_item_layout,container,false);
*/
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_banner_layout, container, false);


        ShapeableImageView product_image=view.findViewById(R.id.product_Image);

        TextView bannerName_txt=view.findViewById(R.id.bannerName_txt);
        TextView bannerHeading_txt=view.findViewById(R.id.bannerHeading_txt);
        TextView bannerText_txt=view.findViewById(R.id.bannerText_txt);
        RelativeLayout buy_nowBtn=view.findViewById(R.id.buy_nowBtn);





        bannerName_txt.setText(items.get(position).getBannerName());
        bannerHeading_txt.setText(items.get(position).getBannerHeading());
        bannerText_txt.setText(items.get(position).getBannerText());
        LoadImage(items.get(position).getBannerImage(),product_image);




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



    public void LoadImage(String ImageUrl,ShapeableImageView imageView) {




        if (ImageUrl != null || ImageUrl.length() != 0) {

            Glide.with(context)
                    .load(All_URL.ImageBase_URL+ImageUrl)
                    //.placeholder(R.drawable.custom_image_placeholder) // Optional: Placeholder image until the actual image is loaded
                    // .error(R.drawable.custom_image_placeholder) // Optional: Error image if the image loading fails
                    .apply(RequestOptions.centerCropTransform()).into(imageView);

        }
    }
}
