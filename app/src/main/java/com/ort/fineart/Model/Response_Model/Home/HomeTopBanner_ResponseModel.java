package com.ort.fineart.Model.Response_Model.Home;

public class HomeTopBanner_ResponseModel {
    private int id;
    private String BannerName;
    private String ButtonName;
    private String ButtonLink;
    private String BannerHeading;
    private String BannerText;
    private boolean isactive;
    private boolean Status;
    private String BannerImage;

    public HomeTopBanner_ResponseModel(int id, String bannerName, String buttonName, String buttonLink, String bannerHeading, String bannerText, boolean isactive, boolean status, String bannerImage) {
        this.id = id;
        BannerName = bannerName;
        ButtonName = buttonName;
        ButtonLink = buttonLink;
        BannerHeading = bannerHeading;
        BannerText = bannerText;
        this.isactive = isactive;
        Status = status;
        BannerImage = bannerImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBannerName() {
        return BannerName;
    }

    public void setBannerName(String bannerName) {
        BannerName = bannerName;
    }

    public String getButtonName() {
        return ButtonName;
    }

    public void setButtonName(String buttonName) {
        ButtonName = buttonName;
    }

    public String getButtonLink() {
        return ButtonLink;
    }

    public void setButtonLink(String buttonLink) {
        ButtonLink = buttonLink;
    }

    public String getBannerHeading() {
        return BannerHeading;
    }

    public void setBannerHeading(String bannerHeading) {
        BannerHeading = bannerHeading;
    }

    public String getBannerText() {
        return BannerText;
    }

    public void setBannerText(String bannerText) {
        BannerText = bannerText;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getBannerImage() {
        return BannerImage;
    }

    public void setBannerImage(String bannerImage) {
        BannerImage = bannerImage;
    }
}
