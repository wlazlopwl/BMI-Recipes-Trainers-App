package com.pawelwlazlo.bmi.ui.trainers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.trainer.Trainer;


public class TrainersDetails extends Fragment {
    private Trainer trainer;

    public TrainersDetails() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        trainer = (Trainer) getArguments().getSerializable("trainer");
        return inflater.inflate(R.layout.fragment_trainers_details, container, false);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView name = view.findViewById(R.id.trainerName);
        TextView experience = view.findViewById(R.id.experience);
        TextView pupils = view.findViewById(R.id.pupils);
        TextView email = view.findViewById(R.id.email);
        TextView phone = view.findViewById(R.id.phone);
        TextView skype = view.findViewById(R.id.skype);
        TextView description = view.findViewById(R.id.description);


        name.setText(trainer.firstName + " " + trainer.lastName);
        experience.setText(String.valueOf(trainer.experience));
        pupils.setText(String.valueOf(trainer.pupils));
        email.setText(trainer.email);
        phone.setText(trainer.phone);
        skype.setText(trainer.skype);
        description.setText(trainer.description);
    }
}