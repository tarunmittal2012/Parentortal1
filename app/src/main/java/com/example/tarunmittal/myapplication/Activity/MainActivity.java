package com.example.tarunmittal.myapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.tarunmittal.myapplication.Fragment.AnnouncementFragment;
import com.example.tarunmittal.myapplication.Fragment.AttendanceFragment;
import com.example.tarunmittal.myapplication.Fragment.HomeFragment;
import com.example.tarunmittal.myapplication.Fragment.MarksFragment;
import com.example.tarunmittal.myapplication.Fragment.MentorDetailFragment;
import com.example.tarunmittal.myapplication.Fragment.PerformanceFragment;
import com.example.tarunmittal.myapplication.Fragment.PlacementFragment;
import com.example.tarunmittal.myapplication.Fragment.StudentDetailFragment;
import com.example.tarunmittal.myapplication.Fragment.SuspensionFragment;
import com.example.tarunmittal.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import java.net.URL;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    View header;

    String str1;
    TextView txtname, txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        header = navigationView.getHeaderView(0);

        SharedPreferences preferences = getSharedPreferences("STUDENT", MODE_PRIVATE);
        str1 = preferences.getString("ID", null);
        String str2 = preferences.getString("NAME", null);
        txtname = header.findViewById(R.id.studentName);
        txtId = header.findViewById(R.id.student_id);
        txtId.setText(str1);
        txtname.setText(str2);

        HomeFragment a_fragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.main_container, a_fragment).commit();

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        if (id == R.id.attendance) {

            AttendanceFragment a_fragment = new AttendanceFragment();
            manager.beginTransaction().replace(R.id.main_container, a_fragment).addToBackStack(null).commit();

        }
        if (id == R.id.marks) {
            MarksFragment marksFragment = new MarksFragment();
            manager.beginTransaction().replace(R.id.main_container, marksFragment).addToBackStack(null).commit();
        }
        if (id == R.id.home) {
            HomeFragment homeFragment = new HomeFragment();
            manager.beginTransaction().replace(R.id.main_container, homeFragment).addToBackStack(null).commit();
        }
        if (id == R.id.performance) {
            PerformanceFragment pFragment = new PerformanceFragment();
            manager.beginTransaction().replace(R.id.main_container, pFragment).addToBackStack(null).commit();
        }

        if (id == R.id.show_detail) {
            StudentDetailFragment detailFragment = new StudentDetailFragment();
            manager.beginTransaction().replace(R.id.main_container, detailFragment).addToBackStack(null).commit();
        }

        if (id == R.id.mentor) {
            MentorDetailFragment fragment = new MentorDetailFragment();
            manager.beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();

        }
        if (id == R.id.placement) {
            PlacementFragment fragment = new PlacementFragment();
            manager.beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();

        }
        if(id==R.id.suspention){
            SuspensionFragment fragment=new SuspensionFragment();
            manager.beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();

        }
        if(id==R.id.announcement){
            AnnouncementFragment announcementFragment=new AnnouncementFragment();
            manager.beginTransaction().replace(R.id.main_container,announcementFragment).addToBackStack(null).commit();
        }
        if(id == R.id.logout){
            SharedPreferences preferences = getSharedPreferences("STUDENT",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            //finish();
            FirebaseAuth.getInstance().signOut();
            finish();

           startActivity(new Intent(this,LoginActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
