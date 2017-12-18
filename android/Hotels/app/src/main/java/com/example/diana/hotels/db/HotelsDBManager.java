package com.example.diana.hotels.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.diana.hotels.model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianahas on 11/30/2017.
 */

public class HotelsDBManager {
    private static HotelsDBManager dbManager;

    private HotelsDBHelper dbHelper;
    private SQLiteDatabase mDatabase;

    private HotelsDBManager(Context context) {
        dbHelper = new HotelsDBHelper(context);
    }

    public static HotelsDBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new HotelsDBManager(context);
        }
        return dbManager;
    }

    public void openDatabase() {
        if (mDatabase == null || !mDatabase.isOpen()) {
            // Open database for writing
            mDatabase = dbHelper.getWritableDatabase();
        }
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public void insertHotels(List<Hotel> hotels) {
        if (hotels != null && !hotels.isEmpty()) {

            // Assure database is open
            openDatabase();

            mDatabase.beginTransaction();
            try {
                for (int i = 0; i < hotels.size(); i++) {
                    // Create a ContentValues object for each Product to be inserted. Please note that ID shouldn't be added, because it is
                    // automatically added to inserted Product's row
                    ContentValues values = new ContentValues();
                    values.put(DatabaseCreator.Hotels.COLUMN_HOTEL_NAME, hotels.get(i).getName());
                    values.put(DatabaseCreator.Hotels.COLUMN_HOTEL_LOCATION, hotels.get(i).getLocation());

                    // Insert each Product
                    mDatabase.insert(DatabaseCreator.Hotels.TABLE_NAME, null, values);
                }
                mDatabase.setTransactionSuccessful();
            } finally {
                mDatabase.endTransaction();
            }
        }
    }

    public void insertHotel(Hotel hotel) {
        if (hotel != null) {
            openDatabase();

            mDatabase.beginTransaction();

            try {
                ContentValues values = new ContentValues();
                values.put(DatabaseCreator.Hotels.COLUMN_HOTEL_NAME, hotel.getName());
                values.put(DatabaseCreator.Hotels.COLUMN_HOTEL_LOCATION, hotel.getLocation());

                // Insert the hotel
                mDatabase.insert(DatabaseCreator.Hotels.TABLE_NAME, null, values);
                mDatabase.setTransactionSuccessful();
            } finally {
                mDatabase.endTransaction();
            }
        }
    }

    public void updateHotel(Hotel hotel) {
        if (hotel != null) {
            openDatabase();

            mDatabase.beginTransaction();

            try {
                ContentValues values = new ContentValues();
                values.put(DatabaseCreator.Hotels.COLUMN_HOTEL_NAME, hotel.getName());
                values.put(DatabaseCreator.Hotels.COLUMN_HOTEL_LOCATION, hotel.getLocation());

                // Insert the hotel
                mDatabase.update(DatabaseCreator.Hotels.TABLE_NAME, values, "_id = ?",
                        new String[]{String.valueOf(hotel.getId())});
                mDatabase.setTransactionSuccessful();
            } finally {
                mDatabase.endTransaction();
            }
        }

    }

    public void deleteHotel(Integer hotelId) {
        if (hotelId != null) {
            openDatabase();

            mDatabase.beginTransaction();

            try {
                mDatabase.delete(DatabaseCreator.Hotels.TABLE_NAME, "_id = ?",
                        new String[]{String.valueOf(hotelId)});
                mDatabase.setTransactionSuccessful();

            } finally {
                mDatabase.endTransaction();
            }
        }
    }

    /**
     * @param ascending If true order Hotels by ascending, else by descending order
     */
    public List<Hotel> getAllProducts(boolean ascending) {
        List<Hotel> hotels = new ArrayList<>();

        // Setup query arguments

        // Columns to return
        String[] projection = {DatabaseCreator.Hotels._ID, DatabaseCreator.Hotels.COLUMN_HOTEL_NAME, DatabaseCreator.Hotels.COLUMN_HOTEL_LOCATION};

        // ORDER BY clause
        String orderBy = DatabaseCreator.Hotels.COLUMN_HOTEL_NAME + (ascending ? " ASC" : " DESC");

        // Assure database is open
        openDatabase();

        // Execute query using arguments
        Cursor cursor = mDatabase.query(DatabaseCreator.Hotels.TABLE_NAME, projection, null, null, null, null, orderBy);

        // Get returned Product rows from cursor
        if (cursor != null) {
            // Get column's index from cursor associated with columns
            int columnIndexHotelId = cursor.getColumnIndex(DatabaseCreator.Hotels._ID);
            int columnIndexHotelName = cursor.getColumnIndex(DatabaseCreator.Hotels.COLUMN_HOTEL_NAME);
            int columnIndexHotelLocation = cursor.getColumnIndex(DatabaseCreator.Hotels.COLUMN_HOTEL_LOCATION);

            // While there are items in the cursor get the Product data row-by-row
            while (cursor.moveToNext()) {
                // Get row data using column index of cursor
                int id = cursor.getInt(columnIndexHotelId);
                String hotelName = cursor.getString(columnIndexHotelName);
                String hotelLocation = cursor.getString(columnIndexHotelLocation);

                // Create Product and add to list
                Hotel hotel = new Hotel(id, hotelName, hotelLocation);
                hotels.add(hotel);
            }

            cursor.close();
        }

        return hotels;
    }
}
