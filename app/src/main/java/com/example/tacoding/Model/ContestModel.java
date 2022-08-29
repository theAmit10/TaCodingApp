package com.example.tacoding.Model;

public class ContestModel {
    int platformImage, remainder;
    String contestTitle, contestDescription, startDate, endDate;

    public ContestModel(int platformImage, int remainder, String contestTitle, String contestDescription, String startDate, String endDate) {
        this.platformImage = platformImage;
        this.remainder = remainder;
        this.contestTitle = contestTitle;
        this.contestDescription = contestDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ContestModel(String contestTitle, String contestDescription, String startDate, String endDate) {
        this.contestTitle = contestTitle;
        this.contestDescription = contestDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getPlatformImage() {
        return platformImage;
    }

    public void setPlatformImage(int platformImage) {
        this.platformImage = platformImage;
    }

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public String getContestTitle() {
        return contestTitle;
    }

    public void setContestTitle(String contestTitle) {
        this.contestTitle = contestTitle;
    }

    public String getContestDescription() {
        return contestDescription;
    }

    public void setContestDescription(String contestDescription) {
        this.contestDescription = contestDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
