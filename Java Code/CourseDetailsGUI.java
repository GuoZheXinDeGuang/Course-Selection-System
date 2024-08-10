import javax.swing.*;
import java.awt.*;

public class CourseDetailsGUI extends JFrame {
    private final Course course;
    private JLabel courseNameLabel;
    private JLabel courseTimeLabel;
    private JLabel courseInstructorLabel;
    private JLabel courseDescriptionLabel;
    private JLabel courseCreditsLabel;
    private JLabel coursePrerequisitesLabel;
    private JLabel courseRatingLabel;

    public CourseDetailsGUI(Course course) {
        this.course = course;

        setTitle("Course Details");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        courseNameLabel = new JLabel("Name: " + course.getCourseName());
        courseTimeLabel = new JLabel("Time: " + course.getCourseTime());
        courseInstructorLabel = new JLabel("Instructor: " + course.getCourseInstructor());
        courseDescriptionLabel = new JLabel("<html>Description: " + course.getCourseDescription() + "</html>");
        courseCreditsLabel = new JLabel("Credits: " + course.getCredits());
        coursePrerequisitesLabel = new JLabel("Prerequisites: " + course.getPrerequisites());
        courseRatingLabel = new JLabel("Rating: " + (course.getRating() == -1 ? "N/A" : course.getRating()));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(courseNameLabel);
        panel.add(courseTimeLabel);
        panel.add(courseInstructorLabel);
        panel.add(courseDescriptionLabel);
        panel.add(courseCreditsLabel);
        panel.add(coursePrerequisitesLabel);
        panel.add(courseRatingLabel);

        add(panel);
    }
}
