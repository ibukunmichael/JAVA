package ultracode.schedule.lib;

import java.util.Comparator;

public class RefsComparator implements Comparator<RefereeCard>
{
	@Override
	public int compare( RefereeCard a1, RefereeCard a2 )
	{
		return a1.getName().compareToIgnoreCase( a2.getName() );
	}
}