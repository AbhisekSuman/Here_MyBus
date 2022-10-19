package com.example.heremybus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private View busview1;
    private View textView;
    private View busView2;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_main);

        busview1=findViewById(R.id.bus1);
        busview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Bus1UserMaps.class);
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

        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Bus1Driver.class);
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
            Intent intent = new Intent(MainActivity.this, driver.class);
            Toast.makeText(this, "Bye!!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}