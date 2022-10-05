package com.example.fallenangels.startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fallenangels.R;

public class Register extends AppCompatActivity {

    //Component variables
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Finding ID's
        btnCreateAccount = findViewById(R.id.btnCreateAcc);

        //Listeners
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });
    }

    //--------------------------------- Log In On Click method -------------------------------------
    public void ClickLogIn(View view) {
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }
    //----------------------------------------------------------------------------------------------
}