package com.example.ahabdelhak.otocabtask.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahabdelhak.otocabtask.Activities.MapsActivity;
import com.example.ahabdelhak.otocabtask.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button buttonLogin;
    private EditText editTextEmail, editTextPassword;
    private String email, password;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        verifyDataEntered();
    }

    //Verification
    public void DataValidation(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(email.isEmpty()){
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
            //Login and Upload data
            LoginData();
        }
    }
    //LoginButton
    private void verifyDataEntered() {
        buttonLogin = findViewById(R.id.button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextEmail = findViewById(R.id.ededmail);
                editTextPassword = findViewById(R.id.edPass);

                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                DataValidation();
            }
        });
    }
    //checkDataLogin
    private void LoginData() {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (task.isSuccessful()){
                    mDialog.dismiss();
                    updateUI(user);
                }else{
                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //LoginSuccess
    private void updateUI(FirebaseUser user) {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(this, MapsActivity.class);
        startActivity(in);
    }


}



