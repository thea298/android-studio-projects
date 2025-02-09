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

public class ColorCarActivity extends AppCompatActivity {

    private EditText colorEditText;
    private Button addModelButton;
    private String name;

    private final ActivityResultLauncher<Intent> modelCarActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String name = result.getData().getStringExtra("name");
                    String color = result.getData().getStringExtra("color");
                    String model = result.getData().getStringExtra("model");

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("name", name);
                    resultIntent.putExtra("color", color);
                    resultIntent.putExtra("model", model);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_car);

        colorEditText = findViewById(R.id.colorEditText);
        addModelButton = findViewById(R.id.addModelButton);

        name = getIntent().getStringExtra("name");
        TextView instructionTextView = findViewById(R.id.instructionTextView);
        instructionTextView.setText("Enter the color of your car: " + name);

        addModelButton.setOnClickListener(v -> {
            String color = colorEditText.getText().toString();
            if (!color.isEmpty()) {
                Intent intent = new Intent(ColorCarActivity.this, ModelCarActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("color", color);
                modelCarActivityResultLauncher.launch(intent);  // Launch ModelCarActivity using the launcher
            } else {
                Toast.makeText(ColorCarActivity.this, "Please enter the car color", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
