package com.example.intentcar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ModelCarActivity extends AppCompatActivity {

    private EditText modelEditText;
    private Button okButton;
    private String name;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_car);

        modelEditText = findViewById(R.id.modelEditText);
        okButton = findViewById(R.id.okButton);

        name = getIntent().getStringExtra("name");
        color = getIntent().getStringExtra("color");

        TextView instructionTextView = findViewById(R.id.instructionTextView);
        instructionTextView.setText("Enter the model of your " + color + " " + name + " car");

        okButton.setOnClickListener(v -> {
            String model = modelEditText.getText().toString();
            if (!model.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("color", color);
                resultIntent.putExtra("model", model);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(ModelCarActivity.this, "Please enter the car model", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
