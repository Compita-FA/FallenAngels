package com.example.fallenangels.adoption.submissions.adoption_pages;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.MainAdoptFragment;
import com.example.fallenangels.others.HomeFragment;
import com.example.fallenangels.startup.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AdoptionForm12 extends Fragment
{
    //Date
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat DayFormat, MonthFormat, YearFormat, TimeFormat;
    private String Day, Month, Year, Time;

    private DatabaseReference dbRef;
    private String userID;
    public static String dog1ID;
    public static String dog2ID;

    private EditText currentDay;
    private EditText currentMonth;
    private EditText currentYear;
    private EditText currrentTime;
    private EditText signature;

    private BottomNavigationView bottomNav;

    private TextView txtViewRules;

    private AppCompatButton btnBack;
    private AppCompatButton btnSubmit;
    private AppCompatButton btnCancel;

    public AdoptionForm12() {
        // Required empty public constructor
    }

    private void setTimeStampData()
    {
        DayFormat = new SimpleDateFormat("dd");
        MonthFormat = new SimpleDateFormat("MMMM");
        YearFormat = new SimpleDateFormat("yyyy");
        TimeFormat = new SimpleDateFormat("HH:mm:ss");

        Day = DayFormat.format(calendar.getTime());
        Month = MonthFormat.format(calendar.getTime());
        Year = YearFormat.format(calendar.getTime());
        Time = TimeFormat.format(calendar.getTime());

        currentDay.setText(Day);
        currentMonth.setText(Month);
        currentYear.setText(Year);
        currrentTime.setText(Time);
    }

    public static AdoptionForm12 newInstance(String param1) {
        AdoptionForm12 fragment = new AdoptionForm12();
        Bundle args = new Bundle();
        args.putString("AdoptForm12", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form12, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        //finding ID's
        currentDay = getView().findViewById(R.id.edt_currentDay);
        currentMonth = getView().findViewById(R.id.edt_currentMonth);
        currentYear = getView().findViewById(R.id.edt_currentYear);
        currrentTime = getView().findViewById(R.id.edt_currentTime);
        signature = getView().findViewById(R.id.edt_Signature);
        btnBack = getView().findViewById(R.id.btnBack11);
        btnSubmit = getView().findViewById(R.id.btnSubmit);
        btnCancel = getView().findViewById(R.id.btnCancel);
        txtViewRules = getView().findViewById(R.id.txtViewTerms);

        //Hiding the nav view
        bottomNav = getActivity().findViewById(R.id.bottomNavView);
        bottomNav.setVisibility(View.INVISIBLE);

        //Default operations
        Login login = new Login();
        userID = login.userID;

        setTimeStampData();

        //Listeners
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm11());
                ft.commit();
            }
        });

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(getActivity());

                //Setting message manually and performing action on button click
                builder.setMessage("Are you sure you want to cancel your progress?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //User clicks yes
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frag_layout, new MainAdoptFragment());
                                ft.commit();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //User clicks no
                        return;
                    }
                });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Cancel form");
                alert.show();
            }
        });

        txtViewRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewTermsConditions(view);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(signature.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getActivity(), "Signature is required to Submit!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    saveUserInput();
                    submitApplicationDialog();
                }
            }
        });
    }

    //------------------------------ Show terms & conditions dialogue ------------------------------
    public void ViewTermsConditions(View view) {
        Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialogue_ts_and_cs_adoption);

        AppCompatButton btnClose = dialog.findViewById(R.id.btnCloseRules);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    //----------------------------------------------------------------------------------------------


    private void saveUserInput()
    {
        AdoptionForm1.newForm.setPg12_CurrentDay(currentDay.getText().toString());
        AdoptionForm1.newForm.setPg12_CurrentMonth(currentMonth.getText().toString());
        AdoptionForm1.newForm.setPg12_CurrentYear(currentYear.getText().toString());
        AdoptionForm1.newForm.setPg12_CurrentTime(currrentTime.getText().toString().trim());
        AdoptionForm1.newForm.setPg12_Signature(signature.getText().toString().trim());
    }


    //----------------------------- Method to save submission to DB --------------------------------
    private void submitApplicationDialog()
    {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());

        DatabaseReference dbRefDog = FirebaseDatabase.getInstance().getReference("Users").child(userID).child("SubmittedDogs").push().child("dogID");

        //Setting message manually and performing action on button click
        builder.setMessage("Would you like to submit this Adoption Application?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //  Action for 'Yes' Button
                        final ProgressDialog pd = new ProgressDialog(getView().getContext());
                        pd.setTitle("Uploading Submission...");
                        pd.show();

                        dbRef = FirebaseDatabase.getInstance().getReference("Forms").child(userID).child("AdoptionForms");
                        dbRef.push().setValue(AdoptionForm1.newForm);

                        dbRefDog.setValue(dog1ID);
                        //dbRefDog.push().setValue(dog2ID);

                        pd.dismiss();
                        Snackbar.make(getActivity().findViewById(android.R.id.content), "Submission Complete.", Snackbar.LENGTH_LONG).show();

                        //Go back to main adoption page
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.frag_layout, new HomeFragment());
                        ft.commit();
                        bottomNav.setSelectedItemId(R.id.bttm_item_home);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //  Action for 'No' Button
                        dialog.dismiss();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Adoption Form");
        alert.show();
    }
    //----------------------------------------------------------------------------------------------


    /*
    public void testStorage()
    {
        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 1:\n"
                + "Animal Selection: " + AdoptionForm1.newForm.getPg1_animalSelection()
                + "Animal Name: " + AdoptionForm1.newForm.getPg1_animalSelection()
                + "Owner Name and Surname: " + AdoptionForm1.newForm.getPg1_animalSelection()
                + "Owner Address: " + AdoptionForm1.newForm.getPg1_animalSelection(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 2:\n"
                + "Owner Contact Number: " + AdoptionForm1.newForm.getPg2_ownerContactNumber()
                + "Owner Email: " + AdoptionForm1.newForm.getPg2_ownerEmail()
                + "Owner ID: " + AdoptionForm1.newForm.getPg2_ownerIDNumber()
                + "Owner Add to Mailing List: " + AdoptionForm1.newForm.getPg2_ownerAddToMailingList()
                + "Age of Children: " + AdoptionForm1.newForm.getPg2_childrenAges()
                + "Other Dogs: " + AdoptionForm1.newForm.getPg2_otherAnimal_Dogs()
                + "Other Cats: " + AdoptionForm1.newForm.getPg2_otherAnimal_Cats()
                + "Other Animals: " + AdoptionForm1.newForm.getPg2_otherAnimal_Other(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 3:\n"
                + "Age of Dogs: " + AdoptionForm1.newForm.getPg3_ageOfOtherAnimals_Dogs()
                + "Age of Cats: " + AdoptionForm1.newForm.getPg3_ageOfOtherAnimals_Cats()
                + "Age of Other: " + AdoptionForm1.newForm.getPg3_ageOfOtherAnimals_Others()
                + "Gender of Dogs: " + AdoptionForm1.newForm.getPg3_genderOfOtherAnimals_Dogs()
                + "Gender of Cats: " + AdoptionForm1.newForm.getPg3_genderOfOtherAnimals_Cats()
                + "Gender of Other: " + AdoptionForm1.newForm.getPg3_genderOfOtherAnimals_Others()
                + "Sterilized: " + AdoptionForm1.newForm.getPg3_animalsSterilised()
                + "Not Sterilized: " + AdoptionForm1.newForm.getPg3_animals_Not_Sterilised(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 4:\n"
                + "Not Sterilized Reason: " + AdoptionForm1.newForm.getPg4_reasonForNonSterilisation()
                + "Litter Before: " + AdoptionForm1.newForm.getPg4_litterBeforeSterilisation()
                + "Fully vaxed: " + AdoptionForm1.newForm.getPg4_fullyVaccinatedStatus()
                + "Pets Diet: " + AdoptionForm1.newForm.getPg4_petsDiet()
                + "Hours Alone: " + AdoptionForm1.newForm.getPg4_hoursAlone(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 5:\n"
                + "Hours together: " + AdoptionForm1.newForm.getPg5_hoursTogether()
                + "Reason for adoption: " + AdoptionForm1.newForm.getPg5_reasonForAdoption()
                + "Surrender Reason: " + AdoptionForm1.newForm.getPg5_animalSurrender_Reason()
                + "Surrender status: " + AdoptionForm1.newForm.getPg5_animalSurrender_Status()
                + "Missing pets?: " + AdoptionForm1.newForm.getPg5_havePetsGoneMissing(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 6:\n"
                + "Permanent Home: " + AdoptionForm1.newForm.getPg6_permanentHome()
                + "Teaching willingness: " + AdoptionForm1.newForm.getPg6_teachingWillingness()
                + "Holiday Responsibility: " + AdoptionForm1.newForm.getPg6_petResponsibility_whenOnHoliday()
                + "On Circumstance Change: " + AdoptionForm1.newForm.getPg6_onCircumstanceChange()
                + "Sleeping situation: " + AdoptionForm1.newForm.getPg6_petSleepingSituation(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 7:\n"
                + "Pet inside: " + AdoptionForm1.newForm.getPg7_petInsideOutside()
                + "Yard status: " + AdoptionForm1.newForm.getPg7_yardStatus()
                + "Pool fencing: " + AdoptionForm1.newForm.getPg7_poolFencing()
                + "Number of feedings: " + AdoptionForm1.newForm.getPg7_numberOfFeedings(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 8:\n"
                + "Yard size: " + AdoptionForm1.newForm.getPg8_yardSize()
                + "Extra acts: " + AdoptionForm1.newForm.getPg8_petExtraActivities()
                + "Deaths: " + AdoptionForm1.newForm.getPg8_deathsOnPremises()
                + "Pet care: " + AdoptionForm1.newForm.getPg8_providePetCare()
                + "Type of house: " + AdoptionForm1.newForm.getPg8_typeOfHousing(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 9:\n"
                + "Own or rent: " + AdoptionForm1.newForm.getPg9_ownOrRent()
                + "Landlord: " + AdoptionForm1.newForm.getPg9_landlordPermission()
                + "Acknowledgment One: " + AdoptionForm1.newForm.getPg9_acknowledgementOf_dewormTicksFleas()
                + "Acknowledgment Two: " + AdoptionForm1.newForm.getPg9_acknowledgementOf_sterilisation(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 10: Reference One\n"
                + "Name: " + AdoptionForm1.newForm.getPg10_referanceOneName()
                + "Relationship: " + AdoptionForm1.newForm.getPg10_referanceOneRelationship()
                + "Address: " + AdoptionForm1.newForm.getPg10_referanceOneAddress()
                + "Cell: " + AdoptionForm1.newForm.getPg10_referanceOneCell()
                + "Landline: " + AdoptionForm1.newForm.getPg10_referanceOneLandline()
                + "Email: " + AdoptionForm1.newForm.getPg10_referanceOneEmail(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 11: Reference Two\n"
                + "Name: " + AdoptionForm1.newForm.getPg11_referanceTwoName()
                + "Relationship: " + AdoptionForm1.newForm.getPg11_referanceTwoRelationship()
                + "Address: " + AdoptionForm1.newForm.getPg11_referanceTwoAddress()
                + "Cell: " + AdoptionForm1.newForm.getPg11_referanceTwoCell()
                + "Landline: " + AdoptionForm1.newForm.getPg11_referanceTwoLandline()
                + "Email: " + AdoptionForm1.newForm.getPg11_referanceTwoEmail(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "All data collect: \n"
                + "Page 12: Reference Two\n"
                + "Day: " + AdoptionForm1.newForm.getPg12_CurrentDay()
                + "Month: " + AdoptionForm1.newForm.getPg12_CurrentMonth()
                + "Year: " + AdoptionForm1.newForm.getPg12_CurrentYear()
                + "Time: " + AdoptionForm1.newForm.getPg12_CurrentTime()
                + "Signature: " + AdoptionForm1.newForm.getPg12_Signature(), Toast.LENGTH_LONG).show();
    }

     */
}