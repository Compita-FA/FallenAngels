package com.example.fallenangels.startup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.startup.userObject.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity
{
    // --- Initialisation of Screen Elements
    private Button btnCreateAccount;
    private TextView btnLogin;
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtEmail;
    private EditText txtPass;
    private EditText txtConPass;

    //Firebase variables
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbUsersRef = db.getReference("Users");
    private FirebaseAuth mAuth;
    private FirebaseUser fUser;

    //User varibles
    private String userID;
    private static final String TAG = "EmailPassword";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //--- Finding ID's
            //--- Buttons
            btnCreateAccount = findViewById(R.id.btn_CreateAcc);
            btnLogin = findViewById(R.id.btnLogin);

            //--- Input
            txtName = findViewById(R.id.edt_firstName);
            txtSurname = findViewById(R.id.edt_lastName);
            txtEmail = findViewById(R.id.edt_Email);
            txtPass = findViewById(R.id.edt_password);
            txtConPass = findViewById(R.id.edt_confirmPassword);

        //--- Firebase
        user = new User();
        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();

        //Listeners
        btnCreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Check if all fields are not empty and contain a value
                if (!txtName.getText().toString().isEmpty() && !txtSurname.getText().toString().isEmpty() &&
                        !txtEmail.getText().toString().isEmpty() && !txtPass.getText().toString().isEmpty() &&
                        !txtConPass.getText().toString().isEmpty())
                {
                    //Run method to check for input validation
                    CheckUserInput();
                }
                else
                {
                    Toast.makeText(Register.this, "Please make sure all fields have been entered",Toast.LENGTH_LONG).show();
                }

                //Intent i = new Intent(Register.this, Login.class);
                //startActivity(i);
            }
        });
    }

    //--------------------------------- Log In On Click method -------------------------------------
    public void ClickLogIn(View view)
    {
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }

    //---------------Checking if all inputs are valid (email, password matching)--------------------
    private void CheckUserInput()
    {
        String email = txtEmail.getText().toString();
        String pass = txtPass.getText().toString();
        String conPass = txtConPass.getText().toString();

        //Check if passwords are matching and email is valid
        if (pass.equals(conPass))
        {
            //Check if length of password is 6 or more
            if (pass.length() >= 6)
            {
                if (ValidateEmail(email))
                {
                    //If email is valid, run method to register user
                    RegisterUser(email, pass);
                }
                else
                {
                    //If the email is not valid
                    Toast.makeText(Register.this,"Invalid email! Please try again.",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                //If length of password is less than 6
                Toast.makeText(Register.this, "Password must contain at least 6 characters.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //If passwords do not match
            Toast.makeText(Register.this,  "Passwords do not match. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    //------------------Check for validity of email. Return true if it's valid----------------------
    private boolean ValidateEmail(String email)
    {
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //--------------------Register the user with authentication in firebase-------------------------
    private void RegisterUser(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    SendVerificationEmail(email);
                }
                else
                {
                    // If sign in fails, display error message to user
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Register.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //-------------------------Method to send a verification email to user--------------------------
    private void SendVerificationEmail(String email)
    {
        //Retrieving string values of name and surname
        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        userID = mAuth.getCurrentUser().getUid().toString();

        fUser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    //If email has successfully been sent
                    DisplaySuccessDialogue();

                    //Run method to add the user to the database
                    AddUserToDatabase(name, surname, email, userID);
                    Log.d("onSuccess_TAG", "onSuccess: Email verification link sent");
                }
                else
                {
                    //If task fails to send a verification email
                    Log.e(TAG, "sendEmailVerification", task.getException());
                    Toast.makeText(Register.this,
                            "Failed to send verification email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //----------------------Method to display the Success message dialogue--------------------------
    private void DisplaySuccessDialogue()
    {
        //New dialogue instance
        Dialog dialog = new Dialog(this);

        //Prevent user from click away
        dialog.setCanceledOnTouchOutside(false);
        //Show register dialogue layout
        dialog.setContentView(R.layout.dialogue_register);

        Button btnOk = dialog.findViewById(R.id.btnOK);

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), Login.class));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //--------------------------Method to add the user to the database------------------------------
    private void AddUserToDatabase(String name, String surname, String email, String userID)
    {
        //Ensuring both first letters of name and surname are capital
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        surname = surname.substring(0,1).toUpperCase() + surname.substring(1);

        DatabaseReference ref = dbUsersRef.child(userID).child("Account");

        user.setFirstName(name);
        user.setLastName(surname);
        user.setEmail(email);
        user.setUserID(userID);

        ref.push().setValue(user);
        //AddDefaultSettings(userID);
    }
}