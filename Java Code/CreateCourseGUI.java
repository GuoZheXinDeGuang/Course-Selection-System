import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCourseGUI extends JFrame {
    private JTextField courseNameField;
    private JTextField courseTimeField;
    private JTextField courseInstructorField;
    private JTextField courseDescriptionField;
    private JTextField coursePrerequisitesField;
    private JSpinner courseCreditsSpinner;
    private JTextField courseCategoryField;
    private JButton createButton;

    public CreateCourseGUI() {
        setTitle("Create Course");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        courseNameField = new JTextField(20);
        courseTimeField = new JTextField(20);
        courseInstructorField = new JTextField(20);
        courseDescriptionField = new JTextField(20);
        coursePrerequisitesField = new JTextField(20);
        courseCreditsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        courseCategoryField = new JTextField(20);
        createButton = new JButton("Create Course");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Course Name:"), gbc);
        gbc.gridx = 1;
        panel.add(courseNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Course Time:"), gbc);
        gbc.gridx = 1;
        panel.add(courseTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Instructor:"), gbc);
        gbc.gridx = 1;
        panel.add(courseInstructorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        panel.add(courseDescriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Prerequisites:"), gbc);
        gbc.gridx = 1;
        panel.add(coursePrerequisitesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Credits:"), gbc);
        gbc.gridx = 1;
        panel.add(courseCreditsSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        panel.add(courseCategoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(createButton, gbc);

        add(panel);

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = courseNameField.getText();
                String time = courseTimeField.getText();
                String instructor = courseInstructorField.getText();
                String description = courseDescriptionField.getText();
                String prerequisites = coursePrerequisitesField.getText();
                int credits = (Integer) courseCreditsSpinner.getValue();
                String category = courseCategoryField.getText();
                btn_click_createCourse(name, time, instructor, description, prerequisites, credits, category);
            }
        });
    }

    private void btn_click_createCourse(String name, String time, String instructor, String description, String prerequisites, int credits, String category) {
        Course course = new Course(name, time, instructor, description, prerequisites, credits, -1, category);
        CourseManagementSystem.addCourse(course);
        JOptionPane.showMessageDialog(this, "Course created successfully!");
        dispose();
    }

}
