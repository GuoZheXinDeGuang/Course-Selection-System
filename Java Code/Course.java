public class Course {
    private String courseName;
    private String courseTime;
    private String courseInstructor;
    private String courseDescription;
    private String prerequisites;
    private int credits;
    private double rating;

    public Course(String courseName, String courseTime, String courseInstructor, String courseDescription, String prerequisites, int credits, double rating) {
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.courseInstructor = courseInstructor;
        this.courseDescription = courseDescription;
        this.prerequisites = prerequisites;
        this.credits = credits;
        this.rating = rating;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public String toString() {
        // This will print the course
        return courseName + " (" + courseInstructor + ")";
    }
}
