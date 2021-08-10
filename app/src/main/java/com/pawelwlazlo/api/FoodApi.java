package com.pawelwlazlo.api;

import com.pawelwlazlo.bmi.data.recipes.Categories;
import com.pawelwlazlo.bmi.data.recipes.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("lookup.php")
    Call<Meals> getMealRecipesById(@Query("i") String mealId);

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);

    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String query);





    static FoodApi getApi() {
        return FoodClient.getFoodClient().create(FoodApi.class);
    }

}

