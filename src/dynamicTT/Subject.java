package dynamicTT;

public class Subject {
	
	private int subjectID;
	private String subjectName;
	private int numberOfLecturesPerWeek;
	private boolean islab;
	private String department;
	
	Subject(int id, String name, int lectures, boolean lab, String dept){
		this.subjectID=id;
		this.subjectName=name;
		this.numberOfLecturesPerWeek=lectures;
		this.islab=lab;
		this.department=dept;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getNumberOfLecturesPerWeek() {
		return numberOfLecturesPerWeek;
	}
	public void setNumberOfLecturesPerWeek(int numberOfLecturesPerWeek) {
		this.numberOfLecturesPerWeek = numberOfLecturesPerWeek;
	}
	public int getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public boolean isIslab() {
		return islab;
	}

	public void setIslab(boolean islab) {
		this.islab = islab;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
}
