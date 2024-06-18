package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ort.fineart.Model.Request_Model.MyCart.ChangeCartProductQuantity_Interface;
import com.ort.fineart.Model.Request_Model.MyCart.DeleteProductFromCart_Interface;
import com.ort.fineart.Model.Request_Model.MyCart.RemoveCartProduct_RequestModel;
import com.ort.fineart.Model.Response_Model.MyCart.MyCart_ResponseModel;
import com.ort.fineart.Model.Response_Model.WishList.GetWishList_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.MyCart_RecyclerAdapter;
import com.ort.fineart.Recycler_Adapter.WishList_RecyclerAdapter;
import com.ort.fineart.Ui.Dialogs.ConformationDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Conformation_Dialog_Interface;
import com.ort.fineart.Ui.Dialogs.SuccesfullDialog_Fragment;
import com.ort.fineart.Ui.Dialogs.Sucess_Dialog_Interface;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.Utils.VerticalSpaceItemDecoration;
import com.ort.fineart.ViewModel.MyCart_ViewModel;
import com.ort.fineart.databinding.ActivityMyCartBinding;

import java.util.List;
import java.util.Set;

public class MyCart_Activity extends AppCompatActivity {
    ActivityMyCartBinding binding;
    MyCart_ViewModel myCart_viewModel;
    MyCart_RecyclerAdapter myCart_recyclerAdapter;

    DeleteProductFromCart_Interface deleteProductFromCart_interface;


    Conformation_Dialog_Interface conformation_dialog_interface;
    Sucess_Dialog_Interface sucess_dialog_interface;
    ChangeCartProductQuantity_Interface changeCartProductQuantity_interface;
    int RecyclerViewCardMargin=16;
    int Checkout_Count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getApplicationContext(), RecyclerViewCardMargin);

        ViewInit();
        changeCartProductQuantity_interface=new ChangeCartProductQuantity_Interface() {
            @Override
            public void increaseDecreaseQuantity(int cart_item_id, int cart_item_quantity) {

                myCart_viewModel.ChangeProductQuantity_ApiCall(cart_item_id,cart_item_quantity);
                myCart_viewModel.GetCartChangeQuantity().observe(MyCart_Activity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        Log.e("ChangeCartQuantity",aBoolean+":aBoolean");
                        if (aBoolean){
                           GetCartList();
                           // GetWishList();
                           // myCart_viewModel.GetCartChangeQuantity().removeObserver(this);
                        }
                    }
                });



            }
        };

        conformation_dialog_interface = new Conformation_Dialog_Interface() {
            @Override
            public void GetConformation(String TaskName, boolean Y_N, Object... arguments) {
                if (TaskName.trim().equals("DeleteCartProduct") && Y_N) {
                    int Id = 0;

                    for (Object arg : arguments) {
                        Id = Integer.parseInt(arg.toString());
                    }

                    RemoveProduct(Id);





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

        sucess_dialog_interface=new Sucess_Dialog_Interface() {
            @Override
            public void doneSuccessfully(String TaskName, boolean TaskDoneStatus, boolean Y_N) {
                if (TaskName.trim().equals("DeleteCartProduct") && Y_N) {
                    // GetWishList();
                    GetCartList();
                }
            }
        };

        deleteProductFromCart_interface = new DeleteProductFromCart_Interface() {
            @Override
            public void deleteProduct(int Id) {
                ConformationDialog_Fragment confomation_dialog = new ConformationDialog_Fragment(conformation_dialog_interface, getDrawable(R.drawable.add_cart_vector_icon), "Remove product from Cart?", "It will permanently remove product from Cart.", "Yes, Remove", "DeleteCartProduct", Id);
                confomation_dialog.show(getSupportFragmentManager(), confomation_dialog.getTag());


            }
        };


        myCart_recyclerAdapter = new MyCart_RecyclerAdapter(getApplicationContext(), deleteProductFromCart_interface,changeCartProductQuantity_interface);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.cartRecyclerview.setLayoutManager(layoutManager);
        binding.cartRecyclerview.setAdapter(myCart_recyclerAdapter);
        binding.cartRecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));

        GetCartList();
        SetCartList();



        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Checkout_Count!=0){
                    Intent intent=new Intent(getApplicationContext(), Order_CheckOut_Activity.class);
                    startActivity(intent);
                }else {
                    ShowToast.ShowMsgToast("Your Cart is Empty",getApplicationContext());
                }

            }
        });

    }

    private void RemoveProduct(int id) {
        myCart_viewModel.RemoveCartProduct_ApiCall(id);
        myCart_viewModel.removeCartProduct_LiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean == true) {
                    SuccesfullDialog_Fragment succesfullDialog_fragment = new SuccesfullDialog_Fragment(sucess_dialog_interface, getDrawable(R.drawable.success_vector_icon), "Remove product from Cart successfully", "Yes, Okay", "DeleteCartProduct",true);
                    succesfullDialog_fragment.show(getSupportFragmentManager(), succesfullDialog_fragment.getTag());

                }else {

                }
                myCart_viewModel.removeCartProduct_LiveData.removeObservers(MyCart_Activity.this);
            }
        });


    }

    private void GetCartList() {

        myCart_viewModel.GetMyCartList_Callback();

        binding.loadingLottie.setVisibility(View.VISIBLE);


    }

    private void SetCartList() {



        myCart_viewModel.GetMyCartList().observe(this, new Observer<List<MyCart_ResponseModel>>() {
            @Override
            public void onChanged(List<MyCart_ResponseModel> myCart_responseModels) {

                if (myCart_responseModels != null && myCart_responseModels.size() != 0) {
                    Log.e("RemoveProductCart", "Remove 4 Size:-" + myCart_responseModels.size());
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.GONE);
                    binding.cartRecyclerview.setVisibility(View.VISIBLE);
                    myCart_recyclerAdapter.setCartList(myCart_responseModels);
                    Checkout_Count=myCart_responseModels.size();
                } else {
                    Checkout_Count=0;
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.VISIBLE);
                    binding.cartRecyclerview.setVisibility(View.GONE);
                    //Not Found

                    // myCart_recyclerAdapter.setCartList(null);
                }

                //myCart_viewModel.GetMyCartList().removeObserver(this);
            }
        });


    }

    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        myCart_viewModel = viewModelProvider.get(MyCart_ViewModel.class);

    }


}