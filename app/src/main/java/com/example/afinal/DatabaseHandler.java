package com.example.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;


public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "final10";

    // Contacts table name
    private static final String TABLE_GOALS = "goals";
    private static final String TABLE_CONTAINERS = "containers";
    private static final String TABLE_HISTORY = "history";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_GOAL = "goal";
    private static final String KEY_PROGRESS = "progress";
    private static final String KEY_UNITS = "units";

    private static final String KEY_NAME = "name";
    private static final String KEY_CAPACITY = "capacity";

    private static final String KEY_TIME = "time";
    private static final String KEY_BEVERAGE = "beverage";
    private static final String KEY_FLUID = "fluid";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GOALS_TABLE = "CREATE TABLE " + TABLE_GOALS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_GOAL + " REAL,"
                + KEY_PROGRESS + " REAL," + KEY_UNITS + " TEXT)";
        db.execSQL(CREATE_GOALS_TABLE);

        String CREATE_CONTAINERS_TABLE = "CREATE TABLE " + TABLE_CONTAINERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CAPACITY + " REAL," + KEY_UNITS + " TEXT)";
        db.execSQL(CREATE_CONTAINERS_TABLE);

        String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_HISTORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TIME + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_BEVERAGE + " TEXT," + KEY_FLUID + " REAL," + KEY_UNITS + " TEXT)";
        db.execSQL(CREATE_HISTORY_TABLE);

        //addGoal(2, 0, "L");
        //addGoal(200, 0, "mg");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTAINERS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new account
    void addGoal(double goal, double progress, String unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GOAL, goal);
        values.put(KEY_PROGRESS, progress);
        values.put(KEY_UNITS, unit);

        // Inserting Row
        db.insert(TABLE_GOALS, null, values);
        db.close(); // Closing database connection
    }

    void addContainer(String name, double capacity, String unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_CAPACITY, capacity);
        values.put(KEY_UNITS, unit);

        // Inserting Row
        db.insert(TABLE_CONTAINERS, null, values);
        db.close(); // Closing database connection
    }

    void addHistory(String name, double amount, String beverage, String unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        Date currentTime = Calendar.getInstance().getTime();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_FLUID, amount);
        values.put(KEY_BEVERAGE, beverage);
        values.put(KEY_UNITS, unit);
        values.put(KEY_TIME, currentTime.toString());

        // Inserting Row
        db.insert(TABLE_HISTORY, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Goal getGoal(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GOALS, new String[] { KEY_ID,
                        KEY_GOAL, KEY_PROGRESS, KEY_UNITS}, KEY_ID + "=?",
                        new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Goal goal = new Goal(Integer.parseInt(cursor.getString(0)), Double.parseDouble(cursor.getString(1)),
                Double.parseDouble(cursor.getString(2)), cursor.getString(3));
        // return order
        return goal;
    }


    Container getContainer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTAINERS, new String[] { KEY_ID,
                        KEY_NAME, KEY_CAPACITY, KEY_UNITS}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Container container = new Container(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Double.parseDouble(cursor.getString(2)),
                cursor.getString(3));
        // return order
        return container;
    }

    // Getting All Orders
    public List<Container> getAllContainers() {
        List<Container> containerList = new ArrayList<Container>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTAINERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Container container = new Container();
                container.setId(Integer.parseInt(cursor.getString(0)));
                container.setName(cursor.getString(1));
                container.setCapacity(Double.parseDouble(cursor.getString(2)));
                container.setUnit(cursor.getString(3));
                // Adding order to list
                containerList.add(container);
            } while (cursor.moveToNext());
        }

        // return order list
        return containerList;
    }

    public List<HistoryItem> getAllHistory() {
        List<HistoryItem> historyList = new ArrayList<HistoryItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HistoryItem historyItem = new HistoryItem();
                historyItem.setId(Integer.parseInt(cursor.getString(0)));
                historyItem.setTime(cursor.getString(1));
                historyItem.setName(cursor.getString(2));
                historyItem.setBeverage(cursor.getString(3));
                historyItem.setFluid(Double.parseDouble(cursor.getString(4)));
                historyItem.setUnit(cursor.getString(5));
                // Adding order to list
                historyList.add(historyItem);
            } while (cursor.moveToNext());
        }

        // return order list
        return historyList;
    }

    // Updating single order
    public int updateProgress(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GOAL, goal.getGoal());
        values.put(KEY_PROGRESS, goal.getProgress());
        values.put(KEY_UNITS, goal.getUnit());

        // updating row
        return db.update(TABLE_GOALS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(goal.getId()) });
    }

    public int updateContainer(Container container) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, container.getName());
        values.put(KEY_CAPACITY, container.getCapacity());
        values.put(KEY_UNITS, container.getUnit());

        // updating row
        return db.update(TABLE_CONTAINERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(container.getId()) });
    }

    // Deleting single order
    public void deleteGoal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GOALS, KEY_ID + " = ?",
                new String[] { String.valueOf(goal.getId()) });
        db.close();
    }

    public void deleteContainer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTAINERS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

}
