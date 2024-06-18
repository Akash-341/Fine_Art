package com.ort.fineart.Ui.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.ort.fineart.Model.Response_Model.Hub.Hub_SubCategoryList_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.ViewModel.Hub_ViewModel;
import com.ort.fineart.databinding.FragmentAddReviewBottomsheetBinding;
import com.ort.fineart.databinding.FragmentFilterViewAllProductBinding;

import java.util.ArrayList;
import java.util.List;


public class Filter_View_All_Product_Fragment extends BottomSheetDialogFragment {
    FragmentFilterViewAllProductBinding binding;

    Hub_ViewModel hub_viewModel;
    String CategoryId;
    String SubCategoryId;
    String SubCategoryName;

    SelectedSubCategory_Interface selectedSubCategory_interface;

    public Filter_View_All_Product_Fragment() {
        // Required empty public constructor
    }





    public Filter_View_All_Product_Fragment(String categoryId, SelectedSubCategory_Interface selectedSubCategory) {
        selectedSubCategory_interface = selectedSubCategory;
        // Required empty public constructor
        CategoryId = categoryId;
       // CategoryId = "57";
    }

    @Override
    public int getTheme() {
        return R.style.NoBackgroundDialogTheme;

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterViewAllProductBinding.inflate(inflater, container, false);
        binding.getRoot().setBackgroundResource(R.drawable.bottom_sheet_backgroud);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewInit();
        GetSubCategoryList(CategoryId);


        binding.CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        binding.applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getContext().getSharedPreferences("HubSubCategoryFilter", Context.MODE_PRIVATE).edit();
                editor.putString("CategoryId", CategoryId);
                editor.putString("SubCategoryId", SubCategoryId);
                editor.putString("SubCategoryName", SubCategoryName);
                editor.apply();
                selectedSubCategory_interface.selected_subCategory(CategoryId,SubCategoryId);
                dismiss();
            }
        });

        binding.clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getContext().getSharedPreferences("HubSubCategoryFilter", Context.MODE_PRIVATE).edit();
                editor.putString("CategoryId", CategoryId);
                editor.putString("SubCategoryId", "NotFound");
                editor.putString("SubCategoryName", "NotFound");
                editor.apply();
                selectedSubCategory_interface.selected_subCategory(CategoryId,"");
                dismiss();
            }
        });


    }

    private void GetSubCategoryList(String CategoryId) {
        hub_viewModel.GetHubSubCategory_Callback(CategoryId);
        hub_viewModel.getHubSubCategory().observe(getViewLifecycleOwner(), new Observer<List<Hub_SubCategoryList_ResponseModel>>() {
            @Override
            public void onChanged(List<Hub_SubCategoryList_ResponseModel> hub_subCategoryList_responseModels) {
                if (hub_subCategoryList_responseModels != null && hub_subCategoryList_responseModels.size() != 0) {
                    SetCategoryChipData(hub_subCategoryList_responseModels);
                } else {

                }
            }
        });
    }

    private void SetCategoryChipData(List<Hub_SubCategoryList_ResponseModel> hub_subCategoryList_responseModels) {
        SharedPreferences prefs = getContext().getSharedPreferences("HubSubCategoryFilter", Context.MODE_PRIVATE);
        int subcategoryId=0;
        if (!prefs.getString("SubCategoryId","").equals("NotFound")){
            subcategoryId= Integer.parseInt(prefs.getString("SubCategoryId",""));

        }

        for (Hub_SubCategoryList_ResponseModel data : hub_subCategoryList_responseModels) {
            Chip chip = new Chip(getActivity());
            chip.setCheckable(true);
            chip.setText(data.getSubCategoryName());
            chip.setId(data.getId());




            if (data.getId()==subcategoryId) {
                chip.setChecked(true);

            }else {

            }

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        SubCategoryId = String.valueOf(data.getId());
                        SubCategoryName = data.getSubCategoryName();
                    }else {
                        SubCategoryId = "NotFound";
                        SubCategoryName = "NotFound";
                    }
                }
            });




            binding.filterCategoryChipGp.addView(chip);
        }


    }


    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        hub_viewModel = viewModelProvider.get(Hub_ViewModel.class);

    }


    public interface SelectedSubCategory_Interface {
        void selected_subCategory(String Category, String Subcategory);
    }
}