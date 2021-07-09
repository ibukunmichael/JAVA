package ultracode.schedule.lib;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.io.Serializable;
import static ultracode.schedule.lib.Constants.*;

public class BackendModel implements Serializable
{
	List<RefereeCard> referees;
	List<ScheduleList> ALPHA_LIST;
	transient ScheduleList ACTIVE_LIST;
	transient ScheduleList TEMP_LIST;
	List<String> scrollMessages;
	int listRate, msgRate;
	
	public BackendModel()
	{
		referees = new ArrayList<RefereeCard>();
		ALPHA_LIST = new ArrayList<ScheduleList>();
		scrollMessages = new ArrayList<String>();
		listRate = 1;
		msgRate = 2;
	}
	
	public void setListRate( int listRate )
	{
		if(listRate>=0)
			this.listRate = listRate;
	}
	
	public int getListRate()
	{
		return listRate;
	}
	
	public void setMessageRate( int msgRate )
	{
		if(msgRate>=0)
			this.msgRate = msgRate;
	}
	
	public int getMessageRate()
	{
		return msgRate;
	}
	
	public int countMessages()
	{
		return scrollMessages.size();
	}
	
	public void addMessage( String msg )
	{
		scrollMessages.add(msg);
	}
	
	public String getMessage( int index )
	{
		return scrollMessages.get(index);
	}
	
	String PREFIX = "    ";	
	public String getMessages()
	{
		StringBuilder ret = new StringBuilder();
		for( int i=0; i<scrollMessages.size(); i++ )
		{
			ret.append(scrollMessages.get(i));
			if(scrollMessages.size()>i+1)
				ret.append(PREFIX);
		}
		return ret.toString();
	}
	
	public void deleteMessage( int index)
	{
		scrollMessages.remove( index );
	}
	
	public void updateMessage( int index, String update )
	{
		scrollMessages.set( index, update );
	}
	
	public void clearMessages()
	{
		scrollMessages.clear();
	}
	
	public String[] getRefereeNames()
	{
		String [] names = new String[referees.size()];
		for(int i=0; i<referees.size(); i++)
		{
			names[i] = referees.get(i).getName();
		}
		return names;
	}
	
	public int countCards()
	{
		return referees.size();
	}
	
	public void addCard( RefereeCard referee )
	{
		referees.add( referee );
		Collections.sort(referees,new RefsComparator() );
	}
	
	public RefereeCard getCard( int index )
	{
		return referees.get(index);
	}
	
	public RefereeCard getCard( long refID )
	{
		RefereeCard ret = null;
		for( RefereeCard ref : referees )
			if( ref.getUniqueID() == refID )
				return ret = ref;
		return ret;
	}
	
	public void updateCard( int index, String name, String designation, String contact, String address )
	{
		referees.get( index ).setName(name);
		referees.get( index ).setDesignation(designation);
		referees.get( index ).setContact(contact);
		referees.get( index ).setAddress(address);
		
		// for( ScheduleList s : ALPHA_LIST )
			// for( Application a : s.getApplications() )
				// if( a.getRefereeCard() != null && a.getRefereeCard().getUniqueID()==(referees.get( index ).getUniqueID()))
					// a.setRefereeCard(referees.get( index ));
	}
	
	public void removeCard( int index )
	{
		referees.remove( index );
	}
	
