package com.example.androidcalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import java.util.List;
import java.util.function.LongBinaryOperator;

public class DateListAdapter extends RecyclerView.Adapter<DateListAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;

        public ViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.entry_header);
            date = (TextView) v.findViewById(R.id.entry_date);
        }
    }

    private final SortedList<Entry> m_entries;

    public DateListAdapter(LongBinaryOperator op) {
        m_entries = new SortedList<>(Entry.class, new SortedList.Callback<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return (int) op.applyAsLong(o1.date.getTime().getTime(), o2.date.getTime().getTime());
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Entry oldItem, Entry newItem) {
                return oldItem.text.equals(newItem.text);
            }

            @Override
            public boolean areItemsTheSame(Entry item1, Entry item2) {
                return item1.id == item2.id;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        }); // Create sorter
    }

    public void add(List<Entry> l) {
        m_entries.beginBatchedUpdates();
        for (Entry e : l) {
            m_entries.add(e);
        }
        m_entries.endBatchedUpdates();
    }

    public void add(Entry l) {
        m_entries.add(l);
    }

    public Entry get(int i) {
        return m_entries.get(i);
    }

    public void remove(int i) {
        m_entries.removeItemAt(i);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_entry, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entry e = m_entries.get(position);

        holder.title.setText(e.text);
        holder.date.setText(java.text.DateFormat.getDateInstance().format(e.date.getTime()) + " " + java.text.DateFormat.getTimeInstance().format(e.date.getTime()));
    }

    @Override
    public int getItemCount() {
        return m_entries.size();
    }
}
