package ui;

import javax.swing.*;

public class AdminPanel extends JFrame {

	public AdminPanel() {

		setTitle("Admin Panel");
		setSize(500, 300);
		setLayout(null);

		JLabel label = new JLabel("Welcome Admin");

		label.setBounds(180, 80, 200, 40);

		add(label);

		setVisible(true);
	}
}