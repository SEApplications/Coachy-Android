package com.example.coachy.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coachy.R;
import com.example.coachy.adapters.TrainingTypeAdapter;
import com.example.coachy.models.TrainingType;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TrainingTypeFragment extends Fragment {

    private List<TrainingType> mDataSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_types, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialTrainingListImages(view);



    }

    private void initialTrainingListImages(View view){
        mDataSet = new ArrayList<>();
//        mDataSet.add(new TrainingType(0,R.drawable.which_training,"which training"));
        mDataSet.add(new TrainingType(1,R.drawable.functional,"functional"));
        mDataSet.add(new TrainingType(2,R.drawable.crossfit,"crossfit"));
        mDataSet.add(new TrainingType(3,R.drawable.rubber_band,"rubber band"));
        mDataSet.add(new TrainingType(4,R.drawable.abs,"abs"));
        mDataSet.add(new TrainingType(5,R.drawable.yoga,"yoga"));
        mDataSet.add(new TrainingType(6,R.drawable.pilatis,"pilatis"));
        mDataSet.add(new TrainingType(7,R.drawable.aerobic,"aerobic"));

        RecyclerView trainingRecycler = view.findViewById(R.id.rv_training_types);
        TrainingTypeAdapter adapter = new TrainingTypeAdapter(getContext(),mDataSet);

        trainingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        trainingRecycler.setAdapter(adapter);

    }
}