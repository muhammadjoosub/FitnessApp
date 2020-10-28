package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class waterIntake extends AppCompatActivity {
EditText Water;
ProgressBar waterBar;
Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake);
        final ProgressBar waterBar = findViewById(R.id.progressBar2);
        Button  submit = findViewById(R.id.buttonWater);
        final EditText water = findViewById(R.id.editTextWater);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterBar.setProgress(Integer.parseInt(water.getText().toString()));
            }
        });
    }
}