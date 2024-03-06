package com.example.fineart.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.fineart.All_URL;
import com.example.fineart.Model.TestimonialModel;
import com.example.fineart.R;
import com.example.fineart.databinding.TestimonialCardBinding;
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TestimonialCardBinding binding=TestimonialCardBinding.inflate(layoutInflater,parent,false);
        return new PersonViewHolder(binding);
    }
    public void setTestimonialList(List<TestimonialModel> testimonialList) {
        this.personList = testimonialList;
    }
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        TestimonialModel person = personList.get(position);

        holder.bind(person);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        TestimonialCardBinding binding;

        public PersonViewHolder(TestimonialCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
            void bind(TestimonialModel model){
                Glide.with(binding.getRoot().getContext())
                        .load(All_URL.imgURL+model.getImage())
                        .transform(new CenterCrop())
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(binding.profileImageView);
              binding.quoteTextView.setText(model.getDescription());
                binding.nameTextView.setText(model.getName());
                binding.designationTextView.setText(model.getSubName());
            }



    }
}
