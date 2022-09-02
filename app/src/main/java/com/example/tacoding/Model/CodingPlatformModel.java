package com.example.tacoding.Model;

public class CodingPlatformModel {
    int codingPlatformImage;
    String codingPlatformName;


    public CodingPlatformModel(int codingPlatformImage, String codingPlatformName) {
        this.codingPlatformImage = codingPlatformImage;
        this.codingPlatformName = codingPlatformName;
    }

    public CodingPlatformModel(int codingPlatformImage) {
        this.codingPlatformImage = codingPlatformImage;
    }

    public String getCodingPlatformName() {
        return codingPlatformName;
    }

    public void setCodingPlatformName(String codingPlatformName) {
        this.codingPlatformName = codingPlatformName;
    }

    public int getCodingPlatformImage() {
        return codingPlatformImage;
    }

    public void setCodingPlatformImage(int codingPlatformImage) {
        this.codingPlatformImage = codingPlatformImage;
    }
}
