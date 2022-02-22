package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.TypefaceProvider;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "Account";
    private static final String PREF_LOGIN = "Login";
    private static final String PREF_PASSWORD = "Password";
    SharedPreferences settings;

    String login;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        TypefaceProvider.registerDefaultIconSets();
        BootstrapEditText eLogin = findViewById(R.id.editLogin);
        BootstrapEditText ePassword = findViewById(R.id.editPassword);
        BootstrapButton signupBtn = findViewById(R.id.signupButton);
        BootstrapButton signinBtn = findViewById(R.id.signinButton);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = eLogin.getText().toString();
                password = ePassword.getText().toString();
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putString(PREF_LOGIN,login);
                prefEditor.putString(PREF_PASSWORD,password);
                prefEditor.apply();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    login = eLogin.getText().toString();
                    password = ePassword.getText().toString();
                    if(login.equals(settings.getString(PREF_LOGIN,"Undefined")) && password.equals(settings.getString(PREF_PASSWORD, "Undefined"))){
                        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Введены некоректные данные", Toast.LENGTH_LONG);
                    }
            }
        });
    }
}