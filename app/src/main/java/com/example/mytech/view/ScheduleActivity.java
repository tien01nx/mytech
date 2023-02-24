package com.example.mytech.view;

import static com.example.mytech.firebase.ConnectFirebase.refSchedule;
import static com.example.mytech.firebase.ConnectFirebase.refTeacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytech.R;
import com.example.mytech.model.Schedule;
import com.example.mytech.model.Student;

public class ScheduleActivity extends AppCompatActivity {
    private EditText scheduleIdEditText;
    private EditText classIdEditText;
    private EditText startTimeEditText;
    private EditText endTimeEditText;
    private EditText roomIdEditText;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getView();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String classId = classIdEditText.getText().toString();
                String startTime = startTimeEditText.getText().toString();
                String endTime = endTimeEditText.getText().toString();
                String roomId = roomIdEditText.getText().toString();

                String scheduleId = refTeacher.push().getKey();

                Schedule schedule = new Schedule(scheduleId,classId ,startTime, endTime, roomId);
                refSchedule.child(scheduleId).setValue(schedule);
                Format();
                Toast.makeText(ScheduleActivity.this, "Update Schedule success", Toast.LENGTH_SHORT).show();

                // Do something with the data...
            }
        });



    }

    public void getView(){

        classIdEditText = findViewById(R.id.class_id);
        startTimeEditText = findViewById(R.id.start_time);
        endTimeEditText = findViewById(R.id.end_time);
        roomIdEditText = findViewById(R.id.room_id);
        submitButton = findViewById(R.id.submit_button);
    }
    
    public void Format(){
        classIdEditText.setText("");
        startTimeEditText.setText("");
        endTimeEditText.setText("");
        roomIdEditText.setText("");
        submitButton.setText("");
    }
}