package com.example.mytech.model;

import java.util.Date;

public class Teacher {
    // giáo viên

   private String  teacher_id ;
    private String name ;
    private String date_of_birth ;
    private String  address;
    private String phone;
    private String email ;
    private String subject_id ;
    private String role;

    public Teacher() {
    }

    public Teacher(String teacher_id, String name, String date_of_birth, String address, String phone, String email, String subject_id, String role) {
        this.teacher_id = teacher_id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.subject_id = subject_id;
        this.role = role;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
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

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
