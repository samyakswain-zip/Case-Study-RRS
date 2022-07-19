package com.yashtech.rrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class User {
	//All fields in user table 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(unique = true)
    private String username;
    private String password;
    private String mobile;
    private String gender;

    private String userRole;

    public User() {
    }

    public User(String username, String password, String mobile, String gender, String userRole) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
