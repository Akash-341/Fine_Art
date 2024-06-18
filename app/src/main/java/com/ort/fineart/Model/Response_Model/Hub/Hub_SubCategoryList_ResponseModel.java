package com.ort.fineart.Model.Response_Model.Hub;

public class Hub_SubCategoryList_ResponseModel {

    private int id;
    private String Category;
    private String SubCategoryName;
    private String Description;
    private boolean isactive;

    public Hub_SubCategoryList_ResponseModel(int id, String category, String subCategoryName, String description, boolean isactive) {
        this.id = id;
        Category = category;
        SubCategoryName = subCategoryName;
        Description = description;
        this.isactive = isactive;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSubCategoryName() {
        return SubCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        SubCategoryName = subCategoryName;
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
