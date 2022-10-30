package com.example.fallenangels.adoption.submissions.foster_pages;

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
import android.widget.TextView;

import com.example.fallenangels.R;


public class FosterForm7 extends Fragment {

    private TextView txtViewRules;
    private AppCompatButton btnBack;

    public FosterForm7() {
        // Required empty public constructor
    }

    public static FosterForm7 newInstance(String param1, String param2) {
        FosterForm7 fragment = new FosterForm7();
        Bundle args = new Bundle();
        args.putString("FosterForm", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foster_form7, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Finding ID's
        txtViewRules = getView().findViewById(R.id.txtViewTerms2);
        btnBack = getView().findViewById(R.id.f_btnBack6);

        txtViewRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewTermsConditions(view);
            }
        });


        //Listeners
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm6());
                ft.commit();
            }
        });
    }

    //------------------------------ Show terms & conditions dialogue ------------------------------
    public void ViewTermsConditions(View view) {
        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialogue_ts_and_cs_foster);

        AppCompatButton btnClose = dialog.findViewById(R.id.f_btnCloseRules);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    //----------------------------------------------------------------------------------------------
}