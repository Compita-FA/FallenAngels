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


public class AdoptionForm4 extends Fragment
{
    //User input fields
    private EditText reasonForNonSterilisation;
    private EditText litterBeforeSterilisation;
    private RadioGroup fullyVaccinatedStatus;

    private EditText petsDiet;
    private EditText hoursAlone;
    //User input fields

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm4()
    {
        // Required empty public constructor
    }

    public static AdoptionForm4 newInstance(String param1)
    {
        AdoptionForm4 fragment = new AdoptionForm4();
        Bundle args = new Bundle();
        args.putString("AdoptForm4", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form4, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        reasonForNonSterilisation = getView().findViewById(R.id.reasonForNonSterilisation);
        litterBeforeSterilisation= getView().findViewById(R.id.litterBeforeSterilisation);
        fullyVaccinatedStatus= getView().findViewById(R.id.fullyVaccinatedStatus);

        petsDiet = getView().findViewById(R.id.petsDiet);
        hoursAlone = getView().findViewById(R.id.hoursAlone);

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext4);
        btnBack = getView().findViewById(R.id.btnBack3);

        checkInformation();

        //listeners
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (checkRequiredUserInput() == true)
                {
                    checkOtherUserInputs();
                    saveUserInput();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frag_layout, new AdoptionForm5());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm3());
                ft.commit();
            }
        });
    }

    private void checkInformation()
    {
        String sterilized = AdoptionForm1.newForm.getPg3_animalsSterilised();

        if(sterilized.equals("All of them"))
        {
            reasonForNonSterilisation.setText("N/A");
            reasonForNonSterilisation.setEnabled(false);
        }
    }

    private boolean checkRequiredUserInput()
    {
        if (reasonForNonSterilisation.getText().toString().trim().isEmpty() || litterBeforeSterilisation.getText().toString().trim().isEmpty() ||
                petsDiet.getText().toString().trim().isEmpty() || hoursAlone.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Please fill in all fields!", Toast.LENGTH_LONG).show();
            return false;
        }else
        {
            return true;
        }
    }

    private void checkOtherUserInputs()
    {
        // Not needed here
    }

    private void saveUserInput()
    {
        AdoptionForm1.newForm.setPg4_reasonForNonSterilisation(reasonForNonSterilisation.getText().toString());
        AdoptionForm1.newForm.setPg4_litterBeforeSterilisation(litterBeforeSterilisation.getText().toString());
        if (fullyVaccinatedStatus.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "Missing Checklist!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            // radio button is checked
            int selectedId = fullyVaccinatedStatus.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = (RadioButton) getView().findViewById(selectedId); // find the radiobutton by returned id
            AdoptionForm1.newForm.setPg1_animalSelection(selectedRadioButton.getText().toString());
        }
        AdoptionForm1.newForm.setPg4_petsDiet(petsDiet.getText().toString());
        AdoptionForm1.newForm.setPg4_hoursAlone(hoursAlone.getText().toString());
    }
}