package com.avijit.healthcareforcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.avijit.healthcareforcovid19.questionfragments.Q1Fragment;

public class CovidTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_test);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new Q1Fragment())
                .commit();
    }
}