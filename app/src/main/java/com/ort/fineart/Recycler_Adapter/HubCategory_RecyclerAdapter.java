package com.ort.fineart.Recycler_Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.ort.fineart.Model.Response_Model.Hub.Hub_CategoryList_ResponseModel;
import com.ort.fineart.Model.Response_Model.WishList.GetWishList_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Ui.Fragment.Category_Data;

import java.util.ArrayList;
import java.util.List;

public class HubCategory_RecyclerAdapter extends RecyclerView.Adapter<HubCategory_RecyclerAdapter.HubCategoryViewHolder> {

    List<Hub_CategoryList_ResponseModel> category_data=new ArrayList<>();
    Context context;
    private int selectedPosition = RecyclerView.NO_POSITION;
    CategorySelection_Interface categorySelection_interface;

    public HubCategory_RecyclerAdapter(Context context,CategorySelection_Interface category_interface) {
        this.context = context;
        categorySelection_interface=category_interface;
    }

    @NonNull
    @Override
    public HubCategory_RecyclerAdapter.HubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hub_category_layout, parent, false);
        return new HubCategory_RecyclerAdapter.HubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HubCategory_RecyclerAdapter.HubCategoryViewHolder holder, int position) {
        holder.bind(category_data.get(position));
        holder.chip.setChecked(position == selectedPosition);

    }

    @Override
    public int getItemCount() {
        return category_data.size();
    }





    public void setCategoryList(List<Hub_CategoryList_ResponseModel> CategoryList) {
        if (CategoryList != null) {
            category_data.clear();
            category_data = CategoryList;
            notifyDataSetChanged();

        } else {
            category_data = null;
            notifyDataSetChanged();
        }
    }
    public class HubCategoryViewHolder extends RecyclerView.ViewHolder {
        private Chip chip;
        public HubCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip);
        }

        public void bind(Hub_CategoryList_ResponseModel category_data) {
            chip.setText(category_data.getCategoryName());
            chip.setId(category_data.getId());





            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() == RecyclerView.NO_POSITION)
                        return;

                    // Update selection
                    notifyItemChanged(selectedPosition);
                    selectedPosition = getAdapterPosition();
                    notifyItemChanged(selectedPosition);
                    categorySelection_interface.CategorySelection(chip.getText().toString(),chip.getId());
                }
            });











        }
    }


    public interface CategorySelection_Interface  {

        void CategorySelection(String SelectionCat,Integer CategoryId);

    }



}


