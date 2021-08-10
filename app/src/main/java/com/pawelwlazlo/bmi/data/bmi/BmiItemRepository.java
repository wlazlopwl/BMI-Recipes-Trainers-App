package com.pawelwlazlo.bmi.data.bmi;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.pawelwlazlo.bmi.data.BMIDatabase;

import java.util.List;

public class BmiItemRepository {

    private final BmiItemDao bmiItemDao;


    public BmiItemRepository(Application application) {

        BMIDatabase db = BMIDatabase.getInstance(application);
        bmiItemDao = db.bmiItemDao();
    }

    public void saveBmiResult(BmiItem bmiItem) {
        bmiItemDao.insert(bmiItem);
    }

    public LiveData<List<BmiItem>> getAllResult() {
        return bmiItemDao.getAllResult();
    }

    public void deleteItem(int idForDelete) {
        bmiItemDao.deleteItem(idForDelete);
    }
}
