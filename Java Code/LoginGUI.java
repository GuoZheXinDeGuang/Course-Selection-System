import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginAsStudentButton;
    private JButton loginAsAdminButton;
    private JLabel statusLabel;
    private static final String filePath = "users.txt";
    

    public LoginGUI() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginAsStudentButton = new JButton("Login as Student");
        loginAsAdminButton = new JButton("Login as Admin");
        statusLabel = new JLabel(" ");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        buttonPanel.add(loginAsStudentButton);
        buttonPanel.add(loginAsAdminButton);

        panel.add(buttonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(statusLabel, gbc);

        add(panel);

        loginAsStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticateStudent(usernameField.getText(), new String(passwordField.getPassword()));
            }
        });

        loginAsAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticateAdmin(usernameField.getText(), new String(passwordField.getPassword()));
            }
        });

        CourseManagementSystem.loadUsers(filePath);
    }

    private void authenticateStudent(String username, String password) {
        if (CourseManagementSystem.authenticateUser(username, password, false)) {
            new MainGUI().setVisible(true);
            dispose();
        } else {
            statusLabel.setText("Invalid username or password");
        }
    }

    private void authenticateAdmin(String username, String password) {
        if (CourseManagementSystem.authenticateUser(username, password, true)) {
            new AdminGUI().setVisible(true);
            dispose();
        } else {
            statusLabel.setText("Invalid username or password");
        }
    }
}
