package com.example.fuelqtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fuelqtrack.API.ApiQueue;
import com.example.fuelqtrack.Models.QueueModel;
import com.example.fuelqtrack.Services.QueueService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveQueue extends AppCompatActivity {
    TextView station, que, time, bike, car, lorry, van, plit, dlit;
    Button afterbtn, beforebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_queue);

        station=findViewById(R.id.textView10l);
        que=findViewById(R.id.textView11l);
        time=findViewById(R.id.textView19l);
        bike=findViewById(R.id.textView12l);
        car=findViewById(R.id.textView13l);
        lorry=findViewById(R.id.textView14l);
        van=findViewById(R.id.textView14l2);
        plit=findViewById(R.id.textView16l);
        dlit=findViewById(R.id.textView18l);
        afterbtn = findViewById(R.id.button6l);
        beforebtn = findViewById(R.id.button7l);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String times = format.format(calendar.getTime());

        String name = getIntent().getStringExtra("stationName");
        String parivalTime = getIntent().getStringExtra("parivalTime");
        String darivalTime = getIntent().getStringExtra("darivalTime");
        int pliters = getIntent().getIntExtra("pliters", 0);
        int dliters = getIntent().getIntExtra("dliters", 0);
        String pfinishTime = getIntent().getStringExtra("pfinishTime");
        String dfinishTime = getIntent().getStringExtra("dfinishTime");
        int leave = getIntent().getIntExtra("userMobile", 0);

        station.setText(name);
        time.setText(times);

        if(pliters == 0){
            plit.setText("Arrival Time\n"+parivalTime);
        }else if(pliters > 0){
            plit.setText("Available\n" + pliters +"L");
        }
        if(dliters == 0){
            dlit.setText("Arrival Time\n"+ darivalTime);

        }else if(dliters > 0){
            dlit.setText("Available\n" + dliters+"L");
        }

        QueueModel queueModel = new QueueModel();

        QueueService apiService = ApiQueue.getRetrofit().create(QueueService.class);
        Call<Object> call = apiService.getAQueue(name);
        Call<Object> call1 = apiService.getCarQueue(name);
        Call<Object> call2 = apiService.getBikeQueue(name);
        Call<Object> call3 = apiService.getLorryQueue(name);
        Call<Object> call5 = apiService.getVanQueue(name);

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

        call5.enqueue(new Callback<Object>() {
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

        queueModel.setJoined(Boolean.parseBoolean("false"));
        afterbtn.setOnClickListener(new View.OnClickListener() {

            Call<QueueModel> call4 = apiService.updateQueue(leave, queueModel);
            @Override
            public void onClick(View view) {
                call4.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(LeaveQueue.this, ViewStationActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });

        beforebtn.setOnClickListener(new View.OnClickListener() {
            Call<QueueModel> call4 = apiService.updateQueue(leave, queueModel);
            @Override
            public void onClick(View view) {
                call4.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(LeaveQueue.this, ViewStationActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });

    }
}