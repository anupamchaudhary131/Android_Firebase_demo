package com.example.android_firebase_demo;

public class ContactModel {

    String name;
    String mobileNo;

    ContactModel(String name, String mobileNo){
        this.name = name;
        this.mobileNo = mobileNo;

    }

    ContactModel(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
