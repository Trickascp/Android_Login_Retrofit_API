package com.example.testauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SuccessLogin extends AppCompatActivity {

    TextView t_nama, t_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_login);

        Intent intent = getIntent();

        String nama = intent.getStringExtra("T_USERNAME");
        String level = intent.getStringExtra("T_LEVEL");

        t_nama = findViewById(R.id.display1);
        t_level = findViewById(R.id.display2);

        t_nama.setText("Welcome "+nama);
        t_level.setText("Level "+level);

    }
}
