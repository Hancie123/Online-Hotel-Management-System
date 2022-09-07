package reception;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import billing.BillingGUI;
import customer.Customer_Details;
import customer.Customer_Management;
import libs.BookingLibs;
import libs.BookingLibs3;
import libs.Global;
import libs.JDBCBooking;
import libs.JDBCRoom;
import libs.PanelRound;
import libs.Room;
import room.AvailableRooms;
import room.Room_Management;
import ui.Login;

public class Reception_Dashboard implements MouseListener {

	protected static final Color COLOR = null;

	JFrame frame;
	JPanel panel, center, north, south;
	JLabel startDate, checkOut, image, roomNo, days, titlelbl, payment, roomID, resultlbl, roomType, floor, title1,
			customerID, customerName, availableroomlbl, manageroomlbl, managecustomerlbl, manageservicesbl,
			managebillinglbl;

	JTextField roomtxt, roomIDTxt, CustomerNametxt, bookingsearch, bookingtxt, searchtxt;
	JComboBox daysCombo, roomTypeCombo, floorCombo, paymentMethodCombo;
	JButton check, checkbtn;
	TableRowSorter sorter;
	JTable table, table1;
	DefaultTableModel dtm, model;
	JTextField bookingidtxt, customeridtxt, roomidtxt;
	JDateChooser checkin, checkout1;
	JComboBox bookingtypetext;
	Object[] columnsName;
	ArrayList<BookingLibs3> a5;

