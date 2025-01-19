package com.example.lab2_ex2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button hide, show, disable, green, reset, close;
    EditText disableMe;
    TextView hideMe, colorMe;

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

        hide=findViewById(R.id.hideBtn);
        show=findViewById(R.id.showBtn);
        disable=findViewById(R.id.disableBtn);
        green=findViewById(R.id.greenBtn);
        reset=findViewById(R.id.resetBtn);
        close=findViewById(R.id.closeBtn);
        disableMe=findViewById(R.id.disableMe);
        hideMe=findViewById(R.id.hideMe);
        colorMe=findViewById(R.id.colorMe);

        hide.setOnClickListener(v -> hideMe.setVisibility(TextView.GONE));

        show.setOnClickListener(v -> hideMe.setVisibility(TextView.VISIBLE));

        disable.setOnClickListener(v -> {
            if(disableMe.isEnabled()){
                disableMe.setEnabled(false);
                disableMe.setText("Enable Me");
                disable.setText("Enable");
            }
            else{
                disableMe.setEnabled(true);
                disableMe.setText("Disable Me");
                disable.setText("Disable");
            }
        });

        green.setOnClickListener(v -> {
            String btnText=green.getText().toString();
            switch(btnText){
                case "Green":
                    colorMe.setBackgroundColor(Color.GREEN);
                    green.setText("Red");
                    break;
                case "Red":
                    colorMe.setBackgroundColor(Color.RED);
                    green.setText("Blue");
                    break;
                case "Blue":
                    colorMe.setBackgroundColor(Color.BLUE);
                    green.setText("Green");
                    break;
            }
        });

        reset.setOnClickListener(v -> {
            hideMe.setVisibility(TextView.VISIBLE);
            disableMe.setEnabled(true);
            disableMe.setText("Disable Me");
            disable.setText("Disable");
            colorMe.setBackgroundColor(Color.TRANSPARENT);
            green.setText("Green");
        });

        close.setOnClickListener(v -> {
            Toast.makeText(this, "Application Closing", Toast.LENGTH_SHORT).show();
            finish();
        });

    }
}