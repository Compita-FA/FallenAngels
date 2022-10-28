package com.example.fallenangels.adoption.submissions.adoption_pages;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fallenangels.R;


public class AdoptionForm7 extends Fragment {

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm7() {
        // Required empty public constructor
    }

    public static AdoptionForm7 newInstance(String param1) {
        AdoptionForm7 fragment = new AdoptionForm7();
        Bundle args = new Bundle();
        args.putString("AdoptForm7", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form7, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext7);
        btnBack = getView().findViewById(R.id.btnBack6);

        //listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm8());
                ft.commit();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm6());
                ft.commit();
            }
        });
    }
}