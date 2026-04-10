public class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String toCsv() {
        return id + "," + name + "," + marks;
    }

    public static Student fromCsv(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            return null;
        }
        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            double marks = Double.parseDouble(parts[2].trim());
            return new Student(id, name, marks);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}