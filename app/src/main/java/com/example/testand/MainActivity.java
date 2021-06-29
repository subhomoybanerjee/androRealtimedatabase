package com.example.testand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    EditText et,cp,pd;
    Button bt;
    public static String s="com.example.testand.extra.NAME";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cp=findViewById(R.id.company);
        et=findViewById(R.id.word);
        bt=findViewById(R.id.button);
        pd=findViewById(R.id.prdesc);
        //Intent inte=new Intent(this,MainActivity2.class);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,et.getText().toString(),Toast.LENGTH_SHORT).show();
                HashMap <String,Object> hm=new HashMap<>();
                hm.put(pd.getText().toString(),et.getText().toString());
                FirebaseDatabase.getInstance().getReference().child(cp.getText().toString())
                        .push()
                        .setValue(hm)
                        .addOnCompleteListener(task ->
                                Toast.makeText(MainActivity.this,"huehue",Toast.LENGTH_SHORT).show());
                //inte.putExtra(s,et.getText().toString());
                //startActivity(inte);

            }
        });

    }
}