package com.example.detailpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_preferences);

        TextView preferencesTextView = findViewById(R.id.preferencesTextView);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String title = sharedPreferences.getString("Title", "No Title");
        String lockScreen = sharedPreferences.getString("LockScreen", "No Lock Screen Selected");
        boolean isLanguageActivated = sharedPreferences.getBoolean("IsLanguageActivated", false);
        String languages = sharedPreferences.getString("Languages", "No Languages Selected");

        String preferences = "Title of Preferences: " + title + "\nLock Screen: " + lockScreen +
                "\nSelect Language: " + isLanguageActivated + "\nList of Language: " + languages;
        preferencesTextView.setText(preferences);
    }
}
