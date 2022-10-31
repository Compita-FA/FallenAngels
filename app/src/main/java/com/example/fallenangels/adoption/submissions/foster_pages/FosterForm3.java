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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm1;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm4;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FosterForm3 extends Fragment {

    boolean ageDogs = true, ageCats = true, ageOther = true;

    //User input fields
    private EditText ageOfOtherAnimals_Dogs;
    private EditText ageOfOtherAnimals_Cats;
    private EditText ageOfOtherAnimals_Others;

    private EditText breedOfOtherAnimals_Dogs;
    private EditText breedOfOtherAnimals_Cats;
    private EditText breedOfOtherAnimals_Others;

    private EditText socializedOfOtherAnimals_Dogs;
    private EditText socializedOfOtherAnimals_Cats;
    private EditText socializedOfOtherAnimals_Others;

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
        ageOfOtherAnimals_Dogs = getView().findViewById(R.id.f_pageOfOtherAnimals_Dogs);
        ageOfOtherAnimals_Cats = getView().findViewById(R.id.f_pageOfOtherAnimals_Cats);
        ageOfOtherAnimals_Others = getView().findViewById(R.id.f_pagefOtherAnimals_Others);
        breedOfOtherAnimals_Dogs = getView().findViewById(R.id.breedOfOtherAnimals_Dogs);
        breedOfOtherAnimals_Cats = getView().findViewById(R.id.breedOfOtherAnimals_Cats);
        breedOfOtherAnimals_Others = getView().findViewById(R.id.breedOfOtherAnimals_Others);
        socializedOfOtherAnimals_Dogs = getView().findViewById(R.id.f_socialised_Dogs3);
        socializedOfOtherAnimals_Cats = getView().findViewById(R.id.f_socialised_Cats3);
        socializedOfOtherAnimals_Others = getView().findViewById(R.id.f_socialised_Others3);
        btnNext = getView().findViewById(R.id.f_btnNext4);
        btnBack = getView().findViewById(R.id.f_btnBack2);

        //Hiding the nav view
        BottomNavigationView bottomNav;
        bottomNav = getActivity().findViewById(R.id.bottomNavView);
        bottomNav.setVisibility(View.INVISIBLE);

        checkInformation();

        //Listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUserInputs();
                saveUserInput();

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

    private void checkInformation()
    {
        int dogs = Integer.parseInt(FosterForm1.newForm.getPg2_otherAnimal_Dogs());
        int cats = Integer.parseInt(FosterForm1.newForm.getPg2_otherAnimal_Cats());
        int other = Integer.parseInt(FosterForm1.newForm.getPg2_otherAnimal_Other());

        if( dogs == 0)
        {
            ageOfOtherAnimals_Dogs.setEnabled(false);
            breedOfOtherAnimals_Dogs.setEnabled(false);
            socializedOfOtherAnimals_Dogs.setEnabled(false);

            ageOfOtherAnimals_Dogs.setHint("N/A");
            breedOfOtherAnimals_Dogs.setHint("N/A");
            socializedOfOtherAnimals_Dogs.setHint("N/A");

            ageDogs = false;
        }

        if( cats == 0)
        {
            ageOfOtherAnimals_Cats.setEnabled(false);
            breedOfOtherAnimals_Cats.setEnabled(false);
            socializedOfOtherAnimals_Cats.setEnabled(false);

            ageOfOtherAnimals_Cats.setHint("N/A");
            breedOfOtherAnimals_Cats.setHint("N/A");
            socializedOfOtherAnimals_Cats.setHint("N/A");

            ageCats = false;
        }

        if( other == 0)
        {
            ageOfOtherAnimals_Others.setEnabled(false);
            breedOfOtherAnimals_Others.setEnabled(false);
            socializedOfOtherAnimals_Others.setEnabled(false);

            ageOfOtherAnimals_Others.setHint("N/A");
            breedOfOtherAnimals_Others.setHint("N/A");
            socializedOfOtherAnimals_Others.setHint("N/A");

            ageOther = false;
        }
    }

    private void setUserInputs()
    {
        if (ageDogs == false)
        {
            ageOfOtherAnimals_Dogs.setText("N/A");
            breedOfOtherAnimals_Dogs.setText("N/A");
            socializedOfOtherAnimals_Dogs.setText("N/A");
        }

        if (ageCats == false)
        {
            ageOfOtherAnimals_Cats.setText("N/A");
            breedOfOtherAnimals_Cats.setText("N/A");
            socializedOfOtherAnimals_Cats.setText("N/A");
        }

        if (ageOther == false)
        {
            ageOfOtherAnimals_Others.setText("N/A");
            breedOfOtherAnimals_Others.setText("N/A");
            socializedOfOtherAnimals_Others.setText("N/A");
        }
    }

    private void saveUserInput()
    {
        FosterForm1.newForm.setPg3_ageOfOtherAnimals_Dogs(ageOfOtherAnimals_Dogs.getText().toString());
        FosterForm1.newForm.setPg3_ageOfOtherAnimals_Cats(ageOfOtherAnimals_Cats.getText().toString());
        FosterForm1.newForm.setPg3_ageOfOtherAnimals_Others(ageOfOtherAnimals_Others.getText().toString());

        FosterForm1.newForm.setPg3_breedOfOtherAnimals_Dogs(breedOfOtherAnimals_Dogs.getText().toString());
        FosterForm1.newForm.setPg3_breedOfOtherAnimals_Cats(breedOfOtherAnimals_Cats.getText().toString());
        FosterForm1.newForm.setPg3_breedOfOtherAnimals_Others(breedOfOtherAnimals_Others.getText().toString());

        FosterForm1.newForm.setPg3_socializedOtherAnimals_Dogs(breedOfOtherAnimals_Dogs.getText().toString());
        FosterForm1.newForm.setPg3_socializedOfOtherAnimals_Cats(breedOfOtherAnimals_Cats.getText().toString());
        FosterForm1.newForm.setPg3_socializedOfOtherAnimals_Others(breedOfOtherAnimals_Others.getText().toString());
    }
}