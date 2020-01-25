package com.example.urgent_call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivityDriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_driver);
    }

    public void open_signUp_driver(View v)
    {
        Intent i;
        i = new Intent(MainActivityDriver.this,SignUpActivityDriver.class);
        startActivity(i);
    }

    public void open_signIn_driver(View v)
    {
        Intent i;
        i = new Intent(MainActivityDriver.this,SignInActivityDriver.class);
        startActivity(i);
    }
}
