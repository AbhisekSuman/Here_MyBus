package com.example.heremybus;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

   public static final String TAG = "TAG";
    EditText efullname,eemail,epassword1,econfirm;
    Button eregisterbutton;
    ProgressBar progressBar;

    FirebaseFirestore fstore;
    String emailid;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        efullname = findViewById(R.id.fullname);
        eemail = findViewById(R.id.email);
        epassword1 = findViewById(R.id.password1);
        econfirm = findViewById(R.id.confirm);
        eregisterbutton = findViewById(R.id.button3);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        if(fauth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),MainActivity .class));
            finish();
        }

        eregisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = eemail.getText().toString().trim();
                String password1 = epassword1.getText().toString().trim();
                final String fullname = efullname.getText().toString().trim();
                final String confirm = econfirm.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    eemail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    epassword1.setError("Password is Required");
                    return;

                }
                if (password1.length() < 6) {
                    epassword1.setError("Password must be >= 6 character");
                    return;
                }

                fauth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser fuser = fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Register successfull", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"Onfailure: Email Not Sent" + e.getMessage());
                                }
                            });


                            Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                            emailid = fauth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("user").document(emailid);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName", fullname);
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onsuccess: user profile is created for"+ emailid);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure: "+ e.toString());

                                }
                            });

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else{
                            Toast.makeText(Register.this,"Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
  }
}




