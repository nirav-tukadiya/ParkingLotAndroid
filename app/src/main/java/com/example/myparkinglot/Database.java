package com.example.myparkinglot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nirav Tukadiya on 19 Jun, 2019.
 */
public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "parking_database.db";
    private static final String TABLE_PARKING_LOTS = "parking_lots";
    private static final String KEY_ID = "id";
    private static final String KEY_LOT_NUMBER = "lot_number";
    private static final String KEY_VEHICLE_NUMBER = "vehicle_number";

    private Context mContext;

    private static final String CREATE_TABLE_SCHEMA = "CREATE TABLE " + TABLE_PARKING_LOTS + " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_LOT_NUMBER + " INTEGER, " + KEY_VEHICLE_NUMBER + " INTEGER UNIQUE)";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SCHEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mContext.deleteDatabase(DATABASE_NAME);
        onCreate(db);
    }

    public boolean ParkVehicle(Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOT_NUMBER, vehicle.getLot());
        values.put(KEY_VEHICLE_NUMBER, vehicle.getId());
        long id = db.insert(TABLE_PARKING_LOTS, null, values);
        db.close();
        return id != -1;
    }

    Cursor getParkedVehicle() {

        String selectQuery = "SELECT  " + KEY_VEHICLE_NUMBER + "," + KEY_LOT_NUMBER + " FROM " + TABLE_PARKING_LOTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    Cursor getParkedVehicle(int vehicleId) {
        String selectQuery = "SELECT " + KEY_VEHICLE_NUMBER + "," + KEY_LOT_NUMBER + " FROM " + TABLE_PARKING_LOTS + " WHERE " + KEY_VEHICLE_NUMBER + "=" + vehicleId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    Cursor getBookedLot(int lot) {
        String selectQuery = "SELECT " + KEY_LOT_NUMBER + " FROM " + TABLE_PARKING_LOTS + " WHERE " + KEY_LOT_NUMBER + "=" + lot;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    Cursor getAllBookedLots() {
        String selectQuery = "SELECT  " + KEY_LOT_NUMBER + " FROM " + TABLE_PARKING_LOTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }
}
