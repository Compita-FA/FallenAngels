package com.example.fallenangels.others;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fallenangels.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GalleryFragment extends Fragment {

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //Showing the nav view
        BottomNavigationView bottomNav;
        bottomNav = getActivity().findViewById(R.id.bottomNavView);
        bottomNav.setVisibility(View.VISIBLE);
    }
}