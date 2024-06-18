package com.ort.fineart.Ui.Activity.SpecifeProductDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.ort.fineart.Banner_Adapter.SpecifeProductDetails_Banner;
import com.ort.fineart.Model.Request_Model.MyCart.AddInCart_RequestModel;
import com.ort.fineart.Model.Request_Model.WishList.AddRemove_WishListProduct_RequestModel;
import com.ort.fineart.Model.Response_Model.Hub.GetHubProductListByCategory_ResponseModel;
import com.ort.fineart.Model.Response_Model.ProductDetails.SpecifeProductDetails_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.ColorList_RecyclerAdapter;
import com.ort.fineart.Recycler_Adapter.RelatedProduct_RecyclerAdapter;
import com.ort.fineart.Recycler_Adapter.SizeList_RecyclerAdapter;
import com.ort.fineart.Ui.Activity.Authentication.Authentication_Activity;
import com.ort.fineart.Ui.Activity.MyCart_Activity;
import com.ort.fineart.Ui.Activity.My_Wishlist_Activity;
import com.ort.fineart.Ui.Activity.Order_CheckOut_Activity;
import com.ort.fineart.Ui.Activity.View_All_Product_Activity;
import com.ort.fineart.Ui.Fragment.Add_Review_Bottomsheet_Fragment;
import com.ort.fineart.Ui.Fragment.Category_Data;
import com.ort.fineart.Ui.Fragment.ProductDetail_ProductDescriptionFragment;
import com.ort.fineart.Ui.Fragment.ProductDetail_ProductMoreDetailsFragment;
import com.ort.fineart.Ui.Fragment.ProductDetail_ProductUsageFragment;
import com.ort.fineart.Ui.Fragment.Profile_Fragment;
import com.ort.fineart.Utils.AddRemoveProductWishlist_Interface;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.HorizontalSpaceItemDecoration;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.ViewModel.Hub_ViewModel;
import com.ort.fineart.ViewModel.MyCart_ViewModel;
import com.ort.fineart.ViewModel.ProductDetails_ViewModel;
import com.ort.fineart.ViewModel.WishList_ViewModel;
import com.ort.fineart.databinding.ActivitySpecifeProductDetailsBinding;

import java.util.ArrayList;
import java.util.List;

public class SpecifeProductDetails_Activity extends AppCompatActivity {
    ActivitySpecifeProductDetailsBinding binding;
    List<String> Horizontal_Banner_ArrayList = new ArrayList<>();
    int RecyclerViewCardMargin = 16;

    ProductDetails_ViewModel productDetails_viewModel;
    MyCart_ViewModel myCart_viewModel;
    WishList_ViewModel wishList_viewModel;
    Hub_ViewModel hub_viewModel;
    RelatedProduct_RecyclerAdapter relatedProduct_recyclerAdapter;
    ColorList_RecyclerAdapter colorList_recyclerAdapter;
    SizeList_RecyclerAdapter sizeList_recyclerAdapter;
    GetProductByCSSProperty_Interface getProductByCSSProperty_interface;
    AddRemoveProductWishlist_Interface addRemoveProductWishlist_interface;

    String ProductDescription_Html;
    String ProductUsages_Html;
    String ProductMoreDetails_Html;
    int ProductQuantity=1;
    int AvailableProductQuantity;


    String Product_VarientId = "";
    int ProductId=0;

