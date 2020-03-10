package com.example.classupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private EditText txtEmail,txtPassword;
    private Button btnLogin;
    private TextView txtReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail= findViewById(R.id.txtEmailInput);
        txtPassword=findViewById(R.id.txtPasswordInput);
        btnLogin=findViewById(R.id.btnLogin);
        txtReg=findViewById(R.id.txtCreateAccount);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+"btnlogin pressed");
                Log.d(TAG, "onClick: email = "+txtEmail.getText().toString());
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));

            }
        });

    }
}
