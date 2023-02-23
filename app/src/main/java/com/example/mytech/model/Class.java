package com.example.mytech.model;

public class Class {
    //Lớp

    private String class_id;
    private String name;
    private String subject_id;
    private String teacher_id;
    private String start_date;
    private String end_date;
    private int num_lessons_per_week;
    // Số tiết học trong tuần của lớp học

    public Class() {
    }

    public Class(String class_id, String name, String subject_id, String teacher_id, String start_date, String end_date, int num_lessons_per_week) {
        this.class_id = class_id;
        this.name = name;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.num_lessons_per_week = num_lessons_per_week;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getNum_lessons_per_week() {
        return num_lessons_per_week;
    }

    public void setNum_lessons_per_week(int num_lessons_per_week) {
        this.num_lessons_per_week = num_lessons_per_week;
    }
}
