package com.example.objectdetect_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DecisionActivity extends AppCompatActivity {

    Button toLogin, toSignUp, toSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);
        toLogin = findViewById(R.id.tologin);
        toSignUp = findViewById(R.id.tosignup);
        toSkip = findViewById(R.id.toskip);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DecisionActivity.this,loginActivity.class));
            }
        });

        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DecisionActivity.this,signUpActivity.class));
            }
        });

        toSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DecisionActivity.this,MainActivity.class));
            }
        });
    }
}