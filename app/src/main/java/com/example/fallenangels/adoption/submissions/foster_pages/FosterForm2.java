package com.example.fallenangels.adoption.submissions.foster_pages;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm1;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm3;


public class FosterForm2 extends Fragment {
    private EditText ownerContactNumber1;
    private EditText ownerContactNumber2;
    private EditText ownerEmail1;
    private EditText ownerEmail2;
    private EditText ownerIDNumber;
    private RadioGroup ownerAddToMailingList;
    private EditText childrenAges;
    private EditText otherAnimal_Dogs;
    private EditText otherAnimal_Cats;
    private EditText otherAnimal_Other;

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    private Boolean contactNumber = false, emailAddress= false, ID= false, MAILINGLIST, OtherAnimalFormat;

    public FosterForm2() {
        // Required empty public constructor
    }

    public static FosterForm2 newInstance(String param1, String param2) {
        FosterForm2 fragment = new FosterForm2();
        Bundle args = new Bundle();
        args.putString("FosterForm2", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foster_form2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        ownerContactNumber1 = getView().findViewById(R.id.owner_f_ContactNumber1);
        ownerContactNumber2 = getView().findViewById(R.id.owner_f_ContactNumber2);

        ownerEmail1 = getView().findViewById(R.id.owner_f_Email1);
        ownerEmail2 = getView().findViewById(R.id.owner_f_Email2);

        ownerIDNumber  = getView().findViewById(R.id.owner_f_IDNumber);

        ownerAddToMailingList = getView().findViewById(R.id.ownerAddToMailingList);

        childrenAges = getView().findViewById(R.id.f_childrenAges);

        otherAnimal_Dogs = getView().findViewById(R.id.f_otherAnimal_Dogs);
        otherAnimal_Cats = getView().findViewById(R.id.f_otherAnimal_Cats);
        otherAnimal_Other = getView().findViewById(R.id.f_otherAnimal_Other);

        //Finding ID's
        btnNext = getView().findViewById(R.id.f_btnNext3);
        btnBack = getView().findViewById(R.id.f_btnBack1);

        //Listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (checkRequiredUserInput() == true)
                {
                    checkOtherUserInputs();
                    saveUserInput();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frag_layout, new FosterForm3());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm1());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        if(ownerContactNumber1.getText().toString().trim().isEmpty() && ownerContactNumber2.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Please enter at least 1 Cell Number!", Toast.LENGTH_LONG).show();
        }
        else
        {
            if (ownerContactNumber1.getText().toString().trim().isEmpty() == false)
            {
                String text = ownerContactNumber1.getText().toString().trim();
                if (text.matches("[0-9]+") && text.length() >= 10)
                {
                    contactNumber = true;
                }
                else
                {
                    Toast.makeText (getActivity(), "Contact Number 1 is invalid!", Toast.LENGTH_LONG).show ();
                }
            }
            else
            {
                String text = ownerContactNumber2.getText().toString().trim();
                if (text.matches("[0-9]+") && text.length() >= 10)
                {
                    contactNumber = true;
                }
                else
                {
                    Toast.makeText (getActivity(), "Contact Number 2 is invalid!", Toast.LENGTH_LONG).show ();
                }
            }
        }

        if(ownerEmail1.getText().toString().trim().isEmpty() && ownerEmail2.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Please enter at least 1 Email Adress!", Toast.LENGTH_LONG).show();
        }
        else
        {
            if (ownerEmail1.getText().toString().trim().isEmpty() == false)
            {
                emailValidator(ownerEmail1);
                emailAddress = true;
            }
            else
            {
                emailValidator(ownerEmail2);
                emailAddress = true;
            }

            emailValidator(ownerEmail1);
            emailAddress = true;
        }

        if(ownerIDNumber.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "You can't leave you ID blank!", Toast.LENGTH_LONG).show();
        }
        else
        {
            String text = ownerIDNumber.getText().toString().trim();

            if (text.matches("[0-9]+") && text.length() >= 13)
            {
                ID = true;
            }
            else
            {
                Toast.makeText (getActivity(), "That's not a valid ID number!\nMake sure it contains only numbers and is longer that 13 Digits", Toast.LENGTH_LONG).show ();
            }
        }

        if (ownerAddToMailingList.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            Toast.makeText(getActivity(), "You need to select Yes or No!", Toast.LENGTH_SHORT).show();
            MAILINGLIST = false;
        }
        else
        {
            // radio button is checked
            MAILINGLIST = true;
        }

        String otherAnimalsDogsT = otherAnimal_Dogs.getText().toString().trim();
        String otherAnimalsCatsT = otherAnimal_Dogs.getText().toString().trim();
        String otherAnimalsOtherT = otherAnimal_Dogs.getText().toString().trim();
        if (otherAnimalsDogsT.matches("[0-9]+") || otherAnimalsDogsT.matches("")
                && otherAnimalsCatsT.matches("[0-9]+") || otherAnimalsCatsT.matches("")
                && otherAnimalsOtherT.matches("[0-9]+") || otherAnimalsOtherT.matches(""))
        {
            OtherAnimalFormat = true;
        }
        else
        {
            OtherAnimalFormat = false;
            Toast.makeText (getActivity(), "Please indicate other animals numerically!", Toast.LENGTH_LONG).show();
        }

        if (contactNumber == true && emailAddress == true && ID == true && MAILINGLIST == true && OtherAnimalFormat == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void emailValidator(EditText email)
    {
        // extract the entered data from the EditText
        String emailBeingTested = email.getText().toString();

        if (!emailBeingTested.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailBeingTested).matches())
        {
            //Toast.makeText(getActivity(), "Email Verified!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(), "Invalid Email address!", Toast.LENGTH_LONG).show();
        }
    }

    private void checkOtherUserInputs()
    {
        if (childrenAges.getText().toString().trim().isEmpty())
        {
            childrenAges.setText("No Children");
        }
        if (otherAnimal_Cats.getText().toString().trim().isEmpty())
        {
            otherAnimal_Cats.setText("0");
        }
        if (otherAnimal_Dogs.getText().toString().trim().isEmpty())
        {
            otherAnimal_Dogs.setText("0");
        }
        if (otherAnimal_Other.getText().toString().trim().isEmpty())
        {
            otherAnimal_Other.setText("0");
        }
    }

    private void saveUserInput()
    {
        FosterForm1.newForm.setPg2_ownerContactNumber(ownerContactNumber1.getText().toString() + ", " + ownerContactNumber2.getText().toString());
        FosterForm1.newForm.setPg2_ownerEmail(ownerEmail1.getText().toString() + ", " + ownerEmail2.getText().toString());
        FosterForm1.newForm.setPg2_ownerIDNumber(ownerIDNumber.getText().toString());

        int selectedId = ownerAddToMailingList.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton) getView().findViewById(selectedId); // find the radiobutton by returned id
        FosterForm1.newForm.setPg2_ownerAddToMailingList(selectedRadioButton.getText().toString());

        FosterForm1.newForm.setPg2_childrenAges(childrenAges.getText().toString());

        FosterForm1.newForm.setPg2_otherAnimal_Dogs(otherAnimal_Dogs.getText().toString().trim());
        FosterForm1.newForm.setPg2_otherAnimal_Cats(otherAnimal_Cats.getText().toString().trim());
        FosterForm1.newForm.setPg2_otherAnimal_Other(otherAnimal_Other.getText().toString().trim());
    }

}