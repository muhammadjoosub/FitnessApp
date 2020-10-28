package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
private FirebaseAuth mAuth;
Button ButtonLogin, ButtonRegister;
EditText ETUsername, ETPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth= FirebaseAuth.getInstance();
        ButtonLogin =(Button) findViewById(R.id.Login);
        ButtonRegister=(Button) findViewById(R.id.Register);
        ETUsername=(EditText) findViewById(R.id.Username);
        ETPassword=(EditText) findViewById(R.id.Password);

        ButtonRegister.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  String Email = ETUsername.getText().toString().trim();
                                                  String Password = ETPassword.getText().toString().trim();
                                                  mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                                      @Override
                                                      public void onComplete(@NonNull Task<AuthResult> task) {

                                                      }
                                                  }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                      @Override
                                                      public void onSuccess(AuthResult authResult) {
                                                          FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                          DatabaseReference myRef = database.getReference("User");
                                                          myRef.child("User").setValue("user");
                                                      }
                                                  }).addOnFailureListener(new OnFailureListener() {
                                                      @Override
                                                      public void onFailure(@NonNull Exception e) {
                                                          Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                                      }
                                                  });


                                              }
                                          });

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = ETUsername.getText().toString().trim();
                String Password = ETPassword.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this, MainPage.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }
}
