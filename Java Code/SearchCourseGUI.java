import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchCourseGUI extends JFrame {
    private JTextField searchField;
    private JButton searchByNameButton;
    private JButton searchByProfessorButton;
    private JList<Course> resultsList;
    private DefaultListModel<Course> listModel;

    public SearchCourseGUI() {
        setTitle("Search Courses");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        searchField = new JTextField(20);
        searchByNameButton = new JButton("Search by Course Name");
        searchByProfessorButton = new JButton("Search by Professor");
        listModel = new DefaultListModel<Course>();
        resultsList = new JList<Course>(listModel);
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        resultsList.setVisibleRowCount(4);
        JScrollPane scrollPane = new JScrollPane(resultsList);
        scrollPane.setPreferredSize(new Dimension(380, 80));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Search by Course Name or Professor:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(searchField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(searchByNameButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(searchByProfessorButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        add(panel);

        searchByNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText().toLowerCase();
                btn_click_performSearch(query, "name");
            }
        });

        searchByProfessorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText().toLowerCase();
                btn_click_performSearch(query, "professor");
            }
        });

        resultsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = resultsList.locationToIndex(e.getPoint());
                    Course selectedCourse = listModel.getElementAt(index);
                    new CourseDetailsGUI(selectedCourse).setVisible(true);
                }
            }
        });
    }

    private void btn_click_performSearch(String query, String type) {
        java.util.List<Course> results = CourseManagementSystem.searchCourses(query, type);

        listModel.clear();
        for (Course course : results) {
            listModel.addElement(course);
        }
    }
}
