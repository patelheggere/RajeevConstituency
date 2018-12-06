package com.patelheggere.rajeevconstituency.model;

public class NewsModel {
    private String id;
    private String name;
    private String image;
    private String profilePic;
    private long timeStamp;
    private String url2;
    private String LikeCount;
    private String commentCount;
    private String status;

    public NewsModel() {
    }

    public NewsModel(String id, String name, String image, String profilePic, long timeStamp, String url2, String likeCount, String commentCount, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.profilePic = profilePic;
        this.timeStamp = timeStamp;
        this.url2 = url2;
        LikeCount = likeCount;
        this.commentCount = commentCount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getLikeCount() {
        return LikeCount;
    }

    public void setLikeCount(String likeCount) {
        LikeCount = likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
