package com.example.classupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    EditText txtEmail,txtPassword;
    Button btnRegister;
    TextView txtLoginGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtEmail= findViewById(R.id.txtEmailInp);
        txtPassword=findViewById(R.id.txtPasswordInp);
        btnRegister=findViewById(R.id.btnRegister);
        txtLoginGo=findViewById(R.id.txtLoginAccount);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+"btnregister pressed");
                Log.d(TAG, "onClick: email = "+txtEmail.getText().toString());

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        txtLoginGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }
}
