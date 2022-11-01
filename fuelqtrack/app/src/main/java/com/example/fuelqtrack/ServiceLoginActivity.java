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

public class ServiceLoginActivity extends AppCompatActivity {
    EditText mobile, password;
    Button regbtn, loginbtn;
    TextView serviclgl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_login);

        regbtn = findViewById(R.id.regViewbtnl);
        loginbtn = findViewById(R.id.loginbtnl);
        mobile = findViewById(R.id.edtnumbl);
        password = findViewById(R.id.editTextTextPasswordl);
        serviclgl = findViewById(R.id.serviclgl);

        serviclgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ServiceRegisterActivity.class);
                startActivity(i);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OwnerDB ownerDB = new OwnerDB(getApplicationContext());
                String uname = mobile.getText().toString();
                String pass = password.getText().toString();
                OwnerModel ownerModel = ownerDB.login(uname, pass);
                if (ownerModel == null){
                    Toast.makeText(ServiceLoginActivity.this, "Invalid User Details.", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(ServiceLoginActivity.this, OwnerHome.class);
                    intent.putExtra("account", ownerModel);
                    intent.putExtra("nic", ownerModel.getNic());
                    startActivity(intent);
                    Toast.makeText(ServiceLoginActivity.this, "Login Success.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}