package com.example.converter;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button convert;
    EditText value;
    RadioButton temp, curr, radio1, radio2;
    RadioGroup temp_curr, radio;
    TextView result;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        convert = findViewById(R.id.convert);
        value = findViewById(R.id.valueInput);
        temp = findViewById(R.id.temperature);
        curr = findViewById(R.id.currency);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        temp_curr = findViewById(R.id.temp_curr);
        radio = findViewById(R.id.radio);
        result = findViewById(R.id.result);

        temp.setChecked(true);
        radio1.setText("C-F");
        radio2.setText("F-C");
        radio1.setChecked(true);

        temp.setOnClickListener(v -> {
            radio1.setText("C-F");
            radio2.setText("F-C");
            radio1.setChecked(true);
        });

        curr.setOnClickListener(v -> {
            radio1.setText("Dollar-Euro");
            radio2.setText("Euro-Dollar");
            radio1.setChecked(true);
        });

        convert.setOnClickListener(v -> {
            String inputValue = value.getText().toString().trim();
            if (inputValue.isEmpty()) {
                result.setText("Result: Please enter a value");
                return;
            }
            double valueDouble;
            try {
                valueDouble = Double.parseDouble(inputValue);
            } catch (NumberFormatException e) {
                result.setText("Result: Invalid number");
                return;
            }
            double convertedValue;
            String ans;

            if (temp.isChecked()) {
                if (radio1.isChecked()) {
                    convertedValue = (9.0 / 5.0) * valueDouble + 32;
                    ans = String.format(Locale.US, "Result: %.2f °F", convertedValue);
                } else {
                    convertedValue = (valueDouble - 32) * (5.0 / 9.0);
                    ans = String.format(Locale.US, "Result: %.2f °C", convertedValue);
                }
            } else if (curr.isChecked()) {
                if (radio1.isChecked()) {
                    convertedValue = 0.9 * valueDouble;
                    ans = String.format(Locale.US, "Result: %.2f €", convertedValue);
                } else {
                    convertedValue = 1.05 * valueDouble;
                    ans = String.format(Locale.US, "Result: %.2f $", convertedValue);
                }
            } else {
                ans = "Result: Please select a conversion type";
            }
            result.setText(ans);
        });
    }
}