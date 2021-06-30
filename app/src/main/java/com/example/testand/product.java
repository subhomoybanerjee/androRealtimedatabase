package com.example.testand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class product extends AppCompatActivity {


    EditText et,cp,pd;
    Button bt,logot;
    TextView tv;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cp=findViewById(R.id.company);
        et=findViewById(R.id.word);
        bt=findViewById(R.id.button);
        pd=findViewById(R.id.prdesc);
        tv=findViewById(R.id.tvname);
        logot=findViewById(R.id.logout);

        Intent intent=getIntent();

        String sholg=intent.getStringExtra(login.d);
        String sho=intent.getStringExtra(register.s);


        tv.setText(sho);
        tv.setText(sholg);

        //Log.i(TAG, "intent created:  "+sho+sholg);

        logot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap <String,Object> hm=new HashMap<>();

                String split_first = tv.getText().toString().substring(0,tv.getText().toString().indexOf("@"));

                hm.put(pd.getText().toString(),et.getText().toString());
                FirebaseDatabase.getInstance().getReference().child(split_first)
                        .push()
                        .setValue(hm)
                        .addOnCompleteListener(task ->
                                Toast.makeText(product.this,"huehue",Toast.LENGTH_SHORT).show());


            }
        });

    }
}