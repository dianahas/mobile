package com.example.diana.hotels.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dianahas on 11/30/2017.
 */

public class HotelsDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HotelsApp.db";


    private static final String SQL_CREATE_HOTELS_TABLE = "CREATE TABLE " + DatabaseCreator.Hotels.TABLE_NAME + "(" +
            DatabaseCreator.Hotels._ID + " INTEGER PRIMARY KEY," +
            DatabaseCreator.Hotels.COLUMN_HOTEL_NAME + " TEXT NOT NULL," +
            DatabaseCreator.Hotels.COLUMN_HOTEL_LOCATION + " TEXT NOT NULL)";

    public HotelsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(SQL_CREATE_HOTELS_TABLE);
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
