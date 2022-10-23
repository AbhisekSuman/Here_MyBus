package com.example.heremybus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText eemail,epassword;
    Button elogin;
    TextView eregister;

    String correctemail = "mybus@gmail.com";
    String correctpassword = "123654";
    String correctemail1 = "mybus@gmail1.com";
    String correctpassword1 = "123456";
    String correctemail2 = "mybus@gmail2.com";
    String correctpassword2 = "654321";


    Boolean isvalid = false;
    private int counter = 5;

    Boolean isvalid1 = false;
    private int counter1 = 5;

    Boolean isvalid2 = false;
    private int counter2 = 5;



    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        LinearLayout constraintLayout = findViewById(R.id.Animation_layout);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();


        eemail = findViewById(R.id.email);
        epassword = findViewById(R.id.password);
        elogin = findViewById(R.id.button3);
        eregister = findViewById(R.id.register);

        final SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);

        final String type = sharedPreferences.getString("Email","");
          if (type.isEmpty()){
            Toast.makeText(getApplicationContext(), "please Login", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }


        fauth = FirebaseAuth.getInstance();

        eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent =new Intent(Login.this,Register.class);
//                startActivity(intent);
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });


        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String email = eemail.getText().toString().trim();
                String password = epassword.getText().toString().trim();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Email",email);
                editor.putString("Password",password);
                editor.commit();

//                Toast.makeText(getApplicationContext(), "Login Succesful", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(i);

                if(TextUtils.isEmpty(email))
                {
                    eemail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    epassword.setError("Password is Required");
                    return;
                }

                if(password.length() < 6)
                {
                    epassword.setError("Password must be >= 6 character");
                    return;
                }

//                Driver Login
                isvalid = validate(email,password);

                if (!isvalid) {
                    counter--;
                    Toast.makeText(Login.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    if (counter == 0) {
                        elogin.setEnabled(false);
                    }

                }
                else {
                    Toast.makeText(Login.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Driver1.class);
                    startActivity(intent);
                }
//              Driver2 login

                isvalid1 = validate1(email,password);

                if (!isvalid1) {
                    counter1--;
                    Toast.makeText(Login.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    if (counter1 == 0) {
                        elogin.setEnabled(false);
                    }

                }
                else {
                    Toast.makeText(Login.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Bus1Driver.class);
                    startActivity(intent);
                }

                //              Driver3 login

                isvalid2 = validate2(email,password);

                if (!isvalid2) {
                    counter2--;
                    Toast.makeText(Login.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    if (counter2 == 0) {
                        elogin.setEnabled(false);
                    }

                }
                else {
                    Toast.makeText(Login.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Map.class);
                    startActivity(intent);
                }



                fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Login Sucesful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Intent inte = new Intent(getApplicationContext(),MainActivity.class);
                            inte.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(inte);
                            finish();
//                            Intent intent =new Intent(Login.this,MainActivity.class);
//                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private Boolean validate2(String email, String password) {
        if (email.equals(correctemail2) && password.equals(correctpassword2)) {
            return  true;

        }
        return  false;


    }

    private Boolean validate1(String email, String password) {
        if (email.equals(correctemail1) && password.equals(correctpassword1)) {
            return  true;

        }
        return  false;

    }

    private Boolean validate(String email, String password) {
        if (email.equals(correctemail) && password.equals(correctpassword)) {
            return  true;

        }
        return  false;
    }


}
