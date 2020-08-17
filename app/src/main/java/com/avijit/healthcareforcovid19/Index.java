package com.avijit.healthcareforcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Index extends AppCompatActivity {
    TextView covidTestIntent,profileIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        covidTestIntent = findViewById(R.id.covid_test_intent);
        profileIntent = findViewById(R.id.profile);
        covidTestIntent.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),CovidTest.class));
        });
        profileIntent.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),Profile.class));
        });

    }
}