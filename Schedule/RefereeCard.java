package ultracode.schedule.lib;
import java.util.GregorianCalendar;
import java.io.Serializable;

public class RefereeCard implements Serializable
{
	String name,designation,contact,address;
	long uniqueID;
	
	public RefereeCard( String name, String designation, String contact, String address )
	{
		uniqueID = System.currentTimeMillis();
		this.name = name;
		this.designation = designation;
		this.contact = contact;
		this.address = address;
	}
	
	public long getUniqueID()
	{
		return uniqueID;
	}
	
	public void setUniqueID( long uniqueID )
	{
		this.uniqueID = uniqueID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}
	
	public String getContact()
	{
		return contact;
	}
	
	public void setContact( String contact )
	{
		this.contact = contact;
	}
	public String getDesignation()
	{
		return designation;
	}
	
	public void setDesignation( String designation )
	{
		this.designation = designation;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress( String address )
	{
		this.address = address;
	}
}