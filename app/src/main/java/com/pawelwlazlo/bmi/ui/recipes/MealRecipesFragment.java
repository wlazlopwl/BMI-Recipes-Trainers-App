package com.pawelwlazlo.bmi.ui.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.recipes.Meals;
import com.squareup.picasso.Picasso;

public class MealRecipesFragment extends Fragment {
    private RecipesViewModel recipesViewModel;
    private String mealId;
    private ProgressBar progressBar;
    private ImageView mealImage;
    private TextView instruction;
    private TextView ingredients;
    private TextView measures;
    private TextView country;
    private TextView category;
    private FloatingActionButton floatingActionButton;
    private Boolean isSave;

    public MealRecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mealId = getArguments().getString("mealId");
        recipesViewModel =
                new ViewModelProvider(this).get(RecipesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_meal_recipes, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.progressBar);
        mealImage = view.findViewById(R.id.mealThumb);
        instruction = view.findViewById(R.id.instructions);
        ingredients = view.findViewById(R.id.ingredient);
        measures = view.findViewById(R.id.measure);
        country = view.findViewById(R.id.country);
        category = view.findViewById(R.id.category);
        floatingActionButton = view.findViewById(R.id.floating_addToDb);


        recipesViewModel.isSave(mealId);
        recipesViewModel.isSave.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                isSave = integer > 0;
                if (isSave) {
                    recipesViewModel.getMealRecipesFromDB(mealId);
                    floatingActionButton.setImageResource(R.drawable.fav);
                } else {
                    recipesViewModel.getMealRecipes(mealId);
                    floatingActionButton.setImageResource(R.drawable.fav_border);
                }

            }

        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSave) {
                    recipesViewModel.deleteFromDB(recipesViewModel.mealLiveData.getValue());
                    Toast.makeText(getContext(), "Delete from favourites", Toast.LENGTH_SHORT).show();
                } else {

                    recipesViewModel.addToDb(recipesViewModel.mealLiveData.getValue());
                    Toast.makeText(getContext(), "Added to favourites", Toast.LENGTH_SHORT).show();

                }
                recipesViewModel.isSave(mealId);

            }
        });


        recipesViewModel.mealLiveData.observe(getViewLifecycleOwner(), new Observer<Meals.Meal>() {
            @Override
            public void onChanged(Meals.Meal meal) {
//                recipesViewModel.isSave(mealId);
                progressBar.setVisibility(View.INVISIBLE);
                Picasso.get().load(meal.getStrMealThumb()).into(mealImage);
                instruction.setText(meal.getStrInstructions());
                country.setText(meal.getStrArea());
                category.setText(meal.getStrCategory());

                setIngredients(meal);
            }
        });

        recipesViewModel.errorMessage.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setIngredients(Meals.Meal meal) {
        ingredients.setText("");
        measures.setText("");
        if (meal.getStrIngredient1() != null) {
            if (!meal.getStrIngredient1().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient1());
        }


        if (meal.getStrIngredient2() != null) {
            if (!meal.getStrIngredient2().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient2());
        }


        if (meal.getStrIngredient3() != null) {
            if (!meal.getStrIngredient3().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient3());
        }

        if (meal.getStrIngredient4() != null) {
            if (!meal.getStrIngredient4().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient4());
        }

        if (meal.getStrIngredient5() != null) {
            if (!meal.getStrIngredient5().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient5());
        }

        if (meal.getStrIngredient6() != null) {
            if (!meal.getStrIngredient6().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient6());
        }

        if (meal.getStrIngredient7() != null) {
            if (!meal.getStrIngredient7().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient7());
        }

        if (meal.getStrIngredient8() != null) {
            if (!meal.getStrIngredient8().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient8());
        }

        if (meal.getStrIngredient9() != null) {
            if (!meal.getStrIngredient9().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient9());
        }

        if (meal.getStrIngredient10() != null) {
            if (!meal.getStrIngredient10().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient10());
        }

        if (meal.getStrIngredient11() != null) {
            if (!meal.getStrIngredient11().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient11());
        }

        if (meal.getStrIngredient12() != null) {
            if (!meal.getStrIngredient12().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient12());
        }

        if (meal.getStrIngredient13() != null) {
            if (!meal.getStrIngredient13().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient13());
        }

        if (meal.getStrIngredient14() != null) {
            if (!meal.getStrIngredient14().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient14());
        }

        if (meal.getStrIngredient15() != null) {
            if (!meal.getStrIngredient15().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient15());
        }

        if (meal.getStrIngredient16() != null) {
            if (!meal.getStrIngredient16().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient16());
        }

        if (meal.getStrIngredient17() != null) {
            if (!meal.getStrIngredient17().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient17());
        }
        if (meal.getStrIngredient18() != null) {
            if (!meal.getStrIngredient18().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient18());
        }
        if (meal.getStrIngredient19() != null) {
            if (!meal.getStrIngredient19().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient19());
        }
        if (meal.getStrIngredient20() != null) {
            if (!meal.getStrIngredient20().isEmpty())
                ingredients.append("\n \u2022 " + meal.getStrIngredient20());
        }


        if (meal.getStrMeasure1() != null) {
            if (!meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure1());
        }
        if (meal.getStrMeasure2() != null) {
            if (!meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure2());
        }

        if (meal.getStrMeasure3() != null) {
            if (!meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure3());
        }
        if (meal.getStrMeasure4() != null) {
            if (!meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure4());
        }
        if (meal.getStrMeasure5() != null) {
            if (!meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure5());
        }
        if (meal.getStrMeasure6() != null) {
            if (!meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure6());
        }
        if (meal.getStrMeasure7() != null) {
            if (!meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure7());
        }

        if (meal.getStrMeasure8() != null) {
            if (!meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure8());
        }
        if (meal.getStrMeasure9() != null) {
            if (!meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure9());
        }
        if (meal.getStrMeasure10() != null) {
            if (!meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure10());
        }
        if (meal.getStrMeasure11() != null) {
            if (!meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure11());
        }
        if (meal.getStrMeasure12() != null) {
            if (!meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure12().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure12());
        }
        if (meal.getStrMeasure13() != null) {
            if (!meal.getStrMeasure13().isEmpty() && !Character.isWhitespace(meal.getStrMeasure13().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure13());
        }
        if (meal.getStrMeasure14() != null) {
            if (!meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure14());
        }
        if (meal.getStrMeasure15() != null) {
            if (!meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure15());
        }
        if (meal.getStrMeasure16() != null) {
            if (!meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure16());
        }
        if (meal.getStrMeasure17() != null) {
            if (!meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure17());
        }
        if (meal.getStrMeasure18() != null) {
            if (!meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure18());
        }
        if (meal.getStrMeasure19() != null) {
            if (!meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure19());
        }
        if (meal.getStrMeasure20() != null) {
            if (!meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0)))
                measures.append("\n : " + meal.getStrMeasure20());
        }


    }

}