import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class CourseManagementSystem  {
    private static List<Course> cart = new ArrayList<Course>();
    private static List<Course> allCourses = new ArrayList<Course>();
    private static final String COURSE_FILE = "courses.bin";
    private static final String CART_FILE = "cart.bin";
    private static User loggedInUser = null;
    private static Map<String, User> users = new HashMap<String, User>();
    private static List<Course> completedCourses = new ArrayList<Course>();

    static {
        loadCourses();
        if (allCourses.isEmpty()) {
            initializeCourses();
        }
        loadCart();
    }

    public static void addCourseToCart(Course course) {
        if (checkPrerequisites(course)) {
            if (!isCourseInCart(course) && !hasCompletedCourse(course)) {
                cart.add(course);
                saveCart();
                JOptionPane.showMessageDialog(null, "Course added to cart!");
            } else {
                JOptionPane.showMessageDialog(null, "Course already selected or completed.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "You do not meet the prerequisites for this course.");
        }
    }

    public static void removeCourseFromCart(Course course) {
        cart.remove(course);
        saveCart();
    }

    public static boolean validateSchedule() {
        for (int i = 0; i < cart.size(); i++) {
            for (int j = i + 1; j < cart.size(); j++) {
                if (isTimeConflict(cart.get(i).getCourseTime(), cart.get(j).getCourseTime())) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isTimeConflict(String time1, String time2) {
        String[] t1 = time1.split("-");
        String[] t2 = time2.split("-");

        int start1 = Integer.parseInt(t1[0].replace(":", ""));
        int end1 = Integer.parseInt(t1[1].replace(":", ""));
        int start2 = Integer.parseInt(t2[0].replace(":", ""));
        int end2 = Integer.parseInt(t2[1].replace(":", ""));

        return (start1 < end2 && end1 > start2);
    }

    public static List<Course> getCart() {
        return new ArrayList<Course>(cart);
    }

    public static boolean checkPrerequisites(Course course) {
        if (course.getPrerequisites().isEmpty()) {
            return true;
        }
        if (loggedInUser instanceof Student) {
            Student student = (Student) loggedInUser;
            for (Course completedCourse : student.getCompletedCourses()) {
                if (completedCourse.getCourseName().equals(course.getPrerequisites())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCourseInCart(Course course) {
        for (Course c : cart) {
            if (c.getCourseName().equals(course.getCourseName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCompletedCourse(Course course) {
        if (loggedInUser instanceof Student) {
            Student student = (Student) loggedInUser;
            return student.hasCompletedCourse(course.getCourseName());
        }
        return false;
    }

    public static void loginUser(User user) {
        loggedInUser = user;
        loadCart();
    }

    public static void logoutUser() {
        saveCart();
        loggedInUser = null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    @SuppressWarnings("unchecked")
    private static void loadCourses() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(COURSE_FILE));
            allCourses = (List<Course>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            allCourses = new ArrayList<Course>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            allCourses = new ArrayList<Course>();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadCart() {
        if (loggedInUser instanceof Student) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(CART_FILE));
                cart = (List<Course>) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            cart.clear();
        }
    }

    private static void saveCart() {
        if (loggedInUser instanceof Student) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(CART_FILE));
                oos.writeObject(cart);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static List<Course> getAllCourses() {
        return new ArrayList<Course>(allCourses);
    }

    public static List<Course> getCoursesByCategory(String category) {
        List<Course> categoryCourses = new ArrayList<Course>();
        for (Course course : allCourses) {
            if (course.getCategory().equals(category)) {
                categoryCourses.add(course);
            }
        }
        return categoryCourses;
    }

    public static void addCourse(Course course) {
        allCourses.add(course);
        saveCourses();
    }

    public static void saveCourses() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(COURSE_FILE));
            oos.writeObject(allCourses);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void initializeCourses() {
        allCourses.add(new Course("Intro to CS", "10:00-11:00", "Prof. A", "Introduction to Computer Science", "", 3,
                -1, "Computer Science"));
        allCourses.add(new Course("Data Structures", "11:00-12:00", "Prof. B", "Introduction to Data Structures",
                "Intro to CS", 3, -1, "Computer Science"));
        allCourses.add(new Course("Computer Systems", "12:00-13:00", "Prof. C", "Introduction to Computer Systems",
                "Data Structures", 3, -1, "Computer Science"));
        allCourses.add(new Course("Object Oriented Programming", "13:00-14:00", "Prof. D", "Advanced concepts of OOP",
                "Computer Systems", 3, -1, "Computer Science"));

        allCourses.add(new Course("Calculus I", "09:00-10:00", "Prof. E", "Introduction to Calculus", "", 3, -1,
                "Mathematics"));
        allCourses.add(new Course("Calculus II", "10:00-11:00", "Prof. F", "Advanced Calculus", "Calculus I", 3, -1,
                "Mathematics"));
        allCourses.add(new Course("Linear Algebra", "11:00-12:00", "Prof. G", "Introduction to Linear Algebra",
                "Calculus II", 3, -1, "Mathematics"));

        allCourses.add(new Course("English Literature I", "14:00-15:00", "Prof. H",
                "Introduction to English Literature", "", 3, -1, "English"));
        allCourses.add(new Course("English Literature II", "15:00-16:00", "Prof. I", "Advanced English Literature",
                "English Literature I", 3, -1, "English"));
        allCourses.add(new Course("Creative Writing", "16:00-17:00", "Prof. J", "Introduction to Creative Writing",
                "English Literature II", 3, -1, "English"));

        saveCourses();
    }

    public static List<Course> searchCourses(String query, String type) {
        List<Course> results = new ArrayList<Course>();
        for (Course course : allCourses) {
            if (type.equals("name") && course.getCourseName().toLowerCase().contains(query)) {
                results.add(course);
            } else if (type.equals("professor") && course.getCourseInstructor().toLowerCase().contains(query)) {
                results.add(course);
            }
        }
        return results;
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

    public static void rateCourse(Course course, double rating) {
        for (int i = 0; i < allCourses.size(); i++) {
            Course c = allCourses.get(i);
            if (c.getCourseName().equals(course.getCourseName())) {
                c.setRating(rating);
                break;
            }
        }
        saveCourses();
    }

    public static void removeCourse(Course course) {
        allCourses.remove(course);
        saveCourses();
    }

    public static void updateCourse(Course course, String name, String time, String instructor, String description,
            String prerequisites, int credits, String category) {
        course.setCourseName(name);
        course.setCourseTime(time);
        course.setCourseInstructor(instructor);
        course.setCourseDescription(description);
        course.setPrerequisites(prerequisites);
        course.setCredits(credits);
        course.setCategory(category);
        saveCourses();
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
