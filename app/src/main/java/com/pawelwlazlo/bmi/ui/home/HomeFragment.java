package com.pawelwlazlo.bmi.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.pawelwlazlo.bmi.R;

public class HomeFragment extends Fragment {

    private CardView maleCard;
    private CardView femaleCard;
    private SeekBar seekBar;
    private TextView height;
    private TextView age;
    private TextView weight;
    private RelativeLayout weightPlus;
    private RelativeLayout weightMinus;
    private RelativeLayout agePlus;
    private RelativeLayout ageMinus;
    private Button calculate;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bmi, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        maleCard = view.findViewById(R.id.cardView_male);
        femaleCard = view.findViewById(R.id.cardView_female);
        seekBar = view.findViewById(R.id.Seekbar);
        height = view.findViewById(R.id.height_txt);
        weight = view.findViewById(R.id.weight);
        weightPlus = view.findViewById(R.id.weight_plus);
        weightMinus = view.findViewById(R.id.weight_minus);
        age = view.findViewById(R.id.age);
        agePlus = view.findViewById(R.id.age_plus);
        ageMinus = view.findViewById(R.id.age_minus);
        calculate = view.findViewById(R.id.calculate);

        maleCard.setOnClickListener(v -> homeViewModel._isMale.postValue(true));
        femaleCard.setOnClickListener(v -> homeViewModel._isMale.postValue(false));
        weightPlus.setOnClickListener(v -> homeViewModel._weight.postValue(Integer.parseInt(weight.getText().toString()) + 1));
        weightMinus.setOnClickListener(v -> homeViewModel._weight.postValue(Integer.parseInt(weight.getText().toString()) - 1));
        agePlus.setOnClickListener(v -> homeViewModel._age.postValue(Integer.parseInt(age.getText().toString()) + 1));
        ageMinus.setOnClickListener(v -> homeViewModel._age.postValue(Integer.parseInt(age.getText().toString()) - 1));

        homeViewModel.getAge().observe(getViewLifecycleOwner(), ageValue -> {
            age.setText(String.valueOf(ageValue));
        });

        homeViewModel.getWeight().observe(getViewLifecycleOwner(), weightValue -> {
            weight.setText(String.valueOf(weightValue));
        });

        homeViewModel.getHeight().observe(getViewLifecycleOwner(), heightValue -> {
            seekBar.setProgress(heightValue);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                height.setText(String.valueOf(progress));
                homeViewModel._height.postValue(Integer.parseInt(height.getText().toString()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        homeViewModel.isMale().observe(getViewLifecycleOwner(), isMale -> {
            if (isMale) {
                maleCard.setCardBackgroundColor(0xff2ecc71);
                femaleCard.setCardBackgroundColor(0xfffcfdff);

            } else {
                femaleCard.setCardBackgroundColor(0xff2ecc71);
                maleCard.setCardBackgroundColor(0xfffcfdff);
            }

        });

        calculate.setOnClickListener(v -> {

            homeViewModel.calculateBMIValue();
            homeViewModel.getBmiItem().observe(getViewLifecycleOwner(), value -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bmiItem", value);
                Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_bmiResultFragment, bundle);

            });
        });

    }
}