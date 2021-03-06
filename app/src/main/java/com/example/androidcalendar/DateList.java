package com.example.androidcalendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateList {
    private RecyclerView m_rv;
    private DateListAdapter m_da;

    DateList(RecyclerView rv) {
        // Add ids
        m_rv = rv;

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
    }
}
