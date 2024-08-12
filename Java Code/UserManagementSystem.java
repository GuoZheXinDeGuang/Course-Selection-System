import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class UserManagementSystem {
    private static User loggedInUser = null;
    private static Map<String, User> users = new HashMap<String, User>();
    private static List<Course> completedCourses = new ArrayList<Course>();

    public static void loginUser(User user) {
        loggedInUser = user;
        CourseManagementSystem.loadCart();
    }

    public static void logoutUser() {
        CourseManagementSystem.saveCart();
        loggedInUser = null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    private static void createStudentWithCompletedCourses(String userData) {
        String[] parts = userData.split(",");
        String username = parts[0];
        String password = parts[1];
        completedCourses.add(
                new Course("Intro to CS", "10:00-11:00", "Prof. A", "Introduction to Computer Science", "", 3, -1,
                        "Computer Science"));
        completedCourses.add(new Course("Data Structures", "11:00-12:00", "Prof. B", "Introduction to Data Structures",
                "Intro to CS", 3, -1, "Computer Science"));
        completedCourses.add(new Course("Calculus I", "09:00-10:00", "Prof. E", "Introduction to Calculus", "", 3, -1,
                "Mathematics"));
        completedCourses.add(new Course("Calculus II", "10:00-11:00", "Prof. F", "Advanced Calculus", "Calculus I", 3,
                -1, "Mathematics"));

        Student student = new Student(username, password);
        for (Course course : completedCourses) {
            student.addCompletedCourse(course);
        }
        users.put(username, student);
    }

    private static void createAdmin(String userData) {
        String[] parts = userData.split(",");
        String username = parts[0];
        String password = parts[1];
        users.put(username, new Admin(username, password));
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static Map<String, User> getUsers() {
        return new HashMap<String, User>(users);
    }

    public static void loadUsers(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    boolean isAdmin = Boolean.parseBoolean(parts[2]);
                    if (isAdmin) {
                        createAdmin(line);
                    } else {
                        createStudentWithCompletedCourses(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean authenticateUser(String username, String password, boolean isAdmin) {
        if (!users.containsKey(username)) {
            return false;
        }
        User user = users.get(username);
        if (user.getPassword().equals(password)
                && ((isAdmin && user instanceof Admin) || (!isAdmin && user instanceof Student))) {
            loginUser(user);
            return true;
        }
        return false;
    }

}
