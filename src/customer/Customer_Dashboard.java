package customer;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;
import libs.BookingLibs;
import libs.BookingLibs2;
import libs.Global;
import libs.JDBCBooking;
import libs.PanelRound;
import ui.Login;


public class Customer_Dashboard implements ActionListener{
	JFrame frame;
	JDateChooser checkin, checkout1;
	JComboBox bookingtypetxt;
	JButton bookingbtn;
	JTextField customeridtxt;
	Object[] Column;
	DefaultTableModel model;
	JTable table;
	TableRowSorter sorter;
	JMenuBar menubar;
	JMenu Logout;
	
	public Customer_Dashboard() {
		
		frame=new JFrame("Customer Dashboard");
		frame.setSize(700,700);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hotel.png")));
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		frame.setLayout(new BorderLayout());
		
		
		menubar=new JMenuBar();
		frame.setJMenuBar(menubar);
		
		Logout=new JMenu("File");
		menubar.add(Logout);
			
		
		JMenuItem logout=new JMenuItem("Logout");
		Logout.add(logout);
		logout.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==logout) {
				frame.dispose();
				new Login();
			}
		}	
		});
		
		
		//------- North Panel--------
		JPanel north=new JPanel();
		north.setLayout(null);
		north.setPreferredSize(new Dimension(100,100));
		north.setBackground(new Color(0,0,0));
		frame.add(north, BorderLayout.NORTH);
		
		JLabel title =new JLabel("Customer Dashboard");
		title.setForeground(Color.white);
		title.setBounds(600,30,400,50);
		title.setFont(new Font("Verdana", Font.BOLD, 30));
		north.add(title);
		
		//------------Center Panel---------------
		JPanel center=new JPanel();
		center.setLayout(null);
		center.setPreferredSize(new Dimension(100,100));
		center.setBackground(new Color(243, 233, 210));
		frame.add(center, BorderLayout.CENTER);
		
		//-------------Profile Panel-----------------------
		PanelRound profilePanel = new PanelRound();
		profilePanel.setRoundBottomRight(100);
		profilePanel.setRoundBottomLeft(100);
		profilePanel.setRoundTopLeft(100);
		profilePanel.setRoundTopRight(100);
		profilePanel.setLayout(null);
		profilePanel.setBounds(20,20,400,620);
		profilePanel.setBackground(new Color(255,255,255));
		center.add(profilePanel, BorderLayout.CENTER);
		
		JLabel profile =new JLabel("My Profile");
		profile.setBounds(150,40,400,50);
		profile.setFont(new Font("Tahoma", Font.BOLD, 25));
		profilePanel.add(profile);
		
		
		JLabel nameLabel =new JLabel("Name:");
		nameLabel.setBounds(80,100,400,50);
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(nameLabel);
		
		JLabel name =new JLabel();
		name.setText(Global.currentUser.getName());
		name.setBounds(180,100,400,50);
		name.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(name);
		
		JLabel genderLabel =new JLabel("Gender:");
		genderLabel.setBounds(80,150,400,50);
		genderLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(genderLabel);
		
		
		JLabel gender =new JLabel();
		gender.setText(Global.currentUser.getGender());
		gender.setBounds(180,150,400,50);
		gender.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(gender);
		
		
		JLabel dobLabel =new JLabel("DOB:");
		dobLabel.setBounds(80,200,400,50);
		dobLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(dobLabel);
		
		
		JLabel dob =new JLabel();
		dob.setText(Global.currentUser.getDOB());
		dob.setBounds(180,200,400,50);
		dob.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(dob);
		
		
		JLabel mobileLabel =new JLabel("Mobile:");
		mobileLabel.setBounds(80,250,400,50);
		mobileLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(mobileLabel);
		
		JLabel mobile =new JLabel();
		mobile.setText(Global.currentUser.getMobile());
		mobile.setBounds(180,250,400,50);
		mobile.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(mobile);
		
		
		JLabel emailLabel =new JLabel("Email:");
		emailLabel.setBounds(80,300,400,50);
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(emailLabel);
		
		JLabel email =new JLabel();
		email.setText(Global.currentUser.getEmail());
		email.setBounds(180,300,400,50);
		email.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(email);
		
		
		JLabel addressLabel =new JLabel("Address:");
		addressLabel.setBounds(80,350,400,50);
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(addressLabel);
		
		
		JLabel address =new JLabel();
		address.setText(Global.currentUser.getAddress());
		address.setBounds(180,350,400,50);
		address.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(address);
		
		
		JLabel usernameLabel =new JLabel("Username:");
		usernameLabel.setBounds(80,400,400,50);
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(usernameLabel);
		
		
		JLabel username =new JLabel();
		username.setText(Global.currentUser.getUsername());
		username.setBounds(180,400,400,50);
		username.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(username);
		
		
		JLabel passwordLabel =new JLabel("Password:");
		passwordLabel.setBounds(80,450,400,50);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(passwordLabel);
		
		
		JLabel password =new JLabel();
		password.setText(Global.currentUser.getPassword());
		password.setBounds(180,450,400,50);
		password.setFont(new Font("Tahoma", Font.BOLD, 16));
		profilePanel.add(password);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//--------------------Booking Panel------------------
		PanelRound bookingPanel = new PanelRound();
		bookingPanel.setRoundBottomRight(100);
		bookingPanel.setRoundBottomLeft(100);
		bookingPanel.setRoundTopLeft(100);
		bookingPanel.setRoundTopRight(100);
		bookingPanel.setLayout(null);
		bookingPanel.setBounds(450,20,1050,620);
		bookingPanel.setBackground(new Color(255,255,255));
		center.add(bookingPanel, BorderLayout.CENTER);
		
		
		JLabel bookinglbl=new JLabel("CHECK FOR BOOKING", SwingConstants.CENTER);
		bookinglbl.setBounds(300,30,400,35);
		bookinglbl.setFont(new Font("Verdana",Font.BOLD,25));
		bookingPanel.add(bookinglbl);
		
		
		JLabel checkinLabel=new JLabel("Arrival Date: ");
		checkinLabel.setBounds(130,100,200,35);
		checkinLabel.setFont(new Font("Verdana",Font.PLAIN,18));
		bookingPanel.add(checkinLabel);
		
		Date date=new Date();
		
		checkin = new JDateChooser();
		checkin.setMinSelectableDate(date);
        checkin.setDateFormatString("yyyy-MM-dd");
		checkin.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		checkin.setFont(new Font("Verdana",Font.PLAIN,18));
		checkin.setBounds(260,100,200,30);
		bookingPanel.add(checkin);
		
		
		JLabel checkoutLabel=new JLabel("Departure Date: ");
		checkoutLabel.setBounds(550,100,200,35);
		checkoutLabel.setFont(new Font("Verdana",Font.PLAIN,18));
		bookingPanel.add(checkoutLabel);
		
		checkout1 = new JDateChooser();
		checkout1.setMinSelectableDate(date);
		checkout1.setDateFormatString("yyyy-MM-dd");
		checkout1.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		checkout1.setFont(new Font("Verdana",Font.PLAIN,18));
		checkout1.setBounds(710,100,200,30);
		bookingPanel.add(checkout1);
		
		
		JLabel bookingtype=new JLabel("Room Type:");
		bookingtype.setBounds(130,150,200,35);
		bookingtype.setFont(new Font("Verdana",Font.PLAIN,18));
		bookingPanel.add(bookingtype);
		
		Object[] h1= {"Single","Twin","Double"};
		
		bookingtypetxt=new JComboBox(h1);
		bookingtypetxt.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
		bookingtypetxt.setBounds(260,150,200,30);
		bookingtypetxt.setFont(new Font("Verdana",Font.PLAIN,18));
		bookingPanel.add(bookingtypetxt);
		
		
		JButton bookingclearbtn=new JButton("Clear");
		bookingclearbtn.setBounds(580,200,100,35);
		bookingclearbtn.setFocusable(false);
		bookingclearbtn.setBackground(new Color(0,0,0));
		bookingclearbtn.setForeground(Color.white);
		bookingclearbtn.setFont(new Font("Verdana",Font.PLAIN,18));
		bookingPanel.add(bookingclearbtn);
		bookingclearbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(ae.getSource()==bookingclearbtn) {
					
					checkin.setCalendar(null);
					checkout1.setCalendar(null);
					bookingtypetxt.setSelectedIndex(0);

				}}});
		
		
		customeridtxt=new JTextField();
		customeridtxt.setVisible(false);
		customeridtxt.setText(Integer.toString(Global.currentUser.getCustomer_ID()));
		bookingPanel.add(customeridtxt);
		
		
		bookingbtn=new JButton("Request Room");
		bookingbtn.setFocusable(false);
		bookingbtn.setBounds(380,200,170,35);
		bookingbtn.setBackground(new Color(0,0,0));
		bookingbtn.setForeground(Color.white);
		bookingbtn.addActionListener(this);
		bookingbtn.setFont(new Font("Verdana",Font.PLAIN,18));
		bookingPanel.add(bookingbtn);
		
		
		
		//------------------------Booking Table-----------------------
		
		Column = new Object[7];
		
		Column[0] = "Name";
		Column[1] = "Booking ID";
		Column[2] = "Check-In";
		Column[3] = "Check-Out";
		Column[4] = "Room Type";
		Column[5] = "Room ID";
		Column[6] = "Room Status";

		table = new JTable();
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(Column);

		update();
		
		
		JTableHeader heading = table.getTableHeader();
		heading.setBackground(Color.black);
		heading.setForeground(Color.white);
		heading.setFont(new Font("Verdana", Font.BOLD, 15));
		table.setFont(new Font("Verdana", Font.PLAIN, 16));
		table.setRowHeight(20);
		table.setBackground(new Color(255,255,255));
		table.setSelectionBackground(Color.BLACK);
		table.setSelectionForeground(Color.white);
		table.setBorder(null);
		sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		
		
		JScrollPane scroll1 = new JScrollPane(table);
		scroll1.setBounds(0, 260, 1050, 300);
		scroll1.setBorder( null );
		bookingPanel.add(scroll1);

		
		
		frame.setVisible(true);
		
		
	}
	
	
          public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==bookingbtn) {
			
            

			int cusid=Integer.parseInt(customeridtxt.getText());
			String str1 = ((JTextField)checkin.getDateEditor().getUiComponent()).getText();
            String str2 = ((JTextField)checkout1.getDateEditor().getUiComponent()).getText();
     
            String bookingtype1=bookingtypetxt.getSelectedItem().toString();
            
            
                   
            BookingLibs booking=new BookingLibs();
            
            booking.setCustomer_ID(cusid);
            booking.setCheckIn(str1);
            booking.setCheckOut(str2);
            booking.setBooking_Type(bookingtype1);
            booking.setBooking_Status("Requested");
            
            
            JDBCBooking jdbc=new JDBCBooking();
            boolean result=jdbc.insert(booking);
            
            if(result==true) {
            	
            	update();
            	
            	ImageIcon i=new ImageIcon(getClass().getResource("hotel (1).png"));
				JOptionPane.showMessageDialog(null, "You have requested successfully","Room Booking",JOptionPane.WIDTH,i);
            }

		
		}
	}
          
          
          
          
          
          private void update() {
     		 JDBCBooking jdbc = new JDBCBooking();
     			ArrayList Booking = jdbc.view_all1();
     			model.setRowCount(0);
     			if (Booking.size() > 0) {
     	            for (int i = 0; i < Booking.size(); i++) {
     	            	BookingLibs2 tmp_person = (BookingLibs2) Booking.get(i);
     	                
     	                Vector tmpPerson = new Vector();
     	                
     	                tmpPerson.add(tmp_person.getName());
     	                tmpPerson.add(tmp_person.getBooking_ID());
     	                tmpPerson.add(tmp_person.getCheckIn());
     	                tmpPerson.add(tmp_person.getCheckOut());
     	                tmpPerson.add(tmp_person.getBooking_Type());
     	                tmpPerson.add(tmp_person.getRoom_ID());
     	                tmpPerson.add(tmp_person.getBooking_Status());
     	                
     	               
     	                
     	                model.addRow(tmpPerson);
     	            }
     	        }
     	    }      
          
          
          
          
          
          
          

	public static void main(String[] args) {
		new Customer_Dashboard();

	}

	

}
