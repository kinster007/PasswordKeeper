package com.alphacholera.passwordkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseManagement extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PasswordsDatabase";
    private static final String TABLE_NAME = "PasswordDatabase";
    private static final String COLUMN_0 = "timeOfCreation";
    private static final String COLUMN_1 = "websiteName";
    private static final String COLUMN_2 = "userName";
    private static final String COLUMN_3 = "password";
    private static final String[] COLUMNS = {COLUMN_0, COLUMN_1, COLUMN_2, COLUMN_3};

    DatabaseManagement(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + COLUMN_0 + " datetime default current_timestamp  primary key, " + COLUMN_1 + " varchar(100), " + COLUMN_2 + " varchar(100), " + COLUMN_3 + " varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Calendar.getInstance().getTime();
    }

    boolean addEntry(String websiteName, String userName, String password) {
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        cv.put("timeOfCreation", time);
        cv.put("websiteName", websiteName);
        cv.put("userName", userName);
        cv.put("password", password);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_NAME, null, cv) != -1;
    }

    ArrayList<EntryItem> getList() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<EntryItem> items = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            items.add(new EntryItem(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return items;
    }

    boolean updateEntry(String dateAndTime, String websiteName, String userName, String password) {
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        cv.put("timeOfCreation", time);
        cv.put("websiteName", websiteName);
        cv.put("userName", userName);
        cv.put("password", password);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(TABLE_NAME, cv, COLUMN_0 + " = '" + dateAndTime+"'", null) > 0;
    }

    boolean deleteEntry(String dateAndTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_0 + " = '" + dateAndTime + "'", null) > 0;
    }

    String[] getDetails(String dateAndTime) {
        String[] strings = new String[3];
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where " + COLUMN_0 + " = '"+dateAndTime + "'", null);
        if (cursor.moveToFirst()) {
            strings[0] = cursor.getString(1);
            strings[1] = cursor.getString(2);
            strings[2] = cursor.getString(3);
        }
        return strings;
    }
}
