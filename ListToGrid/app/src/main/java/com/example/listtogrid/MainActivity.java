package com.example.listtogrid;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnShowSelection;
    GridView gridView;
    ArrayList<String> itemList;
    ArrayList<String> selectedItems;
    ArrayAdapter<String> listAdapter;
    ArrayAdapter<String> gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnShowSelection = findViewById(R.id.btnShowSelection);
        gridView = findViewById(R.id.gridView);

        // Sample data for ListView
        itemList = new ArrayList<>();
        selectedItems = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            itemList.add("Item " + i);
        }

        // Adapter for ListView
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, itemList);
        listView.setAdapter(listAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Adapter for GridView
        gridAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedItems);
        gridView.setAdapter(gridAdapter);

        // Button click event
        btnShowSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItems.clear();
                for (int i = 0; i < listView.getCount(); i++) {
                    if (listView.isItemChecked(i)) {
                        selectedItems.add(itemList.get(i));
                    }
                }

                if (selectedItems.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No items selected", Toast.LENGTH_SHORT).show();
                }

                // Update GridView
                gridAdapter.notifyDataSetChanged();
            }
        });

        // ListView item click event (optional feedback)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = itemList.get(position);
                Toast.makeText(MainActivity.this, selectedItem + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
