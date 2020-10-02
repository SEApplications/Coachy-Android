package com.example.coachy.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coachy.R;
import com.example.coachy.adapters.TrainingListAdapter;
import com.example.coachy.models.Coach;
import com.example.coachy.models.TrainingType;
import com.example.coachy.models.UploadFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TrainingListFragment extends Fragment {

    private TrainingType type;
    private List<Coach> coachList;
    private DatabaseReference databaseRef;
    private List<UploadFirebase> uploads;

    public TrainingListFragment(TrainingType trainingType){
        this.type = trainingType;
    }

    @Override
    public void onStart() {
        super.onStart();
        initCoachesFromFirebase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_trainings, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        trainingRecycler.setHasFixedSize(true);
        trainingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        initCoachesFromFirebase();


    }

    private void init(View view){
        trainingRecycler = view.findViewById(R.id.rv_training_coaches);
    }

    RecyclerView trainingRecycler;
    private List<Coach> initCoachesFromFirebase(){
        List<Coach> coaches = new ArrayList<Coach>();

//        databaseRef.getDatabase().setPersistenceEnabled(true);
        databaseRef = FirebaseDatabase.getInstance().getReference("coaches");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Coach coach = snapshot.getValue(Coach.class);
                    coaches.add(coach);

                }
                TrainingListAdapter adapter = new TrainingListAdapter(getContext(),coaches);
                trainingRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return coaches;
    }

}