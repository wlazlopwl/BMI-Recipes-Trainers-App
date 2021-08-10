package com.pawelwlazlo.bmi.ui.recipes;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.recipes.Categories;
import com.pawelwlazlo.bmi.data.recipes.Meals;

import java.util.List;

public class RecipesFragment extends Fragment {

    private RecipesViewModel recipesViewModel;
    private RecyclerView recyclerView;
    private RecyclerView mealsQueryRecyclerView;
    private TextView searchQuote;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipesViewModel =
                new ViewModelProvider(this).get(RecipesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recipes, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.category_rv);
        mealsQueryRecyclerView = view.findViewById(R.id.meals_query__rv);
        searchQuote = view.findViewById(R.id.editTextSearch);

        recipesViewModel.getCategories();
        recipesViewModel.errorMessage.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }
        });

        searchQuote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    mealsQueryRecyclerView.setVisibility(View.VISIBLE);
                    recipesViewModel.getRecipesByName(s.toString());
                    recipesViewModel.mealByNameLiveData.observe(getViewLifecycleOwner(), new Observer<List<Meals.Meal>>() {
                        @Override
                        public void onChanged(List<Meals.Meal> meals) {

                            MealAdapter adapter = new MealAdapter(meals, "RecipesFragment");
                            mealsQueryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            mealsQueryRecyclerView.setNestedScrollingEnabled(false);
                            mealsQueryRecyclerView.setHasFixedSize(true);
                            mealsQueryRecyclerView.setAdapter(adapter);

                            adapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    mealsQueryRecyclerView.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        recipesViewModel.categoriesList.observe(getViewLifecycleOwner(), new Observer<List<Categories.Category>>() {
            @Override
            public void onChanged(List<Categories.Category> categories) {

                CategoryAdapter adapter = new CategoryAdapter(categories);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

            }


        });
    }
}