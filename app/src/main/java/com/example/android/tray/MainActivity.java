package com.example.android.tray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button Cgroup,Jgroup;
    FirebaseAuth fAuth;
    FirebaseAuth.AuthStateListener fAuthlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cgroup = findViewById(R.id.Cbtn);
        Jgroup = findViewById(R.id.Jbtn);
        fAuth = FirebaseAuth.getInstance();


        Cgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),C_GroupActivity.class));
            }
        });

        Jgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),J_GroupActivity.class));
            }
        });

    }
}
