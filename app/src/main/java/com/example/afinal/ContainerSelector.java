package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContainerSelector extends AppCompatActivity {

    int REQUEST_CODE = 10;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_selector);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = findViewById(R.id.list);

        DatabaseHandler dbh = new DatabaseHandler(this);
        List<Container> containers = dbh.getAllContainers();

        ItemAdapter itemAdapter = new ItemAdapter(this, containers);
        list.setAdapter(itemAdapter);
        setListListener();
    }

    private void setListListener(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Container container = (Container)list.getAdapter().getItem(position);
                Intent i = new Intent(getApplicationContext(), com.example.afinal.IntakeAdder.class);
                i.putExtra("INDEX", container.getId());
                startActivity(i);
            }
        });
    }

    void manualIntakeAdder(View view) {
        Intent i = new Intent(this, com.example.afinal.ManualIntakeAdder.class);
        startActivityForResult(i, REQUEST_CODE);
    }

}
