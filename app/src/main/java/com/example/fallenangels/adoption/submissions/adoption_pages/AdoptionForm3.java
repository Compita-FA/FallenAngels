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
import android.widget.Toast;

import com.example.fallenangels.R;


public class AdoptionForm3 extends Fragment
{
    //User input fields
    private EditText pageOfOtherAnimals_Dogs;
    private EditText pageOfOtherAnimals_Cats;
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
        pageOfOtherAnimals_Dogs = getView().findViewById(R.id.pageOfOtherAnimals_Dogs);
        pageOfOtherAnimals_Cats = getView().findViewById(R.id.pageOfOtherAnimals_Cats);
        ageOfOtherAnimals_Others = getView().findViewById(R.id.ageOfOtherAnimals_Others);

        genderOfOtherAnimals_Dogs = getView().findViewById(R.id.genderOfOtherAnimals_Dogs);
        genderOfOtherAnimals_Cats = getView().findViewById(R.id.genderOfOtherAnimals_Cats);
        genderOfOtherAnimals_Others = getView().findViewById(R.id.genderOfOtherAnimals_Others);

        animalsSterilised = getView().findViewById(R.id.animalsSterilised);

        animals_Not_Sterilised = getView().findViewById(R.id.animals_Not_Sterilised);

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext3);
        btnBack = getView().findViewById(R.id.btnBack2);

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

    private boolean checkRequiredUserInput()
    {

        return true;
    }

    private void checkOtherUserInputs()
    {
        if (pageOfOtherAnimals_Dogs.getText().toString().trim().isEmpty())
        {
            pageOfOtherAnimals_Dogs.setText("0");
        }
        if (pageOfOtherAnimals_Cats.getText().toString().trim().isEmpty())
        {
            pageOfOtherAnimals_Cats.setText("0");
        }
        if (ageOfOtherAnimals_Others.getText().toString().trim().isEmpty())
        {
            ageOfOtherAnimals_Others.setText("0");
        }
        if (genderOfOtherAnimals_Dogs.getText().toString().trim().isEmpty())
        {
            genderOfOtherAnimals_Dogs.setText("0");
        }
        if (genderOfOtherAnimals_Cats.getText().toString().trim().isEmpty())
        {
            genderOfOtherAnimals_Cats.setText("0");
        }
        if (genderOfOtherAnimals_Others.getText().toString().trim().isEmpty())
        {
            genderOfOtherAnimals_Others.setText("0");
        }
    }

    private void saveUserInput()
    {

    }
}