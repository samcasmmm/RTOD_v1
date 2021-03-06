package com.example.objectdetect_v1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import java.util.Timer;
//import java.util.TimerTask;

public class splashscreen extends AppCompatActivity {
    private static final long Splash_length = 2000;

    ImageView splashLogo;
    TextView logo_text;
//    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        splashLogo = findViewById(R.id.splashLogo);
        logo_text = findViewById(R.id.logo_text);
        splashLogo.animate().translationY(600).setDuration(1000).setStartDelay(1000);
        logo_text.animate().translationY(620).setDuration(1000).setStartDelay(1000);

//        Intent intent = new Intent(this, loginActivity.class);
//        splashscreen.this.startActivity(intent);
//        splashscreen.this.finish();
//        timer = new Timer();

//        ActionBar mActionbar = getSupportActionBar();
//        mActionbar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashscreen.this,DecisionActivity.class);
                splashscreen.this.startActivity(intent);
                splashscreen.this.finish();
            }
        },Splash_length);



    }
}