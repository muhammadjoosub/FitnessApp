package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PersonalInfo extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;
    PersonalInfoClass personalInfoClass;
EditText Name, DOB, Age, Height,Weight,TargetWeight;
TextView DisplayName, DisplayDOB, DisplayAge, DisplayHeight, DisplayWeight, DisplayTargetWeight;
Button ButtonSubmit,ButtonUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        Name=(EditText) findViewById(R.id.editTextName);
        DOB=(EditText) findViewById(R.id.editTextDateOfBirth);
        Age=(EditText) findViewById(R.id.editTextAge);
        Height=(EditText) findViewById(R.id.editTextHeight);
        Weight=(EditText) findViewById(R.id.editTextWeight);
        TargetWeight=(EditText) findViewById(R.id.editTextTargetWeight);

        DisplayName=(TextView) findViewById(R.id.Name_Lbl);
        DisplayDOB=(TextView) findViewById(R.id.DateOfBirth_Lbl);
        DisplayAge=(TextView) findViewById(R.id.Age_Lbl);
        DisplayHeight=(TextView) findViewById(R.id.height_Lbl);
        DisplayWeight=(TextView) findViewById(R.id.Weight_Lbl);
        DisplayTargetWeight=(TextView) findViewById(R.id.TargetWeight_Lbl);

        ButtonSubmit=(Button) findViewById(R.id.PersonalInfo_btn);
        ButtonUpdate=(Button) findViewById(R.id.Update_btn);

        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ValueName = Name.getText().toString();
                String ValueDOB = DOB.getText().toString();
                String ValueAge = Age.getText().toString();
                String ValueHeight = Height.getText().toString();
                String ValueWeight = Weight.getText().toString();
                String ValueTargetWeight = TargetWeight.getText().toString();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference reference = database.getReference(user.getUid());

                 personalInfoClass = new PersonalInfoClass(ValueName, ValueDOB,ValueAge,ValueHeight,ValueWeight,ValueTargetWeight);

                reference.child("PersonalInfo").setValue(personalInfoClass);

               DatabaseReference reference1 = database.getReference(mAuth.getCurrentUser().getUid());
              reference1.child("PersonalInfo").addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                      personalInfoClass = dataSnapshot.getValue(PersonalInfoClass.class);



                      DisplayDOB.setText(personalInfoClass.getDOB());
                      DisplayAge.setText(personalInfoClass.getAge());
                      DisplayHeight.setText(personalInfoClass.getHeight());
                      DisplayWeight.setText(personalInfoClass.getWeight());
                      DisplayTargetWeight.setText(personalInfoClass.getTargetWeight());
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });

//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

            }
        });

        ButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ValueName = Name.getText().toString();
                String ValueDOB = DOB.getText().toString();
                String ValueAge = Age.getText().toString();
                String ValueHeight = Height.getText().toString();
                String ValueWeight = Weight.getText().toString();
                String ValueTargetWeight = TargetWeight.getText().toString();

                HashMap hashMap = new HashMap();
                hashMap.put("Name",ValueName);
                hashMap.put("DOB", ValueDOB);
                hashMap.put("Age", ValueAge);
                hashMap.put("Height", ValueHeight);
                hashMap.put("Weight", ValueWeight);
                hashMap.put("Target Weight", ValueTargetWeight);

                DatabaseReference reference = database.getReference(mAuth.getCurrentUser().getUid());
                reference.child("PersonalInfo").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(PersonalInfo.this, "Update Successful", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}