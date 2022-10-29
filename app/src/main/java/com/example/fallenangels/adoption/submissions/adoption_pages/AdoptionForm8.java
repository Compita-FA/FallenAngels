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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.fallenangels.R;


public class AdoptionForm8 extends Fragment
{
    //User input fields
    private RadioGroup yardSize;
    private EditText petExtraActivities;
    private RadioGroup deathsOnPremises;
    private EditText deathsOnPremisesPeriod;
    private EditText providePetCare;
    private RadioGroup typeOfHousing;
    //User input fields

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm8() {
        // Required empty public constructor
    }

    public static AdoptionForm8 newInstance(String param1) {
        AdoptionForm8 fragment = new AdoptionForm8();
        Bundle args = new Bundle();
        args.putString("AdoptForm8", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form8, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        yardSize = getView().findViewById(R.id.yardSize);
        petExtraActivities  = getView().findViewById(R.id.petExtraActivities);
        deathsOnPremises  = getView().findViewById(R.id.deathsOnPremises);
        deathsOnPremisesPeriod  = getView().findViewById(R.id.deathsOnPremisesPeriod);
        providePetCare  = getView().findViewById(R.id.providePetCare);
        typeOfHousing  = getView().findViewById(R.id.typeOfHousing);

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext8);
        btnBack = getView().findViewById(R.id.btnBack7);

        //listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm9());
                ft.commit();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkRequiredUserInput() == true)
                {
                    checkOtherUserInputs();
                    saveUserInput();


                }
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm7());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {

        return true;
    }

    private void checkOtherUserInputs()
    {

    }

    private void saveUserInput()
    {

    }
}