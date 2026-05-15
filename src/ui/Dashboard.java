package ui;

import model.User;

import javax.swing.*;

public class Dashboard extends JFrame {

	private User user;

	public Dashboard(User user) {

		this.user = user;

		setTitle("Student Management System");

		setSize(700, 500);

		setLayout(null);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel welcomeLabel = new JLabel("Welcome : " + user.getUsername() + " (" + user.getRole() + ")");

		welcomeLabel.setBounds(220, 50, 300, 30);

		add(welcomeLabel);

		JLabel titleLabel = new JLabel("Dashboard");

		titleLabel.setBounds(300, 100, 200, 30);

		add(titleLabel);

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");

		JMenuItem logoutItem = new JMenuItem("Logout");

		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(logoutItem);
		fileMenu.add(exitItem);

		JMenu studentMenu = new JMenu("Student");

		JMenuItem addStudentItem = new JMenuItem("Add Student");

		studentMenu.add(addStudentItem);

		JMenu searchMenu = new JMenu("Search");

		JMenuItem searchStudentItem = new JMenuItem("Search Student");

		searchMenu.add(searchStudentItem);

		JMenu helpMenu = new JMenu("Help");

		JMenuItem aboutItem = new JMenuItem("About Application");

		helpMenu.add(aboutItem);

		menuBar.add(fileMenu);
		menuBar.add(studentMenu);
		menuBar.add(searchMenu);
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		logoutItem.addActionListener(e -> {

			JOptionPane.showMessageDialog(null, "Logout Successful");

			new LoginForm();

			dispose();
		});

		exitItem.addActionListener(e -> {

			int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);

			if (choice == JOptionPane.YES_OPTION) {

				System.exit(0);
			}
		});

		addStudentItem.addActionListener(e -> {

			new StudentForm();
		});

		searchStudentItem.addActionListener(e -> {

			new SearchPanel();
		});

		aboutItem.addActionListener(e -> {

			JOptionPane.showMessageDialog(null,
					"Student Management System\n" + "Java Swing + JDBC + MySQL\n" + "Version 1.0");
		});

		setVisible(true);
	}
}