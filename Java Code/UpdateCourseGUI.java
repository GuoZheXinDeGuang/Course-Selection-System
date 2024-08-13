import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateCourseGUI extends JFrame {
    private JComboBox<Course> courseComboBox;
    private JTextField courseNameField;
    private JTextField courseTimeField;
    private JTextField courseInstructorField;
    private JTextField courseDescriptionField;
    private JTextField coursePrerequisitesField;
    private JSpinner courseCreditsSpinner;
    private JTextField courseCategoryField;
    private JButton updateButton;

    public UpdateCourseGUI() {
        setTitle("Update Course");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        courseComboBox = new JComboBox<Course>();
        courseNameField = new JTextField(20);
        courseTimeField = new JTextField(20);
        courseInstructorField = new JTextField(20);
        courseDescriptionField = new JTextField(20);
        coursePrerequisitesField = new JTextField(20);
        courseCreditsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        courseCategoryField = new JTextField(20);
        updateButton = new JButton("Update Course");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Select Course:"), gbc);
        gbc.gridx = 1;
        panel.add(courseComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Course Name:"), gbc);
        gbc.gridx = 1;
        panel.add(courseNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Course Time:"), gbc);
        gbc.gridx = 1;
        panel.add(courseTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Instructor:"), gbc);
        gbc.gridx = 1;
        panel.add(courseInstructorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        panel.add(courseDescriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Prerequisites:"), gbc);
        gbc.gridx = 1;
        panel.add(coursePrerequisitesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Credits:"), gbc);
        gbc.gridx = 1;
        panel.add(courseCreditsSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        panel.add(courseCategoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(updateButton, gbc);

        add(panel);

        // Load existing courses into the combo box
        List<Course> allCourses = CourseManagementSystem.getAllCourses();
        for (Course course : allCourses) {
            courseComboBox.addItem(course);
        }

        courseComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();
                loadCourseDetails(selectedCourse);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();
                if (selectedCourse != null) {
                    String name = courseNameField.getText();
                    String time = courseTimeField.getText();
                    String instructor = courseInstructorField.getText();
                    String description = courseDescriptionField.getText();
                    String prerequisites = coursePrerequisitesField.getText();
                    int credits = ((Integer) courseCreditsSpinner.getValue()).intValue();
                    String category = courseCategoryField.getText();
                    btn_update_clk(selectedCourse, name, time, instructor, description, prerequisites, credits, category);
                }
            }
        });
    }

    private void loadCourseDetails(Course selectedCourse) {
        if (selectedCourse != null) {
            courseNameField.setText(selectedCourse.getCourseName());
            courseTimeField.setText(selectedCourse.getCourseTime());
            courseInstructorField.setText(selectedCourse.getCourseInstructor());
            courseDescriptionField.setText(selectedCourse.getCourseDescription());
            coursePrerequisitesField.setText(selectedCourse.getPrerequisites());
            courseCreditsSpinner.setValue(selectedCourse.getCredits());
            courseCategoryField.setText(selectedCourse.getCategory());
        }
    }

    private void btn_update_clk(Course course, String name, String time, String instructor, String description,
            String prerequisites, int credits, String category) {
        CourseManagementSystem.updateCourse(course, name, time, instructor, description, prerequisites, credits, category);
        JOptionPane.showMessageDialog(this, "Course updated successfully!");
        dispose();
    }
}
