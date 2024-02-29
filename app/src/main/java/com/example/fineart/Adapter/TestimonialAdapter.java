package com.example.fineart.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.fineart.Model.TestimonialModel;
import com.example.fineart.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class TestimonialAdapter extends RecyclerView.Adapter<TestimonialAdapter.PersonViewHolder> {

    private List<TestimonialModel> personList;

    public TestimonialAdapter(List<TestimonialModel> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testimonial_card, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        TestimonialModel person = personList.get(position);

        // Load image using Glide with centerCrop transformation
        Glide.with(holder.itemView.getContext())
                .load(person.getImageUrl())
                .transform(new CenterCrop())
                .placeholder(R.drawable.falogo)
                .into(holder.profileImageView);

        holder.quoteTextView.setText(person.getQuote());
        holder.nameTextView.setText(person.getName());
        holder.designationTextView.setText(person.getDesignation());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView profileImageView;
        TextView quoteTextView;
        TextView nameTextView;
        TextView designationTextView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImageView = itemView.findViewById(R.id.profileImageView);
            quoteTextView = itemView.findViewById(R.id.quoteTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            designationTextView = itemView.findViewById(R.id.designationTextView);
        }
    }
}
