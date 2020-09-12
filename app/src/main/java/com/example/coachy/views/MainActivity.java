package com.example.coachy.views;

import android.os.Bundle;

import com.example.coachy.R;
import com.example.coachy.adapters.TrainingTypeAdapter;
import com.example.coachy.models.TrainingType;

import androidx.appcompat.app.AppCompatActivity;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity implements TrainingTypeAdapter.OnClickSelected{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new TrainingTypeFragment()).commit();

    }

    private void initView() {
        SmoothBottomBar bottomNavigationBar = (SmoothBottomBar) findViewById(R.id.bottomBarMain);




        bottomNavigationBar.setOnItemSelectedListener(i -> {

            switch (i){
                case 0:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new TrainingTypeFragment()).commit();
                    return true;
                case 1:
 //                   getSupportFragmentManager().beginTransaction().replace(R.id.frame, new DetailsTrainingFragment()).commit();
                    return true;
                default:
                    return false;

            }

        });



    }

    public void replaceFragment(TrainingType trainingType){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new DetailsTrainingFragment(trainingType)).addToBackStack("back").commit();

    }


    @Override
    public void onTrainingTypeSelected(TrainingType trainingType) {
        replaceFragment(trainingType);
    }





}