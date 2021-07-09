import java.awt.Point;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.GraphicsConfiguration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observer;
import java.util.Observable;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import ultracode.schedule.lib.BackendUI;
import ultracode.schedule.lib.BackendModel;
import ultracode.schedule.lib.ExtendedUnit;
import ultracode.schedule.lib.Application;
import ultracode.schedule.lib.ScheduleList;
import ultracode.schedule.lib.RefereeCard;
import static ultracode.schedule.lib.Constants.*;

public class Backend implements Observer
{
	ExtendedUnit dd;
	
	BackendModel model;
	BackendUI ui;
	
	JDialog uiD;
	JFrame uiF;
	
	Mode MODE;
	public Backend()
	{
		MODE = Mode.OTHERS;
		
		doPersistModel( model = doInitModel() );
		
		ui = new BackendUI( MODE, model, this );
		dd = new ExtendedUnit( MODE, model );
		
		uiF = new JFrame(APP_TITLE);
		uiF.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		uiF.setIconImage ( new ImageIcon( getClass().getResource( "/resources/images/logo.png") ).getImage() );
		uiF.setUndecorated( true );
		uiF.addWindowListener(
			new WindowAdapter()
			{
				@Override
				public void windowDeiconified( WindowEvent we )
				{
					uiF.setExtendedState( JFrame.MAXIMIZED_BOTH );
				}
			});
		uiF.setExtendedState( JFrame.MAXIMIZED_BOTH );
		uiF.setContentPane( ui );
	}
	
	BackendModel doInitModel()
	{
		BackendModel model = new BackendModel();
		ObjectInputStream input = null;
		try
		{
			input = new ObjectInputStream( new FileInputStream("model.ser") );
			model = (BackendModel)input.readObject();
		}
		catch( IOException io )
		{
			if( io.getMessage() != null )
			{
				io.printStackTrace();
				model = doParseFile( new File("model.txt") );
			}
		}
		catch( ClassNotFoundException cnfe )
		{
			cnfe.printStackTrace();
			model = doParseFile( new File("model.txt") );
		}
		finally
		{
			try
			{
				input.close();
			}
			catch( Exception e )
			{
				
			}
		}
		return model;
	}
	
	void doPersistModel( BackendModel model )
	{
		ObjectOutputStream output = null;
		try
		{
			output = new ObjectOutputStream( new FileOutputStream("model.ser") );
			output.writeObject( model );
		}
		catch( IOException io )
		{
			io.printStackTrace();
		}
		finally
		{
			try
			{
				doPersistFile();
				output.close();
			}
			catch( Exception e )
			{
				
			}
		}
	}

