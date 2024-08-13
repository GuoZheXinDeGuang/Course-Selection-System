import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RemoveCourseGUI extends JFrame {
    private JComboBox<Course> courseComboBox;
    private JButton removeButton;

    public RemoveCourseGUI() {
        setTitle("Remove Course");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        courseComboBox = new JComboBox<Course>();
        removeButton = new JButton("Remove Course");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(new JLabel("Select Course:"), gbc);
        panel.add(courseComboBox, gbc);
        panel.add(removeButton, gbc);

        add(panel);

        List<Course> allCourses = CourseManagementSystem.getAllCourses();
        for (Course course : allCourses) {
            courseComboBox.addItem(course);
        }

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();
                if (selectedCourse != null) {
                    btn_click_removeCourse(selectedCourse);
                }
            }
        });
    }

    private void btn_click_removeCourse(Course selectedCourse) {
        CourseManagementSystem.removeCourse(selectedCourse);
        JOptionPane.showMessageDialog(this, "Course removed successfully!");
        dispose();
    }

}
