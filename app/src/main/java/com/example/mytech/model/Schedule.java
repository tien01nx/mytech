package com.example.mytech.model;

import java.time.LocalDateTime;

public class Schedule {
    // lịch học
    private  String chedule_id; // ID của lịch học
    private String  Stringclass_id ; //ID của lớp học
    private String Stringstart_time ; //Thời gian bắt đầu của buổi học
    private String end_time; //Thời gian kết thúc của buổi học
    private  String room_id ; //ID của phòng học được sử dụng cho buổi học


    public Schedule() {
    }

    public Schedule(String chedule_id, String stringclass_id, String stringstart_time, String end_time, String room_id) {
        this.chedule_id = chedule_id;
        Stringclass_id = stringclass_id;
        Stringstart_time = stringstart_time;
        this.end_time = end_time;
        this.room_id = room_id;
    }

    public String getChedule_id() {
        return chedule_id;
    }

    public void setChedule_id(String chedule_id) {
        this.chedule_id = chedule_id;
    }

    public String getStringclass_id() {
        return Stringclass_id;
    }

    public void setStringclass_id(String stringclass_id) {
        Stringclass_id = stringclass_id;
    }

    public String getStringstart_time() {
        return Stringstart_time;
    }

    public void setStringstart_time(String stringstart_time) {
        Stringstart_time = stringstart_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
}
