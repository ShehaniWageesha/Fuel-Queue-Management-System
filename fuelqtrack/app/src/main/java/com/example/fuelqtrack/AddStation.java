package com.example.fuelqtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.fuelqtrack.API.ApiFuel;
import com.example.fuelqtrack.Models.StationModel;
import com.example.fuelqtrack.Services.FuelService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStation extends AppCompatActivity {

    EditText stationName, address, petrolarival, dieselarrival, petrolLiters, dieselLiters, pfinishTime, dfinishTime;
    Button save;
    Switch pSwitch, dSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);

        stationName = findViewById(R.id.editTextTextPersonName6);
        address = findViewById(R.id.editTextTextPersonName8);
        petrolarival = findViewById(R.id.editTextTextPersonName11);
        dieselarrival = findViewById(R.id.editTextTextPersonName12);
        petrolLiters = findViewById(R.id.editTextTextPersonName13);
        dieselLiters = findViewById(R.id.editTextTextPersonName14);
        pfinishTime = findViewById(R.id.ftimeP);
        dfinishTime = findViewById(R.id.ftimeD);
        save = findViewById(R.id.button6);
        pSwitch = findViewById(R.id.switch1);
        dSwitch = findViewById(R.id.switch2);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StationModel stationModel = new StationModel();

                FuelService fuelService = ApiFuel.getRetrofit().create(FuelService.class);
                Call<StationModel> call = fuelService.insertStation(stationModel);
                stationModel.setStationName(stationName.getText().toString());
                stationModel.setStationLocation(address.getText().toString());
                stationModel.setParivalTime(petrolarival.getText().toString());
                stationModel.setDarivalTime(dieselarrival.getText().toString());
                stationModel.setPliters(Integer.parseInt(petrolLiters.getText().toString()));
                stationModel.setDliters(Integer.parseInt(dieselLiters.getText().toString()));
                stationModel.setPfinishTime(pfinishTime.getText().toString());
                stationModel.setDfinishTime(dfinishTime.getText().toString());

                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run(){
                                                  call.enqueue(new Callback<StationModel>() {

                                                      @Override
                                                      public void onResponse(Call<StationModel> call, Response<StationModel> response) {
                                                          if(response.isSuccessful()){
                                                              Intent i = new Intent(getApplicationContext(), OwnerHome.class);
                                                              startActivity(i);
                                                              Toast.makeText(AddStation.this,"Station Added Success", Toast.LENGTH_LONG).show();
                                                          }
                                                          else{
                                                              Toast.makeText(AddStation.this,"Error", Toast.LENGTH_LONG).show();

                                                          }
                                                      }

                                                      @Override
                                                      public void onFailure(Call<StationModel> call, Throwable t) {
                                                          Toast.makeText(AddStation.this,"Error", Toast.LENGTH_LONG).show();
                                                      }
                                                  });

                                              }
                                          },500);


            }
        });

        pSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    petrolarival.setVisibility(View.VISIBLE);
                    petrolLiters.setVisibility(View.VISIBLE);
                    pfinishTime.setVisibility(View.VISIBLE);
                } else {
                    petrolarival.setVisibility(View.GONE);
                    petrolLiters.setVisibility(View.GONE);
                    pfinishTime.setVisibility(View.GONE);
                }
            }
        });

        dSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dieselarrival.setVisibility(View.VISIBLE);
                    dieselLiters.setVisibility(View.VISIBLE);
                    dfinishTime.setVisibility(View.VISIBLE);
                } else {
                    dieselarrival.setVisibility(View.GONE);
                    dieselLiters.setVisibility(View.GONE);
                    dfinishTime.setVisibility(View.GONE);
                }
            }
        });
    }
}