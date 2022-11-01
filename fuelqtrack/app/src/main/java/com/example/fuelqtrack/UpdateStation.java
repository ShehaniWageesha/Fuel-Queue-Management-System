package com.example.fuelqtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelqtrack.API.ApiFuel;
import com.example.fuelqtrack.Models.StationModel;
import com.example.fuelqtrack.Services.FuelService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateStation extends AppCompatActivity {

    EditText stationName, address, petrolarival, dieselarrival, petrolLiters, dieselLiters, pfinishTime, dfinishTime;

    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_station);

        stationName = findViewById(R.id.editTextTextPersonName6u);
        address = findViewById(R.id.editTextTextPersonName8u);
        petrolarival = findViewById(R.id.editTextTextPersonName11u);
        dieselarrival = findViewById(R.id.editTextTextPersonName12u);
        petrolLiters = findViewById(R.id.editTextTextPersonName13u);
        dieselLiters = findViewById(R.id.editTextTextPersonName14u);
        pfinishTime = findViewById(R.id.pf);
        dfinishTime = findViewById(R.id.df);
        btnUpdate = findViewById(R.id.button6u);

        String stname = getIntent().getStringExtra("stationName");
        String id = getIntent().getStringExtra("_id");
        String loc = getIntent().getStringExtra("stationLocation");

//        boolean pet = getIntent().getBooleanExtra("petrol", false);
//        boolean dies = getIntent().getBooleanExtra("diesel", false);

        String parivaltime = getIntent().getStringExtra("parivalTime");
        String darivaltime = getIntent().getStringExtra("darivalTime");

        int petlit = getIntent().getIntExtra("pliters", 0);
        int dielit = getIntent().getIntExtra("dliters", 0);

        String pfinishtime = getIntent().getStringExtra("pfinishTime");
        String dfinishtime = getIntent().getStringExtra("dfinishTime");

        petrolarival.setText(parivaltime);
        dieselarrival.setText(darivaltime);
        stationName.setText(stname);
        address.setText(loc);
        petrolLiters.setText(""+ petlit);
        dieselLiters.setText(""+ dielit);
        pfinishTime.setText(pfinishtime);
        dfinishTime.setText(dfinishtime);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    StationModel stationModel = new StationModel();
                    stationModel.setStationName(stationName.getText().toString());
                    stationModel.setStationLocation(address.getText().toString());
                    stationModel.setParivalTime(petrolarival.getText().toString());
                    stationModel.setDarivalTime(dieselarrival.getText().toString());
                    stationModel.setPliters(Integer.parseInt(petrolLiters.getText().toString()));
                    stationModel.setDliters(Integer.parseInt(dieselLiters.getText().toString()));
                    stationModel.setParivalTime(pfinishTime.getText().toString());
                    stationModel.setDarivalTime(dfinishTime.getText().toString());
                    FuelService fuelService = ApiFuel.getRetrofit().create(FuelService.class);

                    Call call = fuelService.updateStation(id, stationModel);

                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if (response.isSuccessful()) {
                                finish();
                                Log.d("TAG","Response = "+response.code());
                                Toast.makeText(UpdateStation.this, "Update Success", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(UpdateStation.this, "Fail", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(UpdateStation.this, "Fail", Toast.LENGTH_SHORT).show();

                        }
                    });
                }catch(Exception e){

                }
            }
        });
    }
}