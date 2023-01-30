package com.example.ict652;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfilePictureActivity extends AppCompatActivity {

    Button openEditor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);

        openEditor1 = (Button) findViewById(R.id.close1);
    }

    public void openEditor1(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }
}