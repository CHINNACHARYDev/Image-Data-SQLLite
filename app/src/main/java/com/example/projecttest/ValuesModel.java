package com.example.projecttest;

public class ValuesModel {

    private String lableId;
    private String lable;

    public ValuesModel(String lableId, String lable) {
        this.lableId = lableId;
        this.lable = lable;
    }

    public String getLableId() {
        return lableId;
    }

    public void setLableId(String lableId) {
        this.lableId = lableId;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return lable;
    }
}
