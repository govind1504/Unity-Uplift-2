package com.example.unityuplift2.Model;

public class ImgModel {

    private String ImageUrl,Community,PostDescription,PostTitleImage;
    public ImgModel(){

    }


    public ImgModel(String ImageUrl, String Community,String PostDescription,String PostTitleImage){
        this.ImageUrl=ImageUrl;
        this.Community = Community;
        this.PostDescription = PostDescription;
        this.PostTitleImage = PostTitleImage;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getCommunity() {
        return Community;
    }

    public String getPostTitleImage() {
        return PostTitleImage;
    }

    public void setPostTitleImage(String postTitleImage) {
        PostTitleImage = postTitleImage;
    }

    public void setCommunity(String community) {
        Community = community;
    }

    public String getPostDescription() {
        return PostDescription;
    }

    public void setPostDescription(String PostDescription) {
        PostDescription = PostDescription;
    }
}
