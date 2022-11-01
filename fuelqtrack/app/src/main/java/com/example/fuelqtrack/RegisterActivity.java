package com.example.fuelqtrack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelqtrack.DataBase.UserDB;
import com.example.fuelqtrack.Models.UserModel;

public class RegisterActivity extends AppCompatActivity {

    EditText username, mobile, conPass, password;
    Button lgviewbtn, registerbtn;
    TextView stationReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        lgviewbtn = findViewById(R.id.lgviewbtn);
        registerbtn = findViewById(R.id.regbtn);
        username = findViewById(R.id.editTextTextPersonName);
        mobile = findViewById(R.id.editTextTextPersonName2);
        password = findViewById(R.id.editTextTextPersonName3);
        conPass = findViewById(R.id.editTextTextPersonName4);
        stationReg = findViewById(R.id.statReg);

        stationReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ServiceRegisterActivity.class);
                startActivity(i);
            }
        });

        lgviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(username.length() > 0 && password.length() > 0) {

                        UserDB userDB = new UserDB(getApplicationContext());
                        UserModel userModel = new UserModel();
                        userModel.setPassword(password.getText().toString());
                        userModel.setNumber(mobile.getText().toString());
                        userModel.setName(username.getText().toString());
                        UserModel temp = userDB.checkUsername(username.getText().toString());
//                        if(password.getText().toString() == conPass.getText().toString()) {
                            if (temp == null) {
                                if (userDB.create(userModel)) {
                                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    Toast.makeText(RegisterActivity.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Registered Unsuccessful", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();

                            }
//                        }else{
//                            Toast.makeText(RegisterActivity.this, "Password mismatch", Toast.LENGTH_SHORT).show();
//                        }
                    }


                }catch (Exception e){
                    Toast.makeText(RegisterActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}