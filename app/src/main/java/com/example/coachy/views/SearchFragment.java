package com.example.coachy.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coachy.R;
import com.example.coachy.adapters.SearchAdapter;
import com.example.coachy.adapters.TrainingTypeAdapter;
import com.example.coachy.models.Coach;
import com.example.coachy.models.TrainingType;
import com.paulrybitskyi.persistentsearchview.PersistentSearchView;
import com.paulrybitskyi.persistentsearchview.listeners.OnSearchConfirmedListener;
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    private List<Coach> mDataSet;
    private PersistentSearchView persistentSearchView;
    private SearchAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        initialTrainingListImages(v);

        persistentSearchView = v.findViewById(R.id.persistentSearchView);

        persistentSearchView.setOnLeftBtnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Handle the left button click
                Toast.makeText(getContext(), "ye", Toast.LENGTH_SHORT).show();
            }

        });

        persistentSearchView.setOnClearInputBtnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Handle the clear input button click
                Toast.makeText(getContext(), "yes", Toast.LENGTH_SHORT).show();
            }

        });

        // Setting a delegate for the voice recognition input
        persistentSearchView.setVoiceRecognitionDelegate(new VoiceRecognitionDelegate(this));

        persistentSearchView.setOnSearchConfirmedListener(new OnSearchConfirmedListener() {

            @Override
            public void onSearchConfirmed(PersistentSearchView searchView, String query) {
                // Handle a search confirmation. This is the place where you'd
                // want to perform a search against your data provider.
                adapter.getFilter().filter(query);
                Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();

            }

        });

        // Disabling the suggestions since they are unused in
        // the simple implementation
        persistentSearchView.setSuggestionsDisabled(false);


        return v;
    }




    private void initialTrainingListImages(View view){
        mDataSet = new ArrayList<>();
        mDataSet.add(new Coach(0,"shon ohana",24,"blala","0541111111","1 year",null,null,null,null));
        mDataSet.add(new Coach(0,"eden barhum",24,"blala","0541111111","1 year",null,null,null,null));
        mDataSet.add(new Coach(0,"dolev ifergan",24,"blala","0541111111","1 year",null,null,null,null));


        RecyclerView searchRecycler = view.findViewById(R.id.rv_search);
        adapter = new SearchAdapter(getContext(),mDataSet);

        searchRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        searchRecycler.setAdapter(adapter);

    }

}