package ultracode.schedule.lib;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;

public class ScheduleList implements Serializable
{
	String name;
	List<Application> applications;
	
	public ScheduleList( String name )
	{
		this.name = name;
		applications = new ArrayList<Application>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}
	
	public List<Application> getApplications()
	{
		return applications;
	}
	
	public void setApplications( List<Application> applications )
	{
		this.applications = applications;
	}
	
	
	//APPLICATION UTILITY
	
	public int countApplications()
	{
		return applications.size();
	}
	
	public Application getApplication( int index )
	{
		return applications.get( index );
	}
	
	public void addApplication( Application application )
	{
		applications.add( application );
	}
	
	public void updateApplication( int index, Application update )
	{
		applications.set( index, update );
	}
	
	public void deleteApplication( int index )
	{
		applications.remove( index );
	}
	
	void clearApplications()
	{
		applications.clear();
	}
}	