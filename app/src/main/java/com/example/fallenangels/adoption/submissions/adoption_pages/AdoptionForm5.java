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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        hoursTogether = getView().findViewById(R.id.hoursTogether);
        reasonForAdoption_Gift = getView().findViewById(R.id.reasonForAdoption_Gift);
        reasonForAdoption_Breeding = getView().findViewById(R.id.reasonForAdoption_Breeding);
        reasonForAdoption_Watchdog = getView().findViewById(R.id.reasonForAdoption_Watchdog);
        reasonForAdoption_Companion = getView().findViewById(R.id.reasonForAdoption_Companion);
        animalSurrender_Reason = getView().findViewById(R.id.animalSurrender_Reason);
        animalSurrender_Status = getView().findViewById(R.id.animalSurrender_Status);
        havePetsGoneMissing = getView().findViewById(R.id.havePetsGoneMissing);
        btnNext = getView().findViewById(R.id.btnNext5);
        btnBack = getView().findViewById(R.id.btnBack4);

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

                    Toast.makeText(getActivity(), "Reason: " + getReason(), Toast.LENGTH_LONG).show();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frag_layout, new AdoptionForm6());
                    ft.commit();
                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm4());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        if (hoursTogether.getText().toString().trim().isEmpty() || animalSurrender_Reason.getText().toString().trim().isEmpty() ||
                animalSurrender_Status.getText().toString().trim().isEmpty() || havePetsGoneMissing.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Please fill in all fields!", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            if (reasonForAdoption_Gift.isChecked() == false && reasonForAdoption_Breeding.isChecked() == false &&
                    reasonForAdoption_Watchdog.isChecked() == false && reasonForAdoption_Companion.isChecked() == false)
            {
                Toast.makeText(getActivity(), "Please give a reason for adoption!", Toast.LENGTH_LONG).show();
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
        AdoptionForm1.newForm.setPg5_hoursTogether(hoursTogether.getText().toString().trim());
        AdoptionForm1.newForm.setPg5_animalSurrender_Reason(animalSurrender_Reason.getText().toString().trim());
        AdoptionForm1.newForm.setPg5_reasonForAdoption(getReason());
        AdoptionForm1.newForm.setPg5_animalSurrender_Status(animalSurrender_Status.getText().toString().trim());
        AdoptionForm1.newForm.setPg5_havePetsGoneMissing(havePetsGoneMissing.getText().toString().trim());
    }

    private String getReason()
    {
        String reason;
        String r1 = "", r2 = "", r3 = "", r4 = "";

        if (reasonForAdoption_Gift.isChecked() == true)
        {
            r1 = reasonForAdoption_Gift.getText().toString().trim();
        }
        if (reasonForAdoption_Breeding.isChecked() == true)
        {
            r2 = reasonForAdoption_Breeding.getText().toString().trim();
        }
        if (reasonForAdoption_Watchdog.isChecked() == true)
        {
            r3 = reasonForAdoption_Watchdog.getText().toString().trim();
        }
        if (reasonForAdoption_Companion.isChecked() == true)
        {
            r4 = reasonForAdoption_Companion.getText().toString().trim();
        }

        reason = r1 + " " + r2 + " " + r3 + " " + r4;

        return reason;
    }
}