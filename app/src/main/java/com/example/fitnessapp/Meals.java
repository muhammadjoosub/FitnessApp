package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Meals extends AppCompatActivity {
    Button Camera;
    ImageView Picture;
    Intent intent;

    public static final int RequestPermissionCode = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Camera = findViewById(R.id.btn_camera);
        Picture = (ImageView) findViewById(R.id.imageViewMeal);

        EnableRuntimePermission();

        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 7);

            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            Picture.setImageBitmap(bitmap);
        }
    }

    private void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Meals.this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(Meals.this,"permission allowed", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Meals.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }
    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(Meals.this,"access camera", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(Meals.this,"camera canceled .", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}