package com.example.objectdetect_v1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.objectdetect_v1.chatbot.*;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {
    ImageView chatBotImg;

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
        chatBotImg = findViewById(R.id.id_chatBot);


        chatBotImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.objectdetect_v1.chatbot.MainActivity.class));
            }
        });


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