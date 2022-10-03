package com.example.fallenangels.startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fallenangels.R;

public class GetStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
    }

    //--------------------------------- Log In On Click method -------------------------------------
    public void ClickLogIn(View view) {
        Intent i = new Intent(GetStarted.this, Login.class);
        startActivity(i);
    }
    //----------------------------------------------------------------------------------------------
}