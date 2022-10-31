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
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.MainAdoptFragment;
import com.example.fallenangels.adoption.submissions.adoption_pages.adoptionFormObject.AdoptionFormModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AdoptionForm1 extends Fragment
{
    static AdoptionFormModel newForm = new AdoptionFormModel();

    //User input fields
    private RadioGroup animalChoice;
    public static EditText edt_dogNameOne;
    public static EditText edt_dogNameTwo;
    private EditText edt_NameSurname;
    private EditText edt_address;
    private AppCompatButton btnBack;

    public static String dogName1;
    public static String dogName2;


    //Button to go to next page
    private AppCompatButton btnNext;

    public AdoptionForm1()
    {
        // Required empty public constructor
    }

    public static AdoptionForm1 newInstance(String param1)
    {
        AdoptionForm1 fragment = new AdoptionForm1();
        Bundle args = new Bundle();
        args.putString("AdoptForm1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form1, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        try
        {
            loadPreviousData();
        }
        catch (Exception e)
        {
            //Toast.makeText(getActivity(), "Exception: " + e, Toast.LENGTH_LONG).show();
        }


        //Finding ID's
        animalChoice = getView().findViewById(R.id.rg_animalChoice);
        edt_dogNameOne = getView().findViewById(R.id.edt_dogNameOne);
        edt_dogNameTwo = getView().findViewById(R.id.edt_dogNameTwo);
        edt_NameSurname = getView().findViewById(R.id.edt_NameSurname);
        edt_address = getView().findViewById(R.id.edt_address);
        btnNext = getView().findViewById(R.id.btnNext1);
        btnBack = getView().findViewById(R.id.btnBackViewDog1);

        EnterDog(dogName1);

        //Hiding the nav view
        BottomNavigationView bottomNav;
        bottomNav = getActivity().findViewById(R.id.bottomNavView);
        bottomNav.setVisibility(View.INVISIBLE);

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
                    ft.replace(R.id.frag_layout, new AdoptionForm2());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new MainAdoptFragment());
                ft.commit();
            }
        });
    }

    //-------------------------------- Enters dog name into edit texts -----------------------------
    public void EnterDog(String dog) {
        edt_dogNameOne.setText(dog);
    }
    //----------------------------------------------------------------------------------------------

    private boolean checkRequiredUserInput()
    {
        if (edt_address.getText().toString().isEmpty() || edt_NameSurname.getText().toString().isEmpty())
        {
            Toast.makeText(getActivity(), "Address or Name cannot be empty!", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private void checkOtherUserInputs()
    {
        //Not needed here
    }

    private void saveUserInput()
    {
        if (animalChoice.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "You need to select Canine or Feline!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            // radio button is checked
            int selectedId = animalChoice.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = (RadioButton) getView().findViewById(selectedId); // find the radiobutton by returned id

            newForm.setPg1_animalSelection(selectedRadioButton.getText().toString());
        }

        newForm.setPg1_animalName(edt_dogNameOne.getText().toString());
        newForm.setPg1_ownerNameAndSurname(edt_NameSurname.getText().toString());
        newForm.setPg1_ownerAddress(edt_address.getText().toString());
    }

    public void loadPreviousData()
    {

    }
}