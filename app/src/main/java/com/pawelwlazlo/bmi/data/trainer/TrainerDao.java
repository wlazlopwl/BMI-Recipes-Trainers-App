package com.pawelwlazlo.bmi.data.trainer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface TrainerDao {

    @Query("SELECT * FROM Trainer")
    LiveData<List<Trainer>> getAllTrainers();

    @Transaction default
    void insertTrainer(List<Trainer> trainer) {
        deleteAll();
        insert(trainer);
    }

    @Insert
    void insert(List<Trainer> trainer);

    @Query("DELETE FROM Trainer")
    void deleteAll();


}
