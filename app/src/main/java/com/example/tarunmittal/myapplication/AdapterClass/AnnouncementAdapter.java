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

import com.example.tarunmittal.myapplication.DataClass.Announcement;
import com.example.tarunmittal.myapplication.R;

import java.util.List;
public class AnnouncementAdapter extends ArrayAdapter<Announcement> {

    public AnnouncementAdapter(@NonNull Context context, List<Announcement> announcementList) {

        super(context, 0, announcementList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_announcement, parent, false);
        }
        Announcement announcement = getItem(position);
        TextView type, message, date;
        type = listItemView.findViewById(R.id.type);
        String type1 = announcement.getType();
        if(type1.equals("Placement"))
        {
            type.setBackgroundColor(Color.parseColor("#009688"));
        }
        else if(type1.equals("Academic")){
            type.setBackgroundColor(Color.parseColor("#f44336"));

        }
        else if(type1.equals("Administrative")){
            type.setBackgroundColor(Color.parseColor("#A1887F"));
        }
        else if(type1.equals("Sports/Cultural"))
        {
            type.setBackgroundColor(Color.GREEN);
        }
        message = listItemView.findViewById(R.id.message);
        date = listItemView.findViewById(R.id.date);
        type.setText(type1);
        message.setText(announcement.getMessage());
        date.setText("("+announcement.getDate()+")");
        date.setTextColor(Color.BLACK);
        return listItemView;
    }
}
