package com.example.nhom14didong.Model;

public class User {
    //Khai báo casc thuộc tính...
    public int userID;
    public String userName;
    public String userPass;
    public String fullName;
    public String email;
    public String role;
    public String dataCreate;

    public User() {
    }

    public User(int userID, String userName, String userPass, String fullName, String email, String role, String dataCreate) {
        this.userID = userID;
        this.userName = userName;
        this.userPass = userPass;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.dataCreate = dataCreate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(String dataCreate) {
        this.dataCreate = dataCreate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", dataCreate='" + dataCreate + '\'' +
                '}';
    }
}
