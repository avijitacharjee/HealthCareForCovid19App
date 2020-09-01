package com.avijit.healthcareforcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CovidInfoActivity extends AppCompatActivity {
    private static final String TAG = "CovidInfoActivity";
    LinearLayout nextButton;
    Spinner countrySpinner;
    List<String> countryList;
    TextView totalCasesTextView,newConfirmedTextView,newDeathTextView,newRecoveredTextView,totalDeathTextView,totalRecoveredTextView,totalCountriesTextView;
    TextView countryNameTextView;
    CovidSummery covidSummery;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_info);
        countrySpinner = findViewById(R.id.country_spinner);
        nextButton = findViewById(R.id.next_btn);
        totalCasesTextView = findViewById(R.id.total_text_view);
        newConfirmedTextView = findViewById(R.id.new_text_view);
        newDeathTextView = findViewById(R.id.new_death_text_view);
        newRecoveredTextView = findViewById(R.id.new_recovered_text_view);
        totalDeathTextView = findViewById(R.id.total_death_text_view);
        totalRecoveredTextView = findViewById(R.id.total_recovered_text_view);
        countryNameTextView = findViewById(R.id.country_name_text_view);
        totalCountriesTextView = findViewById(R.id.total_countries_text_view);
        nextButton.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });
        countryList = new ArrayList<>();
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getChildAt(0)==null){
                    return;
                }
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_1));
                ((TextView) parent.getChildAt(0)).setBackgroundColor(getResources().getColor(R.color.white_1));
                ((TextView) parent.getChildAt(0)).setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                if(position>0){
                    if(covidSummery!=null){
                        countryNameTextView.setText(covidSummery.getCountries().get(position-1).getCountry());
                        totalCasesTextView.setText(covidSummery.getCountries().get(position-1).getTotalConfirmed()+"");
                        newConfirmedTextView.setText(covidSummery.getCountries().get(position-1).getNewConfirmed()+"");
                        newDeathTextView.setText(covidSummery.getCountries().get(position-1).getNewDeaths()+"");
                        newRecoveredTextView.setText(covidSummery.getCountries().get(position-1).getNewRecovered()+"");
                        totalRecoveredTextView.setText(covidSummery.getCountries().get(position-1).getTotalRecovered()+"");
                        totalDeathTextView.setText(covidSummery.getCountries().get(position-1).getTotalDeaths()+"");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://api.covid19api.com/summary";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response ->{
            progressDialog.dismiss();
            Log.d(TAG, "onCreate: "+response);
            try {
                CovidSummery data = new Gson().fromJson(response,CovidSummery.class);
                covidSummery = data;
                totalCasesTextView.setText(data.getGlobal().getTotalConfirmed()+"");
                newConfirmedTextView.setText(data.getGlobal().getNewConfirmed()+"");
                newDeathTextView.setText(data.getGlobal().getNewDeaths()+"");
                newRecoveredTextView.setText(data.getGlobal().getNewRecovered()+"");
                totalRecoveredTextView.setText(data.getGlobal().getTotalRecovered()+"");
                totalDeathTextView.setText(data.getGlobal().getTotalDeaths()+"");
                totalCountriesTextView.setText(data.getCountries().size()+"");

                countryList.clear();
                countryList.add("--Select Country--");
                for(int i=0;i<data.getCountries().size();i++){
                    countryList.add(data.getCountries().get(i).getCountry());
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_spinner_dropdown_item, countryList);
                    countrySpinner.setAdapter(adapter);
                }
            }catch (Exception e){
                Log.d(TAG, "onCreate: "+e);
            }
        },
                error -> Log.d(TAG, "onCreate: "+error.toString())){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}