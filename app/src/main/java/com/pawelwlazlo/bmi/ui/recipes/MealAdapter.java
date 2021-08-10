package com.pawelwlazlo.bmi.ui.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.recipes.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    private final List<Meals.Meal> mealList;
    private final String fragmentName;


    public MealAdapter(List<Meals.Meal> mealList, String fragmentName) {
        this.mealList = mealList;
        this.fragmentName = fragmentName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bundle bundle = new Bundle();
        String mealsName = mealList.get(position).getStrMeal();
        String mealsId = mealList.get(position).getIdMeal();
        holder.mealsName.setText(mealsName);
        Picasso.get().load(mealList.get(position).getStrMealThumb()).into(holder.mealImage);


        holder.itemView.setOnClickListener(view -> {
            bundle.putString("mealId", mealsId);
            if (fragmentName.contains("RecipesFragment")) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_recipe_main_to_navigation_recipe_details, bundle);
            } else {
                Navigation.findNavController(view).navigate(R.id.action_navigation_recipe_category_details_to_navigation_recipe_details, bundle);

            }


        });


    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mealsName;
        private final ImageView mealImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealsName = itemView.findViewById(R.id.meals_name);
            mealImage = itemView.findViewById(R.id.imageMeal);


        }
    }
}
