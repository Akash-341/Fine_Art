package com.ort.fineart.Model.Response_Model.ProductDetails;

import java.util.ArrayList;

public class SpecifeProductDetails_ResponseModel {
    public String ProductSize;
    public int ProductWeight;
    public String ProductColor;
    public int ProductDiscount;
    public String ProductDiscountMode;
    public int ProductQuantity;
    public int ProductPrice;
    public int ProductDiscountPrice;
    public int Product_VarientId;
    public String ProductCategory;
    public String ProductSubCategory;
    public int ProductCategoryId;    //Extra
    public int ProductSubCategoryId; //Extra
    public int ProductId;  //Extra
    public String ProductName;
    public String ProductNumber;
    public String HSNCODE;
    public String ProductDescription;
    public String ProductShortDescription;
    public String ProductUsage;
    public String ProductMoreDetails;
    public boolean isactive;
    public boolean Delivery_Charges;
    public boolean Best_seller;
    public boolean Deal_of_the_day;
    public int AverageRating;
    public boolean PublishStatus;
    public String ProductImage;
    public String ProductImages1;
    public String ProductImages2 ;
    public String ProductImages3 ;
    public String ProductImages4 ;
    public String ProductImages5 ;
    public String ProductImages6 ;
    public String ProductImages7 ;
    public String ProductImages8 ;
    public String ProductImages9 ;
    public String ProductImages10;
    public int Product_Id;
    ArrayList <String> Sizelist = new ArrayList ();
    ArrayList < String > Colorslist = new ArrayList ();
    ArrayList < String > ColorsNamelist = new ArrayList  ();

    public SpecifeProductDetails_ResponseModel(String productSize, int productWeight, String productColor, int productDiscount, String productDiscountMode, int productQuantity, int productPrice, int productDiscountPrice, int product_VarientId, String productCategory, String productSubCategory, int productCategoryId, int productSubCategoryId, int productId, String productName, String productNumber, String HSNCODE, String productDescription, String productShortDescription, String productUsage, String productMoreDetails, boolean isactive, boolean delivery_Charges, boolean best_seller, boolean deal_of_the_day, int averageRating, boolean publishStatus, String productImage, String productImages1, String productImages2, String productImages3, String productImages4, String productImages5, String productImages6, String productImages7, String productImages8, String productImages9, String productImages10, int product_Id, ArrayList<String> sizelist, ArrayList<String> colorslist, ArrayList<String> colorsNamelist) {
        ProductSize = productSize;
        ProductWeight = productWeight;
        ProductColor = productColor;
        ProductDiscount = productDiscount;
        ProductDiscountMode = productDiscountMode;
        ProductQuantity = productQuantity;
        ProductPrice = productPrice;
        ProductDiscountPrice = productDiscountPrice;
        Product_VarientId = product_VarientId;
        ProductCategory = productCategory;
        ProductSubCategory = productSubCategory;
        ProductCategoryId = productCategoryId;
        ProductSubCategoryId = productSubCategoryId;
        ProductId = productId;
        ProductName = productName;
        ProductNumber = productNumber;
        this.HSNCODE = HSNCODE;
        ProductDescription = productDescription;
        ProductShortDescription = productShortDescription;
        ProductUsage = productUsage;
        ProductMoreDetails = productMoreDetails;
        this.isactive = isactive;
        Delivery_Charges = delivery_Charges;
        Best_seller = best_seller;
        Deal_of_the_day = deal_of_the_day;
        AverageRating = averageRating;
        PublishStatus = publishStatus;
        ProductImage = productImage;
        ProductImages1 = productImages1;
        ProductImages2 = productImages2;
        ProductImages3 = productImages3;
        ProductImages4 = productImages4;
        ProductImages5 = productImages5;
        ProductImages6 = productImages6;
        ProductImages7 = productImages7;
        ProductImages8 = productImages8;
        ProductImages9 = productImages9;
        ProductImages10 = productImages10;
        Product_Id = product_Id;
        Sizelist = sizelist;
        Colorslist = colorslist;
        ColorsNamelist = colorsNamelist;
    }

    public String getProductSize() {
        return ProductSize;
    }

    public void setProductSize(String productSize) {
        ProductSize = productSize;
    }

    public int getProductWeight() {
        return ProductWeight;
    }

    public void setProductWeight(int productWeight) {
        ProductWeight = productWeight;
    }

    public String getProductColor() {
        return ProductColor;
    }

    public void setProductColor(String productColor) {
        ProductColor = productColor;
    }

    public int getProductDiscount() {
        return ProductDiscount;
    }

    public void setProductDiscount(int productDiscount) {
        ProductDiscount = productDiscount;
    }

