package com.example.autocompletecourse;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView edit;
    TextView selection;
    private static final String[] items = {
            "Info 400", "Info 402", "Info 403", "Info 407", "Info 409",
            "Info 430", "Info 433", "Info 439", "Application Web",
            "Application Java", "Programmation Impérative I",
            "Programmation Impérative II", "XML", "PHP", "HTML", "XHTML"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = findViewById(R.id.edit);
        selection = findViewById(R.id.selection);

        edit.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items));
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                selection.setText(edit.getText());
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
