import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RateCourseGUI extends JFrame {
    private JComboBox<Course> courseComboBox;
    private JSpinner ratingSpinner;
    private JButton rateButton;

    public RateCourseGUI() {
        setTitle("Rate Courses");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        courseComboBox = new JComboBox<Course>();
        ratingSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        rateButton = new JButton("Rate");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(new JLabel("Select Course:"), gbc);
        panel.add(courseComboBox, gbc);
        panel.add(new JLabel("Rate (1-5):"), gbc);
        panel.add(ratingSpinner, gbc);
        panel.add(rateButton, gbc);

        add(panel);

        rateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();
                double rating = (Integer) ratingSpinner.getValue();
                rateSelectedCourse(selectedCourse,rating);
            }
        });

        loadCompletedCourses();
    }

    private void loadCompletedCourses() {
        if (CourseManagementSystem.getLoggedInUser() instanceof Student) {
            Student student = (Student) CourseManagementSystem.getLoggedInUser();
            loadComboBox(student);
        }
    }

    private void loadComboBox(Student student) {
        for (Course course : student.getCompletedCourses()) {
            courseComboBox.addItem(course);
        }
    }

    private void rateSelectedCourse(Course selectedCourse,double rating) {
        if (selectedCourse != null) {
            CourseManagementSystem.rateCourse(selectedCourse, rating);
            JOptionPane.showMessageDialog(this, "Course rated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to rate.");
        }
    }
}
