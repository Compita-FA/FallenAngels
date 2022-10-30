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

import com.example.fallenangels.R;


public class AdoptionForm11 extends Fragment
{
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
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm12());
                ft.commit();
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
}