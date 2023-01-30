package com.example.ict652;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPass extends AppCompatActivity {

    Button openPro;
    EditText confirmPass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass);

        openPro =(Button) findViewById(R.id.submit_password);
        confirmPass = findViewById(R.id.confirmPass);
    }

    public void openPro(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void start(View view){
        String name = confirmPass.getText().toString().trim();
        Toast.makeText(this, "Done update Password",Toast.LENGTH_SHORT).show();
    }
}