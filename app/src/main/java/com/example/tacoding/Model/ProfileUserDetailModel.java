package com.example.tacoding.Model;

public class ProfileUserDetailModel {
    int profileUserImage;
    String profileUserName;

    public ProfileUserDetailModel(int profileUserImage, String profileUserName) {
        this.profileUserImage = profileUserImage;
        this.profileUserName = profileUserName;
    }

    public int getProfileUserImage() {
        return profileUserImage;
    }

    public void setProfileUserImage(int profileUserImage) {
        this.profileUserImage = profileUserImage;
    }

    public String getProfileUserName() {
        return profileUserName;
    }

    public void setProfileUserName(String profileUserName) {
        this.profileUserName = profileUserName;
    }
}
