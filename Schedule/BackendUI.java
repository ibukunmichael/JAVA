package ultracode.schedule.lib;

import java.awt.Point;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Formatter;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.net.Socket;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ListSelectionModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
 
import static ultracode.schedule.lib.Constants.*;

public class BackendUI extends JPanel
{
	public final ImageIcon BG = new ImageIcon( getClass().getResource( "/resources/images/img25.jpg") );
	public final ImageIcon COMPANY_LOGO = new ImageIcon( getClass().getResource( "/resources/images/logo.png") );
	public final ImageIcon MFM_LOGO = new ImageIcon( getClass().getResource( "/resources/images/mfmLogo_min.jpg") );
	
	GridBagConstraints appConstraints;
	
	ExecutorService threadExecutor;
	public enum UID
	{ 
		HOME(0), TITLE(5), PREFS(10), RULES(15), ADD_QUESTION(20), REVIEW(25), EDIT_QUESTIONS(35), DELETE_EXISTING_LIST(55), EDIT_EXISTING_LIST(60),NONE(-1);
		private int intValue;
		UID( int intValue )
		{
			this.intValue = intValue;
		}
		
		int intValue()
		{
			return intValue;
		}
	};
	
	final String HTML_PREFIX = "<html>";
	final String HTML_SUFFIX = "</html>";
	
	UIManager.LookAndFeelInfo [] looks;
	JPanel iconPanel;
		JButton minButton;
		JButton exitButton;
		JButton statusButton;
		JButton statusButton2;
		JButton statusButton3;
	JPanel UIPanel;
		JPanel northPanel;
			JLabel logoPanel;
			JPanel captionPanel;
				JLabel ucorpLabel;
				JLabel viewLabel;
		JPanel westPanel;
			JPanel westCenterPanel;
				JPanel deckPanel;
					JPanel deckStatusPanel;
						JRadioButton offRadio;
						JButton incButton, decButton;
					JPanel deckModePanel;
						ButtonGroup modeGroup;
							JRadioButton listRadio;
							JRadioButton idleRadio;
							JRadioButton onRadio;
		JPanel southPanel;
			JPanel commCenterPanel;
				JPanel commPanel;
					JPanel commsPlacementPanel;
						JTextField commField;
						JButton greenButton;
						JButton blueButton;
						JButton redButton;
						JButton incButton2, decButton2;
					public JDialog commDialog;
						JPanel commDialogPanel;
							JScrollPane commScrollPane;
							JPanel commScrollPanel;
								List<JPanel> commPanels;
								List<JTextField> commFields;
							JPanel commOKPanel;
								JButton commOKButton;
								JButton commCancelButton;
						
	
		JPanel eastPanel;
		JPanel eastCenterPanel;
			JPanel selectContainer;
				JPanel selectHeader;
					JButton selectToggleButton;
				JPanel selectLinkContainer;
					JPanel selLinkPanel;
					GroupLayout selectLayout;
						JPanel selectPanel;
							JLabel selectLabel;
							JComboBox selectActiveCombo;
							JButton selectActiveButton;
	
			JPanel createContainer;
				JPanel createHeader;
					JButton createToggleButton;
				JPanel createLinkContainer;
					JPanel createLinkPanel;
					CardLayout createCard;
						JPanel createPrefsPanel;
							JPanel createTitlePanel;
								JLabel createTitleLabel;
								JTextField listTitleField;
							JPanel createScrollPanel;
							JScrollPane createScrollPane;
								List<JPanel> panels;
									List<JLabel> serialLabels;
									List<JButton> closeButtons;
									List<JTextField> nameFields;
									List<JRadioButton> defaultButtons;
									List<JRadioButton> referredButtons;
									List<JRadioButton> deferredButtons;
									List<JRadioButton> nextTimeButtons;
									List<JRadioButton> waitButtons;
									List<JComboBox> refBoxes;
									List<JTextArea> recFields;
									List<JSpinner> daySpins;
									List<JSpinner> monthSpins;
									List<JSpinner> yearSpins;						
							JPanel createPrefsLinkPanel;
								JPanel prefsToolBar;
									JButton addFormButton;
									JSpinner addFormSpin;
									JButton wipeFormButton;
								JPanel prefsCommandPanel;
									JButton prefsBackButton;
									JButton savePrefsListButton;
								
						JPanel createHomePanel;
							JPanel createDescPanel;
								JLabel createDescLabel;
							JPanel nextButtonPanel;
								JButton uploadButton;
								JButton createNextButton;
						
			JPanel manageContainer;
				JPanel manageHeader;
					JButton manageToggleButton;
				JPanel manageLinkContainer;
					JPanel manageLinkPanel;
					CardLayout manageCard;
						JPanel manageHomePanel;
							JPanel manageDescPanel;
							JButton editExistingButton;
							JButton deleteExistingButton;
						JPanel manageEditPanel;
							JPanel editSelectPanel;
							GroupLayout editSelectLayout;
								JLabel editSelectLabel;
								JComboBox editSelectCombo;
							JPanel editSummaryHeaderPanel;
								JLabel editSummaryLabel;
							JPanel editSummaryPanel;
								JList editSummaryList;
								JScrollPane editSummaryScrollPane;
								JPanel editBottomPanel;
								CardLayout editRightCard;
									JPanel editShowPanel;
									GroupLayout editShowLayout;
										JLabel editShowNameLabel, editShowNameLabel2;
										JLabel editShowStatusLabel, editShowStatusLabel2;
										JLabel editShowRefLabel, editShowRefLabel2;
									JPanel editEditPanel;
									GroupLayout editEditLayout;
										JLabel editEditNameLabel;
										JTextField editEditNameField;
										JLabel editEditStatusLabel;
										ButtonGroup editEditButtonGroup;
										ButtonGroup editEditButtonGroup2;
											JRadioButton editEditDefaultRadio;
											JRadioButton editEditReferredRadio;
											JRadioButton editEditDeferredRadio;
											JRadioButton editEditWaitButton;
											JRadioButton editEditNextTimeButton;
										JPanel editEditSpecPanel;
											JLabel editEditDateLabel;
											JLabel editEditDayLabel;
											JSpinner editEditDaySpin;
											JLabel editEditMonthLabel;
											JSpinner editEditMonthSpin;
											JLabel editEditYearLabel;
											JSpinner editEditYearSpin;
										JLabel editEditReceivedLabel;
										JLabel editEditRefereeLabel;
											JComboBox editEditRefCombo;
										JLabel editEditRecLabel;
											JTextField editEditRecField;
									JPanel editDeletePanel;
										JLabel editEditConfirmDeleteLabel;
										JLabel editEditConfirmDeleteLabel2;
										JButton editEditConfirmYesButton;
										JButton editEditConfirmNoButton;
							JPanel editLinkPanel;
								JButton addRoundButton;
								JButton editRoundsButton;
								JButton editTitleButton;
								JButton editBackButton;
						JPanel manageDeletePanel;
							JPanel manageDeleteSelectPanel;
							GroupLayout manageDeleteLayout;
								JLabel manageDeleteSelectLabel;
								JComboBox manageDeleteSelectCombo;
							JButton manageDeleteButton;
							JPanel manageDeleteConfirmPanel;
								JLabel manageDeleteConfirLabel, 
									manageDeleteConfirLabel2;
								JPanel manageConfirmButtonsPanel;
									JButton manageDeleteYesButton;
									JButton manageDeleteNoButton;
							JPanel manageDeleteLinkPanel;
								JButton manageDeleteBackButton;
			JPanel refContainer;
				JPanel refHeader;
				JPanel refLinkContainer;
					Component blankComponent;
					JPanel refLinkHeader;
					JPanel refLinkPanel;
						JButton refsToggleButton;
						JScrollPane refsListScrollPane;
							JList refsList;
						JPanel refsRightPlacementPanel;
							JPanel cardPlacementPanel;
							CardLayout refUICards;
								JPanel cardConfirmPanel;
									JLabel refsConfirmDeleteLabel;
									JLabel refsConfirmDeleteLabel2;
									JButton refsConfirmYesButton;
									JButton refsConfirmNoButton;
								JPanel cardEditPanel;
									JLabel cardEditNameLabel;
									JTextField cardEditNameField;
									JLabel cardEditDesignationLabel;
									JTextField cardEditDesignationField;
									JLabel cardEditContactLabel;
									JTextField cardEditContactField;
									JLabel cardEditAddressLabel;
									JTextField cardEditAddressField;
								JPanel cardPanel;
									JPanel cardHeaderPanel;
										JLabel cardLogoLabel;
										JLabel cardNameLabel;
									JLabel cardDesignationLabel;
									JTextField cardDesignationField;
									JLabel cardContactLabel;
									JLabel cardAddressLabel;
									JTextField cardAddressField;
							JPanel cardCommandPanel;
								JButton refsEditButton;
								JButton refsAddButton;
								JButton refsDeleteButton;	
	
	BackendModel dataModel;
	Timer signalTimer;

