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

import com.example.tarunmittal.myapplication.DataClass.Placement;
import com.example.tarunmittal.myapplication.R;

import java.util.List;
public class PlacementAdapter extends ArrayAdapter<Placement> {

    public PlacementAdapter(@NonNull Context context, List<Placement> placementList) {

        super(context, 0, placementList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_placement, parent, false);
        }
        Placement placement = getItem(position);
        TextView company_name, is_eligible, is_selected, date;
        company_name = listItemView.findViewById(R.id.company_name);
        is_eligible = listItemView.findViewById(R.id.is_eligible);
        is_selected = listItemView.findViewById(R.id.is_selected);
        date = listItemView.findViewById(R.id.date);
        String isEligibles=placement.getIsEligible();
        if (isEligibles.equals("No")) {
            is_eligible.setTextColor(Color.parseColor("#f44336"));
        } else if (isEligibles.equals("Yes")) {
            is_eligible.setTextColor(Color.parseColor("#4CAF50"));
        }
        company_name.setText(placement.getcName());
        is_eligible.setText(isEligibles);
        is_selected.setText(placement.getIsSelected());
        date.setText(placement.getDate());
        return listItemView;
    }
}
