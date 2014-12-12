package dynamicTT;

import java.util.ArrayList;

public class Day {
	//private String name;
	private ArrayList <TimeSlot> timeSlot=new ArrayList();
	
	public ArrayList<TimeSlot> getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(ArrayList<TimeSlot> timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Day(String inputname){
//		this.setName(inputname);
		for(int i=9; i<16; i++){
			if(i!=12){
				TimeSlot ts=new TimeSlot(/*i*/);
				timeSlot.add(ts);
			}
			
		}
//		TimeSlot ts1=new TimeSlot(12);
//		ts1.setSubject("BREAK");
//     	timeSlot.add(ts1);
//		for(int i=1; i<4; i++){
//			TimeSlot ts=new TimeSlot(i);
//			timeSlot.add(ts);
//		}
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
}
