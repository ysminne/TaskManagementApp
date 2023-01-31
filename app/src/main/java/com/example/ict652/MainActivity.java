package com.example.ict652;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button openChange1;
    Button openEdit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // openChange1 = (Button) findViewById(R.id.change);
       // openEdit1 = (Button) findViewById(R.id.edit);

    }

    public void openChange1(View view){
        Intent intent = new Intent(this, EditPass.class);
        startActivity(intent);
    }

    public void openEdit1(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

}