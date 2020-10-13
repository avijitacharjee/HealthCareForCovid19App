package com.avijit.healthcareforcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView signUpIntentButton;
    TextView goButton;
    EditText emailEditText, passwordEditText;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUpIntentButton = findViewById(R.id.signup_intent_button);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        goButton = findViewById(R.id.go_btn);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        goButton.setOnClickListener(v -> {
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            String url = "https://finalproject.xyz/covid_19/api.php";
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                progressDialog.dismiss();
                Log.d(TAG, "onCreate: " + response);
                if (response.contains("success")) {
                    getSharedPreferences("app", MODE_PRIVATE).edit().putString("user", response).apply();
                    startActivity(new Intent(getApplicationContext(), Index.class));
                } else {
                    Toast.makeText(this, "Incorrect email/password", Toast.LENGTH_SHORT).show();
                }
            },
                    error -> {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return super.getHeaders();
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("login", "1");
                    params.put("email", emailEditText.getText().toString());
                    params.put("password", passwordEditText.getText().toString());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        });
        signUpIntentButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SignUpUi.class)));
    }
}