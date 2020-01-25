package com.example.urgent_call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void open_user(View view)
    {
        Intent i =new Intent(Start.this,MainActivity.class);
        startActivity(i);
    }

    public void open_driver(View view)
    {
        Intent i =new Intent(Start.this,MainActivityDriver.class);
        startActivity(i);
        //finish();
    }
}
