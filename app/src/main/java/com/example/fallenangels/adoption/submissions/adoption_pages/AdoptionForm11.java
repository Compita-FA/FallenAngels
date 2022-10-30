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
import android.widget.Toast;

import com.example.fallenangels.R;


public class AdoptionForm11 extends Fragment
{
    Boolean referanceName = false , referanceRelation = false , referanceAddress = false , referanceCell = false , referanceEmail = false;


    //User input fields
    private EditText refTwo_Name;
    private EditText refTwo_Relationship;
    private EditText refTwo_Address;
    private EditText refTwo_Cell;
    private EditText refTwo_Landline;
    private EditText refTwo_Email;
    //User input fields

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm11() {
        // Required empty public constructor
    }

    public static AdoptionForm11 newInstance(String param1) {
        AdoptionForm11 fragment = new AdoptionForm11();
        Bundle args = new Bundle();
        args.putString("AdoptForm11", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form11, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        refTwo_Name = getView().findViewById(R.id.refTwo_Name);
        refTwo_Relationship = getView().findViewById(R.id.refTwo_Relationship);
        refTwo_Address = getView().findViewById(R.id.refTwo_Address);
        refTwo_Cell = getView().findViewById(R.id.refTwo_Cell);
        refTwo_Landline = getView().findViewById(R.id.refTwo_Landline);
        refTwo_Email = getView().findViewById(R.id.refTwo_Email);

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext11);
        btnBack = getView().findViewById(R.id.btnBack10);


        //listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (checkRequiredUserInput() == true)
                {
                    saveUserInput();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frag_layout, new AdoptionForm12());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm10());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        //Checks Name, relaion and address
        if (refTwo_Name.getText().toString().trim().isEmpty() || refTwo_Relationship.getText().toString().trim().isEmpty() ||
                refTwo_Address.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Please fill in all fields!", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            referanceName = true ;
            referanceRelation = true;
            referanceAddress = true;
        }

        //Checks the contact number
        if (refTwo_Cell.getText().toString().trim().isEmpty() == false)
        {
            String text = refTwo_Cell.getText().toString().trim();
            if (text.matches("[0-9]+") && text.length() >= 10)
            {
                referanceCell = true;
            }
            else
            {
                Toast.makeText (getActivity(), "Cell is invalid!", Toast.LENGTH_LONG).show ();
            }
        }

        //CHecks the email
        if (refTwo_Email.getText().toString().trim().isEmpty() == false)
        {
            emailValidator(refTwo_Email);
        }

        if (referanceName == true && referanceRelation == true && referanceAddress == true && referanceCell == true && referanceEmail == true)
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
            referanceEmail = true;
        }
        else
        {
            Toast.makeText(getActivity(), "Invalid Email address!", Toast.LENGTH_LONG).show();
        }
    }

    private void saveUserInput()
    {
        AdoptionForm1.newForm.setPg11_referanceTwoName(refTwo_Name.getText().toString());
        AdoptionForm1.newForm.setPg11_referanceTwoRelationship(refTwo_Relationship.getText().toString());
        AdoptionForm1.newForm.setPg11_referanceTwoAddress(refTwo_Address.getText().toString());
        AdoptionForm1.newForm.setPg11_referanceTwoCell(refTwo_Cell.getText().toString().trim());
        AdoptionForm1.newForm.setPg11_referanceTwoLandline(refTwo_Landline.getText().toString().trim());
        AdoptionForm1.newForm.setPg11_referanceTwoEmail(refTwo_Email.getText().toString().trim());
    }
}