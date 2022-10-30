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


public class FosterForm6 extends Fragment {

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public FosterForm6() {
        // Required empty public constructor
    }

    public static FosterForm6 newInstance(String param1, String param2) {
        FosterForm6 fragment = new FosterForm6();
        Bundle args = new Bundle();
        args.putString("FosterForm6", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foster_form6, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        btnNext = getView().findViewById(R.id.f_btnNext7);
        btnBack = getView().findViewById(R.id.f_btnBack5);

        //Listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm7());
                ft.commit();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm5());
                ft.commit();
            }
        });
    }
}