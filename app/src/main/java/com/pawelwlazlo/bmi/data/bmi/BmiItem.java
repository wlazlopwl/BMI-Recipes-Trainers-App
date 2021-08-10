package com.pawelwlazlo.bmi.data.bmi;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.pawelwlazlo.util.BMITypeResult;

import java.io.Serializable;

@Entity
public class BmiItem implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        this.isMale = male;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BMITypeResult getTypeResult() {
        return typeResult;
    }

    public void setTypeResult(BMITypeResult typeResult) {
        this.typeResult = typeResult;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private boolean isMale;
    private int height;
    private int weight;
    private int age;


    private BMITypeResult typeResult;

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    private float result;


}
