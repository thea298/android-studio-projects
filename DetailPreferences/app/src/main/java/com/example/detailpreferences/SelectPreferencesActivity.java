package com.example.detailpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectPreferencesActivity extends AppCompatActivity {

    private EditText titleEditText;
    private CheckBox activateLanguageCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_preferences);

        titleEditText = findViewById(R.id.titleEditText);
        activateLanguageCheckBox = findViewById(R.id.activateLanguageCheckBox);

        Button lockScreenButton = findViewById(R.id.lockScreenButton);
        Button languageButton = findViewById(R.id.languageButton);
        Button savePreferencesButton = findViewById(R.id.savePreferencesButton);

        lockScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(SelectPreferencesActivity.this, LockScreenActivity.class);
            startActivity(intent);
        });

        languageButton.setOnClickListener(v -> {
            if (activateLanguageCheckBox.isChecked()) {
                Intent intent = new Intent(SelectPreferencesActivity.this, LanguageActivity.class);
                startActivity(intent);
            }
        });

        savePreferencesButton.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Title", titleEditText.getText().toString());
            editor.putBoolean("IsLanguageActivated", activateLanguageCheckBox.isChecked());
            editor.apply();
            Toast.makeText(this, "Preferences saved!", Toast.LENGTH_SHORT).show();
        });
    }
}