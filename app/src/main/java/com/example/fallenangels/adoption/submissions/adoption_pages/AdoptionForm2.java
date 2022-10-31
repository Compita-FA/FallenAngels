package com.example.fallenangels.adoption.submissions.adoption_pages;

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
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AdoptionForm2 extends Fragment
{
    //User input fields
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
    //User input fields

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    private Boolean contactNumber = false, emailAddress= false, ID= false, MAILINGLIST, OtherAnimalFormat;

    public AdoptionForm2()
    {
        // Required empty public constructor


    }

    public static AdoptionForm2 newInstance(String param1)
    {
        AdoptionForm2 fragment = new AdoptionForm2();
        Bundle args = new Bundle();
        args.putString("AdoptForm2", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        ownerContactNumber1 = getView().findViewById(R.id.ownerContactNumber1);
        ownerContactNumber2 = getView().findViewById(R.id.ownerContactNumber2);
        ownerEmail1 = getView().findViewById(R.id.ownerEmail1);
        ownerEmail2 = getView().findViewById(R.id.ownerEmail2);
        ownerIDNumber  = getView().findViewById(R.id.ownerIDNumber);
        ownerAddToMailingList = getView().findViewById(R.id.ownerAddToMailingList);
        childrenAges = getView().findViewById(R.id.childrenAges);
        otherAnimal_Dogs = getView().findViewById(R.id.otherAnimal_Dogs);
        otherAnimal_Cats = getView().findViewById(R.id.otherAnimal_Cats);
        otherAnimal_Other = getView().findViewById(R.id.otherAnimal_Other);
        btnNext = getView().findViewById(R.id.btnNext2);
        btnBack = getView().findViewById(R.id.btnBack1);

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
                    ft.replace(R.id.frag_layout, new AdoptionForm3());
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
                ft.replace(R.id.frag_layout, new AdoptionForm1());
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
        if (otherAnimalsDogsT.matches("[0-9]+") || !otherAnimalsDogsT.isEmpty()
                && otherAnimalsCatsT.matches("[0-9]+") || !otherAnimalsCatsT.isEmpty()
                && otherAnimalsOtherT.matches("[0-9]+") || !otherAnimalsOtherT.isEmpty())
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
        AdoptionForm1.newForm.setPg2_ownerContactNumber(ownerContactNumber1.getText().toString() + ", " + ownerContactNumber2.getText().toString());
        AdoptionForm1.newForm.setPg2_ownerEmail(ownerEmail1.getText().toString() + ", " + ownerEmail2.getText().toString());
        AdoptionForm1.newForm.setPg2_ownerIDNumber(ownerIDNumber.getText().toString());

        int selectedId = ownerAddToMailingList.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton) getView().findViewById(selectedId); // find the radiobutton by returned id
        AdoptionForm1.newForm.setPg2_ownerAddToMailingList(selectedRadioButton.getText().toString());

        AdoptionForm1.newForm.setPg2_childrenAges(childrenAges.getText().toString());

        AdoptionForm1.newForm.setPg2_otherAnimal_Dogs(otherAnimal_Dogs.getText().toString().trim());
        AdoptionForm1.newForm.setPg2_otherAnimal_Cats(otherAnimal_Cats.getText().toString().trim());
        AdoptionForm1.newForm.setPg2_otherAnimal_Other(otherAnimal_Other.getText().toString().trim());
    }
}