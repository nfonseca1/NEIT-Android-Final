package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<Container> containers;

    public ItemAdapter(Context c, List<Container> n){
        containers = n;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return containers.size();
    }

    @Override
    public Container getItem(int position) {
        return containers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.wordlist_item, null);
        final TextView name = v.findViewById(R.id.name);
        final TextView capacity = v.findViewById(R.id.capacity);
        //ImageView imageView = v.findViewById(R.id.preview);

        String title = containers.get(position).getName();
        double description = containers.get(position).getCapacity();

        name.setText(title);
        capacity.setMaxLines(1);
        capacity.setText("" + description + containers.get(position).getUnit());
        return v;
    }
}