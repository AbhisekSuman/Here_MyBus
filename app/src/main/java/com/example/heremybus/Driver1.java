package com.example.heremybus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class Driver1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver1);



        Intent intent = new Intent(this,BackGround_Servies.class);

        if (Build.VERSION.SDK_INT >= 0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            }
            else {
                stopService(intent);
            }
        }
    }
}