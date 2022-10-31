package com.example.fallenangels.user_pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.MainAdoptFragment;
import com.example.fallenangels.adoption.submissions.adoption_pages.AdoptionForm1;
import com.example.fallenangels.adoption.submissions.foster_pages.FosterForm1;
import com.example.fallenangels.others.AboutUsFragment;
import com.example.fallenangels.others.ContactFragment;
import com.example.fallenangels.others.EventsFragment;
import com.example.fallenangels.others.GalleryFragment;
import com.example.fallenangels.others.HomeFragment;
import com.example.fallenangels.others.MissionFragment;
import com.example.fallenangels.others.VolunteerFragment;
import com.example.fallenangels.startup.GetStarted;
import com.example.fallenangels.startup.Login;
import com.example.fallenangels.startup.userObject.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //Component variables
    private DrawerLayout mainDrawer;
    private BottomNavigationView bottomView;
    private NavigationView sideNavView;
    private TextView txtHeading;
    private TextView txtNavName;
    private TextView txtNavEmail;

    //Type variables
    private String userID;
    public static String currentEmail = "Please sign in or create an account";
    public static String currentName = "Guest User";

    //Firebase variables
    private FirebaseAuth mAuth;

    //Instances
    private HomeFragment homeFrag = new HomeFragment();
    private VolunteerFragment volFrag = new VolunteerFragment();
    private MainAdoptFragment adoptFrag = new MainAdoptFragment();
    private MissionFragment missFrag = new MissionFragment();
    private GalleryFragment gallFrag = new GalleryFragment();
    private EventsFragment eventsFrag = new EventsFragment();
    private ContactFragment conFrag = new ContactFragment();
    private AboutUsFragment aboutFrag = new AboutUsFragment();
    private UserSettingsFragment accFrag = new UserSettingsFragment();
    private AdoptionForm1 adoptFormFrag = new AdoptionForm1();
    private FosterForm1 fosterFormFrag = new FosterForm1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Finding ID's
        mainDrawer = findViewById(R.id.drawerLayout);
        bottomView = findViewById(R.id.bottomNavView);
        sideNavView = findViewById(R.id.mainNavView);
        txtHeading = findViewById(R.id.txtPageName);
        txtNavName = findViewById(R.id.txtNav_Name);
        txtNavEmail = findViewById(R.id.txtNav_Email);

        //Default operations
        Login login = new Login();
        userID = login.userID;
        bottomView.setVisibility(View.VISIBLE);


        if (!userID.equals("NO_USER")) {
            //--> Run method that retrieves details of currently logged in user
            FetchUserDetails();
        }

        //--> loading home screen
        bottomView.setSelectedItemId(R.id.bttm_item_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,homeFrag).commit();
        SetTopBar("Home");

        //Bottom Navigation Bar menu item On Click
        bottomView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.bttm_item_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,homeFrag).commit();
                        SetTopBar("Home");
                        return true;
                    case R.id.bttm_item_volunteer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,volFrag).commit();
                        SetTopBar("Volunteer");
                        return true;
                    case R.id.bttm_item_adopt:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,adoptFrag).commit();
                        SetTopBar("Adopt a Fur Angel");
                        return true;
                }
                return false;
            }
        });

        //Main Navigation Side Bar menu item on Click
        sideNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, homeFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Home");
                        bottomView.setSelectedItemId(R.id.bttm_item_home);
                        return true;
                    case R.id.item_adopt:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, adoptFrag).commit();
                        bottomView.setSelectedItemId(R.id.bttm_item_adopt);
                        SetTopBar("Adopt a Fur Angel");
                        closeDrawer(mainDrawer);
                        return true;
                    case R.id.item_volunteer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, volFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Get Involved");
                        bottomView.setSelectedItemId(R.id.bttm_item_volunteer);
                        return true;
                    case R.id.item_mission:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, missFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Our Mission");
                        bottomView.getMenu().findItem(R.id.bttm_invisible).setChecked(true);
                        return true;
                    case R.id.item_gallery:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, gallFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Gallery");
                        bottomView.getMenu().findItem(R.id.bttm_invisible).setChecked(true);
                        return true;
                    case R.id.item_events:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, eventsFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Events");
                        bottomView.getMenu().findItem(R.id.bttm_invisible).setChecked(true);
                        return true;
                    case R.id.item_contact:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, conFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Contact");
                        bottomView.getMenu().findItem(R.id.bttm_invisible).setChecked(true);
                        return true;
                    case R.id.item_aboutus:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, aboutFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("About Us");
                        bottomView.getMenu().findItem(R.id.bttm_invisible).setChecked(true);
                        return true;
                    case R.id.item_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, accFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Account");
                        bottomView.getMenu().findItem(R.id.bttm_invisible).setChecked(true);
                        return true;
                    case R.id.item_logout:
                        if (!userID.equals("NO_USER")) {
                            LogoutDialogue();
                            userID = "NO_USER";
                            login.userID = "NO_USER";
                        } else {
                            startActivity(new Intent(getApplicationContext(), GetStarted.class));
                        }
                        closeDrawer(mainDrawer);
                        return true;
                }
                return false;
            }
        });

    }

    //Change appearance of top bar if any other page is open
    private void SetTopBar(String heading) {
        txtHeading.setText(heading);
    }

    public void DisableBottomNav() {
        bottomView.setVisibility(View.INVISIBLE);
    }

    //---------------------------- Getting currently logged in user details ------------------------
    public void FetchUserDetails() {

        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

        //Query based on current user ID
        String UserID = fUser.getUid().toString();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        Query query = dbRef.child("Users").child(UserID).child("Account").orderByChild("userID").equalTo(UserID);

        //check if current user is logged in
        if (fUser != null) {
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            //Retrieving user details
                            User accUser = ds.getValue(User.class);
                            currentEmail = accUser.getEmail();
                            currentName = accUser.getFirstName() + " " + accUser.getLastName();
                            SetNavDrawerUserDetails();
                            Log.d("Email","accUser");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("error", error.getMessage());
                }
            });

        } else {
            //No user is logged in
            Toast.makeText(this,"No user is logged in.",Toast.LENGTH_SHORT).show();
        }

    }
    //----------------------------------------------------------------------------------------------

    //----------------------------- Setting the name nav drawer ------------------------------------
    private void SetNavDrawerUserDetails() {
        txtNavName.setText(currentName);
        txtNavEmail.setText(currentEmail);
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------- Drawer Management Code -----------------------------------
    public void ClickMenu(View view)
    {
        //Open the drawer
        openDrawer(mainDrawer);
    }
    public static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open the drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        //Close drawer layout
        //Check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            //When drawer is open
            //Close Drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    //----------------------------------------------------------------------------------------------

    //------------------------------------ Logout Dialogue -----------------------------------------
    private void LogoutDialogue()
    {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setCanceledOnTouchOutside(false); //To prevent a user from clicking away
        dialog.setContentView(R.layout.dialogue_logout);

        AppCompatButton btnLogout = dialog.findViewById(R.id.btnYes);
        AppCompatButton btnDontLogout = dialog.findViewById(R.id.btnNo);

        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //sign user out
                FirebaseAuth auth;
                auth = FirebaseAuth.getInstance();
                auth.signOut();

                //Load get started page
                startActivity(new Intent(getApplicationContext(), GetStarted.class));
                dialog.dismiss();
            }
        });

        btnDontLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    //----------------------------------------------------------------------------------------------

}