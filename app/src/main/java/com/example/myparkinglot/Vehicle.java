package com.example.myparkinglot;

/**
 * Created by Nirav Tukadiya on 19 Jun, 2019.
 */
public class Vehicle {
    Integer id;
    Integer lot;

    public Vehicle(Integer id, Integer lot) {
        this.id = id;
        this.lot = lot;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLot() {
        return lot;
    }
}
