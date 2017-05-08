package hw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model 
{
	
	private Calendar today;
	private Month testMonth;
	private int dayCount = 0;
	private Scanner in = null;
	File eventsTxt = null;
	private ArrayList<Day> usedDays;
	private Day chosenDay;
	private int[][] cellDays;
	private ArrayList<ChangeListener> listeners;
	private Calendar localCal = Calendar.getInstance();

	
	public Model()
	{
		usedDays = new ArrayList<>();
		today = Calendar.getInstance();
		testMonth = new Month();
		testMonth.printMonth(true);
		cellDays = new int[6][7];
		listeners = new ArrayList();
	}
	
	/**
	 * calls method within month is order 
	 * to go to the next month 
	 */
	public void nextMonth()
	{
		localCal.add(Calendar.MONTH, 1);
		setToday();
		changed();
	}
	
	/**
	 * moves instance of month to the prev one
	 */
	public void prevMonth()
	{
		localCal.add(Calendar.MONTH, -1);
		setToday();
		changed();
	}

	
	
	
	/**
	 * 
	 * @param in the scanner being used 
	 * @throws ParseException throws parseExpection
	 * @throws IOException throws parseExceptions
	 */
	public void addEvent() throws ParseException, IOException
	{
	
		Event eventToAdd = new Event();
		String eventDate = eventToAdd.requestInfo(in);
		Day eventDay = null;
		if(dayAlreadyExists(eventDate))
		{
		    eventDay = findDay(eventDate);
			eventDay.addEvent(eventToAdd);
		}
		else
		{
			eventDay = new Day(eventDate);
			eventDay.addEvent(eventToAdd);
			addNewDay(eventDay);
		}
		
		
	}
	
	
	/**
	 * goes to the next day 
	 * and then takes you back 
	 * to the view 
	 */
	public void nextDay()
	{
		localCal.add(Calendar.DATE, 1);
		setToday();
		changed();
	}
	
	/**
	 * goes to the prev day 
	 * and then takes you back 
	 * to the view 
	 */
	public void prevDay()
	{
		today.roll(Calendar.DAY_OF_YEAR, -1);
		localCal.add(Calendar.DATE, -1);
		setToday();
		changed();
	}
	
	/**
	 * 
	 * @param view the type of view you have pick
	 * this takes you through and goes through methods 
	 * to go next/prev or main manu
	 */
	
	/*
	public void dayViewMenu(String view)
	{
		System.out.println("\n[p]revious or [n]ext or [m]ain menu ?");
		String choice = in.nextLine();
		
		if(view.equals("d"))
		{
		switch(choice)
		{
		case "p": prevDay();
		break;
		case "n": nextDay();
		break;
		case "m": 
			testMonth.reset();
			testMonth.printMonth(true);
				  
		}
		}
		else
		{
			switch(choice)
			{
			case "p": prevMonth();
			break;
			case "n": nextMonth();
			break;
			case "m": 
				testMonth.reset();
				testMonth.printMonth(true);
			break;
		}
		
		}
	}
	*/
	
	/**
	 * 
	 * @param in the scanner
	 * takes you to a certain day and prints 
	 * the events  
	 */
	public void goTo(Scanner in)
	{
		System.out.println("Enter a date");
		String date = in.nextLine();
		Day curr = findDay(date);
		if(curr == null)
			curr = new Day(date);
		System.out.println("\n\n" + curr.toString());
		curr.returnEventList();
		System.out.println("\n\n\nSelect one of the following options: \n"
				+ "[l]oad   [v]iew by  [c]reate, [g]o to [e]vent list [d]elete  [q]uit");
	}

	/**
	 * this loads all the events 
	 * @throws FileNotFoundException throws file not found exception
	 * @throws IOException throws io execption
	 */
	public void load() throws FileNotFoundException, IOException 
	{
		FileReader fr = new FileReader("events.txt");
		try(BufferedReader br = new BufferedReader(fr)) {
		    String line = br.readLine();
		    while (line != null) {
		    
		    	String date = line;
		    	String description = br.readLine();
		    	String startTime = br.readLine();
		    	String endTime = br.readLine();
		    	Day eventDay = null;
		    	Event curr = new Event(date, description, startTime, endTime);
		    	if(dayAlreadyExists(curr.getStrDate()))
				{
				   eventDay = findDay(curr.getStrDate());
					eventDay.addEvent(curr);
				}
				else
				{
					eventDay = new Day(curr.getStrDate());
					eventDay.addEvent(curr);
					addNewDay(eventDay);
				}

		        line = br.readLine();
		    }
		    
		   br.close();
		   fr.close();
		    
		    
		}
	}
	
	    /**
	     * writes all the events down to 
	     * a text file so you can load it 
	     * later  
	     * @throws IOException throws ioexception
	     */
		public void quit() throws IOException
		{
			  Collections.sort(usedDays);
		      File eventsTxt = new File("events.txt");
		      eventsTxt.createNewFile();
			  FileWriter writer = new FileWriter(eventsTxt, true);

	
			for(int i = 0; i < usedDays.size(); i++)
			{
				Day curr = usedDays.get(i);
				curr.sort();
			    writer.write(curr.toString() + "\n"); 
			    curr.write(writer);

			}
			
			writer.close();
			
		}
		
		public void getCoordinates()
		{
			
		}
        

	/**
	 * 
	 * @param day the day you're looking for 
	 * @return whether the day exists of not 
	 */
	public boolean dayAlreadyExists(String day)
	{
		
		for(int i = 0; i < dayCount; i++) //wtf is wrong with this. The loops doesn't run. I'm testing delete
		{
			
			if(usedDays.get(i).getDate().equals(day))
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param day the day you want to add 
	 * to arraylist 
	 */
	public void addNewDay(Day day)
	{
		usedDays.add(day);
		dayCount++;
	}
	
	/**
	 * 
	 * @param eventDate the date you're looking for 
	 * @return the day or null if it doesn't exist
	 */
	public Day findDay(String eventDate)
	{
		for(Day current: usedDays)
		{
			if(current.getDate().equals(eventDate))
			{
				return current;
			}
		}
		
		return null;
	}
	
	/**
	 * iterates through sorted days and then 
	 * prints the events for those days
	 */
	public void allEvents()
	{
		Collections.sort(usedDays);
		
		for(int i = 0; i < dayCount; i++)
		{
			usedDays.get(i).returnEventList();
			System.out.println("Day: " + usedDays.get(i).getDay());
			System.out.println("Month: " + usedDays.get(i).getMonth());
			System.out.println("Year: " + usedDays.get(i).getYear());

		}
		
		System.out.println("\n\n\nSelect one of the following options: \n"
				+ "[l]oad   [v]iew by  [c]reate, [g]o to [e]vent list [d]elete  [q]uit");
	}
	
	/**
	 * 
	 * @param in  the scanner being used 
	 * deletes either a certain day's events 
	 * all the days 
	 */
	public void delete(Scanner in)
	{
		System.out.println("How would you like to delete? [1/2] \n"
				+ "1) by date \n2) everything");
		
		int deletionMethod = in.nextInt();
		
		if(deletionMethod == 1)
		{
			System.out.println("Enter date in \"MM/DD/YYYY\" format");
			String date = in.next();
			
			for(int i = 0; i < dayCount; i++)
			{
				if(usedDays.get(i).getDate().equals(date))
				{
					usedDays.remove(i);
					dayCount--;
				}
			}
			
		}
		else
		{
			usedDays.clear();
			dayCount = 0;
		}
		
		System.out.println("deletion confirmed");
		System.out.println("\n\n\nSelect one of the following options: \n"
				+ "[l]oad   [v]iew by  [c]reate, [g]o to [e]vent list [d]elete  [q]uit");
	}
	
	/**
	 * 
	 * @param v the type of view you want 
	 * whether it is d for day or m for month
	 */
	
	/*
	public void view(String v)
	{
		if(v.equals("d"))
		{
		 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		 Date todayDate = today.getTime();
		 String strToday = df.format(todayDate);		 
		 Day todayDay = findDay(strToday);
		 if(todayDay == null)
		 {
			 todayDay = new Day(strToday);
			 usedDays.add(todayDay);
					 
		 }
		System.out.println(todayDay.toString());
		todayDay.returnEventList();
		dayViewMenu("d");

		}
		else
		{
			testMonth.printMonth(false);
			dayViewMenu("m");

		}
	}
	*/
	public ArrayList<Day> getDaysArr()
	{
		return usedDays;
	}
	
	public Calendar getCal()
	{
		return today;
	}
	
	public void setToday()
	{
		
		 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		 Date todayDate = localCal.getTime();
		 String strToday = df.format(todayDate);

		 System.out.println(strToday);
		 
		if(findDay(strToday) == null)
		{
			Day today = new Day(strToday);
			this.chosenDay = today;
		}
		else
		{
			this.chosenDay = findDay(strToday);
		}
		
		changed();

			
			
	}
	
	public void setChosenDay(int x, int y)
	{
		
		int chosenDay = cellDays[x][y];
		
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		today.set(Calendar.DAY_OF_MONTH,chosenDay);
		Date todayDate = today.getTime();
		String strToday = df.format(todayDate);
		if(findDay(strToday) == null)
		{
			Day today = new Day(strToday);
			this.chosenDay = today;
		}
		else
		{
			this.chosenDay = findDay(strToday);
		}
		
		
		changed();
	}
	
	public void addCell(int i, int j, int input)
	{

		cellDays[i][j] = input;
	}
	
	public Day getChosenDay()
	{
		return chosenDay;
	}
	
	public void addChangeListener(ChangeListener listener)
	   {    listeners.add(listener); }
	
	public void changed()
	{
	      // Notify all observers of the change to the invoice
	      ChangeEvent event = new ChangeEvent(this);
	      for (ChangeListener listener : listeners)
	         listener.stateChanged(event);
	}
	 
	public void printCellDays()
	{

		for(int i = 0; i < cellDays.length; i++)
		{
			for(int j = 0; j < cellDays[0].length; j++)
				System.out.println(cellDays[i][j]);
		}
	}
	
	public int[][] getCellDays()
	{
		return cellDays;
	}
}
