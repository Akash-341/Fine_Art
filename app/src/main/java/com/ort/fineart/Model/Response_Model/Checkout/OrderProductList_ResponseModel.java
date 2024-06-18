package com.ort.fineart.Model.Response_Model.Checkout;

public class OrderProductList_ResponseModel {

    private String varient_id;
    private String varient_quantity;
    private String VarientProductId;

    public OrderProductList_ResponseModel(String varient_id, String varient_quantity, String varientProductId) {
        this.varient_id = varient_id;
        this.varient_quantity = varient_quantity;
        VarientProductId = varientProductId;
    }

    public String getVarient_id() {
        return varient_id;
    }

    public void setVarient_id(String varient_id) {
        this.varient_id = varient_id;
    }

    public String getVarient_quantity() {
        return varient_quantity;
    }

    public void setVarient_quantity(String varient_quantity) {
        this.varient_quantity = varient_quantity;
    }

    public String getVarientProductId() {
        return VarientProductId;
    }

    public void setVarientProductId(String varientProductId) {
        VarientProductId = varientProductId;
    }
}
