package com.example.fallenangels.adoption.submissions.adoption_pages;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.fallenangels.R;


public class AdoptionForm12 extends Fragment
{
    private EditText currentDay;
    private EditText currentMonth;
    private EditText currentYear;
    private EditText currrentTime;
    private EditText signature;

    private AppCompatButton btnBack;
    private AppCompatButton btnSubmit;
    private AppCompatButton btnCancel;

    public AdoptionForm12() {
        // Required empty public constructor
    }

    public static AdoptionForm12 newInstance(String param1) {
        AdoptionForm12 fragment = new AdoptionForm12();
        Bundle args = new Bundle();
        args.putString("AdoptForm12", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form12, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        currentDay = getView().findViewById(R.id.refTwo_Name);
        currentMonth = getView().findViewById(R.id.refTwo_Relationship);
        currentYear = getView().findViewById(R.id.refTwo_Address);
        currrentTime = getView().findViewById(R.id.refTwo_Cell);
        signature = getView().findViewById(R.id.refTwo_Landline);

        //Finding ID's
        btnBack = getView().findViewById(R.id.btnBack);
        btnSubmit = getView().findViewById(R.id.btnSubmit);
        btnCancel = getView().findViewById(R.id.btnCancel);

        //Listeners
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm11());
                ft.commit();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Implement Cancel feature
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(signature.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getActivity(), "Signature is required to Submit!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    saveUserInput();
                    submitApplicationDialog();
                }
            }
        });
    }

    private void saveUserInput()
    {
        AdoptionForm1.newForm.setPg12_CurrentDay(currentDay.getText().toString());
        AdoptionForm1.newForm.setPg12_CurrentMonth(currentMonth.getText().toString());
        AdoptionForm1.newForm.setPg12_CurrentYear(currentYear.getText().toString());
        AdoptionForm1.newForm.setPg12_CurrentTime(currrentTime.getText().toString().trim());
        AdoptionForm1.newForm.setPg12_Signature(signature.getText().toString().trim());
    }

    private void submitApplicationDialog()
    {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Would you like to submit this Adoption Application?");

        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to exit?")
        .setCancelable(false)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                //  Action for 'Yes' Button

            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                //  Action for 'No' Button
                dialog.dismiss();
            }
        });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Adoption Form");
        alert.show();
    }
}