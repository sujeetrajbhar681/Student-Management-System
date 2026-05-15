package dao;

import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	Connection con;

	public boolean registerUser(User user) {

		boolean status = false;

		try {

			con = DBConnection.getConnection();

			String checkQuery = "SELECT * FROM users WHERE username=? OR email=?";

			PreparedStatement checkPs = con.prepareStatement(checkQuery);

			checkPs.setString(1, user.getUsername());
			checkPs.setString(2, user.getEmail());

			ResultSet rs = checkPs.executeQuery();

			if (rs.next()) {

				System.out.println("Username or Email Already Exists");

				return false;
			}

			String insertQuery = "INSERT INTO users(username,email,password,role) VALUES(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(insertQuery);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getRole());

			int rows = ps.executeUpdate();

			System.out.println("Rows Inserted = " + rows);

			if (rows > 0) {

				status = true;

				System.out.println("User Registered Successfully");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	public User loginUser(String username, String password) {

		User user = null;

		try {

			con = DBConnection.getConnection();

			String query = "SELECT * FROM users WHERE username=? AND password=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				user = new User();

				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return user;
	}
}