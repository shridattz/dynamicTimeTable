package dynamicTT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;



public class Initialization {
	
	//this class takes all inputs from a file. courseID, courseName, roomID's, subjects and professors associated with course
	//currently hardcoded by taking one course with 6 subjects and 6 teachers
	
	private ArrayList<Subject> subjects=new ArrayList();
	private ArrayList<Professor> professors=new ArrayList();
	private ArrayList<TimeTable> timetables=new ArrayList();
	private ArrayList<Lecture> classes=new ArrayList<>();
	private ArrayList<Combination> combinations=new ArrayList<>();
			
	//reads input from a file.
	
	public void readInput() throws IOException{
				
		ArrayList<ClassRoom> classroom=new ArrayList<>();
		ClassRoom room1 = new ClassRoom("D101", 20, false, "Common");
		classroom.add(room1);
		ClassRoom room2 = new ClassRoom("E101", 20, false, "ComputerScience");
		classroom.add(room2);
		ClassRoom room3 = new ClassRoom("LAB1", 20, true, "ComputerScience");
		classroom.add(room3);
//		ClassRoom room4 = new ClassRoom("LAB2", 20, true);
//		classroom.add(room4);
//		ClassRoom room5 = new ClassRoom("G101", 20, false);
//		classroom.add(room5);
//		ClassRoom room6 = new ClassRoom("H101", 20, false);
//		classroom.add(room6);
//		ClassRoom room6 = new ClassRoom("I101", 60, false);
//		classroom.add(room6);
		
		
		professors.add(new Professor(1, "Shruti", "IR/IRlab/DM"));
		professors.add(new Professor(2, "Snehal", "P&S"));
		professors.add(new Professor(3, "Ramrao", "DS"));
		professors.add(new Professor(4, "Ranjit", "WR"));
		professors.add(new Professor(5, "Shekhar", "TOC"));
		professors.add(new Professor(6, "Monica", "SS"));
		professors.add(new Professor(7, "Ravi", "R"));
		professors.add(new Professor(8, "Amit", "ML/MLlab"));
		professors.add(new Professor(9, "Rama", "DAA/UML"));
		
		createLectures(professors);
		
		TimeTable timetb1=new TimeTable(classroom, classes);//, professors);
		//timetb1.initialization(classroom, classes);
		//TimeTable timetb2=new TimeTable(classroom, classes);
		//TimeTable timetb3=new TimeTable(classroom, classes);
				
		int courseid = 1;
		String courseName="MSc.I.T. Part I";
		System.out.println("reading input.......");
		subjects.add(new Subject(1,"IR",4,false, "ComputerScience"));
		subjects.add(new Subject(2,"P&S",4,false,"ComputerScience"));
		subjects.add(new Subject(3,"DS",4,false,"ComputerScience"));
		subjects.add(new Subject(4,"WR",1,false,"Common"));
		subjects.add(new Subject(5,"TOC",4,false,"ComputerScience"));
		subjects.add(new Subject(6,"IRlab",3,true,"ComputerScience"));
		subjects.add(new Subject(7,"JAVA",3,true,"ComputerScience"));

			
		System.out.println("new course creation.......");
		Course course1 = new Course(courseid, courseName, subjects);
		course1.createCombination("IR/P&S/DS/WR/TOC/IRlab/JAVA/", 20);		
		course1.createStudentGroups();		
		ArrayList<StudentGroups> studentGroups = course1.getStudentGroups();
		timetb1.addStudentGroups(studentGroups);
		//combinations.addAll(course1.getCombinations());
		
		//timetb2.addStudentGroups(studentGroups);
		///timetb3.addStudentGroups(studentGroups);
		subjects.clear();
		
		subjects.add(new Subject(8,"DM",4,false,"ComputerScience"));
		subjects.add(new Subject(9,"DAA",4,false,"ComputerScience"));
		subjects.add(new Subject(10,"SS",1,false,"ComputerScience"));
		subjects.add(new Subject(11,"ML",4,false,"Common"));
		subjects.add(new Subject(12,"UML",4,false,"ComputerScience"));
		subjects.add(new Subject(13,"MLlab",3,true,"ComputerScience"));
		subjects.add(new Subject(14,"R",3,true,"ComputerScience"));
		
		Course course2 = new Course(2, "MSc.I.T. Part II", subjects);
		course2.createCombination("DM/DAA/SS/ML/UML/MLlab/R/", 20);
		course2.createStudentGroups();
		studentGroups = course2.getStudentGroups();
		timetb1.addStudentGroups(studentGroups);
		//combinations.addAll(course2.getCombinations());
		//timetb2.addStudentGroups(studentGroups);
		//timetb3.addStudentGroups(studentGroups);
		
		System.out.println("Setting tt.......");
		
		System.out.println("adding tt.......");
		timetb1.initializeTimeTable();
		//timetb2.initializeTimeTable();
		//timetb3.initializeTimeTable();
		timetables.add(timetb1);
		//timetable.add(timetb2);
		//timetable.add(timetb3);
		
				
		System.out.println("populating.......");
		
		
		
		//display();
		
		populateTimeTable(timetb1);
		GeneticAlgorithm ge=new GeneticAlgorithm();
		
		//ge.fitness(timetb1);
//		timetb1.createTimeTableGroups(combinations);
		ge.populationAccepter(timetables);
//		//ge.fitness(timetb2);
		
		//ge.fitness(timetb3);
		
		//populateTimeTable();
	}
	
