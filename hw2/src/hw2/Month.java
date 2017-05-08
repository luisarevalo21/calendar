package hw2;

import java.util.Calendar;

/**
 * 
 * @author aldonut
 * purpose of class is to display
 * the actual calendar
 */
public class Month  
{
	enum DAYS
	{
		Su, Mo, Tu, We, Th, Fr, Sa ;
	}
	
	private DAYS[] days = DAYS.values();
	
	private String[] months = {"January", "February", "March",
			"April", "May", "June", "July", "August", "September",
			"October", "November", "December"};
	
	private int[][] monthView = new int[6][7];
	private Calendar cal = null;
	private int today;
	private int currentMonth;
	private Calendar fakeCal = Calendar.getInstance();

	
	/**
	 * object instantiates calendar 
	 */
	public Month()
	{
		cal = Calendar.getInstance();	
		today = cal.get(Calendar.DAY_OF_MONTH);
		currentMonth = cal.get(Calendar.MONTH);
	}
	
	/**
	 * 
	 * @return the current month of cal
	 */
	public int getMonth()
	{
		return cal.get(Calendar.MONTH);
	     
	}
	
	/**
	 * 
	 * @return the current year of cal
	 */
	public int getYear()
	{
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 
	 * @return the original day of month
	 */
	public int getDay()
	{
		return today;
	}
	
	/**
	 * 
	 * @return returns the first day of the month as an int
	 */
	public int firstDay()
	{	
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * moves instance of month to the next one
	 */
	public void next()
	{
		cal.roll(Calendar.MONTH, 1);
	}
	
	/**
	 * moves instance of month to the prev one
	 */
	public void prev()
	{
		cal.roll(Calendar.MONTH, -1);
	}
	
	/**
	 * resets instance of cal to todays date
	 */
	public void reset()
	{
		cal = fakeCal;
	}
	
	/**
	 * 
	 * @param mainMenu whether or not to print the main 
	 * along with calendar
	 */
	public void printMonth(boolean mainMenu)
	{
		System.out.println("      " + months[this.getMonth()] + " " + getYear() + "\n");
		int currentDay = 1;
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(DAYS current: days)
		{
			System.out.print(current + "  ");
		}
		
		System.out.println("\n");
		
		for(int i = 0; i < monthView.length; i++)
		{
			for(int j = 0; j < monthView[0].length; j++)
			{
				if(i == 0 && j < firstDay() -1)
				{
					System.out.print("    ");
					continue;
				}
				monthView[i][j] = currentDay;
				if(currentDay == getDay() && getMonth() == currentMonth)
				{
					System.out.print("[" + monthView[i][j] + "] ");

				}
				else
				{
				if(currentDay < 10)
				System.out.print(monthView[i][j] + "   ");
				else 
					System.out.print(monthView[i][j] + "  ");
				}
				if(currentDay == lastDay)
				{
					if(mainMenu == true)
					System.out.println("\n\n\nSelect one of the following options: \n"
					+ "[l]oad   [v]iew by  [c]reate, [g]o to [e]vent list [d]elete  [q]uit");
					return ;
				}
				currentDay++;	
			}
			System.out.println("\n");
		}
		
	}
	
	
}
