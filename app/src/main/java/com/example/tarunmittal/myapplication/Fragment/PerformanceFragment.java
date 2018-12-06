package com.example.tarunmittal.myapplication.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarunmittal.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * A simple {@link Fragment} subclass.
 */
public class PerformanceFragment extends Fragment {

    HorizontalBarChart barChart;

    int subjectMarks[] = {52, 62, 75};

    String subjectName[] = {"CSE101", "INT101", "PEV101"};


    public PerformanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_performance, container, false);
        barChart = view.findViewById(R.id.barChartId);

        setData();

        return view;
    }

    public void setData() {
        ArrayList<BarEntry> yvalues = new ArrayList<>();

        for (int a = 0; a < subjectName.length; a++) {
            yvalues.add(new BarEntry(a, subjectMarks[a]));
        }

        BarDataSet dataSet = new BarDataSet(yvalues, "Marks");
        BarData data = new BarData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        barChart.setData(data);
        dataSet.setDrawValues(true);
        barChart.invalidate();
        final ArrayList<String> xVals = new ArrayList<>();
        xVals.addAll(Arrays.asList(subjectName));

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
        barChart.getAxisLeft().setAxisMaximum(100);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.animateXY(500, 500);
        barChart.setDrawValueAboveBar(false);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.DKGRAY);

        barChart.invalidate();
        barChart.setPinchZoom(false);
        barChart.setTouchEnabled(false);
        barChart.setScaleEnabled(false);
       }

}
