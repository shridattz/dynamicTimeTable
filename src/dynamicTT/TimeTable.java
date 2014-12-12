package dynamicTT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class TimeTable {
	private ArrayList<ClassRoom> rooms = new ArrayList<ClassRoom>();
	private int fittness;
	private ArrayList<Lecture> classes=new ArrayList<>();
	private ArrayList<StudentGroups> studentGroups=new ArrayList<>(); 
	private ArrayList<ClassRoom> practicalRooms = new ArrayList<ClassRoom>();
	private ArrayList<ClassRoom> theoryRooms = new ArrayList<ClassRoom>();
	private ArrayList<StudentGroups> theoryStudentGroups=new ArrayList<>(); 
	private ArrayList<StudentGroups> practicalStudentGroups=new ArrayList<>(); 
	private HashMap<Combination, Week> personalTimeTable= new HashMap<Combination, Week>();
	//private ArrayList<Professor> professors=new ArrayList<>();
	//adds more rooms to timetable

	public TimeTable(ArrayList<ClassRoom> classroom, ArrayList<Lecture> lectures){//, ArrayList<Professor> professors){
		this.rooms=classroom;
		this.classes=lectures;
		this.fittness=999;
//		this.professors=professors;
	}
	
//	public void initialization(ArrayList<ClassRoom> classroom, ArrayList<Lecture> lectures){
//		this.rooms=classroom;
//		this.classes=lectures;
//		this.fittness=999;
//	}
	
	public int getFittness() {
		return fittness;
	}
	
	public void setFittness(int fittness) {
		this.fittness = fittness;
	}

	public void addStudentGroups(ArrayList<StudentGroups> studentgrps) {
		// TODO Auto-generated method stub
		studentGroups.addAll(studentgrps);
	}
	
	public void initializeTimeTable(){
		for (Iterator<ClassRoom> roomsIterator = rooms.iterator(); roomsIterator.hasNext();) {
			ClassRoom room = roomsIterator.next();
			if(room.isLaboratory()){
				practicalRooms.add(room);
			}
			else{
				theoryRooms.add(room);
			}
		}
		for (Iterator<StudentGroups> studentGroupIterator = studentGroups.iterator(); studentGroupIterator.hasNext();) {
			StudentGroups studentGroup = studentGroupIterator.next();
			if(studentGroup.isPractical()){
				practicalStudentGroups.add(studentGroup);
			}
			else{
				theoryStudentGroups.add(studentGroup);
			}
		}
		rooms.clear();
		//studentGroups.clear();
		setTimeTable(practicalStudentGroups, practicalRooms, "practical");
		setTimeTable(theoryStudentGroups, theoryRooms, "theory");
		rooms.addAll(practicalRooms);
		rooms.addAll(theoryRooms);
		//studentGroups.addAll(practicalStudentGroups);
		//studentGroups.addAll(theoryStudentGroups);
	}
	
	public void setTimeTable(ArrayList<StudentGroups> studentGroups2, ArrayList<ClassRoom> rooms2, String string) {
		// TODO Auto-generated method stub
		Collections.shuffle(studentGroups2);
		Stack<Lecture> lecturesStack=new Stack<Lecture>();
		for (Iterator<StudentGroups> sdtGrpIterator = studentGroups2.iterator(); sdtGrpIterator.hasNext();) {			
			StudentGroups studentGrp = sdtGrpIterator.next();
			String subject = studentGrp.getSubjectName();
			int noOfLectures = studentGrp.getNoOfLecturePerWeek();
			for(int i=0; i<noOfLectures; i++){
				Collections.shuffle(classes);
				Iterator<Lecture> classIterator = classes.iterator();
				while(classIterator.hasNext()){
					Lecture lecture = classIterator.next();
					if(lecture.getSubject().equalsIgnoreCase(subject)){
						Lecture mainLecture=new Lecture(lecture.getProfessor(), lecture.getSubject());
						mainLecture.setStudentGroup(studentGrp);
						lecturesStack.push(mainLecture);
						break;
					}
				}
			}
		}
		while(!(lecturesStack.empty())){
				Collections.shuffle(lecturesStack);
				Lecture lecture2 = lecturesStack.pop();
				if(string.equalsIgnoreCase("theory")){
					placeTheoryLecture(lecture2, rooms2);
				}
				if(string.equalsIgnoreCase("practical")){
					placePracticalLecture(lecture2, rooms2);
				}
		}	
	}	
	
		
	
	private void placePracticalLecture(Lecture lecture2, ArrayList<ClassRoom> rooms2) {
		// TODO Auto-generated method stub
		int size = lecture2.getStudentGroup().getSize();
		String dept=lecture2.getStudentGroup().getDepartment();
		int i=0;
		boolean invalid=true;
		ClassRoom room = null;
		Collections.shuffle(rooms2);
		while(invalid){
		room=getBestRoom(size, rooms2);
		if(room.getDepartment().equalsIgnoreCase(dept)){
			invalid=false;
			Collections.shuffle(rooms2);
			}
		else{
			Collections.shuffle(rooms2);
			}
		}
		ArrayList<Day> weekdays = room.getWeek().getWeekDays();
		Iterator<Day> daysIterator=weekdays.iterator();
		while(daysIterator.hasNext() && i<3){
			Day day = daysIterator.next();
			ArrayList<TimeSlot> timeslots = day.getTimeSlot();
			Iterator<TimeSlot> timeslotIterator= timeslots.iterator();
			while(timeslotIterator.hasNext() && i<3){
				TimeSlot lecture3 = timeslotIterator.next();
				if(lecture3.getLecture()==null){
				lecture3.setLecture(lecture2);
				i++;
				}
			}
		}		
	}



	private void placeTheoryLecture(Lecture lecture, ArrayList<ClassRoom> rooms2) {
		// TODO Auto-generated method stub
		int size = lecture.getStudentGroup().getSize();
		String dept=lecture.getStudentGroup().getDepartment();
		boolean invalid=true;
		ClassRoom room = null;
		Collections.shuffle(rooms2);
		while(invalid){
			room=getBestRoom(size, rooms2);
			if(room.getDepartment().equalsIgnoreCase(dept)){
				invalid=false;
				Collections.shuffle(rooms2);
				}
			else{
				Collections.shuffle(rooms2);
				}
			}
		ArrayList<Day> weekdays = room.getWeek().getWeekDays();
		Iterator<Day> daysIterator=weekdays.iterator();
		while(daysIterator.hasNext()){
			Day day = daysIterator.next();
			ArrayList<TimeSlot> timeslots = day.getTimeSlot();
			Iterator<TimeSlot> timeslotIterator= timeslots.iterator();
			while(timeslotIterator.hasNext()){
				TimeSlot lecture2 = timeslotIterator.next();
				if(lecture2.getLecture()==null){
				lecture2.setLecture(lecture);
				return;				
				}
			}
		}		
	}



	private boolean checkOccupiedRoom(ClassRoom tempRoom, ArrayList<ClassRoom> rooms2) {
		// TODO Auto-generated method stub
		for (Iterator<ClassRoom> roomsIterator = rooms2.iterator(); roomsIterator.hasNext();){
			ClassRoom room = roomsIterator.next();
			if(room.equals(tempRoom)){
			ArrayList<Day> weekdays = room.getWeek().getWeekDays();
			Iterator<Day> daysIterator=weekdays.iterator();
			while(daysIterator.hasNext()){
				Day day = daysIterator.next();
				ArrayList<TimeSlot> timeslots = day.getTimeSlot();
				Iterator<TimeSlot> timeslotIterator= timeslots.iterator();
				while(timeslotIterator.hasNext()){
					TimeSlot lecture = timeslotIterator.next();
					if(lecture.getLecture()==null){
						return false;
					}
				}
			}
			return true;
			}		
		}
		return false;
	}



	private ClassRoom getBestRoom(int size, ArrayList<ClassRoom> rooms2) {
		// TODO Auto-generated method stub
		int delta = 1000;
		ClassRoom room = null;
		for (Iterator<ClassRoom> roomsIterator = rooms2.iterator(); roomsIterator.hasNext();){
			ClassRoom tempRoom = roomsIterator.next();
			if(!checkOccupiedRoom(tempRoom, rooms2)){
		        int tmp = Math.abs(size - tempRoom.getSize());
		        if(tmp < delta){
		            delta = tmp;
		            room = tempRoom;
		    }
			}
		}
		return room;
	}

//	public void createTimeTableGroups(ArrayList<Combination> combinations2){
////		ArrayList<Combination> combinations=new ArrayList<>();
////		
////			for(Iterator<Combination> combItr = combinations2.iterator(); combItr.hasNext();){
////				 Combination comb = combItr.next();
////				if(!combinations.contains(comb)){
////					combinations.add(comb);
////				}
////			}
//		
//		
////		for(Iterator<Combination> combIterator = combinations2.iterator(); combIterator.hasNext();){
////			Combination combtn = combIterator.next();
////			personalTimeTable.put(combtn, new Week());
////		}
//		
//		for(Iterator<Combination> combIterator = combinations2.iterator(); combIterator.hasNext();){
//			Combination combtn = combIterator.next();
//			for (Iterator<ClassRoom> roomsIterator = theoryRooms.iterator(); roomsIterator.hasNext();){
//				ClassRoom room=roomsIterator.next();
//				Iterator<Day> daysIterator = room.getWeek().getWeekDays().iterator();
//				while(daysIterator.hasNext()){
//					Day day = daysIterator.next();
//					ArrayList<TimeSlot> timeslots = day.getTimeSlot();
//					Iterator<TimeSlot> timeslotIterator= timeslots.iterator();
//					while(timeslotIterator.hasNext()){
//						TimeSlot lecture = timeslotIterator.next();
//						if(lecture.getLecture()==null){
//							System.out.print(" free ");
//						}
//						else if(lecture.getLecture().getStudentGroup().getCombination().contains(combtn)){
//							System.out.print("###Room="+room.getRoomNo()/*+" Day="+day.getName()+" Time="+lecture.getSlotTime()*/+" Professor="+lecture.getLecture().getProfessor()+" Subject="+lecture.getLecture().getSubject());
//						}
//						else{
//							System.out.print(" free ");
//						}
//					}
//					System.out.print("\n");
//				}
//			}
//		}
//	}
	
//	private void putInPersonalTimeTable(Combination combtn, String roomNo, String name, TimeSlot lecture) {
//		// TODO Auto-generated method stub
//		Week week = personalTimeTable.get(combtn);
//		Iterator<Day> daysIterator=week.getWeekDays().iterator();
//		while(daysIterator.hasNext()){
//			Day day = daysIterator.next();
//			if(day.getName().equalsIgnoreCase(name)){
//				Iterator<TimeSlot> timeslotIterator=day.getTimeSlot().iterator();
//				while(timeslotIterator.hasNext()){
//					TimeSlot lecture2 = (TimeSlot) timeslotIterator.next();
//					if(lecture2)
//				}
//			}
//		}
//	}
		

	//creates random assignment of lecture using lecture objects, subjects and number of lectures per week to a room
	
//	private ClassRoom randomTimetable(ClassRoom room, ArrayList<Subject> subjectsTaught, ArrayList<Lecture> lectureList) {
//		Iterator subIterator=subjectsTaught.iterator();
//		Stack<Lecture> lecturesStack=new Stack();
//		while(subIterator.hasNext()){
//			Subject subject = (Subject) subIterator.next();
//			int noOfLecturesPerWeek = subject.getNumberOfLecturesPerWeek();
//			for(int i=0; i<noOfLecturesPerWeek; i++){
//				Collections.shuffle(lectureList);
//				Iterator<Lecture> classIterator = lectureList.iterator();
//				while(classIterator.hasNext()){
//					Lecture getLecture = classIterator.next();
//					if(getLecture.getSubject().equalsIgnoreCase(subject.getSubjectName())){
//						lecturesStack.push(getLecture);
//						break;
//					}
//				}
//			}
//		}
//		
//		Collections.shuffle(lecturesStack);
//		ArrayList<Day> weekdays = room.getWeek().getWeekDays();
//		Iterator<Day> daysIterator=weekdays.iterator();
//		while(daysIterator.hasNext()){
//			Day day = daysIterator.next();
//			ArrayList<TimeSlot> timeslots = day.getTimeSlot();
//			Iterator timeslotIterator= timeslots.iterator();
//			while(timeslotIterator.hasNext() && !(lecturesStack.isEmpty())){
//				TimeSlot lecture = (TimeSlot) timeslotIterator.next();
//				lecture.setLecture(lecturesStack.pop());
//				Collections.shuffle(lecturesStack);
//			}
//		}		
//		return room;
//	}
		
	

	public ArrayList<ClassRoom> getRoom() {
		return rooms;
	}

	public void setRoom(ArrayList<ClassRoom> room) {
		this.rooms = room;
	}



	public ArrayList<ClassRoom> getPracticalRooms() {
		return practicalRooms;
	}



	public void setPracticalRooms(ArrayList<ClassRoom> practicalRooms) {
		this.practicalRooms = practicalRooms;
	}



	public ArrayList<ClassRoom> getTheoryRooms() {
		return theoryRooms;
	}



	public void setTheoryRooms(ArrayList<ClassRoom> theoryRooms) {
		theoryRooms = theoryRooms;
	}



	public ArrayList<StudentGroups> getTheoryStudentGroups() {
		return theoryStudentGroups;
	}



	public void setTheoryStudentGroups(ArrayList<StudentGroups> theoryStudentGroups) {
		this.theoryStudentGroups = theoryStudentGroups;
	}



	public ArrayList<StudentGroups> getPracticalStudentGroups() {
		return practicalStudentGroups;
	}



	public void setPracticalStudentGroups(ArrayList<StudentGroups> practicalStudentGroups) {
		this.practicalStudentGroups = practicalStudentGroups;
	}
}
