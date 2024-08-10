import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourseGUI extends JFrame {
    private JList<String> categoryList;
    private DefaultListModel<String> categoryModel;
    private JButton selectCategoryButton;

    public AddCourseGUI() {
        setTitle("Add Course");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        categoryModel = new DefaultListModel<String>();
        categoryModel.addElement("Computer Science");
        categoryModel.addElement("Mathematics");
        categoryModel.addElement("English");

        categoryList = new JList<String>(categoryModel);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(categoryList);
        selectCategoryButton = new JButton("Select Category");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        buttonPanel.add(selectCategoryButton, gbc);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        selectCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = categoryList.getSelectedValue();
                btn_click_category(selectedCategory);
            }
        });
    }

    private void btn_click_category(String selectedCategory) {
        if (selectedCategory != null) {
            new CourseCategoryGUI(selectedCategory).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a category.");
        }
    }
}
