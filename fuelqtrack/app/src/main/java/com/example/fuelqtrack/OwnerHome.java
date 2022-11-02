package com.example.fuelqtrack;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.ImageButton;

import com.example.fuelqtrack.API.ApiFuel;
import com.example.fuelqtrack.Adapter.FuelAdapter;
import com.example.fuelqtrack.Adapter.OwnerAdapter;
import com.example.fuelqtrack.Models.StationModel;
import com.example.fuelqtrack.Services.FuelService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerHome extends AppCompatActivity {
    RecyclerView rView;
    List<StationModel> list;
    OwnerAdapter ownerAdapter;
    EditText edtsrch;
    Button AddStat;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        rView = findViewById(R.id.recyclerViewo);
        edtsrch = findViewById(R.id.editTextTextPersonName5o);
        AddStat = findViewById(R.id.addStat2);
        imageButton = findViewById(R.id.imageButton);

        rView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        ownerAdapter = new OwnerAdapter(getApplicationContext(),list);

        rView.setAdapter(ownerAdapter);
        FuelService apiService = ApiFuel.getRetrofit().create(FuelService.class);
        Call<List<StationModel>> call = apiService.getAllStations();

        call.enqueue(new Callback<List<StationModel>>() {
            @Override
            public void onResponse(Call<List<StationModel>> call, Response<List<StationModel>> response) {
                list.addAll(response.body());
                Log.d("TAG","Response = "+list);
                ownerAdapter.setFuelData(list);
            }

            @Override
            public void onFailure(Call<List<StationModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        AddStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddStation.class);
                startActivity(i);
            }
        });

        AddStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
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
        ownerAdapter.setFuelData(filterList);
    }
}