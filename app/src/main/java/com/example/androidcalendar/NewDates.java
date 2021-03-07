package com.example.androidcalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NewDates extends DateList {

    public NewDates() {
    }

    public static NewDates newInstance() {
        NewDates fragment = new NewDates();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_dates, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = v.findViewById(R.id.new_list);
        DAO dao = DB.getInstance().dao();

        super.onViewCreated(v, savedInstanceState, rv, (v1, v2) -> Long.compare(v1, v2), dao.allFuture(Calendar.getInstance())); // Handle event fully

        // Add click listener
        rv.setOnClickListener(v1 -> {
            startActivityForResult(new Intent(getActivity(), DateCreate.class), 0); // Start date creation
        });

        // Add swipe delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeDate(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(rv);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 0:
                    addDate(data.getStringExtra("text"), (Calendar) data.getSerializableExtra("date"));
                    break;
            }
    }
}