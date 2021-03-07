package com.example.androidcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateCreate extends AppCompatActivity {

    private TextView m_dateview, m_timeview;
    private EditText m_edit, m_important;
    private Calendar m_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_create);

        // Find ids
        m_dateview = (TextView) findViewById(R.id.add_dateview);
        m_timeview = (TextView) findViewById(R.id.add_timeview);
        m_edit = (EditText) findViewById(R.id.add_text);
        m_important = (EditText) findViewById(R.id.add_importanttext);

        // Set default date
        m_date = Calendar.getInstance();
        m_dateview.setText(java.text.DateFormat.getDateInstance().format(m_date.getTime()));
        m_timeview.setText(java.text.DateFormat.getTimeInstance().format(m_date.getTime()));
    }

    public void selectTime(View v) {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                m_date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                m_date.set(Calendar.MINUTE, minute);

                m_timeview.setText(java.text.DateFormat.getTimeInstance().format(m_date.getTime()));
            }
        }, m_date.get(Calendar.HOUR_OF_DAY), m_date.get(Calendar.MINUTE), true).show(); // Show to time pick dialog
    }

    public void selectDate(View v) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                m_date.set(Calendar.YEAR, year);
                m_date.set(Calendar.MONTH, month);
                m_date.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                m_dateview.setText(java.text.DateFormat.getDateInstance().format(m_date.getTime()));
            }

        }, m_date.get(Calendar.YEAR), m_date.get(Calendar.MONTH), m_date.get(Calendar.DAY_OF_MONTH)).show(); // Show to date pick dialog
    }

    public void done(View v) {
        Intent res = new Intent();

        res.putExtra("date", m_date);
        res.putExtra("text", m_edit.getText().toString());
        res.putExtra("important", Integer.parseInt(m_important.getText().toString()));

        setResult(Activity.RESULT_OK, res);
        finish();
    }
}