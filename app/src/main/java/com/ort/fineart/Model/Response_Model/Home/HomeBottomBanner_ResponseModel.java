
package com.ort.fineart.Model.Response_Model.Home;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class HomeBottomBanner_ResponseModel {

    @SerializedName("id")
    private Integer id;
    @SerializedName("BannerName")
    private String bannerName;
    @SerializedName("ButtonName")
    private String buttonName;
    @SerializedName("ButtonLink")
    private String buttonLink;
    @SerializedName("BannerHeading")
    private String bannerHeading;
    @SerializedName("BannerText")
    private String bannerText;
    @SerializedName("isactive")
    private Boolean isactive;
    @SerializedName("Status")
    private Boolean status;
    @SerializedName("BannerImage")
    private String bannerImage;
    @SerializedName("BannerInsideImage")
    private String bannerInsideImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonLink() {
        return buttonLink;
    }

    public void setButtonLink(String buttonLink) {
        this.buttonLink = buttonLink;
    }

    public String getBannerHeading() {
        return bannerHeading;
    }

    public void setBannerHeading(String bannerHeading) {
        this.bannerHeading = bannerHeading;
    }

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
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

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getBannerInsideImage() {
        return bannerInsideImage;
    }

    public void setBannerInsideImage(String bannerInsideImage) {
        this.bannerInsideImage = bannerInsideImage;
    }
}
