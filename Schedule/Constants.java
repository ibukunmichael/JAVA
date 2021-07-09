package ultracode.schedule.lib;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
public class Constants
{
	public static final String TITLE_GO = "THE GENERAL OVERSEER'S APPOINTMENTS";
	public static final String TITLE_OTHERS = "OTHER APPOINTMENTS & DEFERMENTS";
	public static final String TITLE_REAL = "SHORT NOTE APPOINTMENTS - REALTIME";
	
	public enum Airbag{TOGGLE_ALERT,ICONIFY,EXIT}
	public enum Mode{GO,OTHERS,REAL,IDLE,LIST,CLOCK,ON,OFF}
	
	public static final Color EXT_BG = new Color( 29,10,18);
	public static final Color OVERLAY_COLOR = new Color( 137,103,98 );
	
	public static final Color LINK_FG = Color.WHITE; //new Color( 92,69,65 );
	public static final Font HEADER_FONT = new Font( "Maiandra GD", Font.BOLD, 15 );
	public static final Font SUB_HEADER_FONT = new Font( "Maiandra GD", Font.PLAIN, 11 );
	public static final Font LINK_FONT_PLAIN = new Font( "Maiandra GD", Font.PLAIN, 12 );
	public static final Font LABEL_FONT_1 = new Font( "Maiandra GD", Font.BOLD, 12 );
	public static final Font LABEL_FONT_MINI_1 = new Font( "Maiandra GD", Font.PLAIN, 11 );
	public static final Font CELL_FONT = new Font("Maiandra GD",Font.BOLD,27);
	public static final Font CELL_HEADER_FONT = new Font("Maiandra GD",Font.BOLD,23);
	public static final Font MODULE_HEADER_FONT = new Font("Maiandra GD",Font.BOLD,27);
	
	
	public static final int TIMER_DELAY = 0;
	public static final String DEFAULT_STATUS = "----!----";
	public static final int UI_HEIGHT = 675;//Double.valueOf( ""+Toolkit.getDefaultToolkit().getScreenSize().getHeight() ).intValue();
	public static final int UI_WIDTH = 60+((UI_HEIGHT/3)*4);//Double.valueOf( ""+Toolkit.getDefaultToolkit().getScreenSize().getWidth() ).intValue();
	public static final int CONTAINER_GAP = 30;
	public static final String APP_TITLE = "Ultracode Labs SX-01";
	public static final String PROGRAM_TITLE = "MFM INT'L HQ INFORMATION MGT. SUITE";
	public static final Color MDI_BG = new Color(91,30,153);
	public static final Color UIP_BG = new Color( 69,20,103 );
	public static final Color FIELD_BG = new Color( 205,211,244 );
	public static final Color LINK_PANEL_BG = new Color( 183,137,130 );
	public static final Font STATUS_FONT = new Font( "Maiandra GD", Font.BOLD, 14 );
	// public static final Color LINK_FG = Color.WHITE;
	public static final Color ANCHOR_FG = new Color( 204,255,153 );
	public static final Color OFFAIR_BG = Color.BLUE;
	public static final Color ONAIR_BG = Color.RED;
	public static final Color GOLD = new Color(218,165,32);
	public static final Font COMPANY_FONT = new Font( "Maiandra GD", Font.BOLD, 20 );
	public static final Font VIEW_FONT = new Font( "Maiandra GD", Font.BOLD, 15 );
	public static final Font VIEW_FONT_2 = new Font( "Lucida Handwriting", Font.PLAIN, 13 );
	public static final Font SUPER_MAXI_FONT = new Font( "Lucida Handwriting", Font.BOLD, 50 );
	public static final Font SUPER_MAXI_FONT2 = new Font( "Maiandra GD", Font.BOLD, 50 );
	public static final Font MAXI_FONT = new Font( "Maiandra GD", Font.BOLD, 35 );
	// public static final Font HEADER_FONT = new Font( "Maiandra GD", Font.BOLD, 15 );
	public static final Font LINK_FONT = new Font( "Maiandra GD", Font.BOLD, 12 );
	public static final Font LABEL_FONT = new Font( "Maiandra GD", Font.BOLD, 15 );
	public static final Font LABEL_FONT_MINI = new Font( "Maiandra GD", Font.PLAIN, 10 );
	public static final Font ANCHOR_FONT = new Font( "Maiandra GD", Font.BOLD, 12 );
	public static final Font ANCHOR_FONT_2 = new Font( "SansSerif", Font.PLAIN, 10 );
}