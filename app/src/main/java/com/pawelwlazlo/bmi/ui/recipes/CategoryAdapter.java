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
import com.pawelwlazlo.bmi.data.recipes.Categories;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final List<Categories.Category> categoryList;


    public CategoryAdapter(List<Categories.Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bundle bundle = new Bundle();
        String categoryName = categoryList.get(position).getStrCategory();
        holder.categoryName.setText(categoryName);
        Picasso.get().load(categoryList.get(position).getStrCategoryThumb()).into(holder.categoryImage);

        bundle.putString("categoryName", categoryName);
        holder.itemView.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_navigation_recipe_main_to_navigation_recipe_category_details, bundle));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryName;
        private final ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryImage = itemView.findViewById(R.id.category_image);


        }
    }
}
