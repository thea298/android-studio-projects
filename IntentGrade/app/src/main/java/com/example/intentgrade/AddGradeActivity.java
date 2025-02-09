package com.example.intentgrade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddGradeActivity extends Activity {

    private EditText courseEditText, gradeEditText;
    private Button resultButton, cancelButton;
    private TextView greetingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        courseEditText = findViewById(R.id.courseEditText);
        gradeEditText = findViewById(R.id.gradeEditText);
        resultButton = findViewById(R.id.resultButton);
        cancelButton = findViewById(R.id.cancelButton);
        greetingTextView = findViewById(R.id.greetingTextView);

        String userName = getIntent().getStringExtra("userName");
        greetingTextView.setText("Hello " + userName + ", Enter the course and your grade");

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course = courseEditText.getText().toString();
                String grade = gradeEditText.getText().toString();
                Intent resultIntent = new Intent();
                if (!course.isEmpty() && !grade.isEmpty()) {
                    resultIntent.putExtra("course", course);
                    resultIntent.putExtra("grade", grade);
                    setResult(RESULT_OK, resultIntent);
                } else {
                    setResult(RESULT_OK, new Intent());
                }
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });
    }
}