	public Reception_Dashboard() {

		// *******************Main Frame*********************
		frame = new JFrame();
		frame.setBackground(new Color(102, 204, 255));
		frame.setTitle("Luton Hotel Booking System");
		frame.setResizable(true);
		frame.setSize(950, 500);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hotel.png")));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new BorderLayout());

		// -------------------North Panel--------------------
		JPanel northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setBackground(new Color(0, 0, 0));
		northPanel.setPreferredSize(new Dimension(100, 70));
		frame.add(northPanel, BorderLayout.NORTH);

		titlelbl = new JLabel("Reception Dashboard", SwingConstants.CENTER);
		titlelbl.setFont(new Font("Tahoma", Font.BOLD, 40));
		titlelbl.setForeground(Color.white);
		titlelbl.setBounds(500, 20, 450, 40);
		northPanel.add(titlelbl);

		JLabel welcomelbl = new JLabel("Welcome: ");
		welcomelbl.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		welcomelbl.setForeground(Color.white);
		welcomelbl.setBounds(1300, 20, 150, 35);
		northPanel.add(welcomelbl);

		JLabel welcomelbl1 = new JLabel();
		welcomelbl1.setText(Global.registrationUser.getUsername());
		welcomelbl1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		welcomelbl1.setForeground(Color.white);
		welcomelbl1.setBounds(1390, 20, 150, 35);
		northPanel.add(welcomelbl1);

		// **************************Center Panel******************
		center = new JPanel();
		center.setLayout(new BorderLayout());
		frame.add(center, BorderLayout.CENTER);

		// *********************Center North Panel******************
		JPanel northPanel1 = new JPanel();
		northPanel1.setBackground(new Color(255, 255, 255));
		northPanel1.setLayout(null);
		northPanel1.setPreferredSize(new Dimension(100, 440));
		center.add(northPanel1, BorderLayout.NORTH);

		JLabel northTitle = new JLabel("SERVICES", SwingConstants.CENTER);
		northTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		northTitle.setBounds(300, 10, 450, 30);
		northTitle.setForeground(Color.white);
		northTitle.setBackground(new Color(0, 0, 0));
		northTitle.setOpaque(true);
		northPanel1.add(northTitle);

		// *****************Available Room Panel***************
		PanelRound availableroomPanel = new PanelRound();
		availableroomPanel.setRoundBottomRight(100);
		availableroomPanel.setRoundBottomLeft(100);
		availableroomPanel.setRoundTopLeft(100);
		availableroomPanel.setRoundTopRight(100);
		availableroomPanel.setLayout(null);
		availableroomPanel.setBackground(new Color(40, 199, 250));
		availableroomPanel.setBounds(40, 60, 200, 180);
		northPanel1.add(availableroomPanel);

		// *******************Image for available room***************8
		JLabel img = new JLabel();
		img.setIcon(new javax.swing.ImageIcon(getClass().getResource("interior-design.png")));
		img.setBounds(25, 10, 128, 128);
		img.setFont(new Font("Tahoma", Font.BOLD, 18));
		img.setForeground(Color.white);
		availableroomPanel.add(img);

		// *******************Label for available room***************
		availableroomlbl = new JLabel("Manage Rooms");
		availableroomlbl.setBounds(30, 130, 200, 30);
		availableroomlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		availableroomlbl.addMouseListener(this);
		availableroomPanel.add(availableroomlbl);
		availableroomlbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

				availableroomlbl.setForeground(new Color(147, 250, 165));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				availableroomlbl.setForeground(COLOR);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
						new Room_Management();
			}

		});

		// *****************Customer Panel***************
		PanelRound customerPanel = new PanelRound();
		customerPanel.setRoundBottomRight(100);
		customerPanel.setRoundBottomLeft(100);
		customerPanel.setRoundTopLeft(100);
		customerPanel.setRoundTopRight(100);
		customerPanel.setLayout(null);
		customerPanel.setBackground(new Color(40, 199, 250));
		customerPanel.setBounds(290, 60, 200, 180);
		northPanel1.add(customerPanel);

		// *******************Image for customer***************8
		JLabel img1 = new JLabel();
		img1.setIcon(new javax.swing.ImageIcon(getClass().getResource("rating.png")));
		img1.setBounds(35, 10, 128, 128);
		img1.setFont(new Font("Tahoma", Font.BOLD, 18));
		img1.setForeground(Color.white);
		customerPanel.add(img1);

		// *******************Label for Customer Panel***************
		JLabel customerLabel = new JLabel("Manage Customer");
		customerLabel.setBounds(20, 130, 200, 30);
		customerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		customerLabel.addMouseListener(this);
		customerPanel.add(customerLabel);
		customerLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

				customerLabel.setForeground(new Color(147, 250, 165));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				customerLabel.setForeground(COLOR);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
								new Customer_Management();
			}

		});

		// ***************** Customer Panel***************
		PanelRound corporatePanel = new PanelRound();
		corporatePanel.setRoundBottomRight(100);
		corporatePanel.setRoundBottomLeft(100);
		corporatePanel.setRoundTopLeft(100);
		corporatePanel.setRoundTopRight(100);
		corporatePanel.setLayout(null);
		corporatePanel.setBackground(new Color(40, 199, 250));
		corporatePanel.setBounds(540, 60, 200, 180);
		northPanel1.add(corporatePanel);

		// *******************Image for  Customer***************8
		JLabel img2 = new JLabel();
		img2.setIcon(new javax.swing.ImageIcon(getClass().getResource("teamwork.png")));
		img2.setBounds(35, 10, 128, 128);
		img2.setFont(new Font("Tahoma", Font.BOLD, 18));
		img2.setForeground(Color.white);
		corporatePanel.add(img2);

		// *******************Label for  Customer Panel***************
		JLabel corporateLabel = new JLabel("Search Customers");
		corporateLabel.setBounds(20, 130, 200, 30);
		corporateLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		corporateLabel.addMouseListener(this);
		corporatePanel.add(corporateLabel);
		corporateLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

				corporateLabel.setForeground(new Color(147, 250, 165));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				corporateLabel.setForeground(COLOR);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
										new Customer_Details();
			}

		});

		// *****************Check Room Panel***************
		PanelRound servicesPanel = new PanelRound();
		servicesPanel.setRoundBottomRight(100);
		servicesPanel.setRoundBottomLeft(100);
		servicesPanel.setRoundTopLeft(100);
		servicesPanel.setRoundTopRight(100);
		servicesPanel.setLayout(null);
		servicesPanel.setBackground(new Color(40, 199, 250));
		servicesPanel.setBounds(790, 60, 200, 180);
		northPanel1.add(servicesPanel);

		// *******************Image for Check Room***************8
		JLabel img3 = new JLabel();
		img3.setIcon(new javax.swing.ImageIcon(getClass().getResource("room.png")));
		img3.setBounds(35, 10, 128, 128);
		img3.setFont(new Font("Tahoma", Font.BOLD, 18));
		img3.setForeground(Color.white);
		servicesPanel.add(img3);

		// *******************Label for Check Room Panel***************
		JLabel servicesLabel = new JLabel("Check Rooms");
		servicesLabel.setBounds(40, 130, 200, 30);
		servicesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		servicesLabel.addMouseListener(this);
		servicesPanel.add(servicesLabel);
		servicesLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

				servicesLabel.setForeground(new Color(147, 250, 165));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				servicesLabel.setForeground(COLOR);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
										new AvailableRooms();
			}

		});
		
		
		
		// *****************Check-In Customer Panel***************
				PanelRound checkPanel = new PanelRound();
				checkPanel.setRoundBottomRight(100);
				checkPanel.setRoundBottomLeft(100);
				checkPanel.setRoundTopLeft(100);
				checkPanel.setRoundTopRight(100);
				checkPanel.setLayout(null);
				checkPanel.setBackground(new Color(40, 199, 250));
				checkPanel.setBounds(40, 250, 200, 180);
				northPanel1.add(checkPanel);

				// *******************Image for Check-In Customer***************8
				JLabel img4 = new JLabel();
				img4.setIcon(new javax.swing.ImageIcon(getClass().getResource("check-in-desk.png")));
				img4.setBounds(35, 10, 128, 128);
				img4.setFont(new Font("Tahoma", Font.BOLD, 18));
				img4.setForeground(Color.white);
				checkPanel.add(img4);

				// *******************Label for Check-In Customer Panel***************
				JLabel checkLabel = new JLabel("Check-In");
				checkLabel.setBounds(60, 135, 200, 30);
				checkLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				checkLabel.addMouseListener(this);
				checkPanel.add(checkLabel);
				checkLabel.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {

						checkLabel.setForeground(new Color(147, 250, 165));

					}

					@Override
					public void mouseExited(MouseEvent e) {

						checkLabel.setForeground(COLOR);
					}

					@Override
					public void mouseClicked(MouseEvent e) {
	             new Customer_CheckIn();
					}

				});
				
				
				// *****************Billing Customer Panel***************
				PanelRound customerBillingPanel = new PanelRound();
				customerBillingPanel.setRoundBottomRight(100);
				customerBillingPanel.setRoundBottomLeft(100);
				customerBillingPanel.setRoundTopLeft(100);
				customerBillingPanel.setRoundTopRight(100);
				customerBillingPanel.setLayout(null);
				customerBillingPanel.setBackground(new Color(40, 199, 250));
				customerBillingPanel.setBounds(290, 250, 200, 180);
				northPanel1.add(customerBillingPanel);

				// *******************Image for Billing Customer***************8
				JLabel img5 = new JLabel();
				img5.setIcon(new javax.swing.ImageIcon(getClass().getResource("bill.png")));
				img5.setBounds(35, 10, 128, 128);
				img5.setFont(new Font("Tahoma", Font.BOLD, 18));
				img5.setForeground(Color.white);
				customerBillingPanel.add(img5);

				// *******************Label for Billing Customer Panel***************
				JLabel customerBillingLabel = new JLabel("Billing");
				customerBillingLabel.setBounds(75, 135, 200, 30);
				customerBillingLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				customerBillingLabel.addMouseListener(this);
				customerBillingPanel.add(customerBillingLabel);
				customerBillingLabel.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {

						customerBillingLabel.setForeground(new Color(147, 250, 165));

					}

					@Override
					public void mouseExited(MouseEvent e) {

						customerBillingLabel.setForeground(COLOR);
					}

					@Override
					public void mouseClicked(MouseEvent e) {
	             new BillingGUI();
					}

				});

		// ********************Center Booking Table*********************
		columnsName = new Object[8];
		columnsName[0] = "Customer ID";
		columnsName[1] = "Name";
		columnsName[2] = "Booking ID";
		columnsName[3] = "Check-In";
		columnsName[4] = "Check-Out";
		columnsName[5] = "Room ID";
		columnsName[6] = "Room Type";
		columnsName[7] = "Room Status";

		table1 = new JTable(model);

		model = (DefaultTableModel) table1.getModel();
		model.setColumnIdentifiers(columnsName);

		updateTable();
		JScrollPane scroll1 = new JScrollPane(table1);
		scroll1.setPreferredSize(new Dimension(200, 250));
		center.add(scroll1, BorderLayout.SOUTH);

