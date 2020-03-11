package com.example.classupdate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    EditText txtEmail,txtPassword,txtConfirmPassword;
    Button btnRegister;
    TextView txtLoginGo;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtEmail= findViewById(R.id.txtEmailInp);
        txtPassword=findViewById(R.id.txtPasswordInp);
        btnRegister=findViewById(R.id.btnRegister);
        txtLoginGo=findViewById(R.id.txtLoginAccount);
        txtConfirmPassword= findViewById(R.id.txtPasswordConfirm);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+"btnregister pressed");
                String email= txtEmail.getText().toString().trim();
                String password= txtPassword.getText().toString().trim();
                String confirmPassword= txtConfirmPassword.getText().toString().trim();
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    txtEmail.setFocusable(true);
                    txtEmail.setError("Invalid Email Address");

                }else if(password.length()<6)
                {
                    txtPassword.setError("Length should at least be 6");
                    txtPassword.setFocusable(true);
                }else if(!password.equals(confirmPassword)) {
                    txtConfirmPassword.setFocusable(true);
                    txtConfirmPassword.setError("Password didn't match");
                }else
                {
                    RegisterUser(email,password);
                }
            }
        });
        txtLoginGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }

    private void RegisterUser(String email, String password) {
       mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
