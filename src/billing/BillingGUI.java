package billing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import libs.BillingLibs2;
import libs.BillingLibs3;
import libs.BookingLibs;
import libs.Global;
import libs.JDBCBilling;
import libs.JDBCRoom;
import libs.Room;

public class BillingGUI implements MouseListener {
	JFrame frame;
	JTextField searchField;
	TableRowSorter sorter;
	JTable table1;
	JTextField credittxt,bookingidtxt,customeridtxt, nametxt, typetxt, roomratetxt, servicepricetxt, foodpricetxt, vattxt, servicechargetxt,
			totalbilltxt, totalpaidtxt, returnamounttxt, drinktxt, roomidtxt, noofdaystxt, roompricetxt;
	JComboBox statustxt, billingstatustxt;
	JButton generatebillbtn, calculatebillbtn;
	Object[] Column;
	DefaultTableModel model;
	ArrayList<BillingLibs2> Billing;
	JDateChooser c1, c2;

	public BillingGUI() {
		frame = new JFrame("Billing System");
		frame.setSize(1450, 770);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hotel.png")));
		frame.setLayout(new BorderLayout());

		// -----------------North Panel----------------------
		JPanel north = new JPanel();
		north.setLayout(null);
		north.setBackground(new Color(0,0,0));
		north.setPreferredSize(new Dimension(100, 100));
		frame.add(north, BorderLayout.NORTH);

		// --------------------Title Label-----------------------
		JLabel customer = new JLabel("CUSTOMER BILLING SYSTEM", SwingConstants.CENTER);
		customer.setBounds(500, 30, 400, 35);
		customer.setForeground(Color.white);
		customer.setFont(new Font("Verdana", Font.BOLD, 25));
		north.add(customer);

		// ------------------Search Label-------------------
		JLabel searchlbl = new JLabel("Search Customers: ");
		searchlbl.setBounds(10, 30, 210, 35);
		searchlbl.setForeground(Color.white);
		searchlbl.setFont(new Font("Verdana", Font.BOLD, 18));
		north.add(searchlbl);

		// ------------------Customer Search Field--------------------
		searchField = new JTextField();
		searchField.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		searchField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		searchField.setBounds(210, 30, 250, 30);
		north.add(searchField);

		searchField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search(searchField.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search(searchField.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search(searchField.getText());
			}

			public void search(String str) {
				if (str.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter(str));
				}
			}
		});

		// -----------------------Center Panel----------------------
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new BorderLayout());
		frame.add(centerPanel, BorderLayout.CENTER);
		
		//----------------------West Panel----------------------
		Column = new Object[7];
			
		Column[0] = "Name";
		Column[1]="Booking ID";
		Column[2] = "Check In";
		Column[3] = "Check Out";
		Column[4] = "Room ID";
		Column[5] = "Rate";
		Column[6] = "Status";
		
		

		table1 = new JTable();
		model = (DefaultTableModel) table1.getModel();
		model.setColumnIdentifiers(Column);

		update();
		table1.addMouseListener(this);
		JTableHeader h1 = table1.getTableHeader();
		h1.setBackground(Color.black);
		h1.setForeground(Color.white);
		h1.setFont(new Font("Verdana", Font.BOLD, 15));
		table1.setFont(new Font("Verdana", Font.PLAIN, 16));
		table1.setRowHeight(20);
		table1.setSelectionBackground(Color.BLACK);
		table1.setSelectionForeground(Color.white);
		sorter = new TableRowSorter<>(model);
		table1.setRowSorter(sorter);
		
		
		JScrollPane scroll1 = new JScrollPane(table1);
		scroll1.setPreferredSize(new Dimension(900,100));
		centerPanel.add(scroll1, BorderLayout.WEST);

		// *******************EAST Panel******************
		JPanel south = new JPanel();
		south.setLayout(null);
		south.setBackground(new Color(255,255,255));
		south.setPreferredSize(new Dimension(100, 280));
		centerPanel.add(south, BorderLayout.CENTER);

		roomidtxt = new JTextField();

		
		bookingidtxt=new JTextField();
		
		
		JLabel titleLabel = new JLabel("BILLING SYSTEM");
		titleLabel.setBounds(150, 10, 310, 35);
		titleLabel.setForeground(Color.black);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		south.add(titleLabel);
		
		JSeparator j1=new JSeparator();
		j1.setBounds(20, 50, 500, 35);
		south.add(j1);

		JLabel namelbl = new JLabel("Name:");
		namelbl.setBounds(10, 100, 210, 35);
		namelbl.setForeground(Color.black);
		namelbl.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(namelbl);

		nametxt = new JTextField();
		nametxt.setBounds(190, 100, 250, 30);
		nametxt.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(nametxt);
		
		JLabel checkinlbl = new JLabel("Check-In:");
		checkinlbl.setVisible(false);
		checkinlbl.setBounds(10, 220, 210, 35);
		checkinlbl.setForeground(Color.white);
		checkinlbl.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(checkinlbl);

		c1 = new JDateChooser();
		c1.setVisible(false);
		c1.setDateFormatString("yyyy-MM-dd");
		c1.setBounds(190, 220, 250, 30);
		c1.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		c1.setFont(new Font("Verdana", Font.PLAIN, 18));
		south.add(c1);

		JLabel checkoutlbl = new JLabel("Check Out: ");
		checkoutlbl.setVisible(false);
		checkoutlbl.setBounds(10, 270, 210, 35);
		checkoutlbl.setForeground(Color.white);
		checkoutlbl.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(checkoutlbl);

		c2 = new JDateChooser();
		c2.setVisible(false);
		c2.setDateFormatString("yyyy-MM-dd");
		c2.setBounds(160, 270, 210, 35);
		c2.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		c2.setFont(new Font("Verdana", Font.PLAIN, 18));
		south.add(c2);

		JLabel roompricelbl = new JLabel("Room Rate:");
		roompricelbl.setVisible(false);
		roompricelbl.setBounds(10, 120, 210, 35);
		roompricelbl.setForeground(Color.white);
		roompricelbl.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(roompricelbl);

		roomratetxt = new JTextField();
		roomratetxt.setVisible(false);
		roomratetxt.setBounds(190, 120, 250, 30);
		roomratetxt.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		roomratetxt.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(roomratetxt);
		
		JLabel noofdays = new JLabel("No of Days: ");
		noofdays.setBounds(10, 150, 210, 35);
		noofdays.setForeground(Color.black);
		noofdays.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(noofdays);

		noofdaystxt = new JTextField();
		noofdaystxt.setBounds(190, 150, 250, 30);
		noofdaystxt.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(noofdaystxt);
		
		JLabel roompricelbl1 = new JLabel("Room Price");
		roompricelbl1.setBounds(10, 200, 210, 35);
		roompricelbl1.setForeground(Color.black);
		roompricelbl1.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(roompricelbl1);

		roompricetxt = new JTextField();
		roompricetxt.setBounds(190, 200, 250, 30);
		roompricetxt.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(roompricetxt);


		JLabel vatlbl = new JLabel("VAT:");
		vatlbl.setBounds(10, 250, 210, 35);
		vatlbl.setForeground(Color.black);
		vatlbl.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(vatlbl);

		vattxt = new JTextField();
		vattxt.setText("13%");
		vattxt.setEditable(false);
		vattxt.setBounds(190, 250, 250, 30);
		vattxt.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(vattxt);

		JLabel servicechargelbl = new JLabel("Service Charge: ");
		servicechargelbl.setBounds(10, 300, 210, 35);
		servicechargelbl.setForeground(Color.black);
		servicechargelbl.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(servicechargelbl);

		servicechargetxt = new JTextField();
		servicechargetxt.setText("$ 500");
		servicechargetxt.setEditable(false);
		servicechargetxt.setBounds(190, 300, 250, 30);
		servicechargetxt.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(servicechargetxt);

		JLabel totalbilllbl = new JLabel("Total Bill: ");
		totalbilllbl.setBounds(10, 350, 210, 35);
		totalbilllbl.setForeground(Color.black);
		totalbilllbl.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(totalbilllbl);

		totalbilltxt = new JTextField();
		totalbilltxt.setBounds(190, 350, 250, 30);
		totalbilltxt.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(totalbilltxt);


		JLabel billingststaus = new JLabel("Billing Status: ");
		billingststaus.setBounds(10, 400, 210, 35);
		billingststaus.setForeground(Color.black);
		billingststaus.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(billingststaus);

		Object[] h55 = { "Paid", "Unpaid", "Due" };
		billingstatustxt = new JComboBox(h55);
		billingstatustxt.setBounds(190, 400, 250, 30);
		billingstatustxt.setFont(new Font("Verdana", Font.BOLD, 18));
		south.add(billingstatustxt);

		generatebillbtn = new JButton("Generate Bill");
		generatebillbtn.setFocusable(false);
		generatebillbtn.setBounds(60, 480, 200, 35);
		generatebillbtn.setFont(new Font("Verdana", Font.BOLD, 18));
		generatebillbtn.setForeground(Color.white);
		generatebillbtn.setBackground(new Color(0,0,0));
		south.add(generatebillbtn);
		generatebillbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == generatebillbtn) {

					//********************Update Room**********************
					Room room = new Room();
					int room_id = Integer.parseInt(roomidtxt.getText());
					room.setId(room_id);
					room.setStatus("Available");
					JDBCRoom jdbc1 = new JDBCRoom();
					boolean result1 = jdbc1.update1(room);
					
					
					//*******************Update Booking Status**************
					BookingLibs booking=new BookingLibs();
					booking.setBooking_ID(Integer.parseInt(bookingidtxt.getText()));
					booking.setBooking_Status("Inactive");
					JDBCBilling jdbc5=new JDBCBilling();
					boolean result5=jdbc5.update(booking);
					
					
					
					

					// ***********************Billing Insert****************
					BillingLibs3 billing = new BillingLibs3();
					
		
					billing.setBooking_ID(Integer.parseInt(bookingidtxt.getText()));
					billing.setName(nametxt.getText());
					billing.setVAT(13);
					billing.setService_Charge(500);
					billing.setRoom_Price(Integer.parseInt(roompricetxt.getText()));
					billing.setTotal_Bill(Double.parseDouble(totalbilltxt.getText()));
					billing.setBilling_Status(billingstatustxt.getSelectedItem().toString());

					

					Global.currentBilling = billing;

					JDBCBilling jdbc = new JDBCBilling();
					boolean result = jdbc.insert(billing);
					update();

				}

				new Invoice();

			
			}
		});

		JButton clearbtn = new JButton("Clear");
		clearbtn.setFocusable(false);
		clearbtn.setBounds(280, 480, 200, 35);
		clearbtn.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		clearbtn.setFont(new Font("Verdana", Font.BOLD, 18));
		clearbtn.setForeground(Color.white);
		clearbtn.setBackground(new Color(0,0,0));
		south.add(clearbtn);
		clearbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nametxt.setText(null);
				noofdaystxt.setText(null);
				roomratetxt.setText(null);
				roompricetxt.setText(null);
				totalbilltxt.setText(null);

			}

		});

		frame.setVisible(true);

	}

	
	
	private void update() {
		 JDBCBilling jdbc = new JDBCBilling();
			ArrayList Billing = jdbc.view_all1();
			model.setRowCount(0);
			if (Billing.size() > 0) {
	            for (int i = 0; i < Billing.size(); i++) {
	            	BillingLibs2 tmp_person = (BillingLibs2) Billing.get(i);
	                
	                Vector tmpPerson = new Vector();
	                
	                tmpPerson.add(tmp_person.getName());
	                tmpPerson.add(tmp_person.getBooking_ID());
	                tmpPerson.add(tmp_person.getCheckIn());
	                tmpPerson.add(tmp_person.getCheckOut());
	                tmpPerson.add(tmp_person.getRoom_ID());	                
	                tmpPerson.add(tmp_person.getRate());
	                tmpPerson.add(tmp_person.getRooms_status());
	                
	               
	                
	                model.addRow(tmpPerson);
	            }
	        }
	    }

	@Override
	public void mouseClicked(MouseEvent e) {

		try {

			int h1 = table1.getSelectedRow();

			TableModel model = table1.getModel();
			
			String bookingid=model.getValueAt(h1, 1).toString();
			bookingidtxt.setText(bookingid);


			String name = model.getValueAt(h1, 0).toString();
			nametxt.setText(name);

			

			String roomid = model.getValueAt(h1, 4).toString();
			roomidtxt.setText(roomid);

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(h1, 2));
			c1.setDate(date);

			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(h1, 3));
			c2.setDate(date2);

			String roomprice = model.getValueAt(h1, 5).toString();
			roomratetxt.setText(roomprice);

			

			String fromdate = ((JTextField) c1.getDateEditor().getUiComponent()).getText();
			String todate = ((JTextField) c2.getDateEditor().getUiComponent()).getText();

			LocalDate fday = LocalDate.parse(fromdate);
			LocalDate tday = LocalDate.parse(todate);

			Long day_gap = ChronoUnit.DAYS.between(fday, tday);
			noofdaystxt.setText(String.valueOf(day_gap));
			
			int rate=Integer.parseInt(roomratetxt.getText());			
			int days=Integer.parseInt(noofdaystxt.getText());			
			int result=rate*days;		
			roompricetxt.setText(String.valueOf(result));
			
			double roomprice1;
						
			roomprice1 = Integer.parseInt(roompricetxt.getText());
			

			double h5 = roomprice1;

			double h6 = 0.13;
			double h7 = h6 * h5;

			double h8 = h7 + h5 + 500;

			totalbilltxt.setText(Double.toString(h8));

		} catch (Exception ex) {
			System.out.println("Error" + ex.getMessage());
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new BillingGUI();
	}

}
