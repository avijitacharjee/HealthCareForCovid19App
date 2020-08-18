package com.avijit.healthcareforcovid19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    TextView nameTextView,emailTextView,phoneTextView,updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameTextView = findViewById(R.id.name_text_view);
        phoneTextView = findViewById(R.id.phone_text_view);
        emailTextView = findViewById(R.id.email_text_view);
        updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener(v->{
            DialogFragment dialogFragment=  new UpdateDialogFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            dialogFragment.show(ft,"update");
        });
        try {
            JSONObject jsonObject = new JSONObject(getSharedPreferences("app",MODE_PRIVATE).getString("user",""));
            JSONObject data = jsonObject.getJSONObject("data");
            nameTextView.setText(data.getString("name"));
            emailTextView.setText(data.getString("email"));
            phoneTextView.setText(data.getString("phone"));
        }catch (Exception e){

        }
    }
    public static class UpdateDialogFragment extends DialogFragment{
        EditText nameEditText,emailEditText,phoneEditText,passwordEditText,confirmPasswordEditText;
        TextView goButton;
        String id;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_update_profile,container,true);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            nameEditText = view.findViewById(R.id.full_name_edit_text);
            emailEditText = view.findViewById(R.id.email_edit_text);
            phoneEditText = view.findViewById(R.id.phone_edit_text);
            passwordEditText = view.findViewById(R.id.password_edit_text);
            confirmPasswordEditText = view.findViewById(R.id.confirm_password_edit_text);
            goButton = view.findViewById(R.id.go);
            try {
                JSONObject jsonObject = new JSONObject(getContext().getSharedPreferences("app",MODE_PRIVATE).getString("user",""));
                JSONObject data = jsonObject.getJSONObject("data");
                nameEditText.setText(data.getString("name"));
                emailEditText.setText(data.getString("email"));
                phoneEditText.setText(data.getString("phone"));
                id = data.getString("id");
            }catch (Exception e){

            }
            goButton.setOnClickListener(v->{
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                String url = "https://finalproject.xyz/covid_19/api.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return super.getHeaders();
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("update_profile","1");
                        params.put("name",nameEditText.getText().toString());
                        params.put("email",emailEditText.getText().toString());
                        params.put("phone",phoneEditText.getText().toString());
                        params.put("password",passwordEditText.getText().toString());
                        params.put("id",id);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            });

        }
    }
}