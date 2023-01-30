package com.example.ict652.model;

public class MemberModel {
    private String name, role;


    public MemberModel(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName(){
        return name ;
    }

    public String getRole(){
        return role;
    }
}
