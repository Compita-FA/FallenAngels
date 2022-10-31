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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AdoptionForm7 extends Fragment
{
    //User input fields
    private EditText petInsideOutside;
    private EditText petOutsideShelter;
    private EditText yardStatus;

    private EditText poolFencing_Cover;
    private EditText poolFencing_fence;
    private EditText poolFencing_CoverType;
    private EditText poolFencing_fenceType;

    private EditText numberOfFeedings;
    //User input fields

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public AdoptionForm7() {
        // Required empty public constructor
    }

    public static AdoptionForm7 newInstance(String param1) {
        AdoptionForm7 fragment = new AdoptionForm7();
        Bundle args = new Bundle();
        args.putString("AdoptForm7", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form7, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        petInsideOutside = getView().findViewById(R.id.petInsideOutside);
        petOutsideShelter = getView().findViewById(R.id.petOutsideShelter);
        yardStatus = getView().findViewById(R.id.yardStatus);
        poolFencing_Cover = getView().findViewById(R.id.poolFencing_Cover);
        poolFencing_fence = getView().findViewById(R.id.poolFencing_fence);
        poolFencing_CoverType = getView().findViewById(R.id.poolFencing_CoverType);
        poolFencing_fenceType = getView().findViewById(R.id.poolFencing_fenceType);
        numberOfFeedings = getView().findViewById(R.id.numberOfFeedings);
        btnNext = getView().findViewById(R.id.btnNext7);
        btnBack = getView().findViewById(R.id.btnBack6);

        //Hiding the nav view
        BottomNavigationView bottomNav;
        bottomNav = getActivity().findViewById(R.id.bottomNavView);
        bottomNav.setVisibility(View.INVISIBLE);

        //listeners
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
                    ft.replace(R.id.frag_layout, new AdoptionForm8());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm6());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        if (petInsideOutside.getText().toString().trim().isEmpty() || petOutsideShelter.getText().toString().trim().isEmpty() ||
                yardStatus.getText().toString().trim().isEmpty() || numberOfFeedings.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Please fill in all fields!", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private void checkOtherUserInputs()
    {
        if (poolFencing_fence.getText().toString().trim().isEmpty() )
        {
            poolFencing_fence.setText("N/A");
        }
        if (poolFencing_fenceType.getText().toString().trim().isEmpty())
        {
            poolFencing_fenceType.setText("N/A");
        }
        if (poolFencing_Cover.getText().toString().trim().isEmpty())
        {
            poolFencing_Cover.setText("N/A");
        }
        if (poolFencing_CoverType.getText().toString().trim().isEmpty())
        {
            poolFencing_CoverType.setText("N/A");
        }
    }

    private void saveUserInput()
    {
        AdoptionForm1.newForm.setPg7_petInsideOutside(petInsideOutside.getText().toString().trim());
        AdoptionForm1.newForm.setPg7_petOutsideShelter(petOutsideShelter.getText().toString().trim());
        AdoptionForm1.newForm.setPg7_yardStatus(yardStatus.getText().toString().trim());
        AdoptionForm1.newForm.setPg7_poolFencing(getPoolFencing());
        AdoptionForm1.newForm.setPg7_numberOfFeedings(numberOfFeedings.getText().toString().trim());
    }

    private String getPoolFencing()
    {
        String reason;
        String r1 = "", r2 = "";
        if(poolFencing_fence.getText().toString().trim().isEmpty() == false && poolFencing_fence.getText().toString().trim().isEmpty() == false)
        {
            r1 = "Fence: " + poolFencing_fence.getText().toString().trim() + ", of type " + poolFencing_fenceType.getText().toString().trim();
            r2 = "Cover: " + poolFencing_Cover.getText().toString().trim() + ", of type " + poolFencing_CoverType.getText().toString().trim();
        }
        else
        {
            if (poolFencing_fence.getText().toString().trim().isEmpty() == false)
            {
                r1 = poolFencing_fence.getText().toString().trim();
                r2 = poolFencing_fenceType.getText().toString().trim();
            }
            else if(poolFencing_Cover.getText().toString().trim().isEmpty() == false)
            {
                r1 = poolFencing_Cover.getText().toString().trim();
                r2 = poolFencing_CoverType.getText().toString().trim();
            }
        }

        reason = r1 + " " + r2;

        return reason;
    }
}