package com.example.mytech.model;

public class Room {// phòng học

    private String room_id; // ID của phòng học
    private String name; // Tên của phòng học

    public Room() {
    }


    public Room(String room_id, String name) {
        this.room_id = room_id;
        this.name = name;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
