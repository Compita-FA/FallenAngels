package com.example.fallenangels.adoption;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;

import com.example.fallenangels.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainAdoptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainAdoptFragment extends Fragment {

    private GridLayout layout;

    public MainAdoptFragment() {
        // Required empty public constructor
    }

    public static MainAdoptFragment newInstance(String title) {
        MainAdoptFragment fragment = new MainAdoptFragment();
        Bundle args = new Bundle();
        args.putString("Main Adoption", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_adopt, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Finding ID's
        layout = getView().findViewById(R.id.container);

        for (int i = 0; i < 10; i++) {
            AddCard();
        }

    }

    /*
   -------------------------- This method will display populated card views -----------------------
   */
    private void AddCard() {

        View cardView = getLayoutInflater().inflate(R.layout.cardview_dog_profile, null);

        layout.addView(cardView);
    }
    //----------------------------------------------------------------------------------------------
}