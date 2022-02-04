package com.example.objectdetect_v1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class signUpActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mUsername, mEmail, mPass, mRepass;
    Button mSignUpbtn;
    TextView HaveAcc,ForgetPass;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = findViewById(R.id.signUp_username);
        mEmail = findViewById(R.id.signUp_Email);
        mPass = findViewById(R.id.signUp_passwd);
        mRepass = findViewById(R.id.signUp_passwd2);

        HaveAcc = findViewById(R.id.HaveAccount);


        mSignUpbtn = findViewById(R.id.signUpButton);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);


        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mSignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Username = mUsername.getText().toString().trim();
                final String Email = mEmail.getText().toString().trim();
                String Password = mPass.getText().toString().trim();
                String RePassword = mRepass.getText().toString().trim();

                if (TextUtils.isEmpty(Username)){
                    mUsername.setError("Username is Required");
                    return;
                }
                if (TextUtils.isEmpty(Email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    mPass.setError("Password is Required");
                    return;
                }
                if (TextUtils.isEmpty(RePassword)){
                    mRepass.setError("Enter Password Again");
                    return;
                }
                if (Password.length() < 6 && RePassword.length() < 6){
                    mPass.setError("Password Must be 6 Characters");
                    return;
                }
                if (!Password.equals(RePassword)){
                    mRepass.setError("Password Doesn't Match");
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser fUser = fAuth.getCurrentUser();
                            fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(signUpActivity.this, "Verification Email Has Been Set", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"OnFailure : Email not sent" + e.getMessage());
                                }
                            });


                            Toast.makeText(signUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("username",Username);
                            user.put("email",Email);
                            user.put("password",Password);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                   Log.d(TAG, "OnSuccess : User Profile is create for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure: "+e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else {
                            Toast.makeText(signUpActivity.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        HaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),loginActivity.class));
            }
        });

    }

}