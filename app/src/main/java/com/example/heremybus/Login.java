package com.example.heremybus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        eemail = findViewById(R.id.email);
        epassword = findViewById(R.id.password);
        elogin = findViewById(R.id.button3);
        eregister = findViewById(R.id.register);


        fauth = FirebaseAuth.getInstance();

        eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });


        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String email = eemail.getText().toString().trim();
                String password = epassword.getText().toString().trim();

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

                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Log In Sucesful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }
                });
            }
        });
    }
}