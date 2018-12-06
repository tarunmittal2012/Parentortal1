package com.example.tarunmittal.myapplication.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tarunmittal.myapplication.R;
public class PlacementActivity extends AppCompatActivity {

    TextView type, i, j, k, l, m, n, o;

    private String companyName, isEligible, isSelecetd, date, jobLocation, jobProfile, salary, about;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#009688"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Job Profile");
        toolbar.setTitleTextColor(Color.WHITE);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        type = findViewById(R.id.type);
        i = findViewById(R.id.type1);
        j = findViewById(R.id.type2);
        k = findViewById(R.id.type3);
        l = findViewById(R.id.type4);
        m = findViewById(R.id.type5);
        n = findViewById(R.id.type6);
        o = findViewById(R.id.type7);

        Intent intent = getIntent();

        companyName = intent.getStringExtra("cname");
        isEligible =intent.getStringExtra("isEligible");
        isSelecetd=intent.getStringExtra("isSelecetd");
        date= intent.getStringExtra("date");
        jobLocation=intent.getStringExtra("jobLocation");
        jobProfile = intent.getStringExtra("jobProfile");
        salary = intent.getStringExtra("salary");
        about=intent.getStringExtra("about");

        type.setText(Html.fromHtml("<u>"+companyName+"</u>"));
        i.setText(isEligible);
        if (isEligible.equals("No")) {
            i.setTextColor(Color.parseColor("#f44336"));
        } else if (isEligible.equals("Yes")) {
            i.setTextColor(Color.parseColor("#4CAF50"));
        }
        j.setText(isSelecetd);
        k.setText(date);
        l.setText(jobLocation);
        m.setText(jobProfile);
        n.setText(salary);
        o.setText(Html.fromHtml("<p>"+about+"</p>"));


    }
}
