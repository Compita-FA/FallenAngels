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


public class AdoptionForm3 extends Fragment
{
    boolean ageDogs = true, ageCats = true, ageOther = true;

    //User input fields
    private EditText ageOfOtherAnimals_Dogs;
    private EditText ageOfOtherAnimals_Cats;
    private EditText ageOfOtherAnimals_Others;

    private EditText genderOfOtherAnimals_Dogs;
    private EditText genderOfOtherAnimals_Cats;
    private EditText genderOfOtherAnimals_Others;

    private RadioGroup animalsSterilised;
    private EditText animals_Not_Sterilised;
    //User input fields

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm3()
    {
        // Required empty public constructor
    }

    public static AdoptionForm3 newInstance(String param1)
    {
        AdoptionForm3 fragment = new AdoptionForm3();
        Bundle args = new Bundle();
        args.putString("AdoptForm3", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        ageOfOtherAnimals_Dogs = getView().findViewById(R.id.pageOfOtherAnimals_Dogs);
        ageOfOtherAnimals_Cats = getView().findViewById(R.id.pageOfOtherAnimals_Cats);
        ageOfOtherAnimals_Others = getView().findViewById(R.id.ageOfOtherAnimals_Others);
        genderOfOtherAnimals_Dogs = getView().findViewById(R.id.genderOfOtherAnimals_Dogs);
        genderOfOtherAnimals_Cats = getView().findViewById(R.id.genderOfOtherAnimals_Cats);
        genderOfOtherAnimals_Others = getView().findViewById(R.id.genderOfOtherAnimals_Others);
        animalsSterilised = getView().findViewById(R.id.animalsSterilised);
        animals_Not_Sterilised = getView().findViewById(R.id.animals_Not_Sterilised);
        btnNext = getView().findViewById(R.id.btnNext3);
        btnBack = getView().findViewById(R.id.btnBack2);

        //Hiding the nav view
        BottomNavigationView bottomNav;
        bottomNav = getActivity().findViewById(R.id.bottomNavView);
        bottomNav.setVisibility(View.INVISIBLE);

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
                    ft.replace(R.id.frag_layout, new AdoptionForm4());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm2());
                ft.commit();
            }
        });
    }

    private void checkInformation()
    {
        int dogs = Integer.parseInt(AdoptionForm1.newForm.getPg2_otherAnimal_Dogs());
        int cats = Integer.parseInt(AdoptionForm1.newForm.getPg2_otherAnimal_Cats());
        int other = Integer.parseInt(AdoptionForm1.newForm.getPg2_otherAnimal_Other());

        if( dogs == 0)
        {
            ageOfOtherAnimals_Dogs.setEnabled(false);
            genderOfOtherAnimals_Dogs.setEnabled(false);
            ageOfOtherAnimals_Dogs.setHint("N/A");
            genderOfOtherAnimals_Dogs.setHint("N/A");

            ageDogs = false;
        }

        if( cats == 0)
        {
            ageOfOtherAnimals_Cats.setEnabled(false);
            genderOfOtherAnimals_Cats.setEnabled(false);
            ageOfOtherAnimals_Cats.setHint("N/A");
            genderOfOtherAnimals_Cats.setHint("N/A");

            ageCats = false;
        }

        if( other == 0)
        {
            ageOfOtherAnimals_Others.setEnabled(false);
            genderOfOtherAnimals_Others.setEnabled(false);
            ageOfOtherAnimals_Others.setHint("N/A");
            genderOfOtherAnimals_Others.setHint("N/A");
            ageOther = false;
        }
    }

    private boolean checkRequiredUserInput()
    {
        if (animalsSterilised.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "Missing Checklist!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            // radio button is checked
            int selectedId = animalsSterilised.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = (RadioButton) getView().findViewById(selectedId); // find the radiobutton by returned id

            if (selectedRadioButton.getText().toString().equals("All of them"))
            {
                animals_Not_Sterilised.setText("N/A");
            }

            return true;
        }
    }

    private void checkOtherUserInputs()
    {
        if (ageDogs == false)
        {
            ageOfOtherAnimals_Dogs.setText("N/A");
            genderOfOtherAnimals_Dogs.setText("N/A");
        }

        if (ageCats == false)
        {
            ageOfOtherAnimals_Cats.setText("N/A");
            genderOfOtherAnimals_Cats.setText("N/A");
        }

        if (ageOther == false)
        {
            ageOfOtherAnimals_Others.setText("N/A");
            genderOfOtherAnimals_Others.setText("N/A");
        }
    }

    private void saveUserInput()
    {
        AdoptionForm1.newForm.setPg3_ageOfOtherAnimals_Dogs(ageOfOtherAnimals_Dogs.getText().toString());
        AdoptionForm1.newForm.setPg3_ageOfOtherAnimals_Cats(ageOfOtherAnimals_Cats.getText().toString());
        AdoptionForm1.newForm.setPg3_ageOfOtherAnimals_Others(ageOfOtherAnimals_Others.getText().toString());

        AdoptionForm1.newForm.setPg3_genderOfOtherAnimals_Dogs(genderOfOtherAnimals_Dogs.getText().toString());
        AdoptionForm1.newForm.setPg3_genderOfOtherAnimals_Cats(genderOfOtherAnimals_Cats.getText().toString());
        AdoptionForm1.newForm.setPg3_genderOfOtherAnimals_Others(genderOfOtherAnimals_Others.getText().toString());

        int selectedId = animalsSterilised.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton) getView().findViewById(selectedId); // find the radiobutton by returned id
        AdoptionForm1.newForm.setPg3_animalsSterilised(selectedRadioButton.getText().toString());

        AdoptionForm1.newForm.setPg3_animals_Not_Sterilised(animals_Not_Sterilised.getText().toString());
    }
}