package com.avijit.healthcareforcovid19.questionfragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.healthcareforcovid19.R;

/**
 * Created by Avijit Acharjee on 8/6/2020 at 11:16 AM.
 * Email: avijitach@gmail.com.
 */
public class Q2Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q2,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ObjectAnimator.ofFloat(view,View.ALPHA,0f,1f).start();
    }
}
