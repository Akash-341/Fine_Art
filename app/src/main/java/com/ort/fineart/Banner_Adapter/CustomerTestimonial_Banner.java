package com.ort.fineart.Banner_Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Response_Model.Home.HomeTestimonial_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTopBanner_ResponseModel;
import com.ort.fineart.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerTestimonial_Banner extends PagerAdapter {
    Context context;


    List<HomeTestimonial_ResponseModel> items=new ArrayList<>();

    public CustomerTestimonial_Banner(Context context, List<HomeTestimonial_ResponseModel> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       /* LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.image_slider_item_layout,container,false);
*/
        View view = LayoutInflater.from(context).inflate(R.layout.user_testimonial_card_layout, container, false);



        ShapeableImageView profile_image=view.findViewById(R.id.profile_image);

        TextView review_txt=view.findViewById(R.id.review_txt);
        TextView username_txt=view.findViewById(R.id.username_txt);
        TextView useremail_txt=view.findViewById(R.id.useremail_txt);
//        CardView cardLayout=view.findViewById(R.id.cardLayout);





        review_txt.setText(items.get(position).getDescription()+items.get(position).getDescription()+items.get(position).getDescription());
        username_txt.setText(items.get(position).getName());
        useremail_txt.setText(items.get(position).getSubName());
        LoadImage(items.get(position).getImage(),profile_image);





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
        Log.e("CustomerTestimonial",items.size()+"");
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
