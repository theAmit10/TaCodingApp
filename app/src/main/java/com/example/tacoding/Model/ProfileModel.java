package com.example.tacoding.Model;

public class ProfileModel {
    int profilePlatformImage;
    String profileCodingPlatformName,profileProblemSolve,profileRating;

    public ProfileModel(int profilePlatformImage, String profileCodingPlatformName, String profileProblemSolve, String profileRating) {

        this.profilePlatformImage = profilePlatformImage;
        this.profileCodingPlatformName = profileCodingPlatformName;
        this.profileProblemSolve = profileProblemSolve;
        this.profileRating = profileRating;

    }




    public int getProfilePlatformImage() {
        return profilePlatformImage;
    }

    public void setProfilePlatformImage(int profilePlatformImage) {
        this.profilePlatformImage = profilePlatformImage;
    }

    public String getProfileCodingPlatformName() {
        return profileCodingPlatformName;
    }

    public void setProfileCodingPlatformName(String profileCodingPlatformName) {
        this.profileCodingPlatformName = profileCodingPlatformName;
    }

    public String getProfileProblemSolve() {
        return profileProblemSolve;
    }

    public void setProfileProblemSolve(String profileProblemSolve) {
        this.profileProblemSolve = profileProblemSolve;
    }

    public String getProfileRating() {
        return profileRating;
    }

    public void setProfileRating(String profileRating) {
        this.profileRating = profileRating;
    }
}
