package com.pawelwlazlo.bmi.ui.trainers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.trainer.Trainer;

import java.util.List;

public class TrainersAdapter extends RecyclerView.Adapter<TrainersAdapter.ViewHolder> {

    private final List<Trainer> trainerList;


    public TrainersAdapter(List<Trainer> trainerList) {
        this.trainerList = trainerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainer_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trainer trainer = trainerList.get(position);
        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                holder.relativeLayout.setBackgroundResource(R.drawable.fit1);
                break;
            case 1:
                holder.relativeLayout.setBackgroundResource(R.drawable.fit2);
                break;
            case 2:
                holder.relativeLayout.setBackgroundResource(R.drawable.fit3);
                break;
            case 3:
                holder.relativeLayout.setBackgroundResource(R.drawable.fit4);
                break;


        }
        holder.trainerName.setText(trainerList.get(position).firstName);
        holder.trainerLastName.setText(trainerList.get(position).lastName);
        bundle.putSerializable("trainer", trainer);
        holder.itemView.setOnClickListener(view ->

                Navigation.findNavController(view).navigate(R.id.action_navigation_trainers_to_trainersDetails, bundle));
    }

    @Override
    public int getItemCount() {
        return trainerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final RelativeLayout relativeLayout;
        private final TextView trainerName;
        private final TextView trainerLastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.trainer_relative_layout);
            trainerName = itemView.findViewById(R.id.trainerName);
            trainerLastName = itemView.findViewById(R.id.trainerLastName);


        }
    }
}
