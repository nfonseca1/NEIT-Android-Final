package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContainerCreator extends AppCompatActivity {

    int REQUEST_CODE = 10;

    EditText name;
    EditText capacity;
    EditText unit;
    Button addBtn;
    Button deleteBtn;

    Container container;

    boolean addInsteadOfUpdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_creator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        name = findViewById(R.id.name);
        capacity = findViewById(R.id.capacity);
        unit = findViewById(R.id.unit);
        addBtn = findViewById(R.id.addBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        Intent intent = getIntent();
        int id = intent.getIntExtra("INDEX", -1);

        if (id != -1){
            DatabaseHandler dbh = new DatabaseHandler(this);
            container = dbh.getContainer(id);

            name.setText(container.getName());
            capacity.setText("" + container.getCapacity());
            unit.setText(container.getUnit());
            deleteBtn.setVisibility(View.VISIBLE);
            addInsteadOfUpdate = false;
        }
    }

    void addContainer(View view){
        if (addInsteadOfUpdate) {
            DatabaseHandler dbh = new DatabaseHandler(this);

            dbh.addContainer(name.getText().toString(), Double.parseDouble(capacity.getText().toString()), unit.getText().toString());

            Intent i = new Intent(this, com.example.afinal.MainActivity.class);
            startActivityForResult(i, REQUEST_CODE);
        }
        else {
            DatabaseHandler dbh = new DatabaseHandler(this);
            Container updatedContainer = new Container(container.getId(), name.getText().toString(),
                    Double.parseDouble(capacity.getText().toString()), unit.getText().toString());

            dbh.updateContainer(updatedContainer);

            Intent i = new Intent(this, com.example.afinal.MainActivity.class);
            startActivityForResult(i, REQUEST_CODE);
        }
    }

    void deleteContainer(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);

        dbh.deleteContainer(container.getId());

        Intent i = new Intent(this, com.example.afinal.MainActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

}
