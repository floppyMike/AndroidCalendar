package com.example.androidcalendar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewDates#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewDates extends Fragment {

    private DateList m_list;

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
        m_list = new DateList(v.findViewById(R.id.new_list));

        // Add click listener
        v.findViewById(R.id.newdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), DateCreate.class);
                startActivityForResult(in, 0); // Start date creation
            }
        });

        return v;
    }
}