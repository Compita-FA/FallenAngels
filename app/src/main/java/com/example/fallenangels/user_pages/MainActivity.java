package com.example.fallenangels.user_pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fallenangels.R;
import com.example.fallenangels.others.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //Component variables
    private DrawerLayout mainDrawer;
    private BottomNavigationView bottomView;
    private TextView txtHeading;

    //Instances
    private HomeFragment homeFrag = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Finding ID's
        mainDrawer = findViewById(R.id.drawerLayout);
        bottomView = findViewById(R.id.bottomNavView);
        txtHeading = findViewById(R.id.txtPageName);

        //by default, load the home screen
        bottomView.setSelectedItemId(R.id.bttm_item_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,homeFrag).commit();
        SetTopBar("Home");

    }

    //Change appearance of top bar if any other page is open
    private void SetTopBar(String heading) {
        txtHeading.setText(heading);
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