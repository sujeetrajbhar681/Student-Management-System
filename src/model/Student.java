package model;

public class Student {

	private int studentId;
	private String name;
	private String email;
	private String course;
	private String phone;
	private String enrollmentDate;

	public Student() {
	}

	public Student(String name, String email, String course, String phone, String enrollmentDate) {

		this.name = name;
		this.email = email;
		this.course = course;
		this.phone = phone;
		this.enrollmentDate = enrollmentDate;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
}