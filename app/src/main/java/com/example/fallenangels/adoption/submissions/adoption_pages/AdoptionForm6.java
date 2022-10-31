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
import android.widget.Toast;

import com.example.fallenangels.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        permanentHome = getView().findViewById(R.id.permanentHome);
        teachingWillingness = getView().findViewById(R.id.teachingWillingness);
        petResponsibility_whenOnHoliday = getView().findViewById(R.id.petResponsibility_whenOnHoliday);
        onCircumstanceChange = getView().findViewById(R.id.onCircumstanceChange);
        petSleepingSituation_Inside = getView().findViewById(R.id.petSleepingSituation_Inside);
        petSleepingSituation_OnBed = getView().findViewById(R.id.petSleepingSituation_OnBed);
        petSleepingSituation_Outside = getView().findViewById(R.id.petSleepingSituation_Outside);
        btnNext = getView().findViewById(R.id.btnNext6);
        btnBack = getView().findViewById(R.id.btnBack5);

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
                    ft.replace(R.id.frag_layout, new AdoptionForm7());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm5());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        if (permanentHome.getText().toString().trim().isEmpty() || teachingWillingness.getText().toString().trim().isEmpty() ||
                petResponsibility_whenOnHoliday.getText().toString().trim().isEmpty() || onCircumstanceChange.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Please fill in all fields!", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            if (petSleepingSituation_Inside.isChecked() == false && petSleepingSituation_OnBed.isChecked() == false &&
                    petSleepingSituation_Outside.isChecked() == false)
            {
                Toast.makeText(getActivity(), "Please tell us where they will sleep.", Toast.LENGTH_LONG).show();
                return false;
            }
            else
            {
                return true;
            }
        }
    }

    private void checkOtherUserInputs()
    {
        //Not needed here
    }

    private void saveUserInput()
    {
        AdoptionForm1.newForm.setPg6_permanentHome(permanentHome.getText().toString().trim());
        AdoptionForm1.newForm.setPg6_teachingWillingness(teachingWillingness.getText().toString().trim());
        AdoptionForm1.newForm.setPg6_petResponsibility_whenOnHoliday(petResponsibility_whenOnHoliday.getText().toString().trim());
        AdoptionForm1.newForm.setPg6_onCircumstanceChange(onCircumstanceChange.getText().toString().trim());
        AdoptionForm1.newForm.setPg6_petSleepingSituation(getSleepingLocation());
    }

    private String getSleepingLocation()
    {
        String reason;
        String r1 = "", r2 = "", r3 = "";

        if (petSleepingSituation_Inside.isChecked() == true)
        {
            r1 = petSleepingSituation_Inside.getText().toString().trim();
        }
        if (petSleepingSituation_OnBed.isChecked() == true)
        {
            r2 = petSleepingSituation_OnBed.getText().toString().trim();
        }
        if (petSleepingSituation_Outside.isChecked() == true)
        {
            r3 = petSleepingSituation_Outside.getText().toString().trim();
        }

        reason = r1 + " " + r2 + " " + r3 + " ";

        return reason;
    }
}