package com.example.unityuplift2.Model;

public class imageModel {

    private String imageUrl,community,postDescription,postTitleImage;



    public imageModel(String imageUrl, String community, String postDescription,String postTitleImage) {
        this.imageUrl = imageUrl;
        this.community = community;
        this.postDescription = postDescription;
        this.postTitleImage = postTitleImage;
    }



    public imageModel(){

    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostTitleImage() {
        return postTitleImage;
    }

    public void setPostTitleImage(String postTitleImage) {
        this.postTitleImage = postTitleImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

}
