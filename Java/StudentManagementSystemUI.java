import java.util.*;
import java.io.*;

class Student {
    private String name;
    private int rollNumber;
    private double grade;

    public Student(String name, int rollNumber, double grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public double getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void saveStudentsToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                writer.newLine();
            }
        }
    }

    public void loadStudentsFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int rollNumber = Integer.parseInt(parts[1]);
                double grade = Double.parseDouble(parts[2]);
                students.add(new Student(name, rollNumber, grade));
            }
        }
    }
}

public class StudentManagementSystemUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Student Management System");
            System.out.println("1. Add a student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save students to file");
            System.out.println("6. Load students from file");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    saveStudentsToFile();
                    break;
                case 6:
                    loadStudentsFromFile();
                    break;
                case 0:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();
        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine();
        studentManagementSystem.addStudent(new Student(name, rollNumber, grade));
        System.out.println("Student added successfully.");
    }

    private static void removeStudent() {
        System.out.print("Enter student roll number to remove: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();
        studentManagementSystem.removeStudent(rollNumber);
        System.out.println("Student removed successfully.");
    }

    private static void searchStudent() {
        System.out.print("Enter student roll number to search: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();
        Student student = studentManagementSystem.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found:");
            System.out.println("Name: " + student.getName());
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Grade: " + student.getGrade());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayAllStudents() {
        List<Student> students = studentManagementSystem.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("All students:");
            for (Student student : students) {
                System.out.println("Name: " + student.getName());
                System.out.println("Roll Number: " + student.getRollNumber());
                System.out.println("Grade: " + student.getGrade());
                System.out.println();
            }
        }
    }

    private static void saveStudentsToFile() {
        System.out.print("Enter file name to save students: ");
        String fileName = scanner.nextLine();
        try {
            studentManagementSystem.saveStudentsToFile(fileName);
            System.out.println("Students saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving students to file: " + e.getMessage());
        }
    }

    private static void loadStudentsFromFile() {
        System.out.print("Enter file name to load students: ");
        String fileName = scanner.nextLine();
        try {
            studentManagementSystem.loadStudentsFromFile(fileName);
            System.out.println("Students loaded from file successfully.");
        } catch (IOException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
        }
    }
}