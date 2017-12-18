package com.example.diana.hotels.db;

import android.provider.BaseColumns;

/**
 * Created by dianahas on 11/30/2017.
 */

public interface DatabaseCreator {
    /**
     * TABLE_NAME table extending BaseColumns that contains _ID column declaration
     */
    interface Hotels extends BaseColumns {
        String TABLE_NAME = "hotels";
        String COLUMN_HOTEL_NAME = "hotel_name";
        String COLUMN_HOTEL_LOCATION = "hotel_location";

        String DROP_TABLE = "DROP TABLE IF EXISTS '" + TABLE_NAME + "'";
    }
}
