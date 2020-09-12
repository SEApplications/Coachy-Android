package com.example.coachy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachy.R;
import com.example.coachy.models.TrainingType;

import java.util.ArrayList;
import java.util.List;

public class TrainingTypeAdapter extends RecyclerView.Adapter<TrainingTypeAdapter.ViewHolder>  {

    //for the images
    private List<TrainingType> mDataSet;
    private Context context;

    public TrainingTypeAdapter(Context context, List<TrainingType> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainig_type_row,parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.trainingType.setImageResource(mDataSet.get(position).getTrainingImage());

        holder.trainingType.setOnClickListener(b->{
            Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView trainingType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trainingType = itemView.findViewById(R.id.training_type_iv);

        }
    }
}
