package com.pawelwlazlo.bmi.ui.trainers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.trainer.Trainer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TrainersFragment extends Fragment {

    private TrainersViewModel trainersViewModel;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trainersViewModel =
                new ViewModelProvider(this).get(TrainersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trainers, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.trainers_rv);

        List<Trainer> trainerList = setTrainerList();


        trainersViewModel.getAllTrainers().observe(getViewLifecycleOwner(), new Observer<List<Trainer>>() {
            @Override
            public void onChanged(List<Trainer> trainers) {
                TrainersAdapter adapter = new TrainersAdapter(trainerList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);


            }
        });


    }

    @NotNull
    private List<Trainer> setTrainerList() {
        List<Trainer> trainerList = new ArrayList<>();
        Trainer trainer = new Trainer();
        trainer.setFirstName("Monika");
        trainer.setLastName("Kowalska");
        trainer.setEmail("monika.kowalska@gmail.com");
        trainer.setPhone("665665665");
        trainer.setSkype("monikam");
        trainer.setExperience(3);
        trainer.setPupils(20);
        trainer.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " +
                "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
                "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum");
        trainerList.add(trainer);

        Trainer trainer1 = new Trainer();
        trainer1.setFirstName("Jan");
        trainer1.setLastName("Nowak");
        trainer1.setEmail("jan.nowak@gmail.com");
        trainer1.setPhone("665665665");
        trainer1.setSkype("jannowakskype");
        trainer1.setExperience(8);
        trainer1.setPupils(25);
        trainer1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " +
                "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
                "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum");
        trainerList.add(trainer1);

        Trainer trainer2 = new Trainer();
        trainer2.setFirstName("Anna");
        trainer2.setLastName("Sikorska");
        trainer2.setEmail("sikorska.a@gmail.com");
        trainer2.setPhone("665665665");
        trainer2.setSkype("sikorskaan");
        trainer2.setExperience(1);
        trainer2.setPupils(10);
        trainer2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " +
                "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
                "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum");
        trainerList.add(trainer2);

        Trainer trainer3 = new Trainer();
        trainer3.setFirstName("Adam");
        trainer3.setLastName("Kowalski");
        trainer3.setEmail("adam.kowalski@gmail.com");
        trainer3.setPhone("665665665");
        trainer3.setSkype("kowalskia");
        trainer3.setExperience(6);
        trainer3.setPupils(12);
        trainer3.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " +
                "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
                "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum");
        trainerList.add(trainer3);
        trainersViewModel.insertTrainer(trainerList);
        return trainerList;
    }
}