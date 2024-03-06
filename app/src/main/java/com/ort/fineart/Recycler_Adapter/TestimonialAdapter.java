package com.ort.fineart.Recycler_Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Model.Response_Model.Testimonial_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.databinding.TestimonialCardBinding;

import java.util.List;

public class TestimonialAdapter extends RecyclerView.Adapter<TestimonialAdapter.PersonViewHolder> {

    private List<Testimonial_ResponseModel> personList;

    public TestimonialAdapter(List<Testimonial_ResponseModel> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TestimonialCardBinding binding=TestimonialCardBinding.inflate(layoutInflater,parent,false);
        return new PersonViewHolder(binding);
    }
    public void setTestimonialList(List<Testimonial_ResponseModel> testimonialList) {
        this.personList = testimonialList;
    }
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Testimonial_ResponseModel person = personList.get(position);

        holder.bind(person);
    }

    @Override
    public int getItemCount() {
        return personList!=null ? personList.size():0;
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        TestimonialCardBinding binding;

        public PersonViewHolder(TestimonialCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
            void bind(Testimonial_ResponseModel model){
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
