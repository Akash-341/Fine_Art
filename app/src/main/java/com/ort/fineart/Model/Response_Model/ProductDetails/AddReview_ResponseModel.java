package com.ort.fineart.Model.Response_Model.ProductDetails;

public class AddReview_ResponseModel {
    private int id;
    private String RatingCount = null;
    private String FullName;
    private String Email;
    private String Title;
    private String Message;
    private String Date;
    private boolean isactive;
    private boolean Aproved;
    private int ProductId;

    public AddReview_ResponseModel(int id, String ratingCount, String fullName, String email, String title, String message, String date, boolean isactive, boolean aproved, int productId) {
        this.id = id;
        RatingCount = ratingCount;
        FullName = fullName;
        Email = email;
        Title = title;
        Message = message;
        Date = date;
        this.isactive = isactive;
        Aproved = aproved;
        ProductId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRatingCount() {
        return RatingCount;
    }

    public void setRatingCount(String ratingCount) {
        RatingCount = ratingCount;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isAproved() {
        return Aproved;
    }

    public void setAproved(boolean aproved) {
        Aproved = aproved;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }
}
