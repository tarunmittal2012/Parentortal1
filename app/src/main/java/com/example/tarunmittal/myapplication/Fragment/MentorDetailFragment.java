package com.example.tarunmittal.myapplication.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tarunmittal.myapplication.R;

import static android.content.Context.MODE_PRIVATE;
public class MentorDetailFragment extends Fragment {

    TextView mentorName,mentorId;
    public MentorDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mentor_detail, container, false);
        mentorName=view.findViewById(R.id.faculty_name);
        mentorId=view.findViewById(R.id.faculty_uid);
        fillFacultyDetails();
        return view;
    }

    private void fillFacultyDetails() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("STUDENT", MODE_PRIVATE);

        String facultyName=preferences.getString("MENTOR",null);
        String facultyuid=preferences.getString("MENTORREG",null);

        mentorName.setText(facultyName);
        mentorId.setText(facultyuid);
    }

}
