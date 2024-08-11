import java.util.List;
import java.util.ArrayList;

public class Student extends User {
    private List<Course> completedCourses;

    public Student(String username, String password) {
        super(username, password);
        this.completedCourses = new ArrayList<Course>();
    }

    public void addCompletedCourse(Course course) {
        completedCourses.add(course);
    }

    public List<Course> getCompletedCourses() {
        return completedCourses;
    }

    public boolean hasCompletedCourse(String courseName) {
        for (Course course : completedCourses) {
            if (course.getCourseName().equals(courseName)) {
                return true;
            }
        }
        return false;
    }
}

