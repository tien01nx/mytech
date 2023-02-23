package com.example.mytech.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytech.R;
import com.example.mytech.model.Class;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClassActivity extends AppCompatActivity {

    private EditText classIdInput, nameInput, subjectIdInput, teacherIdInput,
            startDateInput, endDateInput, numLessonsPerWeekInput;
    Button btnAdd_Class;

    DatabaseReference refClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        // anh xạ
        classIdInput = findViewById(R.id.class_id_input);
        nameInput = findViewById(R.id.name_input);
        subjectIdInput = findViewById(R.id.subject_id_input);
        teacherIdInput = findViewById(R.id.teacher_id_input);
        startDateInput = findViewById(R.id.start_date_input);
        endDateInput = findViewById(R.id.end_date_input);
        numLessonsPerWeekInput = findViewById(R.id.num_lessons_per_week_input);
        btnAdd_Class = findViewById(R.id.btn_add_class);

        refClass = FirebaseDatabase.getInstance().getReference("Classes");

        btnAdd_Class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classId = refClass.push().getKey();
                String name = nameInput.getText().toString();
                String subjectId = subjectIdInput.getText().toString();
                String teacherId = teacherIdInput.getText().toString();
                String startDate = startDateInput.getText().toString();
                String endDate = endDateInput.getText().toString();
                int numLessonsPerWeek = Integer.parseInt(numLessonsPerWeekInput.getText().toString());

                Class classActivity = new Class(classId, name, subjectId, teacherId, startDate, endDate, numLessonsPerWeek);
                refClass.child(classId).setValue(classActivity);
                Format();
                Toast.makeText(ClassActivity.this, "Thêm thành công"   , Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void Format(){
        classIdInput.setText("");
        nameInput.setText("");
        subjectIdInput.setText("");
        teacherIdInput .setText("");
        startDateInput.setText("");
        endDateInput.setText("");
        numLessonsPerWeekInput.setText("");
        btnAdd_Class.setText("");
    }
}