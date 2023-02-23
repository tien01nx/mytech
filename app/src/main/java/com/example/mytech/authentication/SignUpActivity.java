package com.example.mytech.authentication;

import static com.example.mytech.firebase.ConnectFirebase.refStudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytech.MainActivity;
import com.example.mytech.R;
import com.example.mytech.model.Account;
import com.example.mytech.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText username, password, email;
    Button btnSignUp;

    // lay useid khi người dùng đăng nhập
    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email_signup);
        password = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.btnSignup);

        //firebase auth
        auth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterNow(username.getText().toString().trim(),
                        email.getText().toString().trim(),
                        password.getText().toString().trim());
            }
        });

    }

    private void RegisterNow(final String username, String email, String password) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Hàm này sẽ trả về thông tin người dùng hiện tại đăng nhập vào hệ thống

                            FirebaseUser firebaseUser = auth.getCurrentUser();


//                            user ID của người dùng hiện tại, được lấy từ firebaseUser bằng hàm getUid().
                            String userid = firebaseUser.getUid();
                            // khởi tạo myRef de them vao firebase

                            myRef = FirebaseDatabase.getInstance().getReference("Account")
                                    .child(userid);

//                            myRef = FirebaseDatabase.getInstance().getReference("accounts");
//                            String accountId = myRef.push().getKey();

                            Account account = new Account(email, password, username, "");
                            Student student = new Student(email,username);

//                            myRef.child(accountId).setValue(account).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
//                                        startActivity(i);
//                                        finish();
//                                    }
//
//                                }
//                            });


                            Log.d("TAG", "tài khoản: " + email + "Password: " + password);


                            // up dữ liệu lên firebase theo hasmap

                            refStudent.child(userid).setValue(student);

                            myRef.setValue(account).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                        Log.d("hihi","Dang kí thanh cong");
                                    }

                                }
                            });


                        } else {
                            Toast.makeText(SignUpActivity.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
}