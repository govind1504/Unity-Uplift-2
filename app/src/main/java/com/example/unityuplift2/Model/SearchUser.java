package com.example.unityuplift2.Model;

public class SearchUser {
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

    public String getimage() {
        return image;
    }

    public void setImageUrl(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    private String id,name,image,email,bio;
    public SearchUser(String id,String name,String image,String bio){
        this.id = id;
        this.name = name;
        this.image = image;
        this.bio = bio;
    }
    public SearchUser(){

    }

}
