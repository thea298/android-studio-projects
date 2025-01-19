package com.example.idealweight;

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

    Button calculate;
    EditText height;
    RadioButton male;
    RadioButton female;
    RadioGroup gender;
    TextView result;

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
        calculate=findViewById(R.id.calculate);
        height=findViewById(R.id.heightInput);
        result=findViewById(R.id.resultDisplay);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        gender=findViewById(R.id.gender);
        calculate.setOnClickListener(v -> {
            String tmp=height.getText().toString();
            float heightFloat=Float.parseFloat(tmp);
            float idealWeight=0;
            if(heightFloat!=0){
                if(male.isChecked()){
                    idealWeight=heightFloat-100-((heightFloat-150)/4);
                }
                else if(female.isChecked()){
                    idealWeight=heightFloat-100-((heightFloat-150)/2);
                }
            }
            String ans=String.format(Locale.US, "Your ideal weight: %.2f", idealWeight);
            result.setText(ans);
        });
    }
}