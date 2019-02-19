package com.example.fundsdonation.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fundsdonation.R;
import com.example.fundsdonation.model.MyDB;
import com.example.fundsdonation.model.User;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etMobile,etEmail,etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
       etName = (EditText) findViewById(R.id.name);
       etMobile=(EditText) findViewById(R.id.mobile);
       etEmail= (EditText)findViewById(R.id.email);
       etPwd = (EditText)findViewById(R.id.password);
       ((Button)findViewById(R.id.email_sign_in_button)).setOnClickListener(this);
        ((TextView)findViewById(R.id.tvLogin)).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
       switch (v.getId())
       {
           case R.id.email_sign_in_button:
               signupUser();
               break;
           case R.id.tvLogin:
               navigateToLogin();
               break;
       }
    }

    private void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void signupUser() {
        String name = etName.getText().toString();
        String mobile = etMobile.getText().toString();
        String email =etEmail.getText().toString();
        String pwd= etPwd.getText().toString();
        boolean addUser = new MyDB(this).addUser(new User(name, mobile, email, pwd));
        if(!addUser)
            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
