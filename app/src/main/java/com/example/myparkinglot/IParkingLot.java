package com.example.myparkinglot;

/**
 * Created by Nirav Tukadiya on 19 Jun, 2019.
 */
public interface IParkingLot {
    boolean parkVehicle(Vehicle vehicle, Integer lot);

    boolean isLotBooked(int id);

    boolean isVehicleExists(int vehicleId);
}
