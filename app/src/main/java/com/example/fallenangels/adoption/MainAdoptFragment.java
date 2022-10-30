package com.example.fallenangels.adoption;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.dogObject.Dogs;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm1;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm12;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm2;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm3;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm4;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm5;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm6;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm7;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm8;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm9;
import com.example.fallenangels.adoption.submissions.foster_pages.FosterForm1;
import com.example.fallenangels.startup.Login;
import com.example.fallenangels.user_pages.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class MainAdoptFragment extends Fragment {

    private GridLayout layout;

    private String name = "";
    private String dob = "";
    public static String dogKey = "noKey";

    private Bitmap dogImage = null;

    private String dogName;
    private String breed;
    private String dogDOB;
    private String gender;
    private String intake;
    private String nature;
    private String history;
    private String suit;
    private String imageURL;
    private String cardImageURL;
    private String userID;


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

        //Default operations
        Login login = new Login();
        userID = login.userID;

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
                        dogKey = dog.getID();
                        cardImageURL = dog.getImgURL();

                        AddCard(name, dob, dogKey, cardImageURL);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    //----------------------------------------------------------------------------------------------


   //-------------------------- This method will display populated card views ----------------------
    private void AddCard(String name, String dob, String ID, String imgURL) {

        View cardView = getLayoutInflater().inflate(R.layout.cardview_dog_profile, null);
        TextView txtName = cardView.findViewById(R.id.txtCardName);
        TextView txtDOB = cardView.findViewById(R.id.txtCardDOB);
        ImageView imgView = cardView.findViewById(R.id.imgCardDog);

        txtName.setText(name);
        txtDOB.setText(dob);
        RetrieveImage(imgURL, imgView);

        layout.addView(cardView);

        //Show dog profile
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(getContext(), R.style.DialogStyle);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.layout_view_dog);

                Button btnClose = dialog.findViewById(R.id.btnCloseView);

                LoadProfile(dialog, ID);

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }
    //----------------------------------------------------------------------------------------------

    //----------------------------------- Loads view dog profile -----------------------------------
    private void LoadProfile(Dialog dialog, String ID) {

        TextView txtName = dialog.findViewById(R.id.txtDogName);
        TextView txtBreed = dialog.findViewById(R.id.txtBreed);
        TextView txtDOB = dialog.findViewById(R.id.txtDOB);
        TextView txtGender = dialog.findViewById(R.id.txtGender);
        TextView txtIntake = dialog.findViewById(R.id.txtIntake);
        TextView txtNature = dialog.findViewById(R.id.txtNature);
        TextView txtHistory = dialog.findViewById(R.id.txtHistory);
        TextView txtSuit = dialog.findViewById(R.id.txtSuit);
        ImageView imgDog = dialog.findViewById(R.id.imgViewDog);

        AppCompatButton btnAdopt = dialog.findViewById(R.id.btnAdopt);
        AppCompatButton btnFoster = dialog.findViewById(R.id.btnFoster);

        //Disable buttons if no user is logged in
        if (userID.equals("NO_USER")) {
            Toast.makeText(getContext(), userID, Toast.LENGTH_SHORT).show();
            btnAdopt.setEnabled(false);
            btnFoster.setEnabled(false);
            ChangeEnabled(btnAdopt, btnFoster);
        } else {
            Toast.makeText(getContext(), userID, Toast.LENGTH_SHORT).show();
            btnAdopt.setEnabled(true);
            btnFoster.setEnabled(true);
            ChangeEnabled(btnAdopt, btnFoster);

        }


        Query query = FirebaseDatabase.getInstance().getReference("Dogs").orderByChild("ID").equalTo(ID);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Dogs dog = ds.getValue(Dogs.class);

                        dogName = dog.getName();
                        breed = dog.getBreed();
                        dogDOB = dog.getDOB();
                        gender = dog.getGender();
                        intake = dog.getIntake();
                        nature = dog.getNature();
                        history = dog.getHistory();
                        suit = dog.getSuit();
                        imageURL = dog.getImgURL();
                    }

                    txtName.setText(dogName);
                    txtBreed.setText(breed);
                    txtDOB.setText(dogDOB);
                    txtGender.setText(gender);
                    txtIntake.setText(intake);
                    txtNature.setText(nature);
                    txtHistory.setText(history);
                    txtSuit.setText(suit);
                    RetrieveImage(imageURL, imgDog);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //Listeners
        btnAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new AdoptionForm1());
                ft.commit();
                dialog.dismiss();
            }
        });

        btnFoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frag_layout, new FosterForm1());
                ft.commit();
                dialog.dismiss();
            }
        });
    }
    //----------------------------------------------------------------------------------------------

    private void ChangeEnabled(AppCompatButton btnAdopt, AppCompatButton btnFoster) {

        if (btnAdopt.isEnabled() && btnFoster.isEnabled()) {
            btnAdopt.setBackgroundResource(R.drawable.btn_pink_rounded);
            btnFoster.setBackgroundResource(R.drawable.btn_orange_rounded);
        } else {
            btnAdopt.setBackgroundResource(R.drawable.btn_gray_rounded);
            btnFoster.setBackgroundResource(R.drawable.btn_gray_rounded);
        }
    }


    //------------------------------ Returns bitmap for each dog profile ---------------------------
    private void RetrieveImage(String link, ImageView img) {

        //Retrieving the image based on the image link
        StorageReference fileRef = FirebaseStorage.getInstance().getReference().getStorage().getReferenceFromUrl(link);
        try
        {
            final File localFile = File.createTempFile("DogImages", "jpeg");

            fileRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot)
                {
                    dogImage = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    img.setImageBitmap(dogImage);

                }
            }).addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    Log.e("error_retrieving_img", e.getMessage());
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getView().getContext(), "Major Error: retrieving dog image" + e, Toast.LENGTH_LONG).show();
        }


    }
    //----------------------------------------------------------------------------------------------



}