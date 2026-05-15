package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;

public class LoginForm extends JFrame {

	JTextField txtUsername;
	JPasswordField txtPassword;
	JButton btnLogin, btnRegister;

	public LoginForm() {

		setTitle("Login");
		setSize(350, 280);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l1 = new JLabel("Username");
		l1.setBounds(40, 50, 100, 30);
		add(l1);

		txtUsername = new JTextField();
		txtUsername.setBounds(140, 50, 150, 30);
		add(txtUsername);

		JLabel l2 = new JLabel("Password");
		l2.setBounds(40, 100, 100, 30);
		add(l2);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(140, 100, 150, 30);
		add(txtPassword);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(40, 170, 100, 30);
		add(btnLogin);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(180, 170, 100, 30);
		add(btnRegister);

		btnLogin.addActionListener(e -> {

			UserDAO dao = new UserDAO();

			User user = dao.loginUser(txtUsername.getText(), txtPassword.getText());

			if (user != null) {

				JOptionPane.showMessageDialog(null, "Login Successful");

				new Dashboard(user);

				dispose();

			} else {

				JOptionPane.showMessageDialog(null, "Invalid Username or Password");
			}
		});

		btnRegister.addActionListener(e -> {
			new RegisterForm();
			dispose();
		});

		setVisible(true);
	}
}