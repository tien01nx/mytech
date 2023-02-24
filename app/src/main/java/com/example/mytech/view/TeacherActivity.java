package com.example.mytech.view;

import static com.example.mytech.firebase.ConnectFirebase.refTeacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.mytech.R;

import com.example.mytech.model.Teacher;


public class TeacherActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText dateOfBirthEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText subjectIdEditText;
    private EditText roleSpinner;
    Button btnAddTeacher;
//    Resources res = getResources();
//    String[] roles = res.getStringArray(R.array.roles);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        // anh xa
        getViews();

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roles);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        roleSpinner.setAdapter(adapter);


        btnAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String dateOfBirth = dateOfBirthEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String subjectId = subjectIdEditText.getText().toString();
                String role = roleSpinner.getText().toString();

                String teacherId = refTeacher.push().getKey();
                Teacher teacher = new Teacher(teacherId, name, dateOfBirth, address, phone, email, subjectId, role);
                refTeacher.child(teacherId).setValue(teacher);
                Format();
                Toast.makeText(TeacherActivity.this, "Add teacher success", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getViews() {
        nameEditText = findViewById(R.id.name_edittext);
        dateOfBirthEditText = findViewById(R.id.date_of_birth_picker);
        addressEditText = findViewById(R.id.address_edittext);
        phoneEditText = findViewById(R.id.phone_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        subjectIdEditText = findViewById(R.id.subject_id_edittext);
        roleSpinner = findViewById(R.id.role_spinner);
        btnAddTeacher = findViewById(R.id.btnAddTeacher);
    }

    public void Format() {
        nameEditText.setText("");
        dateOfBirthEditText.setText("");
        addressEditText.setText("");
        phoneEditText.setText("");
        emailEditText.setText("");
        subjectIdEditText.setText("");
        roleSpinner.setText("");
        btnAddTeacher.setText("");
    }
}