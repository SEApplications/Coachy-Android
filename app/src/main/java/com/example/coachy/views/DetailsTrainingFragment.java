package com.example.coachy.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coachy.R;
import com.example.coachy.adapters.DetailsTrainingAdapter;
import com.example.coachy.models.Coach;
import com.example.coachy.models.TrainingType;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailsTrainingFragment extends Fragment {

    private TrainingType type;
    private List<Coach> coachList;

    public DetailsTrainingFragment(TrainingType trainingType){
        this.type = trainingType;
        coachList = initCoach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

    }

    private void init(View view){

        RecyclerView trainingRecycler = view.findViewById(R.id.rv_training_coaches);
        DetailsTrainingAdapter adapter = new DetailsTrainingAdapter(getContext(),coachList);

        trainingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        trainingRecycler.setAdapter(adapter);

    }

    //only for check
    private List<Coach> initCoach(){
        List<Coach> coachList = new ArrayList<Coach>();
        for(int i = 0; i < 5; i++){
            coachList.add(new Coach());
        }

        return coachList;
    }

}