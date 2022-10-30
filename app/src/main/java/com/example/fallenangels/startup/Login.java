package com.example.fallenangels.startup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.user_pages.MainActivity;
import com.example.fallenangels.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity
{
    // --- Initialisation of Screen Elements
    private Button btnRegister;
    private Button btnLogin;
    private EditText txtEmail;
    private EditText txtPassword;
    private TextView txtForgotPassword;
    private CircularProgressIndicator indicator;

    // --- Initialisation of Firebase Variables
    private FirebaseAuth mAuth;

    //Type variables
    public static String userID = "NO_USER";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // --- Finding's ID's
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        txtEmail = findViewById(R.id.editTextEmail);
        txtPassword = findViewById(R.id.editTextPassword);

        // --- Firebase Instance
        mAuth = FirebaseAuth.getInstance();

        // --- Listeners
        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LoginUser();
            }
        });
    }

    private void LoginUser()
    {
        //Retrieving email and password string values
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        //Check for email and password combination in firebase authentication
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                    //Check if their email has been verified
                    if (!currentUser.isEmailVerified())
                    {
                        //If email has not been verified
                        Toast.makeText(Login.this, "Unable to login. Please verify your email", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //If email has been valid and password and email combination is authenticated
                        userID = mAuth.getCurrentUser().getUid();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }
                else
                {
                    //If email and password combination fails to authenticate
                    Toast.makeText(Login.this, "Unable to authenticate email and password combination. " +
                            "Please make sure this account exists.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}