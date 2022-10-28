package com.example.fallenangels.adoption.submissions.adoption_pages;

import android.content.Context;
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
import com.example.fallenangels.adoption.submissions.foster_pages.FosterForm1;


public class AdoptionForm1 extends Fragment {

    private AppCompatButton btnNext;

    public AdoptionForm1() {
        // Required empty public constructor
    }

    public static AdoptionForm1 newInstance(String param1) {
        AdoptionForm1 fragment = new AdoptionForm1();
        Bundle args = new Bundle();
        args.putString("AdoptForm1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext1);

        //listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm2());
                ft.commit();
            }
        });
    }
}