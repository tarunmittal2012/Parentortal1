package com.example.tarunmittal.myapplication.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunmittal.myapplication.AdapterClass.AttendanceAdapter;
import com.example.tarunmittal.myapplication.DataClass.Attendance;
import com.example.tarunmittal.myapplication.Networking.NetworkUtils;
import com.example.tarunmittal.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;
/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {

    private static final String TAG = "AttendenceFragment";

    Button sub1_button, sub2_button, sub3_button;
    String numPercent;

    TextView no_internet;
    LinearLayout calculateLayout, buttonLayout;

    int percent;

    TextView present_lac, absent_lac, total_lac, percent_lac;

    String attendencejson;

    URL attendenceurl;

    ListView attendanceList;

    ArrayList<Attendance> mAttendanceArrayList = new ArrayList<>();

    public AttendanceFragment() {

    }

    private static ArrayList<Attendance> subjectAttendenceJsonParsing(String str) throws JSONException {

        ArrayList<Attendance> attendanceArrayList = new ArrayList<>();
        final String REG_NO = "reg_no";
        final String STUDENTNAME = "sname";
        final String SECTION = "section";
        final String MENTOR_REG = "mentor_reg";
        final String SUBJECT = "subject";
        final String ATTENDENCE = "att";
        final String DATE = "date";
        final String RESULT = "result";
        final String RES = "res";

        JSONObject studentJson = new JSONObject(str);
        JSONArray studentatt = studentJson.getJSONArray(RESULT);

        Log.i(TAG, "Size of studentatt : - > " + studentatt.length() + "");

        for (int i = 0; i < 1; i++) {

            JSONObject object = studentatt.getJSONObject(i);
            JSONArray attarray = object.getJSONArray(RES);
            Log.i(TAG, "Size of attarray " + attarray.length());
            Log.i(TAG, "Before Inner If ");

            for (int j = 0; j < attarray.length(); j++) {
                Log.i(TAG, "inside res array");
                JSONObject attObj = attarray.getJSONObject(j);
                String reg_no, sname, attenden, dat, subject, mn_reg, section;
                reg_no = attObj.getString(REG_NO);
                sname = attObj.getString(STUDENTNAME);
                attenden = attObj.getString(ATTENDENCE);
                dat = attObj.getString(DATE);
                subject = attObj.getString(SUBJECT);
                mn_reg = attObj.getString(MENTOR_REG);
                section = attObj.getString(SECTION);
                Log.e(TAG, reg_no + "\n" + sname + "\n" + attenden + "\n" + dat + "\n" + subject + "\n" + section + "\n" + mn_reg);
                Attendance attendance = new Attendance(sname, dat, subject, attenden, section, reg_no, mn_reg);
                attendanceArrayList.add(attendance);

            }
            Log.i(TAG, "After Innner If");
        }

        Log.e(TAG, "Size of arraylist = " + attendanceArrayList.size());

        return attendanceArrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        attendanceList = view.findViewById(R.id.attendance_list);
        calculateLayout = view.findViewById(R.id.calculate_layout);
        buttonLayout = view.findViewById(R.id.button_layout);
        no_internet = view.findViewById(R.id.no_internet);
        sub1_button = view.findViewById(R.id.sub1_button);
        sub2_button = view.findViewById(R.id.sub2_button);
        sub3_button = view.findViewById(R.id.sub3_button);
        absent_lac = view.findViewById(R.id.absent_lac);
        total_lac = view.findViewById(R.id.total_lac);
        present_lac = view.findViewById(R.id.present_lac);
        percent_lac = view.findViewById(R.id.total_percent);
        buttonLayout = view.findViewById(R.id.button_layout);
        getAttendeceURL();
        sub1_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String sub = sub1_button.getText().toString();
                int presentCount = 0, totalCount = 0, absentCount = 0, percentsub1 = 0;

                ArrayList<Attendance> attendanceArrayList = new ArrayList<>();
                for (int i = 0; i < mAttendanceArrayList.size(); i++) {
                    Attendance attendance = mAttendanceArrayList.get(i);
                    if (sub.equals(attendance.getSubject())) {
                        attendanceArrayList.add(attendance);

                        if ("P".equals(mAttendanceArrayList.get(i).getAtt())) {
                            presentCount++;
                        }
                        if ("A".equals(mAttendanceArrayList.get(i).getAtt())) {
                            absentCount++;
                        }
                        totalCount++;
                        percentsub1 = (presentCount * 100) / totalCount;
                    }
                    present_lac.setText(presentCount + "");
                    absent_lac.setText(absentCount + "");
                    total_lac.setText(totalCount + "");
                    percent_lac.setText(percentsub1 + "%");
                    attendanceList.setAdapter(new AttendanceAdapter(getContext(), attendanceArrayList));
                }
            }
        });

        sub2_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String sub = sub2_button.getText().toString();
                int presentCount = 0, totalCount = 0, absentCount = 0, percentSub2 = 0;

                ArrayList<Attendance> attendanceArrayList = new ArrayList<>();
                for (int i = 0; i < mAttendanceArrayList.size(); i++) {
                    Attendance attendance = mAttendanceArrayList.get(i);
                    if (sub.equals(attendance.getSubject())) {
                        attendanceArrayList.add(attendance);
                        if ("P".equals(mAttendanceArrayList.get(i).getAtt())) {
                            presentCount++;
                        }
                        if ("A".equals(mAttendanceArrayList.get(i).getAtt())) {
                            absentCount++;
                        }
                        totalCount++;
                        percentSub2 = (presentCount * 100) / totalCount;
                    }
                    present_lac.setText(presentCount + "");
                    absent_lac.setText(absentCount + "");
                    total_lac.setText(totalCount + "");
                    percent_lac.setText(percentSub2 + "%");
                    attendanceList.setAdapter(new AttendanceAdapter(getContext(), attendanceArrayList));
                }
            }
        });

        sub3_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String sub = sub3_button.getText().toString();
                int presentCount = 0, totalCount = 0, absentCount = 0, percentSub3 = 0;
                ArrayList<Attendance> attendanceArrayList = new ArrayList<>();
                for (int i = 0; i < mAttendanceArrayList.size(); i++) {
                    Attendance attendance = mAttendanceArrayList.get(i);
                    if (sub.equals(attendance.getSubject())) {
                        attendanceArrayList.add(attendance);
                        if ("P".equals(mAttendanceArrayList.get(i).getAtt())) {
                            presentCount++;
                        }
                        if ("A".equals(mAttendanceArrayList.get(i).getAtt())) {
                            absentCount++;
                        }
                        totalCount++;
                        percentSub3 = (presentCount * 100) / totalCount;
                    }
                    present_lac.setText(presentCount + "");
                    absent_lac.setText(absentCount + "");
                    total_lac.setText(totalCount + "");
                    percent_lac.setText(percentSub3 + "%");
                    attendanceList.setAdapter(new AttendanceAdapter(getContext(), attendanceArrayList));
                }
            }
        });

        return view;
    }

    private void getAttendeceURL() {

        final ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online
            SharedPreferences preferences = getActivity().getSharedPreferences("STUDENT", MODE_PRIVATE);
            String username = preferences.getString("ID", null);
            String attString = "http://parentsportal.000webhostapp.com/mobAttendance.php?Username=" + username;

            try {
                attendenceurl = new URL(attString);
                Log.e("AtttendenceUrl", attendenceurl.toString());
                AttendenceTask attendenceTask = new AttendenceTask(getActivity());
                mAttendanceArrayList = attendenceTask.execute().get();
                int total = 0, present = 0, absent = 0, totalPercent = 0;
                for (int i = 0; i < mAttendanceArrayList.size(); i++) {
                    if ("P".equals(mAttendanceArrayList.get(i).getAtt())) {
                        present++;
                    }
                    if ("A".equals(mAttendanceArrayList.get(i).getAtt())) {
                        absent++;
                    }
                    total++;
                    totalPercent = (present * 100) / total;
                     numPercent =String.valueOf(totalPercent);
                    //          Log.i("getAttendeceURL: ", totalPercent + "");
                }

                total_lac.setText(total + "");
                absent_lac.setText(absent + "");
                present_lac.setText(present + "");
                percent_lac.setText(totalPercent + "%");

                attendanceList.setAdapter(new AttendanceAdapter(getActivity(), mAttendanceArrayList));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            // notify user you are not online
            calculateLayout.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.GONE);
            no_internet.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    private class AttendenceTask extends AsyncTask<Void, String, ArrayList<Attendance>> {

        Context mContext;

        ProgressDialog attendanceDialog;

        AttendenceTask(Context context) {

            this.mContext = context;
        }

        @Override
        protected void onPostExecute(ArrayList<Attendance> attendances) {

            super.onPostExecute(attendances);
            attendanceDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {

            attendanceDialog = new ProgressDialog(mContext);
            attendanceDialog.setMessage("Please Wait!");
            attendanceDialog.setTitle("Loading!");
            attendanceDialog.show();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Attendance> doInBackground(Void... voids) {

            ArrayList<Attendance> att = null;
            try {
                attendencejson = NetworkUtils.httpConnection(attendenceurl);
                att = subjectAttendenceJsonParsing(attendencejson);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return att;
        }
    }

}

