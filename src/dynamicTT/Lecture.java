package dynamicTT;

public class Lecture {
	private Professor professor;
	private String subject;
	private StudentGroups studentGroup;
	
	//represents one lecture having professor and subject
	
	Lecture(Professor prof, String sub){
		this.professor=prof;
		this.subject=sub;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public StudentGroups getStudentGroup() {
		return studentGroup;
	}

	public void setStudentGroup(StudentGroups studentGroup) {
		this.studentGroup = studentGroup;
	}
}
