package com.example.tacoding.Model;

public class TopCoderModel {
    int topCoderImage;
    String username;
    String title;
    String codingPlatform;

    public TopCoderModel(int topCoderImage, String username, String title, String codingPlatform) {
        this.topCoderImage = topCoderImage;
        this.username = username;
        this.title = title;
        this.codingPlatform = codingPlatform;
    }

    public int getTopCoderImage() {
        return topCoderImage;
    }

    public void setTopCoderImage(int topCoderImage) {
        this.topCoderImage = topCoderImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCodingPlatform() {
        return codingPlatform;
    }

    public void setCodingPlatform(String codingPlatform) {
        this.codingPlatform = codingPlatform;
    }
}