	void doPersistFile()
	{
		if( model != null )
		try
		{
			File txt = new File("model.txt");
			Formatter output = new Formatter( txt );
			
			for( int i=0; i<model.countCards(); i++ )
			{
				if(i==0)
					output.format("REFS:");
				output.format("\n\tREF:%s",model.getCard(i).getName());
				output.format("\n\t\tREF-DES:%s",model.getCard(i).getDesignation());
				output.format("\n\t\tREF-CON:%s",model.getCard(i).getContact());
				output.format("\n\t\tREF-ADD:%s",model.getCard(i).getAddress());
				output.format("\n\t\tREF-UID:%s",model.getCard(i).getUniqueID());
				output.format("\n\tREF-END:");
				
				if(i==model.countCards()-1)
				{
					output.format("\nREFS-END:");
					output.format("\n\n");
				}
			}
			
			for( int i=0; i<model.countMessages(); i++ )
			{
				if(i==0)
					output.format("MSGS:");
				output.format( "\n\tMSG:%s",model.getMessage(i) );
				
				if(i==model.countMessages()-1)
				{
					output.format("\nMSGS-END:");
					output.format("\n\n");
				}
			}
			
			output.format("MSG-RATE:%s",model.getMessageRate());
			output.format("\n\n");
				
			output.format("LIST-RATE:%s",model.getListRate());
			output.format("\n\n");
			
			for( int i=0; i<model.countLists(); i++ )
			{
				if(i==0)
					output.format("LISTS:");
				
				ScheduleList list = model.getList(i);
				output.format("\n\tLIST:"+list.getName());
				
				for(int u=0; u<list.countApplications(); u++ )
				{
					if(u==0)
						output.format("\n\t\t%s","APPS:");
					
					Application app = list.getApplication(u);
					
					output.format("\n\t\t\tAPP:%s",app.getName());
					output.format("\n\t\t\t\tAPP-STATUS:%s",app.getStatus());
					if( app.getAppointmentDate() != null )
					{
						output.format("\n\t\t\t\tAPP-DATE_DD:%s",app.getAppointmentDate().get(Calendar.DATE));
						output.format("\n\t\t\t\tAPP-DATE_MM:%s",app.getAppointmentDate().get(Calendar.MONTH));
						output.format("\n\t\t\t\tAPP-DATE_YY:%s",app.getAppointmentDate().get(Calendar.YEAR));
					}
					
					if( app.getRefereeCard() != null )
					{
						output.format("\n\t\t\t\tAPP-REF:%s",app.getRefereeCard().getUniqueID());						
					}
					
					if( app.getRecommendation() != null )
					{
						output.format("\n\t\t\t\tAPP-REC:%s",app.getRecommendation());							
					}
					output.format("\n\t\t\tAPP-END:");
					if(u==list.countApplications()-1)
						output.format("\n\t\t%s","APPS-END:");
				}
				
				output.format("\n\tLIST-END:");
				
				if(i==model.countLists()-1)
				{
					output.format("\nLISTS-END:");
					output.format("\n");
				}
			}
			output.flush();
			//System.out.println("Done txt");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	BackendModel doParseFile( File txt )
	{
		BackendModel model = new BackendModel();
		
		String specialChars = "®©¤¥P¡«¿‡/=??–—¬°†‘’‚“”„'¦el™¼½¾ßµa";
		String [] allButN = new String[2];
		allButN[0] = "[ "+specialChars+"#$=(){}~_%;^&£|!;:.,?\\-+*@\"'’‘`/\\[\\]><|]";
		allButN[1] = "[ "+specialChars+"#$=(){}~_%;^&£|!.,?\\-+*@\"'’‘`/\\[\\]><|]";
		try
		{
			Scanner input = new Scanner( txt );
			String fileString = "";
			while( input.hasNextLine() )
				fileString += input.nextLine()+"\n";
				
			Pattern pattern = Pattern.compile("\\w*"+allButN[1]+"*\\w+[ ]*:("+allButN[0]+"*\\w*)+");
			Matcher m = pattern.matcher( fileString );
			Properties props = new Properties();
			ScheduleList tempList = null;
			
			while( m.find() )
			{
				String [] tokens = new String[]{ m.group().substring(0,m.group().indexOf(":")).trim(),m.group().substring(m.group().indexOf(":")+1).trim() };
				if( tokens[0].equalsIgnoreCase("REF") )
				{
					props.setProperty("ref-name",tokens[1]);
					props.setProperty("ref-des","");
					props.setProperty("ref-con","");
					props.setProperty("ref-add","");
					props.setProperty("ref-uid","");
				}									
				else
				if( tokens[0].equalsIgnoreCase("REF-DES") )
					props.setProperty("ref-des",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("REF-CON") )
					props.setProperty("ref-con",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("REF-ADD") )
					props.setProperty("ref-add",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("REF-UID") )
					props.setProperty("ref-uid",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("REF-END") )
				{
					RefereeCard card = new RefereeCard(props.getProperty("ref-name"),props.getProperty("ref-des"),props.getProperty("ref-con"),props.getProperty("ref-add"));
					card.setUniqueID(Long.valueOf(props.getProperty("ref-uid")));
					model.addCard(card);
					//System.out.println("Card added"+card.getName());
				}
				else
				if( tokens[0].equalsIgnoreCase("MSG") )
					model.addMessage( tokens[1] );
				else
				if( tokens[0].equalsIgnoreCase("MSG-RATE") )
					model.setMessageRate(Integer.parseInt(tokens[1]));
				else
				if( tokens[0].equalsIgnoreCase("LIST-RATE") )
					model.setListRate(Integer.parseInt( tokens[1]));
				else
				if( tokens[0].equalsIgnoreCase("LIST") )
					model.createScheduleList(tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("APP") )
				{
					props.setProperty("app-name",tokens[1]);
					props.setProperty("app-status","");
					props.setProperty("app-date-dd","");
					props.setProperty("app-date-mm","");
					props.setProperty("app-date-yy","");
					props.setProperty("app-ref-id","");
					props.setProperty("app-rec","");
				}
				else
				if( tokens[0].equalsIgnoreCase("APP-STATUS") )
					props.setProperty("app-status",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("APP-DATE_DD") )
					props.setProperty("app-date-dd",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("APP-DATE_MM") )
					props.setProperty("app-date-mm",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("APP-DATE_YY") )
					props.setProperty("app-date-yy",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("APP-REF") )
					props.setProperty("app-ref-id",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("APP-REC") )
					props.setProperty("app-rec",tokens[1]);
				else
				if( tokens[0].equalsIgnoreCase("APP-END") )
				{
					Application app = new Application(props.getProperty("app-name"));
					app.setStatus(Application.Status.valueOf(props.getProperty("app-status")));
					
					if(props.getProperty("app-date-dd").trim().length()>0)
						app.setAppointmentDate(new GregorianCalendar(Integer.valueOf(props.getProperty("app-date-yy")),Integer.valueOf(props.getProperty("app-date-mm")),Integer.valueOf(props.getProperty("app-date-dd"))));
					else
					if(props.getProperty("app-ref-id").trim().length()>0)
						app.setRefereeCard(model.getCard(Long.valueOf(props.getProperty("app-ref-id"))));
					else
					if(props.getProperty("app-rec").trim().length()>0)
						app.setRecommendation(props.getProperty("app-rec"));
					model.addApplication(app);
				}
				else
				if( tokens[0].equalsIgnoreCase("LIST-END") )
				{
					model.commitList();
				}
			}	
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return model;
	}
	
	public void update( Observable observable, Object update )
	{
		if( update instanceof BackendModel )
		{
			doPersistModel( (BackendModel)update );
			dd.update( update );
		}
		else
		if( update instanceof Airbag )
		{
			if( update == Airbag.ICONIFY )
				uiF.setExtendedState( JFrame.ICONIFIED );
			else
			if( update == Airbag.EXIT )
			{
				if( uiF != null )
					uiF.setVisible( false );
				if( uiD != null )
					uiD.setVisible( false );
				System.exit(0);
			}
			else
			if( update == Airbag.TOGGLE_ALERT )
				dd.update(update);
		}
		else
		if( update instanceof Mode )
		{
			if( update == Mode.LIST )
			{
				extend( MODE );
			}
			else
			if( update == Mode.IDLE )
			{
				switch(MODE)
				{
					case GO:
					{
						
					}break;
					case OTHERS:
					{
						
					}break;
					case REAL:
					{
						extend( Mode.CLOCK );
					}break;
				}
			}
			else
			if( update == Mode.ON )
			{
				extend(Mode.ON);
			}else
			if( update == Mode.OFF )
			{
				unextend();
			}
		}
		//////System.out.println("doPersistModel, returns");
	}
	
	public void extend( Mode MODE )
	{
		if( uiD != null )
			uiD.setVisible( false );
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice [] gs = ge.getScreenDevices();
		
		for( int i=0; i<gs.length; i++ )
		{
			if( !(gs[i].getDefaultConfiguration().getBounds().getX() == 0 && gs[i].getDefaultConfiguration().getBounds().getY() == 0) )
			{
				GraphicsConfiguration gc = gs[i].getDefaultConfiguration();
				uiD = new JDialog( new JFrame(gc), "", false, gc );
				dd.setMode(MODE);
				uiD.add( dd );
				uiD.setUndecorated( true );
				uiD.setBounds( gc.getBounds() );
				uiD.setAlwaysOnTop( true );
				uiD.setVisible( true );
				ui.updateSignal(MODE);
				return;
			}
			else
				continue;			
		}
		ui.updateSignal(Mode.OFF);
	}
	
	public void unextend()
	{
		if( uiD != null )
			uiD.setVisible( false );
		ui.updateSignal(Mode.OFF);
	}
	
	public static void main( String args[] )
	{
		Backend backend = new Backend();
		
		try
		{
			UIManager.LookAndFeelInfo [] looks = UIManager.getInstalledLookAndFeels();
			UIManager.setLookAndFeel( looks[1].getClassName() );
			SwingUtilities.updateComponentTreeUI( backend.uiF );
			SwingUtilities.updateComponentTreeUI( backend.ui.commDialog );
			SwingUtilities.updateComponentTreeUI( backend.dd );
			SwingUtilities.updateComponentTreeUI( backend.dd.gosPanel );
			SwingUtilities.updateComponentTreeUI( backend.dd.othersPanel );
			SwingUtilities.updateComponentTreeUI( backend.dd.realPanel );
		}
		catch( UnsupportedLookAndFeelException ulfe )
		{
		}
		catch( Exception e )
		{
		}
		finally
		{
			backend.uiF.setVisible( true );
			backend.extend(Mode.ON);
		}
	}
	
}