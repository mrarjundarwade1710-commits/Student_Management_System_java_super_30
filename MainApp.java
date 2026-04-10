import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MainApp {
    private ArrayList<Student> students;
    private Scanner scanner;

    public MainApp() {
        students = FileHandler.readStudents();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            showMenu();
            int choice = readInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    sortStudents();
                    break;
                case 7:
                    showTopper();
                    break;
                case 8:
                    countStudents();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nStudent Management System using File Handling");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Sort Students by Marks");
        System.out.println("7. Show Topper");
        System.out.println("8. Count Students");
        System.out.println("9. Exit");
        System.out.print("Enter choice: ");
    }

    private int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter number: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter marks: ");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter ID: ");
        int id = readInt();
        if (findStudentById(id) != null) {
            System.out.println("ID already exists");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }

        System.out.print("Enter Marks: ");
        double marks = readDouble();
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks");
            return;
        }

        students.add(new Student(id, name, marks));
        FileHandler.writeStudents(students);
        System.out.println("Student added");
    }

    private void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }
        System.out.printf("%-5s %-20s %-6s%n", "ID", "Name", "Marks");
        for (Student student : students) {
            System.out.printf("%-5d %-20s %-6.1f%n", student.getId(), student.getName(), student.getMarks());
        }
    }

    private void searchStudent() {
        System.out.print("Enter ID: ");
        int id = readInt();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found");
            return;
        }
        System.out.printf("%-5s %-20s %-6s%n", "ID", "Name", "Marks");
        System.out.printf("%-5d %-20s %-6.1f%n", student.getId(), student.getName(), student.getMarks());
    }

    private void updateStudent() {
        System.out.print("Enter ID: ");
        int id = readInt();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found");
            return;
        }
        System.out.print("Enter new marks: ");
        double marks = readDouble();
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks");
            return;
        }
        student.setMarks(marks);
        FileHandler.writeStudents(students);
        System.out.println("Student updated");
    }

    private void deleteStudent() {
        System.out.print("Enter ID: ");
        int id = readInt();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found");
            return;
        }
        students.remove(student);
        FileHandler.writeStudents(students);
        System.out.println("Student deleted");
    }

    private void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        FileHandler.writeStudents(students);
        System.out.println("Students sorted by marks");
    }

    private void showTopper() {
        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }
        Student topper = students.stream().max(Comparator.comparingDouble(Student::getMarks)).orElse(null);
        if (topper == null) {
            System.out.println("No students found");
            return;
        }
        System.out.println("Topper:");
        System.out.printf("%-5s %-20s %-6s%n", "ID", "Name", "Marks");
        System.out.printf("%-5d %-20s %-6.1f%n", topper.getId(), topper.getName(), topper.getMarks());
    }

    private void countStudents() {
        System.out.println("Total students: " + students.size());
    }

    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new MainApp().run();
    }
}