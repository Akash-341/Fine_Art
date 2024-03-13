package com.ort.fineart.Model.Response_Model;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {

    @SerializedName("id")
    private Integer id;
    @SerializedName("CategoryName")
    private String categoryName;
    @SerializedName("Description")
    private String description;
    @SerializedName("isactive")
    private Boolean isactive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

}