    //Passing data to checkout
    String Product_color;
    String Product_size;
    public int ProductCategoryId;
    public int ProductSubCategoryId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpecifeProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getApplicationContext(), RecyclerViewCardMargin);
        ViewInit();
        Intent intent = getIntent();
        Product_VarientId = intent.getStringExtra("Product_VarientId");



        ProductQuantityDetails();
        /**
         * Interface Implementation
         */
        addRemoveProductWishlist_interface=new AddRemoveProductWishlist_Interface() {
            @Override
            public void add_Or_remove_productWish(Integer varientId) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    wishList_viewModel.AddToWishList_Callback(varientId);
                    wishList_viewModel.addToWishList().observe(SpecifeProductDetails_Activity.this, new Observer<AddRemove_WishListProduct_RequestModel>() {
                        @Override
                        public void onChanged(AddRemove_WishListProduct_RequestModel addRemove_wishListProduct_requestModel) {

                            if (addRemove_wishListProduct_requestModel!=null){

                                ShowToast.ShowMsgToast(addRemove_wishListProduct_requestModel.getMessge(),getApplicationContext());

                            }
                            wishList_viewModel.addToWishList().removeObserver(this);
                        }
                    });
                } else {
                    Intent intent = new Intent(SpecifeProductDetails_Activity.this, Authentication_Activity.class);
                    startActivity(intent);

                }
            }
        };







        getProductByCSSProperty_interface = new GetProductByCSSProperty_Interface() {
            @Override
            public void getProductByColor(String color) {
                Product_color=color;
                getProductDetailsByCSS();
            }

            @Override
            public void getProductBySize(String size) {
                Product_size=size;
                getProductDetailsByCSS();
            }
        };

        /**
         * End
         */

        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.relatedProductTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), View_All_Product_Activity.class);
                intent.putExtra("AppBarName","All Product");
                intent.putExtra("CategoryId",String.valueOf(ProductCategoryId));
                startActivity(intent);
            }
        });

        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()&& AvailableProductQuantity != 0) {

                    Log.e("AddToCartBt","If");
                    AddToCartList(Product_VarientId, ProductQuantity);



                } else if (userToken != null && !userToken.isEmpty()&& AvailableProductQuantity == 0) {
                    ShowToast.ShowMsgToast("Out Of Stock",getApplicationContext());
                    Log.e("AddToCartBt","else if");
                } else {
                    Log.e("AddToCartBt","else");
                    Intent intent = new Intent(getApplicationContext(), Authentication_Activity.class);
                    startActivity(intent);
                }

            }
        });
        binding.addToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    wishList_viewModel.AddToWishList_Callback(Integer.parseInt(Product_VarientId));
                    wishList_viewModel.addToWishList().observe(SpecifeProductDetails_Activity.this, new Observer<AddRemove_WishListProduct_RequestModel>() {
                        @Override
                        public void onChanged(AddRemove_WishListProduct_RequestModel addRemove_wishListProduct_requestModel) {

                            if (addRemove_wishListProduct_requestModel!=null){

                                ShowToast.ShowMsgToast(addRemove_wishListProduct_requestModel.getMessge(),getApplicationContext());

                            }
                            wishList_viewModel.addToWishList().removeObserver(this);
                        }
                    });
                } else {
                    Intent intent = new Intent(getApplicationContext(), Authentication_Activity.class);
                    startActivity(intent);

                }



            }
        });

        binding.quickBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {

                    if (AvailableProductQuantity != 0&&ProductId!=0){
                        //Quick Buy
                        Intent intent=new Intent(getApplicationContext(), Order_CheckOut_Activity.class);
                        intent.putExtra("QuickBuy","QuickBuy");
                        intent.putExtra("ProductColor",Product_color);
                        intent.putExtra("ProductSize",Product_size);
                        intent.putExtra("ProductId",ProductId);
                        intent.putExtra("ProductQuntity",ProductQuantity);
                        startActivity(intent);

                    }



                } else {
                    Intent intent = new Intent(getApplicationContext(), Authentication_Activity.class);
                    startActivity(intent);

                }

            }
        });




        relatedProduct_recyclerAdapter = new RelatedProduct_RecyclerAdapter(getApplicationContext(),addRemoveProductWishlist_interface);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.relatedProductRecyclerView.setLayoutManager(layoutManager);
        binding.relatedProductRecyclerView.setAdapter(relatedProduct_recyclerAdapter);
        binding.relatedProductRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));


        sizeList_recyclerAdapter = new SizeList_RecyclerAdapter(getApplicationContext(), getProductByCSSProperty_interface);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.sizeListRecyclerView.setLayoutManager(layoutManager1);
        binding.sizeListRecyclerView.setAdapter(sizeList_recyclerAdapter);
        binding.sizeListRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));

        colorList_recyclerAdapter = new ColorList_RecyclerAdapter(getApplicationContext(), getProductByCSSProperty_interface);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.colorListRecyclerView.setLayoutManager(layoutManager3);
        binding.colorListRecyclerView.setAdapter(colorList_recyclerAdapter);
        binding.colorListRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));


        binding.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), My_Wishlist_Activity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), Authentication_Activity.class);
                    startActivity(intent);

                }
            }
        });

        binding.cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), MyCart_Activity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), Authentication_Activity.class);
                    startActivity(intent);

                }
            }
        });


        binding.writeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ProductId!=0){
                    Add_Review_Bottomsheet_Fragment bottomSheet = new Add_Review_Bottomsheet_Fragment(ProductId);


                    bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                }


            }
        });


        binding.productDescBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.productDescBtn.setTextColor(getResources().getColor(R.color.app_color));
                binding.productUsageBtn.setTextColor(getResources().getColor(R.color.black));
                binding.productMoreDetailsBtn.setTextColor(getResources().getColor(R.color.black));

                OpenFragment(new ProductDetail_ProductDescriptionFragment(ProductDescription_Html));
            }
        });

        binding.productUsageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.productDescBtn.setTextColor(getResources().getColor(R.color.black));
                binding.productUsageBtn.setTextColor(getResources().getColor(R.color.app_color));
                binding.productMoreDetailsBtn.setTextColor(getResources().getColor(R.color.black));

                OpenFragment(new ProductDetail_ProductUsageFragment(ProductUsages_Html));
            }
        });

        binding.productMoreDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.productDescBtn.setTextColor(getResources().getColor(R.color.black));
                binding.productUsageBtn.setTextColor(getResources().getColor(R.color.black));
                binding.productMoreDetailsBtn.setTextColor(getResources().getColor(R.color.app_color));

                OpenFragment(new ProductDetail_ProductMoreDetailsFragment(ProductMoreDetails_Html));
            }
        });


    }

    private void AddToCartList(String product_varientId, int productQuantity) {


        myCart_viewModel.AddInCartList_Callback(product_varientId, String.valueOf(productQuantity));
        myCart_viewModel.addInCart().observe(SpecifeProductDetails_Activity.this, new Observer<AddInCart_RequestModel>() {
            @Override
            public void onChanged(AddInCart_RequestModel addInCart_requestModel) {
                if (addInCart_requestModel != null&&addInCart_requestModel.getMessge()!=null) {
                    ShowToast.ShowMsgToast(addInCart_requestModel.getMessge(), getApplicationContext());
                    myCart_viewModel.addInCart().removeObserver(this);
                } else if (addInCart_requestModel!=null&&addInCart_requestModel.getStatus()==202) {
                    ShowToast.ShowMsgToast("Product Already Exist In Cart", getApplicationContext());
                    myCart_viewModel.addInCart().removeObserver(this);
                } else {
                    ShowToast.ShowMsgToast("Something Wrong,Try again ", getApplicationContext());
                    myCart_viewModel.addInCart().removeObserver(this);
                }

            }
        });


    }

    private void ProductQuantityDetails() {
        binding.quntityTxt.setText(ProductQuantity + "");
        binding.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ProductQuantity < AvailableProductQuantity) {
                    ProductQuantity++;
                    binding.quntityTxt.setText(ProductQuantity + "");
                }
            }
        });

        binding.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ProductQuantity > 1) {
                    ProductQuantity--;
                    binding.quntityTxt.setText(ProductQuantity + "");
                }
            }
        });
    }


    public void OpenFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_product, fragment);
        // transaction.addToBackStack(fragment.getTag()); // Optional: Adds the transaction to the back stack
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            Log.e("HomeFragmentBack", getSupportFragmentManager().getBackStackEntryCount() + ":Replace");
            // If there's only one fragment in the back stack, close the activity
            finish();
        } else {
            Log.e("HomeFragmentBack", ":One Replace");

            // If there are multiple fragments in the back stack, proceed with the default back behavior
            super.onBackPressed();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();


        ProductQuantity = 1;
        binding.quntityTxt.setText(ProductQuantity+"");
        Horizontal_Banner_ArrayList.clear();
        getProductDetails(Product_VarientId);
        setProductDetails();
        SetRelatedProductList();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Product_VarientId = intent.getStringExtra("Product_VarientId");
        binding.nestedScrollView.scrollTo(0, 0);


    }


    public void getProductDetails(String Product_VarientId) {
        Horizontal_Banner_ArrayList.clear();
        productDetails_viewModel.GetProductDetails_Callback(Product_VarientId);
    }

    public void getProductDetailsByCSS() {
        Horizontal_Banner_ArrayList.clear();
        productDetails_viewModel.GetProductByCSS_Callback(ProductId, Product_color, Product_size,ProductCategoryId,ProductSubCategoryId);
        setProductDetails();
    }

    public void setProductDetails() {

        productDetails_viewModel.getProductDetails_LiveData().observe(SpecifeProductDetails_Activity.this, new Observer<SpecifeProductDetails_ResponseModel>() {
            @Override
            public void onChanged(SpecifeProductDetails_ResponseModel specifeProductDetails_responseModel) {
                if (specifeProductDetails_responseModel != null) {
                    String productName = specifeProductDetails_responseModel.getProductName() != null ? specifeProductDetails_responseModel.getProductName() : "";
                    String productShortDec = specifeProductDetails_responseModel.getProductShortDescription() != null ? specifeProductDetails_responseModel.getProductShortDescription() : "";
                    Horizontal_Banner_ArrayList.clear();
                    ProductDescription_Html = specifeProductDetails_responseModel.getProductDescription() != null ? specifeProductDetails_responseModel.getProductDescription() : "";
                    ProductUsages_Html = specifeProductDetails_responseModel.getProductUsage() != null ? specifeProductDetails_responseModel.getProductUsage() : "";
                    ProductMoreDetails_Html = specifeProductDetails_responseModel.getProductMoreDetails() != null ? specifeProductDetails_responseModel.getProductMoreDetails() : "";
                    ProductId=specifeProductDetails_responseModel.getProduct_Id();
                    ProductCategoryId=specifeProductDetails_responseModel.getProductCategoryId();
                    ProductSubCategoryId=specifeProductDetails_responseModel.getProductSubCategoryId();
                    GetRelatedProductList(String.valueOf(ProductCategoryId));
                    AvailableProductQuantity = specifeProductDetails_responseModel.getProductQuantity();


                    if (AvailableProductQuantity==0){
                        binding.quickBuyTxt.setText("Out of Stock");
                    }



                    int productPrice = specifeProductDetails_responseModel.getProductPrice();
                    int productDisPrice = specifeProductDetails_responseModel.getProductDiscountPrice();
                    String productDiscPer = specifeProductDetails_responseModel.getProductDiscount() + " " + specifeProductDetails_responseModel.getProductDiscountMode()+" off";
                    String productColor = specifeProductDetails_responseModel.getProductColor() != null ? specifeProductDetails_responseModel.getProductColor() : "";
                    String productSize = specifeProductDetails_responseModel.getProductSize() != null ? specifeProductDetails_responseModel.getProductSize() : "";
                    String productRating= String.valueOf(specifeProductDetails_responseModel.getAverageRating());

                    Product_color=productColor;
                    Product_size=productSize;



                    ArrayList<String> colorList = specifeProductDetails_responseModel.getColorsNamelist();
                    ArrayList<String> colorHexList = specifeProductDetails_responseModel.getColorslist();
                    ArrayList<String> sizeList = specifeProductDetails_responseModel.getSizelist();


                    Log.e("ProductDetailsCSS",colorList.size()+":Color List Size");
                    Log.e("ProductDetailsCSS",colorHexList.size()+":Color List Size");
                    Log.e("ProductDetailsCSS",sizeList.size()+":Size List Size");





                    colorList_recyclerAdapter.setDataList(colorList, colorHexList, productColor);
                    sizeList_recyclerAdapter.setDataList(sizeList);


                    // Getting Images
                    String productImage = specifeProductDetails_responseModel.getProductImage() != null ? specifeProductDetails_responseModel.getProductImage() : "Not Found";
                    String productImage1 = specifeProductDetails_responseModel.getProductImages1() != null ? specifeProductDetails_responseModel.getProductImages1() : "Not Found";
                    String productImage2 = specifeProductDetails_responseModel.getProductImages2() != null ? specifeProductDetails_responseModel.getProductImages2() : "Not Found";
                    String productImage3 = specifeProductDetails_responseModel.getProductImages3() != null ? specifeProductDetails_responseModel.getProductImages3() : "Not Found";
                    String productImage4 = specifeProductDetails_responseModel.getProductImages4() != null ? specifeProductDetails_responseModel.getProductImages4() : "Not Found";
                    String productImage5 = specifeProductDetails_responseModel.getProductImages5() != null ? specifeProductDetails_responseModel.getProductImages5() : "Not Found";
                    String productImage6 = specifeProductDetails_responseModel.getProductImages6() != null ? specifeProductDetails_responseModel.getProductImages6() : "Not Found";
                    String productImage7 = specifeProductDetails_responseModel.getProductImages7() != null ? specifeProductDetails_responseModel.getProductImages7() : "Not Found";
                    String productImage8 = specifeProductDetails_responseModel.getProductImages8() != null ? specifeProductDetails_responseModel.getProductImages8() : "Not Found";
                    String productImage9 = specifeProductDetails_responseModel.getProductImages9() != null ? specifeProductDetails_responseModel.getProductImages9() : "Not Found";
                    String productImage10 = specifeProductDetails_responseModel.getProductImages10() != null ? specifeProductDetails_responseModel.getProductImages10() : "Not Found";

                    SetImages(productImage, productImage1, productImage2, productImage3, productImage4, productImage5, productImage6, productImage7, productImage8, productImage9, productImage10);


                    //
                    binding.productNameTxt.setText(productName);

                    binding.productDescriptionTxt.setText(Html.fromHtml(productShortDec));
                    binding.productDescriptionTxt.setPadding(0, 0, 0, 0); // Set padding to zero
                    binding.productDescriptionTxt.setLineSpacing(0, 1); // Set line spacing to zero

                    binding.productPriceTxt.setText(productPrice + "");
                    binding.discountPriceTxt.setText(productDisPrice + "");
                    binding.discountPercTxt.setText(productDiscPer);
                    binding.productRatingTxt.setText(productRating);


                    binding.productDescBtn.setTextColor(getResources().getColor(R.color.app_color));
                    OpenFragment(new ProductDetail_ProductDescriptionFragment(ProductDescription_Html));


                }
                productDetails_viewModel.getProductDetails_LiveData().removeObserver(this);
            }
        });

    }

    private void SetImages(String productImage, String productImage1, String productImage2, String productImage3, String productImage4, String productImage5, String productImage6, String productImage7, String productImage8, String productImage9, String productImage10) {

        ArrayList<String> imageArray = new ArrayList<>();
        imageArray.add(productImage);
        imageArray.add(productImage1);
        imageArray.add(productImage2);
        imageArray.add(productImage3);
        imageArray.add(productImage4);
        imageArray.add(productImage5);
        imageArray.add(productImage6);
        imageArray.add(productImage7);
        imageArray.add(productImage8);
        imageArray.add(productImage9);
        imageArray.add(productImage10);
        for (String images : imageArray) {
            if (!images.equals("Not Found")) {
                Horizontal_Banner_ArrayList.add(images);
            }
        }

        binding.ViewPager.setAdapter(new SpecifeProductDetails_Banner(getApplicationContext(), Horizontal_Banner_ArrayList));
        binding.tabLayout.setupWithViewPager(binding.ViewPager);


    }




    private void SetRelatedProductList() {

        hub_viewModel.getHubProduct_List().observe(SpecifeProductDetails_Activity.this, new Observer<List<GetHubProductListByCategory_ResponseModel>>() {
            @Override
            public void onChanged(List<GetHubProductListByCategory_ResponseModel> getHubProductListByCategory_responseModels) {
                if (getHubProductListByCategory_responseModels!=null&&getHubProductListByCategory_responseModels.size()!=0){
                    List<GetHubProductListByCategory_ResponseModel> getHubProductListByCategory=new ArrayList<>();
                    for (GetHubProductListByCategory_ResponseModel model:getHubProductListByCategory_responseModels){
                        if (model.getProductPrice().toString().length()!=0){
                            getHubProductListByCategory.add(model);
                        }
                    }

                    if (getHubProductListByCategory.size()!=0){

                        binding.relatedProductRecyclerView.setVisibility(View.VISIBLE);
                        relatedProduct_recyclerAdapter.setDataList(getHubProductListByCategory);
                    }else {

                        binding.relatedProductRecyclerView.setVisibility(View.GONE);
                        relatedProduct_recyclerAdapter.setDataList(null);
                    }



                }else {


                    binding.relatedProductRecyclerView.setVisibility(View.GONE);
                    relatedProduct_recyclerAdapter.setDataList(null);
                }
            }
        });


    }
    private void GetRelatedProductList(String CategoryId) {

        hub_viewModel.GetHubListByCategory_Callback(CategoryId,"");





    }



    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        productDetails_viewModel = viewModelProvider.get(ProductDetails_ViewModel.class);
        myCart_viewModel = viewModelProvider.get(MyCart_ViewModel.class);
        wishList_viewModel=viewModelProvider.get(WishList_ViewModel.class);
        hub_viewModel =viewModelProvider.get(Hub_ViewModel.class);
    }


}