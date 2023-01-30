package com.example.ict652.model;

import java.util.ArrayList;

public class TodoModel {
    private String id,title,description;

    public MemberModel memberModel;

    public TodoModel(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setMemberModel(MemberModel memberModel) {
        this.memberModel = memberModel;
    }

    public MemberModel getMemberModel() {
        return memberModel;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
