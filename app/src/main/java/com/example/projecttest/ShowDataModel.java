package com.example.projecttest;

public class ShowDataModel {

    private int id;
    private String name;
    private String phone;
    private String gender;
    private String area;
    private String work;
    private byte[] image;

//    public ShowDataModel(int id, String name, String phone, String gender, String area, String work, String image) {
//        this.id = id;
//        this.name = name;
//        this.phone = phone;
//        this.gender = gender;
//        this.area = area;
//        this.work = work;
//        this.image = image;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
