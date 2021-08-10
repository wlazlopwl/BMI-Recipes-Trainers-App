package com.pawelwlazlo.bmi.data.recipes;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pawelwlazlo.api.FoodApi;
import com.pawelwlazlo.bmi.data.BMIDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesRepository {
    private final RecipesDao recipesDao;

    MutableLiveData<List<Categories.Category>> _categoriesList = new MutableLiveData<>();
    public LiveData<List<Categories.Category>> categoriesList = _categoriesList;

    MutableLiveData<List<Meals.Meal>> _mealsList = new MutableLiveData<>();
    public LiveData<List<Meals.Meal>> mealsList = _mealsList;

    MutableLiveData<Meals.Meal> _meal = new MutableLiveData<>();
    public LiveData<Meals.Meal> meal = _meal;

    MutableLiveData<Integer> _isSave = new MutableLiveData<>();
    public LiveData<Integer> isSave = _isSave;


    MutableLiveData<List<Meals.Meal>> _favMealsList = new MutableLiveData<>();
    public LiveData<List<Meals.Meal>> favMealsList = _favMealsList;

    MutableLiveData<List<Meals.Meal>> _mealsByQuery = new MutableLiveData<>();
    public LiveData<List<Meals.Meal>> mealsByQuery = _mealsByQuery;

    MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    public RecipesRepository(Application application) {
        BMIDatabase db = BMIDatabase.getInstance(application);
        recipesDao = db.recipesDao();
    }

    public void getCategories() {

        Call<Categories> listCall = FoodApi.getApi().getCategories();
        listCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {

                _categoriesList.postValue(response.body().getCategories());

            }


            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                _errorMessage.postValue(t.getMessage());
            }


        });

    }

    public void getMealsByCategoryName(String categoryName) {
        Call<Meals> mealsCall = FoodApi.getApi().getMealByCategory(categoryName);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                _mealsList.postValue(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                _errorMessage.postValue(t.getMessage());

            }
        });
    }

    public void getMealRecipes(String mealId) {
        Call<Meals> mealCall = FoodApi.getApi().getMealRecipesById(mealId);
        mealCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                _meal.postValue(response.body().getMeals().get(0));
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                _errorMessage.postValue(t.getMessage());

            }
        });
    }

    public void getRecipesByName(String query) {
        Call<Meals> mealsCall = FoodApi.getApi().getMealByName(query);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                _mealsByQuery.postValue(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                _errorMessage.postValue(t.getMessage());

            }
        });
    }

    public void addToDB(Meals.Meal value) {
        recipesDao.insert(value);
    }

    public void isSave(String mealId) {

        _isSave.postValue(recipesDao.isSave(mealId));
    }

    public void deleteFromDb(Meals.Meal value) {
        recipesDao.deleteItem(value.getIdMeal());
    }

    public void getAllFavourite() {
        _favMealsList.postValue(recipesDao.getAllMeals());
    }


    public void getMealRecipesFromDb(String mealId) {
        _meal.postValue(recipesDao.getMealRecipes(mealId));
    }
}
