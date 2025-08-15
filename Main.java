import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private final String fileName = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully.");
    }

    public void removeStudent(String rollNumber) {
        boolean removed = students.removeIf(s -> s.getRollNumber().equalsIgnoreCase(rollNumber));
        if (removed) {
            saveToFile();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void editStudent(String rollNumber, String newName, String newGrade) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                s.setName(newName);
                s.setGrade(newGrade);
                saveToFile();
                System.out.println("Student details updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("Student List:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            students = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load data: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input! Enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name;
                    while (true) {
                        name = sc.nextLine().trim();
                        if (!name.isEmpty()) break;
                        System.out.print("Name cannot be empty! Enter again: ");
                    }

                    System.out.print("Enter Roll Number: ");
                    String roll;
                    while (true) {
                        roll = sc.nextLine().trim();
                        if (!roll.isEmpty()) break;
                        System.out.print("Roll number cannot be empty! Enter again: ");
                    }

                    System.out.print("Enter Grade: ");
                    String grade;
                    while (true) {
                        grade = sc.nextLine().trim().toUpperCase();
                        if (!grade.isEmpty()) break;
                        System.out.print("Grade cannot be empty! Enter again: ");
                    }

                    sms.addStudent(new Student(name, roll, grade));
                    break;

                case 2:
                    System.out.print("Enter Roll Number to Remove: ");
                    sms.removeStudent(sc.nextLine().trim());
                    break;

                case 3:
                    System.out.print("Enter Roll Number to Edit: ");
                    String editRoll = sc.nextLine().trim();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine().trim();
                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine().trim().toUpperCase();
                    sms.editStudent(editRoll, newName, newGrade);
                    break;

                case 4:
                    System.out.print("Enter Roll Number to Search: ");
                    sms.searchStudent(sc.nextLine().trim());
                    break;

                case 5:
                    sms.displayAllStudents();
                    break;

                case 6:
                    System.out.println("Exiting... Data saved.");
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1-6.");
            }
        } while (choice != 6);

        sc.close();}
}