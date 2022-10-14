package com.example.tacoding.Model;

public class ProfileModel {
    String profileImage;
    String name;
    String rating;
    String rank;
    String maxRating;
    String handle;
    String lastOnlineTimeSeconds;

    public ProfileModel(String profileImage, String name, String rating, String rank, String maxRating, String handle, String lastOnlineTimeSeconds) {
        this.profileImage = profileImage;
        this.name = name;
        this.rating = rating;
        this.rank = rank;
        this.maxRating = maxRating;
        this.handle = handle;
        this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(String maxRating) {
        this.maxRating = maxRating;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getLastOnlineTimeSeconds() {
        return lastOnlineTimeSeconds;
    }

    public void setLastOnlineTimeSeconds(String lastOnlineTimeSeconds) {
        this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
    }
}
