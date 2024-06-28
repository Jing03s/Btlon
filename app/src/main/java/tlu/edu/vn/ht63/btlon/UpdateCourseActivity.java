package tlu.edu.vn.ht63.btlon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateCourseActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText courseNameEdt, courseStartEdt, courseEndEdt, courseDescriptionEdt;
    private Button updateCourseBtn, deleteCourseBtn, startCourseBtn;
    private DBHandler dbHandler;
    String courseName, courseStart, courseEnd, courseDesc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseStartEdt = findViewById(R.id.idEdtCourseStart);
        courseEndEdt = findViewById(R.id.idEdtCourseEnd);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);
        startCourseBtn = findViewById(R.id.idBtnStartCourse);

        // on below line we are initializing our dbhandler class.
        dbHandler = new DBHandler(UpdateCourseActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        courseName = getIntent().getStringExtra("name");
        courseStart = getIntent().getStringExtra("start");
        courseEnd = getIntent().getStringExtra("end");
        courseDesc = getIntent().getStringExtra("description");

        // setting data to edit text
        // of our update activity.
        courseNameEdt.setText(courseName);
        courseStartEdt.setText(courseStart);
        courseEndEdt.setText(courseEnd);
        courseDescriptionEdt.setText(courseDesc);

        // adding on click listener to our update course button.
        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateCourse(courseName, courseNameEdt.getText().toString(), courseEndEdt.getText().toString(), courseStartEdt.getText().toString(), courseDescriptionEdt.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateCourseActivity.this, "Cập nhật khóa học thành công", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateCourseActivity.this, HomePageActivity.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our course.
        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteCourse(courseName);
                Toast.makeText(UpdateCourseActivity.this, "Khóa học đã được xóa", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateCourseActivity.this, HomePageActivity.class);
                startActivity(i);
            }
        });

        // adding on click listener for start button to start our course.
        startCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launching course activity.
                Intent i = new Intent(UpdateCourseActivity.this, StartActivity.class);
                startActivity(i);
            }
        });
    }
}
