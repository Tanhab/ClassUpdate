package com.example.classupdate;

import java.util.HashMap;
import java.util.Map;

public class Student{
    private String nickname;
    private String email;
    private String phoneNumber;
    private String bloodGroup;
    private String url;
    private String uId;

    private Student(Builder builder){
        this.nickname=builder.nickname;
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
        private String nickname;
        private String email;
        private String phoneNumber;
        private String bloodGroup;
        private String url;
        private String uId;

        Builder withNickname(String nickname){
            this.nickname=nickname;
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
        map.put("nickname",nickname);
        map.put("email",email);
        map.put("phoneNumber",phoneNumber);
        map.put("bloodGroup",bloodGroup);
        map.put("url",url);
        map.put("uId",uId);
        return map;
    }
}

