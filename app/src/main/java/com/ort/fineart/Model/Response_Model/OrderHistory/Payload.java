package com.ort.fineart.Model.Response_Model.OrderHistory;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payload {

    @SerializedName("id")

    private Integer id;
    @SerializedName("OrderId")

    private String orderId;
    @SerializedName("OrderDate")
    private String orderDate;
    @SerializedName("ShipingAddress")
    private String shipingAddress;
    @SerializedName("TotalPrice")
    private String totalPrice;
    @SerializedName("PaymentMode")
    private String paymentMode;
    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("Customer")
    private String customer;
    @SerializedName("PhoneNumber")
    private String phoneNumber;
    @SerializedName("TotalDiscount")
    private String totalDiscount;
    @SerializedName("GiftCharges")
    private Integer giftCharges;
    @SerializedName("DeliveryCharges")
    private Integer deliveryCharges;
    @SerializedName("OrderPlaced")
    private Boolean orderPlaced;
    @SerializedName("OrderDispatched")
    private Boolean orderDispatched;
    @SerializedName("OrderDelivered")
    private Boolean orderDelivered;
    @SerializedName("OrderCancelled")
    private Boolean orderCancelled;
    @SerializedName("isactive")
    private Boolean isactive;
    @SerializedName("Courierid")
    private String courierid;
    @SerializedName("CourierLink")
    private String courierLink;
    @SerializedName("CouponDiscount")
    private Integer couponDiscount;
    @SerializedName("products")
    private List<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipingAddress() {
        return shipingAddress;
    }

    public void setShipingAddress(String shipingAddress) {
        this.shipingAddress = shipingAddress;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Integer getGiftCharges() {
        return giftCharges;
    }

    public void setGiftCharges(Integer giftCharges) {
        this.giftCharges = giftCharges;
    }

    public Integer getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(Integer deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public Boolean getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(Boolean orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public Boolean getOrderDispatched() {
        return orderDispatched;
    }

    public void setOrderDispatched(Boolean orderDispatched) {
        this.orderDispatched = orderDispatched;
    }

    public Boolean getOrderDelivered() {
        return orderDelivered;
    }

    public void setOrderDelivered(Boolean orderDelivered) {
        this.orderDelivered = orderDelivered;
    }

    public Boolean getOrderCancelled() {
        return orderCancelled;
    }

    public void setOrderCancelled(Boolean orderCancelled) {
        this.orderCancelled = orderCancelled;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getCourierid() {
        return courierid;
    }

    public void setCourierid(String courierid) {
        this.courierid = courierid;
    }

    public String getCourierLink() {
        return courierLink;
    }

    public void setCourierLink(String courierLink) {
        this.courierLink = courierLink;
    }

    public Integer getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Integer couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
