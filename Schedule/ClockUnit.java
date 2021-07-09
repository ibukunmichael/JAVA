package ultracode.schedule.lib;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Locale;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.Timer;

public class ClockUnit extends JPanel
{
	GroupLayout displayLayout;
		JPanel displayPanel;
			JPanel timePanel;
				JPanel timeLayoutPanel;
				GroupLayout timeLayout;
					JLabel timeLabel;
					JLabel ampmLabel;
			JPanel datePanel;
				JLabel dateLabel;
	Timer clockTimer;
	
	public ClockUnit()
	{
		super();
		setBackground( Color.BLACK );
		setOpaque( false );
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS ) );
			clockTimer = new Timer( 1000, new ActionListener()
			{
				@Override
				public void actionPerformed( ActionEvent ae )
				{
					//System.out.println("time sent");
					setTime( GregorianCalendar.getInstance() );
				}
			});
			displayPanel = new JPanel();
			displayPanel.setOpaque( false );
			displayLayout = new GroupLayout( displayPanel );
			displayPanel.setLayout( displayLayout );
			displayPanel.setBackground( Color.BLACK );
				
				timePanel = new JPanel();
				timePanel.setLayout( new BoxLayout(timePanel,BoxLayout.X_AXIS) );
				timePanel.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED),BorderFactory.createEtchedBorder(EtchedBorder.RAISED) ) );
				timePanel.setBackground( Color.WHITE );
				// timePanel.setOpaque( false );
				timeLayoutPanel = new JPanel();
				timeLayout = new GroupLayout( timeLayoutPanel );
				timeLayoutPanel.setLayout( timeLayout );
				timeLayoutPanel.setOpaque( false );
					timeLabel = new JLabel();
					timeLabel.setForeground( Color.BLACK );
					timeLabel.setFont( new Font( "Serif",Font.BOLD,200 ) );//220
					timeLabel.setAlignmentY( JLabel.CENTER_ALIGNMENT );
					ampmLabel = new JLabel();
					ampmLabel.setForeground( Color.BLACK );
					ampmLabel.setFont( new Font( "Serif",Font.BOLD,100 ) );
					ampmLabel.setAlignmentY( JLabel.CENTER_ALIGNMENT );
				timePanel.add( Box.createHorizontalGlue() );
				timePanel.add( timeLayoutPanel );
				timePanel.add( Box.createHorizontalGlue() );
				timeLayout.setHorizontalGroup(
					timeLayout.createSequentialGroup()
						.addComponent(timeLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(ampmLabel));
						
				timeLayout.setVerticalGroup(
					timeLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(timeLabel)
						.addComponent(ampmLabel));
				
				datePanel = new JPanel();
				datePanel.setLayout( new BoxLayout(datePanel, BoxLayout.X_AXIS) );
				datePanel.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED),BorderFactory.createEtchedBorder(EtchedBorder.RAISED) ) );
				datePanel.setBackground( Color.BLACK );
					dateLabel = new JLabel();
					dateLabel.setOpaque(false);
					dateLabel.setForeground( Color.WHITE );
					dateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT );
					dateLabel.setFont( new Font( "Serif",Font.BOLD,50 ) );
				datePanel.add(Box.createHorizontalGlue());
				datePanel.add(dateLabel);
				datePanel.add(Box.createHorizontalGlue());
				
			displayLayout.setAutoCreateGaps( false );
			displayLayout.setAutoCreateContainerGaps( false );
			displayLayout.setHorizontalGroup(
				displayLayout.createParallelGroup( GroupLayout.Alignment.CENTER)
					.addComponent(timePanel)
					.addComponent(datePanel));
			
			displayLayout.setVerticalGroup(
				displayLayout.createSequentialGroup()
					.addComponent(timePanel)
					.addComponent(datePanel));
		
		add( Box.createVerticalGlue() );
		add( displayPanel );
		add( Box.createVerticalGlue() );
		add( Box.createVerticalGlue() );
	}
	
	void setTime( String time )
	{
		timeLabel.setText( time );
	}
	
	void setAMPM( String ampm )
	{
		ampmLabel.setText( ampm );
	}
	
	void setDate( String date )
	{
		dateLabel.setText( date );
	}

	void setTime( Calendar gc )
	{
		String clockMode = "12";
		String clockStyle = "medium";
		String dateStyle = "full";
		if( gc != null )
		{
			SimpleDateFormat sdf = new SimpleDateFormat("h:mm a",Locale.getDefault());
			
			String date,time,h,m,s,ampm;
			date=time=h=m=s=ampm="";
			if( clockMode.equals("24") )
			{
				if( clockStyle.equals("short") )
				{
					sdf.applyPattern("HH:mm");
					time = sdf.format(gc.getTime());
				}
				else
					if( clockStyle.equals("medium") )
					{
						sdf.applyPattern("HH:mm:ss");
						time = sdf.format(gc.getTime());
					}
			}
			else
			{
				if( clockStyle.equals("short") )
				{
					sdf.applyPattern("h:mm");
					time = sdf.format(gc.getTime());
					
					sdf.applyPattern("a");					
					ampm = sdf.format(gc.getTime());
				}
				else
					if( clockStyle.equals("medium") )
					{
						sdf.applyPattern("h:mm:ss");
						time = sdf.format(gc.getTime());
					
						sdf.applyPattern("a");					
						ampm = sdf.format(gc.getTime());
					}
			}
			
			
			if( dateStyle.equals("short") )
				date = DateFormat.getDateInstance(DateFormat.SHORT,Locale.getDefault()).format(gc.getTime()).toUpperCase();
			else
				if( dateStyle.equals("medium") )
					date = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.getDefault()).format(gc.getTime()).toUpperCase();
				else
					if( dateStyle.equals("long") )
						date = DateFormat.getDateInstance(DateFormat.LONG,Locale.getDefault()).format(gc.getTime()).toUpperCase();
					else
						date = DateFormat.getDateInstance(DateFormat.FULL,Locale.getDefault()).format(gc.getTime()).toUpperCase();
			
			setTime(time);
			setAMPM(ampm);
			setDate(date);
		}
	}

	public void startTime()
	{
		clockTimer.start();
	}
	
	public void stopTime()
	{
		clockTimer.stop();
	}
	
}