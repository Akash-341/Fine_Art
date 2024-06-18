package com.ort.fineart.Model.Response_Model.Checkout;

public class PlaceOrder_ResponseModel {

    private int id;
    private int OrderId;
    private String OrderDate;
    private String ShipingAddress;
    private int TotalPrice;
    private String PaymentMode;
    private String FirstName;
    private String LastName;
    private String Customer;
    private String PhoneNumber;
    private int TotalDiscount;
    private int GiftCharges;
    private int DeliveryCharges;
    private boolean OrderPlaced;
    private boolean OrderDispatched;
    private boolean OrderDelivered;
    private boolean OrderCancelled;
    private boolean isactive;
    private String Courierid;
    private String CourierLink;
    private int CouponDiscount;

    public PlaceOrder_ResponseModel(int id, int orderId, String orderDate, String shipingAddress, int totalPrice, String paymentMode, String firstName, String lastName, String customer, String phoneNumber, int totalDiscount, int giftCharges, int deliveryCharges, boolean orderPlaced, boolean orderDispatched, boolean orderDelivered, boolean orderCancelled, boolean isactive, String courierid, String courierLink, int couponDiscount) {
        this.id = id;
        OrderId = orderId;
        OrderDate = orderDate;
        ShipingAddress = shipingAddress;
        TotalPrice = totalPrice;
        PaymentMode = paymentMode;
        FirstName = firstName;
        LastName = lastName;
        Customer = customer;
        PhoneNumber = phoneNumber;
        TotalDiscount = totalDiscount;
        GiftCharges = giftCharges;
        DeliveryCharges = deliveryCharges;
        OrderPlaced = orderPlaced;
        OrderDispatched = orderDispatched;
        OrderDelivered = orderDelivered;
        OrderCancelled = orderCancelled;
        this.isactive = isactive;
        Courierid = courierid;
        CourierLink = courierLink;
        CouponDiscount = couponDiscount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getShipingAddress() {
        return ShipingAddress;
    }

    public void setShipingAddress(String shipingAddress) {
        ShipingAddress = shipingAddress;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getPaymentMode() {
        return PaymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        PaymentMode = paymentMode;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public int getGiftCharges() {
        return GiftCharges;
    }

    public void setGiftCharges(int giftCharges) {
        GiftCharges = giftCharges;
    }

    public int getDeliveryCharges() {
        return DeliveryCharges;
    }

    public void setDeliveryCharges(int deliveryCharges) {
        DeliveryCharges = deliveryCharges;
    }

    public boolean isOrderPlaced() {
        return OrderPlaced;
    }

    public void setOrderPlaced(boolean orderPlaced) {
        OrderPlaced = orderPlaced;
    }

    public boolean isOrderDispatched() {
        return OrderDispatched;
    }

    public void setOrderDispatched(boolean orderDispatched) {
        OrderDispatched = orderDispatched;
    }

    public boolean isOrderDelivered() {
        return OrderDelivered;
    }

    public void setOrderDelivered(boolean orderDelivered) {
        OrderDelivered = orderDelivered;
    }

    public boolean isOrderCancelled() {
        return OrderCancelled;
    }

    public void setOrderCancelled(boolean orderCancelled) {
        OrderCancelled = orderCancelled;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public String getCourierid() {
        return Courierid;
    }

    public void setCourierid(String courierid) {
        Courierid = courierid;
    }

    public String getCourierLink() {
        return CourierLink;
    }

    public void setCourierLink(String courierLink) {
        CourierLink = courierLink;
    }

    public int getCouponDiscount() {
        return CouponDiscount;
    }

    public void setCouponDiscount(int couponDiscount) {
        CouponDiscount = couponDiscount;
    }
}
