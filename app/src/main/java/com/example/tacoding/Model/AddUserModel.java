package com.example.tacoding.Model;

public class AddUserModel {
    int addprofileUserImage;
    String profileUserName;
    String userPlatform;

    public AddUserModel(int addprofileUserImage, String profileUserName,String userPlatform) {
        this.addprofileUserImage = addprofileUserImage;
        this.profileUserName = profileUserName;
        this.userPlatform = userPlatform;
    }

    public String getUserPlatform() {
        return userPlatform;
    }

    public void setUserPlatform(String userPlatform) {
        this.userPlatform = userPlatform;
    }

    public int getAddprofileUserImage() {
        return addprofileUserImage;
    }

    public void setAddprofileUserImage(int addprofileUserImage) {
        this.addprofileUserImage = addprofileUserImage;
    }

    public String getProfileUserName() {
        return profileUserName;
    }

    public void setProfileUserName(String profileUserName) {
        this.profileUserName = profileUserName;
    }
}
