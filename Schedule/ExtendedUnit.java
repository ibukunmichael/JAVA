package ultracode.schedule.lib;

import java.awt.Point;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.text.DateFormat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.StringTokenizer;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JViewport;
import javax.swing.Timer;

import static ultracode.schedule.lib.Constants.*;

public class ExtendedUnit extends JPanel
{
	final int ROW_PADDING = 50;
	final ImageIcon LOGO = new ImageIcon( getClass().getResource("/resources/images/mfmLogo.jpg") );
	final ImageIcon LOGO_MIN = new ImageIcon( getClass().getResource("/resources/images/mfmLogo_min2.jpg") );
	public final ImageIcon BG = new ImageIcon( getClass().getResource( "/resources/images/img25.jpg") );
	final int FPS = 50;	//fps = 1000/fps
	int MSG_RATE = 1, LIST_RATE = 1;
	
	final Color bg1 = new Color(0x67,0x24,0x35);//new Color( 0x66,0x33,0x00 );
	final Color bg2 = new Color( 0x66,0x00,0x33 );
	final Color HEADER_COLOR = Color.BLACK;
	final Color SN = new Color(0xe2,0xc3,0x91);// new Color(246,196,178); //new Color(0xff,0xcc,0xcc);
	
	ExecutorService threadExecutor;
	
	JPanel northPanel;
		JLabel stripLabel1;
		JComponent animLogoComponent;
		JPanel stripPanel2;
			JLabel stripLabel2, stripLabel3;
		
	GridBagConstraints appConstraints;
	
	JPanel centerPanel;
		public JPanel gosPanel;
			JPanel gosLabelPanel;
			GridBagLayout gosLabelLayout;
				JLabel gosLabel;
			JPanel gosListContainer;
				JPanel gosHeaderPanel;
				GridBagLayout gosHeaderLayout;
					JLabel gosSerialLabel;
					JLabel gosNameLabel;
					JLabel gosDateLabel;
				JScrollPane gosContentScroll;
				JPanel grandScrollPanel;
					JPanel prefixPanel;
					GridBagLayout prefixLayout;
					JPanel gosContentPanel;
					GridBagLayout gosContentLayout;
					JPanel suffixPanel;
					GridBagLayout suffixLayout;
		
		public JPanel realPanel;
			JPanel realLabelPanel;
			GridBagLayout realLabelLayout;
				JLabel realLabel;
			JPanel realListContainer;
				JPanel realHeaderPanel;
				GridBagLayout realHeaderLayout;
					JLabel realSerialLabel;
					JLabel realNameLabel;
					JLabel realDateLabel;
				JScrollPane realContentScroll;
				JPanel grandScrollPanelc;
					JPanel prefixPanelc;
					GridBagLayout prefixLayoutc;
					JPanel realContentPanel;
					GridBagLayout realContentLayout;
					JPanel suffixPanelc;
					GridBagLayout suffixLayoutc;
		
		public JPanel othersPanel;
			JPanel othersLabelPanel;
			GridBagLayout othersLabelLayout;
				JLabel othersLabel;
			JPanel othersListContainer;
				JPanel othersHeaderPanel;
				GridBagLayout othersHeaderLayout;
					JLabel othersSerialLabel;
					JLabel othersNameLabel;
					JLabel othersRefLabel;
					JLabel othersContactLabel;
				JScrollPane othersContentScroll;
				JPanel grandScrollPanelb;
					JPanel prefixPanelb;
					GridBagLayout prefixLayoutb;
					JPanel othersContentPanel;
					GridBagLayout othersContentLayout;
					JPanel suffixPanelb;
					GridBagLayout suffixLayoutb;

	JPanel southPanel;
		JScrollPane messageScroll;
		JPanel messageScrollPanel;
			JPanel messagesPanel;
			JLabel announcementLabel;
			JLabel prefixLabel, suffixLabel;
	List<String> gosNames,gosDates,othersNames,othersNames2,othersContact,realNames,realStatus;
	
	Mode MODE = Mode.GO;
	BackendModel dataModel;
	ClockUnit clockUnit;
	Timer timerHandler;
	
