package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import org.w3c.dom.Text;

public class GoalSetter extends AppCompatActivity {

    int REQUEST_CODE = 10;

    EditText goalText;
    EditText goalUnitText;
    EditText limitText;
    EditText limitUnitText;

    SeekBar fluidBar;
    SeekBar caffeineBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setter);
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

        goalText = findViewById(R.id.goal);
        goalUnitText = findViewById(R.id.goalUnit);
        limitText = findViewById(R.id.limit);
        limitUnitText = findViewById(R.id.limitUnit);
        fluidBar = findViewById(R.id.seekBarFluid);
        caffeineBar = findViewById(R.id.seekBarCaffeine);

        DatabaseHandler dbh = new DatabaseHandler(this);
        Goal goal = dbh.getGoal(1);
        Goal limit = dbh.getGoal(2);

        goalText.setText("" + goal.getGoal());
        limitText.setText("" + limit.getGoal());

        fluidBar.setProgress((int)((goal.getProgress() / goal.getGoal()) * 100));
        caffeineBar.setProgress((int)((limit.getProgress() / limit.getGoal()) * 100));
    }

    void setGoals(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);
        Goal goal = dbh.getGoal(1);
        Goal limit = dbh.getGoal(2);

        Goal newGoal = new Goal(1, Double.parseDouble(goalText.getText().toString()), goal.progress, goalUnitText.getText().toString());
        Goal newLimit = new Goal(2, Double.parseDouble(limitText.getText().toString()), limit.progress, limitUnitText.getText().toString());

        dbh.updateProgress(newGoal);
        dbh.updateProgress(newLimit);

        Intent i = new Intent(this, com.example.afinal.MainActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    void clearProgress(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);
        Goal goal = dbh.getGoal(1);
        Goal limit = dbh.getGoal(2);

        Goal newGoal = new Goal(1, goal.getGoal(), 0, goal.getUnit());
        Goal newLimit = new Goal(2, limit.getGoal(), 0, limit.getUnit());

        dbh.updateProgress(newGoal);
        dbh.updateProgress(newLimit);

        Intent i = new Intent(this, com.example.afinal.MainActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

}
