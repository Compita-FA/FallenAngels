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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.fallenangels.R;


public class AdoptionForm5 extends Fragment
{
    //User input fields
    private EditText hoursTogether;

    private CheckBox reasonForAdoption_Gift;
    private CheckBox reasonForAdoption_Breeding;
    private CheckBox reasonForAdoption_Watchdog;
    private CheckBox reasonForAdoption_Companion;

    private EditText animalSurrender_Reason;
    private EditText animalSurrender_Status;
    private EditText havePetsGoneMissing;
    //User input fields

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm5() {
        // Required empty public constructor
    }


    public static AdoptionForm5 newInstance(String param1) {
        AdoptionForm5 fragment = new AdoptionForm5();
        Bundle args = new Bundle();
        args.putString("AdoptForm5", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form5, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext5);
        btnBack = getView().findViewById(R.id.btnBack4);

        //listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm6());
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
                ft.replace(R.id.frag_layout, new AdoptionForm4());
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