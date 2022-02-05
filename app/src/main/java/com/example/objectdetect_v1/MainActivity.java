package com.example.objectdetect_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    static {
        if(OpenCVLoader.initDebug()){
            Log.d("MainActivity ","OpenCV is Loaded");
        }
        else {
            Log.d("MainActivity ","OpenCV is Not Loaded");
        }
    }

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

        RTODetectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
}