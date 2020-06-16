package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class IntakeAdder extends AppCompatActivity {

    int REQUEST_CODE = 10;

    SeekBar slider;
    EditText fluidIntake;
    EditText fluidUnit;
    EditText caffeineIntake;
    EditText caffeineUnit;
    TextView amount;
    TextView max;

    RadioButton water;
    RadioButton coffee;
    RadioButton tea;

    Container container;

    Double intake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intake_adder);
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

        fluidIntake = findViewById(R.id.fluidIntake);
        caffeineIntake = findViewById(R.id.caffeineIntake);
        slider = findViewById(R.id.seekBar);
        amount = findViewById(R.id.amount);
        max = findViewById(R.id.max);

        water = findViewById(R.id.water);
        coffee = findViewById(R.id.coffee);
        tea = findViewById(R.id.tea);

        Intent intent = getIntent();
        int id = intent.getIntExtra("INDEX", 100);

        DatabaseHandler dbh = new DatabaseHandler(this);
        container = dbh.getContainer(id);

        max.setText("" + container.getCapacity() + container.getUnit());
        intake = (slider.getProgress() * container.getCapacity()) / 100;
        amount.setText("" + intake + container.getUnit());

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intake = (slider.getProgress() * container.getCapacity()) / 100;
                amount.setText("" + intake);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void addIntake(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);
        Goal goal = dbh.getGoal(1);
        Goal limit = dbh.getGoal(2);

        Goal newGoal = new Goal(1, goal.getGoal(), goal.getProgress() + intake, goal.getUnit());
        Goal newLimit = new Goal(2, limit.getGoal(), limit.getProgress() + Double.parseDouble(caffeineIntake.getText().toString()), limit.getUnit());

        dbh.updateProgress(newGoal);
        dbh.updateProgress(newLimit);

        String bev = "Water";
        if (coffee.isSelected()){
            bev = "Coffee";
        }
        else if (tea.isSelected()){
            bev = "tea";
        }
        dbh.addHistory(container.getName(), intake, bev, goal.getUnit());

        Intent i = new Intent(this, com.example.afinal.MainActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

}
