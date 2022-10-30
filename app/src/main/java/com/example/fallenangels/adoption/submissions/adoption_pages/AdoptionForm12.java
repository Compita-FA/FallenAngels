package com.example.fallenangels.adoption.submissions.adoption_pages;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fallenangels.R;


public class AdoptionForm12 extends Fragment {

    private AppCompatButton btnBack;
    private TextView txtView;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Finding ID's
        btnBack = getView().findViewById(R.id.btnBack11);
        txtView = getView().findViewById(R.id.txtViewTerms);

        //Listeners
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm11());
                ft.commit();
            }
        });

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewTermsConditions(view);
            }
        });

    }

    //------------------------------ Show terms & conditions dialogue ------------------------------
    public void ViewTermsConditions(View view) {

        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialogue_ts_and_cs_adoption);

        AppCompatButton closeDialogue = dialog.findViewById(R.id.btnCloseRules);

        closeDialogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    //----------------------------------------------------------------------------------------------
}