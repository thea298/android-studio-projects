package com.example.generallistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private EditText words;
    private final List<String> wordList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        words = findViewById(R.id.words);
        Button enter = findViewById(R.id.enter);
        Button show = findViewById(R.id.show);
        ListView view = findViewById(R.id.view);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wordList);

        enter.setOnClickListener(v -> {
            String word = words.getText().toString().trim();

            if (word.isEmpty()) {
                Toast.makeText(MainActivity.this, "The EditText is empty", Toast.LENGTH_SHORT).show();
            } else {
                wordList.add(word);
                adapter.notifyDataSetChanged();
                words.setText("");
            }
        });

        show.setOnClickListener(v -> {
            if (wordList.isEmpty()) {
                Toast.makeText(MainActivity.this, "The List is empty", Toast.LENGTH_SHORT).show();
            }else{
                view.setAdapter(adapter);
            }
        });
    }
}