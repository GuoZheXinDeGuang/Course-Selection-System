import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JButton addCourseButton;
    private JButton viewCartButton;
    private JButton searchCourseButton;
    private JButton rateCourseButton;
    private JButton logoutButton;

    public MainGUI() {
        setTitle("Course Selection System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addCourseButton = new JButton("Add Course");
        viewCartButton = new JButton("View Cart");
        searchCourseButton = new JButton("Search Course");
        rateCourseButton = new JButton("Rate Courses");
        logoutButton = new JButton("Logout");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(addCourseButton, gbc);
        panel.add(viewCartButton, gbc);
        panel.add(searchCourseButton, gbc);
        panel.add(rateCourseButton, gbc);
        panel.add(logoutButton, gbc);

        add(panel);

        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_click_addCourseGUI();
            }
        });

        viewCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_click_viewCartGUI();
            }
        });

        searchCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_click_searchCourseGUI();
            }
        });

        rateCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_click_rateCourseGUI();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_click_logout();
            }
        });
    }

    private void btn_click_addCourseGUI() {
        new AddCourseGUI().setVisible(true);
    }

    private void btn_click_viewCartGUI() {
        new CartGUI().setVisible(true);
    }

    private void btn_click_searchCourseGUI() {
        new SearchCourseGUI().setVisible(true);
    }

    private void btn_click_rateCourseGUI() {
        new RateCourseGUI().setVisible(true);
    }

    private void btn_click_logout() {
        UserManagementSystem.logoutUser();
        new LoginGUI().setVisible(true);
        dispose();
    }
}
