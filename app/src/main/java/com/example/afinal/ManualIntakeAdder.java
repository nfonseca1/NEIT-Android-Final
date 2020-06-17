package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class ManualIntakeAdder extends AppCompatActivity {

    int REQUEST_CODE = 10;

    EditText fluidIntake;
    EditText fluidUnit;
    EditText caffeineIntake;
    EditText caffeineUnit;

    RadioButton water;
    RadioButton coffee;
    RadioButton tea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_intake_adder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fluidIntake = findViewById(R.id.fluidIntake);
        caffeineIntake = findViewById(R.id.caffeineIntake);

        water = findViewById(R.id.water);
        coffee = findViewById(R.id.coffee);
        tea = findViewById(R.id.tea);
    }

    void addIntake(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);
        Goal goal = dbh.getGoal(1);
        Goal limit = dbh.getGoal(2);

        Goal newGoal = new Goal(1, goal.getGoal(), goal.getProgress() + Double.parseDouble(fluidIntake.getText().toString()), goal.getUnit());
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

        dbh.addHistory("Random cup", Double.parseDouble(fluidIntake.getText().toString()), bev, goal.getUnit());

        Intent i = new Intent(this, com.example.afinal.MainActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

}
