package com.ort.fineart.Model.Response_Model;
import com.google.gson.annotations.SerializedName;


public class TestimonialModel_ResponseModel {

    public TestimonialModel_ResponseModel(Integer id, String title, String name, String subName, String description, Boolean isactive, Boolean status, String image) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.subName = subName;
        this.description = description;
        this.isactive = isactive;
        this.status = status;
        this.image = image;
    }

    @SerializedName("id")
    private Integer id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Name")
    private String name;
    @SerializedName("SubName")
    private String subName;
    @SerializedName("Description")
    private String description;
    @SerializedName("isactive")
    private Boolean isactive;
    @SerializedName("Status")
    private Boolean status;
    @SerializedName("Image")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}