	public void populateTimeTable(TimeTable timetb1){
		int i=0;
		System.out.println("populating started.......");
		while(i<3){
			TimeTable tempTimetable = timetb1;
			ArrayList<ClassRoom> allrooms = tempTimetable.getRoom();
			Iterator<ClassRoom> allroomsIterator = allrooms.iterator();
			while(allroomsIterator.hasNext()){
				ClassRoom room = allroomsIterator.next();
				ArrayList<Day> weekdays = room.getWeek().getWeekDays();
				Collections.shuffle(weekdays);
				if(!room.isLaboratory()){
					Iterator<Day> daysIterator=weekdays.iterator();
					while(daysIterator.hasNext()){
						Day day = daysIterator.next();
						Collections.shuffle(day.getTimeSlot());
					}
				}				
			}
			timetables.add(tempTimetable);
			i++;
		}
		System.out.println("populating done.......");
		System.out.println("display called.......");
		display();
	}
	
	private void createLectures (ArrayList<Professor> professors) {
		// TODO Auto-generated method stub
		
		java.util.Iterator<Professor> professorIterator=professors.iterator();
		while(professorIterator.hasNext()){
			Professor professor=professorIterator.next();
			ArrayList<String> subjectsTaught = professor.getSubjectTaught();
			Iterator<String> subjectIterator = subjectsTaught.iterator();
			while(subjectIterator.hasNext()){
				String subject = subjectIterator.next();
				classes.add(new Lecture (professor, subject));
			}
		}
	}
	
	//creates another 3 timetable objects for population by taking first yimetable and shuffling it.
	
//	public void populateTimeTable(){
//		int i=0;
//		System.out.println("populating started.......");
//		while(i<6){
//			TimeTable tempTimetable = timetbl;
//			ArrayList<ClassRoom> allrooms = tempTimetable.getRoom();
//			Iterator<ClassRoom> allroomsIterator = allrooms.iterator();
//			while(allroomsIterator.hasNext()){
//				ClassRoom room = allroomsIterator.next();
//				ArrayList<Day> weekdays = room.getWeek().getWeekDays();
//				Iterator<Day> daysIterator=weekdays.iterator();
//				while(daysIterator.hasNext()){
//					Day day = daysIterator.next();
//					Collections.shuffle(day.getTimeSlot());
//				}
//			}
//			timetable.add(tempTimetable);
//			i++;
//		}
//		System.out.println("populating done.......");
//		System.out.println("display called.......");
//		display();
//		
//		GeneticAlgorithm.populationAccepter(timetable);
//	}
	
	//displays all timetables
	
	private void display() {
		// TODO Auto-generated method stub
		int i=1;
		System.out.println("displaying all tt's.......");
		Iterator<TimeTable> timetableIterator = timetables.iterator();
		while(timetableIterator.hasNext()){
			System.out.println("+++++++++++++++++++++++++++++++++++++++++\nTime Table no. "+i);
			TimeTable currentTimetable = timetableIterator.next();
			System.out.println("Score : "+currentTimetable.getFittness());
			ArrayList<ClassRoom> allrooms = currentTimetable.getRoom();
			Iterator<ClassRoom> allroomsIterator = allrooms.iterator();
			while(allroomsIterator.hasNext()){
				ClassRoom room = allroomsIterator.next();
				System.out.println("Room: "+room.getRoomNo());
				ArrayList<Day> weekdays = room.getWeek().getWeekDays();
				Iterator<Day> daysIterator=weekdays.iterator();
				while(daysIterator.hasNext()){
					Day day = daysIterator.next();
					ArrayList<TimeSlot> timeslots = day.getTimeSlot();
					Iterator<TimeSlot> timeslotIterator= timeslots.iterator();
					//System.out.print(""+day.getName()+": ");
					while(timeslotIterator.hasNext()){
						TimeSlot lecture = (TimeSlot) timeslotIterator.next();
						if(lecture.getLecture()!=null){
						//System.out.print(" (Subject: "+lecture.getLecture().getSubject()+" --> Professor: "+lecture.getLecture().getProfessor().getProfessorName()+" GrpName: "+lecture.getLecture().getStudentGroup().getName()+")");
							System.out.print("("+lecture.getLecture().getSubject()+"#"+lecture.getLecture().getProfessor().getProfessorName()+"#"+lecture.getLecture().getStudentGroup().getName().split("/")[0]+")");
						}
						else{
							System.out.print("   free   ");
						}
					}
					System.out.print("\n");
				}
				System.out.print("\n\n");
			}
			i++;
		}
	}
}
