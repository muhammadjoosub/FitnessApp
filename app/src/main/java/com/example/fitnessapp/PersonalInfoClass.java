package com.example.fitnessapp;

public class PersonalInfoClass {
    String Name;
    String DOB;
    String Age;
    String Height;
    String Weight;
    String TargetWeight;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getTargetWeight() {
        return TargetWeight;
    }

    public void setTargetWeight(String targetWeight) {
        TargetWeight = targetWeight;
    }
public PersonalInfoClass(){

}
    public PersonalInfoClass(String name, String DOB, String age, String height, String weight, String targetWeight) {
        Name = name;
        this.DOB = DOB;
        Age = age;
        Height = height;
        Weight = weight;
        TargetWeight = targetWeight;
    }
}
