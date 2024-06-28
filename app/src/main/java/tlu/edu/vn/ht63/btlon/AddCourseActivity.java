package tlu.edu.vn.ht63.btlon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddCourseActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText courseNameEdt, courseEndEdt, courseStartEdt, courseDescriptionEdt;
    private Button addCourseBtn, readCourseBtn;
    private DBHandler dbHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Courses Activity
        setContentView(R.layout.layout_addcourse);

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseStartEdt = findViewById(R.id.idEdtCourseStart);
        courseEndEdt = findViewById(R.id.idEdtCourseEnd);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        readCourseBtn = findViewById(R.id.idBtnReadCourse);


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(AddCourseActivity.this);

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String courseName = courseNameEdt.getText().toString();
                String courseEnd = courseEndEdt.getText().toString();
                String courseStart = courseStartEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (courseName.isEmpty() || courseEnd.isEmpty() || courseStart.isEmpty() || courseDescription.isEmpty()) {
                    Toast.makeText(AddCourseActivity.this, "Hãy nhập đầy đủ các mục", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(courseName, courseStart, courseDescription, courseEnd);

                // after adding the data we are displaying a toast message.
                Toast.makeText(AddCourseActivity.this, "Đã thêm khóa học thành công", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseStartEdt.setText("");
                courseEndEdt.setText("");
                courseDescriptionEdt.setText("");
            }
        });

        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(AddCourseActivity.this, HomePageActivity.class);
                startActivity(i);
            }
        });
    }
}