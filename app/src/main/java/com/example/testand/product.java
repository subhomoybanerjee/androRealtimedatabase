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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class product extends AppCompatActivity {

    public static final String s="com.example.record.extra.NAME";
    EditText et,pd;
    Button bt,logot,bts;
    TextView tv,cp;
    DatabaseReference myref;
    FirebaseDatabase db;

    public void tost(String s){
        Toast.makeText(product.this,s,Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cp=findViewById(R.id.company);
        et=findViewById(R.id.word);
        bt=findViewById(R.id.button);
        bts=findViewById(R.id.button2);
        pd=findViewById(R.id.prdesc);
        tv=findViewById(R.id.tvname);
        logot=findViewById(R.id.logout);

        Intent intent=getIntent();

        String sholg=intent.getStringExtra(login.d);
        String sho=intent.getStringExtra(register.s);


        tv.setText(sho);
        tv.setText(sholg);
        String split_first = tv.getText().toString().substring(0,tv.getText().toString().indexOf("@"));

        db=FirebaseDatabase.getInstance();
        myref=db.getReference(split_first);

        //Log.i(TAG, "intent created:  "+sho+sholg);

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child(split_first);
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
                hm.put("company",et.getText().toString());
                hm.put("product",pd.getText().toString());
                myref.push().setValue(hm).addOnCompleteListener(task -> tost("added")) ;

            }
        });

        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),showdata.class);
                intent.putExtra(s,split_first);
                startActivity(intent);

            }
        });



    }
}