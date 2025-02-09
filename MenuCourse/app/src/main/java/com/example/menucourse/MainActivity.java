package com.example.menucourse;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private TextView courseCodeText, creditsText, courseNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courseCodeText = findViewById(R.id.courseCodeText);
        creditsText = findViewById(R.id.creditsText);
        courseNameText = findViewById(R.id.courseNameText);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registerForContextMenu(courseCodeText);
        registerForContextMenu(creditsText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.info438) {
            displayCourseInfo("Info 438");
            return true;
        } else if (item.getItemId() == R.id.info439) {
            displayCourseInfo("Info 439");
            return true;
        } else if (item.getItemId() == R.id.reset) {
            resetCourseInfo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.courseCodeText) {
            getMenuInflater().inflate(R.menu.menu_course_code, menu);
        } else if (v.getId() == R.id.creditsText) {
            getMenuInflater().inflate(R.menu.menu_credits, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.I2201) {
            displayCourseInfo("I2201");
            return true;
        } else if (item.getItemId() == R.id.I2207) {
            displayCourseInfo("I2207");
            return true;
        } else if (item.getItemId() == R.id.I3302) {
            displayCourseInfo("I3302");
            return true;
        } else if (item.getItemId() == R.id.I3350) {
            displayCourseInfo("I3350");
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private void displayCourseInfo(String courseCode) {
        if (courseCode.equals("I2201")) {
            courseNameText.setText("Course Name: HTML");
            courseCodeText.setText("Course Code: I2201");
            creditsText.setText("Credits: 3 Cts");
        } else if (courseCode.equals("I2207")) {
            courseNameText.setText("Course Name: Réseau");
            courseCodeText.setText("Course Code: I2207");
            creditsText.setText("Credits: 5 Cts");
        } else if (courseCode.equals("I3302")) {
            courseNameText.setText("Course Name: PHP");
            courseCodeText.setText("Course Code: I3302");
            creditsText.setText("Credits: 4 Cts");
        } else if (courseCode.equals("I3350")) {
            courseNameText.setText("Course Name: BDD");
            courseCodeText.setText("Course Code: I3350");
            creditsText.setText("Credits: 4 Cts");
        } else if (courseCode.equals("Info 438")) {
            courseNameText.setText("Course Name: Android, Java");
            courseCodeText.setText("Course Code: Info 438");
            creditsText.setText("Credits: 6 Cts");
        } else if (courseCode.equals("Info 439")) {
            courseNameText.setText("Course Name: Final project");
            courseCodeText.setText("Course Code: Info 439");
            creditsText.setText("Credits: 6 Cts");
        } else {
            resetCourseInfo();
        }
    }

    private void resetCourseInfo() {
        courseNameText.setText("Course Name:");
        courseCodeText.setText("Course Code:");
        creditsText.setText("Credits:");
    }
}
