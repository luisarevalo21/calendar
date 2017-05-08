package hw2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GUITester 
{
	
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		Scanner in = new Scanner(System.in);
		
		Model model = new Model();
		MonthIcon month = new MonthIcon(model);
		month.iconSetCellDays();
		//Controller cal = new Controller(in, month);

		
		File eventsTxt = new File("events.txt");

		if(!eventsTxt.exists())
			System.out.println("There is no file yet since"
					+ " this is the first run");
		//else
				//model.load();
		JFrame frame = new JFrame();
		JLabel MonthLabel = new JLabel(month);
        model.setToday();
		
		DayIcon day = new DayIcon(model);
		JLabel DayLabel = new JLabel(day);
		
		MouseListener ml = new MouseListener(){
			public void mouseClicked(MouseEvent e) 
			{
				int x = e.getX();
				int y = e.getY();
				

				model.setChosenDay((y/100) - 1, (x/100));
			}

			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		};
		
		
		MonthLabel.addMouseListener(ml);
		
		ChangeListener listener = new
		         ChangeListener()
		         {
		            public void stateChanged(ChangeEvent event)
		            {
		               frame.repaint();
		            }
		         };
		      model.addChangeListener(listener);
		
		     JButton nextMonth = new JButton("Next Month");
				nextMonth.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						model.nextMonth();
					}
				});
				
				 JButton prevMonth = new JButton("Prev Month");
					prevMonth.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							model.prevMonth();
						}
					});
				
					JButton nextDay = new JButton("Next Day");
					nextDay.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							model.nextDay();
						}
					});
					
					 JButton prevDay = new JButton("Next Day");
						prevDay.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e)
							{
								model.prevDay();
							}
						});
		
						 JButton quit = new JButton("QUIT");
							prevDay.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e)
								{
									try {
										model.quit();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
			
		JPanel monthPanel = new JPanel();
		monthPanel.setLayout(new BorderLayout());
		monthPanel.add(MonthLabel, BorderLayout.CENTER);
		JPanel monthButtons = new JPanel();
		monthButtons.add(prevMonth);
		monthButtons.add(nextMonth);
		monthPanel.add(monthButtons, BorderLayout.SOUTH);

		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new BorderLayout());
		dayPanel.add(DayLabel, BorderLayout.CENTER);
		JPanel dayButtons = new JPanel();
		dayButtons.add(quit);
		dayButtons.add(prevDay);
		dayButtons.add(nextDay);
		dayPanel.add(dayButtons, BorderLayout.SOUTH);

		

		frame.setLayout(new BorderLayout());
		frame.add(monthPanel, BorderLayout.WEST);
		frame.add(dayPanel, BorderLayout.EAST);		

		frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);	
        
        

	} 
}