	Observer observer;
	ObserverDelegate observerDelegate;
	Mode MODE;
	public BackendUI( Mode mode, BackendModel dataModel, Observer observer )
	{
		this.dataModel = dataModel;
		this.MODE = mode;
		observerDelegate = new ObserverDelegate();
		observerDelegate.addObserver( observer );
		
		signalTimer = new Timer( 750, new SignalHandler() );

		appConstraints = new GridBagConstraints();
		appConstraints.weighty = 0;
		
		setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
			iconPanel = new JPanel();
			iconPanel.setLayout( new BoxLayout(iconPanel, BoxLayout.X_AXIS) );
			iconPanel.setOpaque( false );
				statusButton = new JButton();
				statusButton.setFocusable( false );
				statusButton.setBackground( STATUS_COLOR );
				statusButton2 = new JButton();
				statusButton2.setFocusable( false );
				statusButton2.setBackground( STATUS_COLOR );
				statusButton3 = new JButton();
				statusButton3.setFocusable( false );
				statusButton3.setBackground( STATUS_COLOR );
				minButton = new JButton();
				minButton.setToolTipText("<html><font color=\"blue\">Minimize</font></html>");
				minButton.addActionListener(
					new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent ae )
						{
							publish( Airbag.ICONIFY );
						}
					});
				minButton.setBackground( Color.BLUE );
				
				exitButton = new JButton();
				exitButton.setToolTipText("<html><font color=\"red\">Exit</font></html>");
				exitButton.addActionListener(
					new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent ae )
						{
							publish( Airbag.EXIT );
						}
					});
				exitButton.setBackground( Color.RED );
			iconPanel.add( Box.createHorizontalStrut(10) );
			iconPanel.add( statusButton );
			iconPanel.add( Box.createHorizontalStrut(10) );
			iconPanel.add( statusButton2 );
			iconPanel.add( Box.createHorizontalStrut(10) );
			iconPanel.add( statusButton3 );
			iconPanel.add( Box.createHorizontalGlue() );
			iconPanel.add( minButton );
			iconPanel.add( Box.createHorizontalStrut(10) );
			iconPanel.add( exitButton );
			iconPanel.add( Box.createHorizontalStrut(10) );
			
			UIPanel = new JPanel( new BorderLayout() )
				{
					public void paintComponent( Graphics g )
					{
						super.paintComponent( g );
						// this.setOpaque( false );
						// g.drawImage( COMPANY_LOGO.getImage(), 0,0,55,55, this );
						g.drawImage( BG.getImage(), 0,0,Double.valueOf(this.getSize().getWidth() ).intValue(),Double.valueOf(this.getSize().getHeight() ).intValue(),this );
					}
				};
			doSetSize( UIPanel, UI_WIDTH, UI_HEIGHT );
			UIPanel.setBackground( OVERLAY_COLOR );
			UIPanel.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createRaisedBevelBorder(),BorderFactory.createRaisedBevelBorder() ) );
				northPanel = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
				northPanel.setOpaque( false );
					logoPanel = new JLabel( COMPANY_LOGO );
					captionPanel = new JPanel();
					captionPanel.setOpaque( false );
					captionPanel.setLayout( new BoxLayout( captionPanel, BoxLayout.Y_AXIS ) );
						ucorpLabel = new JLabel( PROGRAM_TITLE );
						ucorpLabel.setForeground( Color.WHITE );
						ucorpLabel.setFont( COMPANY_FONT );
						viewLabel = new JLabel("VIEW_MODULE");
						viewLabel.setForeground( ANCHOR_FG );
						viewLabel.setFont( VIEW_FONT );
					captionPanel.add( Box.createVerticalGlue() );
					captionPanel.add( ucorpLabel );
					captionPanel.add( viewLabel );
				northPanel.add( logoPanel );
				northPanel.add( captionPanel );
				
						refLinkContainer = new JPanel();
						refLinkContainer.setLayout( new BorderLayout() );
						refLinkContainer.setOpaque( false );
							refLinkHeader = new JPanel( new FlowLayout(FlowLayout.RIGHT)){
								public void paintComponent( Graphics g )
								{
									g.setColor( OVERLAY_COLOR );
									// g.fillRect( 12,5,375,50 );
									g.drawImage( COMPANY_LOGO.getImage(), 7,2,25,25, this );
									g.setFont( new Font( "Maiandra GD", Font.BOLD, 15 ) );
									g.setColor( Color.WHITE );
									g.drawString( "Referees Complimentary Cards", 40, 19 );
								}
							};
							refLinkHeader.setOpaque( false );
							doSetSize( refLinkHeader,375,30);
							refLinkHeader.setBackground( OVERLAY_COLOR );
							refLinkHeader.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
							refsToggleButton = new JButton();							
							refsToggleButton.addActionListener( new ToggleButtonListener(0));
								
							refLinkHeader.add(refsToggleButton);
							
							refLinkPanel = new JPanel();
							refLinkPanel.setOpaque( false );
							refLinkPanel.setBackground( LINK_PANEL_BG );
							refLinkPanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) );
							refLinkPanel.setLayout( new BoxLayout(refLinkPanel,BoxLayout.X_AXIS) );
								blankComponent = Box.createRigidArea( refLinkPanel.getSize() );
								doSetSize(blankComponent,375,150 );
								refsList = new JList();
								refsList.addListSelectionListener( 
									new ListSelectionListener()
									{
										public void valueChanged( ListSelectionEvent lse )
										{
											if( ((JList)lse.getSource()).getSelectedIndex() >= 0 )
											{
												int index = ((JList)lse.getSource()).getSelectedIndex();
												
												RefereeCard refCard = getCard(index);
												
												cardNameLabel.setText(refCard.getName());
												cardDesignationLabel.setText(refCard.getDesignation());
												cardContactLabel.setText(refCard.getContact());
												cardAddressLabel.setText(refCard.getAddress());
												
												cardEditNameField.setText(refCard.getName());
												cardEditDesignationField.setText(refCard.getDesignation());
												cardEditContactField.setText(refCard.getContact());
												cardEditAddressField.setText(refCard.getAddress());
											}
										}
									});
								refsList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
								refsList.setOpaque( false );
								refsList.setBackground( Color.WHITE );
								refsList.setForeground( OVERLAY_COLOR );
								refsList.setSelectionBackground( OVERLAY_COLOR);
								refsList.setSelectionForeground( Color.WHITE );
								refsListScrollPane = new JScrollPane( refsList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
								refsListScrollPane.setBorder( BorderFactory.createLineBorder( Color.PINK,0 ) );
								doSetSize(refsListScrollPane,375-250-5,150 );
								refsListScrollPane.getViewport().setOpaque( false );
								refsListScrollPane.setViewportBorder( BorderFactory.createLineBorder(Color.PINK,0) );
								refsRightPlacementPanel = new JPanel();
								refsRightPlacementPanel.setLayout( new BoxLayout( refsRightPlacementPanel, BoxLayout.Y_AXIS ) );
								refsRightPlacementPanel.setOpaque( false );
									cardPlacementPanel = new JPanel();
									refUICards = new CardLayout();
									cardPlacementPanel.setLayout( refUICards );
									cardPlacementPanel.setOpaque( false );
										cardPanel = new JPanel();
										doSetSize(cardPanel,250,110);
										cardPanel.setLayout( new GridLayout(4,1) );
										cardPanel.setOpaque( true );
										cardPanel.setBackground( FIELD_BG );
										cardPanel.setBorder( BorderFactory.createRaisedBevelBorder() );
											cardHeaderPanel = new JPanel();
											cardHeaderPanel.setLayout( new BoxLayout( cardHeaderPanel, BoxLayout.X_AXIS ) );
											cardHeaderPanel.setOpaque( true );
											cardHeaderPanel.setBackground( Color.WHITE );
												cardLogoLabel = new JLabel(MFM_LOGO);
												cardNameLabel = new JLabel("Pastor David Popoola");
											cardHeaderPanel.add( cardLogoLabel );
											cardHeaderPanel.add( Box.createHorizontalGlue() );
											cardHeaderPanel.add( cardNameLabel );
											cardHeaderPanel.add( Box.createHorizontalGlue() );
											cardDesignationLabel = new JLabel("HQ Youth Church, Assembly Pastor");
											cardDesignationLabel.setHorizontalAlignment( JLabel.CENTER );
											cardContactLabel = new JLabel("01-8234353,08023532135");
											cardContactLabel.setHorizontalAlignment( JLabel.CENTER );
											cardAddressLabel = new JLabel("<html>#10 MFM House, Onike, Yaba, Lagos.</html>");
											cardAddressLabel.setHorizontalAlignment( JLabel.CENTER );
										cardPanel.add( cardHeaderPanel);
										cardPanel.add( cardDesignationLabel);
										cardPanel.add( cardContactLabel);
										cardPanel.add( cardAddressLabel);
										
										cardConfirmPanel = new JPanel();
										cardConfirmPanel.setLayout( new GridBagLayout() );
										cardConfirmPanel.setOpaque( false );
											refsConfirmDeleteLabel = new JLabel("Confirm Label");
											refsConfirmDeleteLabel.setHorizontalAlignment(JLabel.CENTER);
											refsConfirmDeleteLabel.setForeground( Color.WHITE );
											refsConfirmDeleteLabel.setFont(LABEL_FONT_1);
											refsConfirmDeleteLabel.setForeground(Color.WHITE);
											refsConfirmDeleteLabel2 = new JLabel("Confirm Delete?");
											refsConfirmDeleteLabel2.setHorizontalAlignment(JLabel.CENTER);
											refsConfirmDeleteLabel2.setForeground(Color.WHITE);
											refsConfirmYesButton = new LinkButton("YES");
											refsConfirmYesButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														deleteCard( refsList.getSelectedIndex() );
														refreshRefsList();
														refUICards.show(cardPlacementPanel,"Card");
														cardCommandPanel.setVisible( true );
													}
												});
											refsConfirmNoButton = new LinkButton("NO");
											refsConfirmNoButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														refUICards.show(cardPlacementPanel,"Card");
														cardCommandPanel.setVisible( true );
													}
												});
										
										appConstraints.fill = GridBagConstraints.HORIZONTAL;
										appConstraints.anchor = GridBagConstraints.CENTER;
										appConstraints.ipady = 10;
										
											appConstraints.weightx = 2;
											appConstraints.gridwidth = GridBagConstraints.REMAINDER;
										addGBComponent( cardConfirmPanel,refsConfirmDeleteLabel,0,0 );
											appConstraints.weightx = 2;
										addGBComponent( cardConfirmPanel,refsConfirmDeleteLabel2,0,1 );
											appConstraints.fill = GridBagConstraints.NONE;
											appConstraints.weightx = 1;
											appConstraints.gridwidth = 1;
										appConstraints.ipady = 0;
										addGBComponent( cardConfirmPanel,refsConfirmNoButton,GridBagConstraints.RELATIVE,2 );
										addGBComponent( cardConfirmPanel,refsConfirmYesButton,GridBagConstraints.RELATIVE,2 );
										
										cardEditPanel = new JPanel();
										cardEditPanel.setLayout( new GridBagLayout() );
										cardEditPanel.setOpaque( false );
										cardEditPanel.setBackground( Color.WHITE );
											cardEditNameLabel = new JLabel(" Name");
											cardEditNameLabel.setForeground( Color.WHITE );
											cardEditNameLabel.setHorizontalAlignment(JLabel.LEFT);
											cardEditNameField = new JTextField();
											
											cardEditDesignationLabel = new JLabel(" Designation");
											cardEditDesignationLabel.setForeground( Color.WHITE );
											cardEditDesignationLabel.setHorizontalAlignment(JLabel.LEFT);											
											cardEditDesignationField = new JTextField();
											
											cardEditContactLabel = new JLabel(" Contact");
											cardEditContactLabel.setForeground( Color.WHITE );
											cardEditContactLabel.setHorizontalAlignment(JLabel.LEFT);
											cardEditContactField = new JTextField();
											
											cardEditAddressLabel = new JLabel(" Address");
											cardEditAddressLabel.setForeground( Color.WHITE );
											cardEditAddressLabel.setHorizontalAlignment(JLabel.LEFT);
											cardEditAddressField = new JTextField();
											
											appConstraints.fill = GridBagConstraints.HORIZONTAL;
											appConstraints.weightx = 1;
										addGBComponent( cardEditPanel, cardEditNameLabel,GridBagConstraints.RELATIVE,0 );
											appConstraints.weightx = 15;
										addGBComponent( cardEditPanel, cardEditNameField,GridBagConstraints.RELATIVE,0 );
											appConstraints.weightx = 1;
										addGBComponent( cardEditPanel, cardEditDesignationLabel,GridBagConstraints.RELATIVE,1 );
											appConstraints.weightx = 15;
										addGBComponent( cardEditPanel, cardEditDesignationField,GridBagConstraints.RELATIVE,1 );
											appConstraints.weightx = 1;
										addGBComponent( cardEditPanel, cardEditAddressLabel,GridBagConstraints.RELATIVE,2 );
											appConstraints.weightx = 15;
										addGBComponent( cardEditPanel, cardEditAddressField,GridBagConstraints.RELATIVE,2 );
											appConstraints.weightx = 1;
										addGBComponent( cardEditPanel, cardEditContactLabel,GridBagConstraints.RELATIVE,3 );
											appConstraints.weightx = 15;
										addGBComponent( cardEditPanel, cardEditContactField,GridBagConstraints.RELATIVE,3 );
									cardPlacementPanel.add( cardPanel,"Card" );
									cardPlacementPanel.add( cardEditPanel,"Edit Card" );
									cardPlacementPanel.add( cardConfirmPanel,"Delete Card" );
									
									cardCommandPanel = new JPanel();
									cardCommandPanel.setLayout( new BoxLayout( cardCommandPanel, BoxLayout.X_AXIS));
									cardCommandPanel.setOpaque( false );
										refsEditButton = new LinkButton("EDIT");
										// doPimpLink(refsEditButton);
										refsEditButton.addActionListener(
											new ActionListener()
											{
												public void actionPerformed( ActionEvent ae )
												{
													if( ((JButton)ae.getSource()).getText().equals("EDIT") )
													{
														if( refsList.getSelectedIndex()>= 0 )
														{
															refUICards.show(cardPlacementPanel,"Edit Card");
															((JButton)ae.getSource()).setText("SAVE");
															refsAddButton.setText("Cancel");
														}
													}
													else
														if( ((JButton)ae.getSource()).getText().equals("SAVE") )
														{
															if( cardEditNameField.getText().trim().length() > 1 && ( cardEditDesignationField.getText().trim().length() > 1 || cardEditContactField.getText().trim().length() > 1 || cardEditAddressField.getText().trim().length() > 1 ) )
															{
																updateCard( refsList.getSelectedIndex(), cardEditNameField.getText().trim(), cardEditDesignationField.getText(), cardEditContactField.getText().trim(), cardEditAddressField.getText().trim() );
																cardEditNameField.setText("");
																cardEditDesignationField.setText("");
																cardEditContactField.setText("");
																cardEditAddressField.setText("");
																
																refsAddButton.setText("ADD");				
																refsEditButton.setText("EDIT");												
																refUICards.show(cardPlacementPanel,"Card");
															}
														}
														else
															if( ((JButton)ae.getSource()).getText().equals("Cancel") )
															{
																refsEditButton.setText("EDIT");																
																refsAddButton.setText("ADD");
																refUICards.show(cardPlacementPanel,"Card");
															}
												}
											});
										refsAddButton = new LinkButton( "ADD");
										// doPimpLink(refsAddButton);
										refsAddButton.addActionListener(
											new ActionListener()
											{
												public void actionPerformed( ActionEvent ae )
												{
													if( ((JButton)ae.getSource()).getText().equals("ADD") )
													{
														cardEditNameField.setText("");
														cardEditDesignationField.setText("");
														cardEditContactField.setText("");
														cardEditAddressField.setText("");
														refUICards.show(cardPlacementPanel,"Edit Card");
														refsAddButton.setText("SAVE");
														
														refsEditButton.setText("Cancel");
													}
													else
														if( ((JButton)ae.getSource()).getText().equals("SAVE") )
														{
															if( cardEditNameField.getText().trim().length() > 1 && ( cardEditDesignationField.getText().trim().length() > 1 || cardEditContactField.getText().trim().length() > 1 || cardEditAddressField.getText().trim().length() > 1 ) )
															{
																addCard( cardEditNameField.getText().trim(), cardEditDesignationField.getText(), cardEditContactField.getText().trim(), cardEditAddressField.getText().trim() );
																cardEditNameField.setText("");
																cardEditDesignationField.setText("");
																cardEditContactField.setText("");
																cardEditAddressField.setText("");
																refUICards.show(cardPlacementPanel,"Card");
																refsAddButton.setText("ADD");
																refsEditButton.setText("EDIT");
															}
														}
														else
															if( ((JButton)ae.getSource()).getText().equals("Cancel") )
															{
																refUICards.show(cardPlacementPanel,"Card");
																
																refsAddButton.setText("ADD");																
																refsEditButton.setText("EDIT");
															}
												}
											});
										refsDeleteButton = new LinkButton("DELETE");
										refsDeleteButton.addActionListener(
											new ActionListener()
											{
												public void actionPerformed( ActionEvent ae )
												{
													if( ae.getActionCommand().equals("DELETE") )
													{
														if( refsList.getSelectedIndex() >= 0 )
														{
															refsConfirmDeleteLabel.setText("<html><p style=\"text-align:center\">Delete<br>"+getRefName(refsList.getSelectedIndex())+"</p></html>" );
															cardCommandPanel.setVisible( false );
															refUICards.show(cardPlacementPanel,"Delete Card");
														}
													}
												}
											});
										
									cardCommandPanel.add( Box.createHorizontalGlue() );
									cardCommandPanel.add( refsEditButton );
									cardCommandPanel.add( refsAddButton );
									cardCommandPanel.add( refsDeleteButton );
									cardCommandPanel.add( Box.createHorizontalGlue() );
								refsRightPlacementPanel.add( cardPlacementPanel );
								refsRightPlacementPanel.add( cardCommandPanel );
								refsRightPlacementPanel.add( Box.createRigidArea(new Dimension(0,5)) );
							refLinkPanel.add( refsListScrollPane );
							refLinkPanel.add( Box.createRigidArea(new Dimension(2,0)) );
							refLinkPanel.add( refsRightPlacementPanel );
							refLinkPanel.setVisible( false );
					refLinkContainer.add( refLinkHeader,BorderLayout.NORTH );
					refLinkContainer.add( refLinkPanel,BorderLayout.CENTER );
					refLinkContainer.add( blankComponent,BorderLayout.CENTER );
						doSetSize(refLinkPanel,375,150 );
					refLinkContainer.setMaximumSize( new Dimension(375,150));
					
				westPanel = new JPanel();
				westPanel.setOpaque( false );
				westPanel.setLayout( new BoxLayout( westPanel, BoxLayout.X_AXIS ) );
				doSetSize( westPanel, 405,20 );
					westCenterPanel = new JPanel();
					westCenterPanel.setOpaque( false );
					westCenterPanel.setBackground( Color.CYAN );
					westCenterPanel.setLayout( new BoxLayout( westCenterPanel, BoxLayout.Y_AXIS ) );
						deckPanel = new JPanel();
						deckPanel.setLayout( new BoxLayout( deckPanel, BoxLayout.Y_AXIS ) );
						deckPanel.setOpaque( false );
						deckPanel.setBackground( OVERLAY_COLOR );
						deckPanel.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createRaisedBevelBorder(),BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) ) );
							deckStatusPanel = new JPanel();
							deckStatusPanel.setLayout( new BoxLayout(deckStatusPanel,BoxLayout.X_AXIS) );
							deckStatusPanel.setBackground( OVERLAY_COLOR );
							deckStatusPanel.setOpaque( false );
								decButton = new LinkButton("-M");
								decButton.addActionListener(
									new ActionListener()
									{
										public void actionPerformed(ActionEvent ae)
										{
											changeListSpeed(-1);
										}
									});
								incButton = new LinkButton("+M");
								incButton.addActionListener(
									new ActionListener()
									{
										public void actionPerformed(ActionEvent ae)
										{
											changeListSpeed(1);
										}
									});
							deckModePanel = new JPanel();
							deckModePanel.setLayout( new BoxLayout( deckModePanel, BoxLayout.X_AXIS ) );
							deckModePanel.setOpaque( false );
							deckModePanel.setBackground( OVERLAY_COLOR );
								modeGroup = new ButtonGroup();
									listRadio = new JRadioButton("<html><font color=\"#0000ff\">LIST MODE</font></html>");
									listRadio.addItemListener(
										new ItemListener()
										{
											@Override
											public void itemStateChanged( ItemEvent ie )
											{
												if( ie.getStateChange() == ItemEvent.SELECTED )
												{
													publish( Mode.LIST );
												}
											}
										});
									doPimpMiniLabel(listRadio);
									idleRadio = new JRadioButton("<html><font color=\"yellow\">IDLE MODE</font></html>");
									idleRadio.addItemListener(
										new ItemListener()
										{
											@Override
											public void itemStateChanged( ItemEvent ie )
											{
												if( ie.getStateChange() == ItemEvent.SELECTED )
												{
													publish( Mode.IDLE );
												}
											}
										});
									doPimpMiniLabel(idleRadio);
									onRadio = new JRadioButton("<html><font color=\"#00ff00\">LIVE!</font></html>");
									onRadio.addItemListener(
										new ItemListener()
										{
											@Override
											public void itemStateChanged( ItemEvent ie )
											{
												if( ie.getStateChange() == ItemEvent.SELECTED )
												{
													publish( Mode.ON );
												}
											}
										});
									doPimpMiniLabel(onRadio);
									offRadio = new JRadioButton("<html><font color=\"red\">OFF</font></html>");
									offRadio.setSelected( true );
									offRadio.addItemListener(
										new ItemListener()
										{
											@Override
											public void itemStateChanged( ItemEvent ie )
											{
												if( ie.getStateChange() == ItemEvent.SELECTED )
												{
													publish( Mode.OFF );
												}
											}
										});
									doPimpMiniLabel(offRadio);
								modeGroup.add( listRadio );
								modeGroup.add( idleRadio );
								modeGroup.add( onRadio );
								modeGroup.add( offRadio );
								listRadio.setEnabled(false);
							deckModePanel.add( Box.createRigidArea(new Dimension(5,0)) );
							deckModePanel.add( listRadio );
							deckModePanel.add( Box.createHorizontalGlue() );
							if( MODE.equals(Mode.REAL) )
							{
								deckModePanel.add( idleRadio );
								deckModePanel.add( Box.createHorizontalGlue() );
							}
							deckModePanel.add( onRadio );
							deckModePanel.add( Box.createRigidArea(new Dimension(5,0)) );
							
							deckStatusPanel.add( Box.createRigidArea(new Dimension(5,0)) );
							deckStatusPanel.add( offRadio );							
							deckStatusPanel.add(Box.createHorizontalGlue() );
							deckStatusPanel.add(decButton);
							deckStatusPanel.add(Box.createRigidArea(new Dimension(5,0)));
							deckStatusPanel.add(incButton);
						deckPanel.add( Box.createRigidArea(new Dimension(0,5) ) );
						deckPanel.add( deckStatusPanel );
						deckPanel.add( Box.createRigidArea(new Dimension(0,10) ) );
						deckPanel.add( deckModePanel );
						deckPanel.add( Box.createRigidArea(new Dimension(0,5) ) );
						
					westCenterPanel.add( Box.createVerticalGlue() );
					westCenterPanel.add( deckPanel );
						westCenterPanel.add( Box.createVerticalStrut(68) );
					if( MODE.equals(Mode.OTHERS) )
						westCenterPanel.add( refLinkContainer );
					else
						westCenterPanel.add( Box.createVerticalGlue() );
					westCenterPanel.add( Box.createVerticalStrut( CONTAINER_GAP ) );
				westPanel.add( Box.createHorizontalStrut( CONTAINER_GAP ) );
				westPanel.add( westCenterPanel );

				southPanel = new JPanel();
				southPanel.setLayout( new BoxLayout( southPanel, BoxLayout.X_AXIS ) );
				southPanel.setOpaque( false );
					commDialog = new JDialog((JFrame)null,true);
					commDialog.setUndecorated(true);
						commDialogPanel = new JPanel();
						commDialogPanel.setOpaque(false);
						commDialogPanel.setLayout(new BorderLayout());
						doSetSize(commDialogPanel,390,150);
							commScrollPanel = new JPanel();
							commScrollPanel.setOpaque(false);
							commScrollPanel.setLayout(new BoxLayout(commScrollPanel,BoxLayout.Y_AXIS));
								commPanels = new ArrayList<JPanel>();
								commFields = new ArrayList<JTextField>();
							commScrollPane = new JScrollPane(commScrollPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
							commOKPanel = new JPanel();
							commOKPanel.setBackground(OVERLAY_COLOR);
							commOKPanel.setLayout(new BoxLayout(commOKPanel,BoxLayout.X_AXIS));
								commOKButton = new LinkButton("OK");
								commOKButton.addActionListener(
									new ActionListener()
									{
										@Override
										public void actionPerformed(ActionEvent ae)
										{
											clearMessages();
											for( int i=0; i<commPanels.size(); i++ )
											{
												if( commPanels.get(i).isVisible() )
												{
													if( commFields.get(i).getText().trim().length() > 1 )
														appendMessage(commFields.get(i).getText().trim() );
													else
														continue;
												}
												else
													continue;
											}
											commDialog.setVisible(false);
										}
									});
								commCancelButton = new LinkButton("CANCEL");
								commCancelButton.addActionListener(
									new ActionListener()
									{
										@Override
										public void actionPerformed(ActionEvent ae)
										{
											commDialog.setVisible(false);
										}
									});
							commOKPanel.add(Box.createHorizontalGlue());
							commOKPanel.add(commCancelButton);
							commOKPanel.add(commOKButton);
							commOKPanel.add(Box.createHorizontalGlue());
						commDialogPanel.add(commScrollPane,BorderLayout.CENTER);
						commDialogPanel.add(commOKPanel,BorderLayout.SOUTH);
					commDialog.setContentPane(commDialogPanel);
					commCenterPanel = new JPanel();
					commCenterPanel.setLayout( new BoxLayout( commCenterPanel, BoxLayout.Y_AXIS ) );
					commCenterPanel.setOpaque( false );				
						commPanel = new JPanel();
						commPanel.setLayout( new BoxLayout( commPanel, BoxLayout.Y_AXIS ) );
						commPanel.setOpaque( false );
						commPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder( OVERLAY_COLOR,1 ), "COMM. ALERT", TitledBorder.LEFT, TitledBorder.TOP, new Font( "Maiandra GD", Font.BOLD, 10 ), Color.WHITE ) );
							commsPlacementPanel = new JPanel();
							commsPlacementPanel.setBackground( OVERLAY_COLOR );
							commsPlacementPanel.setOpaque( false );
							commsPlacementPanel.setLayout( new BoxLayout( commsPlacementPanel, BoxLayout.X_AXIS ) );
								commField = new JTextField();
								commField.setMaximumSize( new Dimension( 1000, 25 ) );
								commField.addActionListener( 
									new ActionListener()
									{
										@Override
										public void actionPerformed(ActionEvent ae )
										{
											if( commField.getText().trim().length() > 0 )
												appendMessage(commField.getText().trim());
											commField.setText("");
										}
									});
								commField.setFont( LABEL_FONT );
								greenButton = new JButton();
								greenButton.setToolTipText("<html><font color=\"green\">Append Message</font></html>");
								greenButton.addActionListener(
									new ActionListener()
									{
										public void actionPerformed( ActionEvent ae )
										{
											if( commField.getText().trim().length() > 0 )
												appendMessage(commField.getText().trim());
											commField.setText("");
										}
									});
								greenButton.setBackground( Color.GREEN );
								blueButton = new JButton();
								blueButton.setToolTipText("<html><font color=\"blue\">Edit Messages</font></html>");
								blueButton.addActionListener(
									new ActionListener()
									{
										public void actionPerformed( ActionEvent ae )
										{
											printCommForms(getMessages(),commScrollPanel);
											commDialog.pack();
											double x = (UIPanel.getX()+UIPanel.getWidth())-commDialog.getWidth()-CONTAINER_GAP-5;
											double y = southPanel.getY()-commDialog.getHeight()+CONTAINER_GAP*3;
											commDialog.setLocation(Double.valueOf(x).intValue(),Double.valueOf(y).intValue());
											commDialog.setVisible(true);
										}
									});
								blueButton.setBackground( Color.BLUE );
								redButton = new JButton();
								redButton.setToolTipText("<html><font color=\"red\">Close Alert</font></html>");
								redButton.addActionListener(
									new ActionListener()
									{
										public void actionPerformed( ActionEvent ae )
										{
											publish(Airbag.TOGGLE_ALERT);
										}
									});
								redButton.setBackground( Color.RED );
								
								decButton2 = new LinkButton("-M");
								decButton2.addActionListener(
									new ActionListener()
									{
										public void actionPerformed(ActionEvent ae)
										{
											changeMessageSpeed(-1);
										}
									});
								incButton2 = new LinkButton("+M");
								incButton2.addActionListener(
									new ActionListener()
									{
										public void actionPerformed(ActionEvent ae)
										{
											changeMessageSpeed(1);
										}
									});
							commsPlacementPanel.add( Box.createRigidArea(new Dimension(5,0)) );
							commsPlacementPanel.add( commField );
							commsPlacementPanel.add( Box.createHorizontalStrut( 15 ) );
							commsPlacementPanel.add( greenButton );
							commsPlacementPanel.add( Box.createHorizontalStrut( 15 ) );
							commsPlacementPanel.add( blueButton );
							commsPlacementPanel.add( Box.createHorizontalStrut( 15 ) );
							commsPlacementPanel.add( redButton );
							commsPlacementPanel.add( Box.createHorizontalStrut( 25 ) );
							commsPlacementPanel.add( decButton2 );
							commsPlacementPanel.add( Box.createRigidArea(new Dimension(5,0)) );
							commsPlacementPanel.add( incButton2 );
							commsPlacementPanel.add( Box.createRigidArea(new Dimension(5,0)) );
						commPanel.add( commsPlacementPanel );
						commPanel.add( Box.createVerticalStrut( 5 ) );
					commCenterPanel.add( commPanel );
					commCenterPanel.add( Box.createVerticalStrut(CONTAINER_GAP) );
				southPanel.add( Box.createHorizontalStrut( CONTAINER_GAP ) );
				southPanel.add( commCenterPanel );
				southPanel.add( Box.createHorizontalStrut( CONTAINER_GAP ) );
				
				eastPanel = new JPanel();
				eastPanel.setOpaque( false );
				eastPanel.setLayout( new BoxLayout( eastPanel, BoxLayout.X_AXIS ) );
					eastCenterPanel = new JPanel();
					eastCenterPanel.setLayout( new BoxLayout( eastCenterPanel, BoxLayout.Y_AXIS ) );
					eastCenterPanel.setOpaque( false );
						selectContainer = new JPanel();
						selectContainer.setLayout( new BoxLayout( selectContainer, BoxLayout.Y_AXIS ) );
						selectContainer.setOpaque( false );
							selectHeader = new HeaderPanel("Select Active List");
							selectHeader.setLayout( new GridBagLayout() );
							doSetSize(selectHeader,405,70);
								selectToggleButton = new JButton();
								selectToggleButton.addActionListener( new ToggleButtonListener(1) );
								appConstraints.fill = GridBagConstraints.NONE;
								appConstraints.anchor = GridBagConstraints.LINE_END;
								appConstraints.gridwidth = GridBagConstraints.REMAINDER;
								appConstraints.weightx = 1;
								appConstraints.weighty = 1;
								appConstraints.ipady = 0;
							addGBComponent(selectHeader,selectToggleButton,0,0);
							selectLinkContainer = new JPanel();
							selectLinkContainer.setLayout( new BorderLayout() );
							selectLinkContainer.setOpaque( false );
								selLinkPanel = new JPanel();
								selLinkPanel.setBackground( LINK_PANEL_BG );
								selLinkPanel.setOpaque( false );
								selLinkPanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) );
								selLinkPanel.setLayout( new BoxLayout( selLinkPanel, BoxLayout.Y_AXIS ) );
									selectPanel = new JPanel();
									selectPanel.setOpaque( false );
									selectLayout = new GroupLayout(selectPanel);
									selectPanel.setLayout(selectLayout);
										selectLabel = new JLabel( "Select List:" );
										doPimpMiniLabel( selectLabel );
									selectLayout.setAutoCreateGaps(false);
									selectLayout.setAutoCreateContainerGaps(false);
									
										selectActiveButton = new LinkButton( "LOCK IN" );
										selectActiveCombo = new JComboBox( getAbridgedListNames() );
										selectActiveCombo.addItemListener(
											new ItemListener()
											{
												public void itemStateChanged( ItemEvent ie )
												{
													if( ie.getStateChange() == ItemEvent.SELECTED )
														if( ((JComboBox)ie.getSource()).getSelectedIndex() > 0 && getListApplicationNames(((JComboBox)ie.getSource()).getSelectedIndex()-1).length>0)
														{
															selectActiveButton.setVisible( true );
														}
														else
														{
															selectActiveButton.setVisible( false );
															if(!selectActiveCombo.isEnabled())
																selectActiveCombo.setEnabled(true);
														}
												}
											});
										doSetSize(selectActiveCombo,350,25);
										selectActiveButton.addActionListener(
											new ActionListener()
											{
												public void actionPerformed( ActionEvent ae )
												{
													if( ae.getActionCommand().equals("LOCK IN") )
													{	
														selectActiveCombo.setEnabled( false );														
														selectActiveButton.setText( "RELEASE" );
														selectActiveButton.setActionCommand( "RELEASE" );
														setActiveList( selectActiveCombo.getSelectedIndex()-1 );
														listRadio.setEnabled(true);
														if( listRadio.isSelected() )
															publish(Mode.LIST);
													}
													else
													{
														selectActiveButton.setText( "LOCK IN" );
														selectActiveButton.setActionCommand( "LOCK IN" );
														selectActiveCombo.setEnabled( true );
														listRadio.setEnabled(false);
														
														if( MODE.equals(Mode.REAL) )
														{
															if(!idleRadio.isSelected())
																idleRadio.setSelected(true);
														}
														else
															if(!onRadio.isSelected())
																onRadio.setSelected(true);
													}
												}
											});
									selectActiveButton.setVisible( false );
									
									selectLayout.setHorizontalGroup(
										selectLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
											.addComponent(selectLabel)
											.addComponent(selectActiveCombo)
											.addGroup(selectLayout.createSequentialGroup()
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
												.addComponent(selectActiveButton)));
									selectLayout.setVerticalGroup(
										selectLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(selectLabel)
											.addComponent(selectActiveCombo)
											.addComponent(selectActiveButton)
											.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED));
									
								selLinkPanel.add( selectPanel );
								selLinkPanel.add( Box.createVerticalGlue() );
								doSetSize(selLinkPanel,390,90 );	
							doSetSize(selectLinkContainer,405,90 );
						
						createContainer = new JPanel();
						createContainer.setLayout( new BoxLayout( createContainer, BoxLayout.Y_AXIS ) );
						createContainer.setOpaque( false );
							createHeader = new HeaderPanel("Create New List");
							doSetSize( createHeader,405,70 );
							createHeader.setLayout( new GridBagLayout() );
								createToggleButton = new JButton();
								createToggleButton.addActionListener( new ToggleButtonListener(2) );
								appConstraints.fill = GridBagConstraints.NONE;
								appConstraints.anchor = GridBagConstraints.LINE_END;
								appConstraints.gridwidth = GridBagConstraints.REMAINDER;
								appConstraints.weightx = 1;
								appConstraints.weighty = 1;
								appConstraints.ipady = 0;
							addGBComponent(createHeader,createToggleButton,0,0);
							createLinkContainer = new JPanel();
							createLinkContainer.setLayout( new BorderLayout() );
							createLinkContainer.setOpaque( false );
								createLinkPanel = new JPanel();						
								createLinkPanel.setBackground( LINK_PANEL_BG );
								createLinkPanel.setOpaque( false );
								createLinkPanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) );
								createLinkPanel.setLayout( createCard = new CardLayout() );									
									
									createHomePanel = new JPanel();						
									createHomePanel.setBackground( LINK_PANEL_BG );
									createHomePanel.setOpaque( false );
									createHomePanel.setLayout( new BorderLayout() );
										createDescPanel = new JPanel();
										createDescPanel.setOpaque( false );
										createDescPanel.setLayout( new BoxLayout( createDescPanel, BoxLayout.X_AXIS ) );
											createDescLabel = new JLabel("<html>Each list contains the names of Applicants with the current status of each application.<font color=\"#ccff99\">To continue, click next</font></html>" );
											createDescLabel.setForeground( Color.WHITE );
										createDescPanel.add( Box.createHorizontalStrut(CONTAINER_GAP*2) );
										createDescPanel.add( createDescLabel );
										nextButtonPanel = new JPanel();
										nextButtonPanel.setOpaque( false );
										nextButtonPanel.setLayout( new BoxLayout(nextButtonPanel,BoxLayout.X_AXIS) );
											uploadButton = new LinkButton( "Upload from File ^" );
											uploadButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae  )
													{
														/* JFileChooser fileChooser = new JFileChooser();
														fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
														fileChooser.setFileFilter( new FileNameExtensionFilter("*.txt","txt") );
														
														int result = fileChooser.showOpenDialog( BackendUI.this );
														
														if( result == JFileChooser.CANCEL_OPTION )
															return;
														
														doParseFile( fileChooser.getSelectedFile() ); */
														
													}
												});
											createNextButton = new LinkButton( "next >>" );
											createNextButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae  )
													{
														doUI(UID.PREFS);
													}
												});
										nextButtonPanel.add( Box.createHorizontalGlue() );
										nextButtonPanel.add( createNextButton );
									createHomePanel.add( createDescPanel, BorderLayout.CENTER );
									createHomePanel.add( nextButtonPanel, BorderLayout.SOUTH );
									
									createPrefsPanel = new JPanel();						
									createPrefsPanel.setBackground( LINK_PANEL_BG );
									createPrefsPanel.setOpaque( false );
									createPrefsPanel.setLayout( new BorderLayout() );
										
										createTitlePanel = new JPanel();						
										createTitlePanel.setBackground( LINK_PANEL_BG );
										createTitlePanel.setOpaque( false );
										createTitlePanel.setLayout( new BoxLayout( createTitlePanel, BoxLayout.X_AXIS ) );
											createTitleLabel = new JLabel( "List title:" );
											createTitleLabel.setForeground( Color.WHITE );
											createTitleLabel.setFont( LABEL_FONT_1 );
											listTitleField = new JTextField();
											listTitleField.setBackground( Color.WHITE );
											doSetSize( listTitleField, 300, 30 );
											listTitleField.setFont( LABEL_FONT_1 );
											listTitleField.setForeground( Color.BLACK );
											listTitleField.setHorizontalAlignment( JTextField.CENTER );
											listTitleField.addKeyListener(
												new KeyAdapter()
												{
													public void keyReleased( KeyEvent ke )
													{
														if( listTitleField.getText().trim().length() > 0 )
														{
															savePrefsListButton.setVisible( true );				
														}
														else
														{
															listTitleField.setText("");
															savePrefsListButton.setVisible( false );
														}
													}
												});
										
										createTitlePanel.add( Box.createHorizontalGlue() );
										createTitlePanel.add( createTitleLabel );
										createTitlePanel.add( Box.createHorizontalStrut(2) );
										createTitlePanel.add( listTitleField );
										createTitlePanel.add( Box.createHorizontalGlue() );
										
										createScrollPanel = new JPanel();
										createScrollPanel.setOpaque( false );
										createScrollPanel.setLayout( new BoxLayout(createScrollPanel,BoxLayout.Y_AXIS) );
											panels = new ArrayList<JPanel>();
											serialLabels = new ArrayList<JLabel>();
											closeButtons = new ArrayList<JButton>();
											nameFields = new ArrayList<JTextField>();
											defaultButtons = new ArrayList<JRadioButton>();
											referredButtons = new ArrayList<JRadioButton>();
											deferredButtons = new ArrayList<JRadioButton>();
											refBoxes = new ArrayList<JComboBox>();
											recFields = new ArrayList<JTextArea>();
											daySpins = new ArrayList<JSpinner>();
											monthSpins = new ArrayList<JSpinner>();
											yearSpins = new ArrayList<JSpinner>();
											nextTimeButtons = new ArrayList<JRadioButton>();
											waitButtons = new ArrayList<JRadioButton>();
										
										printApplicationForm(7, createScrollPanel);
										
										createScrollPane = new JScrollPane( createScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
										createScrollPane.setBorder( BorderFactory.createLineBorder(Color.PINK,0) );
										createScrollPane.getViewport().setOpaque( false );
										
										createPrefsLinkPanel = new JPanel();
										createPrefsLinkPanel.setLayout( new BoxLayout( createPrefsLinkPanel, BoxLayout.Y_AXIS ) );
										createPrefsLinkPanel.setOpaque( false );								
											prefsToolBar = new JPanel();
											prefsToolBar.setLayout( new BoxLayout( prefsToolBar, BoxLayout.X_AXIS ) );
											prefsToolBar.setOpaque( false );			
												addFormButton = new JButton();
												addFormButton.setBackground( Color.GREEN );
												addFormButton.addActionListener(
													new ActionListener()
													{
														@Override
														public void actionPerformed( ActionEvent ae )
														{
															if( (addFormSpin.getValue().toString().matches("\\d+")) && Integer.parseInt(""+addFormSpin.getValue()) > 0 )
															{
																printApplicationForm( Integer.parseInt(""+addFormSpin.getValue()),createScrollPanel );
															}
														}
													});
												addFormSpin = new JSpinner();
												addFormSpin.addChangeListener(
													new ChangeListener()
													{
														@Override
														public void stateChanged( ChangeEvent ce )
														{
															if( (Integer)((JSpinner)ce.getSource()).getValue() <= 0 )
																((JSpinner)ce.getSource()).setValue(1);
														}
													});
												addFormSpin.addKeyListener(
													new KeyAdapter()
													{
														@Override
														public void keyPressed( KeyEvent ke )
														{
															if( ke.getKeyCode() == KeyEvent.VK_ENTER )
																if( (addFormSpin.getValue().toString().matches("\\d+")) && Integer.parseInt(""+addFormSpin.getValue()) > 0 )
																{
																	// printApplicationForm( Integer.parseInt(""+addFormSpin.getValue()),createScrollPanel );
																}
														}
													});
												addFormSpin.setValue(7);
												doSetSize( addFormSpin, 55, 25 );
												wipeFormButton = new JButton();
												wipeFormButton.setBackground( Color.RED );
												wipeFormButton.addMouseListener(
													new MouseAdapter()
													{
														@Override
														public void mouseClicked( MouseEvent me )
														{
															if( me.getClickCount() == 2 )
															{
																clearApplicationForm( createScrollPanel );
																createScrollPane.validate();
															}
														}
													});
											prefsToolBar.add( Box.createHorizontalGlue() );
											prefsToolBar.add( addFormSpin );
											prefsToolBar.add( addFormButton );
											prefsToolBar.add( Box.createHorizontalGlue() );
											prefsToolBar.add( wipeFormButton );
											prefsToolBar.add( Box.createHorizontalGlue() );
											
											prefsCommandPanel = new JPanel();
											prefsCommandPanel.setLayout( new BoxLayout( prefsCommandPanel, BoxLayout.X_AXIS ) );
											prefsCommandPanel.setOpaque( false );								
												prefsBackButton = new LinkButton( "<< back" );
												prefsBackButton.addActionListener(
													new ActionListener()
													{
														public void actionPerformed( ActionEvent ae )
														{
															doUI( UID.HOME );
														}
													});
												savePrefsListButton = new LinkButton(  "<<Save List>>" );
												savePrefsListButton.addActionListener(
													new ActionListener()
													{
														public void actionPerformed( ActionEvent ae )
														{
															int currentI = 0;
															if( listTitleField.getText().trim().length() > 1 )
															{
																listTitleField.setBackground( Color.WHITE );
																doCreateNewList( listTitleField.getText().trim() );
																boolean isValid = true;																
																
																for( int i=0; i<panels.size(); i++ )
																{
																	currentI = i;
																	if( panels.get(i).isVisible() )
																	{
																		if( nameFields.get(i).getText().trim().length() > 0 )
																		{
																			nameFields.get(i).setBackground( Color.WHITE );
																			Application app = new Application(nameFields.get(i).getText().trim());
																			
																			switch( MODE )
																			{
																				case GO:
																				{
																					app.setAppointmentDate( new GregorianCalendar((Integer)yearSpins.get(i).getValue(),(Integer)monthSpins.get(i).getValue()-1,(Integer)daySpins.get(i).getValue()) );
																				}break;
																				case OTHERS:
																				{
																					if( defaultButtons.get(i).isSelected() )
																					{
																						app.setStatus(Application.Status.DEFAULT);
																					}
																					else
																					if( referredButtons.get(i).isSelected() )
																					{
																						app.setStatus(Application.Status.REFERRED);
																						app.setRefereeCard( getCard(refBoxes.get(i).getSelectedIndex()) );
																					}
																					else
																					if( deferredButtons.get(i).isSelected() )
																					{
																						recFields.get(i).setBackground( Color.WHITE );
																						app.setStatus(Application.Status.DEFERRED);
																						if( recFields.get(i).getText().trim().length() > 0 )
																							app.setRecommendation( recFields.get(i).getText().trim() );
																						else
																						{
																							isValid = false;
																							recFields.get(i).setBackground( Color.YELLOW );
																							break;
																						}
																					}																				
																				}break;
																				case REAL:
																				{
																					waitButtons.get(i).setForeground(Color.BLACK);
																					nextTimeButtons.get(i).setForeground( Color.BLACK);
																					
																					if( nextTimeButtons.get(i).isSelected() )
																						app.setStatus( Application.Status.REAL_NEXT_TIME );
																					else
																					if( waitButtons.get(i).isSelected() )
																						app.setStatus( Application.Status.REAL_WAIT );
																					else
																					{
																						isValid = false;
																						waitButtons.get(i).setForeground(Color.RED);
																						nextTimeButtons.get(i).setForeground(Color.RED);
																						break;
																					}
																				}break;
																			}
																			if(!isValid)
																			break;	//break didn't apply in switch
																			addApplication(app);
																		}
																		else
																		{
																			isValid = false;
																			nameFields.get(i).setBackground( Color.YELLOW );
																			break;																			
																		}
																	}
																	else
																		continue;
																}
																
																if( isValid )
																{
																	commitList();
																	listTitleField.setText("");
																	clearApplicationForm( createScrollPanel );
																	printApplicationForm((Integer)addFormSpin.getValue(),createScrollPanel);
																	doUI(UID.HOME);
																}
																else
																{
																	// ////////////System.out.println("!!!list not committed::"+nameFields.get(currentI).getText());
																	int x = panels.get(currentI).getX();
																	int y = panels.get(currentI).getY();
																	createScrollPanel.setVisible(false);
																	createScrollPane.getViewport().setViewPosition( new Point(x,y) );
																	createScrollPanel.setVisible(true);
																}
															}
															else
																listTitleField.setBackground( Color.YELLOW );
														}
													});	
											prefsCommandPanel.add( prefsBackButton );
											prefsCommandPanel.add( Box.createHorizontalGlue() );
											prefsCommandPanel.add( savePrefsListButton );
										createPrefsLinkPanel.add( Box.createVerticalGlue() );
										createPrefsLinkPanel.add( prefsToolBar );
										createPrefsLinkPanel.add( prefsCommandPanel );
									createPrefsPanel.add( createTitlePanel, BorderLayout.NORTH );
									createPrefsPanel.add( createScrollPane, BorderLayout.CENTER );
									createPrefsPanel.add( createPrefsLinkPanel, BorderLayout.SOUTH );
									
								createLinkPanel.add( createHomePanel, "HOME" );
								createLinkPanel.add( createPrefsPanel, "PREFS" );
								doSetSize(createLinkPanel,390,90);
							doSetSize(createLinkContainer,405,90);
						
						manageContainer = new JPanel();
						manageContainer.setLayout( new BoxLayout( manageContainer, BoxLayout.Y_AXIS ) );
						manageContainer.setOpaque( false );
							manageHeader = new HeaderPanel("Manage Existing List");
							doSetSize(manageHeader,405,70);
							manageHeader.setLayout( new GridBagLayout() );
								manageToggleButton = new JButton();
								manageToggleButton.addActionListener( new ToggleButtonListener(3) );
								appConstraints.fill = GridBagConstraints.NONE;
								appConstraints.anchor = GridBagConstraints.LINE_END;
								appConstraints.gridwidth = GridBagConstraints.REMAINDER;
								appConstraints.weightx = 1;
								appConstraints.weighty = 1;
								appConstraints.ipady = 0;
							addGBComponent(manageHeader,manageToggleButton,0,0);
							manageLinkContainer = new JPanel();
							manageLinkContainer.setLayout( new BorderLayout() );
							manageLinkContainer.setOpaque( false );
								manageLinkPanel = new JPanel();
								manageLinkPanel.setBackground( LINK_PANEL_BG );
								manageLinkPanel.setOpaque( false );
								manageLinkPanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) );
								manageLinkPanel.setLayout( manageCard = new CardLayout() );
									manageHomePanel = new JPanel();
									manageHomePanel.setOpaque( false );
									manageHomePanel.setLayout( new BoxLayout( manageHomePanel, BoxLayout.X_AXIS ) );
										manageDescPanel = new JPanel();
										manageDescPanel.setOpaque( false );
										manageDescPanel.setLayout( new BoxLayout( manageDescPanel, BoxLayout.Y_AXIS ) );
											editExistingButton = new LinkButton( "Edit Existing List" );
											editExistingButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														doUI( UID.EDIT_EXISTING_LIST );
													}
												});
											deleteExistingButton = new LinkButton( "Delete Existing List" );
											deleteExistingButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														doUI( UID.DELETE_EXISTING_LIST );
													}
												});
										manageDescPanel.add( editExistingButton );
										manageDescPanel.add( deleteExistingButton );
									manageHomePanel.add( Box.createHorizontalStrut(CONTAINER_GAP+10) );
									manageHomePanel.add( manageDescPanel );
									manageHomePanel.add( Box.createHorizontalGlue() );
									
									manageEditPanel = new JPanel();						
									manageEditPanel.setBackground( LINK_PANEL_BG );
									manageEditPanel.setLayout( new BoxLayout( manageEditPanel, BoxLayout.Y_AXIS ) );
									manageEditPanel.setOpaque( false );
										editSelectPanel = new JPanel();
										editSelectLayout = new GroupLayout(editSelectPanel);
										editSelectPanel.setLayout(editSelectLayout);
										editSelectPanel.setOpaque( false );
											editSelectLabel = new JLabel( "Select List" );
											doPimpMiniLabel( editSelectLabel );
											editSelectCombo = new JComboBox();
											editSelectCombo.addItemListener(
												new ItemListener()
												{
													public void itemStateChanged( ItemEvent ie )
													{
														if( ie.getStateChange() == ItemEvent.SELECTED && manageEditPanel.isVisible() )
															if( ((JComboBox)ie.getSource()).getSelectedIndex() > 0 )
															{
																editSummaryList.setListData( getAbridgedListApplicationNames( ((JComboBox)ie.getSource()).getSelectedIndex()-1) );
																if(getListApplicationNames( ((JComboBox)ie.getSource()).getSelectedIndex()-1).length>0)
																	editSummaryList.setSelectedIndex(0);
																editSummaryLabel.setText("<html><p style=\"text-align:center\">SUMMARY<br>[ " +((JComboBox)ie.getSource()).getSelectedItem() +" ]</p></html>");
																editSummaryPanel.setVisible( true );
																editSummaryHeaderPanel.setVisible( true );
															}
															else
															{
																editSummaryList.setListData( new String[]{""} );
																// doUI( UID.EDIT_EXISTING_LIST );
															}
													}
												});
											doSetSize(editSelectCombo,350,25);
										
										editSelectLayout.setHorizontalGroup(
											editSelectLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(editSelectLabel)
												.addComponent(editSelectCombo));
										editSelectLayout.setVerticalGroup(
											editSelectLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(editSelectLabel)
												.addComponent(editSelectCombo));
										
										editSummaryHeaderPanel = new JPanel();
										editSummaryHeaderPanel.setLayout( new BoxLayout( editSummaryHeaderPanel, BoxLayout.X_AXIS ) );
										editSummaryHeaderPanel.setOpaque( false );
											editSummaryLabel = new JLabel("LIST SUMMARY");
											doPimpTableHeader( editSummaryLabel );
											editSummaryLabel.setFont(LABEL_FONT_1);
											editSummaryLabel.setForeground( OVERLAY_COLOR );
										editSummaryHeaderPanel.add( Box.createHorizontalGlue() );								
										editSummaryHeaderPanel.add( editSummaryLabel );			
										editSummaryHeaderPanel.add( Box.createHorizontalStrut(5) );		
										
										editSummaryPanel = new JPanel();
										editSummaryPanel.setLayout( new BorderLayout() );
										editSummaryPanel.setOpaque( false );
											editSummaryList = new JList();
											editSummaryList.setOpaque( false );
											editSummaryList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
											editSummaryList.addListSelectionListener(
												new ListSelectionListener()
												{
													@Override
													public void valueChanged( ListSelectionEvent lse )
													{
														if( ((JList)lse.getSource()).getSelectedIndex() >= 0 )
														{
															editShowRefLabel.setVisible( false );
															editShowRefLabel2.setVisible( false );
															
															editShowNameLabel.setVisible( true );
															editShowNameLabel2.setVisible( true );
															
															editShowStatusLabel.setVisible( true );
															editShowStatusLabel2.setVisible( true );
															
															Application app = getListApplication( editSelectCombo.getSelectedIndex()-1, ((JList)lse.getSource()).getSelectedIndex() );
															editShowNameLabel2.setText( app.getName() );
															
															//treat SHOW
															switch( MODE )
															{
																case GO:
																{
																	editShowStatusLabel.setText("Date:");
																	
																	editShowStatusLabel2.setText( getDateString(app.getAppointmentDate()) );
																	editShowStatusLabel2.setForeground( Color.WHITE );
																}break;
																case OTHERS:
																{
																	editShowStatusLabel.setText("Status:");
																	editShowStatusLabel2.setText( getStatusString(app.getStatus()) );
																	
																	if( app.getStatus().equals( Application.Status.REFERRED ) )
																	{
																		editShowRefLabel.setText("Referee:");
																		editShowRefLabel2.setText( HTML_PREFIX+app.getRefereeCard().getName()+HTML_SUFFIX);
																		
																		editShowRefLabel.setVisible( true );
																		editShowRefLabel2.setVisible( true );
																	}
																	else
																	if( app.getStatus().equals( Application.Status.DEFERRED ) )
																	{
																		editShowRefLabel.setText("Recommendation:");
																		editShowRefLabel2.setText( HTML_PREFIX+app.getRecommendation()+HTML_SUFFIX );			
																		editShowRefLabel.setVisible( true );
																		editShowRefLabel2.setVisible( true );
																	}
																}break;
																case REAL:
																{
																	editShowStatusLabel.setText("Status:");
																	editShowStatusLabel2.setText( getStatusString(app.getStatus()) );
																}break;
															}
															
															//treat EDIT
															
															editEditDateLabel.setVisible( false );
															
															editEditDayLabel.setVisible( false );
															editEditDaySpin.setVisible( false );
															editEditMonthLabel.setVisible( false );
															editEditMonthSpin.setVisible( false );
															editEditYearLabel.setVisible( false );
															editEditYearSpin.setVisible( false );
															
															editEditReceivedLabel.setVisible( false );
															
															editEditRefereeLabel.setVisible( false );
															editEditRefCombo.setVisible( false );
															
															editEditRecLabel.setVisible( false );
															editEditRecField.setVisible( false );															
															
															editEditNameField.setText( app.getName() );
															switch( MODE )
															{
																case GO:
																{
																	Calendar now = app.getAppointmentDate();																
																	
																	editEditDaySpin.setValue( now.get(Calendar.DAY_OF_MONTH) );
																	editEditMonthSpin.setValue( now.get(Calendar.MONTH)+1 );
																	editEditYearSpin.setValue( now.get(Calendar.YEAR) );
																	
																	editEditDateLabel.setVisible( true );
																	
																	editEditDayLabel.setVisible( true );
																	editEditDaySpin.setVisible( true );
																	editEditMonthLabel.setVisible( true );
																	editEditMonthSpin.setVisible( true );
																	editEditYearLabel.setVisible( true );
																	editEditYearSpin.setVisible( true );
																}break;
																case OTHERS:
																{																	
																	if( app.getStatus().equals( Application.Status.DEFAULT ) )
																	{
																		editEditDefaultRadio.setSelected( true );
																		editEditReceivedLabel.setVisible( true );
																	}
																	else
																	if( app.getStatus().equals( Application.Status.REFERRED ) )
																	{
																		editEditReferredRadio.setSelected( true );
																		editEditRefCombo.setSelectedItem( app.getRefereeCard().getName() );
																		
																		editEditRefereeLabel.setVisible( true );
																		editEditRefCombo.setVisible( true );
																	}
																	else
																	if( app.getStatus().equals( Application.Status.DEFERRED ) )
																	{
																		editEditDeferredRadio.setSelected( true );
																		editEditRecField.setText( app.getRecommendation() );
																		
																		editEditRecLabel.setVisible( true );
																		editEditRecField.setVisible( true );														
																	}
																	
																}break;
																case REAL:
																{
																	if( app.getStatus().equals(Application.Status.REAL_NEXT_TIME ) )
																	{
																		editEditNextTimeButton.setSelected(true);
																	}
																	else
																	if( app.getStatus().equals(Application.Status.REAL_WAIT) )
																		editEditWaitButton.setSelected( true );
																}break;
															}
														}
													}
												});
											editSummaryList.setBackground( refsList.getBackground() );
											editSummaryList.setForeground( refsList.getForeground() );
											editSummaryList.setSelectionBackground( refsList.getSelectionBackground() );
											editSummaryList.setSelectionForeground( refsList.getSelectionForeground() );
											
											editSummaryScrollPane = new JScrollPane( editSummaryList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
											editSummaryScrollPane.setBackground( Color.PINK );
											// editSummaryScrollPane.getViewport().setOpaque( false );
											editSummaryScrollPane.setViewportBorder( BorderFactory.createLineBorder(Color.PINK,0));
											editSummaryScrollPane.setBorder( BorderFactory.createLineBorder(Color.PINK,0) );
											
											editBottomPanel = new JPanel();
											editBottomPanel.setLayout( editRightCard = new CardLayout() );
											editBottomPanel.setOpaque( false );
												editShowPanel = new JPanel();
												editShowLayout = new GroupLayout(editShowPanel);												
												editShowPanel.setLayout( editShowLayout );
												editShowPanel.setOpaque( false );
												
													editShowNameLabel = new JLabel("Name:");
													editShowNameLabel.setForeground( Color.WHITE );
													editShowNameLabel2 = new JLabel("Mrs Akinbo Odubiyi");
													editShowNameLabel2.setForeground( Color.WHITE );
													editShowNameLabel2.setFont(LABEL_FONT_1);
													
													editShowStatusLabel = new JLabel("Status:");
													editShowStatusLabel.setForeground( Color.WHITE );
													editShowStatusLabel2 = new JLabel("Letter received");
													editShowStatusLabel2.setForeground( Color.WHITE );
													editShowStatusLabel2.setFont( STATUS_FONT );
													
													editShowRefLabel = new JLabel("Referee:");
													editShowRefLabel.setForeground( Color.WHITE );
													editShowRefLabel2 = new JLabel("Pastor Jeremiah S.");
													editShowRefLabel2.setForeground( Color.WHITE );
													editShowRefLabel2.setFont(LABEL_FONT_1);
												
												editShowLayout.setAutoCreateGaps( true );
												editShowLayout.setAutoCreateContainerGaps( true );
												
												editShowLayout.setVerticalGroup(
													editShowLayout.createSequentialGroup()
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
														.addGroup(editShowLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
															.addComponent(editShowNameLabel)
															.addComponent(editShowNameLabel2))
														.addGroup(editShowLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
															.addComponent(editShowStatusLabel)
															.addComponent(editShowStatusLabel2))
														.addGroup(editShowLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
															.addComponent(editShowRefLabel)
															.addComponent(editShowRefLabel2))
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE));
												
												editShowLayout.setHorizontalGroup(
													editShowLayout.createSequentialGroup()
														.addGroup(editShowLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
															.addComponent(editShowNameLabel)
															.addComponent(editShowStatusLabel)
															.addComponent(editShowRefLabel))
														.addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED )
														.addGroup(editShowLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
															.addComponent(editShowNameLabel2)
															.addComponent(editShowStatusLabel2)
															.addComponent(editShowRefLabel2)));
												
												
												editEditPanel = new JPanel();
												editEditPanel.setOpaque( false );
												editEditPanel.setLayout( editEditLayout = new GroupLayout( editEditPanel) );
												
													editEditNameLabel = new JLabel("Name");
													editEditNameLabel.setForeground( Color.WHITE );
													editEditNameField = new JTextField();
													editEditNameField.setMaximumSize(new Dimension(390,30));
													
													editEditStatusLabel = new JLabel("Status");
													editEditStatusLabel.setForeground( Color.WHITE );
													editEditNextTimeButton = new JRadioButton("Next time");
													editEditWaitButton = new JRadioButton("WAIT");
													editEditDefaultRadio = new JRadioButton("Default");
													editEditReferredRadio = new JRadioButton("Referred");
													editEditDeferredRadio = new JRadioButton("Deferred");
													editEditDefaultRadio.addItemListener(
														new ItemListener()
														{
															@Override
															public void itemStateChanged( ItemEvent ie )
															{
																if( ie.getStateChange() == ItemEvent.SELECTED )
																{
																	editEditReceivedLabel.setVisible( true );
																	
																	editEditDateLabel.setVisible( false );
																	editEditDaySpin.setVisible( false );
																	editEditMonthSpin.setVisible( false );
																	editEditYearSpin.setVisible( false );
																	
																	editEditRefereeLabel.setVisible( false );
																	editEditRefCombo.setVisible( false );
																	
																	editEditRecLabel.setVisible( false );
																	editEditRecField.setVisible( false );
																}
															}
														});
													editEditReferredRadio.addItemListener(
														new ItemListener()
														{
															@Override
															public void itemStateChanged( ItemEvent ie )
															{
																if( ie.getStateChange() == ItemEvent.SELECTED )
																{
																	editEditReceivedLabel.setVisible( false );
																	
																	editEditDateLabel.setVisible( false );
																	editEditDaySpin.setVisible( false );
																	editEditMonthSpin.setVisible( false );
																	editEditYearSpin.setVisible( false );
																	
																	editEditRefereeLabel.setVisible( true );
																	editEditRefCombo.setVisible( true );
																	
																	editEditRecLabel.setVisible( false );
																	editEditRecField.setVisible( false );
																}
															}
														});
													editEditDeferredRadio.addItemListener(
														new ItemListener()
														{
															@Override
															public void itemStateChanged( ItemEvent ie )
															{
																if( ie.getStateChange() == ItemEvent.SELECTED )
																{
																	editEditReceivedLabel.setVisible( false );
																	
																	editEditDateLabel.setVisible( false );
																	editEditDaySpin.setVisible( false );
																	editEditMonthSpin.setVisible( false );
																	editEditYearSpin.setVisible( false );
																	
																	editEditRefereeLabel.setVisible( false );
																	editEditRefCombo.setVisible( false );
																	
																	editEditRecLabel.setVisible( true );
																	editEditRecField.setVisible( true );
																}
															}
														});
													editEditButtonGroup = new ButtonGroup();
													editEditButtonGroup.add( editEditDefaultRadio );
													editEditButtonGroup.add( editEditReferredRadio );
													editEditButtonGroup.add( editEditDeferredRadio );
													editEditButtonGroup2 = new ButtonGroup();
													editEditButtonGroup2.add(editEditNextTimeButton);
													editEditButtonGroup2.add(editEditWaitButton);
													
													editEditSpecPanel = new JPanel();
													editEditSpecPanel.setOpaque( false );
													editEditSpecPanel.setLayout( new GridBagLayout() );
													
														editEditDateLabel = new JLabel("Date");
														editEditDateLabel.setForeground( Color.WHITE );
														
														editEditDayLabel = new JLabel("Day:");
														editEditDayLabel.setForeground( Color.WHITE );
														editEditDaySpin = new JSpinner();
														
														editEditMonthLabel = new JLabel("Month:");
														editEditMonthLabel.setForeground( Color.WHITE );
														editEditMonthSpin = new JSpinner();
														
														editEditYearLabel = new JLabel("Year:");
														editEditYearLabel.setForeground( Color.WHITE );
														editEditYearSpin = new JSpinner();
														
														
														editEditDaySpin.addChangeListener(
															new ChangeListener()
															{
																@Override
																public void stateChanged( ChangeEvent ce )
																{
																	if( (Integer)((JSpinner)ce.getSource()).getValue() <= 0 || (Integer)((JSpinner)ce.getSource()).getValue() > 31 )
																		((JSpinner)ce.getSource()).setValue(GregorianCalendar.getInstance().get(Calendar.DATE));
																}
															});
														editEditMonthSpin.addChangeListener(
															new ChangeListener()
															{
																@Override
																public void stateChanged( ChangeEvent ce )
																{
																	if( (Integer)((JSpinner)ce.getSource()).getValue() <= 0 || (Integer)((JSpinner)ce.getSource()).getValue() > 12 )
																		((JSpinner)ce.getSource()).setValue(GregorianCalendar.getInstance().get(Calendar.MONTH)+1);
																}
															});
														
														editEditYearSpin.addChangeListener(
															new ChangeListener()
															{
																@Override
																public void stateChanged( ChangeEvent ce )
																{
																	if( (Integer)((JSpinner)ce.getSource()).getValue() < GregorianCalendar.getInstance().get(Calendar.YEAR) )
																		((JSpinner)ce.getSource()).setValue(GregorianCalendar.getInstance().get(Calendar.YEAR));
																}
															});
						
														editEditReceivedLabel = new JLabel("Application received!");
														editEditReceivedLabel.setForeground( Color.BLACK );
														editEditReceivedLabel.setHorizontalAlignment(JLabel.LEFT);
														editEditReceivedLabel.setFont( new Font("SansSerif",Font.ITALIC,15) );
														
														editEditRefereeLabel = new JLabel("Referee");
														editEditRefereeLabel.setForeground( Color.WHITE );
														editEditRefCombo = new JComboBox(getRefNames());
														editEditRefCombo.setMaximumSize(new Dimension(390,30));
														
														editEditRecLabel = new JLabel("REC:");
														editEditRecLabel.setForeground( Color.WHITE );
														editEditRecField = new JTextField();
														// editEditRecField.setLineWrap( false );
														editEditRecField.setMaximumSize(new Dimension(390,30));
														
													editEditLayout.setAutoCreateGaps( true );
													editEditLayout.setAutoCreateContainerGaps( true );
													
													//do eidt Layout
													switch( MODE )
													{
														case GO:
														{
															editEditLayout.setVerticalGroup(
															editEditLayout.createSequentialGroup()
																.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																	.addComponent(editEditNameLabel)
																	.addComponent(editEditNameField))
																.addGroup( editEditLayout.createParallelGroup( GroupLayout.Alignment.CENTER)
																	.addComponent( editEditDateLabel )
																	.addGroup(editEditLayout.createSequentialGroup()
																		.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																			.addComponent(editEditDayLabel)
																			.addComponent(editEditMonthLabel)
																			.addComponent(editEditYearLabel))
																		.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																			.addComponent(editEditDaySpin)
																			.addComponent(editEditMonthSpin)
																			.addComponent(editEditYearSpin)))));
																
															
															editEditLayout.setHorizontalGroup(
																editEditLayout.createSequentialGroup()
																	.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addComponent(editEditNameLabel)
																		.addComponent(editEditDateLabel))
																	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																	.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addComponent(editEditNameField)																	
																		.addGroup(editEditLayout.createSequentialGroup()
																			.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																				.addComponent(editEditDayLabel)
																				.addComponent(editEditDaySpin))
																			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
																			.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																				.addComponent(editEditMonthLabel)
																				.addComponent(editEditMonthSpin))
																			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
																			.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																				.addComponent(editEditYearLabel)
																				.addComponent(editEditYearSpin)))));
														}break;
														case OTHERS:
														{
															editEditLayout.setVerticalGroup(
															editEditLayout.createSequentialGroup()
																.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																	.addComponent(editEditNameLabel)
																	.addComponent(editEditNameField))
																.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																	.addComponent(editEditStatusLabel)
																	.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																		.addComponent(editEditDefaultRadio)
																		.addComponent(editEditReferredRadio)
																		.addComponent(editEditDeferredRadio)))
																.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																	.addComponent(editEditRefereeLabel)
																	.addComponent(editEditRefCombo))
																.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																	.addComponent(editEditRecLabel)
																	.addComponent(editEditRecField))
																.addComponent(editEditReceivedLabel));
																
															
															editEditLayout.setHorizontalGroup(
																editEditLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
																	.addGroup( editEditLayout.createSequentialGroup()
																		.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																			.addComponent(editEditNameLabel)
																			.addComponent(editEditStatusLabel)
																			.addComponent(editEditRefereeLabel)
																			.addComponent(editEditRecLabel))
																		.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																			.addComponent(editEditNameField)																	
																			.addGroup(editEditLayout.createSequentialGroup()
																				.addComponent(editEditDefaultRadio)
																				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
																				.addComponent(editEditReferredRadio)
																				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
																				.addComponent(editEditDeferredRadio))
																			.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																				.addComponent(editEditRefereeLabel)
																				.addComponent(editEditRefCombo))
																			.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																				.addComponent(editEditRecLabel)
																				.addComponent(editEditRecField))))
																	.addComponent(editEditReceivedLabel));
														}break;
														case REAL:
														{
															editEditLayout.setVerticalGroup(
															editEditLayout.createSequentialGroup()
																.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																	.addComponent(editEditNameLabel)
																	.addComponent(editEditNameField))
																.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																	.addComponent(editEditStatusLabel)
																	.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
																		.addComponent(editEditWaitButton)
																		.addComponent(editEditNextTimeButton))));
															
															editEditLayout.setHorizontalGroup(
																editEditLayout.createSequentialGroup()
																	.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addComponent(editEditNameLabel)
																		.addComponent(editEditStatusLabel))
																	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																	.addGroup(editEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addComponent(editEditNameField)																	
																		.addGroup(editEditLayout.createSequentialGroup()
																			.addComponent(editEditWaitButton)
																			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
																			.addComponent(editEditNextTimeButton))));
														}break;
													}
													
												editDeletePanel = new JPanel();
												editDeletePanel.setLayout( new GridBagLayout() );
												editDeletePanel.setOpaque( false );
													editEditConfirmDeleteLabel = new JLabel("Confirm Label");
													editEditConfirmDeleteLabel.setFont(LABEL_FONT_1);
													editEditConfirmDeleteLabel.setHorizontalAlignment(JLabel.CENTER);
													editEditConfirmDeleteLabel.setForeground( Color.WHITE );
													editEditConfirmDeleteLabel2 = new JLabel("Are you sure?");
													editEditConfirmDeleteLabel2.setHorizontalAlignment(JLabel.CENTER);
													editEditConfirmDeleteLabel2.setForeground( Color.WHITE );
													editEditConfirmDeleteLabel2.setFont(LABEL_FONT_1);
													editEditConfirmYesButton = new LinkButton("YES");
													// doPimpLink(editEditConfirmYesButton);
													editEditConfirmYesButton.addActionListener(
														new ActionListener()
														{
															public void actionPerformed( ActionEvent ae )
															{
																deleteApplication( editSelectCombo.getSelectedIndex()-1, editSummaryList.getSelectedIndex() );
																editSummaryList.setListData( getAbridgedListApplicationNames( editSelectCombo.getSelectedIndex()-1) );
																if( getListApplicationNames( editSelectCombo.getSelectedIndex()-1).length > 0 )
																	editSummaryList.setSelectedIndex(0);
																editSummaryList.setEnabled( true );
																editRightCard.show(editBottomPanel,"SHOW");
															}
														});
													editEditConfirmNoButton = new LinkButton("NO");
													editEditConfirmNoButton.addActionListener(
														new ActionListener()
														{
															public void actionPerformed( ActionEvent ae )
															{
																editSummaryList.setEnabled( true );
																editRightCard.show(editBottomPanel,"SHOW");
															}
														});
													// doPimpLink(editEditConfirmNoButton);
												
												appConstraints.fill = GridBagConstraints.HORIZONTAL;
												appConstraints.anchor = GridBagConstraints.CENTER;
												appConstraints.ipady = 10;
												
													appConstraints.weightx = 2;
													appConstraints.gridwidth = GridBagConstraints.REMAINDER;
												addGBComponent( editDeletePanel,editEditConfirmDeleteLabel,0,0 );
													appConstraints.weightx = 2;
												addGBComponent( editDeletePanel,editEditConfirmDeleteLabel2,0,1 );
													appConstraints.fill = GridBagConstraints.NONE;
													appConstraints.weightx = 1;
													appConstraints.gridwidth = 1;
												appConstraints.ipady = 0;
												addGBComponent( editDeletePanel,editEditConfirmNoButton,GridBagConstraints.RELATIVE,2 );
												addGBComponent( editDeletePanel,editEditConfirmYesButton,GridBagConstraints.RELATIVE,2 );
												
											editBottomPanel.add( editShowPanel, "SHOW" );
											editBottomPanel.add( editDeletePanel, "DELETE" );
											editBottomPanel.add( editEditPanel, "EDIT" );
										editSummaryPanel.add( editSummaryScrollPane, BorderLayout.NORTH );
										editSummaryPanel.add( editBottomPanel, BorderLayout.CENTER );
										editSummaryScrollPane.setMinimumSize(new Dimension(390,100) );
										
										editLinkPanel = new JPanel();
										editLinkPanel.setLayout( new BoxLayout( editLinkPanel, BoxLayout.X_AXIS ) );
										editLinkPanel.setOpaque( false );
											addRoundButton = new LinkButton( "ADD" );
											addRoundButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														if( editSelectCombo.getSelectedIndex() > 0 )
														{
															if( ((JButton)ae.getSource()).getText().equals("ADD") )
															{
																editEditNameField.setText("");
																editEditDefaultRadio.setSelected( true );
																
																editEditDateLabel.setVisible( true );
																editEditDaySpin.setVisible( true );
																editEditMonthSpin.setVisible( true );
																editEditYearSpin.setVisible( true );
																
																editEditReceivedLabel.setVisible( true );
																
																editEditRefereeLabel.setVisible( false );
																editEditRefCombo.setVisible( false );
																
																editEditRecLabel.setVisible( false );
																editEditRecField.setVisible( false );
																editEditRecField.setText("");
																
																editEditNextTimeButton.setSelected(false);
																editEditWaitButton.setSelected(false);
																
																
																editEditDaySpin.setValue( GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH) );
																editEditMonthSpin.setValue( GregorianCalendar.getInstance().get(Calendar.MONTH)+1 );
																editEditYearSpin.setValue( GregorianCalendar.getInstance().get(Calendar.YEAR) );
																
																
																addRoundButton.setText("SAVE");
																editRoundsButton.setText("OK");
																editTitleButton.setText("DONE");
																editRightCard.show(editBottomPanel,"EDIT");
															}
															else
															if( ((JButton)ae.getSource()).getText().equals("SAVE") )
															{
																if( editEditNameField.getText().trim().length() > 0 )
																{
																	Application app = new Application(editEditNameField.getText());
																	switch( MODE )
																	{
																		case GO:
																		{
																			app.setAppointmentDate( new GregorianCalendar((Integer)editEditYearSpin.getValue(),(Integer)editEditMonthSpin.getValue()-1,(Integer)editEditDaySpin.getValue()) );
																		}break;
																		case OTHERS:
																		{
																			if( editEditDefaultRadio.isSelected() )
																			{
																				app.setStatus(Application.Status.DEFAULT);
																			}
																			else
																			if( editEditReferredRadio.isSelected() )
																			{
																				app.setStatus(Application.Status.REFERRED);
																				app.setRefereeCard( getCard(editEditRefCombo.getSelectedIndex()) );
																			}
																			else
																			if( editEditDeferredRadio.isSelected() )
																			{
																				app.setStatus(Application.Status.DEFERRED);
																				app.setRecommendation( editEditRecField.getText().trim() );
																			}																			
																		}break;
																		case REAL:
																		{
																			if( editEditNextTimeButton.isSelected() )
																				app.setStatus( Application.Status.REAL_NEXT_TIME );
																			else
																			if( editEditWaitButton.isSelected() )
																				app.setStatus( Application.Status.REAL_WAIT );
																		}break;
																	}
																	
																	insertApplication( editSelectCombo.getSelectedIndex()-1,app );				
																	editSummaryList.setListData( getAbridgedListApplicationNames( editSelectCombo.getSelectedIndex()-1) );
																	if( getListApplicationNames( editSelectCombo.getSelectedIndex()-1 ).length > 0 )
																		editSummaryList.setSelectedIndex( getListApplicationNames( editSelectCombo.getSelectedIndex()-1 ).length-1 );
																		
																	editEditNameField.setText("");
																	editEditRecField.setText("");
																}
															}
															else
															if( ((JButton)ae.getSource()).getText().equals("CANCEL") )
															{
																editRoundsButton.setText("EDIT");
																editTitleButton.setText("DELETE");
																addRoundButton.setText("ADD");
																editRightCard.show(editBottomPanel,"SHOW");
															}
														}
													}
												});
											editRoundsButton = new LinkButton( "EDIT" );
											// doPimpLink( editRoundsButton );
											editRoundsButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														if( editSummaryList.getSelectedIndex() >= 0 )
														{
															if( ((JButton)ae.getSource()).getText().equals("EDIT") )
															{
																editRoundsButton.setText("SAVE");
																editTitleButton.setText("CANCEL");
																addRoundButton.setText("ADD");
																editRightCard.show( editBottomPanel, "EDIT");
															}
															else
															if( ((JButton)ae.getSource()).getText().equals("SAVE") )
															{
																if( editEditNameField.getText().trim().length() > 0 )
																{
																	Application app = new Application(editEditNameField.getText());
																	switch( MODE )
																	{
																		case GO:
																		{
																			app.setAppointmentDate( new GregorianCalendar((Integer)editEditYearSpin.getValue(),(Integer)editEditMonthSpin.getValue()-1,(Integer)editEditDaySpin.getValue()) );
																		}break;
																		case OTHERS:
																		{
																			if( editEditDefaultRadio.isSelected() )
																			{
																				app.setStatus(Application.Status.DEFAULT);
																			}
																			else
																			if( editEditReferredRadio.isSelected() )
																			{
																				app.setStatus(Application.Status.REFERRED);
																				app.setRefereeCard( getCard(editEditRefCombo.getSelectedIndex()) );
																			}
																			else
																			if( editEditDeferredRadio.isSelected() )
																			{
																				app.setStatus(Application.Status.DEFERRED);
																				app.setRecommendation( editEditRecField.getText().trim() );
																			}																			
																		}break;
																		case REAL:
																		{
																			if( editEditNextTimeButton.isSelected() )
																				app.setStatus( Application.Status.REAL_NEXT_TIME );
																			else
																			if( editEditWaitButton.isSelected() )
																				app.setStatus( Application.Status.REAL_WAIT );
																		}break;
																	}
																	updateApplication( editSelectCombo.getSelectedIndex()-1, editSummaryList.getSelectedIndex(),app );				
																	int prevSelect = editSummaryList.getSelectedIndex();
																	editSummaryList.setListData( getAbridgedListApplicationNames( editSelectCombo.getSelectedIndex()-1) );
																	// if( getListApplicationNames( editSelectCombo.getSelectedIndex()-1).length > 0 )
																	editSummaryList.setSelectedIndex(0);
																	editSummaryList.setSelectedIndex(prevSelect);
																	editRightCard.show(editBottomPanel,"SHOW");
																	editRoundsButton.setText("EDIT");
																	editTitleButton.setText("DELETE");
																	addRoundButton.setText("ADD");
																}
															}
															else
															if( ((JButton)ae.getSource()).getText().equals("CANCEL") )
															{
																editRightCard.show(editBottomPanel,"SHOW");
																editRoundsButton.setText("EDIT");
																editTitleButton.setText("DELETE");
																addRoundButton.setText("ADD");																
															}
														}
														else
														if( ((JButton)ae.getSource()).getText().equals("OK") )
														{
															if( editEditNameField.getText().trim().length() > 0 )
															{
																Application app = new Application(editEditNameField.getText());
																switch( MODE )
																{
																	case GO:
																	{
																		app.setAppointmentDate( new GregorianCalendar((Integer)editEditYearSpin.getValue(),(Integer)editEditMonthSpin.getValue()-1,(Integer)editEditDaySpin.getValue()) );
																	}break;
																	case OTHERS:
																	{
																		if( editEditDefaultRadio.isSelected() )
																		{
																			app.setStatus(Application.Status.DEFAULT);
																		}
																		else
																		if( editEditReferredRadio.isSelected() )
																		{
																			app.setStatus(Application.Status.REFERRED);
																			app.setRefereeCard( getCard(editEditRefCombo.getSelectedIndex()) );
																		}
																		else
																		if( editEditDeferredRadio.isSelected() )
																		{
																			app.setStatus(Application.Status.DEFERRED);
																			app.setRecommendation( editEditRecField.getText().trim() );
																		}																			
																	}break;
																	case REAL:
																	{
																		if( editEditNextTimeButton.isSelected() )
																			app.setStatus( Application.Status.REAL_NEXT_TIME );
																		else
																		if( editEditWaitButton.isSelected() )
																			app.setStatus( Application.Status.REAL_WAIT );
																	}break;
																}
																
																insertApplication( editSelectCombo.getSelectedIndex()-1,app );				
																editSummaryList.setListData( getAbridgedListApplicationNames( editSelectCombo.getSelectedIndex()-1) );
																if( getListApplicationNames( editSelectCombo.getSelectedIndex()-1 ).length > 0 )
																	editSummaryList.setSelectedIndex( getListApplicationNames( editSelectCombo.getSelectedIndex()-1 ).length-1 );
																	
																editEditNameField.setText("");
																editEditRecField.setText("");
															}
															editRoundsButton.setText("EDIT");
															editTitleButton.setText("DELETE");
															addRoundButton.setText("ADD");
															editRightCard.show(editBottomPanel,"SHOW");
														}
													}
												});
											editTitleButton = new LinkButton( "DELETE" );
											editTitleButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														if( editSummaryList.getSelectedIndex() >= 0 )
														{
															if( ((JButton)ae.getSource()).getText().equals("DELETE") )
															{
																editSummaryList.setEnabled( false );
																editEditConfirmDeleteLabel.setText( HTML_PREFIX+"<p style=\"text-align:center\">Delete <font color=\"black\">"+editSummaryList.getSelectedValue()+"</font></p>"+HTML_SUFFIX );
																editRoundsButton.setText("EDIT");
																addRoundButton.setText("ADD");
																editRightCard.show( editBottomPanel, "DELETE");
															}
															else
															if( ((JButton)ae.getSource()).getText().equals("CANCEL") || ((JButton)ae.getSource()).getText().equals("DONE") )
															{
																editSummaryList.setEnabled( true );
																editRoundsButton.setText("EDIT");
																editTitleButton.setText("DELETE");
																addRoundButton.setText("ADD");
																editRightCard.show(editBottomPanel,"SHOW");
															}
														}
													}
												});
											editBackButton = new LinkButton( "<< back" );
											editBackButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														doUI( UID.HOME );
													}
												});
										editLinkPanel.add( Box.createHorizontalGlue() );
										editLinkPanel.add( editBackButton );
										editLinkPanel.add( Box.createHorizontalGlue() );
										editLinkPanel.add( editTitleButton );
										editLinkPanel.add( Box.createHorizontalGlue() );
										editLinkPanel.add( editRoundsButton );
										editLinkPanel.add( Box.createHorizontalGlue() );
										editLinkPanel.add( addRoundButton );
										editLinkPanel.add( Box.createHorizontalGlue() );
									manageEditPanel.add( Box.createVerticalStrut(2) );
									manageEditPanel.add( editSelectPanel );
									// manageEditPanel.add( editComboPanel );
									manageEditPanel.add( Box.createVerticalGlue() );
									manageEditPanel.add( Box.createVerticalStrut(5) );
									manageEditPanel.add( editSummaryHeaderPanel );
									manageEditPanel.add( editSummaryPanel );
									manageEditPanel.add( Box.createVerticalGlue() );
									manageEditPanel.add( editLinkPanel );
									
									manageDeletePanel = new JPanel();
									manageDeletePanel.setBackground( LINK_PANEL_BG );
									manageDeletePanel.setOpaque( false );
									manageDeletePanel.setLayout( new BoxLayout( manageDeletePanel, BoxLayout.Y_AXIS ) );
										manageDeleteSelectPanel = new JPanel();
										manageDeleteLayout = new GroupLayout(manageDeleteSelectPanel);
										manageDeleteSelectPanel.setLayout( manageDeleteLayout );
										manageDeleteSelectPanel.setOpaque( false );
											manageDeleteSelectLabel = new JLabel( "Select List:" );
											doPimpMiniLabel( manageDeleteSelectLabel );
											manageDeleteSelectCombo = new JComboBox( getAbridgedListNames() );
											manageDeleteSelectCombo.addItemListener(
												new ItemListener()
												{
													public void itemStateChanged( ItemEvent ie )
													{
														if( ie.getStateChange() == ItemEvent.SELECTED && manageDeletePanel.isVisible() )
															if( manageDeleteSelectCombo.getSelectedIndex() > 0 )
															{
																manageDeleteButton.setVisible( true );
															}
															else
															{
																manageDeleteButton.setVisible( false );
															}
													}
												});
											doSetSize(manageDeleteSelectCombo,350,25);
											
											manageDeleteButton = new LinkButton("DELETE" );
											manageDeleteButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														manageDeleteSelectCombo.setEnabled( false );
														manageDeleteConfirLabel.setText( "<html><p style=\"text-align:center\">Delete <br>"+manageDeleteSelectCombo.getSelectedItem()+"</p></html>");
														manageDeleteConfirmPanel.setVisible( true );
															doSetSize(manageDeletePanel,390,160 );
														doSetSize(manageLinkContainer,405,160 ); 
													}
												});
										
										
									manageDeleteLayout.setHorizontalGroup(
										manageDeleteLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
											.addComponent(manageDeleteSelectLabel)
											.addComponent(manageDeleteSelectCombo)
											.addGroup(manageDeleteLayout.createSequentialGroup()
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
												.addComponent(manageDeleteButton)));
									manageDeleteLayout.setVerticalGroup(
										manageDeleteLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(manageDeleteSelectLabel)
											.addComponent(manageDeleteSelectCombo)
											.addComponent(manageDeleteButton)
											.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED));
									
										
										manageDeleteConfirmPanel = new JPanel();						
										manageDeleteConfirmPanel.setBackground( LINK_PANEL_BG );
										manageDeleteConfirmPanel.setOpaque( false );
										manageDeleteConfirmPanel.setLayout( new BoxLayout(manageDeleteConfirmPanel, BoxLayout.Y_AXIS) );
											manageDeleteConfirLabel = new JLabel("Delete .....");
											manageDeleteConfirLabel.setForeground(OVERLAY_COLOR);
											manageDeleteConfirLabel.setFont(LABEL_FONT_1);
											manageDeleteConfirLabel.setAlignmentX( JLabel.CENTER_ALIGNMENT );
											// manageDeleteConfirLabel.setOpaque( true );
											manageDeleteConfirLabel.setHorizontalAlignment( JLabel.CENTER );
											manageDeleteConfirLabel2 = new JLabel("Confirm Delete?");
											// manageDeleteConfirLabel2.setOpaque( true );
											manageDeleteConfirLabel2.setAlignmentX( JLabel.CENTER_ALIGNMENT );
											manageDeleteConfirLabel2.setHorizontalAlignment( JLabel.CENTER );
											manageConfirmButtonsPanel = new JPanel();						
											manageConfirmButtonsPanel.setBackground( LINK_PANEL_BG );
											manageConfirmButtonsPanel.setOpaque( false );
											manageConfirmButtonsPanel.setLayout( new BoxLayout( manageConfirmButtonsPanel, BoxLayout.X_AXIS ) );
												manageDeleteYesButton = new LinkButton("YES");
												manageDeleteYesButton.addActionListener(
													new ActionListener()
													{
														public void actionPerformed( ActionEvent ae )
														{
															deleteList( manageDeleteSelectCombo.getSelectedIndex()-1 );
															manageDeleteSelectCombo.setEnabled( true );
															manageDeleteConfirmPanel.setVisible( false );
																doSetSize(manageDeletePanel,390,90 );
															doSetSize(manageLinkContainer,405,90 ); 
														}
													});
												// doPimpLink( manageDeleteYesButton );
												manageDeleteNoButton = new LinkButton("NO");
												manageDeleteNoButton.addActionListener(
													new ActionListener()
													{
														public void actionPerformed( ActionEvent ae )
														{
															manageDeleteSelectCombo.setSelectedIndex(0);
															manageDeleteSelectCombo.setEnabled( true );
															manageDeleteConfirmPanel.setVisible( false );
																doSetSize(manageDeletePanel,390,90 );
															doSetSize(manageLinkContainer,405,90 ); 
														}
													});
												// doPimpLink( manageDeleteNoButton );
											manageConfirmButtonsPanel.add( Box.createHorizontalGlue() );
											manageConfirmButtonsPanel.add( manageDeleteNoButton );
											manageConfirmButtonsPanel.add( Box.createHorizontalStrut(40) );
											manageConfirmButtonsPanel.add( manageDeleteYesButton );
											manageConfirmButtonsPanel.add( Box.createHorizontalGlue() );
										manageDeleteConfirmPanel.add( manageDeleteConfirLabel );
										manageDeleteConfirmPanel.add( Box.createVerticalStrut(5) );
										manageDeleteConfirmPanel.add( manageDeleteConfirLabel2 );
										manageDeleteConfirmPanel.add( Box.createVerticalGlue() );
										manageDeleteConfirmPanel.add( manageConfirmButtonsPanel );

										manageDeleteLinkPanel = new JPanel();
										manageDeleteLinkPanel.setLayout( new BoxLayout( manageDeleteLinkPanel, BoxLayout.X_AXIS ) );
										manageDeleteLinkPanel.setOpaque( false );
											manageDeleteBackButton = new LinkButton( "<< back" );
											manageDeleteBackButton.addActionListener(
												new ActionListener()
												{
													public void actionPerformed( ActionEvent ae )
													{
														doUI( UID.HOME );
													}
												});
										manageDeleteLinkPanel.add( manageDeleteBackButton );
										manageDeleteLinkPanel.add( Box.createHorizontalGlue() );
									
									manageDeletePanel.add( manageDeleteSelectPanel );
									manageDeletePanel.add( Box.createVerticalGlue() );
									manageDeletePanel.add( manageDeleteConfirmPanel );
									manageDeletePanel.add( Box.createVerticalGlue() );
									manageDeletePanel.add( manageDeleteLinkPanel );
									// manageDeletePanel.add( Box.createVerticalGlue() );
								
								manageLinkPanel.add( manageHomePanel, "HOME" );
								manageLinkPanel.add( manageDeletePanel, "DELETE" );
								manageLinkPanel.add( manageEditPanel, "EDIT" );
								doSetSize(manageLinkPanel,390,90);
							doSetSize(manageLinkContainer,405,90);
						
							selectLinkContainer.add( selLinkPanel,BorderLayout.EAST );					
							selectLinkContainer.setVisible( false );
						selectContainer.add( selectHeader );
						selectContainer.add( selectLinkContainer );	
						
							createLinkContainer.add( createLinkPanel,BorderLayout.EAST );
							createLinkContainer.setVisible( false );
						createContainer.add( createHeader );
						createContainer.add( createLinkContainer );		
						
							manageLinkContainer.add( manageLinkPanel,BorderLayout.EAST );
							manageLinkContainer.setVisible( false );
						manageContainer.add( manageHeader );
						manageContainer.add( manageLinkContainer );
					
					eastCenterPanel.add( selectContainer );
					eastCenterPanel.add( Box.createVerticalStrut(10) );
					eastCenterPanel.add( createContainer );
					eastCenterPanel.add( Box.createVerticalStrut(10) );
					eastCenterPanel.add( manageContainer );
				eastCenterPanel.add( Box.createVerticalGlue() );
			eastPanel.add( eastCenterPanel );
			eastPanel.add( Box.createHorizontalStrut(CONTAINER_GAP) );
				
			UIPanel.add( northPanel, BorderLayout.NORTH );
			UIPanel.add( westPanel, BorderLayout.WEST );
			UIPanel.add( southPanel, BorderLayout.SOUTH );
			UIPanel.add( eastPanel, BorderLayout.EAST );
		add( iconPanel );
		add( Box.createVerticalGlue() );
		add( UIPanel );
		add( Box.createVerticalGlue() );
		doUI( UID.HOME );
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		// this.setOpaque( false );
		g.drawImage( BG.getImage(), 0,0,Double.valueOf(this.getSize().getWidth() ).intValue(),Double.valueOf(this.getSize().getHeight() ).intValue(),this );
	}
	
	public void addGBComponent( Container cC, Component c, int x, int y )
	{
		appConstraints.gridx = x;
		appConstraints.gridy = y;
		cC.add( c, appConstraints );
	}
	
	public void doUI( UID index )
	{
		switch( index )
		{
			case HOME:
			{
				refreshActiveList();
				refreshRefsList();
				refreshActiveList();
				
				if( MODE.equals( Mode.GO) )
					viewLabel.setText(TITLE_GO);
				else
				if( MODE.equals( MODE.OTHERS) )
					viewLabel.setText(TITLE_OTHERS);
				else
				if( MODE.equals( MODE.REAL) )
					viewLabel.setText(TITLE_REAL);
				
				((HeaderPanel)createHeader).redefine("Create New List");
				((HeaderPanel)manageHeader).redefine("Manage Existing List");
				createCard.show(createLinkPanel,"HOME");
				manageCard.show(manageLinkPanel,"HOME");
				
				doSetSize(createLinkPanel,390,90);
				doSetSize(createLinkContainer,405,90);
				doSetSize(manageLinkPanel,390,90);
				doSetSize(manageLinkContainer,405,90);
				
				selectContainer.setVisible( true );
				createContainer.setVisible( true );
				manageContainer.setVisible( true );
				
				selectLinkContainer.setVisible( false );
				createLinkContainer.setVisible( false );
				manageLinkContainer.setVisible( false );
			}break;
			case PREFS:
			{
				if( listTitleField.getText().trim().length() == 0 )
					listTitleField.setText( getDefaultListTitle() );
				listTitleField.setCaretPosition(0);
				
				((HeaderPanel)createHeader).redefine("Create New List","Populate List");
				
				
				doSetSize(createLinkPanel,390,370);
				doSetSize(createLinkContainer,405,370);
				
				createCard.show(createLinkPanel,"PREFS");// createLinkPanel
				
				manageContainer.setVisible( false );
				selectContainer.setVisible( false );
			}break;
			case EDIT_EXISTING_LIST:
			{
				((HeaderPanel)manageHeader).redefine("Manage Existing List","Edit existing list" );
				manageCard.show(manageLinkPanel,"EDIT");
				
				doSetSize(manageLinkPanel,390,370);
					doSetSize(manageLinkContainer,405,370);
				
				createContainer.setVisible( false );
				selectContainer.setVisible( false );
				
				editSelectCombo.setSelectedIndex(0);
				
				editSummaryPanel.setVisible( false );
				editSummaryHeaderPanel.setVisible( false );
				editShowNameLabel.setVisible( false );
				editShowNameLabel2.setVisible( false );
				editShowStatusLabel.setVisible( false );
				editShowStatusLabel2.setVisible( false );
				editShowRefLabel.setVisible( false );
				editShowRefLabel2.setVisible( false );
			}break;
			case DELETE_EXISTING_LIST:
			{
				((HeaderPanel)manageHeader).redefine("Manage Existing List","Delete existing list" );
				manageCard.show(manageLinkPanel,"DELETE");
				manageDeleteSelectCombo.setEnabled( true );
				manageDeleteButton.setVisible( false );
				manageDeleteConfirmPanel.setVisible( false );
					doSetSize(manageDeletePanel,390,90 );
				doSetSize(manageLinkContainer,405,90 ); 
				
				createContainer.setVisible( false );
				selectContainer.setVisible( false );
			}break;
		}
		UIPanel.doLayout();
		UIPanel.revalidate();
	}
	
	void printApplicationForm( int index, JPanel panel )
	{
		int prevCount = panel.getComponentCount();
		panel.setVisible( false );
		for( int i=0; i<index; i++ )
		{
			printApplicationForm(panel);
		}
		
		if( panel.getComponentCount()>0 && prevCount>0 )
		try{
			int x = panel.getComponents()[prevCount-1].getX();
			int y = panel.getComponents()[prevCount-1].getY();
			createScrollPane.getViewport().setViewPosition( new Point(x,y) );
		}catch(NullPointerException e )
		{}
		panel.setVisible( true );
	}
	
	void printApplicationForm( JPanel panel )
	{
		final JPanel newPanel = new JPanel();
		newPanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) );
		GroupLayout layout = new GroupLayout( newPanel );
		layout.setAutoCreateGaps( true );
		layout.setAutoCreateContainerGaps( true );
		newPanel.setLayout( layout );
		newPanel.setOpaque( false );		
		
			JLabel serialLabel = new JLabel((panels.size()+1)+". ");
			doPimpMiniLabel( serialLabel );
			serialLabel.setForeground( Color.BLACK );
			serialLabel.setHorizontalAlignment(JLabel.LEFT);
			
			JButton closeButton = new JButton();
			// doPimpLink(closeButton);
			closeButton.setBackground(Color.RED);
			closeButton.addMouseListener(
				new MouseAdapter()
				{
					@Override
					public void mouseClicked( MouseEvent me )
					{
						if( me.getClickCount() == 2 )
						{
							newPanel.setVisible( false );
							int serial = 0;
							for(int i=0; i<panels.size(); i++ )
							{
								if( panels.get(i).isVisible() )
								{
									serialLabels.get(i).setText(++serial+"");
								}
								else
									continue;
							}
						}
					}
				});
				
			final JLabel nameLabel = new JLabel("Name ");
			nameLabel.setHorizontalAlignment(JLabel.LEFT);
			nameLabel.setForeground( Color.WHITE );
			nameLabel.setBackground( Color.PINK );
			final JTextField nameField = new JTextField();
			nameField.setMaximumSize( new Dimension( 400, 25 ) );
			
			switch( MODE )
			{
				case GO:
				{					
					final JLabel dateLabel = new JLabel("Date");
						dateLabel.setForeground( Color.WHITE );
					
					final JLabel dayLabel = new JLabel("Day:");
					doPimpMiniLabel( dayLabel );
					dayLabel.setHorizontalAlignment(JLabel.LEFT);
					final JLabel monthLabel = new JLabel("Month:");
					doPimpMiniLabel( monthLabel );
					monthLabel.setHorizontalAlignment(JLabel.LEFT);
					final JLabel yearLabel = new JLabel("Year:");
					doPimpMiniLabel( yearLabel );
					yearLabel.setHorizontalAlignment(JLabel.LEFT);
					
					final JSpinner daySpin = new JSpinner();
					doSetSize( daySpin, 58, 30 );
					daySpin.addChangeListener(
						new ChangeListener()
						{
							@Override
							public void stateChanged( ChangeEvent ce )
							{
								if( (Integer)((JSpinner)ce.getSource()).getValue() <= 0 || (Integer)((JSpinner)ce.getSource()).getValue() > 31 )
									((JSpinner)ce.getSource()).setValue(GregorianCalendar.getInstance().get(Calendar.DATE));
							}
						});
					final JSpinner monthSpin = new JSpinner();
					doSetSize( monthSpin, 66, 30 );	
					monthSpin.addChangeListener(
						new ChangeListener()
						{
							@Override
							public void stateChanged( ChangeEvent ce )
							{
								if( (Integer)((JSpinner)ce.getSource()).getValue() <= 0 || (Integer)((JSpinner)ce.getSource()).getValue() > 12 )
									((JSpinner)ce.getSource()).setValue(GregorianCalendar.getInstance().get(Calendar.MONTH)+1);
							}
						});
					final JSpinner yearSpin = new JSpinner();
						doSetSize( yearSpin, 82, 30 );
					yearSpin.addChangeListener(
						new ChangeListener()
						{
							@Override
							public void stateChanged( ChangeEvent ce )
							{
								if( (Integer)((JSpinner)ce.getSource()).getValue() < GregorianCalendar.getInstance().get(Calendar.YEAR) )
									((JSpinner)ce.getSource()).setValue(GregorianCalendar.getInstance().get(Calendar.YEAR));
							}
						});
						
					Calendar now = GregorianCalendar.getInstance();																
					
					daySpin.setValue( now.get(Calendar.DATE) );
					monthSpin.setValue( now.get(Calendar.MONTH)+1 );
					yearSpin.setValue( now.get(Calendar.YEAR) );
					
					layout.setVerticalGroup(
						layout.createSequentialGroup()
							.addGroup(layout.createSequentialGroup()
								.addGroup( layout.createParallelGroup(GroupLayout.Alignment.CENTER)
									.addComponent( serialLabel)
									.addComponent( closeButton ))
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.CENTER)
									.addComponent( nameLabel )
									.addComponent( nameField ))
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.CENTER)
									.addComponent( dateLabel )
									.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
											.addComponent(dayLabel)
											.addComponent(monthLabel)
											.addComponent(yearLabel))
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
											.addComponent(daySpin)
											.addComponent(monthSpin)
											.addComponent(yearSpin))))));
					
					layout.setHorizontalGroup(
						layout.createSequentialGroup()
							.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING)
								.addComponent(serialLabel)
								.addComponent(nameLabel)
								.addComponent(dateLabel))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED )
							.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
								.addComponent( nameField)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(dayLabel)
										.addComponent(daySpin))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(monthLabel)
										.addComponent(monthSpin))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(yearLabel)
										.addComponent(yearSpin))))
								.addComponent(closeButton));
					daySpins.add( daySpin );
					monthSpins.add( monthSpin );
					yearSpins.add( yearSpin );
				}break;
				case OTHERS:
				{
					final JLabel statusLabel = new JLabel("Status ");
					statusLabel.setHorizontalAlignment(JLabel.LEFT);
					statusLabel.setForeground( Color.WHITE );
					
					final JLabel receivedLabel = new JLabel("Application received!");
					receivedLabel.setForeground( Color.BLACK );
					receivedLabel.setHorizontalAlignment(JLabel.LEFT);
					receivedLabel.setFont( new Font("SansSerif",Font.ITALIC,15) );
					
					final JLabel refLabel = new JLabel("Referee  ");
					refLabel.setHorizontalAlignment(JLabel.LEFT);
					refLabel.setForeground( Color.WHITE );
					final JComboBox refCombo = new JComboBox(getRefNames());
						refCombo.setMaximumSize( new Dimension( 400, 25 ) );
					
					final JLabel recLabel = new JLabel("Recommendation  ");
					recLabel.setForeground( Color.WHITE );
					final JTextArea recField = new JTextArea();
					recField.setLineWrap( true );
					recField.setRows( 100 );
					recField.setMaximumSize( new Dimension( 400, 60 ) );

					JRadioButton defaultButton = new JRadioButton("Default");
					defaultButton.addItemListener(
						new ItemListener()
						{
							@Override
							public void itemStateChanged( ItemEvent ie )
							{
								if( ie.getStateChange() == ItemEvent.SELECTED )
								{
									receivedLabel.setVisible( true );
									
									// dateLabel.setVisible( false );
									// daySpin.setVisible( false );
									// monthSpin.setVisible( false );
									// yearSpin.setVisible( false );
									
									// dayLabel.setVisible( false );
									// monthLabel.setVisible( false );
									// yearLabel.setVisible( false );
									
									refLabel.setVisible( false );
									refCombo.setVisible( false );
									
									recLabel.setVisible( false );
									recField.setVisible( false );
								}
							}
						});
					
					JRadioButton referredButton = new JRadioButton("Referred");
					referredButton.addItemListener(
						new ItemListener()
						{
							@Override
							public void itemStateChanged( ItemEvent ie )
							{
								if( ie.getStateChange() == ItemEvent.SELECTED )
								{
									receivedLabel.setVisible( false );
									
									// dateLabel.setVisible( false );
									// daySpin.setVisible( false );
									// monthSpin.setVisible( false );
									// yearSpin.setVisible( false );
									
									// dayLabel.setVisible( false );
									// monthLabel.setVisible( false );
									// yearLabel.setVisible( false );
									
									refLabel.setVisible( true );
									refCombo.setVisible( true );
									
									recLabel.setVisible( false );
									recField.setVisible( false );
								}
							}
						});
					JRadioButton deferredButton = new JRadioButton("Deferred");
					deferredButton.addItemListener(
						new ItemListener()
						{
							@Override
							public void itemStateChanged( ItemEvent ie )
							{
								if( ie.getStateChange() == ItemEvent.SELECTED )
								{
									receivedLabel.setVisible( false );
									
									// dateLabel.setVisible( false );
									// daySpin.setVisible( false );
									// monthSpin.setVisible( false );
									// yearSpin.setVisible( false );
									
									// dayLabel.setVisible( false );
									// monthLabel.setVisible( false );
									// yearLabel.setVisible( false );
									
									refLabel.setVisible( false );
									refCombo.setVisible( false );
									
									recLabel.setVisible( true );
									recField.setVisible( true );
								}
							}
						});
					ButtonGroup statusGroup = new ButtonGroup();
					statusGroup.add( defaultButton );
					// statusGroup.add( goButton );
					statusGroup.add( referredButton );
					statusGroup.add( deferredButton );
					defaultButton.setSelected( true );
					
					JPanel specPanel = new JPanel();
					GroupLayout specLayout = new GroupLayout( specPanel );
					specPanel.setLayout( specLayout );
					specPanel.setOpaque( false );
						
						specLayout.setAutoCreateContainerGaps( false );
						specLayout.setAutoCreateGaps( false );
						
						specLayout.setVerticalGroup(
							specLayout.createSequentialGroup()
								.addGroup(specLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
									.addComponent(refLabel)
									.addComponent(refCombo))
								.addGroup(specLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
									.addComponent(recLabel)
									.addComponent(recField))
								.addComponent(receivedLabel));
						
						specLayout.setHorizontalGroup(
							specLayout.createSequentialGroup()
								.addGroup(specLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(refLabel)
									.addComponent(recLabel)
									.addComponent(receivedLabel))
								.addGroup(specLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(refCombo)
									.addComponent(recField)
									.addComponent(receivedLabel)));//!
					
					layout.setVerticalGroup(
						layout.createSequentialGroup()
							.addGroup(layout.createSequentialGroup()
								.addGroup( layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent( serialLabel)
								.addComponent( closeButton ))
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.CENTER)
									.addComponent( nameLabel )
									.addComponent( nameField ))
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.CENTER)
									.addComponent( statusLabel )
									.addComponent( defaultButton )
									.addComponent( referredButton )
									.addComponent( deferredButton) ) )
							.addComponent( specPanel ));
					
					layout.setHorizontalGroup(
						layout.createParallelGroup( GroupLayout.Alignment.LEADING)
							.addGroup( layout.createSequentialGroup()
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING)
									.addComponent(serialLabel)
									.addComponent(nameLabel)
									.addComponent(statusLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED )
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
									.addComponent( nameField)
									.addGroup( layout.createSequentialGroup()
										.addComponent(defaultButton)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(referredButton)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(deferredButton)))
								.addComponent(closeButton))
							.addComponent(specPanel));
					
					
					defaultButtons.add( defaultButton );
					referredButtons.add( referredButton );
					deferredButtons.add( deferredButton );
					refBoxes.add( refCombo );
					recFields.add( recField );
				}break;
				case REAL:
				{
					final JLabel statusLabel = new JLabel("Status ");
					statusLabel.setHorizontalAlignment(JLabel.LEFT);
					statusLabel.setForeground( Color.WHITE );
					
					final JRadioButton nextTimeButton = new JRadioButton("Next Time");
					final JRadioButton waitButton = new JRadioButton("WAIT");
					ButtonGroup realGroup = new ButtonGroup();
					realGroup.add( nextTimeButton );
					realGroup.add( waitButton );
					
					layout.setVerticalGroup(
						layout.createSequentialGroup()
							.addGroup(layout.createSequentialGroup()
								.addGroup( layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent( serialLabel)
								.addComponent( closeButton ))
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.CENTER)
									.addComponent( nameLabel )
									.addComponent( nameField ))
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.CENTER)
									.addComponent( statusLabel )
									.addComponent( waitButton )
									.addComponent( nextTimeButton ) ) ) );
					
					layout.setHorizontalGroup(
						layout.createParallelGroup( GroupLayout.Alignment.LEADING)
							.addGroup( layout.createSequentialGroup()
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING)
									.addComponent(serialLabel)
									.addComponent(nameLabel)
									.addComponent(statusLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED )
								.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
									.addComponent( nameField)
									.addGroup( layout.createSequentialGroup()
										.addComponent(waitButton)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(nextTimeButton)))
								.addComponent(closeButton)));
					
					nextTimeButtons.add(nextTimeButton);
					waitButtons.add(waitButton);
				}break;
			}
		
		panels.add( newPanel );
		serialLabels.add( serialLabel );
		closeButtons.add( closeButton );
		nameFields.add( nameField );
		
		panel.add( newPanel );
		panel.doLayout();
	}
	
	void printCommForms( String [] msgs, JPanel panel )
	{
		panel.setVisible(false);
		panel.removeAll();
		commPanels.clear();
		commFields.clear();
		for( int i=0; i<msgs.length; i++ )
		{
			printCommForm( msgs[i],panel );
		}
		panel.setVisible(true);
	}
	
	void printCommForm( String msg, JPanel panel )
	{
		final JPanel newPanel = new JPanel();
		newPanel.setOpaque(false);
		GroupLayout layout = new GroupLayout(newPanel);
		newPanel.setLayout(layout);
		
			JTextField newField = new JTextField(msg);
			doSetSize(newField,320,30);
			newField.setCaretPosition(0);
			JButton delButton = new JButton();
			delButton.setBackground(Color.RED);
			delButton.setFocusable(false);
			delButton.addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent ae)
					{
						newPanel.setVisible(false);
					}
				});
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addComponent(newField)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(delButton));
		layout.setVerticalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(newField)
				.addComponent(delButton));
		
		panel.add(newPanel);
		commPanels.add(newPanel);
		commFields.add(newField);
		
	}
	
	void clearApplicationForm( JPanel panel )
	{
		panel.setVisible( false );
		panels.clear();
		serialLabels.clear();
		closeButtons.clear();
		nameFields.clear();
		defaultButtons.clear();
		referredButtons.clear();
		deferredButtons.clear();
		refBoxes.clear();
		recFields.clear();
		daySpins.clear();
		monthSpins.clear();
		yearSpins.clear();
		nextTimeButtons.clear();
		waitButtons.clear();
		panel.removeAll();
		panel.setVisible( true );
	}
	
	void doSetSize( Component c, int x, int y )
	{
		c.setPreferredSize( new Dimension(x,y) );
		c.setMinimumSize( new Dimension(x,y) );
		c.setMaximumSize( new Dimension(x,y) );
		c.setSize( new Dimension(x,y) );
	}
	
	void doPimpLink( JButton c )
	{
		c.setCursor( new Cursor(Cursor.HAND_CURSOR) );
		c.setForeground( LINK_FG );
		c.setFont( LINK_FONT_PLAIN );
		doSetSize( c, c.getFontMetrics(c.getFont()).stringWidth( c.getText() )+40, 25 );
		c.setHorizontalTextPosition( JButton.CENTER );
		c.setVerticalTextPosition( JButton.CENTER );
		c.setHorizontalAlignment( JButton.CENTER );
		c.setVerticalAlignment( JButton.CENTER );
		c.setBorderPainted( false );
		c.addMouseListener(
			new MouseAdapter()
			{
				public void mouseEntered( MouseEvent me)
				{
					((JButton)me.getSource()).setForeground( OVERLAY_COLOR );
				}
				
				public void mouseExited( MouseEvent me)
				{
					((JButton)me.getSource()).setForeground( LINK_FG );
				}
				
				public void mousePressed( MouseEvent me)
				{
					((JButton)me.getSource()).setForeground( Color.RED );
				}
				
				public void mouseReleased( MouseEvent me)
				{
					((JButton)me.getSource()).setForeground( OVERLAY_COLOR );
				}
				
			});
	}
	
	void refreshRefsList()
	{
		int prevList = refsList.getSelectedIndex()>0?refsList.getSelectedIndex():0;
		refsList.setListData( dataModel.getRefereeNames() );
		editEditRefCombo.removeAllItems();
		
		if( dataModel.countCards() > 0 )
		{
			prevList = dataModel.countCards() > prevList?prevList:0;
			
			RefereeCard  ref = dataModel.getCard(prevList);
			cardNameLabel.setText(ref.getName());
			cardDesignationLabel.setText(ref.getDesignation());
			cardContactLabel.setText(ref.getContact());
			cardAddressLabel.setText(ref.getAddress());
			refsList.setSelectedIndex(prevList);
		}
		else
		{
			cardNameLabel.setText("");
			cardDesignationLabel.setText("");
			cardContactLabel.setText("");
			cardAddressLabel.setText("");
		}
		
		for( String s : dataModel.getRefereeNames() )
			editEditRefCombo.addItem( s );
		if( editEditRefCombo.getItemCount() > 0 )
			editEditRefCombo.setSelectedIndex(0);
		
		String [] refs = dataModel.getRefereeNames();
		for( int i=0; i<refBoxes.size(); i++ )
		{
			int prevIndex = refBoxes.get(i).getSelectedIndex()>0?refBoxes.get(i).getSelectedIndex():0;
			refBoxes.get(i).removeAllItems();
			for( String s : refs )
				refBoxes.get(i).addItem(s);
			if(dataModel.countCards() > 0 )
				refBoxes.get(i).setSelectedIndex(dataModel.countCards() > prevList?prevList:0 );
		}
		
	}
	
	void refreshActiveList()
	{
		int prevCombo = selectActiveCombo.getSelectedIndex();
		int prevCombo2 = editSelectCombo.getSelectedIndex();
		int prevCombo3 = manageDeleteSelectCombo.getSelectedIndex();
		
		selectActiveCombo.removeAllItems();
		editSelectCombo.removeAllItems();
		manageDeleteSelectCombo.removeAllItems();
		
		
		for( String s : getAbridgedListNames() )
		{
			selectActiveCombo.addItem( s );
			editSelectCombo.addItem( s );		
			manageDeleteSelectCombo.addItem( s );		
		}
		
		if( getAbridgedListNames().length > 1 )
		{
			selectActiveCombo.setSelectedIndex(getAbridgedListNames().length>prevCombo?prevCombo:0);
			editSelectCombo.setSelectedIndex(getAbridgedListNames().length>prevCombo2?prevCombo2:0);
			manageDeleteSelectCombo.setSelectedIndex(getAbridgedListNames().length>prevCombo3?prevCombo3:0);
		}
		else
		{
			selectActiveCombo.setSelectedIndex(0);
			editSelectCombo.setSelectedIndex(0);
			manageDeleteSelectCombo.setSelectedIndex(0);
		}
	}
	
	void addCard( String name, String designation, String contact, String address )
	{
		dataModel.addCard( new RefereeCard( name, designation, contact, address ) );
		doPersistModel();
		refreshRefsList();
	}
	
	void updateCard( int index, String name, String designation, String contact, String address )
	{
		dataModel.updateCard( index, name, designation, contact, address );
		// dataModel.updateCard( index, new RefereeCard( name, designation, contact, address ) );
		doPersistModel();
		refreshRefsList();
	}
	
	void deleteCard( int index )
	{
		dataModel.removeCard( index );
		doPersistModel();
		refreshRefsList();
	}
	
	RefereeCard getCard( int index )
	{
		return dataModel.getCard( index );
	}
	
	public String getRefName( int index )
	{
		return dataModel.getCard(index).getName();
	}
	
	public String[] getRefNames()
	{
		return dataModel.getRefereeNames();
	}
	
	public String[] getListNames()
	{
		return dataModel.getListNames();
	}
	
	public String[] getAbridgedListNames()
	{
		String [] ret = new String[dataModel.getListNames().length+1];
		for( int i=0; i<dataModel.getListNames().length; i++)
		{
			ret[i+1] = dataModel.getListNames()[i];
		}
		ret[0] = "-Please select-";
		return ret;
	}
	
	String getDefaultListTitle()
	{
		return dataModel.getDefaultListTitle(MODE);
	}
	
	String getDateString( Calendar now )
	{
		return dataModel.getDateString( now );
	}
	
	String getStatusString( Application.Status status )
	{
		return dataModel.getStatusString( status );
	}
	
	class LinkButton extends JButton
	{
		String caption = "button 1";
		Color fg;
		
		public LinkButton( String cap )
		{
			caption = cap.toUpperCase();
			setText(caption);
			fg = Color.WHITE;
			
			setCursor( new Cursor(Cursor.HAND_CURSOR) );
			setFont( LINK_FONT_PLAIN );
			
			// doSetSize( this, getFontMetrics(getFont()).stringWidth( caption )+40, 25 );
			int stringWidth = getFontMetrics(getFont()).stringWidth( caption );
			int stringHeight = getFontMetrics(getFont()).getHeight();
			doSetSize( this, stringWidth+28,stringHeight+5);
			addMouseListener(
				new MouseAdapter()
				{
					public void mouseEntered( MouseEvent me)
					{
						fg = OVERLAY_COLOR;
						repaint();
					}
					
					public void mouseExited( MouseEvent me)
					{
						fg = LINK_FG;
						repaint();
					}
					
					public void mousePressed( MouseEvent me)
					{
						fg = Color.RED;
						repaint();
					}
					
					public void mouseReleased( MouseEvent me)
					{
						fg = OVERLAY_COLOR;
						repaint();
					}
					
				});
			repaint();
		}
		
		@Override
		public void paintComponent( Graphics g )
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(fg);
			int stringWidth = getFontMetrics(getFont()).stringWidth( caption );
			int stringHeight = getFontMetrics(getFont()).getHeight();
			g2.drawString("[ "+(getText().length()>1?getText():caption) +" ]",10,15);
		}
	}
	
	class HeaderPanel extends JPanel
	{
		String subText = "";
		String menuHeader = "";
		boolean doSub = false;
		
		public HeaderPanel( String menuHeader )
		{
			super();
			this.menuHeader = menuHeader;
			repaint();
		}
		
		public void paintComponent( Graphics g )
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			
			g.setColor( OVERLAY_COLOR );
			g.fillRect( 15,15,390,55 );
			g.drawImage( COMPANY_LOGO.getImage(), 0,0,55,55, this );
			g.setFont( HEADER_FONT );
			g.setColor( Color.WHITE );
			g.drawString( menuHeader, 75, 45 );
			if( doSub )
			{
				g.setColor( FIELD_BG );
				g.setFont( SUB_HEADER_FONT );
				int height = g.getFontMetrics().getHeight();
				int stringWidth = g.getFontMetrics().stringWidth(subText);
				g.drawString( subText, 400-stringWidth, 75-height );			
			}
		}
		
		public void redefine( String menuHeader )
		{
			doSub = false;
			this.menuHeader = menuHeader;
			repaint();
		}
		
		public void redefine( String menuHeader, String subText )
		{
			doSub = true;
			this.menuHeader = menuHeader;
			this.subText = subText;
			repaint();
		}
	}
	
	class ObserverDelegate extends Observable
	{
		public void update( Object update )
		{
			setChanged();
			notifyObservers( update );
		}
	}
	
	void changeListSpeed( int factor )
	{
		dataModel.setListRate(dataModel.getListRate()+factor);
		doPersistModel();
	}
	
	void changeMessageSpeed(int factor )
	{
		dataModel.setMessageRate(dataModel.getMessageRate()+factor);
		doPersistModel();
	}
	
	Color STATUS_COLOR = new Color(0,0,0,50);
	public void updateSignal( Mode mode )
	{
		signalTimer.start();
		switch( mode )
		{
			case GO:
			case OTHERS:
			case REAL:
			{
				STATUS_COLOR = new Color(0,0,255,50);
			}break;
			case CLOCK:
			case IDLE:
			{
				STATUS_COLOR = new Color(255,255,0,50);
			}break;
			case ON:
			{
				if( !onRadio.isSelected() )
					onRadio.setSelected(true);
				STATUS_COLOR = new Color(0,255,0,50);
			}break;
			case OFF:
			{
				if( !offRadio.isSelected() )
					offRadio.setSelected(true);
				STATUS_COLOR = new Color(0,0,0,50);
			}break;
		}
	}
	
	class SignalHandler implements ActionListener
	{
		int counter;
		public SignalHandler()
		{
			counter = 0;
		}
		
		public void actionPerformed( ActionEvent ae )
		{
			if( counter%3 == 0 )
			{
				statusButton.setBackground( STATUS_COLOR );
				statusButton2.setBackground( Color.BLACK );
				statusButton3.setBackground( Color.BLACK );
			}
			else
			if( counter%3 == 1 )
			{
				statusButton.setBackground( Color.BLACK );
				statusButton2.setBackground( STATUS_COLOR );
				statusButton3.setBackground( Color.BLACK );
			}
			else
			{
				statusButton.setBackground( Color.BLACK );
				statusButton2.setBackground( Color.BLACK );
				statusButton3.setBackground( STATUS_COLOR );
			}
			
			counter++;
			
			if( counter >= 2000 )
				counter = 1;
		}
	}
	
	void doCreateNewList( String name )
	{
		dataModel.createScheduleList(name);
	}
	
	void commitList()
	{
		dataModel.commitList();
		doPersistModel();
		////////////System.out.println("List committed");
		refreshActiveList();
	}
	
	void deleteList( int index )
	{
		dataModel.deleteList( index );
		doPersistModel();
		refreshActiveList();
	}
	
	int countApplications()
	{
		return dataModel.getList().countApplications();
	}
	
	void addApplication( Application newApplication )
	{
		dataModel.addApplication( newApplication );
	}
	
	void updateApplication( int listIndex, int appIndex, Application update )
	{
		dataModel.updateApplication( listIndex, appIndex, update );
		doPersistModel();
	}
	
	void insertApplication( int listIndex, Application insert )
	{
		dataModel.insertApplication( listIndex, insert );
		doPersistModel();
	}
	
	void appendMessage( String msg )
	{
		dataModel.addMessage(msg);
		doPersistModel();
	}
	
	void setMessage( String msg )
	{
		dataModel.clearMessages();
		if(msg.trim().length()>0)
			dataModel.addMessage( msg );
		doPersistModel();
	}
	
	void clearMessages()
	{
		dataModel.clearMessages();
		doPersistModel();
	}
	
	String [] getMessages()
	{
		String [] ret = new String[dataModel.countMessages()];
		for(int i=0; i<dataModel.countMessages(); i++)
			ret[i] = dataModel.getMessage(i);
		return ret;
	}
	
	int countMessages()
	{
		return dataModel.countMessages();
	}
	
	void doPersistModel()
	{
		publish( dataModel );
	}
	
	void publish( Object update )
	{
		observerDelegate.update( update );
	}
	
	void setActiveList( int index )
	{
		dataModel.setActiveList( index );
	}
	
	String [] getListApplicationNames( int index )
	{
		return dataModel.getApplicationNames(index);
	}
	
	String [] getAbridgedListApplicationNames( int index )
	{
		int len = dataModel.getApplicationNames(index).length;
		String [] names = new String[len];
		
		for( int i=0; i<dataModel.getApplicationNames(index).length; i++ )
		{
			String name = dataModel.getApplicationNames(index)[i];
			String sn = (i+1)+".";
			
			while(sn.length()-1<(""+len).length())
				sn+=" ";
			
			sn+=" "+name;
			names[i] = sn;
		}
		return names;
	}
	
	Application getListApplication( int listIndex, int appIndex )
	{
		return dataModel.getApplication( listIndex, appIndex );
	}
	
	void deleteApplication( int listIndex, int appIndex )
	{
		dataModel.deleteApplication( listIndex, appIndex );
		doPersistModel();
	}
	
	void doPimpTableHeader( JLabel c )
	{
		c.setForeground( LINK_FG );
		c.setFont( LINK_FONT_PLAIN );
		// doSetSize( c, c.getFontMetrics(c.getFont()).stringWidth( c.getText() )+35, 20 );
		c.setHorizontalTextPosition( JButton.CENTER );
		c.setVerticalTextPosition( JButton.CENTER );
		c.setHorizontalAlignment( JButton.CENTER );
		c.setVerticalAlignment( JButton.CENTER );
	}

	void doPimpMiniLabel( Component c )
	{
		c.setForeground( Color.WHITE );
		c.setFont( LABEL_FONT_MINI_1 );
	}
	
	void doPimpHeader( Graphics g, String menuHeader )
	{
		g.setColor( OVERLAY_COLOR );
		g.fillRect( 15,15,390,55 );
		g.drawImage( COMPANY_LOGO.getImage(), 0,0,55,55, this );
		g.setFont( new Font( "Maiandra GD", Font.BOLD, 15 ) );
		g.setColor( Color.WHITE );
		g.drawString( menuHeader, 75, 45 );
	}
	
	void doPimpHeader( Graphics g, String menuHeader, String subText )
	{		
		g.setColor( OVERLAY_COLOR );
		g.fillRect( 15,15,390,55 );
		g.drawImage( COMPANY_LOGO.getImage(), 0,0,55,55, this );
		g.setFont( HEADER_FONT );
		g.setColor( Color.WHITE );
		g.drawString( menuHeader, 75, 45 );
		g.setColor( FIELD_BG );
		g.setFont( SUB_HEADER_FONT );
		int height = g.getFontMetrics().getHeight();
		int stringWidth = g.getFontMetrics().stringWidth(subText);
		g.drawString( subText, 400-stringWidth, 70-height );
	
	}
	
	class ToggleButtonListener implements ActionListener
	{
		int index = -1;
		public ToggleButtonListener( int index )
		{
			this.index = index;
		}
		
		public void actionPerformed( ActionEvent ae )
		{
			switch( index )
			{
				case 0:
				{
					if( refLinkPanel.isVisible() )
					{											
						refLinkPanel.setVisible( false );
						blankComponent.setVisible( true );
						refLinkContainer.add( blankComponent,BorderLayout.CENTER );
						refLinkContainer.revalidate();
					}
					else
					{
						refLinkPanel.setVisible( true );
						blankComponent.setVisible( false );
						refLinkContainer.add( refLinkPanel,BorderLayout.CENTER );
						refLinkContainer.revalidate();
					}
				}break;
				case 1:
				{
					if( selectLinkContainer.isVisible() )
					{											
						selectLinkContainer.setVisible( false );
					}
					else
					{
						selectLinkContainer.setVisible( true );
						manageLinkContainer.setVisible( false );
						createLinkContainer.setVisible( false );
					}
				}break;
				case 2:
				{
					if( createLinkContainer.isVisible() )
					{											
						createLinkContainer.setVisible( false );
					}
					else
					{
						createLinkContainer.setVisible( true );
						selectLinkContainer.setVisible( false );
						manageLinkContainer.setVisible( false );
					}
				}break;
				case 3:
				{
					if( manageLinkContainer.isVisible() )
					{											
						manageLinkContainer.setVisible( false );
					}
					else
					{
						manageLinkContainer.setVisible( true );
						createLinkContainer.setVisible( false );
						selectLinkContainer.setVisible( false );
					}
				}break;
			}
		}
		
	}
	
	class ExitEnterListener extends MouseAdapter
	{
		int radix=0;
		
		public ExitEnterListener( int radix )
		{
			this.radix = radix;
		}
		
		public void mouseEntered( MouseEvent me)
		{
			switch( radix )
			{
				case 0: //sel
				{
					selectLinkContainer.setVisible( true );
					manageLinkContainer.setVisible( false );
					createLinkContainer.setVisible( false );
					// refLinkContainer.setVisible( false );
				}break;
				case 1: //create
				{
					createLinkContainer.setVisible( true );
					selectLinkContainer.setVisible( false );
					manageLinkContainer.setVisible( false );
					// refLinkContainer.setVisible( false );
				}break;
				case 2:	//manage
				{
					manageLinkContainer.setVisible( true );
					selectLinkContainer.setVisible( false );
					createLinkContainer.setVisible( false );
					// refLinkContainer.setVisible( false );
				}break;
				case 3:	//refs
				{
					refLinkPanel.setVisible( true );
					// selectLinkContainer.setVisible( false );
					// createLinkContainer.setVisible( false );
					// manageLinkContainer.setVisible( false );
				}break;
			} 
		}
		
		public void mouseExited( MouseEvent me )
		{
			switch( radix )
			{
				case 0: //sel
				{
					selectLinkContainer.setVisible( false );
				}break;
				case 1: //create
				{
					createLinkContainer.setVisible( false );
				}break;
				case 2:	//manage
				{
					manageLinkContainer.setVisible( false );
				}break;
				case 3:	//manage
				{
					refLinkPanel.setVisible( false );
				}break;
			}
		}
	}
	
	class AlertHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			/* if( commField.getText().trim().length() > 0 )
				publishPostBack( AirUID.BACKSTAGE_ALERT );
			 */
		}
	}
	
	/*void rcvConfig( Config newConfig )
	{
		 statusLabel.setText( "connected" );
		statusLabel.setForeground( Color.GREEN );
		statusIconLabel.setIcon( _1_IMG );

		switch( newConfig.getAirUID() )
		{
			case EXIT:
			{
				try
				{
					Runnable disconnect = new ClientDisconnector( clientManager );
					Future disconnecting = threadExecutor.submit( disconnect );
					disconnecting.get();				
				}
				catch( Exception e )
				{
					e.printStackTrace();
					debugMsg( "Admin EXIT",e.getMessage() );
				}
				ucorpLabel.setText(PROGRAM_TITLE);
				addressField.setEnabled( false );
				commField.setEnabled( false );
				connectButton.setEnabled( false );
				commButton.setEnabled( false );
				selActiveCombo.setEnabled( true );
				lockInButton.setText( "LOCK IN" );
				lockInButton.setEnabled( true );
				statusIconLabel.setIcon( _0_IMG );
				statusLabel.setText( "Not Connected" );
				statusLabel.setForeground( Color.RED );
				viewLabel.setText( "ADMIN SYSTEM" );
			}break;
			case WINNER:
			{
			}break;
			case DISQUALIFIED:
			{
			}break;
			case REVIEW:
			{
			}break;
			case FAFF_WINNER:
			{
			}break;
			case FAFF_ANSWER:
			{
			}break;
			case ANSWER:
			{
			}break;
			case QUESTION_SELECTED_OPTION:
			{
			}break;
			case QUESTION_DISQUALIFIED:
			{
			}break;
			case QUESTION:
			{
			}break;
			case QUESTION_INTERLUDE:
			{
			}break;
			case PLAY_INTERLUDE:
			{
			}break;
			case RULE_INFO:
			{
			}break;
			case RULES_INTERLUDE:
			{
			}break;
			case CONTESTANT_INTRO:
			{
			}break;
			case CONTESTANTS_INTERLUDE:
			{
			}break;
			case ROUND_INTERLUDE:
			{
			}break;
			case WELCOME:
			{
			}break;
			case CONNECTION_INDEX:
			{
				publishPostBack( AirUID.ACTIVE_QUIZ );
				viewLabel.setText( "BACKSTAGE VIEW" );
				connected();
			}break;
			case CONNECTION_REFUSED:
			{
				Config wrongConfig = new Config();
				wrongConfig.setAirUID( AirUID.EXIT );
				rcvConfig( wrongConfig );
				debugMsg("Connection Refused","Sorry, cannot connect at this time");
				return;
			}
		}
		 
	}*/
	
	/* void publishPostBack( AirUID airUID )
	{
		POSTBACK.setAirUID( airUID );
		POSTBACK.setSenderIndex( -1 );
		
		switch( airUID )
		{
			case ACTIVE_QUIZ:
			{
				POSTBACK.setActiveQuiz( TEMP_QUIZ );
				ucorpLabel.setText( TEMP_QUIZ.getTitle().toUpperCase() );
			}break;
			case BACKSTAGE_ALERT:
			{
				POSTBACK.setAlert( commField.getText().toUpperCase().trim() );
				commField.setText("");
			}break;
		}
		sendPostBack(); 
	}*/
	
	void sendPostBack()
	{
		// clientManager.sendToken( POSTBACK );
	}
	
	// @override interface TokenReceiver
	public void socketFound()
	{
		// statusLabel.setText( " ... waiting for presenter" );
	}
	
	// @override interface TokenReceiver
	public void connected()
	{
		/* statusLabel.setText( "connected" );
		statusLabel.setForeground( Color.GREEN );
		statusIconLabel.setIcon( _1_IMG );
		doSetSize( statusIconLabel, statusIconLabel.getIcon().getIconWidth(), statusIconLabel.getIcon().getIconHeight() );
		selActiveCombo.setEnabled( false );
		lockInButton.setEnabled( false );
		commField.setEnabled( true );
		commButton.setEnabled( true ); */
	}
	
	// @override interface TokenReceiver
	/* public void tokenReceived( AirToken token )
	{
		// if( token instanceof Config )
			// rcvConfig( (Config)token );
	} */
	
	// @override interface TokenReceiver
	public void disconnected( Socket s )
	{
		/* statusLabel.setText( " ... re-connecting to "+SERVER_ADDRESS );
		statusLabel.setForeground( Color.YELLOW );
		
		statusIconLabel.setIcon( WAIT_IMG );
		doSetSize( statusIconLabel, statusIconLabel.getIcon().getIconWidth(), statusIconLabel.getIcon().getIconHeight() );
		
		clientManager.setConnected( false );
		threadExecutor.execute( serverBlocker ); */
	}
	
	void debugMsg( String title, String msg )
	{
		javax.swing.JOptionPane.showMessageDialog( null, msg, title, JOptionPane.INFORMATION_MESSAGE );
	}
	
}