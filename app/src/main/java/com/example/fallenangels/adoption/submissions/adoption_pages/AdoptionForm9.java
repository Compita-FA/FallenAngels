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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fallenangels.R;


public class AdoptionForm9 extends Fragment
{
    //User input fields
    private RadioGroup ownOrRent;
    private RadioGroup landlordPermission;
    private RadioGroup acknowledgementOf_dewormTicksFleas;
    private RadioGroup acknowledgementOf_sterilisation;
    //User input fields

    Boolean landlordPerm = false;

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm9() {
        // Required empty public constructor
    }

    public static AdoptionForm9 newInstance(String param1) {
        AdoptionForm9 fragment = new AdoptionForm9();
        Bundle args = new Bundle();
        args.putString("AdoptForm9", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form9, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        ownOrRent = getView().findViewById(R.id.ownOrRent);
        landlordPermission = getView().findViewById(R.id.landlordPermission);
        acknowledgementOf_dewormTicksFleas = getView().findViewById(R.id.acknowledgementOf_dewormTicksFleas);
        acknowledgementOf_sterilisation = getView().findViewById(R.id.acknowledgementOf_sterilisation);

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext9);
        btnBack = getView().findViewById(R.id.btnBack8);

        //listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkRequiredUserInput() == true)
                {
                    saveUserInput();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frag_layout, new AdoptionForm10());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm8());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        if (ownOrRent() == true && ensureAcknowledgements() == true)
        {
            if (landLord() == true)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    private boolean landLord()
    {
        if (landlordPermission.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "You forgot about landlord permission!", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean ownOrRent()
    {
        if (ownOrRent.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "Rent or Own cant be left blank!", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean ensureAcknowledgements()
    {
        if (acknowledgementOf_dewormTicksFleas.getCheckedRadioButtonId() == -1 && acknowledgementOf_sterilisation.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "To proceed you must acknowledge the last two options!", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            // radio button is checked
            int selectedId1 = acknowledgementOf_dewormTicksFleas.getCheckedRadioButtonId();
            RadioButton selectedAcknowledgementOne = (RadioButton) getView().findViewById(selectedId1); // find the radiobutton by returned id

            int selectedId2 = acknowledgementOf_sterilisation.getCheckedRadioButtonId();
            RadioButton selectedAcknowledgementTwo = (RadioButton) getView().findViewById(selectedId2); // find the radiobutton by returned id

            if (selectedAcknowledgementOne.getText().toString().trim().equals("Yes") && selectedAcknowledgementTwo.getText().toString().trim().equals("Yes"))
            {
                return true;
            }
            else
            {
                Toast.makeText(getActivity(), "To proceed you must acknowledge both as Yes!", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

    private void saveUserInput()
    {
        int ownRent = ownOrRent.getCheckedRadioButtonId();
        RadioButton ownRentButton = (RadioButton) getView().findViewById(ownRent); // find the radiobutton by returned id
        AdoptionForm1.newForm.setPg9_ownOrRent(ownRentButton.getText().toString().trim());

        if (landlordPermission.getCheckedRadioButtonId() == -1)
        {
            AdoptionForm1.newForm.setPg9_landlordPermission("N/A");
        }
        else
        {
            int yesNo = landlordPermission.getCheckedRadioButtonId();
            RadioButton yesNoRadioButton = (RadioButton) getView().findViewById(yesNo); // find the radiobutton by returned id
            AdoptionForm1.newForm.setPg9_landlordPermission(yesNoRadioButton.getText().toString().trim());
        }

        int ackOne = acknowledgementOf_dewormTicksFleas.getCheckedRadioButtonId();
        RadioButton ackOneRadioButton = (RadioButton) getView().findViewById(ackOne); // find the radiobutton by returned id
        AdoptionForm1.newForm.setPg9_acknowledgementOf_dewormTicksFleas(ackOneRadioButton.getText().toString().trim());

        int ackTwo = acknowledgementOf_sterilisation.getCheckedRadioButtonId();
        RadioButton ackTwoRadioButton = (RadioButton) getView().findViewById(ackTwo); // find the radiobutton by returned id
        AdoptionForm1.newForm.setPg9_acknowledgementOf_sterilisation(ackTwoRadioButton.getText().toString().trim());
    }
}