package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainPage extends AppCompatActivity {
Button PersonalInfo, Water, Meals, steps, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        PersonalInfo=(Button) findViewById(R.id.PersonalInfo);
        Water=(Button) findViewById(R.id.Intake);
        Meals=(Button) findViewById(R.id.Meals);
        steps = findViewById(R.id.btn_StepCounter);
        view= findViewById(R.id.btn_PersonalView);

        PersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, PersonalInfo.class));
            }
        });

        Water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, waterIntake.class));
            }
        });

        Meals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, Meals.class));
            }
        });

        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, ViewData.class));
            }
        });
    }
}