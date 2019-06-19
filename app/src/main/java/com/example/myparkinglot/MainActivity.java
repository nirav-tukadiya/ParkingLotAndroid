package com.example.myparkinglot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText vehicle_lot_number_field;
    EditText vehicle_number_field;
    Button btn_park_now;

    Database db = new Database(this);
    ParkingLot pl = new ParkingLot(db);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicle_lot_number_field = findViewById(R.id.lot_number_field);
        vehicle_number_field = findViewById(R.id.vehicle_number_field);
        btn_park_now = findViewById(R.id.Parknow_btn);

        btn_park_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkVehicleNow();
            }
        });
    }

    private void ParkVehicleNow() {
        String vehicle_id_field_txt = vehicle_number_field.getText().toString().trim();
        String vehicle_lot_number_field_txt = vehicle_lot_number_field.getText().toString().trim();

        if (areInputsValid(vehicle_id_field_txt, vehicle_lot_number_field_txt)) {

            int id = Integer.parseInt(vehicle_id_field_txt);
            int lot = Integer.parseInt(vehicle_lot_number_field_txt);

            park(id, lot);
        }
    }

    private void park(int vehicle_id_field_txt, int vehicle_lot_number_field_txt) {

        Vehicle vehicle = new Vehicle(vehicle_id_field_txt, vehicle_lot_number_field_txt);

        boolean lotTaken = pl.isLotBooked(vehicle.getLot());
        boolean isVehicleExists = pl.isVehicleExists(vehicle.getId());

        if (isVehicleExists) {
            showtoast("Vehicle already present");
        } else if (lotTaken) {
            showtoast("Lot already taken");
        } else {
            boolean wasAbleToPark = pl.parkVehicle(vehicle, vehicle.getLot());
            if (wasAbleToPark) {
                showtoast("Vehicle Parked");
            } else {
                showtoast("Vehicle Already Parked choose a different lot or vehicle");
            }
        }
    }

    private boolean areInputsValid(String vehicle_id_field_txt, String vehicle_lot_number_field_txt) {

        if (TextUtils.isEmpty(vehicle_id_field_txt)) {
            showtoast("Please enter vehicle number");
            return false;
        } else if (TextUtils.isEmpty(vehicle_lot_number_field_txt)) {
            showtoast("Please enter parking lot number");
            return false;
        }

        return true;
    }

    private void showtoast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
