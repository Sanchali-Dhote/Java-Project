import java.util.*;

// ==================== Course Class ====================
class Course {
    private String courseId;
    private String courseName;
    private int duration;
    private double fee;

    public Course(String courseId, String courseName, int duration, double fee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.duration = duration;
        this.fee = fee;
    }

    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public int getDuration() { return duration; }
    public double getFee() { return fee; }

    @Override
    public String toString() {
        return courseId + " - " + courseName + " (" + duration + " hrs, Rs." + fee + ")";
    }
}

// ==================== Student Class ====================
class Student {
    private String studentId;
    private String studentName;
    private List<Course> registeredCourses = new ArrayList<>();

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public void registerCourse(Course c) {
        registeredCourses.add(c);
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }

    @Override
    public String toString() {
        return studentId + " - " + studentName;
    }
}

// ==================== MAIN SYSTEM CLASS ====================
public class CourseRegistrationSystem {

    private static Map<String, Course> courseList = new HashMap<>();
    private static Map<String, Student> studentList = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // ===== 10 FIXED COURSES =====
        courseList.put("C101", new Course("C101", "Java Programming", 40, 3000));
        courseList.put("C102", new Course("C102", "Python Basics", 35, 2500));
        courseList.put("C103", new Course("C103", "Data Structures", 50, 4000));
        courseList.put("C104", new Course("C104", "Web Development", 60, 4500));
        courseList.put("C105", new Course("C105", "Database Management", 45, 3200));
        courseList.put("C106", new Course("C106", "Android Development", 55, 5000));
        courseList.put("C107", new Course("C107", "C Programming", 30, 2000));
        courseList.put("C108", new Course("C108", "Operating Systems", 40, 3500));
        courseList.put("C109", new Course("C109", "Networking Basics", 38, 2800));
        courseList.put("C110", new Course("C110", "Machine Learning Intro", 50, 6000));

        int choice;
        do {
            System.out.println("\n===== COURSE REGISTRATION SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Courses");
            System.out.println("3. Register Student for Course");
            System.out.println("4. View Student Details");
            System.out.println("5. Remove Student (Unregister)");
            System.out.println("6. List All Students");
            System.out.println("7. Generate Invoice");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: displayCourses(); break;
                case 3: registerStudentForCourse(); break;
                case 4: viewStudentDetails(); break;
                case 5: removeStudent(); break;
                case 6: listAllStudents(); break;
                case 7: generateInvoice(); break;
                case 8: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 8);
    }

    // ===== Add Student =====
    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        studentList.put(id, new Student(id, name));
        System.out.println("Student added successfully!");
    }

    // ===== Display Courses =====
    private static void displayCourses() {
        System.out.println("\nAvailable Courses (10 Total):");
        for (Course c : courseList.values()) {
            System.out.println(c);
        }
    }

    // ===== Register Student for a Course =====
    private static void registerStudentForCourse() {
        System.out.print("Enter Student ID: ");
        String sid = sc.nextLine();

        Student s = studentList.get(sid);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        displayCourses();
        System.out.print("Enter Course ID: ");
        String cid = sc.nextLine();

        Course c = courseList.get(cid);
        if (c == null) {
            System.out.println("Course not found!");
            return;
        }

        s.registerCourse(c);
        System.out.println("Course registered successfully!");
    }

    // ===== View Student Details =====
    private static void viewStudentDetails() {
        System.out.print("Enter Student ID: ");
        String sid = sc.nextLine();

        Student s = studentList.get(sid);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("\nStudent: " + s);
        System.out.println("Registered Courses:");
        for (Course c : s.getRegisteredCourses()) {
            System.out.println(" - " + c);
        }
    }

    // ===== Unregister Student (Delete Permanently) =====
    private static void removeStudent() {
        System.out.print("Enter Student ID to remove: ");
        String sid = sc.nextLine();

        if (studentList.remove(sid) != null) {
            System.out.println("Student removed permanently!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // ===== List All Students =====
    private static void listAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n===== STUDENT LIST =====");
        for (Student s : studentList.values()) {
            System.out.println(s);
        }
    }

    // ===== Generate Invoice =====
    private static void generateInvoice() {
        System.out.print("Enter Student ID: ");
        String sid = sc.nextLine();

        Student s = studentList.get(sid);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        List<Course> list = s.getRegisteredCourses();
        if (list.isEmpty()) {
            System.out.println("No courses registered!");
            return;
        }

        System.out.println("\n===== INVOICE =====");
        System.out.println("Student: " + s);

        double total = 0;

        for (Course c : list) {
            System.out.println(c.getCourseName() + " - Rs." + c.getFee());
            total += c.getFee();
        }

        System.out.println("-------------------------");
        System.out.println("Total Amount: Rs." + total);
    }
}
