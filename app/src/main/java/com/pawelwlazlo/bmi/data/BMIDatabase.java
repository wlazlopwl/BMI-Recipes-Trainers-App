package com.pawelwlazlo.bmi.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.pawelwlazlo.bmi.data.bmi.BmiItem;
import com.pawelwlazlo.bmi.data.bmi.BmiItemDao;
import com.pawelwlazlo.bmi.data.recipes.Meals;
import com.pawelwlazlo.bmi.data.recipes.RecipesDao;
import com.pawelwlazlo.bmi.data.trainer.Trainer;
import com.pawelwlazlo.bmi.data.trainer.TrainerDao;
import com.pawelwlazlo.util.BmiResultTypeConverter;

@Database(entities = {Trainer.class, Meals.Meal.class, BmiItem.class}, version = 2)
@TypeConverters(BmiResultTypeConverter.class)
public abstract class BMIDatabase extends RoomDatabase {


    private static final String DB_NAME = "bmi_db";
    private static BMIDatabase instance;

    public static synchronized BMIDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BMIDatabase.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract TrainerDao trainerDao();

    public abstract RecipesDao recipesDao();

    public abstract BmiItemDao bmiItemDao();

}
