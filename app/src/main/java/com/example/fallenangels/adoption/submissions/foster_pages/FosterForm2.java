package com.example.fallenangels.adoption.submissions.foster_pages;

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


public class FosterForm2 extends Fragment {

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public FosterForm2() {
        // Required empty public constructor
    }

    public static FosterForm2 newInstance(String param1, String param2) {
        FosterForm2 fragment = new FosterForm2();
        Bundle args = new Bundle();
        args.putString("FosterForm2", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foster_form2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        //Finding ID's
        btnNext = getView().findViewById(R.id.f_btnNext3);
        btnBack = getView().findViewById(R.id.f_btnBack1);

        //Listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm3());
                ft.commit();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm1());
                ft.commit();
            }
        });
    }
}