    public String getProductDiscountMode() {
        return ProductDiscountMode;
    }

    public void setProductDiscountMode(String productDiscountMode) {
        ProductDiscountMode = productDiscountMode;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public int getProductDiscountPrice() {
        return ProductDiscountPrice;
    }

    public void setProductDiscountPrice(int productDiscountPrice) {
        ProductDiscountPrice = productDiscountPrice;
    }

    public int getProduct_VarientId() {
        return Product_VarientId;
    }

    public void setProduct_VarientId(int product_VarientId) {
        Product_VarientId = product_VarientId;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getProductSubCategory() {
        return ProductSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        ProductSubCategory = productSubCategory;
    }

    public int getProductCategoryId() {
        return ProductCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        ProductCategoryId = productCategoryId;
    }

    public int getProductSubCategoryId() {
        return ProductSubCategoryId;
    }

    public void setProductSubCategoryId(int productSubCategoryId) {
        ProductSubCategoryId = productSubCategoryId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(String productNumber) {
        ProductNumber = productNumber;
    }

    public String getHSNCODE() {
        return HSNCODE;
    }

    public void setHSNCODE(String HSNCODE) {
        this.HSNCODE = HSNCODE;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getProductShortDescription() {
        return ProductShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        ProductShortDescription = productShortDescription;
    }

    public String getProductUsage() {
        return ProductUsage;
    }

    public void setProductUsage(String productUsage) {
        ProductUsage = productUsage;
    }

    public String getProductMoreDetails() {
        return ProductMoreDetails;
    }

    public void setProductMoreDetails(String productMoreDetails) {
        ProductMoreDetails = productMoreDetails;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isDelivery_Charges() {
        return Delivery_Charges;
    }

    public void setDelivery_Charges(boolean delivery_Charges) {
        Delivery_Charges = delivery_Charges;
    }

    public boolean isBest_seller() {
        return Best_seller;
    }

    public void setBest_seller(boolean best_seller) {
        Best_seller = best_seller;
    }

    public boolean isDeal_of_the_day() {
        return Deal_of_the_day;
    }

    public void setDeal_of_the_day(boolean deal_of_the_day) {
        Deal_of_the_day = deal_of_the_day;
    }

    public int getAverageRating() {
        return AverageRating;
    }

    public void setAverageRating(int averageRating) {
        AverageRating = averageRating;
    }

    public boolean isPublishStatus() {
        return PublishStatus;
    }

    public void setPublishStatus(boolean publishStatus) {
        PublishStatus = publishStatus;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductImages1() {
        return ProductImages1;
    }

    public void setProductImages1(String productImages1) {
        ProductImages1 = productImages1;
    }

    public String getProductImages2() {
        return ProductImages2;
    }

    public void setProductImages2(String productImages2) {
        ProductImages2 = productImages2;
    }

    public String getProductImages3() {
        return ProductImages3;
    }

    public void setProductImages3(String productImages3) {
        ProductImages3 = productImages3;
    }

    public String getProductImages4() {
        return ProductImages4;
    }

    public void setProductImages4(String productImages4) {
        ProductImages4 = productImages4;
    }

    public String getProductImages5() {
        return ProductImages5;
    }

    public void setProductImages5(String productImages5) {
        ProductImages5 = productImages5;
    }

    public String getProductImages6() {
        return ProductImages6;
    }

    public void setProductImages6(String productImages6) {
        ProductImages6 = productImages6;
    }

    public String getProductImages7() {
        return ProductImages7;
    }

    public void setProductImages7(String productImages7) {
        ProductImages7 = productImages7;
    }

    public String getProductImages8() {
        return ProductImages8;
    }

    public void setProductImages8(String productImages8) {
        ProductImages8 = productImages8;
    }

    public String getProductImages9() {
        return ProductImages9;
    }

    public void setProductImages9(String productImages9) {
        ProductImages9 = productImages9;
    }

    public String getProductImages10() {
        return ProductImages10;
    }

    public void setProductImages10(String productImages10) {
        ProductImages10 = productImages10;
    }

    public int getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(int product_Id) {
        Product_Id = product_Id;
    }

    public ArrayList<String> getSizelist() {
        return Sizelist;
    }

    public void setSizelist(ArrayList<String> sizelist) {
        Sizelist = sizelist;
    }

    public ArrayList<String> getColorslist() {
        return Colorslist;
    }

    public void setColorslist(ArrayList<String> colorslist) {
        Colorslist = colorslist;
    }

    public ArrayList<String> getColorsNamelist() {
        return ColorsNamelist;
    }

    public void setColorsNamelist(ArrayList<String> colorsNamelist) {
        ColorsNamelist = colorsNamelist;
    }
}
