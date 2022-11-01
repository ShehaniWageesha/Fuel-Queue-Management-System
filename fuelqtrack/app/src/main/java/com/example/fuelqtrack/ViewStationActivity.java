package com.example.fuelqtrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fuelqtrack.API.ApiFuel;
import com.example.fuelqtrack.Adapter.FuelAdapter;
import com.example.fuelqtrack.Models.StationModel;
import com.example.fuelqtrack.Services.FuelService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStationActivity extends AppCompatActivity {

    RecyclerView rView;
    List<StationModel> list;
    FuelAdapter fuelAdapter;
    EditText edtsrch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_station);

        rView = findViewById(R.id.recyclerView);
        edtsrch = findViewById(R.id.editTextTextPersonName5);

        rView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        fuelAdapter = new FuelAdapter(getApplicationContext(),list);

        rView.setAdapter(fuelAdapter);
        FuelService apiService = ApiFuel.getRetrofit().create(FuelService.class);
        Call<List<StationModel>> call = apiService.getAllStations();

        call.enqueue(new Callback<List<StationModel>>() {
            @Override
            public void onResponse(Call<List<StationModel>> call, Response<List<StationModel>> response) {
                list.addAll(response.body());
                Log.d("TAG","Response = "+list);
                fuelAdapter.setFuelData(list);
            }

            @Override
            public void onFailure(Call<List<StationModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        edtsrch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search(editable.toString());

            }
        });

    }

    private void search(String toString) {
        ArrayList<StationModel> filterList = new ArrayList<>();

        for (StationModel item: list){
            if (item.getStationName().toLowerCase().contains(toString.toLowerCase())) {
                filterList.add(item);
            }
        }
        fuelAdapter.setFuelData(filterList);
    }
}