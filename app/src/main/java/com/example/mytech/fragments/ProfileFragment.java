package com.example.mytech.fragments;

import static android.app.Activity.RESULT_OK;
import static com.example.mytech.firebase.ConnectFirebase.fUser;
import static com.example.mytech.firebase.ConnectFirebase.refStudent;
import static com.example.mytech.firebase.ConnectFirebase.storageReference;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mytech.MainActivity;
import com.example.mytech.R;
import com.example.mytech.authentication.LoginActivity;
import com.example.mytech.model.Student;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    Button btnUpdateProfile;
    EditText name, dateofbirth, address, phone;
    TextView email;
    ImageView imageView;
    String image_logo_url;


    // image
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;
    DatabaseReference reference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.name_profile);
        email = view.findViewById(R.id.email_profile);
        dateofbirth = view.findViewById(R.id.date_of_birht);
        address = view.findViewById(R.id.address);
        phone = view.findViewById(R.id.phone);
        imageView = view.findViewById(R.id.profile_image);


        btnUpdateProfile = view.findViewById(R.id.button);

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), fUser.getUid(), Toast.LENGTH_SHORT).show();
                Student student = new Student(fUser.getUid(),name.getText().toString(),dateofbirth.getText().toString(),
                        address.getText().toString(),phone.getText().toString(),fUser.getEmail(),image_logo_url);

                refStudent.child(fUser.getUid()).setValue(student);
                Toast.makeText(getContext(), "Update thanh cong", Toast.LENGTH_SHORT).show();
//
            }
        });

        Log.d("HIHIHI", fUser.getUid());

        refStudent.child(fUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = snapshot.getValue(Student.class);

                name.setText(student.getName());
                email.setText(student.getEmail());


//                Toast.makeText(getContext(), student.getName(), Toast.LENGTH_SHORT).show();

                if (student.getImageUrl().equals("default")){
                    imageView.setImageResource(R.drawable.logo_default);
                }else{
                    Glide.with(getContext()).load(student.getImageUrl()).into(imageView);
                    image_logo_url = student.getImageUrl();
                }

                dateofbirth.setText(student.getDate_of_birth());
                address.setText(student.getAddress());
                phone.setText(student.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lấy ảnh từ thư viện
                SelectImage();


            }
        });

        return view;


    }

    private void SelectImage() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, IMAGE_REQUEST);

    }
    private String getFileExtention(Uri uri){


        ContentResolver contentResolver =getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }


    private void UploadMyImage(){


        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading");
        progressDialog.show();

        if(imageUri != null) {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtention(imageUri));


            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                    if (!task.isSuccessful()){

                        throw  task.getException();
                    }

                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if (task.isSuccessful()){

                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        HashMap<String, Object> map = new HashMap<>();

                        map.put("imageUrl", mUri);
                        refStudent.child(fUser.getUid()).updateChildren(map);

                        progressDialog.dismiss();
                    }else{

                        Toast.makeText(getContext(), "Failed!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });


        }else
        {
            Toast.makeText(getContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == IMAGE_REQUEST &&  resultCode == RESULT_OK
                && data != null && data.getData() != null){


            imageUri = data.getData();


            if (uploadTask != null && uploadTask.isInProgress()){

                Toast.makeText(getContext(), "Upload in progress..", Toast.LENGTH_SHORT).show();



            }else {

                UploadMyImage();
            }


        }
    }
}