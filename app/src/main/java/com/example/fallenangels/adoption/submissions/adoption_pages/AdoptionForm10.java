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


public class AdoptionForm10 extends Fragment
{
    Boolean referanceName = false , referanceRelation = false , referanceAddress = false , referanceCell = false , referanceEmail = false;

    //User input fields
    private EditText refOne_Name;
    private EditText refOne_Relationship;
    private EditText refOne_Address;
    private EditText refOne_Cell;
    private EditText refOne_Landline;
    private EditText refOne_Email;
    //User input fields

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;


    public AdoptionForm10()
    {
        // Required empty public constructor
    }


    public static AdoptionForm10 newInstance(String param1) {
        AdoptionForm10 fragment = new AdoptionForm10();
        Bundle args = new Bundle();
        args.putString("AdoptForm10", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form10, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        refOne_Name = getView().findViewById(R.id.refOne_Name);
        refOne_Relationship = getView().findViewById(R.id.refOne_Relationship);
        refOne_Address = getView().findViewById(R.id.refOne_Address);
        refOne_Cell = getView().findViewById(R.id.refOne_Cell);
        refOne_Landline = getView().findViewById(R.id.refOne_Landline);
        refOne_Email = getView().findViewById(R.id.refOne_Email);

        //Finding ID's
        btnNext = getView().findViewById(R.id.btnNext10);
        btnBack = getView().findViewById(R.id.btnBack9);

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
                    ft.replace(R.id.frag_layout, new AdoptionForm11());
                    ft.commit();
                }


            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm9());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        //Checks Name, relaion and address
        if (refOne_Name.getText().toString().trim().isEmpty() || refOne_Relationship.getText().toString().trim().isEmpty() ||
                refOne_Address.getText().toString().trim().isEmpty())
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
        if (refOne_Cell.getText().toString().trim().isEmpty() == false)
        {
            String text = refOne_Cell.getText().toString().trim();
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
        if (refOne_Email.getText().toString().trim().isEmpty() == false)
        {
            emailValidator(refOne_Email);
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
        AdoptionForm1.newForm.setPg10_referanceOneName(refOne_Name.getText().toString());
        AdoptionForm1.newForm.setPg10_referanceOneRelationship(refOne_Relationship.getText().toString());
        AdoptionForm1.newForm.setPg10_referanceOneAddress(refOne_Address.getText().toString());
        AdoptionForm1.newForm.setPg10_referanceOneCell(refOne_Cell.getText().toString().trim());
        AdoptionForm1.newForm.setPg10_referanceOneLandline(refOne_Landline.getText().toString().trim());
        AdoptionForm1.newForm.setPg10_referanceOneEmail(refOne_Email.getText().toString().trim());
    }
}