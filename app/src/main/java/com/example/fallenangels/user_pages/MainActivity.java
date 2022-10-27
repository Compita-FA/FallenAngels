package com.example.fallenangels.user_pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fallenangels.R;
import com.example.fallenangels.adoption.MainAdoptFragment;
import com.example.fallenangels.adoption.submissions.AdoptSubmissionForm;
import com.example.fallenangels.adoption.submissions.FosterSubmissionForm;
import com.example.fallenangels.others.AboutUsFragment;
import com.example.fallenangels.others.ContactFragment;
import com.example.fallenangels.others.EventsFragment;
import com.example.fallenangels.others.GalleryFragment;
import com.example.fallenangels.others.HomeFragment;
import com.example.fallenangels.others.MissionFragment;
import com.example.fallenangels.others.VolunteerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //Component variables
    private DrawerLayout mainDrawer;
    private BottomNavigationView bottomView;
    private NavigationView sideNavView;
    private TextView txtHeading;

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
    private AdoptSubmissionForm adoptFormFrag = new AdoptSubmissionForm();
    private FosterSubmissionForm fosterFormFrag = new FosterSubmissionForm();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Finding ID's
        mainDrawer = findViewById(R.id.drawerLayout);
        bottomView = findViewById(R.id.bottomNavView);
        sideNavView = findViewById(R.id.mainNavView);
        txtHeading = findViewById(R.id.txtPageName);

        //by default, load the home screen
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, homeFrag).commit();
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
                        return true;
                    case R.id.item_gallery:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, gallFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Gallery");
                        return true;
                    case R.id.item_events:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, eventsFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Events");
                        return true;
                    case R.id.item_contact:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, conFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Contact");
                        return true;
                    case R.id.item_aboutus:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, aboutFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("About Us");
                        return true;
                    case R.id.item_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, accFrag).commit();
                        closeDrawer(mainDrawer);
                        SetTopBar("Account");
                        return true;
                    case R.id.item_logout:
                        //TODO: logout code
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


    public void ShowAdoptForm(Dialog dialog) {
        dialog.dismiss();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, adoptFormFrag).commit();
    }

    public void ShowFosterForm(Dialog dialog) {
        dialog.dismiss();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout, fosterFormFrag).commit();
    }

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

}