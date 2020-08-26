package com.avijit.healthcareforcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Index extends AppCompatActivity {
    TextView covidTestIntent,profileIntent,familyIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        covidTestIntent = findViewById(R.id.covid_test_intent);
        profileIntent = findViewById(R.id.profile);
        familyIntent = findViewById(R.id.family_info);
        covidTestIntent.setOnClickListener(v->{
            startActivity(CovidTest.class);
        });
        profileIntent.setOnClickListener(v->{
            startActivity(Profile.class);
        });
        familyIntent.setOnClickListener(v->{
            startActivity(FamilyInfo.class);
        });
    }

    public void startActivity(Class cls) {
        Intent intent=new Intent(getApplicationContext(), cls);
        super.startActivity(intent);
    }
}