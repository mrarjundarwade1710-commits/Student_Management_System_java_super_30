import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    private static final String DATA_FILE = "students.txt";

    public static ArrayList<Student> readStudents() {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(DATA_FILE);

        try {
            if (!file.exists()) {
                file.createNewFile();
                return students;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue;
                    }
                    Student student = Student.fromCsv(line);
                    if (student != null) {
                        students.add(student);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading students.txt: " + e.getMessage());
        }

        return students;
    }

    public static void writeStudents(ArrayList<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Student student : students) {
                writer.write(student.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing students.txt: " + e.getMessage());
        }
    }
}