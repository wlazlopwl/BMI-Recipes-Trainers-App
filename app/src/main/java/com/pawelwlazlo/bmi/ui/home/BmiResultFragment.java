package com.pawelwlazlo.bmi.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.bmi.BmiItem;
import com.pawelwlazlo.util.BMITypeResult;

import java.util.List;

public class BmiResultFragment extends Fragment implements BmiResultAdapter.OnItemClickListener {
    private HomeViewModel homeViewModel;
    private TextView bmiValue;
    private TextView result;
    private BmiItem bmiItem;
    private Button button;
    private RecyclerView recyclerView;
    private BmiResultAdapter adapter;


    public BmiResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bmiItem = (BmiItem) getArguments().getSerializable("bmiItem");
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bmi_result, container, false);


        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bmiValue = view.findViewById(R.id.bmi_value);
        result = view.findViewById(R.id.textView3);
        button = view.findViewById(R.id.save_bmi_result);
        recyclerView = view.findViewById(R.id.bmi_result_rv);
        bmiValue.setText(String.valueOf(bmiItem.getResult()));


        result.setText(bmiItem.getTypeResult() + " WEIGHT");


        homeViewModel.getAllResult().observe(getViewLifecycleOwner(), new Observer<List<BmiItem>>() {
            @Override
            public void onChanged(List<BmiItem> bmiItems) {
                initRecyclerView(bmiItems);

            }


        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.saveBmiResult(bmiItem);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "Usunięto zapisany rezultat", Toast.LENGTH_SHORT).show();
        homeViewModel.deleteItem(position);

    }

    private void initRecyclerView(List<BmiItem> bmiItems) {
        adapter = new BmiResultAdapter(bmiItems, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}