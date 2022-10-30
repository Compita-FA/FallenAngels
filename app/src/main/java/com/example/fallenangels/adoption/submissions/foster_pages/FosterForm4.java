package com.example.fallenangels.adoption.submissions.foster_pages;

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

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm1;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm5;


public class FosterForm4 extends Fragment
{
    boolean dogsBool = true, catsBool = true, otherBool = true;

    private EditText sterilizedOtherAnimals_Dogs;
    private EditText sterilizedOtherAnimals_Cats;
    private EditText sterilizedOtherAnimals_Others;

    private EditText steriReasonOtherAnimals_Dogs;
    private EditText steriReasonOtherAnimals_Cats;
    private EditText steriReasonOtherAnimals_Others;

    private EditText vaxedOtherAnimals_Dogs;
    private EditText vaxedOtherAnimals_Cats;

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public FosterForm4() {
        // Required empty public constructor
    }

    public static FosterForm4 newInstance(String param1, String param2) {
        FosterForm4 fragment = new FosterForm4();
        Bundle args = new Bundle();
        args.putString("FosterForm4", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foster_form4, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        sterilizedOtherAnimals_Dogs = getView().findViewById(R.id.f_sterilised_Dogs);
        sterilizedOtherAnimals_Cats = getView().findViewById(R.id.f_sterilised_Cats);
        sterilizedOtherAnimals_Others = getView().findViewById(R.id.f_sterilised_Others);

        steriReasonOtherAnimals_Dogs = getView().findViewById(R.id.f_reason_not_sterile_Dogs);
        steriReasonOtherAnimals_Cats = getView().findViewById(R.id.f_reason_not_sterile_Cats);
        steriReasonOtherAnimals_Others = getView().findViewById(R.id.f_reason_not_sterile_Other);

        vaxedOtherAnimals_Dogs = getView().findViewById(R.id.f_vaccinated_Dogs);
        vaxedOtherAnimals_Cats = getView().findViewById(R.id.f_vaccinated_Cats);

        checkInformation();

        //Finding ID's
        btnNext = getView().findViewById(R.id.f_btnNext5);
        btnBack = getView().findViewById(R.id.f_btnBack3);

        //Listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUserInputs();
                saveUserInput();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm5());
                ft.commit();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm3());
                ft.commit();
            }
        });
    }

    private void checkInformation()
    {
        int dogs = Integer.parseInt(FosterForm1.newForm.getPg2_otherAnimal_Dogs());
        int cats = Integer.parseInt(FosterForm1.newForm.getPg2_otherAnimal_Cats());
        int other = Integer.parseInt(FosterForm1.newForm.getPg2_otherAnimal_Other());

        if( dogs == 0)
        {
            sterilizedOtherAnimals_Dogs.setEnabled(false);
            steriReasonOtherAnimals_Dogs.setEnabled(false);
            vaxedOtherAnimals_Dogs.setEnabled(false);

            sterilizedOtherAnimals_Dogs.setHint("N/A");
            steriReasonOtherAnimals_Dogs.setHint("N/A");
            vaxedOtherAnimals_Dogs.setHint("N/A");

            dogsBool = false;
        }

        if( cats == 0)
        {
            sterilizedOtherAnimals_Cats.setEnabled(false);
            steriReasonOtherAnimals_Cats.setEnabled(false);
            vaxedOtherAnimals_Cats.setEnabled(false);

            sterilizedOtherAnimals_Cats.setHint("N/A");
            steriReasonOtherAnimals_Cats.setHint("N/A");
            vaxedOtherAnimals_Cats.setHint("N/A");

            catsBool = false;
        }

        if( other == 0)
        {
            sterilizedOtherAnimals_Others.setEnabled(false);
            steriReasonOtherAnimals_Others.setEnabled(false);

            sterilizedOtherAnimals_Others.setHint("N/A");
            steriReasonOtherAnimals_Others.setHint("N/A");

            otherBool = false;
        }
    }

    private void setUserInputs()
    {
        if (dogsBool == false)
        {
            sterilizedOtherAnimals_Dogs.setText("N/A");
            steriReasonOtherAnimals_Dogs.setText("N/A");
            vaxedOtherAnimals_Dogs.setText("N/A");
        }

        if (catsBool == false)
        {
            sterilizedOtherAnimals_Cats.setText("N/A");
            steriReasonOtherAnimals_Cats.setText("N/A");
            vaxedOtherAnimals_Cats.setText("N/A");
        }

        if (otherBool == false)
        {
            sterilizedOtherAnimals_Others.setText("N/A");
            steriReasonOtherAnimals_Others.setText("N/A");
        }
    }

    private void saveUserInput()
    {
        FosterForm1.newForm.setPg4_sterilizedOtherAnimals_Dogs(sterilizedOtherAnimals_Dogs.getText().toString());
        FosterForm1.newForm.setPg4_sterilizedOtherAnimals_Cats(sterilizedOtherAnimals_Cats.getText().toString());
        FosterForm1.newForm.setPg4_sterilizedOtherAnimals_Others(sterilizedOtherAnimals_Others.getText().toString());

        FosterForm1.newForm.setPg4_steriReasonOtherAnimals_Dogs(steriReasonOtherAnimals_Dogs.getText().toString());
        FosterForm1.newForm.setPg4_steriReasonOtherAnimals_Cats(steriReasonOtherAnimals_Cats.getText().toString());
        FosterForm1.newForm.setPg4_steriReasonOtherAnimals_Other(steriReasonOtherAnimals_Others.getText().toString());

        FosterForm1.newForm.setPg4_vaxedOtherAnimals_Dogs(vaxedOtherAnimals_Dogs.getText().toString());
        FosterForm1.newForm.setPg4_vaxedOfOtherAnimals_Cats(vaxedOtherAnimals_Cats.getText().toString());
    }
}