package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;

public class StudentForm extends JFrame {

	JTextField txtName, txtEmail, txtCourse, txtPhone, txtDate;

	JButton btnSave;

	int studentId = 0;

	public StudentForm() {

		formUI();

		btnSave.addActionListener(e -> {

			Student s = new Student(txtName.getText(), txtEmail.getText(), txtCourse.getText(), txtPhone.getText(),
					txtDate.getText());

			StudentDAO dao = new StudentDAO();

			if (dao.addStudent(s)) {

				JOptionPane.showMessageDialog(null, "Student Added Successfully");

				dispose();

			} else {

				JOptionPane.showMessageDialog(null, "Failed");
			}
		});
	}

	public StudentForm(Student s) {

		formUI();

		studentId = s.getStudentId();

		txtName.setText(s.getName());

		txtEmail.setText(s.getEmail());

		txtCourse.setText(s.getCourse());

		txtPhone.setText(s.getPhone());

		txtDate.setText(s.getEnrollmentDate());

		btnSave.setText("Update");

		btnSave.addActionListener(e -> {

			Student student = new Student(txtName.getText(), txtEmail.getText(), txtCourse.getText(),
					txtPhone.getText(), txtDate.getText());

			student.setStudentId(studentId);

			StudentDAO dao = new StudentDAO();

			if (dao.updateStudent(student)) {

				JOptionPane.showMessageDialog(null, "Student Updated");

				new SearchPanel();

				dispose();

			} else {

				JOptionPane.showMessageDialog(null, "Update Failed");
			}
		});
	}

	public void formUI() {

		setTitle("Student Form");

		setSize(400, 450);

		setLayout(null);

		setLocationRelativeTo(null);

		JLabel l1 = new JLabel("Name");

		l1.setBounds(40, 40, 100, 30);

		add(l1);

		txtName = new JTextField();

		txtName.setBounds(150, 40, 180, 30);

		add(txtName);

		JLabel l2 = new JLabel("Email");

		l2.setBounds(40, 90, 100, 30);

		add(l2);

		txtEmail = new JTextField();

		txtEmail.setBounds(150, 90, 180, 30);

		add(txtEmail);

		JLabel l3 = new JLabel("Course");

		l3.setBounds(40, 140, 100, 30);

		add(l3);

		txtCourse = new JTextField();

		txtCourse.setBounds(150, 140, 180, 30);

		add(txtCourse);

		JLabel l4 = new JLabel("Phone");

		l4.setBounds(40, 190, 100, 30);

		add(l4);

		txtPhone = new JTextField();

		txtPhone.setBounds(150, 190, 180, 30);

		add(txtPhone);

		JLabel l5 = new JLabel("Enrollment Date");

		l5.setBounds(40, 240, 120, 30);

		add(l5);

		txtDate = new JTextField();

		txtDate.setBounds(150, 240, 180, 30);

		add(txtDate);

		btnSave = new JButton("Save");

		btnSave.setBounds(120, 320, 120, 40);

		add(btnSave);

		setVisible(true);
	}
}