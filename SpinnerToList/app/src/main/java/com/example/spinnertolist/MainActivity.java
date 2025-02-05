package com.example.spinnertolist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Map<String, List<String>> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);

        courses = new HashMap<>();
        courses.put("L1", Arrays.asList("Info 100", "Info 102", "Info 103"));
        courses.put("L2", Arrays.asList("Info 200", "Info 202", "Info 203"));
        courses.put("L3", Arrays.asList("Info 300", "Info 302", "Info 303"));
        courses.put("M1", Arrays.asList("Info 400", "Info 402", "Info 403"));

        List<String> levels = new ArrayList<>(new TreeSet<>(courses.keySet()));

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, levels);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        updateListView(levels.get(0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLevel = levels.get(position);
                updateListView(selectedLevel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                listView.setAdapter(null);
            }
        });
    }

    private void updateListView(String level) {
        List<String> courseList = courses.get(level);
        if (courseList != null) {
            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
            listView.setAdapter(listAdapter);
        }
    }
}
