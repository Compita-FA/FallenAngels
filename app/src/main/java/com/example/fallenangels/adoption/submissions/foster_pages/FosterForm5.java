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
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm1;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm5;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FosterForm5 extends Fragment {

    private EditText petsDiet;
    private EditText hoursAlone;
    private EditText sleepArea;
    private EditText petPast;

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public FosterForm5() {
        // Required empty public constructor
    }

    public static FosterForm5 newInstance(String param1, String param2) {
        FosterForm5 fragment = new FosterForm5();
        Bundle args = new Bundle();
        args.putString("FosterForm5", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foster_form5, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        petsDiet = getView().findViewById(R.id.f_petsDiet);
        hoursAlone = getView().findViewById(R.id.f_hoursAlone);
        sleepArea = getView().findViewById(R.id.f_fosters_sleep);
        petPast = getView().findViewById(R.id.f_hoursAlone);
        btnNext = getView().findViewById(R.id.f_btnNext6);
        btnBack = getView().findViewById(R.id.f_btnBack4);

        //Hiding the nav view
        BottomNavigationView bottomNav;
        bottomNav = getActivity().findViewById(R.id.bottomNavView);
        bottomNav.setVisibility(View.INVISIBLE);

        //Listeners
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkRequiredUserInput() == true)
                {
                    saveUserInput();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frag_layout, new FosterForm6());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm4());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        if (petsDiet.getText().toString().trim().isEmpty() || hoursAlone.getText().toString().trim().isEmpty() ||
                sleepArea.getText().toString().trim().isEmpty() || petPast.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getActivity(), "Please fill in all fields!", Toast.LENGTH_LONG).show();
            return false;
        }else
        {
            return true;
        }
    }

    private void saveUserInput()
    {
        FosterForm1.newForm.setPg5_petsDiet(petsDiet.getText().toString());
        FosterForm1.newForm.setPg5_hoursAlone(hoursAlone.getText().toString());
        FosterForm1.newForm.setPg5_sleepingArea(sleepArea.getText().toString());
        FosterForm1.newForm.setPg5_sleepingArea(petPast.getText().toString());
    }
}