package com.example.mytech.model;

public class Subject {
    //môn học
    private String subject_id; // ID của môn học
    private String name; // Tên của môn học

    public Subject() {
    }

    public Subject(String subject_id, String name) {
        this.subject_id = subject_id;
        this.name = name;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
