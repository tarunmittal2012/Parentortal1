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
public class StudentDetailFragment extends Fragment {

    TextView name, fname, regno, dob, email, section, stream, fmobile, smobile, state, address;

    public StudentDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_detail, container, false);
        //  findViewById();
        name = view.findViewById(R.id.student_name);
        fname = view.findViewById(R.id.father_name);
        regno = view.findViewById(R.id.registration_number);
        dob = view.findViewById(R.id.dob);
        email = view.findViewById(R.id.email);
        section = view.findViewById(R.id.section);
        stream = view.findViewById(R.id.stream);
        smobile=view.findViewById(R.id.student_number);
        fmobile = view.findViewById(R.id.father_number);
        state = view.findViewById(R.id.state);
        address = view.findViewById(R.id.address);

        fillDetails();
        return view;
    }

    private void fillDetails() {

        SharedPreferences preferences = this.getActivity().getSharedPreferences("STUDENT", MODE_PRIVATE);
        String stId = preferences.getString("ID", null);
        String stName = preferences.getString("NAME", null);
        String stSection = preferences.getString("SECTION", null);
        String stFatherName = preferences.getString("FNAME", null);
        String stEmail = preferences.getString("EMAIL", null);
        String stStream = preferences.getString("STREAM", null);
        String stState = preferences.getString("STATE", null);
        String stDob = preferences.getString("DOB", null);
        String stAddress = preferences.getString("ADDRESS", null);
        String stMobile = preferences.getString("MOBILE", null);
        String stFmobile = preferences.getString("FMOBILE", null);

        name.setText(stName);
        fname.setText(stFatherName);
        regno.setText(stId);
        dob.setText(stDob);
        email.setText(stEmail);
        section.setText(stSection);
        stream.setText(stStream);
        fmobile.setText(stFmobile);
        smobile.setText(stMobile);
        state.setText(stState);
        address.setText(stAddress);
    }

    /*private void findViewById() {

        name = getView().findViewById(R.id.student_name);
        fname = getView().findViewById(R.id.father_name);
        regno = getView().findViewById(R.id.registration_number);
        dob = getView().findViewById(R.id.dob);
        email = getView().findViewById(R.id.email);
        section = getView().findViewById(R.id.section);
        stream = getView().findViewById(R.id.stream);
        fmobile = getView().findViewById(R.id.father_number);
        smobile = getView().findViewById(R.id.student_number);
        state = getView().findViewById(R.id.state);
        address = getView().findViewById(R.id.address);
    }
*/
}
