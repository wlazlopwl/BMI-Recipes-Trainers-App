package com.pawelwlazlo.bmi.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pawelwlazlo.bmi.R;
import com.pawelwlazlo.bmi.data.bmi.BmiItem;
import com.pawelwlazlo.util.BMITypeResult;

import java.util.List;

public class BmiResultAdapter extends RecyclerView.Adapter<BmiResultAdapter.ViewHolder> {

    private final List<BmiItem> bmiResultList;
    private final OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public BmiResultAdapter(List<BmiItem> bmiResultList, OnItemClickListener mOnItemClickListener) {
        this.bmiResultList = bmiResultList;
        this.mOnItemClickListener = mOnItemClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bmi_result_rv_item, parent, false);
        return new ViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (bmiResultList.get(position).getTypeResult() == BMITypeResult.UNDERWEIGHT)
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF0ad2b5"));
        else if (bmiResultList.get(position).getTypeResult() == BMITypeResult.CORRECT)
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF2cb508"));
        else if (bmiResultList.get(position).getTypeResult() == BMITypeResult.OVERWEIGHT)
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FFfbb004"));
        else if (bmiResultList.get(position).getTypeResult() == BMITypeResult.OBESITY)
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FFf26408"));

        holder.height.setText(String.valueOf(bmiResultList.get(position).getHeight()));
        holder.weight.setText(String.valueOf(bmiResultList.get(position).getWeight()));
        holder.age.setText(String.valueOf(bmiResultList.get(position).getAge()));
        holder.result.setText(String.valueOf(bmiResultList.get(position).getResult()).substring(0, 4));
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(bmiResultList.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return bmiResultList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView height;
        private final TextView weight;
        private final TextView age;
        private final TextView result;
        private final CardView cardView;
        private final ImageView deleteIcon;
        OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            height = itemView.findViewById(R.id.result_height);
            age = itemView.findViewById(R.id.result_age);
            weight = itemView.findViewById(R.id.result_weight);
            result = itemView.findViewById(R.id.result_value);
            cardView = itemView.findViewById(R.id.bmi_result_cardview);
            deleteIcon = itemView.findViewById(R.id.bmi_delete_item_iv);
            this.onItemClickListener = onItemClickListener;
            deleteIcon.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
