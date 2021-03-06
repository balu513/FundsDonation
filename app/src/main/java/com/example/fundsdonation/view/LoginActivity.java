package com.example.fundsdonation.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fundsdonation.R;
import com.example.fundsdonation.model.MyDB;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener{

    EditText etEmail, etPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.email);
        etPwd = (EditText)findViewById(R.id.password);

        ((Button)findViewById(R.id.email_sign_in_button)).setOnClickListener(this);
        ((TextView)findViewById(R.id.tv_register)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId())
         {
             case R.id.email_sign_in_button:
                 login();
                 break;
             case R.id.tv_register:
                 startActivity(new Intent(this, SignupActivity.class));
                 break;
         }
    }

    private void login() {

        String emmail = etEmail.getText().toString();
        String pwd = etPwd.getText().toString();

        boolean validUser = new MyDB(this).isValidUser(emmail, pwd);

        if(!validUser)
            Toast.makeText(this,"Invalid Credentials", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this,"Logged In Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,PaymentActivity.class));
        }

    }
}
