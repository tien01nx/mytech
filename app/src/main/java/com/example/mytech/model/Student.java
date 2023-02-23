package com.example.mytech.model;


import android.widget.EditText;
import android.widget.TextView;

public class Student  {
    // học viên
    private String student_id;
    private String name;
    private String date_of_birth;
    private String address;
    private String phone;
    private String email;
    private String class_id;
    private String imageUrl;
    private String role;


    public Student() {

    }


    public Student(String student_id, String name, String date_of_birth, String address, String phone) {
        this.student_id = student_id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
    }

    public Student(String email, String name, String imageUrl) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public Student(String student_id, String name, String date_of_birth, String address, String phone, String email, String imageUrl) {
        this.student_id = student_id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public Student(String student_id, String name, String date_of_birth, String address, String phone, String email, String class_id, String imageUrl, String role) {
        this.student_id = student_id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.class_id = class_id;
        this.imageUrl = imageUrl;
        this.role = role;
    }


    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_bỉth(String date_of_bỉth) {
        this.date_of_birth = date_of_bỉth;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;

    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;

    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;

    }
}
