package com.example.mytech.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConnectFirebase {


    // Student
    public static final DatabaseReference refStudent = FirebaseDatabase.getInstance().getReference("Students");


    //Storage image
    public static final StorageReference storageReference = FirebaseStorage.getInstance().getReference("uploads");
    // Account nguoi dung
    public static final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
    ;


}
