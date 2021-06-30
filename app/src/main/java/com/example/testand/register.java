package com.example.testand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    FirebaseAuth fauth;
    EditText name,pass,rpass;
    Button add,logu;
    public static final String s="hehehehehehehehe";

    public void tost(String s){
        Toast.makeText(register.this,s,Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        add=findViewById(R.id.register);
        rpass=findViewById(R.id.repass);
        logu=findViewById(R.id.logus);
        fauth=FirebaseAuth.getInstance();


        logu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=name.getText().toString();
                String passw=pass.getText().toString();
                String rep=rpass.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(passw)){
                    tost("email or pass is empty");
                    return;
                }
                if(rep.compareTo(passw)!=0){
                    tost("passwords dont match");
                    return;
                }
                fauth.createUserWithEmailAndPassword(email,passw)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    tost("user added");
                                    Intent inte=new Intent(getApplicationContext(),product.class);
                                    inte.putExtra(s,email);
                                    startActivity(inte);

                                    //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }
                                else{
                                    tost("error"+task.getException().getMessage());
                                }

                            }
                        });

            }
        });



    }
}