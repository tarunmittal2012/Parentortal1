package com.example.tarunmittal.myapplication.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tarunmittal.myapplication.R;
public class AnnouncementActivity extends AppCompatActivity {

    TextView type, detail, date,upload;

    private String detail1, date1, type1,uplaod1;

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
        setContentView(R.layout.activity_announcement);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#009688"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Announcement");
        toolbar.setTitleTextColor(Color.WHITE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        type = findViewById(R.id.type);
        detail = findViewById(R.id.detail);
        date = findViewById(R.id.date);
        upload = findViewById(R.id.upload);
        Intent intent = getIntent();

        detail1 = intent.getStringExtra("detail");
        date1 = intent.getStringExtra("date");
        type1 = intent.getStringExtra("type");
        uplaod1 = intent.getStringExtra("upload");
        type.setText(type1);
        detail.setText(detail1);
        date.setText("("+date1+")");
        upload.setText(uplaod1);

    }
}
