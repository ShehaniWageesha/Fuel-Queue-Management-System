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

public class LoginActivity extends AppCompatActivity {
    EditText mobile, password;
    Button regbtn, loginbtn;
    TextView serviclg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        regbtn = findViewById(R.id.regViewbtn);
        loginbtn = findViewById(R.id.loginbtn);
        mobile = findViewById(R.id.edtnumb);
        password = findViewById(R.id.editTextTextPassword);
        serviclg = findViewById(R.id.serviclg);

        serviclg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ServiceLoginActivity.class);
                startActivity(i);
            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDB accountDB = new UserDB(getApplicationContext());
                String uname = mobile.getText().toString();
                String pass = password.getText().toString();
                UserModel userModel = accountDB.login(uname, pass);
                if (userModel == null){
                    Toast.makeText(LoginActivity.this, "Invalid User Details.", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(LoginActivity.this, ViewStationActivity.class);
                    intent.putExtra("account", userModel);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Success.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}