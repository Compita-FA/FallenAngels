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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AdoptionForm8 extends Fragment
{
    Boolean bYard = false, bYesNo = false, bHouse = false, activities = false, extraCare = false;

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
        //Finding ID's
        yardSize = getView().findViewById(R.id.yardSize);
        petExtraActivities  = getView().findViewById(R.id.petExtraActivities);
        deathsOnPremises  = getView().findViewById(R.id.deathsOnPremises);
        deathsOnPremisesPeriod  = getView().findViewById(R.id.deathsOnPremisesPeriod);
        providePetCare  = getView().findViewById(R.id.providePetCare);
        typeOfHousing  = getView().findViewById(R.id.typeOfHousing);
        btnNext = getView().findViewById(R.id.btnNext8);
        btnBack = getView().findViewById(R.id.btnBack7);

        //Hiding the nav view
        BottomNavigationView bottomNav;
        bottomNav = getActivity().findViewById(R.id.bottomNavView);
        bottomNav.setVisibility(View.INVISIBLE);

        //listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkRequiredUserInput() == true)
                {
                    checkOtherUserInputs();
                    saveUserInput();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frag_layout, new AdoptionForm9());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm7());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        if (yardSize.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "Please enter the size of your yard.", Toast.LENGTH_LONG).show();
        }
        else
        {
            // radio button is checked
            bYard = true;
        }

        if (petExtraActivities.getText().toString().trim().isEmpty() || providePetCare.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Please check your inputs for Grooming and Extra care.", Toast.LENGTH_LONG).show();
        }else
        {
            activities = true;
            extraCare = true;
        }

        if (deathsOnPremises.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "Please enter if there has been a death.", Toast.LENGTH_LONG).show();
        }
        else
        {
            // radio button is checked
            bYesNo = true;
        }

        if (typeOfHousing.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "Please enter your type of housing.", Toast.LENGTH_LONG).show();
        }
        else
        {
            // radio button is checked
            bHouse = true;
        }

        if (bYard == true && bYesNo == true && bHouse == true && activities == true && extraCare == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void checkOtherUserInputs()
    {
        //Not needed here
    }

    private void saveUserInput()
    {
        int yard = yardSize.getCheckedRadioButtonId();
        RadioButton yardRadioButton = (RadioButton) getView().findViewById(yard); // find the radiobutton by returned id
        AdoptionForm1.newForm.setPg8_yardSize(yardRadioButton.getText().toString().trim());

        AdoptionForm1.newForm.setPg8_petExtraActivities(petExtraActivities.getText().toString().trim());

        int yesNo = deathsOnPremises.getCheckedRadioButtonId();
        RadioButton yesNoRadioButton = (RadioButton) getView().findViewById(yesNo); // find the radiobutton by returned id
        if (yesNoRadioButton.getText().toString().trim().equals("Yes"))
        {
            AdoptionForm1.newForm.setPg8_deathsOnPremises(yesNoRadioButton.getText().toString().trim() + ", " + deathsOnPremisesPeriod.getText().toString().trim());
        }
        else
        {
            AdoptionForm1.newForm.setPg8_deathsOnPremises(yesNoRadioButton.getText().toString().trim());
        }

        AdoptionForm1.newForm.setPg8_providePetCare(providePetCare.getText().toString().trim());

        int housingType = typeOfHousing.getCheckedRadioButtonId();
        RadioButton housingTypeRadioButton = (RadioButton) getView().findViewById(housingType); // find the radiobutton by returned id
        AdoptionForm1.newForm.setPg8_typeOfHousing(housingTypeRadioButton.getText().toString().trim());
    }
}