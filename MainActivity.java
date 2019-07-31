package com.example.hello;

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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText skemail,skpassword;
    private Button btn,btn1;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                skemail=(EditText)findViewById(R.id.username);
                skpassword=(EditText)findViewById(R.id.userpassword);
                btn = (Button)findViewById(R.id.reg);
                btn1 = (Button)findViewById(R.id.sklogin);

                firebaseAuth=FirebaseAuth.getInstance();
                FirebaseUser user=firebaseAuth.getCurrentUser();
                validate(skemail.getText().toString(), skpassword.getText().toString());

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, user_registration.class));
            }

        });

    }
    private void validate(String skemail,String skpassword){

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(skemail,skpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    startActivity(new Intent(MainActivity.this, skhomepage.class));
                    Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}