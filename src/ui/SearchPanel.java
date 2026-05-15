package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SearchPanel extends JFrame {

	JTable table;
	DefaultTableModel model;

	JTextField txtSearch;

	JButton btnSearch, btnDelete, btnUpdate, btnRefresh, btnAdd;

	public SearchPanel() {

		setTitle("Student CRUD Management");
		setSize(950, 500);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		JPanel topPanel = new JPanel();

		JLabel lblSearch = new JLabel("Search (ID / Name / Course)");

		txtSearch = new JTextField(20);

		btnSearch = new JButton("Search");
		btnRefresh = new JButton("Refresh");
		btnAdd = new JButton("Add Student");

		topPanel.add(lblSearch);
		topPanel.add(txtSearch);
		topPanel.add(btnSearch);
		topPanel.add(btnRefresh);
		topPanel.add(btnAdd);

		add(topPanel, BorderLayout.NORTH);

		model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Email");
		model.addColumn("Course");
		model.addColumn("Phone");
		model.addColumn("Enrollment Date");

		table = new JTable(model);

		JScrollPane pane = new JScrollPane(table);
		add(pane, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel();

		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");

		bottomPanel.add(btnUpdate);
		bottomPanel.add(btnDelete);

		add(bottomPanel, BorderLayout.SOUTH);

		loadStudents();

		btnAdd.addActionListener(e -> {
			new StudentForm();
		});

		btnSearch.addActionListener(e -> {

			String keyword = txtSearch.getText().trim();

			model.setRowCount(0);

			StudentDAO dao = new StudentDAO();

			ArrayList<Student> list = dao.searchStudents(keyword);

			if (list.isEmpty()) {

				JOptionPane.showMessageDialog(null, "No Records Found");

			} else {

				for (Student s : list) {

					model.addRow(new Object[] { s.getStudentId(), s.getName(), s.getEmail(), s.getCourse(),
							s.getPhone(), s.getEnrollmentDate() });
				}
			}
		});

		btnRefresh.addActionListener(e -> {
			model.setRowCount(0);
			loadStudents();
		});

		btnUpdate.addActionListener(e -> {

			int row = table.getSelectedRow();

			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Please Select Student");
				return;
			}

			Student s = new Student();

			s.setStudentId(Integer.parseInt(model.getValueAt(row, 0).toString()));
			s.setName(model.getValueAt(row, 1).toString());
			s.setEmail(model.getValueAt(row, 2).toString());
			s.setCourse(model.getValueAt(row, 3).toString());
			s.setPhone(model.getValueAt(row, 4).toString());
			s.setEnrollmentDate(model.getValueAt(row, 5).toString());

			new StudentForm(s);

			dispose();
		});

		btnDelete.addActionListener(e -> {

			int row = table.getSelectedRow();

			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Please Select Student");
				return;
			}

			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student?",
					"Confirm Delete", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {

				int id = Integer.parseInt(model.getValueAt(row, 0).toString());

				StudentDAO dao = new StudentDAO();

				if (dao.deleteStudent(id)) {

					JOptionPane.showMessageDialog(null, "Student Deleted Successfully");

					model.removeRow(row);

				} else {
					JOptionPane.showMessageDialog(null, "Delete Failed");
				}
			}
		});

		setVisible(true);
	}

	public void loadStudents() {

		StudentDAO dao = new StudentDAO();

		ArrayList<Student> list = dao.getAllStudents();

		for (Student s : list) {

			model.addRow(new Object[] { s.getStudentId(), s.getName(), s.getEmail(), s.getCourse(), s.getPhone(),
					s.getEnrollmentDate() });
		}
	}
}