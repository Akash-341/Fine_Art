package com.ort.fineart.Model.Response_Model.WishList;

public class AddRemove_WishListProduct_ResponseModel {

    private Object id;
    private Object customer;
    private Object isactive;
    private Object varient;

    public AddRemove_WishListProduct_ResponseModel(Object id, Object customer, Object isactive, Object varient) {
        this.id = id;
        this.customer = customer;
        this.isactive = isactive;
        this.varient = varient;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Object customer) {
        this.customer = customer;
    }

    public Object getIsactive() {
        return isactive;
    }

    public void setIsactive(Object isactive) {
        this.isactive = isactive;
    }

    public Object getVarient() {
        return varient;
    }

    public void setVarient(Object varient) {
        this.varient = varient;
    }
}
