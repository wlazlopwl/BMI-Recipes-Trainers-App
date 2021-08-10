package com.pawelwlazlo.bmi.ui.recipes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pawelwlazlo.bmi.data.recipes.Categories;
import com.pawelwlazlo.bmi.data.recipes.Meals;
import com.pawelwlazlo.bmi.data.recipes.RecipesRepository;

import java.util.List;

public class RecipesViewModel extends AndroidViewModel {

    private final RecipesRepository recipesRepository;

    LiveData<List<Categories.Category>> categoriesList;
    LiveData<List<Meals.Meal>> mealsListByCategory;
    LiveData<List<Meals.Meal>> favMealsList;
    LiveData<Meals.Meal> mealLiveData;
    LiveData<List<Meals.Meal>> mealByNameLiveData;
    LiveData<Integer> isSave;
    LiveData<String> errorMessage;

    public RecipesViewModel(@NonNull Application application) {
        super(application);
        recipesRepository = new RecipesRepository(application);
        categoriesList = recipesRepository.categoriesList;
        mealsListByCategory = recipesRepository.mealsList;
        mealLiveData = recipesRepository.meal;
        isSave = recipesRepository.isSave;
        favMealsList = recipesRepository.favMealsList;
        mealByNameLiveData = recipesRepository.mealsByQuery;
        errorMessage = recipesRepository.errorMessage;

    }


    public void isSave(String mealId) {

        recipesRepository.isSave(mealId);
    }

    public void getCategories() {

        recipesRepository.getCategories();
    }

    public void getMealsByCategory(String categoryName) {
        recipesRepository.getMealsByCategoryName(categoryName);
    }

    public void getMealRecipes(String mealId) {

        recipesRepository.getMealRecipes(mealId);
    }

    public void addToDb(Meals.Meal value) {
        recipesRepository.addToDB(value);
    }

    public void deleteFromDB(Meals.Meal value) {
        recipesRepository.deleteFromDb(value);
    }

    public void getAllFavourite() {
        recipesRepository.getAllFavourite();
    }

    public void getRecipesByName(String query) {
        recipesRepository.getRecipesByName(query);
    }

    public void getMealRecipesFromDB(String mealId) {
        recipesRepository.getMealRecipesFromDb(mealId);
    }
}