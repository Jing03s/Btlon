package tlu.edu.vn.ht63.btlon;

public class CourseModal {

    private String courseName;
    private String courseStart;
    private String courseEnd;
    private String courseDescription;
    private int id;

    // creating getter and setter methods
    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseStart()
    {
        return courseStart;
    }

    public void setCourseStart(String courseStart)
    {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() { return courseEnd; }

    public void setCourseEnd(String courseEnd)
    {
        this.courseEnd = courseEnd;
    }

    public String getCourseDescription()
    {
        return courseDescription;
    }

    public void
    setCourseDescription(String courseDescription)
    {
        this.courseDescription = courseDescription;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    // constructor
    public CourseModal(String courseName,
                       String courseStart,
                       String courseEnd,
                       String courseDescription)
    {
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseDescription = courseDescription;
    }
}
