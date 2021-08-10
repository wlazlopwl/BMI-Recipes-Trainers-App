package com.pawelwlazlo.bmi.data.recipes;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipesDao {

    @Query("SELECT * FROM Meal")
    List<Meals.Meal> getAllMeals();


    @Insert
    void insert(Meals.Meal meal);

    @Query("DELETE FROM Meal")
    void deleteAll();

    @Query("DELETE FROM MEAL WHERE idMeal LIKE '%' || :mealId || '%'")
    void deleteItem(String mealId);

    @Query("SELECT COUNT(id) FROM Meal WHERE idMeal LIKE '%' || :mealId || '%'")
    Integer isSave(String mealId);

    @Query("SELECt * FROM MEAL WHERE idMeal=:mealId")
    Meals.Meal getMealRecipes(String mealId);
}
