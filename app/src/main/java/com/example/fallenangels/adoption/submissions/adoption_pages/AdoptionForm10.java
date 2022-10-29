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

import com.example.fallenangels.R;


public class AdoptionForm10 extends Fragment
{
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
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm11());
                ft.commit();
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
}