package com.example.heremybus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private View busview1;
    LottieAnimationView animationView;
    ImageView imageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationView = findViewById(R.id.clickanimation);
        imageView = findViewById(R.id.image1);


        setContentView(R.layout.activity_main);
        busview1=findViewById(R.id.image1);
        busview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                animationView.playAnimation();

                Thread td = new Thread(){

                    public void run(){
                        try {
                            sleep(
                                    100
                            );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finally {
                            Intent intent =new Intent(MainActivity.this,Bus1UserMaps.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                };td.start();

            }
        });

    }
}