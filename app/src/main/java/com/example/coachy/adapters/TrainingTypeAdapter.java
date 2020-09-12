package com.example.coachy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachy.R;
import com.example.coachy.models.TrainingType;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TrainingTypeAdapter extends RecyclerView.Adapter<TrainingTypeAdapter.ViewHolder>  {

    //for the images
    private List<TrainingType> mDataSet;
    private Context context;
    private OnClickSelected callback;

    public TrainingTypeAdapter(Context context, List<TrainingType> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
        this.callback =(OnClickSelected)context;

    }

    public interface OnClickSelected{

        public void onTrainingTypeSelected(TrainingType trainingType);

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

        holder.cardView.setOnClickListener(b->{
            callback.onTrainingTypeSelected(mDataSet.get(position));
        });
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        callback = null;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView trainingType;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trainingType = itemView.findViewById(R.id.training_type_iv);
            cardView = itemView.findViewById(R.id.parent_layout);

        }
    }


}
