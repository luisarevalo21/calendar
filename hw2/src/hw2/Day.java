package hw2;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Icon;

/**
 * 
 * @author aldonut
 * class makes day obj which holds day 
 * and events within the day 
 *
 */
public class Day implements Comparable<Day>
{
	private String day;
	private ArrayList<Event> events = null;
	private int eventCount = 0;
	private int[] arrDate = new int[3];
	
	/**
	 * 
	 * @param day string representation of current day
	 */
	public Day(String day)
	{
		events = new ArrayList<>();
		this.day = day;
		setArrDate();
	}
	
	
	
	/**
	 * 
	 * @return string rep of this day
	 */
	public String getDate()
	{
		return day;
	}
	
	/**
	 * 
	 * @param day to compare 
	 * @return true/false depending on if they're equal
	 */
	public boolean equals(Day day)
	{
		if(this.getDate().equals(day.getDate()))
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param event event to be added to arraylist
	 */
	public void addEvent(Event event)
	{
		events.add(event);
		eventCount++;
		
	}
	
	/**
	 * 
	 * @return int rep of month of date
	 */
	public int getMonth()
	{
		return arrDate[0];
	}
	
	/**
	 * 
	 * @return in rep of day of date
	 */
	public int getDay()
	{
		return arrDate[1];
	}
	
	/**
	 * 
	 * @return int rep of year of date
	 */
	public int getYear()
	{
		return arrDate[2];
	}
	
	/**
	 * 
	 * @return returns amount of events in current day
	 */
	public int getEventCount()
	{
		return eventCount;
	}
	
	
	/**
	 * prints out all the events in this day
	 */
	public void returnEventList()
	{
	     Collections.sort(events);
	     
		for(Event current: events)
		{
			System.out.println(current.toString());
		}
	}

	/**
	 * turns a strings rep into arr rep of date
	 * in order to compare
	 */
	public void setArrDate()
	{
		String strMonth = day.substring(0, 2);
		String strDay = day.substring(3, 5);
		String strYear = day.substring(6, 10);
				
		int intMonth = Integer.parseInt(strMonth);
		int intDay = Integer.parseInt(strDay);
		int intYear = Integer.parseInt(strYear);
		
		arrDate[0] = intMonth;
		arrDate[1] = intDay;
		arrDate[2] = intYear;

		
	}
	
	/**
	 * @return returns negative num is o is after
	 * @param o the day being compared
	 * returns positive num if o is before
	 * returns zero if they're equal
	 */
	public int compareTo(Day o) 
	{	
		if(this.getYear() == o.getYear())
		{
			if(this.getMonth() == o.getMonth())
			{
				return this.getDay() - o.getDay();
			}
			else
			{
				return this.getMonth() - o.getMonth();
			}
		}
		else
		{
			return this.getYear() - o.getYear();
		}
		
	}
	
	
	/**
	 * @return string rep of date
	 */
	public String toString()
	{
		return day;
	}
	
	/**
	 * 
	 * @param writer a filewriter 
	 * @throws IOException throws the IOException
	 */
	public void write(FileWriter writer) throws IOException
	{

	    
		for(int j = 0; j < eventCount; j++)
		{	
			Event eventToAdd = events.get(j);
		     writer.write(eventToAdd.getDescription() + "\n");
		     writer.write(eventToAdd.getStrStartTime() + "\n");
		     writer.write(eventToAdd.getStrEndTime() + "\n");
	}

	}
	    /**
	     * sorts the events within day
	     */
		public void sort()
		{
			Collections.sort(events);
		}

		public ArrayList<Event> getEventsArr()
		{
			return events;
		}

}
