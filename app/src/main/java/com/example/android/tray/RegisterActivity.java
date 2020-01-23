package com.example.android.tray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText rName,rPassword,rmail;
    private Button registerbtn;
    private FirebaseAuth fAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rName = findViewById(R.id.rname);
        rmail = findViewById(R.id.Rmail);
        rPassword = findViewById(R.id.Rpass);
        registerbtn = findViewById(R.id.signup_btn);
        fAuth = FirebaseAuth.getInstance();
        final ProgressDialog dialog = new ProgressDialog(this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Name = rName.getText().toString().trim();
                final String Mail = rmail.getText().toString().trim();
                String Password = rPassword.getText().toString().trim();

                dialog.setMessage("Registering you in...");
                dialog.show();
                fAuth.createUserWithEmailAndPassword(Mail,Password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(
                                    Name,
                                    Mail
                            );
                            FirebaseDatabase.getInstance().getReference().child("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Login1Activity.class));
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                });
            }
        });



    }
}
