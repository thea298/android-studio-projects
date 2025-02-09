package com.example.detailpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LanguageActivity extends AppCompatActivity {

    private CheckBox arabicCheckBox, englishCheckBox, frenchCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        arabicCheckBox = findViewById(R.id.arabicCheckBox);
        englishCheckBox = findViewById(R.id.englishCheckBox);
        frenchCheckBox = findViewById(R.id.frenchCheckBox);

        Button saveLanguageButton = findViewById(R.id.saveLanguageButton);

        saveLanguageButton.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            StringBuilder selectedLanguages = new StringBuilder();
            if (arabicCheckBox.isChecked()) selectedLanguages.append("ARA,");
            if (englishCheckBox.isChecked()) selectedLanguages.append("ENG,");
            if (frenchCheckBox.isChecked()) selectedLanguages.append("FRE,");

            if (selectedLanguages.length() > 0) {
                selectedLanguages.setLength(selectedLanguages.length() - 1);
            }

            editor.putString("Languages", selectedLanguages.toString());
            editor.apply();

            Toast.makeText(this, "Language preferences saved!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}