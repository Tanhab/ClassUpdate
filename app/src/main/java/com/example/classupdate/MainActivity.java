package com.example.classupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FloatingActionButton btnLogout;
    private CardView cardClassRoutine,cardExamRoutine,cardNotification,cardResources,cardCR,cardProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogout= findViewById(R.id.btnLogout);
        cardClassRoutine=findViewById(R.id.cardClassRoutine);
        cardCR=findViewById(R.id.cardCR);
        cardExamRoutine=findViewById(R.id.cardExamRoutine);
        cardNotification=findViewById(R.id.cardNotification);
        cardResources=findViewById(R.id.cardResources);
        cardProfile=findViewById(R.id.cardProfile);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        cardClassRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cardExamRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });cardClassRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cardResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cardNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cardCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
