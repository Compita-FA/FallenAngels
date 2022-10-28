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


public class AdoptionForm9 extends Fragment {

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm9() {
        // Required empty public constructor
    }

    public static AdoptionForm9 newInstance(String param1) {
        AdoptionForm9 fragment = new AdoptionForm9();
        Bundle args = new Bundle();
        args.putString("AdoptForm9", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form9, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext9);
        btnBack = getView().findViewById(R.id.btnBack8);

        //listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm10());
                ft.commit();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm8());
                ft.commit();
            }
        });
    }
}