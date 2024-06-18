package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ort.fineart.Model.Request_Model.MyCart.AddInCart_RequestModel;
import com.ort.fineart.Model.Request_Model.WishList.AddToCart_Interface;
import com.ort.fineart.Model.Request_Model.WishList.DeleteProductFromWishList_Interface;
import com.ort.fineart.Model.Response_Model.WishList.GetWishList_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.TestimonialAdapter;
import com.ort.fineart.Recycler_Adapter.WishList_RecyclerAdapter;
import com.ort.fineart.Ui.Activity.Authentication.Authentication_Activity;
import com.ort.fineart.Ui.Activity.SpecifeProductDetails.SpecifeProductDetails_Activity;
import com.ort.fineart.Ui.Dialogs.ConformationDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Conformation_Dialog_Interface;
import com.ort.fineart.Ui.Dialogs.SuccesfullDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Sucess_Dialog_Interface;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.Utils.VerticalSpaceItemDecoration;
import com.ort.fineart.ViewModel.MyCart_ViewModel;
import com.ort.fineart.ViewModel.WishList_ViewModel;
import com.ort.fineart.databinding.ActivityMyWishlistBinding;

import java.util.List;

public class My_Wishlist_Activity extends AppCompatActivity {
    ActivityMyWishlistBinding binding;
    WishList_ViewModel wishList_viewModel;
    MyCart_ViewModel myCart_viewModel;
    WishList_RecyclerAdapter wishList_recyclerAdapter;
    int RecyclerViewCardMargin = 16;

    DeleteProductFromWishList_Interface deleteProductFromWishList_interface;
    AddToCart_Interface addToCart_interface;
    Conformation_Dialog_Interface conformation_dialog_interface;
    Sucess_Dialog_Interface sucess_dialog_interface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyWishlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getApplicationContext(), RecyclerViewCardMargin);

        ViewInit();


        conformation_dialog_interface = new Conformation_Dialog_Interface() {
            @Override
            public void GetConformation(String TaskName, boolean Y_N, Object... arguments) {
                if (TaskName.trim().equals("DeleteWishProduct") && Y_N) {
                    int VerriatId = 0;

                    for (Object arg : arguments) {
                        VerriatId = Integer.parseInt(arg.toString());
                    }

                    RemoveProduct(VerriatId);





                   /* for (int i = 0; i < arguments.length; i++) {
                        Object arg = arguments[i];
                        if (i == 0) {
                            Log.e("ConforationDailogArg", arg.toString() + "i:" + i);
                        } else if (i == 1) {
                            Log.e("ConforationDailogArg", arg.toString() + "i:" + i);
                        }
                    }*/


                }
            }
        };

        sucess_dialog_interface = new Sucess_Dialog_Interface() {
            @Override
            public void doneSuccessfully(String TaskName, boolean TaskDoneStatus, boolean Y_N) {
                if (TaskName.trim().equals("DeleteWishProduct") && Y_N) {
                    GetWishList();
                    Log.e("DoneWishlist", "Done Success");
                } else {
                    Log.e("DoneWishlist", "Not Done Success");
                }
            }
        };


        deleteProductFromWishList_interface = new DeleteProductFromWishList_Interface() {
            @Override
            public void deleteProduct(int varientId) {

                ConformationDialog_Fragment confomation_dialog = new ConformationDialog_Fragment(conformation_dialog_interface, getDrawable(R.drawable.wishlist_remove_vector_icon), "Remove product from Wishlist?", "It will permanently remove product from Wishlist.", "Yes, Remove", "DeleteWishProduct", varientId);
                confomation_dialog.show(getSupportFragmentManager(), confomation_dialog.getTag());


            }
        };

        addToCart_interface = new AddToCart_Interface() {
            @Override
            public void addCart(int varientId, int quantity) {

                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {

                    AddToCartList(String.valueOf(varientId), quantity);


                } else {
                    Log.e("AddToCartBt", "else");
                    Intent intent = new Intent(getApplicationContext(), Authentication_Activity.class);
                    startActivity(intent);
                }


            }
        };


        wishList_recyclerAdapter = new WishList_RecyclerAdapter(getApplicationContext(), deleteProductFromWishList_interface, addToCart_interface);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.wishListRecyclerview.setLayoutManager(layoutManager);
        binding.wishListRecyclerview.setAdapter(wishList_recyclerAdapter);
        binding.wishListRecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));


        GetWishList();
        SetWishList();


        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void RemoveProduct(int verriatId) {

        wishList_viewModel.AddRemoveWishListProduct_Callback(verriatId);
        wishList_viewModel.wishlistAddRemove().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean == true) {

                    SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.success_vector_icon), "Remove product from Wishlist successfully", "Yes, Okay", "DeleteWishProduct", true);
                    succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());

                }

                wishList_viewModel.wishlistAddRemove().removeObserver(this);
            }
        });


    }

    private void SetWishList() {

        wishList_viewModel.getWishList().observe(this, new Observer<List<GetWishList_ResponseModel>>() {
            @Override
            public void onChanged(List<GetWishList_ResponseModel> getWishList_responseModels) {

                if (getWishList_responseModels != null && getWishList_responseModels.size() != 0) {

                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.GONE);
                    binding.wishListRecyclerview.setVisibility(View.VISIBLE);
                    wishList_recyclerAdapter.setWishList(getWishList_responseModels);
                    Log.e("MyWhishlist", ":20" + getWishList_responseModels.size() + ":Size");
                } else {
                    //Not Found
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.VISIBLE);
                    binding.wishListRecyclerview.setVisibility(View.GONE);

                    Log.e("MyWhishlist", ":21");
                }
                //wishList_viewModel.getWishList().removeObserver(this);

            }
        });

    }

    public void GetWishList() {
        wishList_viewModel.GetWishList_Callback();
        binding.loadingLottie.setVisibility(View.VISIBLE);

    }

    private void AddToCartList(String product_varientId, int productQuantity) {


        myCart_viewModel.AddInCartList_Callback(product_varientId, String.valueOf(productQuantity));
        myCart_viewModel.addInCart().observe(My_Wishlist_Activity.this, new Observer<AddInCart_RequestModel>() {
            @Override
            public void onChanged(AddInCart_RequestModel addInCart_requestModel) {
                if (addInCart_requestModel != null && addInCart_requestModel.getMessge() != null) {
                    ShowToast.ShowMsgToast(addInCart_requestModel.getMessge(), getApplicationContext());
                    GetWishList();
                    myCart_viewModel.addInCart().removeObserver(this);
                } else {
                    ShowToast.ShowMsgToast("Something Wrong,Try again ", getApplicationContext());

                    GetWishList();
                    myCart_viewModel.addInCart().removeObserver(this);
                }

            }
        });


    }


    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        wishList_viewModel = viewModelProvider.get(WishList_ViewModel.class);
        myCart_viewModel = viewModelProvider.get(MyCart_ViewModel.class);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}