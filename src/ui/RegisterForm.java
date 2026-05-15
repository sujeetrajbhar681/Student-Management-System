package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;

public class RegisterForm extends JFrame {

	JTextField txtUsername, txtEmail;
	JPasswordField txtPassword;
	JComboBox<String> roleBox;

	JButton btnRegister, btnLogin;

	public RegisterForm() {

		setTitle("Register Form");

		setSize(400, 350);

		setLayout(null);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l1 = new JLabel("Username");

		l1.setBounds(50, 50, 100, 30);

		add(l1);

		txtUsername = new JTextField();

		txtUsername.setBounds(150, 50, 180, 30);

		add(txtUsername);

		JLabel l2 = new JLabel("Email");

		l2.setBounds(50, 100, 100, 30);

		add(l2);

		txtEmail = new JTextField();

		txtEmail.setBounds(150, 100, 180, 30);

		add(txtEmail);

		JLabel l3 = new JLabel("Password");

		l3.setBounds(50, 150, 100, 30);

		add(l3);

		txtPassword = new JPasswordField();

		txtPassword.setBounds(150, 150, 180, 30);

		add(txtPassword);

		JLabel l4 = new JLabel("Role");

		l4.setBounds(50, 200, 100, 30);

		add(l4);

		roleBox = new JComboBox<>(new String[] { "Admin", "User" });

		roleBox.setBounds(150, 200, 180, 30);

		add(roleBox);

		btnRegister = new JButton("Register");

		btnRegister.setBounds(50, 260, 120, 35);

		add(btnRegister);

		btnLogin = new JButton("Login");

		btnLogin.setBounds(210, 260, 120, 35);

		add(btnLogin);

		btnRegister.addActionListener(e -> {

			String username = txtUsername.getText();

			String email = txtEmail.getText();

			String password = txtPassword.getText();

			String role = roleBox.getSelectedItem().toString();

			User user = new User(username, email, password, role);

			UserDAO dao = new UserDAO();

			if (dao.registerUser(user)) {

				JOptionPane.showMessageDialog(null, "Registration Successful");

				new LoginForm();

				dispose();

			} else {

				JOptionPane.showMessageDialog(null, "Username or Email Already Exists");
			}
		});

		btnLogin.addActionListener(e -> {

			new LoginForm();

			dispose();
		});

		setVisible(true);
	}
}