	public ExtendedUnit( Mode MODE, BackendModel dataModel )
	{
		this.MODE = MODE;
		this.dataModel = dataModel;
		MSG_RATE = dataModel.getMessageRate();
		LIST_RATE = dataModel.getListRate();

		clockUnit = new ClockUnit();
		threadExecutor = Executors.newCachedThreadPool();
		
		gosNames = new ArrayList<String>();
		gosDates = new ArrayList<String>();
		othersNames = new ArrayList<String>();
		othersNames2 = new ArrayList<String>();
		othersContact = new ArrayList<String>();
		realNames = new ArrayList<String>();
		realStatus = new ArrayList<String>();
		
		appConstraints = new GridBagConstraints();
		appConstraints.fill = GridBagConstraints.HORIZONTAL;
		setOpaque( false );
		setLayout( new BorderLayout() );
			northPanel = new JPanel();
			northPanel.setOpaque( false );
			northPanel.setLayout( new BoxLayout(northPanel, BoxLayout.X_AXIS));
			northPanel.setBackground( Color.WHITE );
				stripLabel1 = new JLabel(".....");
				doPimpHeader(stripLabel1);
				stripLabel1.setOpaque( true );
				
				animLogoComponent = new RotateAnimComponent(LOGO);
				
				stripPanel2 = new JPanel();
				stripPanel2.setOpaque( true );
				stripPanel2.setLayout( new BoxLayout(stripPanel2,BoxLayout.X_AXIS) );
				stripPanel2.setBackground( new Color( 0xff,0x00,0x33 ) );
					stripLabel2 = new JLabel()
					{
						String caption = "....";
						@Override
						public void paintComponent(Graphics g)
						{
							setFont(HEADER_FONT);
							Graphics2D g2 = (Graphics2D)g;
							g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
							
							Shape rect = new RoundRectangle2D.Float(0,2,getWidth()-1,getHeight()-5,5,5);
							
							g2.setStroke(new BasicStroke(2));
							g2.setPaint(Color.WHITE);
							g2.draw( rect );							
							
							g2.setPaint(new Color(29,10,18));
							((RoundRectangle2D)rect).setFrame(1,3,getWidth()-2,getHeight()-6);
							g2.fill( rect );
							g2.setPaint(Color.WHITE);
							
							int stringWidth = getFontMetrics(getFont()).stringWidth( caption );
							int stringHeight = getFontMetrics(getFont()).getHeight();
							
							g.drawString(caption,(getWidth()-stringWidth)/2,((getHeight()-stringHeight)/2)+(4*stringHeight/5));//+(4*stringHeight/5));//
							// doSetSize(this,stringWidth+10,stringHeight);
						}
						
						@Override
						public void setText(String text )
						{
							caption = text;
							repaint();
						}
					};
					doSetSize(stripLabel2,200,25);
					stripLabel3 = new JLabel()
					{
						String caption = "....";
						@Override
						public void paintComponent(Graphics g)
						{
							setFont(HEADER_FONT);
							Graphics2D g2 = (Graphics2D)g;
							g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
							
							Shape rect = new RoundRectangle2D.Float(0,2,getWidth()-1,getHeight()-5,10,10);
							
							g2.setStroke(new BasicStroke(2));
							g2.setPaint(Color.WHITE);
							g2.draw( rect );							
							
							g2.setPaint(new Color(29,10,18));
							((RoundRectangle2D)rect).setFrame(1,3,getWidth()-2,getHeight()-6);
							g2.fill( rect );
							g2.setPaint(Color.WHITE);
							
							int stringWidth = getFontMetrics(getFont()).stringWidth( caption );
							int stringHeight = getFontMetrics(getFont()).getHeight();
							
							g.drawString(caption,(getWidth()-stringWidth)/2,((getHeight()-stringHeight)/2)+(4*stringHeight/5));//+(4*stringHeight/5));//
							// doSetSize(this,stringWidth+10,stringHeight);
						}
						
						@Override
						public void setText(String text )
						{
							caption = text;
							repaint();
						}
					};
					doSetSize(stripLabel3,150,25);
				stripPanel2.add( Box.createHorizontalGlue() );
				stripPanel2.add( stripLabel2 );
				stripLabel1.setForeground( stripPanel2.getBackground() );
				stripLabel1.setBackground( stripPanel2.getBackground() );
			northPanel.add( stripLabel1 );
			northPanel.add( Box.createHorizontalStrut(5) );
			northPanel.add( animLogoComponent );
			northPanel.add( Box.createHorizontalStrut(5) );
			northPanel.add( stripPanel2 );
			
			centerPanel = new JPanel();
			centerPanel.setLayout( new BoxLayout(centerPanel,BoxLayout.X_AXIS) );
			centerPanel.setOpaque( false );
			centerPanel.setBackground( EXT_BG );
				gosPanel = new JPanel();
				gosPanel.setOpaque( false );
				gosPanel.setLayout( new GridBagLayout() );
					gosLabelPanel = new JPanel()
					{				
						@Override
						public void paintComponent( Graphics g )
						{
							super.paintComponent( g );
							// g.drawImage( HEADER.getImage(), -5,-5,Double.valueOf(this.getSize().getWidth() ).intValue(),50, 0,0,HEADER.getIconWidth(),HEADER.getIconHeight(),this );
						}
					};
					gosLabelLayout = new GridBagLayout();
					gosLabelPanel.setLayout( gosLabelLayout );
					gosLabelPanel.setOpaque( false );
					gosLabelPanel.setBackground( EXT_BG );
					gosLabelPanel.setBorder( BorderFactory.createLoweredBevelBorder() );
						gosLabel = new JLabel(TITLE_GO);
						doPimpTitle(gosLabel);
						gosLabel.setOpaque( false );
						gosLabel.setHorizontalTextPosition(JLabel.CENTER);
						gosLabel.setForeground( HEADER_COLOR );
					
					appConstraints.weighty = 0;
					appConstraints.weightx = 1;
					appConstraints.ipady = 10;
					
					addComponent( gosLabelPanel,gosLabel,0,0,1,1 );
					
					gosListContainer = new JPanel();
					gosListContainer.setOpaque( false );
					gosListContainer.setLayout( new BorderLayout() );
					gosListContainer.setBackground( EXT_BG );
					gosListContainer.setBorder( BorderFactory.createRaisedBevelBorder() );
						gosHeaderPanel = new JPanel();
						gosHeaderPanel.setOpaque( false );
						gosHeaderLayout = new GridBagLayout();
						gosHeaderPanel.setLayout( gosHeaderLayout );
							gosSerialLabel = new JLabel("");
							doPimpHeader( gosSerialLabel );
							gosSerialLabel.setHorizontalAlignment( JLabel.LEFT );
							gosSerialLabel.setForeground( gosLabel.getForeground() );
							
							gosNameLabel = new JLabel("NAME(S)");
							doPimpHeader( gosNameLabel );
							gosNameLabel.setForeground( gosLabel.getForeground() );
							
							gosDateLabel = new JLabel( "APPOINTMENT DATE");
							doPimpHeader( gosDateLabel );
							gosDateLabel.setForeground( gosLabel.getForeground() );
							
							appConstraints.fill = GridBagConstraints.HORIZONTAL;
							appConstraints.weightx = 1;
							appConstraints.weighty = 1;
							appConstraints.ipady = 10;						
							
							appConstraints.weightx = 0;
							addComponent( gosHeaderPanel, gosSerialLabel,0,0,1,1 );
							
							appConstraints.weightx = 10;
							addComponent( gosHeaderPanel, gosNameLabel,GridBagConstraints.RELATIVE,0,1,1 );
							
							appConstraints.weightx = 1;
							addComponent( gosHeaderPanel, gosDateLabel,GridBagConstraints.RELATIVE,0,1,1 );
						
						grandScrollPanel = new JPanel();
						grandScrollPanel.setLayout( new BoxLayout(grandScrollPanel,BoxLayout.Y_AXIS ) );
						grandScrollPanel.setOpaque( false );
							prefixPanel = new JPanel();
							prefixPanel.setOpaque( true );
							prefixPanel.setBackground(EXT_BG);
							prefixLayout = new GridBagLayout();
							prefixPanel.setLayout( prefixLayout );
							
							gosContentPanel = new JPanel();
							gosContentLayout = new GridBagLayout();
							gosContentPanel.setLayout( gosContentLayout );
							gosContentPanel.setOpaque( false );
							gosContentPanel.setBackground( Color.BLACK );
							
							suffixPanel = new JPanel();
							suffixPanel.setOpaque( true );
							suffixPanel.setBackground(EXT_BG);
							suffixLayout = new GridBagLayout();
							suffixPanel.setLayout( suffixLayout );
						grandScrollPanel.add( prefixPanel );
						grandScrollPanel.add( gosContentPanel );
						grandScrollPanel.add( suffixPanel );
						
						gosContentScroll = new JScrollPane(grandScrollPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
						gosContentScroll.setBorder( BorderFactory.createLineBorder(Color.PINK,0) );
						gosContentScroll.setViewportBorder( BorderFactory.createLineBorder(Color.PINK,0) );
						gosContentScroll.getViewport().setOpaque( false );
					gosListContainer.add( gosHeaderPanel,BorderLayout.PAGE_START );
					gosListContainer.add( gosContentScroll,BorderLayout.CENTER );
				appConstraints.weighty=0;
				appConstraints.fill=GridBagConstraints.BOTH;
				addComponent( gosPanel,gosLabelPanel,0,0,1,1 );
				appConstraints.weighty=1;
				addComponent( gosPanel,gosListContainer,0,1,1,1 );
				appConstraints.weighty=0;
				appConstraints.fill=GridBagConstraints.HORIZONTAL;

				realPanel = new JPanel();
				realPanel.setOpaque( false );
				realPanel.setLayout( new GridBagLayout() );
					realLabelPanel = new JPanel()
					{				
						@Override
						public void paintComponent( Graphics g )
						{
							super.paintComponent( g );
							// g.drawImage( HEADER.getImage(), -5,-5,Double.valueOf(this.getSize().getWidth() ).intValue(),50, 0,0,HEADER.getIconWidth(),HEADER.getIconHeight(),this );
						}
					};
					realLabelLayout = new GridBagLayout();
					realLabelPanel.setLayout( realLabelLayout );
					realLabelPanel.setOpaque( false );
					realLabelPanel.setBackground( EXT_BG );
					realLabelPanel.setBorder( BorderFactory.createLoweredBevelBorder() );
						realLabel = new JLabel(TITLE_REAL);
						doPimpTitle(realLabel);
						realLabel.setOpaque( false );
						realLabel.setHorizontalTextPosition(JLabel.CENTER);
						realLabel.setForeground( HEADER_COLOR );
					
					appConstraints.weighty = 0;
					appConstraints.weightx = 1;
					appConstraints.ipady = 10;
					
					addComponent( realLabelPanel,realLabel,0,0,1,1 );
					
					realListContainer = new JPanel();
					realListContainer.setOpaque( false );
					realListContainer.setLayout( new BorderLayout() );
					realListContainer.setBackground( EXT_BG );
					realListContainer.setBorder( BorderFactory.createRaisedBevelBorder() );
						realHeaderPanel = new JPanel();
						realHeaderPanel.setOpaque( false );
						realHeaderLayout = new GridBagLayout();
						realHeaderPanel.setLayout( realHeaderLayout );
							realSerialLabel = new JLabel("");
							doPimpHeader( realSerialLabel );
							realSerialLabel.setHorizontalAlignment( JLabel.LEFT );
							realSerialLabel.setForeground( realLabel.getForeground() );
							
							realNameLabel = new JLabel("NAME(S)");
							doPimpHeader( realNameLabel );
							realNameLabel.setForeground( realLabel.getForeground() );
							
							realDateLabel = new JLabel("APPLICATION STATUS");
							doPimpHeader( realDateLabel );
							realDateLabel.setForeground( realLabel.getForeground() );
							
							appConstraints.fill = GridBagConstraints.HORIZONTAL;
							appConstraints.weightx = 1;
							appConstraints.weighty = 1;
							appConstraints.ipady = 10;								
							
							appConstraints.weightx = 0;
							addComponent( realHeaderPanel, realSerialLabel,0,0,1,1 );
							
							appConstraints.weightx = 10;
							addComponent( realHeaderPanel, realNameLabel,GridBagConstraints.RELATIVE,0,1,1 );
							
							appConstraints.weightx = 1;
							addComponent( realHeaderPanel, realDateLabel,GridBagConstraints.RELATIVE,0,1,1 );
						
						grandScrollPanelc = new JPanel();
						grandScrollPanelc.setLayout( new BoxLayout(grandScrollPanelc,BoxLayout.Y_AXIS ) );
						grandScrollPanelc.setOpaque( false );
							prefixPanelc = new JPanel();
							prefixPanelc.setOpaque( true );
							prefixPanelc.setBackground(EXT_BG);
							prefixLayoutc = new GridBagLayout();
							prefixPanelc.setLayout( prefixLayoutc );
							
							realContentPanel = new JPanel();
							realContentLayout = new GridBagLayout();
							realContentPanel.setLayout( realContentLayout );
							realContentPanel.setOpaque( false );
							realContentPanel.setBackground( Color.BLACK );
							
							suffixPanelc = new JPanel();
							suffixPanelc.setOpaque( true );
							suffixPanelc.setBackground(EXT_BG);
							suffixLayoutc = new GridBagLayout();
							suffixPanelc.setLayout( suffixLayoutc );
						grandScrollPanelc.add( prefixPanelc );
						grandScrollPanelc.add( realContentPanel );
						grandScrollPanelc.add( suffixPanelc );
						
						realContentScroll = new JScrollPane(grandScrollPanelc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
						realContentScroll.setBorder( BorderFactory.createLineBorder(Color.PINK,0) );
						realContentScroll.setViewportBorder( BorderFactory.createLineBorder(Color.PINK,0) );
						realContentScroll.getViewport().setOpaque( false );
					realListContainer.add( realHeaderPanel,BorderLayout.PAGE_START );
					realListContainer.add( realContentScroll,BorderLayout.CENTER );
				appConstraints.weighty=0;
				appConstraints.fill=GridBagConstraints.BOTH;
				addComponent( realPanel,realLabelPanel,0,0,1,1 );
				appConstraints.weighty=1;
				addComponent( realPanel,realListContainer,0,1,1,1 );
				appConstraints.weighty=0;
				appConstraints.fill=GridBagConstraints.HORIZONTAL;

				othersPanel = new JPanel();
				othersPanel.setOpaque( false );
				othersPanel.setLayout( new GridBagLayout() );
					othersLabelPanel = new JPanel()
					{				
						@Override
						public void paintComponent( Graphics g )
						{
							super.paintComponent( g );
							// g.drawImage( HEADER.getImage(), -5,-5,Double.valueOf(this.getSize().getWidth() ).intValue(),50, 0,0,HEADER.getIconWidth(),HEADER.getIconHeight(),this );
						}
					};
					othersLabelLayout = new GridBagLayout();
					othersLabelPanel.setLayout( othersLabelLayout );
					othersLabelPanel.setOpaque( false );
					othersLabelPanel.setBackground( EXT_BG );
					othersLabelPanel.setBorder( BorderFactory.createLoweredBevelBorder() );
						othersLabel = new JLabel(TITLE_OTHERS);
						doPimpTitle(othersLabel);
						othersLabel.setOpaque( false );
						othersLabel.setHorizontalTextPosition(JLabel.CENTER);
						othersLabel.setForeground( HEADER_COLOR );
						
						appConstraints.weighty = 0;
						appConstraints.weightx = 1;
						appConstraints.ipady = 10;
						
					addComponent( othersLabelPanel,othersLabel,0,0,1,1 );
					
					othersListContainer = new JPanel();
					othersListContainer.setOpaque( false );
					othersListContainer.setLayout( new BorderLayout() );
					othersListContainer.setBackground( EXT_BG );
					othersListContainer.setBorder( BorderFactory.createRaisedBevelBorder() );
						othersHeaderPanel = new JPanel();
						othersHeaderPanel.setOpaque( false );
						othersHeaderLayout = new GridBagLayout();
						othersHeaderPanel.setLayout( othersHeaderLayout );
							othersSerialLabel = new JLabel("");
							doPimpHeader( othersSerialLabel );
							othersSerialLabel.setHorizontalAlignment( JLabel.LEFT );
							othersSerialLabel.setForeground( othersLabel.getForeground() );
							
							othersNameLabel = new JLabel("NAME(S) & APPLICATION STATUS");
							doPimpHeader( othersNameLabel );
							othersNameLabel.setForeground( othersLabel.getForeground() );
							
							othersRefLabel = new JLabel( "WHOM TO SEE");
							doPimpHeader( othersRefLabel );
							othersRefLabel.setForeground( othersLabel.getForeground() );
							
							othersContactLabel = new JLabel( "REMARK ");
							doPimpHeader( othersContactLabel );
							othersContactLabel.setForeground( othersLabel.getForeground() );
							othersContactLabel.setHorizontalAlignment(JLabel.RIGHT);
							
							appConstraints.fill = GridBagConstraints.HORIZONTAL;
							appConstraints.weightx = 1;
							appConstraints.weighty = 1;
							appConstraints.ipady = 10;								
							
							// appConstraints.weightx = 0;
							// addComponent( othersHeaderPanel, othersSerialLabel,0,0,1,1 );
							
							appConstraints.weightx = 1;
							addComponent( othersHeaderPanel, othersNameLabel,GridBagConstraints.RELATIVE,0,GridBagConstraints.REMAINDER,1 );
							
							// appConstraints.weightx = 1;
							// addComponent( othersHeaderPanel, othersRefLabel,GridBagConstraints.RELATIVE,0,1,1 );

							// appConstraints.weightx = 1;
							// addComponent( othersHeaderPanel, othersContactLabel,GridBagConstraints.RELATIVE,0,1,1 );

						grandScrollPanelb = new JPanel();
						grandScrollPanelb.setLayout( new BoxLayout(grandScrollPanelb,BoxLayout.Y_AXIS ) );
						grandScrollPanelb.setOpaque( false );
							prefixPanelb = new JPanel();
							prefixPanelb.setOpaque( true );
							prefixPanelb.setBackground(EXT_BG);
							prefixLayoutb = new GridBagLayout();
							prefixPanelb.setLayout( prefixLayoutb );
							
						othersContentPanel = new JPanel();
						othersContentLayout = new GridBagLayout();
						othersContentPanel.setLayout( othersContentLayout );
						othersContentPanel.setOpaque( false );
						othersContentPanel.setBackground( Color.BLACK );
						
							suffixPanelb = new JPanel();
							suffixPanelb.setOpaque( true );
							suffixPanelb.setBackground(EXT_BG);
							suffixLayoutb = new GridBagLayout();
							suffixPanelb.setLayout( suffixLayoutb );
						grandScrollPanelb.add( prefixPanelb );
						grandScrollPanelb.add( othersContentPanel );
						grandScrollPanelb.add( suffixPanelb );
						
						othersContentScroll = new JScrollPane(grandScrollPanelb,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
						othersContentScroll.setBorder( BorderFactory.createLineBorder(Color.PINK,0) );
						othersContentScroll.setViewportBorder( BorderFactory.createLineBorder(Color.PINK,0) );
						othersContentScroll.getViewport().setOpaque( false );
					othersListContainer.add( othersHeaderPanel, BorderLayout.PAGE_START );
					othersListContainer.add( othersContentScroll, BorderLayout.CENTER );
				appConstraints.weighty=0;
				appConstraints.fill=GridBagConstraints.BOTH;
				addComponent( othersPanel,othersLabelPanel,0,0,1,1 );
				appConstraints.weighty=1;
				addComponent( othersPanel,othersListContainer,0,1,1,1 );
				appConstraints.weighty=0;
				appConstraints.fill=GridBagConstraints.HORIZONTAL;
				
			southPanel = new JPanel();
			southPanel.setOpaque( true );
			southPanel.setLayout( new BorderLayout() );
			southPanel.setBackground( Color.RED );			
					messageScrollPanel = new JPanel();
					messageScrollPanel.setBackground(Color.RED);
					messageScrollPanel.setLayout(new BoxLayout(messageScrollPanel,BoxLayout.X_AXIS));
						messagesPanel = new JPanel();
						messagesPanel.setLayout( new BoxLayout(messagesPanel, BoxLayout.X_AXIS));
						messagesPanel.setBackground(Color.RED);
						prefixLabel = new JLabel();
						suffixLabel = new JLabel();
					messageScrollPanel.add(prefixLabel);
					messageScrollPanel.add(messagesPanel);
					messageScrollPanel.add(suffixLabel);
				messageScroll = new JScrollPane( messageScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
				messageScroll.setBorder( BorderFactory.createLineBorder( Color.BLACK,0 ) );
			announcementLabel = new JLabel("<html><font color=\"yellow\">ANNOUNCEMENTS:</font></html>")
			{
				String caption = "INFORMATION";
				@Override
				public void paintComponent(Graphics g)
				{
					setFont(LABEL_FONT_1);
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setStroke(new BasicStroke(2));
					g2.setPaint(Color.WHITE);
					g.drawOval(0,2,getWidth()-1,getHeight()-5);
					g2.setPaint(new Color(29,10,18));
					g.fillOval(1,3,getWidth()-2,getHeight()-6);
					g.setColor(Color.WHITE);
					int stringWidth = getFontMetrics(getFont()).stringWidth( caption );
					int stringHeight = getFontMetrics(getFont()).getHeight();
					g.drawString(caption,(getWidth()-1-stringWidth)/2,((getHeight()-stringHeight)/2)+(4*stringHeight/5));//
				}
			};
			announcementLabel.setOpaque(true);
			doSetSize(announcementLabel,150,25);
			southPanel.add( announcementLabel, BorderLayout.WEST );
			southPanel.add( messageScroll, BorderLayout.CENTER );
			southPanel.add( stripLabel3, BorderLayout.EAST );
		add( northPanel, BorderLayout.NORTH );
		add( centerPanel, BorderLayout.CENTER );
		add( southPanel, BorderLayout.SOUTH );

		threadExecutor.execute((Runnable)animLogoComponent);
		timerHandler = new Timer(FPS,new TimerHandler());
		timerHandler.start();
		// threadExecutor.execute(new TimerHandler());
		threadExecutor.execute(new ScrollHandler());
		// setMode(MODE);
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.drawImage( BG.getImage(), 0,0,Double.valueOf(this.getSize().getWidth() ).intValue(),Double.valueOf(this.getSize().getHeight() ).intValue(),this );
	}
	
	public void setMode( Mode MODE )
	{
		centerPanel.removeAll();
		refreshLists();
		clockUnit.stopTime();
		switch( MODE )
		{
			case GO:		//GO
			{
				gosContentScroll.getViewport().setViewPosition( new Point(0,0) );
				centerPanel.add( gosPanel );
			}break;
			case OTHERS:	//OTHERS
			{
				othersContentScroll.getViewport().setViewPosition( new Point(0,0) );
				centerPanel.add( othersPanel );
			}break;
			case REAL:		//REAL-TIME
			{
				realContentScroll.getViewport().setViewPosition( new Point(0,0) );
				centerPanel.add( realPanel );
			}break;
			case CLOCK:
			{
				centerPanel.add(clockUnit);
				clockUnit.startTime();
			}break;
			case ON:
			{
			}break;
		}
		centerPanel.validate();
	}
	
	public void setTime( String text )
	{
		stripLabel2.setText(text);
	}
	
	public void update( Object update )
	{
		if( update instanceof BackendModel )
			updateModel( (BackendModel)update );
		else
		if( update instanceof Mode )
		{
			// setMode((Mode)update);
		}
		else
		if( update instanceof Airbag )
		{
			if( update == Airbag.TOGGLE_ALERT )
			{
				southPanel.setVisible(!southPanel.isVisible());
				if(southPanel.isVisible())
					messageScroll.getViewport().setViewPosition( new Point(0,0) );
			}
		}
	}
	
	void updateModel( BackendModel dataModel )
	{
		this.dataModel = dataModel;
		MSG_RATE = dataModel.getMessageRate();
		LIST_RATE = dataModel.getListRate();
	}
	
	ScheduleList ACTIVE_LIST;
	public void refreshLists()
	{
		ACTIVE_LIST = dataModel.getActiveList();
		
		if( ACTIVE_LIST != null )
		{
			List<Application> apps = ACTIVE_LIST.getApplications();
			// List<Application> apps = new ArrayList<Application>();
			
			// for( Application a : ACTIVE_LIST.getApplications() )
				// apps.add(a);			
			Collections.sort( apps, new ApplicationComparator() );
			
			int TEXT_LIMIT = 35;
			switch( MODE )
			{
				case GO:
				{
					gosNames.clear();
					gosDates.clear();
					gosContentPanel.removeAll();
					gosContentPanel.validate();
					gosContentScroll.validate();
					
					boolean ex = true;
					while(ex)
						try
						{
							for( Application app : apps )
							{
								ex = false;
								gosNames.add( getTitleCase((app.getName().length()<=TEXT_LIMIT)?app.getName():app.getName().substring(0,TEXT_LIMIT-3)+"...") );
								gosDates.add( "<html><font color=\"#e2c391\">"+dataModel.getDateString(app.getAppointmentDate())+"</font></html>" );
								updateGosList(gosNames.get(gosNames.size()-1),gosDates.get(gosDates.size()-1),gosNames.size());
							}					
						}
						catch(Exception e)
						{
							ex = true;
							continue;
						}
					gosContentPanel.validate();
					gosContentScroll.getViewport().validate();
				}break;
				case OTHERS:
				{
					othersNames.clear();
					othersNames2.clear();
					othersContact.clear();
					othersContentPanel.removeAll();
					othersContentPanel.invalidate();
					
					boolean ex = true;
					while(ex)
						try
						{
							for( Application app : apps )
							{
								ex = false;
								othersNames.add( getTitleCase((app.getName().length()<=TEXT_LIMIT)?app.getName():app.getName().substring(0,TEXT_LIMIT-3)+"...") );
								if( app.getStatus().equals( Application.Status.REFERRED ) )
								{
									othersNames2.add( "<html><p style=\"font-style:italics\"><font color=\"#e2c391\">SEE &gt;&gt; </font>"+getTitleCase(app.getRefereeCard().getName())+(app.getRefereeCard().getDesignation().trim().length()>0?" <font style=\"color:#e2c391;font-size:medium\">"+getTitleCase(app.getRefereeCard().getDesignation().trim())+"</font>":"")+" </p></html>" );
									othersContact.add( "<html><p style=\"font-style:italics\"><font color=\"#e2c391\">@ </font>"+getSentenceCase(dataModel.getContactString(app.getRefereeCard()).replace("<html>","").replace("</html>",""))+"</p></html>" );
								}
								else
								if( app.getStatus().equals( Application.Status.DEFERRED ) )
								{
									othersNames2.add( "<html><p style=\"font-style:italics\">"+dataModel.getStatusString(app.getStatus())+"</p></html>" );
									othersContact.add( "<html><p style=\"font-style:italics\"><font color=\"yellow\">RECOMM: </font>"+getSentenceCase(app.getRecommendation())+"</p></html>");
								}
								else
								if( app.getStatus().equals( Application.Status.DEFAULT ) )
								{
									othersNames2.add( dataModel.getStatusString(app.getStatus()) );
									othersContact.add("");
								}
								updateOthersList( othersNames.get(othersNames.size()-1),othersNames2.get(othersNames2.size()-1),othersContact.get(othersContact.size()-1),othersNames.size() );
							}
						}
						catch(Exception e)
						{
							ex = true;
							continue;
						}
					othersContentScroll.getViewport().validate();
				}break;
				case REAL:
				{
					realLabel.setText(ACTIVE_LIST.getName());
					realNames.clear();
					realStatus.clear();
					realContentPanel.removeAll();
					realContentPanel.invalidate();
					boolean ex = true;
					while(ex)
						try
						{
							for( Application app : apps )
							{
								ex = false;
								realNames.add( getTitleCase((app.getName().length()<=TEXT_LIMIT)?app.getName():app.getName().substring(0,TEXT_LIMIT-3)+"...") );
								realStatus.add( dataModel.getStatusString(app.getStatus()) );
								updateRealList( realNames.get(realNames.size()-1),realStatus.get(realStatus.size()-1),realNames.size() );
							}
						}
						catch(Exception e)
						{
							ex = true;
							continue;
						}
					realContentScroll.getViewport().validate();
				}break;
			}
		}
	}
	
	public void refreshMessages()
	{
		if( dataModel!=null && dataModel.countMessages()>0)
		{
			messagesPanel.removeAll();
			messagesPanel.invalidate();
			boolean ex = true;
			while(ex)
				try
				{
					for( int i=0; i<dataModel.countMessages(); i++ )
					{
						ex = false;
						JLabel messageLabel = new JLabel(getSentenceCase(dataModel.getMessage(i)));
						doPimpCell(messageLabel);
						messageLabel.setFont(CELL_FONT);
						messageLabel.setBackground(Color.RED);
						
						if(i==0)
							messagesPanel.add(getScrollLogo());
						messagesPanel.add(messageLabel);
						messagesPanel.add(getScrollLogo());
					}
				}
				catch(Exception e)
				{
					ex = true;
					continue;
				}
			messagesPanel.add(Box.createHorizontalGlue());
			messageScroll.getViewport().validate();
		}
	}
	
	String getTitleCase( String message )
	{
		StringBuffer ret = new StringBuffer("");
		StringTokenizer tokens = new StringTokenizer(message);
		while( tokens.hasMoreTokens() )
		{
			StringBuilder str = new StringBuilder(tokens.nextToken());
			str.setCharAt(0,str.substring(0,1).toUpperCase().charAt(0));
			ret.append(str.toString()+" ");
			
		}
		return ret.toString().trim();
	}
	
	String getSentenceCase( String message )
	{
		StringBuffer ret = new StringBuffer("");
		if( message.length() > 0 )
		{
			StringBuilder str2 = new StringBuilder(message);
			str2.setCharAt(0,str2.substring(0,1).toUpperCase().charAt(0));
			ret.append(str2.toString());		
		}
		return ret.toString().trim();		
	}
	
	public void updateGosList( String name, String date, int size )
	{
		appConstraints.fill = GridBagConstraints.HORIZONTAL;
		appConstraints.weightx = 1;
		appConstraints.weighty = 1;
		appConstraints.ipady = ROW_PADDING;		
		appConstraints.insets = new Insets(1,0,1,0);

		Color bg = ((size%2==0)?bg1:bg2);
		
		JLabel serialLabel = new JLabel(" "+size+". ");
		doPimpCell( serialLabel );
		serialLabel.setBackground( bg );
		serialLabel.setForeground( SN );
		
		
		JLabel nameLabel = new JLabel( name );
		doPimpCell( nameLabel );
		nameLabel.setBackground( bg );
		
		
		JLabel dateLabel = new JLabel( date );
		doPimpCell( dateLabel );
		dateLabel.setBackground( bg );
		
		appConstraints.anchor = GridBagConstraints.LINE_START;
		
		appConstraints.weightx = 0;
		addComponent( gosContentPanel,serialLabel,0,size,1,1 );
		
		appConstraints.weightx = 10;
		addComponent( gosContentPanel,nameLabel,GridBagConstraints.RELATIVE,size,1,1 );
		
		appConstraints.weightx = 1;
		addComponent( gosContentPanel,dateLabel,GridBagConstraints.RELATIVE,size,1,1 );
		appConstraints.insets = new Insets(0,0,0,0);
		// doDynamicFont(nameLabel.getText(),nameLabel);
		// doDynamicFont(dateLabel.getText(),dateLabel);
	}
	
	void updateOthersList( String name, String name2, String contact, int size )
	{
		appConstraints.fill = GridBagConstraints.HORIZONTAL;
		appConstraints.weightx = 1;
		appConstraints.weighty = 1;
		appConstraints.insets = new Insets(1,0,1,0);
		appConstraints.ipady = ROW_PADDING;
		
		Color bg = ((size%2==0)?bg1:bg2);
		
		JLabel serialLabel = new JLabel(" "+size+". ");
		doPimpCell( serialLabel );
		serialLabel.setOpaque( false );
		serialLabel.setForeground( SN );
		
		JLabel nameLabel = new JLabel(name);
		doPimpCell( nameLabel );
		nameLabel.setBackground( bg );
		
		JPanel refPanel = new JPanel();	
		refPanel.setBackground(bg);
		GroupLayout refLayout = new GroupLayout(refPanel);
		refPanel.setLayout(refLayout);
		refLayout.setAutoCreateGaps(false);
		refLayout.setAutoCreateContainerGaps(false);
			
			int INDENT = 40;
			JLabel nameLabel2 = new JLabel(name2)
			{
				public void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					// Graphics2D g2 = (Graphics2D)g;
					// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
					// g2.setStroke(new BasicStroke(2));
					// g2.setPaint(Color.WHITE);
					// int stringWidth = getWidth()/2;//getFontMetrics(getFont()).stringWidth( getText() );
					// int stringHeight = getHeight();//getFontMetrics(getFont()).getHeight();
					// g.drawRoundRect(getWidth()-stringWidth,1,stringWidth-2,stringHeight-2,5,5);
				}
			};
			doPimpCell( nameLabel2 );
			nameLabel2.setOpaque( false );
			nameLabel2.setHorizontalAlignment(JLabel.RIGHT);
			nameLabel2.setMaximumSize( new Dimension(othersContentScroll.getWidth()-othersContentScroll.getVerticalScrollBar().getWidth()-INDENT,Short.MAX_VALUE) );
			
			JLabel contactLabel = new JLabel(contact);
			doPimpCell( contactLabel );
			contactLabel.setOpaque( false );
			contactLabel.setHorizontalAlignment(JLabel.LEFT);
			contactLabel.setMaximumSize( new Dimension(othersContentScroll.getWidth()-othersContentScroll.getVerticalScrollBar().getWidth()-INDENT,Short.MAX_VALUE) );
			contactLabel.setMinimumSize( new Dimension(othersContentScroll.getWidth()-othersContentScroll.getVerticalScrollBar().getWidth()-INDENT,0) );
			
			Component c1 = Box.createRigidArea(new Dimension(INDENT,0));
			Component c2 = Box.createRigidArea(new Dimension(INDENT,0));
		
		refLayout.setVerticalGroup(
			refLayout.createSequentialGroup()
				.addGroup(refLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(serialLabel)
					.addComponent(nameLabel))
				.addGroup(refLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(c1)
					.addComponent(nameLabel2))
				.addGroup(refLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(c2)
					.addComponent(contactLabel)));
		refLayout.setHorizontalGroup(
			refLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(refLayout.createSequentialGroup()
					.addComponent(serialLabel)
					.addComponent(nameLabel))
				.addGroup(refLayout.createSequentialGroup()
					.addComponent(c1)
					.addComponent(nameLabel2))
				.addGroup(refLayout.createSequentialGroup()
					.addComponent(c2)
					.addComponent(contactLabel)));
					
		appConstraints.anchor = GridBagConstraints.LINE_START;									
		addComponent( othersContentPanel,refPanel,GridBagConstraints.RELATIVE,size,GridBagConstraints.REMAINDER,1 );

		appConstraints.insets = new Insets(0,0,0,0);
	}
	
	void updateRealList( String name, String status, int size )
	{
		appConstraints.fill = GridBagConstraints.HORIZONTAL;
		appConstraints.weightx = 1;
		appConstraints.weighty = 1;
		appConstraints.ipady = ROW_PADDING;
		appConstraints.insets = new Insets(1,0,1,0);
		
		Color bg = ((size%2==0)?bg1:bg2);
		
		JLabel serialLabel = new JLabel(" "+size+". ");
		doPimpCell( serialLabel );
		serialLabel.setBackground( bg );
		serialLabel.setForeground( SN );
		
		JLabel nameLabel = new JLabel(name);
		doPimpCell( nameLabel );
		nameLabel.setBackground( bg );
		
		JLabel nameLabel2 = new JLabel(status);
		doPimpCell( nameLabel2 );
		nameLabel2.setBackground( bg );
		
		appConstraints.anchor = GridBagConstraints.LINE_START;									
		appConstraints.weightx = 0;
		addComponent( realContentPanel,serialLabel,0,size,1,1  );
		
		appConstraints.weightx = 10;
		addComponent( realContentPanel,nameLabel,GridBagConstraints.RELATIVE,size,1,1 );
		
		appConstraints.weightx = 1;
		addComponent( realContentPanel,nameLabel2,GridBagConstraints.RELATIVE,size,1,1 );
		appConstraints.insets = new Insets(0,0,0,0);
	}
	
	public void addComponent( Container cC, Component c, int x, int y, int dx, int dy )
	{
		appConstraints.gridx = x;
		appConstraints.gridy = y;
		appConstraints.gridwidth = dx;
		appConstraints.gridheight = dy;
		cC.add( c,appConstraints );
	}
	
	void doDynamicFont( String text, JComponent component )
	{
		int targetWidth = component.getWidth();
		int targetHeight = component.getHeight();
		int stringHeight = component.getFontMetrics(component.getFont()).getHeight();
		int stringWidth = component.getFontMetrics(component.getFont()).stringWidth(text);
		
		int pts = component.getFont().getSize();
		Font dynamicFont = component.getFont();
		
		if( stringWidth < targetWidth )
			while( stringHeight < targetHeight && stringWidth < targetWidth && pts <= 300 )
			{
				dynamicFont = new Font( component.getFont().getFontName(),component.getFont().getStyle(),++pts );
				component.setFont( dynamicFont );
				stringWidth = component.getFontMetrics(dynamicFont).stringWidth(text);
				stringHeight = component.getFontMetrics(dynamicFont).getHeight();
			}
		else
			do
			{
				dynamicFont = new Font( component.getFont().getFontName(),component.getFont().getStyle(),--pts );
				component.setFont( dynamicFont );
				stringWidth = component.getFontMetrics(dynamicFont).stringWidth(text);
				stringHeight = component.getFontMetrics(dynamicFont).getHeight();
			}while( stringWidth > targetWidth );
		
		dynamicFont = new Font( component.getFont().getFontName(),component.getFont().getStyle(), --pts );
		component.setFont( dynamicFont );
	}
	
	public void doPimpHeader( JLabel c )
	{
		c.setFont( CELL_HEADER_FONT );
		c.setForeground( Color.WHITE );
		c.setOpaque( false );
		c.setHorizontalAlignment( SwingConstants.CENTER );
	}
	
	public void doPimpTitle( JLabel c )
	{
		c.setFont( MODULE_HEADER_FONT );
		c.setForeground( Color.WHITE );
		c.setOpaque( false );
		c.setHorizontalAlignment( SwingConstants.CENTER );
	}
	
	public void doPimpCell( JLabel c )
	{
		c.setFont( CELL_FONT );
		c.setForeground( Color.WHITE );
		c.setOpaque( true );
		c.setHorizontalAlignment( SwingConstants.LEFT );
	}
	
	void doSetSize( Component c, int x, int y )
	{
		c.setSize( new Dimension(x,y) );
		c.setPreferredSize( new Dimension(x,y) );
		c.setMinimumSize( new Dimension(x,y) );
		c.setMaximumSize( new Dimension(x,y) );
	}

	public String getDate()
	{
		String date = DateFormat.getDateInstance(DateFormat.LONG).format(GregorianCalendar.getInstance().getTime()).toUpperCase();
		
		return date;
	}
	
	public String getTime()
	{
		String time = DateFormat.getTimeInstance(DateFormat.LONG).format(GregorianCalendar.getInstance().getTime()).toUpperCase();
		
		return time;
	}
	
	class TimerHandler implements Runnable, ActionListener
	{
		JViewport gosViewport;
		JViewport othersViewport;
		JViewport realViewport;
		public TimerHandler()
		{
			gosViewport = gosContentScroll.getViewport();
			othersViewport = othersContentScroll.getViewport();
			realViewport = realContentScroll.getViewport();
		}
		
		double viewWidth,viewHeight;
		double rectWidth,rectHeight;
		double x,y;
		double dx,dy;
		
		@Override
		public void actionPerformed( ActionEvent ae )
		{
			doScroll();
		}
		
		@Override
		public void run()
		{
			while(true)
			try
			{
				doScroll();
				Thread.sleep(FPS);
			}
			catch(Exception e )
			{
				continue;
			}
		}
	
		void doScroll()
		{
			switch( MODE )
			{
				case GO:	//GO
				{
					viewWidth = gosViewport.getViewSize().getWidth();
					viewHeight = gosViewport.getViewSize().getHeight();
					
					rectWidth = gosViewport.getExtentSize().getWidth();
					rectHeight = gosViewport.getExtentSize().getHeight();
					
					x = gosViewport.getViewPosition().getX();
					y = gosViewport.getViewPosition().getY();
					
					dx = gosViewport.toViewCoordinates( gosViewport.getViewPosition() ).getX();
					dy = gosViewport.toViewCoordinates( gosViewport.getViewPosition() ).getY();
					
					int listSize=0;
					
					if( y == 0  && LIST_RATE > 0 )
					{
						Runnable refresh = new Runnable()
						{
							public void run()
							{
								refreshLists();
								gosViewport.validate();
							}
						};
						
						
						Future refreshing = threadExecutor.submit(refresh);
						try
						{
							refreshing.get();
						}
						catch(Exception e){}
							
						
						listSize = gosNames.size();
						
						viewWidth = gosViewport.getViewSize().getWidth();
						viewHeight = gosViewport.getViewSize().getHeight();
						
						rectWidth = gosViewport.getExtentSize().getWidth();
						rectHeight = gosViewport.getExtentSize().getHeight();
						
						x = gosViewport.getViewPosition().getX();
						y = gosViewport.getViewPosition().getY();
						
						dx = gosViewport.toViewCoordinates( gosViewport.getViewPosition() ).getX();
						dy = gosViewport.toViewCoordinates( gosViewport.getViewPosition() ).getY();
						
						double mod = rectHeight - (viewHeight%rectHeight);
						double pH = rectHeight/(viewHeight/listSize);
						
						int numRows = Double.valueOf(Math.ceil(pH)).intValue();
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									doSetSize(prefixPanel,Double.valueOf(""+viewWidth).intValue(),Double.valueOf(""+rectHeight).intValue());
									doSetSize(suffixPanel,Double.valueOf(""+viewWidth).intValue(),Double.valueOf(""+rectHeight).intValue());
									gosViewport.validate();
								}
							}
						);
					}
					
					if( viewHeight-dy > rectHeight ) //&& counter == 0
					{
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									gosViewport.setViewPosition( new Point(Double.valueOf(""+x).intValue(),Double.valueOf(""+y).intValue()+LIST_RATE) );
								}
							});
					}
					else
					{
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									gosViewport.setViewPosition( new Point(0,0) );
								}
							});
					}					
				}break;
				case OTHERS:
				{
					viewWidth = othersViewport.getViewSize().getWidth();
					viewHeight = othersViewport.getViewSize().getHeight();
					
					rectWidth = othersViewport.getExtentSize().getWidth();
					rectHeight = othersViewport.getExtentSize().getHeight();
					
					x = othersViewport.getViewPosition().getX();
					y = othersViewport.getViewPosition().getY();
					
					dx = othersViewport.toViewCoordinates( othersViewport.getViewPosition() ).getX();
					dy = othersViewport.toViewCoordinates( othersViewport.getViewPosition() ).getY();
					
					int listSize=0;
					
					if( y == 0  && LIST_RATE > 0 )
					{
						Runnable refresh = new Runnable()
						{
							public void run()
							{
								refreshLists();
								othersViewport.validate();
							}
						};
						
						Future refreshing = threadExecutor.submit(refresh);
						try
						{
							refreshing.get();
						}
						catch(Exception e){}
						
						listSize = othersNames.size();
						
						viewWidth = othersViewport.getViewSize().getWidth();
						viewHeight = othersViewport.getViewSize().getHeight();
						
						rectWidth = othersViewport.getExtentSize().getWidth();
						rectHeight = othersViewport.getExtentSize().getHeight();
						
						x = othersViewport.getViewPosition().getX();
						y = othersViewport.getViewPosition().getY();
						
						dx = othersViewport.toViewCoordinates( othersViewport.getViewPosition() ).getX();
						dy = othersViewport.toViewCoordinates( othersViewport.getViewPosition() ).getY();
						
						double mod = rectHeight - (viewHeight%rectHeight);
						double pH = rectHeight/(viewHeight/listSize);
						
						int numRows = Double.valueOf(Math.ceil(pH)).intValue();
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									doSetSize(prefixPanelb,Double.valueOf(""+viewWidth).intValue(),Double.valueOf(""+rectHeight).intValue());
									doSetSize(suffixPanelb,Double.valueOf(""+viewWidth).intValue(),Double.valueOf(""+rectHeight).intValue());
								}
							}
						);
					}
					
					if( viewHeight-dy > rectHeight ) //&& counter == 0
					{
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									othersViewport.setViewPosition( new Point(Double.valueOf(""+x).intValue(),Double.valueOf(""+y).intValue()+LIST_RATE) );
								}
							});
					}
					else
					{
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									othersViewport.setViewPosition( new Point(0,0) );
								}
							});
					}
				}break;
				case REAL:
				{
					viewWidth = realViewport.getViewSize().getWidth();
					viewHeight = realViewport.getViewSize().getHeight();
					
					rectWidth = realViewport.getExtentSize().getWidth();
					rectHeight = realViewport.getExtentSize().getHeight();
					
					x = realViewport.getViewPosition().getX();
					y = realViewport.getViewPosition().getY();
					
					dx = realViewport.toViewCoordinates( realViewport.getViewPosition() ).getX();
					dy = realViewport.toViewCoordinates( realViewport.getViewPosition() ).getY();
					
					int listSize=0;
					
					if( y == 0 && LIST_RATE > 0 )
					{
						Runnable refresh = new Runnable()
						{
							public void run()
							{
								refreshLists();
								realViewport.validate();
							}
						};
						
						Future refreshing = threadExecutor.submit(refresh);
						try
						{
							refreshing.get();
						}
						catch(Exception e){}
						
						listSize = realNames.size();
						
						viewWidth = realViewport.getViewSize().getWidth();
						viewHeight = realViewport.getViewSize().getHeight();
						
						rectWidth = realViewport.getExtentSize().getWidth();
						rectHeight = realViewport.getExtentSize().getHeight();
						
						x = realViewport.getViewPosition().getX();
						y = realViewport.getViewPosition().getY();
						
						dx = realViewport.toViewCoordinates( realViewport.getViewPosition() ).getX();
						dy = realViewport.toViewCoordinates( realViewport.getViewPosition() ).getY();
						
						double mod = rectHeight - (viewHeight%rectHeight);
						double pH = rectHeight/(viewHeight/listSize);
						
						int numRows = Double.valueOf(Math.ceil(pH)).intValue();
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									doSetSize(prefixPanelc,Double.valueOf(""+viewWidth).intValue(),Double.valueOf(""+rectHeight).intValue());
									doSetSize(suffixPanelc,Double.valueOf(""+viewWidth).intValue(),Double.valueOf(""+rectHeight).intValue());
								}
							}
						);
					}
					
					if( viewHeight-dy > rectHeight ) //&& counter == 0
					{
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									realViewport.setViewPosition( new Point(Double.valueOf(""+x).intValue(),Double.valueOf(""+y).intValue()+LIST_RATE) );
								}
							});
					}
					else
					{
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									realViewport.setViewPosition( new Point(0,0) );
								}
							});
					}
				}break;
			}
			gosViewport.validate();
			othersViewport.validate();
			realViewport.validate();
		}
	}
	
	class ScrollHandler implements Runnable
	{
		JViewport viewPort;					
		double viewWidth;
		double viewHeight;
		
		double rectWidth;
		double rectHeight;
		
		double x,y;
		
		double dx;
		double dy;
		@Override
		public void run()
		{
			while(true)
				try
				{
					stripLabel2.setText( getDate() );
					stripLabel3.setText( getTime() );
					viewPort = messageScroll.getViewport();
					
					viewWidth = viewPort.getViewSize().getWidth();
					viewHeight = viewPort.getViewSize().getHeight();
					
					rectWidth = viewPort.getExtentSize().getWidth();
					rectHeight = viewPort.getExtentSize().getHeight();
					
					x = viewPort.getViewPosition().getX();
					y = viewPort.getViewPosition().getY();
					
					dx = viewPort.toViewCoordinates( viewPort.getViewPosition() ).getX();
					dy = viewPort.toViewCoordinates( viewPort.getViewPosition() ).getY();
					
					if( x == 0  && MSG_RATE > 0 )
					{
						
						Runnable refresh = new Runnable()
						{
							public void run()
							{
								refreshMessages();
								viewPort.validate();
							}
						};
						
						Future refreshing = threadExecutor.submit(refresh);
						refreshing.get();						
						
						viewWidth = viewPort.getViewSize().getWidth();
						viewHeight = viewPort.getViewSize().getHeight();
						
						rectWidth = viewPort.getExtentSize().getWidth();
						rectHeight = viewPort.getExtentSize().getHeight();
						
						x = viewPort.getViewPosition().getX();
						y = viewPort.getViewPosition().getY();
						
						dx = viewPort.toViewCoordinates( viewPort.getViewPosition() ).getX();
						dy = viewPort.toViewCoordinates( viewPort.getViewPosition() ).getY();
						
						double mod = rectHeight - (viewHeight%rectHeight);
						double pH = rectHeight/(viewHeight/1);
						
						int numRows = Double.valueOf(Math.ceil(pH)).intValue();
						SwingUtilities.invokeLater(
							new Runnable()
							{
								@Override
								public void run()
								{
									doSetSize(prefixLabel,messageScroll.getWidth(),messageScroll.getHeight());
									doSetSize(suffixLabel,messageScroll.getWidth(),messageScroll.getHeight());
								}
							}
						);
					}
					
					if( viewWidth-dx > rectWidth )
					{
						SwingUtilities.invokeLater(
							new Runnable()
							{
								public void run()
								{
									viewPort.setViewPosition( new Point(Double.valueOf(""+x).intValue()+MSG_RATE,Double.valueOf(""+y).intValue() ) );
								}
							});
					}
					else
					{
						SwingUtilities.invokeLater(
							new Runnable()
							{
								public void run()
								{
									viewPort.setViewPosition( new Point(0,0) );
								}
							});
					}
					Thread.sleep(FPS);
				}
				catch(Exception ie )
				{
					continue;
				}
		}
	}
	
	JComponent getScrollLogo()
	{
		JComponent icon = new JComponent()
		{
			@Override
			public void paintComponent(Graphics g)
			{
				Graphics2D g2 = (Graphics2D)g;
				Image i = LOGO_MIN.getImage();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				g2.drawImage( i, 25,0,25,25,this );
			}
		};
		doSetSize(icon,75,25);
		return icon;
	}
	
	class RotateAnimComponent extends JComponent implements Runnable
	{
		Image image = null;
		int w=100,h = w, rim=18;
		double rad = 0, delta = 0;
		boolean animate = false;
		
		public RotateAnimComponent( ImageIcon LOGO )
		{
			animate = true;
			image = LOGO.getImage();
			repaint();
		}
		
		@Override
		public void paintComponent( Graphics g )
		{
			g = (Graphics2D)g;
			BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2 = (Graphics2D)bi.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g2.translate(w/2,h/2);
				Shape conc = new Arc2D.Double((-w/2),(-h/2),w,h,0,360,Arc2D.CHORD);
			g2.clip(conc);
			
			if(animate)
			{
				delta+=Math.PI/180;
				g2.rotate(delta);
			}
			else
				g2.rotate(0);
			g2.drawImage( image, -w/2,-h/2,w/2,h/2, 0,0,image.getWidth(this), image.getHeight(this),this );
			g2.setPaint(Color.BLACK);
			g2.setStroke(new BasicStroke(2));
			g2.draw( new Arc2D.Double((-w/2)+rim,(-h/2)+rim,w-rim*2,h-rim*2,0,360,Arc2D.CHORD) );
			
			g2.rotate(-delta);
			drawConc(image,g2);
			doSetSize(this,w,h);
			g.drawImage(bi,0,0,w,h,this);
		}
		
		void drawConc( Image i, Graphics2D g2 )
		{
			Shape conc = new Arc2D.Double((-w/2)+rim,(-h/2)+rim,w-rim*2,h-rim*2,0,360,Arc2D.CHORD);
			g2.clip(conc);
			g2.drawImage( image, -w/2,-h/2,w/2,h/2, 0,0,image.getWidth(this), image.getHeight(this),this );
		}
		
		public void setAnimate( boolean animate )
		{
			this.animate = animate;
		}
		
		@Override
		public void run()
		{
			while(true)
				try
				{
					repaint();
					Thread.sleep(FPS);
				}
				catch( InterruptedException ie )
				{
				}
		}
	}
}
