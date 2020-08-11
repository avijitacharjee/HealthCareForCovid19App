package com.avijit.healthcareforcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.avijit.healthcareforcovid19.questionfragments.Q1Fragment;
import com.avijit.healthcareforcovid19.questionfragments.Q2Fragment;

public class CovidTest extends AppCompatActivity {
    TextView previousButton,nextButton;
    int currentFragment=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_test);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new Q1Fragment())
                .commit();
        previousButton = findViewById(R.id.prev_button);
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,new Q2Fragment())
                    .commit();
        });
        previousButton.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,new Q1Fragment())
                    .commit();
        });

    }
}