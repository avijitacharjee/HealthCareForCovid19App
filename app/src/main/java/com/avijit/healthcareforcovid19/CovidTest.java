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
            if(currentFragment<4){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,new Q2Fragment())
                        .commit();
                currentFragment+=1;
            }
        });
        previousButton.setOnClickListener(v -> {
            if(currentFragment>0){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,new Q1Fragment())
                        .commit();
                currentFragment-=1;
            }
        });

    }
}