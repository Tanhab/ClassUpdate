package com.example.classupdate;

import java.util.HashMap;
import java.util.Map;

public class Student{
    private String name;
    private String email;
    private String phoneNumber;
    private String bloodGroup;
    private String url;
    private String uId;

    private Student(Builder builder){
        this.name=builder.name;
        this.email=builder.email;
        this.phoneNumber=builder.phoneNumber;
        this.bloodGroup=builder.bloodGroup;
        this.url=builder.url;
        this.uId=builder.uId;
    }

    public static Builder getBuilder(){

        return new Student.Builder();
    }

    public static class Builder{
        private String name;
        private String email;
        private String phoneNumber;
        private String bloodGroup;
        private String url;
        private String uId;

        Builder withName(String name){
            this.name=name;
            return this;
        }

        Builder withEmail(String email){
            this.email=email;
            return this;
        }

        Builder withPhoneNumber(String phoneNumber){
            this.phoneNumber=phoneNumber;
            return this;
        }

        Builder withBloodGroup(String bloodGroup){
            this.bloodGroup=bloodGroup;
            return this;
        }
        Builder withUrl(String url){
            this.url=url;
            return this;
        }
        Builder withUId(String uId){
            this.uId=uId;
            return this;
        }

        Student build(){
            return new Student(this);
        }
    }
    Map<String, Object> toMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("email",email);
        map.put("phoneNumber",phoneNumber);
        map.put("bloodGroup",bloodGroup);
        map.put("url",url);
        map.put("uId",uId);
        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}

