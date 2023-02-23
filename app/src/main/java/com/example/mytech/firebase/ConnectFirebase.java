package com.example.mytech.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConnectFirebase {

    public static final DatabaseReference refStudent =  FirebaseDatabase.getInstance().getReference("Students");
}
