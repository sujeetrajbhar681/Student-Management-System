package dao;

import db.DBConnection;
import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

	Connection con;

	public boolean addStudent(Student s) {

		boolean status = false;

		try {

			con = DBConnection.getConnection();

			String query = "INSERT INTO students(name,email,course,phone,enrollment_date) VALUES(?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getCourse());
			ps.setString(4, s.getPhone());
			ps.setString(5, s.getEnrollmentDate());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				status = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public ArrayList<Student> getAllStudents() {

		ArrayList<Student> list = new ArrayList<>();

		try {

			con = DBConnection.getConnection();

			String query = "SELECT * FROM students";

			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Student s = new Student();

				s.setStudentId(rs.getInt("student_id"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				s.setPhone(rs.getString("phone"));
				s.setEnrollmentDate(rs.getString("enrollment_date"));

				list.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean updateStudent(Student s) {

		boolean status = false;

		try {

			con = DBConnection.getConnection();

			String query = "UPDATE students SET name=?,email=?,course=?,phone=?,enrollment_date=? WHERE student_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getCourse());
			ps.setString(4, s.getPhone());
			ps.setString(5, s.getEnrollmentDate());
			ps.setInt(6, s.getStudentId());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				status = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public boolean deleteStudent(int id) {

		boolean status = false;

		try {

			con = DBConnection.getConnection();

			String query = "DELETE FROM students WHERE student_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, id);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				status = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public ArrayList<Student> searchStudents(String keyword) {

		ArrayList<Student> list = new ArrayList<>();

		try {

			con = DBConnection.getConnection();

			String query = "SELECT * FROM students WHERE " + "CAST(student_id AS CHAR) LIKE ? OR " + "name LIKE ? OR "
					+ "course LIKE ?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ps.setString(3, "%" + keyword + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Student s = new Student();

				s.setStudentId(rs.getInt("student_id"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				s.setPhone(rs.getString("phone"));
				s.setEnrollmentDate(rs.getString("enrollment_date"));

				list.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}