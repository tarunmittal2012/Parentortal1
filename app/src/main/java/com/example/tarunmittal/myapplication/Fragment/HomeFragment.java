package com.example.tarunmittal.myapplication.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunmittal.myapplication.DataClass.Attendance;
import com.example.tarunmittal.myapplication.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<Attendance> myAttendance;

    TextView percent;

    String studentId;

    LinearLayout attendanceLayout, performanceLayout, studentLayout, mentorFragment, marksLayout,
            announcementLayout, placementLayout, suspensionLayout;

    public HomeFragment() {

    }

    public void callParentMethod() {

        getActivity().onBackPressed();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        attendanceLayout = view.findViewById(R.id.rel1);
        performanceLayout = view.findViewById(R.id.rel6);
        marksLayout = view.findViewById(R.id.marks_layout);
        suspensionLayout = view.findViewById(R.id.suspension_layout);
        announcementLayout = view.findViewById(R.id.announcement_layout);

        studentLayout = view.findViewById(R.id.studentDetail_layout);
        mentorFragment = view.findViewById(R.id.mentorDetailLayout);
        placementLayout = view.findViewById(R.id.placement_layout);
        SharedPreferences preferences = this.getActivity().getSharedPreferences("STUDENT", MODE_PRIVATE);
        studentId = preferences.getString("ID", null);

        suspensionLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SuspensionFragment suspensionFragment = new SuspensionFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.main_container, suspensionFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        announcementLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AnnouncementFragment announcementFragment = new AnnouncementFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, announcementFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        placementLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                PlacementFragment placementFragment = new PlacementFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, placementFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        attendanceLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AttendanceFragment attendanceFragment = new AttendanceFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, attendanceFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });
        performanceLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Fragment newFragment = new PerformanceFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        marksLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Fragment newFragment = new MarksFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        studentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Fragment newFragment = new StudentDetailFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mentorFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Fragment newFragment = new MentorDetailFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onResume() {

        super.onResume();
        if (getView() == null) {
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    Toast.makeText(getActivity(), "Either Logout or stay here!!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }


}
