package com.avijit.healthcareforcovid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avijit.healthcareforcovid19.adapter.FamilyMemberRecyclerViewAdapter;
import com.avijit.healthcareforcovid19.adapter.PlasmaDonorRecyclerViewAdapter;
import com.avijit.healthcareforcovid19.model.Donor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlasmaDonorsActivity extends AppCompatActivity {
    private static final String TAG = "PlasmaDonorsActivity";
    RecyclerView donorListRecyclerView;
    List<Donor> donorList;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plasma_donors);
        donorListRecyclerView = findViewById(R.id.donor_list_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        donorListRecyclerView.setLayoutManager(llm);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
        setDonorList();
    }
    private void setDonorList(){
        donorList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://finalproject.xyz/covid_19/api.php?plasma_donors=1";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                donorList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i=0;i<data.length();i++){
                        Donor donor = new Donor();
                        donor.setId(data.getJSONObject(i).getString("id"));
                        donor.setName(data.getJSONObject(i).getString("name"));
                        donor.setBloodGroup(data.getJSONObject(i).getString("blood_group"));
                        donor.setPhone(data.getJSONObject(i).getString("phone"));
                        donorList.add(donor);
                    }
                    PlasmaDonorRecyclerViewAdapter adapter = new PlasmaDonorRecyclerViewAdapter(donorList,getApplicationContext());
                    donorListRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Log.d(TAG, "onResponse: "+e);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("plasma_donors", "1");
               //params.put("member_of_user_id",id );

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}