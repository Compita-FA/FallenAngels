package com.example.fallenangels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fallenangels.startup.GetStarted;
import com.example.fallenangels.startup.Login;

public class MainActivity extends AppCompatActivity {

    private String hello = "hello world";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}