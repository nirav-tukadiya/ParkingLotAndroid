package com.example.myparkinglot;

import android.database.Cursor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nirav Tukadiya on 19 Jun, 2019.
 */
public class ParkingLot implements IParkingLot {

    private Database db = null;
    Map<Integer, Vehicle> parkedVehicle = new HashMap<>();

    public ParkingLot() {
    }

    public ParkingLot(Database db) {
        this.db = db;
    }

    @Override
    public boolean parkVehicle(Vehicle vehicle, Integer lot) {
        if (db != null) {
            return db.ParkVehicle(vehicle);
        }

        return false;
    }

    @Override
    public boolean isLotBooked(int id) {

        if (db == null) {
            //since we cannot check if the lot is taken or not, returning true and not allowing to park the vehicle at this moment.
            //Ideally this will never happen because parking lot must be provided Database object.
            return true;
        }

        boolean lotTaken = false;
        Cursor lCursor = db.getBookedLot(id);
        if (lCursor != null) {

            if (lCursor.moveToFirst()) {
                lotTaken = true;
            }

            if (!lCursor.isClosed()) {
                lCursor.close();
            }
        }

        return lotTaken;
    }

    @Override
    public boolean isVehicleExists(int vehicleId) {

        if (db == null) {
            //since we cannot check if the vehicle exists or not, returning true and not allowing to park the vehicle at this moment.
            //Ideally this will never happen because parking lot must be provided Database object.
            return true;
        }

        boolean vehicleParked = false;
        Cursor vCursor = db.getParkedVehicle(vehicleId);
        if (vCursor != null) {

            if (vCursor.moveToFirst()) {
                vehicleParked = true;
            }

            if (!vCursor.isClosed()) {
                vCursor.close();
            }
        }

        return vehicleParked;
    }
}
