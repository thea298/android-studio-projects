package com.example.intentcar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Button addColorButton;
    private TextView carDetailsTextView;

    private final ActivityResultLauncher<Intent> colorCarActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String name = result.getData().getStringExtra("name");
                    String color = result.getData().getStringExtra("color");
                    String model = result.getData().getStringExtra("model");

                    carDetailsTextView.setText("Your car is " + name + ", the color is " + color + ", the model is " + model);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        addColorButton = findViewById(R.id.addColorButton);
        carDetailsTextView = findViewById(R.id.carDetailsTextView);

        addColorButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, ColorCarActivity.class);
                intent.putExtra("name", name);
                colorCarActivityResultLauncher.launch(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
