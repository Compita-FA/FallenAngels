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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fallenangels.R;


public class AdoptionForm6 extends Fragment
{
    //User input fields
    private EditText permanentHome;
    private EditText teachingWillingness;
    private EditText petResponsibility_whenOnHoliday;
    private EditText onCircumstanceChange;

    private CheckBox petSleepingSituation_Inside;
    private CheckBox petSleepingSituation_OnBed;
    private CheckBox petSleepingSituation_Outside;
    //User input fields

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm6() {
        // Required empty public constructor
    }


    public static AdoptionForm6 newInstance(String param1) {
        AdoptionForm6 fragment = new AdoptionForm6();
        Bundle args = new Bundle();
        args.putString("AdoptForm6", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form6, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext6);
        btnBack = getView().findViewById(R.id.btnBack5);

        //listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm7());
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
                ft.replace(R.id.frag_layout, new AdoptionForm5());
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