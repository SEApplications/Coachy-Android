package com.example.coachy.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.coachy.R;

import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {


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
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new SearchFragment()).commit();
                    return true;
                default:
                    return false;

            }

        });



    }


}