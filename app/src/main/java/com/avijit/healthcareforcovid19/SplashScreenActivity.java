package com.avijit.healthcareforcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.avijit.healthcareforcovid19.R;
import com.bumptech.glide.Glide;

public class SplashScreenActivity extends AppCompatActivity {
    static int SPLASH_TIME = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView imageView = (ImageView) findViewById(R.id.gif_image);
        Glide.with(this).asGif().load(R.drawable.stay_home).into(imageView);
        splash();
    }
    public void splash()
    {
        new Handler().postDelayed(() -> {
            Intent splashIntent= new Intent(SplashScreenActivity.this, CovidInfoActivity.class );
            startActivity(splashIntent);
            finish();
        },SPLASH_TIME);
    }
}