package com.example.tarunmittal.myapplication.AdapterClass;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tarunmittal.myapplication.DataClass.Attendance;
import com.example.tarunmittal.myapplication.R;

import java.util.List;
public class AttendanceAdapter extends ArrayAdapter<Attendance> {

    public AttendanceAdapter(@NonNull Context context, List<Attendance> attendanceList) {

        super(context, 0, attendanceList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_attendance, parent, false);
        }
        Attendance currentAttendance = getItem(position);
        TextView subject, mentor_reg, date, status;
        subject = listItemView.findViewById(R.id.sub_name);
        mentor_reg = listItemView.findViewById(R.id.mentor_id);
        date = listItemView.findViewById(R.id.date);
        status = listItemView.findViewById(R.id.status);
        String att = currentAttendance.getAtt();
        if (att.equals("A")) {
            status.setTextColor(Color.parseColor("#f44336"));
        } else if(att.equals("P")){
            status.setTextColor(Color.parseColor("#4CAF50"));
        }
        subject.setText(currentAttendance.getSubject());
        mentor_reg.setText(currentAttendance.getMentorId());
        date.setText(currentAttendance.getDate());
        status.setText(att);
        return listItemView;
    }
}