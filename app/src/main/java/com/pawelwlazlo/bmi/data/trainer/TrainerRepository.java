package com.pawelwlazlo.bmi.data.trainer;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.pawelwlazlo.bmi.data.BMIDatabase;

import java.util.List;

public class TrainerRepository {

    private final TrainerDao trainerDao;
    private final LiveData<List<Trainer>> allTrainers;

    public TrainerRepository(Application application) {
        BMIDatabase db = BMIDatabase.getInstance(application);
        trainerDao = db.trainerDao();
        allTrainers = trainerDao.getAllTrainers();
    }

    public LiveData<List<Trainer>> getAllTrainers() {
        return allTrainers;

    }
    public void insertTrainer(List<Trainer> trainer) {

        trainerDao.insert(trainer);

    }

}
