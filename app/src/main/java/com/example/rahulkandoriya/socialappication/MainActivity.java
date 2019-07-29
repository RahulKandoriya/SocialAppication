package com.example.rahulkandoriya.socialappication;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import com.example.rahulkandoriya.socialappication.fragments.HomeFragment;
import com.example.rahulkandoriya.socialappication.fragments.NotificationFragment;
import com.example.rahulkandoriya.socialappication.fragments.ProfileFragment;
import com.example.rahulkandoriya.socialappication.fragments.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    final Fragment homeFragment = new HomeFragment();
    final Fragment searchFargment = new SearchFragment();
    final Fragment notificationFragment = new NotificationFragment();
    final Fragment profileFragment = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();


    Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.title_home);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit();
        fm.beginTransaction().add(R.id.main_container, searchFargment, "2").hide(searchFargment).commit();
        fm.beginTransaction().add(R.id.main_container, notificationFragment, "4").hide(notificationFragment).commit();
        fm.beginTransaction().add(R.id.main_container, profileFragment, "5").hide(profileFragment).commit();

    }





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    setTitle(R.string.title_home);
                    return true;
                case R.id.navigation_search:
                    fm.beginTransaction().hide(active).show(searchFargment).commit();
                    active = searchFargment;
                    setTitle(R.string.title_search);
                    return true;
                case R.id.navigation_edit:
                    startActivity(new Intent(MainActivity.this, NewPostActivity.class));
                    return true;
                case R.id.navigation_notifications:
                    fm.beginTransaction().hide(active).show(notificationFragment).commit();
                    active = notificationFragment;
                    setTitle("Notifications");
                    return true;
                case R.id.navigation_profile:
                    fm.beginTransaction().hide(active).show(profileFragment).commit();
                    active = profileFragment;
                    setTitle("Your Recent Posts");
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();

        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }



}
