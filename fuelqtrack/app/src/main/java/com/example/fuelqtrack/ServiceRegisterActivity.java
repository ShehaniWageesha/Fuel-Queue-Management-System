package com.example.fuelqtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelqtrack.DataBase.OwnerDB;
import com.example.fuelqtrack.DataBase.UserDB;
import com.example.fuelqtrack.Models.OwnerModel;
import com.example.fuelqtrack.Models.UserModel;

public class ServiceRegisterActivity extends AppCompatActivity {
    EditText username, mobile, nic, stationName, conPass, password;
    Button lgviewbtn, registerbtn;
    TextView vehicleReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_register);

        lgviewbtn = findViewById(R.id.lgviewbtns);
        registerbtn = findViewById(R.id.regbtns);
        username = findViewById(R.id.editTextTextPersonNames);
        mobile = findViewById(R.id.editTextTextPersonName2s);
        password = findViewById(R.id.editTextTextPersonName3s);
        conPass = findViewById(R.id.editTextTextPersonName4s);
        nic = findViewById(R.id.nictxt);
        stationName = findViewById(R.id.statN);
        vehicleReg = findViewById(R.id.textView22);

        vehicleReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        lgviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ServiceLoginActivity.class);
                startActivity(i);
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(username.length() > 0 && password.length() > 0) {

                        OwnerDB ownerDB = new OwnerDB(getApplicationContext());
                        OwnerModel ownerModel = new OwnerModel();
                        ownerModel.setPassword(password.getText().toString());
                        ownerModel.setNic(nic.getText().toString());
                        ownerModel.setStationName(stationName.getText().toString());
                        ownerModel.setNumber(mobile.getText().toString());
                        ownerModel.setName(username.getText().toString());
                        OwnerModel temp = ownerDB.checkUsername(username.getText().toString());
                        if (temp == null) {
                            if (ownerDB.create(ownerModel)) {
                                Intent i = new Intent(ServiceRegisterActivity.this, ServiceLoginActivity.class);
                                startActivity(i);
                                Toast.makeText(ServiceRegisterActivity.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ServiceRegisterActivity.this, "Registered Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ServiceRegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();

                        }

                    }


                }catch (Exception e){
                    Toast.makeText(ServiceRegisterActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}