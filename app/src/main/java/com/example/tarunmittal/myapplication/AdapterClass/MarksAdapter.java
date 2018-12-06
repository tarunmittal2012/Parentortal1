package com.example.tarunmittal.myapplication.AdapterClass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tarunmittal.myapplication.DataClass.Marks;
import com.example.tarunmittal.myapplication.R;

import java.util.List;
public class MarksAdapter extends ArrayAdapter<Marks> {

    public MarksAdapter(@NonNull Context context, List<Marks> marksList) {

        super(context, 0, marksList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_mark, parent, false);
        }
        Marks currentMarks = getItem(position);
        TextView mentor, date, number, test_type, subject, section, percentage;
        subject = listItemView.findViewById(R.id.sub_name);
        number = listItemView.findViewById(R.id.marks);
        date = listItemView.findViewById(R.id.date);
        test_type = listItemView.findViewById(R.id.test_type);
        mentor = listItemView.findViewById(R.id.mentor_id);
        section = listItemView.findViewById(R.id.section);
        percentage = listItemView.findViewById(R.id.percentage);
        String mrks = currentMarks.getMarks();
        int mr = Integer.parseInt(mrks);
        //   mr=mr*100;
        // int percent = (mr/30);
        int percent = mr*100;
        String type = currentMarks.getExam_type();
        Log.e("getView: ", mr + "\n" + type);

        if (type.equals("CA1") || type.equals("CA2") || type.equals("CA3")) {
             percent = percent/30;
            number.setText(currentMarks.getMarks() + "/" + "30");
            percentage.setText(percent+"%");
            mentor.setVisibility(View.VISIBLE);
         } else if (type.equals("ETE")) {
            percent  = (percent/70);
            percentage.setText(percent+"%");
            number.setText(currentMarks.getMarks() + "/" + "70");
            mentor.setVisibility(View.INVISIBLE);
        } else if (type.equals("MTE")){
             percent = percent/40;
            percentage.setText(percent+"%");
            number.setText(currentMarks.getMarks() + "/" + "40");
            mentor.setVisibility(View.INVISIBLE);
        }
  //      Log.e("getView: ", percent + "");
        subject.setText(currentMarks.getSubject());
    //   percentage.setText(percent + "%");
        date.setText(currentMarks.getDate());

        test_type.setText(type);
        mentor.setText("ID :" + currentMarks.getTreg_no());
        section.setText(currentMarks.getSection());
        return listItemView;
    }
}
