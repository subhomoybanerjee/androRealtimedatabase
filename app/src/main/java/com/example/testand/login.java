package com.example.testand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    public static final String d="hehehehehehehehe";
    EditText lnme,lpass;
    Button go,freg;
    FirebaseAuth lauth;
    public void tost(String s){
        Toast.makeText(login.this,s,Toast.LENGTH_SHORT).show();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lnme=findViewById(R.id.lname);
        lpass=findViewById(R.id.lpass);
        go=findViewById(R.id.lesgo);
        freg=findViewById(R.id.regnew);
        lauth=FirebaseAuth.getInstance();


        freg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nme=lnme.getText().toString();
                String pss=lpass.getText().toString();

                lauth.signInWithEmailAndPassword(nme,pss).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            tost("user found");
                            Intent inte=new Intent(getApplicationContext(),product.class);
                            inte.putExtra(d,nme);
                            startActivity(inte);
                        }
                        else{
                            tost("error");
                        }
                    }
                });
            }
        });


    }
}