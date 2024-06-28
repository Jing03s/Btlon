package tlu.edu.vn.ht63.btlon;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "coursedb";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "mycourses";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String START_COL = "start";

    private static final String DESCRIPTION_COL = "description";

    private static final String END_COL = "timeend";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + START_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + END_COL + " TEXT)";
        db.execSQL(query);
    }

    public boolean addAlarm() {
        Calendar cal = Calendar.getInstance();
        int start = cal.get(Calendar.HOUR);
        int timeend = cal.get(Calendar.HOUR);
        ContentValues values = new ContentValues();
        values.put(START_COL, start);
        values.put(END_COL, timeend);
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public void addNewCourse(String courseName, String courseStart, String courseEnd, String courseDescription) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, courseName);
        values.put(START_COL, courseStart);
        values.put(END_COL, courseEnd);
        values.put(DESCRIPTION_COL, courseDescription);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<CourseModal> readCourses() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                        cursorCourses.getString(4),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }

    public void updateCourse(String originalCourseName, String courseName, String courseStart, String courseEnd, String courseDescription) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, courseName);
        values.put(START_COL, courseStart);
        values.put(END_COL, courseEnd);
        values.put(DESCRIPTION_COL, courseDescription);

        db.update(TABLE_NAME, values, "name=?", new String[]{originalCourseName});
        db.close();
    }

    public void deleteCourse(String courseName) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}