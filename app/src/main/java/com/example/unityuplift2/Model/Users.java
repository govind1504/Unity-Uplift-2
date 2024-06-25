package com.example.unityuplift2.Model;

public class Users {
    String name,email,pass;
    public  Users(String name,String email, String pass){
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
    public Users(String name){
        this.name = name;
    }

    public Users(){}

    public String getName(){
        return  name;
    }
    public  String setName(){
        return name;
    }

    public String getEmail(){
        return  email;
    }
    public String setEmail(){
        return email;
    }

    public String getPass(){
        return pass;
    }
    public String setPass(){
        return pass;
    }

}
