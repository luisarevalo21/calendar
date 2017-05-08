package hw2;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Icon;

public class DayIcon implements Icon 
{
	private Model m; 
	private Day d;
	private ArrayList<Event> e;
	public DayIcon(Model m)
	{
		this.m = m;
		e = m.getChosenDay().getEventsArr();

	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		System.out.println(d);
		System.out.println("paint");
		Graphics2D g2 = (Graphics2D) g;
		
		 		g2.drawString(m.getChosenDay().toString(), 200, 15);

		 
		 int eventX = 5;
		 int eventY = 50;
		 
		 
	     
			for(int i = 0; i < m.getChosenDay().getEventCount(); i++)
			{

				g2.drawString(e.get(i).toString(), eventX, eventY);
				eventY += 20;

				//System.out.println(current.toString());
			}
		
	}


	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 500;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 500;
	}
	
}
