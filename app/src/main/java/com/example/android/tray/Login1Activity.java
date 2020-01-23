package com.example.android.tray;

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

public class Login1Activity extends AppCompatActivity {

    EditText mail,password;
    Button loginbtn;
    TextView Create_user,forgot_pass;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        mail = findViewById(R.id.Rmail);
        password = findViewById(R.id.Rpass);
        loginbtn = findViewById(R.id.signup_btn);
        Create_user = findViewById(R.id.create_user);
        forgot_pass = findViewById(R.id.forgotpass);

        fAuth = FirebaseAuth.getInstance();


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Mail = mail.getText().toString().trim();
                String Password = password.getText().toString().trim();


                if(TextUtils.isEmpty(Mail)){
                    mail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("Password is required");
                    return;
                }
                if(password.length() <6) {
                    password.setError("Password must be >= 6 characters");
                    return;
                }


                fAuth.signInWithEmailAndPassword(Mail,Password).addOnCompleteListener(Login1Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){


                            Toast.makeText(Login1Activity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Login1Activity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }
}
