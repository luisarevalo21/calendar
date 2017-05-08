package hw2;

import java.text.ParseException;
import java.util.Scanner;

/**
 * 
 * @author aldonut the class creates event obj
 * with all info about event
 *
 */
public class Event implements Comparable<Event>
{
	private String strStartTime = "";
	private String strEndTime= " - (undifined)";
	private String description ="";
	private int[] arrStartTime = new int[2];
	private int[] arrEndTime = new int[2];
	private String strDate = "";
	
	
	/**
	 * creates blank event obj
	 */
	public Event() 
	{
		
	}
	
	/**
	 * 
	 * @param date event date
	 * @param description event description
	 * @param startTime event start time 
	 * @param endTime event end time
	 */
	public Event(String date, String description, String startTime, String endTime)
	{
		this.strDate = date;
		this.description = description;
		strStartTime = startTime;
		strEndTime = endTime;
	}
	
	/**
	 * 
	 * @param in the scanner 
	 * @return strdDate a string rep of the date 
	 * @throws ParseException throws pasrse exception
	 */
	public String requestInfo(Scanner in) throws ParseException
	{
		String start = "";
		
		System.out.println("\n\nEnter the date of your event in the form of"
				+ "\"MM/DD/YYYY\"");
        String strDate = in.nextLine();
        
		System.out.println("Please include a description for your event");
		description = in.nextLine();
		
		System.out.println("Enter the start time of your event in military time."
				+ "\n 8:30pm would be entered as \"20:30\" and 6:25am would be "
				+ "entered as \"06:25\". ");
		strStartTime = in.nextLine();
		arrStartTime = stringToIntTime(strStartTime);
		
		do
		{
		System.out.println("Would you like to enter an end time? [y/n]");
		start = in.nextLine();
		}
		while(!start.equals("y") && !start.equals("n"));
		
		if(start.equals("y"))
		{
			System.out.println("Enter the end time of your event in military time."
				+ "\n8:30pm would be entered as \"20:30\" and 6:25am would be "
				+ "entered as \"06:25\". \n");
			strEndTime = in.nextLine();
			arrEndTime = stringToIntTime(strEndTime);
		}
		
		
		System.out.println("You have successfully added the following event: \n\n"
				+ toString());
		
		System.out.println("\n\n\nSelect one of the following options: \n"
				+ "[l]oad   [v]iew by  [c]reate, [g]o to [e]vent list [d]elete  [q]uit");
		
		return strDate;
	}
	 
	/**
	 * 
	 * @param strTime string rep of the time 
	 * @return array rep of the time
	 */
	public int[] stringToIntTime(String strTime)
	{
		int[] times = new int[2];
		
		String strHour = strTime.substring(0, 2);
		String strMin  = strTime.substring(3,5);
		int intHour = Integer.parseInt(strHour);
		int intMin = Integer.parseInt(strMin);
		
		times[0] = intHour;
		times[1] = intMin;
		
		return times;
		
	}
	
	/**
	 * 
	 * @return string rep of the date
	 */
	public String getStrDate()
	{
		return strDate;
	}
	
	/**
	 * 
	 * @return array rep of start time 
	 */
	public int[] getArrStartTime()
	{
		return arrStartTime;
	}

	/**
	 * 
	 * @return array rep of end time 
	 */
	public int[] getArrEndTime()
	{
		return arrEndTime;
	}
	
	/**
	 * 
	 * @return string rep of start time 
	 */
	public String getStrStartTime()
	{
		return strStartTime;
	}
	
	/**
	 * 
	 * @return String rep of end time 
	 */
	public String getStrEndTime()
	{
		return strEndTime;
	}
	
	/**
	 * 
	 * @return string rep of description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * compares events by start time 
	 */
	public int compareTo(Event o)
	{
		Event other = o;
		int [] thisStart = this.getArrStartTime();
		int [] otherStart = other.getArrStartTime();
		if(thisStart[0] == otherStart[0])
		{
			if(thisStart[1] < otherStart[1])
				return -1;
			return 1;
		}
		else
		{
			if(thisStart[0] < otherStart[0])
				return -1;
			return 1;
		}
		
	}
	
	/**
	 * to string rep of the event
	 */
	public String toString()
	{
		return  strDate + " " + description + " "  + strStartTime + " " + strEndTime ;
	}

	
}
