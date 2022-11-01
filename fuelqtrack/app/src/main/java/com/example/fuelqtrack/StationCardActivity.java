package com.example.fuelqtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StationCardActivity extends AppCompatActivity {

    Button View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stationcard);
        getSupportActionBar().hide();

        View = findViewById(R.id.view);

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UpdateStation.class);
                startActivity(i);
            }
        });

    }
}
