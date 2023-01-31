package com.example.ict652.model;

import org.json.JSONException;
import org.json.JSONObject;

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

    public JSONObject toJSON() throws JSONException {

        JSONObject jo = new JSONObject();
        jo.put("name", this.name);
        jo.put("role", this.role);

        return jo;
    }
}
