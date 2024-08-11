import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CartGUI extends JFrame {

    private JPanel contentPane;
    private JLabel lblCart;
    private JLabel lblCredit;
    private JLabel lblNumOfCredit;
    private JTable tblCourses;
    private DefaultTableModel tableModel;
    private JButton btnDelete;
    private JButton btnValidate;
    private JScrollPane scrollPane;

    public CartGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblCart = new JLabel("Shopping Cart");
        lblCart.setBounds(160, 0, 122, 21);
        contentPane.add(lblCart);

        lblCredit = new JLabel("Total Credits:");
        lblCredit.setBounds(240, 15, 100, 21);
        contentPane.add(lblCredit);

        lblNumOfCredit = new JLabel("");
        lblNumOfCredit.setBounds(340, 15, 50, 21);
        contentPane.add(lblNumOfCredit);

        tableModel = new DefaultTableModel(new Object[] { "Course", "Credits", "Instructor", "Time" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Course> cart = CourseManagementSystem.getCart();
        for (Course course : cart) {
            tableModel.addRow(new Object[] {
                    course.getCourseName(),
                    course.getCredits(),
                    course.getCourseInstructor(),
                    course.getCourseTime()
            });
        }

        tblCourses = new JTable(tableModel);
        tblCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumnModel columnModel = tblCourses.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(48);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);

        scrollPane = new JScrollPane(tblCourses);
        scrollPane.setBounds(15, 39, 398, 146);
        contentPane.add(scrollPane);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(50, 200, 123, 29);
        contentPane.add(btnDelete);

        btnValidate = new JButton("Validate Schedule");
        btnValidate.setBounds(228, 200, 185, 29);
        contentPane.add(btnValidate);

        getTotalCredits();

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_delete_clk();
            }
        });
        btnValidate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_validate_clk();
            }
        });
    }

    private void getTotalCredits() {
        int totalCredits = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object value = tableModel.getValueAt(i, 1);
            if (value instanceof Integer) {
                totalCredits += (Integer) value;
            }
        }
        lblNumOfCredit.setText(String.valueOf(totalCredits));
    }

    private void btn_delete_clk() {
        int selectedRow = tblCourses.getSelectedRow();
        if (selectedRow != -1) {
            Course selectedCourse = CourseManagementSystem.getCart().get(selectedRow);
            CourseManagementSystem.removeCourseFromCart(selectedCourse);
            tableModel.removeRow(selectedRow);
            getTotalCredits();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a course to delete.");
        }
    }

    private void btn_validate_clk() {
        boolean isValid = CourseManagementSystem.validateSchedule();
        if (isValid) {
            JOptionPane.showMessageDialog(null, "Schedule is valid. No conflicts detected.");
        } else {
            JOptionPane.showMessageDialog(null, "Schedule conflict detected. Please review your cart.");
        }
    }
}
