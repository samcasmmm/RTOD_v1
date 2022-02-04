package com.example.objectdetect_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button RTODetectionBtn,HandSignBtn,EditProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RTODetectionBtn = findViewById(R.id.ObjectDetectionBTN);
        HandSignBtn = findViewById(R.id.handSignBTN);
        EditProfileBtn = findViewById(R.id.EditProfileBTN);



        EditProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UpdateProfile.class));
            }
        });
    }
}