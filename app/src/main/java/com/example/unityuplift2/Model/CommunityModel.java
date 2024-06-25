package com.example.unityuplift2.Model;

import com.example.unityuplift2.ui.Community;

public class CommunityModel {
    String name,description,image;


    public CommunityModel(String name,String description,String image){
        this.name = name;
        this.description = description;
        this.image = image;
    }
    public CommunityModel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
