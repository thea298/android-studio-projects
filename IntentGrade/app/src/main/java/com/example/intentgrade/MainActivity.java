package com.example.intentgrade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Button addGradeButton;
    private TextView resultTextView;

    private final ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String course = data.getStringExtra("course");
                    String grade = data.getStringExtra("grade");

                    if (course != null && grade != null) {
                        resultTextView.setText("Your course is " + course + " and your grade is " + grade);
                    } else {
                        resultTextView.setText("You didn't enter your course and your grade.");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        addGradeButton = findViewById(R.id.addGradeButton);
        resultTextView = findViewById(R.id.resultTextView);

        addGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                if (!name.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, AddGradeActivity.class);
                    intent.putExtra("userName", name);
                    activityResultLauncher.launch(intent);
                } else {
                    resultTextView.setText("Please enter your name.");
                }
            }
        });
    }
}
