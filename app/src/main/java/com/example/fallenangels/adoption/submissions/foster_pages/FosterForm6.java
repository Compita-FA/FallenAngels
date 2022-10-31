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
import android.widget.Toast;

import com.example.fallenangels.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FosterForm6 extends Fragment {
    private EditText previousPets;
    private EditText missingDogs;
    private EditText permanentHome;
    private EditText teachingWillingness;
    private EditText holidayRespon;

    private AppCompatButton btnNext;
    private AppCompatButton btnBack;

    public FosterForm6() {
        // Required empty public constructor
    }

    public static FosterForm6 newInstance(String param1, String param2) {
        FosterForm6 fragment = new FosterForm6();
        Bundle args = new Bundle();
        args.putString("FosterForm6", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foster_form6, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        previousPets = getView().findViewById(R.id.f_previousPets);
        missingDogs = getView().findViewById(R.id.f_havePetsGoneMissing);
        permanentHome = getView().findViewById(R.id.f_permanent_home);
        teachingWillingness = getView().findViewById(R.id.f_teach_pet);
        holidayRespon = getView().findViewById(R.id.f_responsibleForPets);
        btnNext = getView().findViewById(R.id.f_btnNext7);
        btnBack = getView().findViewById(R.id.f_btnBack5);

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
                    ft.replace(R.id.frag_layout, new FosterForm7());
                    ft.commit();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm5());
                ft.commit();
            }
        });
    }

    private boolean checkRequiredUserInput()
    {
        if (previousPets.getText().toString().trim().isEmpty() || missingDogs.getText().toString().trim().isEmpty()
                || permanentHome.getText().toString().trim().isEmpty() || teachingWillingness.getText().toString().trim().isEmpty()
                || holidayRespon.getText().toString().trim().isEmpty())
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
        FosterForm1.newForm.setPg6_previousPets(previousPets.getText().toString());
        FosterForm1.newForm.setPg6_havePetsGoneMissing(missingDogs.getText().toString());
        FosterForm1.newForm.setPg6_permanentHome(permanentHome.getText().toString());
        FosterForm1.newForm.setPg6_teachingWillingness(teachingWillingness.getText().toString());
        FosterForm1.newForm.setPg6_petResponsibility_whenOnHoliday(holidayRespon.getText().toString());
    }
}