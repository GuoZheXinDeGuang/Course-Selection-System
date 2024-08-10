import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame {
    private JButton updateCourseButton;
    private JButton removeCourseButton;
    private JButton logoutButton;
    private JButton addCourseButton;

    public AdminGUI() {
        setTitle("Admin Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        updateCourseButton = new JButton("Update Course");
        removeCourseButton = new JButton("Remove Course");
        addCourseButton = new JButton("Create Course");
        logoutButton = new JButton("Logout");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(addCourseButton, gbc);
        panel.add(updateCourseButton, gbc);
        panel.add(removeCourseButton, gbc);
        panel.add(logoutButton, gbc);

        add(panel);
        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_click_createCourseGUI();
            }
        });

        updateCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_click_updateCourseGUI();
            }
        });

        removeCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_click_removeCourseGUI();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_click_logout();
            }
        });
    }

    private void btn_click_createCourseGUI() {
        new CreateCourseGUI().setVisible(true);
    }

    private void btn_click_updateCourseGUI() {
        new UpdateCourseGUI().setVisible(true);
    }

    private void btn_click_removeCourseGUI() {
        new RemoveCourseGUI().setVisible(true);
    }

    private void btn_click_logout() {
        CourseManagementSystem.logoutUser();
        new LoginGUI().setVisible(true);
        dispose();
    }
}
