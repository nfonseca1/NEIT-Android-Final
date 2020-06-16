package com.example.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<HistoryItem> items;

    public HistoryAdapter(Context c, List<HistoryItem> n){
        items = n;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public HistoryItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.history_item, null);
        final TextView timeSlot = v.findViewById(R.id.time);
        final TextView fluidSlot = v.findViewById(R.id.fluid);
        final TextView containerType = v.findViewById(R.id.containerType);
        //ImageView imageView = v.findViewById(R.id.preview);

        String time = items.get(position).getTime();
        double fluid = items.get(position).getFluid();
        String unit = items.get(position).getUnit();
        String beverage = items.get(position).getBeverage();
        String name = items.get(position).getName();

        timeSlot.setText(time);
        fluidSlot.setText(fluid + unit + " " + beverage);
        containerType.setText(" in " + name);

        return v;
    }
}
