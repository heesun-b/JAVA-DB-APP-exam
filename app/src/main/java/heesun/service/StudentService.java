package heesun.service;

import java.sql.Connection;
import java.sql.SQLException;

import heesun.model.StudentRepository;

public class StudentService {
    private StudentRepository studentRepository;
    private Connection conn;

    public StudentService(StudentRepository studentRepository, Connection conn) {
        this.studentRepository = studentRepository;
        this.conn = conn;
    }

    public void insert(String name, int age, String tel, String email) throws SQLException {
        try {
            studentRepository.insert(name, age, tel, email);
            conn.setAutoCommit(false);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void deleteById(int id) throws SQLException {
        try {
            studentRepository.deleteById(id);
            conn.setAutoCommit(false);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void update(int id, String name, int age, String tel, String email) throws SQLException {
        try {
            studentRepository.update(id, name, age, tel, email);
            conn.setAutoCommit(false);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
