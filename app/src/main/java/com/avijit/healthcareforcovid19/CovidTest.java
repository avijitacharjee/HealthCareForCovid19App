package com.avijit.healthcareforcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.avijit.healthcareforcovid19.questionfragments.Q1Fragment;
import com.avijit.healthcareforcovid19.questionfragments.Q2Fragment;
import com.avijit.healthcareforcovid19.questionfragments.Q3Fragment;
import com.avijit.healthcareforcovid19.questionfragments.Q4Fragment;

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
        previousButton.setVisibility(View.GONE);
        nextButton.setOnClickListener(v -> {
            switch (currentFragment){
                case 1: {
                        getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new Q2Fragment())
                            .commit();
                    currentFragment+=1;
                    previousButton.setVisibility(View.VISIBLE);
                    break;
                }
                case 2: {
                        getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new Q3Fragment())
                            .commit();
                    currentFragment+=1;
                    break;
                }
                case 3: {
                        getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new Q4Fragment())
                            .commit();
                    currentFragment+=1;
                    nextButton.setVisibility(View.GONE);
                    break;
                }
                case 4: {

                        break;
                }

            }
        });
        previousButton.setOnClickListener(v -> {
            switch (currentFragment){
                case 1 :
                {

                    break;
                }
                case 2 :
                {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new Q1Fragment())
                            .commit();
                    currentFragment-=1;
                    previousButton.setVisibility(View.GONE);
                    break;
                }
                case 3 :
                {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new Q2Fragment())
                            .commit();
                    currentFragment-=1;
                    break;
                }
                case 4 :
                {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new Q3Fragment())
                            .commit();
                    currentFragment-=1;
                    nextButton.setVisibility(View.VISIBLE);
                    break;
                }

            }
        });

    }
}