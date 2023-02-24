package com.example.mytech.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytech.MainActivity;
import com.example.mytech.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText usemail_in, password_in;
    TextView signUp_in,forgot_password;
    Button btnLogin;

    // Firebase:
    FirebaseAuth auth;  // firebase auth
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        // kiem tra nguoi dung da dang nhap hay chua
        if (firebaseUser != null) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            // email nguoi dung dang nhap
            Toast.makeText(LoginActivity.this, firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usemail_in = findViewById(R.id.email_in);
        password_in = findViewById(R.id.password_in);
        signUp_in = findViewById(R.id.signUp_in);
        forgot_password = findViewById(R.id.forgot_password_in);
        btnLogin = findViewById(R.id.btnLogin);


        auth = FirebaseAuth.getInstance();
        // lấy thông tin người dùng khi đang đăng nhập
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        signUp_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogRePassword();
//                Intent i = new Intent(LoginActivity.this,ForgotPassword.class);
//                startActivity(i);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_text = usemail_in.getText().toString();
                String pass_text = password_in.getText().toString();


                if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)) {
                    Toast.makeText(LoginActivity.this, "không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email_text, pass_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu sai", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }
    private void DialogRePassword() {
        Dialog dialog2 = new Dialog(LoginActivity.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.forgot_password);
        dialog2.setCanceledOnTouchOutside(false);

        //ánh xạ dialog
        Button btnDongY2 = dialog2.findViewById(R.id.btnForgotPassword);
        Button btnThoat2 = dialog2.findViewById(R.id.btnCancel);

        EditText email = dialog2.findViewById(R.id.edtEmailResetPassword);


        btnDongY2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //lấy giá trị người dùng nhập vào edit text
                String newPassword = email.getText().toString().trim();

                //check trống
                if (!newPassword.isEmpty() ) {
                    //so sánh 2 mật khẩu
                    FirebaseAuth firebaseAuth;
                    firebaseAuth = FirebaseAuth.getInstance();

                 firebaseAuth.sendPasswordResetEmail(newPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(getApplicationContext(), "Kiểm tra thư trong email", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Toast.makeText(LoginActivity.this, "Email khong ton tai", Toast.LENGTH_SHORT).show();
                             }
                         });



                } else {
                    Toast.makeText(LoginActivity.this, "Không được để trống!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //click nút thoát
        btnThoat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tắt dialog
                dialog2.dismiss();
            }
        });
        //hiện dialog
        dialog2.show();

    }
}