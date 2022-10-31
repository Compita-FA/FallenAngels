package com.example.fallenangels.startup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
        txtForgotPassword = findViewById(R.id.txtChangePass);

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

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetPassword();
            }
        });
    }

    //--------------------------------- Log user in implementation ---------------------------------
    private void LoginUser()
    {
        //Retrieving email and password string values
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password.",Toast.LENGTH_SHORT).show();
        } else {

            //Check for email and password combination in firebase authentication
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                        //Check if their email has been verified
                        if (!currentUser.isEmailVerified()) {
                            //If email has not been verified
                            Toast.makeText(Login.this, "Unable to login. Please verify your email", Toast.LENGTH_SHORT).show();
                        } else {
                            //If email has been valid and password and email combination is authenticated
                            userID = mAuth.getCurrentUser().getUid();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    } else {
                        //If email and password combination fails to authenticate
                        Toast.makeText(Login.this, "Unable to authenticate email and password combination. " +
                                "Please make sure this account exists.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    //----------------------------------------------------------------------------------------------

    //-------------------------------- Forgot Password Dialogue ------------------------------------
    private void ResetPassword()
    {

        //New dialogue instance
        Dialog dialog = new Dialog(this, R.style.DialogStyle);

        //Prevent user from click away
        dialog.setCanceledOnTouchOutside(false);
        //Show register dialogue layout
        dialog.setContentView(R.layout.dialogue_reset_password);

        //Finding Id's
        Button btnChange = dialog.findViewById(R.id.btnChangePass);
        Button btnCancel = dialog.findViewById(R.id.btnCancelReset);
        EditText txtChangeEmail = dialog.findViewById(R.id.edtChangeEmail);

        btnChange.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!txtChangeEmail.getText().toString().isEmpty()) {

                    String email = txtChangeEmail.getText().toString();

                    mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            dialog.dismiss();
                            Toast.makeText(Login.this,"A reset link has been sent to your email.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(Login.this,"Email could not be found. Please make sure this email is registered.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else
                {
                    Toast.makeText(Login.this, "Please enter your email",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    //----------------------------------------------------------------------------------------------
}