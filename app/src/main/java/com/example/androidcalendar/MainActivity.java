package com.example.androidcalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout m_tablayout;
    private ViewPager2 m_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ids
        m_tablayout = (TabLayout) findViewById(R.id.tablayout);
        m_viewpager = (ViewPager2) findViewById(R.id.viewpager);

        // Setup the tab layout
        m_viewpager.setAdapter(new PagerAdaptor(this));
        new TabLayoutMediator(m_tablayout, m_viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("New");
                        break;
                    case 1:
                        tab.setText("Old");
                        break;
                }
            }
        }).attach();
    }
}