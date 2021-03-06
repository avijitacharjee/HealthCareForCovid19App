package com.avijit.healthcareforcovid19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avijit.healthcareforcovid19.adapter.FamilyMemberRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyInfo extends AppCompatActivity {
    private static final String TAG = "FamilyInfo";
    FloatingActionButton fab;
    private ProgressDialog progressDialog;
    String id;
    List<String> names = new ArrayList<>();
    List<String> ages = new ArrayList<>();
    List<String> relations = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info);
        fab = findViewById(R.id.add_member_button);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        FamilyMemberRecyclerViewAdapter adapter = new FamilyMemberRecyclerViewAdapter(names,ages,relations);
        recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
        try {
            JSONObject jsonObject = new JSONObject(getApplication().getSharedPreferences("app",MODE_PRIVATE).getString("user",""));
            JSONObject data = jsonObject.getJSONObject("data");
            id = data.getString("id");
        }catch (Exception e){

        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://finalproject.xyz/covid_19/api.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                names.clear();
                ages.clear();
                relations.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i=0;i<data.length();i++){
                        names.add(data.getJSONObject(i).getString("name"));
                        ages.add(data.getJSONObject(i).getString("age"));
                        relations.add(data.getJSONObject(i).getString("relation"));
                    }
                    Log.d(TAG, "onResponse: "+names.toString());
                    FamilyMemberRecyclerViewAdapter adapter = new FamilyMemberRecyclerViewAdapter(names,ages,relations);
                    recyclerView.setAdapter(adapter);
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
                params.put("get_family_members", "1");
                params.put("member_of_user_id",id );

                return params;
            }
        };
        requestQueue.add(stringRequest);
        fab.setOnClickListener(v->{
            DialogFragment dialogFragment=  new AddFamilyMemberDialogFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            dialogFragment.show(ft,"update");
        });

    }

    public static class AddFamilyMemberDialogFragment extends DialogFragment {
        EditText nameEditText, ageEditText, relationEditText;
        TextView goButton;
        String id="";
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_add_family_member, container, true);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            nameEditText = findViewById(view, R.id.full_name_edit_text);
            ageEditText = findViewById(view, R.id.age_edit_text);
            relationEditText = findViewById(view, R.id.relation_edit_text);
            goButton = findViewById(view, R.id.go);
            try {
                JSONObject jsonObject = new JSONObject(getContext().getSharedPreferences("app",MODE_PRIVATE).getString("user",""));
                JSONObject data = jsonObject.getJSONObject("data");
                id = data.getString("id");
            }catch (Exception e){

            }
            goButton.setOnClickListener(v -> {
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                String url = "https://finalproject.xyz/covid_19/api.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        startActivity( new Intent(getContext(),FamilyInfo.class));
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return super.getHeaders();
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("add_family_member", "1");
                        params.put("member_of_user_id", id);
                        params.put("name", nameEditText.getText().toString());
                        params.put("age", ageEditText.getText().toString());
                        params.put("relation", relationEditText.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            });
        }

        public static <T extends View> T findViewById(View view, int id) {
            return view.findViewById(id);
        }
    }
}