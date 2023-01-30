package com.example.ict652;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
//import androidx.legacy.app.ActionBarDrawerToggle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    //private Button openAdd, logout;
  //  sharedPreferenceClass sharedPreferenceClass;
  //  private Toolbar toolbar;
  //  private DrawerLayout drawerLayout;
 //   private ActionBarDrawerToggle drawerToggle;
  //  private NavigationView navigationView;

    HomeFragment fragobj ;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
       // sharedPreferenceClass = new sharedPreferenceClass(this);
        MaterialToolbar toolbar2 = findViewById(R.id.topAppBar);
        NavigationView navigationView = findViewById(R.id.navigation_view);
      //  navigationView.setItemIconTintList(null);

        Intent intent = getIntent();
        token = intent.getStringExtra("token");

        Bundle bundle = new Bundle();
        bundle.putString("token", token);
// set Fragmentclass Arguments
        fragobj = new HomeFragment();
        fragobj.setArguments(bundle);

        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {
                    case R.id.nav_home:
                        replaceFragment(fragobj);
                        break;
                    case R.id.nav_userprofile:
                        replaceFragment(new UserProfileFragment());
                        break;
                    case R.id.nav_logout:
                        replaceFragment(new LogoutFragment());
                        break;
                }
                return true;
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }
}