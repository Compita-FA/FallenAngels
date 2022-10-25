package com.example.fallenangels.adoption;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.dogObject.Dogs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainAdoptFragment extends Fragment {

    private GridLayout layout;

    private String name = "";
    private String dob = "";

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

        RetrieveDogData();
    }

    //------------------------------- Method retrieves dog name and DOB ----------------------------
    private void RetrieveDogData() {

        Query query = FirebaseDatabase.getInstance().getReference("Dogs");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Dogs dog = ds.getValue(Dogs.class);

                        name = dog.getName();
                        dob = dog.getDOB();

                        AddCard(name, dob);
                    }
                } else {
                    Toast.makeText(getContext(),"Snapshot does not exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    //----------------------------------------------------------------------------------------------


   //-------------------------- This method will display populated card views ----------------------
    private void AddCard(String name, String dob) {

        //TODO: RETRIEVE IMAGES

        View cardView = getLayoutInflater().inflate(R.layout.cardview_dog_profile, null);
        TextView txtName = cardView.findViewById(R.id.txtCardName);
        TextView txtDOB = cardView.findViewById(R.id.txtCardDOB);

        txtName.setText(name);
        txtDOB.setText(dob);

        layout.addView(cardView);
    }
    //----------------------------------------------------------------------------------------------

}