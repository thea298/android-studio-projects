package com.example.bmi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
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
    Button reset;
    CheckBox inchPound;
    EditText weight;
    EditText height;
    RadioButton meterBtn;
    RadioGroup group;
    TextView resultDisplay;

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
        calculate=findViewById(R.id.calculate);
        reset=findViewById(R.id.reset);
        inchPound=findViewById(R.id.poundInch);
        weight=findViewById(R.id.weightInput);
        height=findViewById(R.id.heightInput);
        meterBtn=findViewById(R.id.meterBtn);
        group=findViewById(R.id.meterCM);
        resultDisplay=findViewById(R.id.resultDisplay);
        calculate.setOnClickListener(v -> {
            String tmp=weight.getText().toString();
            float floatWeight=Float.parseFloat(tmp);
            String tmp1=height.getText().toString();
            float floatHeight=Float.parseFloat(tmp1);
            float bmi = 0;
            if(floatWeight==0||floatHeight==0){
                resultDisplay.setText("Your measure is invalid!");
            }
            else{
                if(inchPound.isChecked()){
                    group.clearCheck();
                    bmi = floatWeight * 703 / (floatHeight * floatHeight);
                }
                else if (meterBtn.isChecked()){
                    bmi = floatWeight / (floatHeight * floatHeight);
                }
                else {
                    floatHeight/=100;
                    bmi = floatWeight / (floatHeight * floatHeight);
                }
            }
            String ans=String.format(Locale.US, "Your BMI is: %.2f", bmi);
            if(bmi<18.5){
                ans+="\nBMI category: Underweight";
            }
            else if(bmi>=18.5&&bmi<25){
                ans+="\nBMI category: Normal";
            }
            else if(bmi>=25&&bmi<30){
                ans+="\nBMI category: Overweight";
            }
            else if(bmi>=30){
                ans+="\nBMI category: Obese";
            }
            resultDisplay.setText(ans);
        });
        reset.setOnClickListener(v -> {
            weight.setText("");
            height.setText("");
            inchPound.setChecked(false);
            meterBtn.setChecked(true);
            resultDisplay.setText("You must click on the \\\"Calculate BMI\\\" button to get a result");
        });
    }

}