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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewDates#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewDates extends Fragment {

    private DateListAdapter m_da;
    private DAO m_dao;

    public NewDates() {
        // Required empty public constructor
    }

    public static NewDates newInstance() {
        NewDates fragment = new NewDates();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_dates, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        // Setup list
        RecyclerView rv = v.findViewById(R.id.new_list);
        m_dao = DB.getInstance().dao();
        m_da = new DateListAdapter();

        // Add click listener
        v.findViewById(R.id.newdate).setOnClickListener(v1 -> {
            startActivityForResult(new Intent(getActivity(), DateCreate.class), 0); // Start date creation
        });

        // Add vertical lines
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));

        // Add from db
        m_da.add(m_dao.all());

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

        rv.setAdapter(m_da);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext())); // Linear list
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

    public void addDate(String text, Calendar time) {
        Entry e = new Entry(text, time, 0);
        m_dao.insert(e);
        m_da.add(e);
    }

    public void removeDate(int i) {
        m_dao.delete(m_da.get(i));
        m_da.remove(i);
    }
}