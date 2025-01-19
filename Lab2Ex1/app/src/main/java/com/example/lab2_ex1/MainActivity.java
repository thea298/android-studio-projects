package com.example.lab2_ex1;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button equals, show, reset;
    EditText num1, num2;
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

        equals=findViewById(R.id.equals);
        show=findViewById(R.id.show);
        reset=findViewById(R.id.reset);
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        result=findViewById(R.id.result);

        equals.setOnClickListener(v -> {
            String n1=num1.getText().toString();
            String n2=num2.getText().toString();
            if(n1.isEmpty()||n2.isEmpty()){
                new AlertDialog.Builder(this)
                    .setTitle("Error Message")
                    .setMessage("Enter these 2 values")
                    .setPositiveButton("OK", (dialog, which)->dialog.dismiss())
                    .create()
                    .show();
            }
            else{
                double d1=Double.parseDouble(n1);
                double d2=Double.parseDouble(n2);
                double ans=d1+d2;
                result.setText(String.valueOf(ans));
            }
        });

        show.setOnClickListener(v -> {
            String res=result.getText().toString();
            if(res.isEmpty()){
                res="There is no result";
            }
            else{
                res="The result is "+result.getText();
            }
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        });

        reset.setOnClickListener(v -> {
            num1.setText("");
            num2.setText("");
            result.setText("");
        });
    }
}