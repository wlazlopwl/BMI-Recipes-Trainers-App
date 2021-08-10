package com.pawelwlazlo.bmi.data.bmi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BmiItemDao {

    @Insert
    void insert(BmiItem bmiItem);

    @Query("DELETE FROM BmiItem")
    void deleteAll();

    @Query("DELETE FROM BmiItem WHERE id=:id ")
    void deleteItem(int id);

    @Query("SELECT *  FROM BmiItem ORDER BY id DESC ")
    LiveData<List<BmiItem>> getAllResult();
}
