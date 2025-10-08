package Controller;
import Model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {

    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name"; // Change database
    private static final String USER = "root";  // Change username
    private static final String PASSWORD = "your_password"; // Change password

    Connection conn;

    public StudentController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            createTableIfNotExists();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create table if not exists
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS Student (" +
                "StudentID INT AUTO_INCREMENT PRIMARY KEY, " +
                "Name VARCHAR(100), " +
                "Department VARCHAR(50), " +
                "Marks DOUBLE)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ADD new student
    public void addStudent(Student s) {
        String sql = "INSERT INTO Student (Name, Department, Marks) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getMarks());
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Student added successfully!" : "❌ Failed to add student.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIEW all students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Department"),
                        rs.getDouble("Marks")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE student
    public void updateStudent(Student s) {
        String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getMarks());
            ps.setInt(4, s.getStudentID());
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Student updated successfully!" : "❌ No student found with that ID.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE student
    public void deleteStudent(int id) {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Student deleted successfully!" : "❌ No student found with that ID.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
