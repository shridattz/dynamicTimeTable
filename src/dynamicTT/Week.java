package dynamicTT;


import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class Week {
	private String[] weekDaysName;
	private ArrayList <Day> weekDays=new ArrayList<Day>();
	
public ArrayList<Day> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(ArrayList<Day> weekDays) {
		this.weekDays = weekDays;
	}

public Week(){
	this.weekDaysName = new DateFormatSymbols().getWeekdays();
    for (int i = 1; i < weekDaysName.length; i++) {
        //System.out.println("weekday = " + weekDaysName[i]);
    	if(!(weekDaysName[i].equalsIgnoreCase("Sunday"))){
    	Day newday=new Day(weekDaysName[i]);
    	weekDays.add(newday);
    		}
    	}
	}
}
