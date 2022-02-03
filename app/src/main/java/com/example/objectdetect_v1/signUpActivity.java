package com.example.objectdetect_v1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class signUpActivity extends AppCompatActivity {
    TextView HaveAccount;
    EditText signup_username,signup_email,signup_pass,signup_repass;
    Button signupButton;
//    String pattern ="[a-zA-Z0._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        HaveAccount = findViewById(R.id.HaveAccount);
        signup_username = findViewById(R.id.signUp_username_InputEditText);
        signup_email = findViewById(R.id.signUp_EmailInputEditText);
        signup_pass = findViewById(R.id.signUp_passwd_InputEditText1);
        signup_repass = findViewById(R.id.signUp_passwd_InputEditText2);
        signupButton = findViewById(R.id.signupButton);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.HaveAccount:
                startActivity(new Intent(this, loginActivity.class));
                break;
            case R.id.signupButton:
                signingUp();
                break;
        }
    }
    private void signingUp(){
        String username = signup_username.getText().toString().trim();
        String email = signup_email.getText().toString().trim();
        String passwd = signup_pass.getText().toString();
        String repasswd = signup_repass.getText().toString();

        if(username.isEmpty()){
            signup_username.setError("Please Enter Username");
            signup_username.requestFocus();
            return;
        }
        if(email.isEmpty()){
            signup_email.setError("Please Enter Email");
            signup_email.requestFocus();
            return;
        }
        if(passwd.isEmpty()){
            signup_pass.setError("Please Enter password");
            signup_pass.requestFocus();
            return;
        }
        if(repasswd.isEmpty()){
            signup_repass.setError("Please Enter password again");
            signup_repass.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signup_email.setError("Please Enter Valid Email");
            signup_email.requestFocus();
            return;
        }
        if(passwd.length() < 6 && repasswd.length() < 6){
            signup_pass.setError("Too short Enter Six ");
            signup_pass.requestFocus();
            signup_repass.requestFocus();
            return;
        }
        if (!passwd.equals(repasswd)){
            signup_pass.setError("Password Doesn't match");
        }

    }


}