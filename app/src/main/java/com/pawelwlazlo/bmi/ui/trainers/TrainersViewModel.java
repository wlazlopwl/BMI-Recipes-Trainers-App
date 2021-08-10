package com.pawelwlazlo.bmi.ui.trainers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pawelwlazlo.bmi.data.trainer.Trainer;
import com.pawelwlazlo.bmi.data.trainer.TrainerRepository;

import java.util.List;

public class TrainersViewModel extends AndroidViewModel {

    private final TrainerRepository trainerRepository;
    private final LiveData<List<Trainer>> allTrainers;

    public TrainersViewModel(@NonNull Application application) {
        super(application);
        trainerRepository = new TrainerRepository(application);
        allTrainers = trainerRepository.getAllTrainers();

    }


    public LiveData<List<Trainer>> getAllTrainers() {
        return allTrainers;
    }

    public void insertTrainer(List<Trainer> trainer) {
        trainerRepository.insertTrainer(trainer);
    }

}