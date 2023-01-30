package com.example.ict652;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProjectActivity extends AppCompatActivity implements View.OnClickListener {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        b = findViewById(R.id.toast);

        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Toast.makeText(ProjectActivity.this, "Project created", Toast.LENGTH_SHORT ).show();
    }

}