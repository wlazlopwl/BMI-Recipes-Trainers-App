package com.pawelwlazlo.bmi.ui.recipes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainTabsAdapter extends FragmentStateAdapter {

    public MainTabsAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==0) return new RecipesFragment();
        else  return new FavouriteRecipesFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
