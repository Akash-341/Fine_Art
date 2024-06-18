package com.ort.fineart.Recycler_Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.R;

import java.util.List;

public class Image_Slider_Recyclerview_Adapter extends RecyclerView.Adapter {


    Activity context;
    private List<String> ImageArray_List;
    ViewPager2 viewPager2;
    int Position;

    public Image_Slider_Recyclerview_Adapter(Activity context, List<String> imageArray_List, ViewPager2 viewPager2, int position) {
        this.context = context;
        ImageArray_List = imageArray_List;
        this.viewPager2 = viewPager2;
        Position = position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutOne_image = LayoutInflater.from(context).inflate(R.layout.slide_image_viewpager_cardview, parent, false);
        return new Image_Slider_ViewHolder(layoutOne_image);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Image_Slider_ViewHolder rowViewHolder = (Image_Slider_ViewHolder) holder;


        //   Picasso.get().load(ImageArray_List.get(Position).toString().trim()).noFade().into(rowViewHolder.imageViewMain);
        // Position = position;

        Glide.with(context)
                .load(All_URL.ImageBase_URL+ImageArray_List.get(Position).toString().trim())
                //.placeholder(R.drawable.image_loading_placeholder) // Optional: Placeholder image until the actual image is loaded
                // .error(R.drawable.custom_image_placeholder) // Optional: Error image if the image loading fails
                .into(rowViewHolder.imageViewMain);
        Position = position;




    }

    @Override
    public int getItemCount() {
        return ImageArray_List.size();
    }

    public class Image_Slider_ViewHolder extends RecyclerView.ViewHolder {
        ZoomageView imageViewMain;


        public Image_Slider_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMain = itemView.findViewById(R.id.imageViewMain);
        }
    }
}
