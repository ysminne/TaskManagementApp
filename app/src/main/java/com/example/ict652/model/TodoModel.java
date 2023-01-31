package com.example.ict652.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TodoModel {
    private String id,title,description;

    private MemberModel memberModel;
    private ArrayList<MemberModel> memberModelArrayList;

    public TodoModel(String id, String title, String description, JSONArray memberJson) throws JSONException {
        this.id = id;
        this.title = title;
        this.description = description;
        memberModelArrayList = new ArrayList<>();
        if (memberJson != null) {
            for (int i=0;i<memberJson.length();i++){
                JSONObject jsonObject = memberJson.getJSONObject(i);
                memberModelArrayList.add(new MemberModel(memberJson.getJSONObject(i).getString("name"),memberJson.getJSONObject(i).getString("role")));
            }
        }
    }

    public void setMemberModel(MemberModel memberModel) {
        this.memberModel = memberModel;
    }

    public MemberModel getMemberModel() {
        if(memberModel == null){
            return new MemberModel("","");
        }
        return memberModel;
    }

    public ArrayList<MemberModel> getMemberModelArrayList() {
        return memberModelArrayList;
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
