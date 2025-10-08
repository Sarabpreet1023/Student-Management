package View;

import Controller.StudentController;
import Model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentController controller = new StudentController();
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    Student s = new Student(0, name, dept, marks);
                    controller.addStudent(s);
                }
                case 2 -> {
                    List<Student> students = controller.getAllStudents();
                    System.out.printf("\n%-10s %-20s %-15s %-10s\n", "ID", "Name", "Department", "Marks");
                    System.out.println("------------------------------------------------------------");
                    for (Student st : students) {
                        System.out.println(st);
                    }
                }
                case 3 -> {
                    System.out.print("Enter Student ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter New Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter New Marks: ");
                    double marks = sc.nextDouble();
                    Student s = new Student(id, name, dept, marks);
                    controller.updateStudent(s);
                }
                case 4 -> {
                    System.out.print("Enter Student ID to delete: ");
                    int id = sc.nextInt();
                    controller.deleteStudent(id);
                }
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
