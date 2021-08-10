package com.pawelwlazlo.bmi.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pawelwlazlo.bmi.data.bmi.BmiItem;
import com.pawelwlazlo.bmi.data.bmi.BmiItemRepository;
import com.pawelwlazlo.util.BMITypeResult;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private final BmiItemRepository bmiItemRepository;
    public MutableLiveData<Integer> _height;
    public MutableLiveData<Boolean> _isMale;
    public MutableLiveData<Integer> _weight;
    public MutableLiveData<Integer> _age;
    public MutableLiveData<Float> _bmiValue;
    public MutableLiveData<BmiItem> _bmiItem;

    public LiveData<Integer> getHeight() {
        return _height;
    }

    public LiveData<Boolean> isMale() {
        return _isMale;
    }

    public LiveData<Integer> getWeight() {
        return _weight;
    }

    public LiveData<Integer> getAge() {
        return _age;
    }

    public LiveData<Float> getBmiValue() {
        return _bmiValue;
    }

    public LiveData<BmiItem> getBmiItem() {
        return _bmiItem;
    }

    public HomeViewModel(Application application) {
        super(application);
        bmiItemRepository = new BmiItemRepository(application);
        _height = new MutableLiveData<>();
        _weight = new MutableLiveData<>();
        _isMale = new MutableLiveData<>();
        _age = new MutableLiveData<>();
        _bmiValue = new MutableLiveData<>();
        _bmiItem = new MutableLiveData<>();
        _height.setValue(100);
        _isMale.setValue(true);
        _weight.setValue(50);
        _age.setValue(20);

    }

    public LiveData<List<BmiItem>> getAllResult(){
        return bmiItemRepository.getAllResult();
    }

    public void saveBmiResult(BmiItem bmiItem){
        bmiItemRepository.saveBmiResult(bmiItem);
    }

    public void calculateBMIValue() {

        double weight = getWeight().getValue();
        double height = getHeight().getValue();
        height = height / 100;
        _bmiValue.setValue((float) (weight / (height * height)));

        _bmiItem.setValue(initBMIResult());


    }

    private BmiItem initBMIResult() {
        BmiItem bmiItem = new BmiItem();
        bmiItem.setAge(getAge().getValue());
        bmiItem.setHeight(getHeight().getValue());
        bmiItem.setWeight(getWeight().getValue());
        bmiItem.setMale(isMale().getValue());
        bmiItem.setResult(getBmiValue().getValue());
        bmiItem.setTypeResult(calculateBMIResult(bmiItem.isMale(), bmiItem.getAge(), bmiItem.getResult()));
    return bmiItem;
    }

    private BMITypeResult calculateBMIResult(boolean isMale, int age, float bmiValue){
        if (isMale){
            if ((age>=18)&&(age<=24)){
                if (bmiValue<20) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=20)&&(bmiValue<=25)) return BMITypeResult.CORRECT;
                if ((bmiValue>=26)&&(bmiValue<=29)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>29) return BMITypeResult.OBESITY;
            }
            if ((age>=25)&&(age<=34)){
                if (bmiValue<21) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=21)&&(bmiValue<=26)) return BMITypeResult.CORRECT;
                if ((bmiValue>=27)&&(bmiValue<=30)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>30) return BMITypeResult.OBESITY;
            }
            if ((age>=35)&&(age<=44)){
                if (bmiValue<22) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=22)&&(bmiValue<=27)) return BMITypeResult.CORRECT;
                if ((bmiValue>=28)&&(bmiValue<=31)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>31) return BMITypeResult.OBESITY;
            }
            if ((age>=45)&&(age<=54)){
                if (bmiValue<23) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=23)&&(bmiValue<=28)) return BMITypeResult.CORRECT;
                if ((bmiValue>=29)&&(bmiValue<=32)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>32) return BMITypeResult.OBESITY;
            }
            if ((age>=55)&&(age<=64)){
                if (bmiValue<24) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=24)&&(bmiValue<=29)) return BMITypeResult.CORRECT;
                if ((bmiValue>=30)&&(bmiValue<=33)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>33) return BMITypeResult.OBESITY;
            }
            if (age>=55){
                if (bmiValue<25) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=25)&&(bmiValue<=30)) return BMITypeResult.CORRECT;
                if ((bmiValue>=31)&&(bmiValue<=34)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>34) return BMITypeResult.OBESITY;
            }
        }
        else {
            if ((age>=18)&&(age<=24)){
                if (bmiValue<19) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=19)&&(bmiValue<=24)) return BMITypeResult.CORRECT;
                if ((bmiValue>=25)&&(bmiValue<=28)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>28) return BMITypeResult.OBESITY;
            }
            if ((age>=25)&&(age<=34)){
                if (bmiValue<20) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=20)&&(bmiValue<=25)) return BMITypeResult.CORRECT;
                if ((bmiValue>=26)&&(bmiValue<=29)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>29) return BMITypeResult.OBESITY;
            }
            if ((age>=35)&&(age<=44)){
                if (bmiValue<21) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=21)&&(bmiValue<=26)) return BMITypeResult.CORRECT;
                if ((bmiValue>=27)&&(bmiValue<=30)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>30) return BMITypeResult.OBESITY;
            }
            if ((age>=45)&&(age<=54)){
                if (bmiValue<22) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=22)&&(bmiValue<=27)) return BMITypeResult.CORRECT;
                if ((bmiValue>=28)&&(bmiValue<=31)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>31) return BMITypeResult.OBESITY;
            }
            if ((age>=55)&&(age<=64)){
                if (bmiValue<23) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=23)&&(bmiValue<=28)) return BMITypeResult.CORRECT;
                if ((bmiValue>=29)&&(bmiValue<=32)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>32) return BMITypeResult.OBESITY;
            }
            if (age>=55){
                if (bmiValue<24) return BMITypeResult.UNDERWEIGHT;
                if ((bmiValue>=24)&&(bmiValue<=29)) return BMITypeResult.CORRECT;
                if ((bmiValue>=30)&&(bmiValue<=33)) return BMITypeResult.OVERWEIGHT;
                if (bmiValue>33) return BMITypeResult.OBESITY;
            }
        }

     return null;
    }

    public void deleteItem(int idForDelete) {
        bmiItemRepository.deleteItem(idForDelete);
    }
}