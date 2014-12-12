package dynamicTT;

import java.util.ArrayList;

public class Professor {
	private int professorID;
	private String professorName;
	private ArrayList <String> subjectsTaught = new ArrayList();
	
	Professor(int id, String name, String subj){
		this.professorID=id;
		this.professorName=name;
		String[] subjectNames=subj.split("/");
		for(int i=0; i<subjectNames.length; i++){
			this.subjectsTaught.add(subjectNames[i]);
		}
	}
	
	public int getProfessorID() {
		return professorID;
	}
	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public ArrayList<String> getSubjectTaught() {
		return subjectsTaught;
	}

	public void setSubjectTaught(ArrayList<String> subjectTaught) {
		this.subjectsTaught = subjectTaught;
	}
	
	
}
