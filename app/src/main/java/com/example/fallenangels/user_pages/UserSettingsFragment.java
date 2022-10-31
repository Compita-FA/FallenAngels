package com.example.fallenangels.user_pages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.startup.Login;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class UserSettingsFragment extends Fragment {

    //Type variables
    private String userID;

    //Component Variables
    private TextView txtName;
    private TextView txtEmail;
    private AppCompatButton btnChangeEmail;
    private AppCompatButton btnUpdatePassword;

    //Firebase variables
    private FirebaseAuth mAuth;

    public UserSettingsFragment() {
        // Required empty public constructor
    }

    public static UserSettingsFragment newInstance(String param1, String param2) {
        UserSettingsFragment fragment = new UserSettingsFragment();
        Bundle args = new Bundle();
        args.putString("Settings", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Finding ID's
        txtName = getView().findViewById(R.id.txtAccName);
        txtEmail = getView().findViewById(R.id.txtAccEmail);
        btnChangeEmail = getView().findViewById(R.id.btnChangeEmail);
        btnUpdatePassword = getView().findViewById(R.id.btnChangePass);

        //Default operations
        MainActivity mainAct = new MainActivity();
        Login login = new Login();
        userID = login.userID;

        //Showing the nav view
        BottomNavigationView bottomNav;
        bottomNav = getActivity().findViewById(R.id.bottomNavView);
        bottomNav.setVisibility(View.VISIBLE);

        //--> Updating name and email
        txtName.setText(mainAct.currentName);
        txtEmail.setText(mainAct.currentEmail);

        if (!userID.equals("NO_USER")) {
            btnChangeEmail.setEnabled(true);
            btnUpdatePassword.setEnabled(true);
            ChangeEnabled(btnChangeEmail, btnUpdatePassword);
        } else {
            btnChangeEmail.setEnabled(false);
            btnUpdatePassword.setEnabled(false);
            ChangeEnabled(btnChangeEmail, btnUpdatePassword);
        }

    }



    //------------------------------- Change button colours accordingly ----------------------------
    private void ChangeEnabled(AppCompatButton btnChangeEmail, AppCompatButton btnUpdatePassword) {

        if (btnChangeEmail.isEnabled() && btnUpdatePassword.isEnabled()) {
            btnChangeEmail.setBackgroundResource(R.drawable.btn_pink_rounded);
            btnUpdatePassword.setBackgroundResource(R.drawable.btn_black_rounded);
        } else {
            btnChangeEmail.setBackgroundResource(R.drawable.btn_gray_rounded);
            btnUpdatePassword.setBackgroundResource(R.drawable.btn_gray_rounded);
        }
    }
    //----------------------------------------------------------------------------------------------
}