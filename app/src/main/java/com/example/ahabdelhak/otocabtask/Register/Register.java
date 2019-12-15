package com.example.ahabdelhak.otocabtask.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ahabdelhak.otocabtask.Activities.MapsActivity;
import com.example.ahabdelhak.otocabtask.Model.Users;
import com.example.ahabdelhak.otocabtask.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    private ProgressDialog mDialog;
    private Button btnRegister;
    private EditText et_name, et_email, et_password;
    TextView tv_alreadyRegistered;
    private String email, password, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Display Gui
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mDialog = new ProgressDialog(this);
        //Create News users in database
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("NewUsers");

        ButtonLogin();
        ButtonRegister();

    }

    //verification and upload data
    public void DataValidation(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(name.isEmpty()){
            Toast.makeText(this, "Kindly enter name !", Toast.LENGTH_SHORT).show();
            mDialog.dismiss();
        }
        else if(email.isEmpty()){
            Toast.makeText(this, "Kindly enter email !", Toast.LENGTH_SHORT).show();
            mDialog.dismiss();
        }
        else if(!(email.matches(emailPattern))){
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            mDialog.dismiss();
        }
        else if(password.isEmpty()){
            Toast.makeText(this, "Kindly enter password !", Toast.LENGTH_SHORT).show();
            mDialog.dismiss();
        }
        else if(!(password.length() >= 6)){
            Toast.makeText(getApplicationContext(), "password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            mDialog.dismiss();
        }
        else{
            UploadRegiteredData();
        }
    }
    //button Login
    private void ButtonLogin() {
        tv_alreadyRegistered=findViewById(R.id.tv_alreadyRegistered);
        tv_alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    //Button To Register
    private void ButtonRegister() {
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_name = findViewById(R.id.et_name);
                et_email = findViewById(R.id.et_email);
                et_password = findViewById(R.id.et_password);

                name = et_name.getText().toString().trim();
                email = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();


                Registrationrsult();
            }
        });
    }
    //Upload Data To Server
    public void UploadRegiteredData(){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            OnAuth(task.getResult().getUser());
                            Intent i = new Intent(Register.this, MapsActivity.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(Register.this, "Registration Done.",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        } else{
                            Toast.makeText(Register.this, "Registration Failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //RegisterData
    private void Registrationrsult() {
        mDialog.setMessage("Registration Process, Please Wait...");
        mDialog.setIndeterminate(true);
        mDialog.show();

        DataValidation();
    }

    private void OnAuth(FirebaseUser user) {
        createNewUser(user.getUid());
    }
    private void createNewUser(String uid) {
        Users user = BuildNewUser();
        mDatabase.child(uid).setValue(user);
    }
    private Users BuildNewUser(){
        return new Users(
                name,
                email,
                password,
                new Date().getTime()
        );
    }
}