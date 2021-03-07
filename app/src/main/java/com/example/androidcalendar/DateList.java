package com.example.androidcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.LongBinaryOperator;

public class DateList extends Fragment {
    private DAO m_dao;
    private DateListAdapter m_da;

    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState, RecyclerView rv,
                              LongBinaryOperator comp, List<Entry> values) {
        super.onViewCreated(v, savedInstanceState);

        // Setup list
        m_dao = DB.getInstance().dao();
        m_da = new DateListAdapter(comp);

        // Add vertical lines
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));

        // Add from db
        m_da.add(values);

        rv.setAdapter(m_da);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext())); // Linear list
    }

    public void addDate(String text, Calendar time, int imp) {
        if (time.before(Calendar.getInstance())) { // Handle past problem
            Toast.makeText(getContext(), "Discarding because date is in the past.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (text.isEmpty()) { // Handle empty text problem
            Toast.makeText(getContext(), "Discarding because no text given.", Toast.LENGTH_SHORT).show();
            return;
        }

        Entry e = new Entry(text, time, imp);
        m_dao.insert(e);
        m_da.add(e);
    }

    public void removeDate(int i) {
        m_dao.delete(m_da.get(i));
        m_da.remove(i);
    }
}
