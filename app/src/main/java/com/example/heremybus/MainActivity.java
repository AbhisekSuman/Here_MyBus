package com.example.heremybus;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private View busview1;
    private View textView;
    private View busView2;
    Button button;





//    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        setContentView(R.layout.activity_main);



// BACKGROUND SERVICE
            setContentView(R.layout.activity_main);
            button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startService(new Intent(getApplicationContext(),BackGround_Servies.class));
                    Intent i = new Intent(MainActivity.this,Bus1Driver.class);
                    startActivity(i);
                }
            });


        /*Intent serviceIntent = new Intent(this, BackGround_Servies.class);
        startForegroundService(serviceIntent);

        public boolean foreGround(){
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)){
                if (BackGround_Servies.class.getName().equals(service.service.getClassName())){
                    return true;
                }
            }
            return;
        }*/


        Intent intent = new Intent(this,BackGround_Servies.class);

        if (Build.VERSION.SDK_INT >= 0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            }
            else {
                stopService(intent);
            }
        }


        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Bus1Driver.class);
                startActivity(intent);
            }
        });


        busView2 = findViewById(R.id.bus2);
        busView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Bus1UserMaps.class);
                startActivity(intent);
            }
        });

        busview1=findViewById(R.id.bus1);
        busview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Bus1UserMaps.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
            final SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);


        if (id == R.id.driver){
            Intent intent = new Intent(MainActivity.this, driver.class);
            startActivity(intent);
            Toast.makeText(this, "Driver", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (id == R.id.feedback){
            Intent intent = new Intent(MainActivity.this, Feedback.class);
            Toast.makeText(this, "Feedback", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (id == R.id.aboutus){
            Intent intent = new Intent(MainActivity.this, driver.class);
            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (id == R.id.logout){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            Toast.makeText(this, "Bye!!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}