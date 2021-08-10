package com.pawelwlazlo.bmi.ui.recipes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.recipes.Meals;

import java.util.List;

public class FavouriteRecipesFragment extends Fragment {

    private RecipesViewModel recipesViewModel;
    private RecyclerView recyclerView;

    public FavouriteRecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recipesViewModel =
                new ViewModelProvider(this).get(RecipesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourite_recipes, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.fav_recipes_rv);

        recipesViewModel.getAllFavourite();

        recipesViewModel.favMealsList.observe(getViewLifecycleOwner(), new Observer<List<Meals.Meal>>() {
            @Override
            public void onChanged(List<Meals.Meal> meals) {

                FavMealAdapter adapter = new FavMealAdapter(meals);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

            }
        });
    }
}