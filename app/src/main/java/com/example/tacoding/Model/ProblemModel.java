package com.example.tacoding.Model;

public class ProblemModel {
    int problemPlatformImage;
    String problemContestName, problemTitle;

    public ProblemModel(int problemPlatformImage, String problemContestName, String problemTitle) {
        this.problemPlatformImage = problemPlatformImage;
        this.problemContestName = problemContestName;
        this.problemTitle = problemTitle;
    }

    public int getProblemPlatformImage() {
        return problemPlatformImage;
    }

    public void setProblemPlatformImage(int problemPlatformImage) {
        this.problemPlatformImage = problemPlatformImage;
    }

    public String getProblemContestName() {
        return problemContestName;
    }

    public void setProblemContestName(String problemContestName) {
        this.problemContestName = problemContestName;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }
}

