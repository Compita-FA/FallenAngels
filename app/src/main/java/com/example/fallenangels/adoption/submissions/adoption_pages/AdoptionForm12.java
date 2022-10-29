package com.example.fallenangels.adoption.submissions.adoption_pages;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fallenangels.R;


public class AdoptionForm12 extends Fragment {

    public AdoptionForm12() {
        // Required empty public constructor
    }

    public static AdoptionForm12 newInstance(String param1) {
        AdoptionForm12 fragment = new AdoptionForm12();
        Bundle args = new Bundle();
        args.putString("AdoptForm12", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form12, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}