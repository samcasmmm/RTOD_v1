package com.example.objectdetect_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {
    ImageView loginLogo;
    TextView forgetPasswd, dontHaveAccount;
    Button skipbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginLogo = findViewById(R.id.loginlogo);

        forgetPasswd = (TextView) findViewById(R.id.forgetpasswd);
        dontHaveAccount = (TextView) findViewById(R.id.dontHaveAccount);
        skipbtn = findViewById(R.id.skip_btn);


        dontHaveAccount.setOnClickListener(view -> {
            Intent intent1 = new Intent(loginActivity.this, signUpActivity.class);
            startActivity(intent1);

        });
        skipbtn.setOnClickListener((view -> {
            startActivity(new Intent(loginActivity.this, MainActivity.class));
        }));


    }
}
