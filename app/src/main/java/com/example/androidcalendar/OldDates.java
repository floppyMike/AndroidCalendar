package com.example.androidcalendar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

public class OldDates extends DateList {

    public OldDates() {
    }

    public static OldDates newInstance() {
        OldDates fragment = new OldDates();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_old_dates, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = v.findViewById(R.id.old_list);
        DAO dao = DB.getInstance().dao();
        super.onViewCreated(v, savedInstanceState, rv, (v1, v2) -> Long.compare(v2, v1), dao.allPast(Calendar.getInstance())); // Handle event fully
    }
}