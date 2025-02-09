package com.example.detailpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LockScreenActivity extends AppCompatActivity {

    private Spinner lockScreenSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        lockScreenSpinner = findViewById(R.id.lockScreenSpinner);
        Button saveLockScreenButton = findViewById(R.id.saveLockScreenButton);

        saveLockScreenButton.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LockScreen", lockScreenSpinner.getSelectedItem().toString());
            editor.apply();
            Toast.makeText(this, "Lock Screen preference saved!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}