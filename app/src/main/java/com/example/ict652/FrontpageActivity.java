package com.example.ict652;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrontpageActivity extends AppCompatActivity {

    Button opensignup1;
    Button openLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);

        opensignup1 = (Button) findViewById(R.id.signup1);
       // buttonLogin = (Button) findViewById(R.id.buttonLogin);
        openLog = (Button) findViewById(R.id.logmasuk);
    }

    public void opensignup1(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openLog(View view){
        Intent intent = new Intent( this, LoginActivity.class);
        startActivity(intent);
    }


}