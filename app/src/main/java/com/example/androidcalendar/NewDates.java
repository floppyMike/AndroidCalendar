package com.example.androidcalendar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewDates#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewDates extends Fragment {

    private RecyclerView m_rv;
    private DateListAdapter m_da;

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
        View v = inflater.inflate(R.layout.fragment_new_dates, container, false);

        // Setup list
        m_rv = v.findViewById(R.id.new_list);

        // Add click listener
        v.findViewById(R.id.newdate).setOnClickListener(v1 -> {
            Intent in = new Intent(getActivity(), DateCreate.class);
            startActivityForResult(in, 0); // Start date creation
        });

        // Add vertical lines
        m_rv.addItemDecoration(new DividerItemDecoration(m_rv.getContext(), DividerItemDecoration.VERTICAL));

        // Create list adapter
        m_da = new DateListAdapter();

        // Test list
        Entry e1 = new Entry();
        e1.text = "Test1";
        e1.date = Calendar.getInstance();

        Entry e2 = new Entry();
        e2.text = "Test2";
        e2.date = Calendar.getInstance();

        m_da.add(new Entry[]{e1, e2});

        // Add swipe delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                m_da.remove(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(m_rv);

        m_rv.setAdapter(m_da);
        m_rv.setLayoutManager(new LinearLayoutManager(m_rv.getContext())); // Linear list

        return v;
    }
}