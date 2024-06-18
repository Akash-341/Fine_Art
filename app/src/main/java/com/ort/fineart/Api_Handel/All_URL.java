package com.ort.fineart.Api_Handel;

public class All_URL {


    //Producation
    // public final static String BASE_URL="http://www.thefinearthub.com/";
   // public final static String ImageBase_URL="http://www.thefinearthub.com";

    //uat
    public final static String BASE_URL = "http://www.thefinearthub.com/";
    public final static String ImageBase_URL="http://www.thefinearthub.com";

    public final static String imgURL = "http://www.thefinearthub.com";

    public final static String bottom_baneer_list = "banner_master_backend/get_published_bottombanner_list/";
    public final static String testimonial_list = "testimonial_master_backend/get_published_customer_testimonials_list";
    public final static String top_banner_list = "banner_master_backend/get_published_topbanner_list/";
    public final static String Product_List = "product_master_backend/get_published_product_list/";
    public final static String Category_List = "catlogue_master_backend/get_category_list/";
    public final static String PERSONAL_DETAILS = "Customer_Backend/getcustomer/";
    public final static String UPDATE_PERSONAL_DETAILS="Customer_Backend/update_customer_details/";
    public final static String deal_of_the_day = "product_master_backend/dealoftheday_product_list/";
    public final static String Best_Seller_List = "product_master_backend/bestseller_product_list/";
    public final static String viewProduct = "product_master_backend/get_varient_details/";


    /** Authentication
     *
     */
    public final static String login = "Customer_Backend/login/?&";
    public final static String register ="Customer_Backend/add_customer/";
    public final static String forgotPassword ="Customer_Backend/forget_password/";
    public final static String resetPassword ="Customer_Backend/change_customer_password/";





    /**
       My Cart
     */
    public final static String myCart = "Customer_Backend/user_cart_list/";
    public final static String changeMyCartProductQuantity="Customer_Backend/change_cart_product_quantity/";

    public final static String removeProductFromCart="Customer_Backend/delete_product_form_cart/";


    public final static String addInCart="Customer_Backend/add_item_to_cart/";


    /**
    WishList
     */
    public final static String getWishList = "Customer_Backend/user_wish_list/";
    public final static String addRemoveWishListProduct="Customer_Backend/add_and_remove_item_to_wishlist/";

    /**
     * Address
     */

    public final static String getAddressList = "Customer_Backend/get_customer_address/";
    public final static String addNewAddress = "Customer_Backend/addnew_customer_address/";
    public final static String updateAddress = "Customer_Backend/update_customer_address/";
    public final static String getAddressById = "Customer_Backend/get_customer_address_by_id/";
    public final static String deleteAddress="Customer_Backend/delete_customer_address/";


    /**
     * Hub
     */

    public final static String hubCategoryList="catlogue_master_backend/get_category_list_api/";
    public final static String hubProductList="product_master_backend/get_varients_by_category/";
    public final static String subCategoryList="catlogue_master_backend/get_subcategory_list_by_main_category/";


    /**
     * Specify Product Details
     */


     public final static String specifeProductDetails="product_master_backend/get_varient_details/";
     public final static String getProductByCSS="product_master_backend/get_varient_details_by_CSI/";

    public final static String addProductReview="Customer_Backend/add_review/";






    /**
     * Order History
     */


    public final static String getOrderHistoryList="/Customer_Backend/get_customer_order_history/";
    public final static String orderHistoryDetails="/Customer_Backend/get_customer_order_history_by_orderId/";


    /**
     * Checkout
     */
    public final static String quickOrder="/Customer_Backend/quick_buy_product/";
    public final static String getOrderDetails="/Customer_Backend/customer_order_details/";
    public final static String applyCoupon="/Customer_Backend/apply_couponcode/";
    public final static String quickBuy="Customer_Backend/quick_buy_product/";
    public final static String placeOrder="order_master_backend/place_customer_order/";

    public final static String getCouponList="Customer_Backend/get_publish_coupons/";
























































































































































































}
