import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseCategoryGUI extends JFrame {
    private JList<Course> courseList;
    private DefaultListModel<Course> listModel;
    private JButton viewDetailsButton;
    private JButton addCourseButton;
    private String category;

    public CourseCategoryGUI(String category) {
        this.category = category;

        setTitle(category + " Courses");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<Course>();
        courseList = new JList<Course>(listModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(courseList);

        viewDetailsButton = new JButton("View Details");
        addCourseButton = new JButton("Add Course");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(addCourseButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        loadCourses();

        viewDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = courseList.getSelectedValue();
                btn_click_viewDetails(selectedCourse);
            }
        });

        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = courseList.getSelectedValue();
                btn_click_addCourse(selectedCourse);
            }
        });
    }

    private void loadCourses() {
        List<Course> courses = CourseManagementSystem.getCoursesByCategory(category);
        listModel.clear();
        for (Course course : courses) {
            listModel.addElement(course);
        }
    }

    private void btn_click_viewDetails(Course selectedCourse) {
        if (selectedCourse != null) {
            new CourseDetailsGUI(selectedCourse).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(CourseCategoryGUI.this, "Please select a course to view details.");
        }
    }

    private void btn_click_addCourse(Course selectedCourse) {
        if (selectedCourse != null) {
            CourseManagementSystem.addCourseToCart(selectedCourse);
        } else {
            JOptionPane.showMessageDialog(CourseCategoryGUI.this, "Please select a course to add.");
        }
    }
}
