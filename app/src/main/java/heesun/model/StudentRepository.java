package heesun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

public class StudentRepository {
    private Connection conn;

    public StudentRepository(Connection conn) {
        this.conn = conn;
    }

    public void insert(String name, int age, String tel, String email) throws SQLException {
        String sql = "insert into student(name, age, tel, email) values(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.setString(3, tel);
        pstmt.setString(4, email);

        int result = pstmt.executeUpdate();

        if (result == 1) {
            System.out.println("insert 완료");
        } else {
            System.out.println("insert 실패");
        }

        pstmt.close();
    }

    public void deleteById(int id) throws SQLException {
        String sql = "delete from student where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);
        int result = pstmt.executeUpdate();

        if (result == 1) {
            System.out.println("delete 완료");
        } else {
            System.out.println("delete 실패");
        }
        pstmt.close();
    }

    public void update(int id, String name, int age, String tel, String email) throws SQLException {
        String sql = "update student set name = ?, age = ?, tel = ?, email = ? where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.setString(3, tel);
        pstmt.setString(4, email);
        pstmt.setInt(5, id);

        int result = pstmt.executeUpdate();

        if (result == 1) {
            System.out.println("update 완료");
        } else {
            System.out.println("udpate 실패");
        }

        pstmt.close();
    }

    public List<Student> findAll() throws SQLException {
        List<Student> studentList = new ArrayList<>();

        String sql = "select * from student";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {

            String name = rs.getString("name");
            int age = rs.getInt("age");
            String tel = rs.getString("tel");
            String email = rs.getString("email");

            Student s2 = new Student(age, name, age, tel, email);
            studentList.add(s2);
        }
        return studentList;
    }

    public Student findById(int id) throws SQLException {
        Student student = null;

        String sql = "select * from student where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String tel = rs.getString("tel");
            String email = rs.getString("email");
            student = new Student(id, name, age, tel, email);
        }
        return student;
    }

}
