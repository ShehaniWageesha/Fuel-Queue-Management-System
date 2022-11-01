package com.example.fuelqtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.fuelqtrack.API.ApiQueue;
import com.example.fuelqtrack.Models.QueueModel;
import com.example.fuelqtrack.Services.QueueService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinDieselActivity extends AppCompatActivity {
    EditText mobile;
    RadioButton van, lorry;
    boolean join;
    String type;
    Button joinbtn, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_diesel);

        joinbtn = findViewById(R.id.button4d);
        mobile = findViewById(R.id.editTextTextPersonName7d);
        van = findViewById(R.id.radioButton4d);
        lorry = findViewById(R.id.radioButton3d);
        cancel =findViewById(R.id.button5d);

        String name = getIntent().getStringExtra("stationName");
        String parivalTime = getIntent().getStringExtra("parivalTime");
        String darivalTime = getIntent().getStringExtra("darivalTime");
        int pliters = getIntent().getIntExtra("pliters", 0);
        int dliters = getIntent().getIntExtra("dliters", 0);
        String pfinishTime = getIntent().getStringExtra("pfinishTime");
        String dfinishTime = getIntent().getStringExtra("dfinishTime");

        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(mobile.getText().toString() == null){
                        Toast.makeText(getApplicationContext(),"Please add your mobile number", Toast.LENGTH_SHORT).show();

                    }else {

                        if (lorry.isChecked()) {
                            type = "Lorry";
                        } else {
                            type = "Van";
                        }
                        QueueModel queueModel = new QueueModel();
                        queueModel.setStationName(name);
                        queueModel.setUserMobile(Integer.parseInt(mobile.getText().toString()));
                        queueModel.setFuelType("Diesel");
                        queueModel.setVehicalType(type);
                        queueModel.setJoined(Boolean.parseBoolean("true"));

                        QueueService apiService = ApiQueue.getRetrofit().create(QueueService.class);
                        Call call = apiService.createQueue(queueModel);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                if (response.isSuccessful()) {
                                    Intent intent = new Intent(JoinDieselActivity.this, LeaveQueue.class);
                                    intent.putExtra("userMobile", Integer.parseInt(mobile.getText().toString()));
                                    intent.putExtra("stationName", name);
                                    intent.putExtra("pliters", pliters);
                                    intent.putExtra("dliters", dliters);
                                    intent.putExtra("parivalTime", parivalTime);
                                    intent.putExtra("darivalTime", darivalTime);
                                    intent.putExtra("pfinishTime", pfinishTime);
                                    intent.putExtra("dfinishTime", dfinishTime);
                                    startActivity(intent);
                                }

                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }catch (Exception e){

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinDieselActivity.this, ViewStationActivity.class);
                intent.putExtra("stationName", name);
                intent.putExtra("pliters", pliters);
                intent.putExtra("dliters", dliters);
                intent.putExtra("parivalTime", parivalTime);
                intent.putExtra("darivalTime", darivalTime);
                intent.putExtra("pfinishTime", pfinishTime);
                intent.putExtra("dfinishTime", dfinishTime);
                startActivity(intent);
            }
        });
    }
}