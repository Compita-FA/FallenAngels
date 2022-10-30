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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm2;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm3;
import com.example.fallenangels.adoption.submissions.adoption_pages.adoptionFormObject.AdoptionFormModel;
import com.example.fallenangels.adoption.submissions.foster_pages.FostorFormObject.FostorFormModel;


public class FosterForm1 extends Fragment {
    static FostorFormModel newForm = new FostorFormModel();

    private RadioGroup animalChoice;
    private EditText edt_dogNameOne;
    private EditText edt_dogNameTwo;
    private EditText edt_NameSurname;
    private EditText edt_address;

    private AppCompatButton btnNext;

    public FosterForm1() {
        // Required empty public constructor
    }

    public static FosterForm1 newInstance(String param1, String param2) {
        FosterForm1 fragment = new FosterForm1();
        Bundle args = new Bundle();
        args.putString("FosterForm1", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foster_form1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        animalChoice = getView().findViewById(R.id.rg_animalChoice);
        edt_dogNameOne = getView().findViewById(R.id.edt_f_dogNameOne);
        edt_dogNameTwo = getView().findViewById(R.id.edt_f_dogNameTwo);
        edt_NameSurname = getView().findViewById(R.id.edt_f_NameSurname);
        edt_address = getView().findViewById(R.id.edt_f_address);

        //Finding ID's
        btnNext = getView().findViewById(R.id.f_btnNext2);

        //Listeners
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                if (checkRequiredUserInput() == true)
                {
                    saveUserInput();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frag_layout, new FosterForm2());
                    ft.commit();
                }
            }
        });
    }

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
}