package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int REQUEST_CODE = 10;

    TextView fluidGoal;
    TextView caffeineLimit;
    TextView fluidProgress;
    TextView caffeineProgress;
    SeekBar goalBar;
    SeekBar limitBar;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        fluidGoal = findViewById(R.id.fluidGoal);
        caffeineLimit = findViewById(R.id.caffeineLimit);
        fluidProgress = findViewById(R.id.fluidProgress);
        caffeineProgress = findViewById(R.id.caffeineProgress);
        goalBar = findViewById(R.id.goalBar);
        limitBar = findViewById(R.id.limitBar);

        DatabaseHandler dbh = new DatabaseHandler(this);

        dbh.addGoal(2, 0, "L");
        dbh.addGoal(200, 0, "mg");
        Goal goal = dbh.getGoal(1);
        Goal limit = dbh.getGoal(2);

        goalBar.setProgress((int)((goal.getProgress() / goal.getGoal()) * 100));
        limitBar.setProgress((int)((limit.getProgress() / limit.getGoal()) * 100));
        fluidGoal.setText("Fluid Goal: " + goal.getGoal() + " " + goal.getUnit());
        caffeineLimit.setText("Caffeine Limit: " + limit.getGoal() + " " + limit.getUnit());
        fluidProgress.setText(goal.getProgress() + " - " + goalBar.getProgress() + "%");
        caffeineProgress.setText(limit.getProgress() + " - " + limitBar.getProgress() + "%");


        list = findViewById(R.id.list);

        List<HistoryItem> historyItems = dbh.getAllHistory();

        HistoryAdapter itemAdapter = new HistoryAdapter(this, historyItems);
        list.setAdapter(itemAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_add) {
            Intent i = new Intent(this, com.example.afinal.ContainerSelector.class);
            startActivityForResult(i, REQUEST_CODE);
        }
        else if (id == R.id.action_manage_containers) {
            Intent i = new Intent(this, com.example.afinal.ContainerManager.class);
            startActivityForResult(i, REQUEST_CODE);
        }
        else if (id == R.id.action_set_goal) {
            Intent i = new Intent(this, com.example.afinal.GoalSetter.class);
            startActivityForResult(i, REQUEST_CODE);
        }

        return super.onOptionsItemSelected(item);
    }
}
