package com.example.simplepreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SimplePreferences";
    private SharedPreferences sharedPreferences;

    private TextView nameTextView;
    private TextView musicTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

\        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        nameTextView = findViewById(R.id.nameTextView);
        musicTextView = findViewById(R.id.musicTextView);

        loadPreferences();
    }

    private void loadPreferences() {
        String name = sharedPreferences.getString("name", "Not set");
        boolean music = sharedPreferences.getBoolean("music", false);

        if (name != null) {
            nameTextView.setText("Name: " + name);
        } else {
            nameTextView.setText("Name: Not set");
        }

        if (music) {
            musicTextView.setText("Music: Yes");
        } else {
            musicTextView.setText("Music: No");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.select_preferences) {
            showPreferencesDialog();
            return true;
        } else if (item.getItemId() == R.id.delete_preferences) {
            deletePreferences();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showPreferencesDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_preferences, null);
        EditText nameEditText = dialogView.findViewById(R.id.nameEditText);
        CheckBox musicCheckBox = dialogView.findViewById(R.id.musicCheckBox);

        String name = sharedPreferences.getString("name", "");
        if (name != null) {
            nameEditText.setText(name);
        }

        boolean music = sharedPreferences.getBoolean("music", false);
        if (music) {
            musicCheckBox.setChecked(true);
        } else {
            musicCheckBox.setChecked(false);
        }

        new AlertDialog.Builder(this)
                .setTitle("Select Preferences")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newName = nameEditText.getText().toString();
                    boolean newMusic = musicCheckBox.isChecked();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", newName);
                    editor.putBoolean("music", newMusic);
                    editor.apply();

                    loadPreferences();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deletePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        loadPreferences();
        Toast.makeText(this, "Preferences deleted", Toast.LENGTH_SHORT).show();
    }
}
