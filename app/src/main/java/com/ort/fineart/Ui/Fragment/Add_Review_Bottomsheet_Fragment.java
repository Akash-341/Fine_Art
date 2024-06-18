package com.ort.fineart.Ui.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ort.fineart.Model.Request_Model.ProductDetails.AddReview_RequestModel;
import com.ort.fineart.R;
import com.ort.fineart.Utils.ShowDialog;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.Utils.Validations;
import com.ort.fineart.ViewModel.ProductDetails_ViewModel;
import com.ort.fineart.databinding.FragmentAddReviewBottomsheetBinding;


public class Add_Review_Bottomsheet_Fragment extends BottomSheetDialogFragment {


    FragmentAddReviewBottomsheetBinding binding;
    ProductDetails_ViewModel productDetails_viewModel;

    int RatingCount = 2;
    int productId;


    public Add_Review_Bottomsheet_Fragment() {
        // Required empty public constructor
    }

    public Add_Review_Bottomsheet_Fragment(int ProductId) {
        // Required empty public constructor
        productId = ProductId;
    }

    @Override
    public int getTheme() {
        return R.style.NoBackgroundDialogTheme;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddReviewBottomsheetBinding.inflate(inflater, container, false);
        binding.getRoot().setBackgroundResource(R.drawable.bottom_sheet_backgroud);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HandleRating(binding.ratingStarOne, binding.ratingStarTwo, binding.ratingStarThree, binding.ratingStarFour, binding.ratingStarFive);
        ViewInit();


        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.fullNameEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please Enter Your Full Name", getContext());
                } else if (binding.emailEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please Enter Your Email", getContext());

                } else if (!Validations.isValidEmail(binding.emailEdt.getText().toString().trim())) {

                    ShowToast.ShowMsgToast("Please Enter Your Valid Email", getContext());

                } else if (binding.reviewTitleEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please Enter Your Review Title", getContext());

                } else if (binding.reviewsEdt.getText().toString().trim().length() == 0) {
                    ShowToast.ShowMsgToast("Please Enter Your Experience", getContext());

                } else {
                    String full_name = binding.fullNameEdt.getText().toString().trim();
                    String title = binding.reviewTitleEdt.getText().toString().trim();
                    String email = binding.emailEdt.getText().toString().trim();
                    String message = binding.reviewsEdt.getText().toString().trim();

                    String rating_count = String.valueOf(RatingCount);
                    AddReview(full_name, title, email, message, rating_count);
                }
            }
        });

        binding.CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    private void AddReview(String full_name, String title, String email, String message, String rating_count) {

        ShowDialog.showCustomProgressDialog(getContext(), false);

        productDetails_viewModel.AddReview_Callback(full_name, title, email, message, String.valueOf(productId), rating_count);

        productDetails_viewModel.addReviews_LiveData().observe(getViewLifecycleOwner(), new Observer<AddReview_RequestModel>() {
            @Override
            public void onChanged(AddReview_RequestModel addReview_requestModel) {

                if (addReview_requestModel != null && addReview_requestModel.getStatus() == 200) {
                    ShowToast.ShowMsgToast(addReview_requestModel.getMessge(), getContext());
                    ClearAllField();
                } else if (addReview_requestModel != null && addReview_requestModel.getStatus() == 201) {
                    ShowToast.ShowMsgToast(addReview_requestModel.getMessge(), getContext());
                } else {
                    ShowToast.ShowMsgToast("Something wrong ,please try again", getContext());
                }
                productDetails_viewModel.addReviews_LiveData().removeObserver(this);
                ShowDialog.hideCustomProgressDialog();
            }
        });


    }

    private void ClearAllField() {

        binding.fullNameEdt.setText("");
        binding.reviewTitleEdt.setText("");
        binding.emailEdt.setText("");
        binding.reviewsEdt.setText("");
        RatingCount = 2;


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);


        // Set the custom animation for the BottomSheetDialog
        //dialog.getWindow().getAttributes().windowAnimations = R.style.MyBottomSheetDialogTheme;

        // Create and return the BottomSheetDialog
        return dialog;
    }

    private void HandleRating(ImageView rating_one, ImageView rating_two, ImageView rating_three, ImageView rating_four, ImageView rating_five) {

        rating_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingCount = 1;
                RatingImagesHandle(1, rating_one, rating_two, rating_three, rating_four, rating_five);
            }
        });


        rating_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingCount = 2;

                RatingImagesHandle(2, rating_one, rating_two, rating_three, rating_four, rating_five);
            }
        });
        rating_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingCount = 3;
                RatingImagesHandle(3, rating_one, rating_two, rating_three, rating_four, rating_five);
            }
        });
        rating_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingCount = 4;
                RatingImagesHandle(4, rating_one, rating_two, rating_three, rating_four, rating_five);
            }
        });
        rating_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingCount = 5;
                RatingImagesHandle(5, rating_one, rating_two, rating_three, rating_four, rating_five);
            }
        });


    }


    public void RatingImagesHandle(int Rating, ImageView rating_one, ImageView rating_two, ImageView rating_three, ImageView rating_four, ImageView rating_five) {


        if (Rating == 1) {
            rating_one.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_two.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));
            rating_three.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));
            rating_four.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));
            rating_five.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));

        } else if (Rating == 2) {
            rating_one.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_two.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_three.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));
            rating_four.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));
            rating_five.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));

        } else if (Rating == 3) {
            rating_one.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_two.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_three.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_four.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));
            rating_five.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));

        } else if (Rating == 4) {
            rating_one.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_two.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_three.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_four.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_five.setImageDrawable(getResources().getDrawable(R.drawable.star_unfilled_icon));

        } else if (Rating == 5) {
            rating_one.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_two.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_three.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_four.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));
            rating_five.setImageDrawable(getResources().getDrawable(R.drawable.star_filled_icon));

        }

    }


    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        productDetails_viewModel = viewModelProvider.get(ProductDetails_ViewModel.class);

    }


}