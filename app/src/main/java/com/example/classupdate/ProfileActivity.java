package com.example.classupdate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";

    private EditText txtName,txtPhnNo,txtBloodGroup;
    private Button btnOpenGallery,btnSaveProfile;
    private CircleImageView profilePic;
    private Uri imageUri;
    Bitmap thumbBitmap=null;
    public static final int IMAGE_REQUEST= 100;
    ProgressDialog pd;

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtName= findViewById(R.id.txtName);
        txtPhnNo=findViewById(R.id.txtPhnNo);
        txtBloodGroup=findViewById(R.id.txtBloodGroup);
        btnOpenGallery=findViewById(R.id.btnOpenGallery);
        btnSaveProfile=findViewById(R.id.btnSaveProfile);
        profilePic=findViewById(R.id.profilePic);
        pd = new ProgressDialog(this);
        pd.setTitle("Updating Profile...");
        pd.setCancelable(false);



        storageReference= FirebaseStorage.getInstance().getReference();

        btnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfileChooser();
            }
        });
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                Log.d(TAG, "onClick: started save profile");
                saveProfilePic();
            }
        });



    }

    private void saveProfilePic()  {
        String imageName= FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d(TAG, "saveProfilePic: started" +imageUri);

        StorageReference ref= storageReference.child(imageName+"."+getFileExtension(imageUri));
        if(imageUri!=null)
        {
            Log.d(TAG, "saveProfilePic: started imageuri ! null");
            UploadTask uploadTask= ref.putFile(imageUri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.d(TAG, "onSuccess: started got uri "+ uri);
                            addToDatabase(uri);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.getLocalizedMessage();
                            e.printStackTrace();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    // ...
                    pd.dismiss();
                    exception.getLocalizedMessage();

                }
            });

        }else {
            //TODO: check for other Info
            Uri uri= null;
            addToDatabase(uri);
        }


    }

    private void addToDatabase(Uri uri) {
        Log.d(TAG, "addToDatabase: started");

        String email= Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
        Student student= Student.getBuilder()
                .withName(txtName.getText().toString().trim())
                .withPhoneNumber(txtPhnNo.getText().toString().trim())
                .withEmail(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail())
                .withBloodGroup(txtBloodGroup.getText().toString().trim().toUpperCase())
                .withUrl(uri.toString())
                .withUId(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .build();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(email)
                .update(student.toMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Started : DocumentSnapshot successfully written!");

                        pd.dismiss();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: "+e.getMessage());
                        pd.dismiss();
                        Toast.makeText(ProfileActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST&& resultCode==RESULT_OK&& data!= null && data.getData()!= null)
        {
            imageUri= data.getData();

            Glide.with(this).load(imageUri).placeholder(R.drawable.prof).into(profilePic);
        }
    }

    private void openfileChooser() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
    public String getFileExtension(Uri imageUri)
    {
        ContentResolver contentResolver= getContentResolver();
        MimeTypeMap mimeTypeMap= MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));

    }


}