	public String getDefaultListTitle( Mode MODE )
	{
		String [] mths = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		String [] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		Calendar now = GregorianCalendar.getInstance();
		
		String ret = "";
		if( MODE.equals(Mode.GO) )
			ret = "MFM-GO-List "+now.get(Calendar.YEAR)+"-"+(mths[now.get(Calendar.MONTH)])+"-"+now.get(Calendar.DATE)+" "+now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE);
		else
		if( MODE.equals(MODE.OTHERS) )
			ret = "MFM-Other-List "+now.get(Calendar.YEAR)+"-"+(mths[now.get(Calendar.MONTH)])+"-"+now.get(Calendar.DATE)+" "+now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE);
		else
		if( MODE.equals(MODE.REAL) )
			ret = "COUNSELLING LIST FOR TODAY, "+days[now.get(Calendar.DAY_OF_WEEK)-1]+", "+(mths[now.get(Calendar.MONTH)])+" "+now.get(Calendar.DATE)+", "+now.get(Calendar.YEAR)+" "+now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE);
		
		return ret;
	}
	
	public ScheduleList getList()
	{
		return TEMP_LIST;
	}
	
	public ScheduleList getList( int index )
	{
		return ALPHA_LIST.get(index);
	}
	
	public void createScheduleList( String name )
	{
		TEMP_LIST = new ScheduleList( name );
	}
	
	public void createScheduleList( int index )
	{
		TEMP_LIST = ALPHA_LIST.get(index);
	}
	
	public void addApplication( Application application )
	{
		if(TEMP_LIST != null )
			TEMP_LIST.addApplication( application );
	}
	
	public void updateApplication( int listIndex, int appIndex, Application update )
	{
		ALPHA_LIST.get(listIndex).updateApplication( appIndex, update );
	}
	
	public void insertApplication( int listIndex, Application insert )
	{
		ALPHA_LIST.get(listIndex).addApplication( insert );
	}
	
	public void commitList()
	{
		if( TEMP_LIST != null )
		{
			while(isDuplicateListName())
				TEMP_LIST.setName(TEMP_LIST.getName()+"-Copy");
			ALPHA_LIST.add( TEMP_LIST );
		}
		TEMP_LIST = null;
	}
	
	public boolean isDuplicateListName()
	{
		boolean ret = false;
		for( ScheduleList s : ALPHA_LIST )
		{
			if( s.getName().equalsIgnoreCase(TEMP_LIST.getName()) )
				ret = true;
		}
		return ret;
	}
	
	public String [] getListNames()
	{
		String [] ret = new String[ALPHA_LIST.size()];
		for( int i=0; i<ret.length; i++ )
		{
			ret[i] = ALPHA_LIST.get(i).getName();
		}
		return ret;
	}
	
	public String [] getApplicationNames( int index )
	{
		String [] ret = new String[ALPHA_LIST.get(index).countApplications()];
		for( int i=0; i<ret.length; i++ )
		{
			ret[i] = ALPHA_LIST.get(index).getApplication(i).getName();
		}
		return ret;
	}
	
	public Application getApplication( int listIndex, int appIndex )
	{
		return ALPHA_LIST.get(listIndex).getApplications().get(appIndex);
	}
	
	public void setActiveList( int index )
	{
		ACTIVE_LIST = Collections.synchronizedList(ALPHA_LIST).get(index);
	}
	
	public ScheduleList getActiveList()
	{
		return ACTIVE_LIST;
	}
	
	public void deleteApplication( int listIndex, int appIndex )
	{
		ALPHA_LIST.get(listIndex).deleteApplication( appIndex );
	}
	
	public void deleteList( int index )
	{
		ALPHA_LIST.remove( index );
	}
	
	public int countLists()
	{
		return ALPHA_LIST.size();
	}
	
	//UTILITIES
	String HTML_PRE = "<html>";
	String HTML_SUF = "</html>";
	
	public String getDateString( Calendar now )
	{
		StringBuilder ret = new StringBuilder("");
		if( now != null )
		{
			String [] mths = {"January","February","March","April","May","June","July","August","September","October","November","December"};
			ret.append( (mths[now.get(Calendar.MONTH)])+" "+now.get(Calendar.DATE)+", "+now.get(Calendar.YEAR) );
		}
		else
			ret.append( "N/A" );
		return padHtml(ret.toString());
	}
	
	public String getContactString( RefereeCard card )
	{
		StringBuilder ret = new StringBuilder("");
		if( card != null )
		{
			if( card.getAddress().trim().length() > 0 )
				if( card.getContact().trim().length() > 0 )
					ret.append( card.getAddress() +", <font color=\"#0000f0\">"+card.getContact()+"</font>");
				else
					ret.append( card.getAddress() );
			else
				if( card.getContact().trim().length() > 0 )
					ret.append( card.getContact() );
				else
					ret.append( "N/A" );
		}
		return ret.toString().trim();
	}
	
	public String getStatusString( Application.Status status )
	{
		String ret = "N/A";
		if( status.equals( Application.Status.DEFAULT ) )
			ret = padHtml( "<font style=\"font-style:italics;color:#00ff33\">APPLICATION RECEIVED!</font>" );
		else
		if( status.equals( Application.Status.REFERRED ) )
			ret = padHtml("<font color=\"blue\">REFERRED</font>");
		else
		if( status.equals( Application.Status.DEFERRED ) )
			ret = padHtml("<font color=\"yellow\">APPLICATION DEFERRED</font>");
		else
		if( status.equals( Application.Status.REAL_WAIT ) )
			ret = padHtml("<font style=\"color:#00ff33\">WAIT TO SEE THE G.O</font>");
		else
		if( status.equals( Application.Status.REAL_NEXT_TIME ) )
			ret = padHtml("<font color=\"yellow\">NEXT TIME</font>");
		return ret;
	}
	
	public String padHtml( String string )
	{
		return HTML_PRE+string+HTML_SUF;
	}
	
	
}