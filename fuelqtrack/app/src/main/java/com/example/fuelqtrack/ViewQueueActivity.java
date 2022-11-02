package com.example.fuelqtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fuelqtrack.API.ApiQueue;
import com.example.fuelqtrack.Services.QueueService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewQueueActivity extends AppCompatActivity {

    TextView station, que, time, bike, car, lorry, van, plit, dlit;
    Integer list;
    Button petbtn, disbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_queue);

        station=findViewById(R.id.textView10);
        que=findViewById(R.id.textView11);
        time=findViewById(R.id.textView19);
        bike=findViewById(R.id.textView12);
        car=findViewById(R.id.textView13);
        lorry=findViewById(R.id.textView14);
        van=findViewById(R.id.textView26);
        plit=findViewById(R.id.textView16);
        dlit=findViewById(R.id.textView18);
        petbtn = findViewById(R.id.update);
        disbtn = findViewById(R.id.button3);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String times = format.format(calendar.getTime());

        String stationName = getIntent().getStringExtra("stationName");
        String id = getIntent().getStringExtra("_id");
        String stationLocation = getIntent().getStringExtra("stationLocation");
        String petrol = getIntent().getStringExtra("petrol");
        String diesel = getIntent().getStringExtra("diesel");
        String parivalTime = getIntent().getStringExtra("parivalTime");
        String darivalTime = getIntent().getStringExtra("darivalTime");
        int pliters = getIntent().getIntExtra("pliters", 0);
        int dliters = getIntent().getIntExtra("dliters", 0);
        String pfinishTime = getIntent().getStringExtra("pfinishTime");
        String dfinishTime = getIntent().getStringExtra("dfinishTime");

        station.setText(stationName);
        time.setText(times);

        if(pliters == 0){
            plit.setText("Arrival Time\n"+parivalTime);
            petbtn.setEnabled(false);
        }else if(pliters > 0){
            plit.setText("Available\n" + pliters +"L");
            petbtn.setEnabled(true);
        }

        if(dliters == 0){
            dlit.setText("Arrival Time\n"+ darivalTime);
            disbtn.setEnabled(false);

        }else if(dliters > 0){
            dlit.setText("Available\n" + dliters+"L");
            disbtn.setEnabled(true);

        }

        QueueService apiService = ApiQueue.getRetrofit().create(QueueService.class);
        Call<Object> call = apiService.getAQueue(stationName);
        Call<Object> call1 = apiService.getCarQueue(stationName);
        Call<Object> call2 = apiService.getBikeQueue(stationName);
        Call<Object> call3 = apiService.getLorryQueue(stationName);
        Call<Object> call4 = apiService.getVanQueue(stationName);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                que.setText("Vehicle Queue : "+data.toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        call1.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                car.setText("Car : "+data.toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        call2.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                bike.setText("Motor Bike : "+data.toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        call3.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                lorry.setText("Lorry : "+data.toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        call4.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                van.setText("Van : "+data.toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        petbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), JoinActivity.class);
                i.putExtra("stationName", stationName);
                i.putExtra("pliters", pliters);
                i.putExtra("dliters", dliters);
                i.putExtra("parivalTime", parivalTime);
                i.putExtra("darivalTime", darivalTime);
                i.putExtra("pfinishTime", pfinishTime);
                i.putExtra("dfinishTime", dfinishTime);
                startActivity(i);
            }
        });

        disbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), JoinDieselActivity.class);
                i.putExtra("stationName", stationName);
                i.putExtra("pliters", pliters);
                i.putExtra("dliters", dliters);
                i.putExtra("parivalTime", parivalTime);
                i.putExtra("darivalTime", darivalTime);
                i.putExtra("pfinishTime", pfinishTime);
                i.putExtra("dfinishTime", dfinishTime);
                startActivity(i);
            }
        });
    }
}