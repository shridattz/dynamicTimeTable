package dynamicTT;

public class ClassRoom {
	
	//represents each classroom
	
private String roomID;
private int size;
private boolean islaboratory;
private Week week;
private String department;

public ClassRoom(String id, int i, boolean islab, String dept){
	this.roomID=id;
	this.setWeek(new Week());
	this.setSize(i);
	this.islaboratory=islab;
	this.department=dept;
}


public boolean isLaboratory() {
	return islaboratory;
}
public void setLaboratory(boolean laboratory) {
	this.islaboratory = laboratory;
}

public String getRoomNo() {
	return roomID;
}
public void setRoomNo(String roomNo) {
	this.roomID = roomNo;
}

public Week getWeek() {
	return week;
}

public void setWeek(Week week) {
	this.week = week;
}

public int getSize() {
	return size;
}

public void setSize(int size) {
	this.size = size;
}


public String getDepartment() {
	return department;
}


public void setDepartment(String department) {
	this.department = department;
}
}
