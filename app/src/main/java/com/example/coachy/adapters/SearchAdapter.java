package com.example.coachy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachy.R;
import com.example.coachy.models.Coach;
import com.example.coachy.models.TrainingType;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {


    private List<Coach> mDataSet;
    private List<Coach> mDataFilter;
    private Context context;

    public SearchAdapter(Context context, List<Coach> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_coach_row,parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(mDataSet.get(position).getFullName());
        holder.age.setText(String.valueOf(mDataSet.get(position).getAge()));
        holder.seniority.setText(mDataSet.get(position).getSeniority());


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if (key.isEmpty()) {
                    mDataFilter = mDataSet;
                } else {
                    List<Coach> list_filtered = new ArrayList<>();
                    for (Coach row : mDataSet) {
                        if (row.getFullName().toLowerCase().contains(key.toLowerCase())) {
                            list_filtered.add(row);
                        }
                    }

                    mDataFilter = list_filtered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataFilter.clear();
                mDataFilter = (List<Coach>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView age;
        private TextView seniority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_tv);
            age = itemView.findViewById(R.id.age_tv);
            seniority = itemView.findViewById(R.id.seniority_tv);
        }
    }
}

