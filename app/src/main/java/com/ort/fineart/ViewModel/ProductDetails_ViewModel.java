package com.ort.fineart.ViewModel;

import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.ProductDetails.AddReview_RequestModel;
import com.ort.fineart.Model.Response_Model.ProductDetails.GetSpecifeproductDetailsByCSS_ResponseModel;
import com.ort.fineart.Model.Response_Model.ProductDetails.SpecifeProductDetails_ResponseModel;
import com.ort.fineart.Repository.ProductDetails_Repository;
import com.ort.fineart.Utils.SharedPreferenceManager;

public class ProductDetails_ViewModel extends ViewModel {
    private ProductDetails_Repository productDetailRepository;
    private MutableLiveData<SpecifeProductDetails_ResponseModel> productdetails_LiveData=new MutableLiveData<>();

    private MutableLiveData<AddReview_RequestModel> addReviews_LiveData=new MutableLiveData<>();





    public ProductDetails_ViewModel() {
        productDetailRepository = new ProductDetails_Repository();

    }

    /**
     * All Return
     */
    public LiveData<SpecifeProductDetails_ResponseModel> getProductDetails_LiveData() {
        return productdetails_LiveData;
    }


    public LiveData<AddReview_RequestModel>addReviews_LiveData(){
        return addReviews_LiveData;
    }



    /**
     * Api Call
     */


    public void GetProductDetails_Callback(String varientId) {

        Log.e("SpecifeProductDetails",varientId+":Varient Id");


        productDetailRepository.GetProductDetails_ApiCall(varientId, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                SpecifeProductDetails_ResponseModel model = (SpecifeProductDetails_ResponseModel) data;
                if (model != null) {

                    productdetails_LiveData.postValue(model);
                }else {
                    productdetails_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                productdetails_LiveData.postValue(null);
            }
        });
    }


    public void GetProductByCSS_Callback(Integer productId,String color,String size,int ProductCategoryId , int ProductSubCategoryId){
        productDetailRepository.GetProductByCSS_ApiCall(productId, color, size, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                GetSpecifeproductDetailsByCSS_ResponseModel model= (GetSpecifeproductDetailsByCSS_ResponseModel) data;



              //  SpecifeProductDetails_ResponseModel(String productSize, int productWeight, String productColor, int productDiscount, String productDiscountMode, int productQuantity, int productPrice, int productDiscountPrice, int product_VarientId, String productCategory, String productSubCategory, int productCategoryId, int productSubCategoryId, int productId, String productName, String productNumber, String HSNCODE, String productDescription, String productShortDescription, String productUsage, String productMoreDetails, boolean isactive, boolean delivery_Charges, boolean best_seller, boolean deal_of_the_day, int averageRating, boolean publishStatus, String productImage, String productImages1, String productImages2, String productImages3, String productImages4, String productImages5, String productImages6, String productImages7, String productImages8, String productImages9, String productImages10, int product_Id, ArrayList<String> sizelist, ArrayList<String> colorslist, ArrayList<String> colorsNamelist)

                if (model != null) {
                    SpecifeProductDetails_ResponseModel productDetails=new SpecifeProductDetails_ResponseModel(
                   model.getProductSize(),
                    model.getProductWeight(),
                            model.getProductColor(),
                            model.getProductDiscount(),
                            model.getProductDiscountMode(),
                            model.getProductQuantity(),
                            model.getProductPrice(),
                            model.getProductDiscountPrice(),
                            model.getProduct_VarientId(),
                            model.getProductCategory(),
                            model.getProductSubCategory(),
                            ProductCategoryId,    //Product Category ID
                            ProductSubCategoryId,     //Proudct Sub Category Id
                            model.getProduct_Id(),
                            model.getProductName(),
                            model.getProductNumber(),
                            model.getHSNCODE(),
                            model.getProductDescription(),
                            model.getProductShortDescription(),
                            model.getProductUsage(),
                            model.getProductMoreDetails(),
                            model.isIsactive(),
                            model.isDelivery_Charges(),
                            model.isBest_seller(),
                            model.isDeal_of_the_day(),
                            model.getAverageRating(),
                            model.isPublishStatus(),
                            model.getProductImage(),
                            model.getProductImages1(),
                            model.getProductImages2(),
                            model.getProductImages3(),
                            model.getProductImages4(),
                            model.getProductImages5(),
                            model.getProductImages6(),
                            model.getProductImages7(),
                            model.getProductImages8(),
                            model.getProductImages9(),
                            model.getProductImages10(),
                            model.getProduct_Id(),
                            model.getSizelist(),
                            model.getColorslist(),
                            model.getColorsNamelist()
                    );



                    productdetails_LiveData.postValue(productDetails);
                }else {
                    productdetails_LiveData.postValue(null);
                }
            }

            @Override
            public void onFailureResponse(String message) {
                productdetails_LiveData.postValue(null);
            }
        });
    }


    public void AddReview_Callback(String full_name,String title,String email,String message,String product_id,String rating_count){

        productDetailRepository.AddProductReviews_ApiCall(full_name, title, email, message, product_id, rating_count, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                AddReview_RequestModel model= (AddReview_RequestModel) data;
                if (model!=null){
                    addReviews_LiveData.postValue(model);
                }else {
                    addReviews_LiveData.postValue(null);
                }


            }

            @Override
            public void onFailureResponse(String message) {

                addReviews_LiveData.postValue(null);
            }
        });




    }



}