//--------------------West Panel----------------------------

		panel = new JPanel();
		panel.setBackground(new Color(61, 108, 185));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 100));
		frame.add(panel, BorderLayout.WEST);

		checkbtn = new JButton("Check Room");
		checkbtn.setFocusable(false);
		checkbtn.setFont(new Font("Verdana", Font.BOLD, 25));
		checkbtn.setBounds(70, 550, 300, 35);
		checkbtn.setForeground(Color.white);
		checkbtn.setBackground(new Color(0,0,0));
		panel.add(checkbtn);
		checkbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new AvailableRooms();

			}

		});
		
		JSeparator sep = new JSeparator();
		sep.setBounds(10, 565, 425, 21);
		panel.add(sep);

		title1 = new JLabel("CHECK FOR BOOKING", JLabel.CENTER);
		title1.setFont(new Font("Tahoma", Font.BOLD, 25));
		title1.setForeground(Color.white);
		title1.setBounds(50, 20, 350, 35);
		panel.add(title1);

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(10, 50, 420, 21);
		panel.add(separator1);
		
		JLabel logoImg = new JLabel();
		logoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("booking.png")));
		logoImg.setBounds(160, 60, 128, 128);
		logoImg.setFont(new Font("Tahoma", Font.BOLD, 18));
		logoImg.setForeground(Color.white);
		panel.add(logoImg);

		JLabel bookinglbl = new JLabel("Booking ID:");
		bookinglbl.setBounds(20, 200, 200, 35);
		bookinglbl.setFont(new Font("Verdana", Font.PLAIN, 18));
		bookinglbl.setForeground(Color.white);
		panel.add(bookinglbl);

		bookingtxt = new JTextField();
		bookingtxt.setBorder(new MatteBorder(0, 0, 2, 0, Color.green));
		bookingtxt.setBounds(180, 200, 250, 35);
		bookingtxt.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel.add(bookingtxt);

		JLabel CheckInlbl = new JLabel("Arrival Date:");
		CheckInlbl.setBounds(20, 250, 200, 35);
		CheckInlbl.setFont(new Font("Verdana", Font.PLAIN, 18));
		CheckInlbl.setForeground(Color.white);
		panel.add(CheckInlbl);

		checkin = new JDateChooser();
		checkin.setBorder(new MatteBorder(0, 0, 2, 0, Color.green));
		checkin.setDateFormatString("yyyy-MM-dd");
		checkin.setBounds(180, 250, 250, 35);
		checkin.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel.add(checkin);

		JLabel CheckOutlbl = new JLabel("Departure Date:");
		CheckOutlbl.setBounds(20, 300, 200, 35);
		CheckOutlbl.setFont(new Font("Verdana", Font.PLAIN, 18));
		CheckOutlbl.setForeground(Color.white);
		panel.add(CheckOutlbl);

		checkout1 = new JDateChooser();
		checkout1.setDateFormatString("yyyy-MM-dd");
		checkout1.setBounds(180, 300, 250, 35);
		checkout1.setBorder(new MatteBorder(0, 0, 2, 0, Color.green));
		checkout1.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel.add(checkout1);

		JLabel roomidlbl = new JLabel("Room No:");
		roomidlbl.setBounds(20, 350, 200, 35);
		roomidlbl.setFont(new Font("Verdana", Font.PLAIN, 18));
		roomidlbl.setForeground(Color.white);
		panel.add(roomidlbl);

		roomidtxt = new JTextField();
		roomidtxt.setBounds(180, 350, 250, 35);
		roomidtxt.setBorder(new MatteBorder(0, 0, 2, 0, Color.green));
		roomidtxt.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel.add(roomidtxt);

		JLabel statuslbl = new JLabel("Status:");
		statuslbl.setBounds(20, 400, 200, 35);
		statuslbl.setFont(new Font("Verdana", Font.PLAIN, 18));
		statuslbl.setForeground(Color.white);
		panel.add(statuslbl);

		Object[] h5 = { "Booked", "Room Not Available" };
		bookingtypetext = new JComboBox(h5);
		bookingtypetext.setBounds(180, 400, 250, 35);
		bookingtypetext.setBorder(new MatteBorder(0, 0, 2, 0, Color.green));
		bookingtypetext.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel.add(bookingtypetext);

		

		check = new JButton("Confirm Booking");
		check.setFocusable(false);
		check.setFont(new Font("Tahoma", Font.BOLD, 20));
		check.setForeground(Color.WHITE);
		check.setBackground(new Color(0,0,0));
		check.setBounds(100, 465, 250, 35);
		panel.add(check);
		check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// *********Update Room Status***************
				Room room = new Room();

				int room_id1 = Integer.parseInt(roomidtxt.getText());

				room.setId(room_id1);
				room.setStatus("Booked");
				JDBCRoom jdbc1 = new JDBCRoom();
				boolean result1 = jdbc1.update1(room);

				// **************Update Customer Booking***********************
				int cusid = Integer.parseInt(bookingtxt.getText());
				String str1 = ((JTextField) checkin.getDateEditor().getUiComponent()).getText();
				String str2 = ((JTextField) checkout1.getDateEditor().getUiComponent()).getText();

				String bookingtype1 = bookingtypetext.getSelectedItem().toString();
				int room_id = Integer.parseInt(roomidtxt.getText());

				BookingLibs booking = new BookingLibs();

				booking.setBooking_ID(cusid);
				booking.setCheckIn(str1);
				booking.setCheckOut(str2);
				booking.setRoom_ID(room_id);
				booking.setBooking_Status(bookingtype1);

				JDBCBooking jdbc = new JDBCBooking();
				boolean result = jdbc.update(booking);

				if (result == true) {
					updateTable();
					ImageIcon i = new ImageIcon(getClass().getResource("hotel (1).png"));
					JOptionPane.showMessageDialog(null, "The booking is done successfully", "Room Booking", JOptionPane.WIDTH, i);
				}

			}

		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 480, 425, 21);
		panel.add(separator);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		

		menuBar.add(file);
		

		

		JMenuItem LogOut = new JMenuItem("Log Out");
		LogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() == LogOut) {
					frame.dispose();
					new Login();
				}
			}
		});
		file.add(LogOut);

		JMenuItem Exit = new JMenuItem("Exit");
		Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() == Exit) {
					System.exit(0);
				}
			}
		});
		file.add(Exit);

	
		

		

		frame.setVisible(true);

	}

	public void updateTable() {

		a5 = new JDBCBooking().ReceptionViewAllCustomer();
		model.setRowCount(0);
		for (BookingLibs3 bookingLibs : a5) {
			Object tmpRow[] = { bookingLibs.getCustomer_ID(), bookingLibs.getName(), bookingLibs.getBooking_ID(),
					bookingLibs.getCheckIn(), bookingLibs.getCheckOut(), bookingLibs.getRoom_ID(),
					bookingLibs.getBooking_Type(), bookingLibs.getBooking_Status()

			};

			model.addRow(tmpRow);
		}

		table1 = new JTable(model);
		JTableHeader t2 = table1.getTableHeader();
		t2.setBackground(Color.black);
		t2.setForeground(Color.white);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table1.setSelectionBackground(Color.black);
		table1.setSelectionForeground(Color.white);
		table1.setRowHeight(30);
		table1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
		table1.addMouseListener(this);
		sorter = new TableRowSorter<>(model);
		table1.setRowSorter(sorter);

	}

	public void mouseClicked(MouseEvent ae) {
		if (ae.getSource() == table1) {

			try {
				int h2 = table1.getSelectedRow();

				TableModel model = table1.getModel();

				String name = model.getValueAt(h2, 2).toString();
				bookingtxt.setText(name);

				Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(h2, 3));
				checkin.setDate(date);

				Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(h2, 4));
				checkout1.setDate(date2);

			} catch (Exception ex) {
				System.out.println("Erro" + ex.getMessage());
			}

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		

	}

	@Override
	public void mouseExited(MouseEvent e) {
		

	}

	public static void main(String[] args) {
		new Reception_Dashboard();

	}

}
