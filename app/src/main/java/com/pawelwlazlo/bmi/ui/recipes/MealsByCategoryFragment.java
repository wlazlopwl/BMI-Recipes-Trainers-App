package com.pawelwlazlo.bmi.ui.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.recipes.Meals;

import java.util.List;


public class MealsByCategoryFragment extends Fragment {

    private String categoryName;
    private RecipesViewModel recipesViewModel;
    private RecyclerView recyclerView;

    public MealsByCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        categoryName = getArguments().getString("categoryName");
        recipesViewModel =
                new ViewModelProvider(this).get(RecipesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_meals_by_category, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.meals_rv);

        recipesViewModel.getMealsByCategory(categoryName);

        recipesViewModel.mealsListByCategory.observe(getViewLifecycleOwner(), new Observer<List<Meals.Meal>>() {
            @Override
            public void onChanged(List<Meals.Meal> meals) {

                MealAdapter adapter = new MealAdapter(meals, "MealsByCategoryFragment");
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

            }
        });

        recipesViewModel.errorMessage.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }
        });
    }
}