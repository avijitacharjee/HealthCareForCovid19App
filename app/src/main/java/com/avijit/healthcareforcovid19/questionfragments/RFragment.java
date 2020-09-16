package com.avijit.healthcareforcovid19.questionfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.healthcareforcovid19.R;

/**
 * Created by Avijit Acharjee on 9/17/2020 at 2:52 AM.
 * Email: avijitach@gmail.com.
 */
public class RFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_r,null,false);
    }
}
