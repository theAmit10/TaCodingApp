package com.example.tacoding.Model;

public class ContestModel {
    int platformImage, remainder;
    String contestTitle, contestDescription, startDate, endDate, url, in_24_hours, status;

    public ContestModel(int platformImage, int remainder, String contestTitle, String contestDescription, String startDate, String endDate) {
        this.platformImage = platformImage;
        this.remainder = remainder;
        this.contestTitle = contestTitle;
        this.contestDescription = contestDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ContestModel(String contestTitle, String contestDescription, String startDate, String endDate, String url, String in_24_hours, String status) {
        this.contestTitle = contestTitle;
        this.contestDescription = contestDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.url = url;
        this.in_24_hours = in_24_hours;
        this.status = status;
    }

    public ContestModel(String contestTitle, String contestDescription, String startDate, String endDate) {
        this.contestTitle = contestTitle;
        this.contestDescription = contestDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIn_24_hours() {
        return in_24_hours;
    }

    public void setIn_24_hours(String in_24_hours) {
        this.in_24_hours = in_24_hours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
