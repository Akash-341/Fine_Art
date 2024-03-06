package com.ort.fineart.Recycler_Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ort.fineart.R;

import java.util.List;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.NameViewHolder> {
    private List<String> namesList;
    private OnItemClickListener onItemClickListener;


    public BottomSheetAdapter(List<String> namesList) {
        this.namesList = namesList;
    }


    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_item, parent, false);
        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        String name = namesList.get(position);
        holder.textName.setText(name);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v,name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }
    public interface OnItemClickListener {
        void onItemClick(View view, String selectedName);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    public static class NameViewHolder extends RecyclerView.ViewHolder {
        TextView textName;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
        }
    }
}
