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
import com.example.tarunmittal.myapplication.DataClass.Placement;
import com.example.tarunmittal.myapplication.DataClass.Suspension;
import com.example.tarunmittal.myapplication.R;

import java.util.List;
public class SuspensionAdapter extends ArrayAdapter<Suspension> {

    public SuspensionAdapter(@NonNull Context context, List<Suspension> suspensionList) {

        super(context, 0, suspensionList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_suspension, parent, false);
        }
        Suspension suspension = getItem(position);
        TextView reason,  date,fine;
        reason = listItemView.findViewById(R.id.reason);
        date = listItemView.findViewById(R.id.date);
        fine = listItemView.findViewById(R.id.fine);
        fine.setText(suspension.getFine());
        reason.setText(suspension.getReason());
        date.setText(suspension.getDate());
        return listItemView;
    }
}
