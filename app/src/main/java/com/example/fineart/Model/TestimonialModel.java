package com.example.fineart.Model;

public class TestimonialModel {
    private String quote;
    private String name;
    private String designation;
    private String imageUrl;

    public TestimonialModel(String quote, String name, String designation, String imageUrl) {
        this.quote = quote;
        this.name = name;
        this.designation = designation;
        this.imageUrl = imageUrl;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
