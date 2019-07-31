package com.example.hello;

import android.app.ProgressDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_registration extends AppCompatActivity{

    private EditText skname,skemail,skpassword,skcpassword;
    private Button sreg;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_registration);
        setupUIViews();
        firebaseAuth=FirebaseAuth.getInstance();

        sreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String email=skemail.getText().toString().trim();
                    String password=skpassword.getText().toString().trim();

                    progressDialog.setMessage("Registering Please Wait...");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(user_registration.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(user_registration.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(user_registration.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });
    }
    private void setupUIViews(){
        skname = (EditText) findViewById(R.id.skname);
        skemail = (EditText) findViewById(R.id.skemail);
        skpassword = (EditText) findViewById(R.id.skpassword);
        skcpassword = (EditText) findViewById(R.id.skcpassword);
        sreg = (Button) findViewById(R.id.sreg);
        progressDialog = new ProgressDialog(this);
    }
    private boolean validate(){
        Boolean result=false;

        String name= skname.getText().toString();
        String email= skemail.getText().toString();
        String password= skpassword.getText().toString();
        String cpassword= skcpassword.getText().toString();

        if(name.isEmpty()){
            Toast.makeText(user_registration.this, "Please enter Name", Toast.LENGTH_SHORT).show();
        }
        else if (email.isEmpty()){
            Toast.makeText(user_registration.this, "Please enter Email ID", Toast.LENGTH_SHORT).show();
        }
        else if (password.isEmpty()){
            Toast.makeText(user_registration.this, "Please enter Password", Toast.LENGTH_SHORT).show();
        }
        else if (cpassword.isEmpty()){
            Toast.makeText(user_registration.this, "Please enter confirm Password", Toast.LENGTH_SHORT).show();
        }
        else if (!cpassword.equals(password)){
            Toast.makeText(user_registration.this, "Confirm password does not match", Toast.LENGTH_SHORT).show();
        }
        else{
            result=true;
        }
        return result;
    }
}