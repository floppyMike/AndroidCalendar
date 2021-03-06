package com.example.androidcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class DateCreate extends AppCompatActivity {

    private TextView m_dateview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_create);

        // Find ids
        m_dateview = (TextView)findViewById(R.id.add_dateview);

        // Set default date
        m_dateview.setText(java.text.DateFormat.getDateInstance().format(new Date()));
    }
}