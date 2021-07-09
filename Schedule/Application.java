package ultracode.schedule.lib;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Application implements Serializable
{
	public enum Status{DEFAULT,GO,REFERRED,DEFERRED,REAL_WAIT,REAL_NEXT_TIME}
	
	
	// Status.DEFAULT
	String name;
	Status status;
	
	// Status.G_O
	Calendar appointmentDate;

	// Status.REFERRED
	RefereeCard refereeCard;
	
	// Status.DEFERRED
	String recommendation;
	
	public Application( String name )
	{
		this.name = name;
		status = Status.DEFAULT;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}
	
	public Status getStatus()
	{
		return status;
	}
	
	public void setStatus( Status status )
	{
		this.status = status;
	}
	
	public Calendar getAppointmentDate()
	{
		return appointmentDate;
	}
	
	public void setAppointmentDate( Calendar appointmentDate )
	{
		this.appointmentDate = appointmentDate;
	}
	
	public RefereeCard getRefereeCard()
	{
		return refereeCard;
	}
	
	public void setRefereeCard( RefereeCard refereeCard )
	{
		this.refereeCard = refereeCard;
	}
	
	public String getRecommendation()
	{
		return recommendation;
	}
	
	public void setRecommendation( String recommendation )
	{
		this.recommendation = recommendation;
	}
}