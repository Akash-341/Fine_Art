package com.ort.fineart.Model.Response_Model.Hub;

public class Hub_CategoryList_ResponseModel {
    private int id;
    private String CategoryName;
    private String Description;
    private boolean isactive;

    public Hub_CategoryList_ResponseModel(int id, String categoryName, String description, boolean isactive) {
        this.id = id;
        CategoryName = categoryName;
        Description = description;
        this.isactive = isactive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
