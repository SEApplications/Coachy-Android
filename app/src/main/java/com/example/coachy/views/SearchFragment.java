package com.example.coachy.views;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import java.util.Arrays;
import java.util.List;


public class SearchFragment extends Fragment {

    private ArrayList<Coach> mDataSet;
    private SearchView mSearchView;
    private SearchAdapter adapter;
    public  ArrayList<Coach>  temp_search, mShowList;
    private RecyclerView searchRecycler;
    private int SEARCH_LENGTH = 0;
    private ArrayList<ArrayList<Coach>> searchArrayMatrix = new ArrayList<>();


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        initialTrainingListImages(v);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        mSearchView = v.findViewById(R.id.search);
        mSearchView.setFocusable(false);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doSearch(newText, false);
                return false;
            }
        });



        return v;
    }



    public void doSearch(String query, boolean voice) {
        List<String> words = new ArrayList<>();
        if (query.contains(" ")) {
            words.addAll(Arrays.asList(query.split("\\s+")));
        } else {
            words.add(query);
        }

            try {
                if (voice)
                    mSearchView.setQuery(query, false);
                //Empty List
                if (query.length() == 0) {
                    temp_search = getShowList();
                    searchArrayMatrix = new ArrayList<>();
                    SEARCH_LENGTH = query.length();
//                    priceListName = "מציג קבוצה: " + String.valueOf(itemGroupList.getItemGroupsList().get(groupIdForTextView).getItmsGrpNam());
                    setItemsRecyclerAdapter(temp_search);
                    return;
                }

                //Case Writing text
                if (SEARCH_LENGTH <= query.length()) {

                    temp_search = new ArrayList<>();
                    if (searchArrayMatrix.size() == 0)
                        addSearchListToMatrix(getShowList(), query.length());

                    for (Coach m : searchArrayMatrix.get(searchArrayMatrix.size() - 1)) {
                        boolean match = true;
                        for (int i = 0; i < words.size(); i++) {
                            match = m.getFullName().toLowerCase().contains(words.get(i).toLowerCase());
                            if (!match)
                                break;
                        }
                        if (match)
                            temp_search.add(m);
                    }
                    addSearchListToMatrix(temp_search, query.length());
                } else {//Case Deleting text
                    //searchArrayMatrix.remove(searchArrayMatrix.size()-1);
                    removeSearchListFromMatrix(temp_search, query.length() - 1);
                    temp_search = searchArrayMatrix.get(searchArrayMatrix.size() - 1);
                }

                SEARCH_LENGTH = query.length();

//                priceListName = "מציג תוצאות חיפוש";
                setItemsRecyclerAdapter(temp_search);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void setItemsRecyclerAdapter(List<Coach> mDataSet) {
        if (mDataSet == null) return;


        if (searchRecycler.getAdapter() != null)
            ((SearchAdapter) searchRecycler.getAdapter()).setDataSet(mDataSet);
    }

    private ArrayList<Coach> getShowList() {
        if (mShowList == null || mShowList.isEmpty())
            return mDataSet;
        else
            return mShowList;
    }

    private void addSearchListToMatrix(ArrayList<Coach> list, int textLength) {
        if (searchArrayMatrix == null)
            searchArrayMatrix = new ArrayList<>();
        while (searchArrayMatrix.size() > textLength - 1) {
            if (searchArrayMatrix.size() > 0)
                searchArrayMatrix.remove(searchArrayMatrix.size() - 1);
        }

        while (searchArrayMatrix.size() < textLength - 1) {
            searchArrayMatrix.add(new ArrayList<>());
        }

        if (searchArrayMatrix.size() != 0)
            searchArrayMatrix.add(list);
        else
            searchArrayMatrix.add(getShowList());
    }

    private void removeSearchListFromMatrix(ArrayList<Coach> list, int textLength) {
        if (searchArrayMatrix == null)
            searchArrayMatrix = new ArrayList<>();

        if (searchArrayMatrix == null)
            searchArrayMatrix = new ArrayList<>();
        while (searchArrayMatrix.size() - 1 > textLength) {
            if (searchArrayMatrix.size() > 0)
                searchArrayMatrix.remove(searchArrayMatrix.size() - 1);
        }

        while (searchArrayMatrix.size() - 1 < textLength) {
            searchArrayMatrix.add(new ArrayList<Coach>());
        }

        if (searchArrayMatrix.size() > 0)
            searchArrayMatrix.remove(searchArrayMatrix.size() - 1);
    }


    private void initialTrainingListImages(View view){
        mDataSet = new ArrayList<>();
        mDataSet.add(new Coach(0,"shon ohana",24,"blala","0541111111","1 year",null,null,null,null));
        mDataSet.add(new Coach(0,"eden barhum",24,"blala","0541111111","1 year",null,null,null,null));
        mDataSet.add(new Coach(0,"dolev ifergan",24,"blala","0541111111","1 year",null,null,null,null));


        searchRecycler = view.findViewById(R.id.rv_search);
        adapter = new SearchAdapter(getContext(),mDataSet);

        searchRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        searchRecycler.setAdapter(adapter);

    }

}