package com.example.detailpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selectPreferencesButton = findViewById(R.id.selectPreferencesButton);
        Button showPreferencesButton = findViewById(R.id.showPreferencesButton);
        Button deletePreferencesButton = findViewById(R.id.deletePreferencesButton);

        selectPreferencesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SelectPreferencesActivity.class);
            startActivity(intent);
        });

        showPreferencesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShowPreferencesActivity.class);
            startActivity(intent);
        });

        deletePreferencesButton.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Toast.makeText(this, "All preferences deleted!", Toast.LENGTH_SHORT).show();
        });
    }
}