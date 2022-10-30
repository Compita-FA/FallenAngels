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


public class FosterForm3 extends Fragment {

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public FosterForm3() {
        // Required empty public constructor
    }

    public static FosterForm3 newInstance(String param1, String param2) {
        FosterForm3 fragment = new FosterForm3();
        Bundle args = new Bundle();
        args.putString("FosterForm3", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foster_form3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        btnNext = getView().findViewById(R.id.f_btnNext4);
        btnBack = getView().findViewById(R.id.f_btnBack2);

        //Listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm4());
                ft.commit();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm2());
                ft.commit();
            }
        });
    }
}